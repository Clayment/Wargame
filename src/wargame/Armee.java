package wargame;

    /**
     * Classe gérant tous les aspects de l'armée, les soldats, leur quantité, l'armée est-elle toujours debout ou non.
     * 
     * @author Team 7 
     */

public class Armee {
    private Soldat[] soldats;
    private int taille;
    private int effectif = 0;
    
    /**
     * Constructeur de la classe Armee.
     *
     * @param n nombre de soldats dans l'armee
     */
    public Armee(int n){
        soldats = new Soldat[n];
        taille = n;
    }
    
    /* Fonctions de gestion de l'armée */
    
    /** 
     * Compte le nombre de soldats de l'armée spécifiée en paramètre.
     *
     * @return Effectif de l'armée
     */
    public int recensement(){
        return(effectif);
    }
    
    /**
     * Teste si l'armée est au complet ou non.
     * 
     * @return L'armée est-elle complète ?
     */
    public boolean complete(){
        return(recensement() == taille);
    }

    /**
     * Teste si l'armée a été entièrement vaincue. 
     * 
     * @return L'armée a-t-elle été décimée ?
     */
    public boolean vaincu(){
        int actifs = 0;
        for (int i=0; i<recensement(); i++)
            if (!soldats[i].estMort())
                return(false);
        return(true);
    }
    
    /* GESTION DES SOLDATS */
    
    /**
     * Méthode ajoutant un soldat dans une armée.
     * 
     * @param recrue Type du soldat à ajouter dans l'armée.
     * 
     * @return Le soldat a-t-il pu être rajouté ?
     */
    public boolean ajouteSoldat(Soldat recrue){
        if (complete())
            return(false);
        soldats[effectif] = recrue;
        effectif ++;
        return(true);
    }
    
    /**
     * Méthode renvoyant le soldat de numéro i de l'armée.
     * 
     * @param i Numéro du soldat à retourner.
     * 
     * @return Le soldat de numéro i.
     */
    public Soldat getSoldat(int i){
        return(soldats[i]);
    }
}
