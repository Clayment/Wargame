/*
 * All the following code was written by the Team 7 Developers
 * Clément Horgues, Alexis Braine et Arslen Remaci
 * with the help of Nathan Ingrao for the sprites and the tiles.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This game is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package Middangeard;

import java.util.ArrayList;

/**
 * Classe gérant les monstres, personnages non-jouables du jeu.
 * 
 * @author Team 7
 */

public class Monstre extends Soldat{
    private final TypesM race;
    
    
    /**
     * Constructeur de la classe Monstre.
     * @param race Nom de la race voulue pour le monstre à créer.
     * @param coords Coordonnées où le monstre nouvellement créé sera placé.
     */
    public Monstre(TypesM race, Position coords){
        super(race.getPoints(), race.getPortee(), race.getPuissance(), race.getTir(), coords, race.getSprite());
        this.race = race;
    }
    
    /**
     * Redéfinition de la méthode toString pour afficher les détails de la case ciblée dans le bas de la fenêtre.
     * @return La race et les PV du soldat ciblé.
     */
    public String toString(){
        return(pv + "/" + maxPv + "pv " + this.race.name());
    }
    
    /**
     * Méthode mettant en place le tour des ennemis,
     * attaquer une cible si elle est dans la portée,
     * se soigner si la vie descend en dessous d'un seuil critique,
     * et se déplacer sinon.
     * @param L ArrayList contenant les cases dans la portée du monstre.
     * @param m Map.
     */
    public void mouvMonstre(ArrayList<Element> L, Carte m){
        for(Element E : L){
            if(!E.estLibre()){
                if(E.getSoldat() instanceof Heros){
                    this.combat(E.getSoldat());
                    return;
                }
            }
        }
        if(this.getPoints() < (int) ((this.getMaxPoints())/4)){
            this.guerir();
        }
        else{
            int j = (int) ((Math.random()) * L.size());
            while(!L.get(j).estLibre()){
                j = (int) ((Math.random()) * L.size());
            }
            Position Posi = new Position(L.get(j).getPos().getX(), L.get(j).getPos().getY());
            m.getElement(this.getPos()).enleveSoldat();
            this.seDeplace(Posi);
            m.getElement(Posi).ajouteSoldat(this);
        }
    }
}
