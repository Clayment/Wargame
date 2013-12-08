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
        String output = this.race.name().substring(0, 1).toUpperCase() + this.race.name().substring(1).toLowerCase();
        return(pv + "/" + maxPv + "pv " + output);
    }
    
    /* TEST D'IA */
    
    /*
    public void mouvMonstre(ArrayList<Element> L, Carte m){
        for(Element E : L){
            if(!E.estLibre()){
                if(E.getSoldat() instanceof Heros){
                    this.combat(m.getElement(getPos().getX(), getPos().getY()).getSoldat());
                    break;
                }
                else if(E.getSoldat().pv < E.getSoldat().maxPv){
                    this.guerir();
                    break;
                }
                else{
                    int j = (int) (Math.random()) * L.size();
                    Position Posi = new Position(L.get(j).getPos().getX(), L.get(j).getPos().getY());
                    this.seDeplace(Posi);
                    break;
                }
            }
        }
    } 
    */
    
    /* FIN TEST D'IA */
    
}
