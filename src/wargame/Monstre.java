package wargame;

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
    
    /* TEST D'IA */
    
    
    public void mouvMonstre(ArrayList<Element> L, Carte m){
        for(Element E : L){
            if(!E.estLibre()){
                if(E.getSoldat() instanceof Heros){
/* TEST */          //System.out.println(toString() + getPos().toString() + " attaque " + E.getSoldat().toString());
                    this.combat(E.getSoldat());
                    return;
                }
            }
        }
        if(this.getPoints() < this.getMaxPoints()){
/* TEST */  //System.out.println(toString() + " needs a medic !!!");
            this.guerir();
        }
        else{
            int j = (int) (Math.random()) * L.size();
            Position Posi = new Position(L.get(j).getPos().getX(), L.get(j).getPos().getY());
            this.seDeplace(Posi);
        }
    }
    
    /* FIN TEST D'IA */
    
}
