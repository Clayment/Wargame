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

/**
 * Classe gérant les héros, personnages jouables du jeu.
 * 
 * @author Team 7
 */

public class Heros extends Soldat{
    private final TypesH race;
    
    
    /**
     * Constructeur de la classe Héros.
     * @param race Nom de la race voulue pour le héros à créer.
     * @param coords Coordonnées où le héros nouvellement créé sera placé.
     */
    public Heros(TypesH race, Position coords){
        super(race.getPoints(), race.getPortee(), race.getPuissance(), race.getTir(), coords,race.getSprite());
        this.race = race;
    }
    
    /**
     * Redéfinition de la méthode toString pour afficher les détails de la case ciblée dans le bas de la fenêtre.
     * @return La race et les PV du soldat ciblé.
     */
    public String toString(){
        return(pv + "/" + maxPv + "pv " + this.race.name());
    }
}
