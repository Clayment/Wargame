package wargame;

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
