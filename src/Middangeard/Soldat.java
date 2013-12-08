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
 * Classe gérant tous les aspects des soldats, héros et monstres.
 * @author Team 7
 */
public class Soldat implements ISoldat{
    protected final int maxPv;      // Vie maximale de l'unité
    private final int portee;       // Portée maximale, 0 pour les unités de CaC
    private final int puissance;    // Dégats maximaux au CaC
    private final int tir;          // Dégats maximaux à distance
    protected int pv;               // Nombre de points de vie actuels de l'unité (<= maxPv)
    private Position pos;
    private boolean aJoue;          // Indique si le tour est consommé ou non
    protected SoldatEnum sprite;
    
    /* CONSTRUCTEUR*/
    
    /**
     * Constructeur de la classe Soldat.
     * @param maxPv Le maximum de PV de l'unité.
     * @param portee La portée de l'unité.
     * @param puissance La puissance d'attaque de l'unité.
     * @param tir La puissance d'attaque à distance de l'unité.
     * @param coords Coordonnées de l'unité.
     * @param sprite Sprite de l'unité.
     */
    public Soldat(int maxPv, int portee, int puissance, int tir, Position
     coords,SoldatEnum sprite){
        this.maxPv = maxPv;
        this.pv = maxPv;            // Initialisation des points de vie au maximum
        this.portee = portee;
        this.puissance = puissance;
        this.tir = tir;
        this.seDeplace(coords);     // Placement à la position de départ
        this.sprite = sprite;
    }
    
    /* COMBAT */
    
    /**
     * Méthode mettant en place le combat entre unités.
     * @param soldat Unité à combattre.
     */
    public void combat(Soldat soldat){
        // Attaque
        if(!estMort() && !soldat.estMort()){
            soldat.blesser(attaque(soldat));
        }
        
        // Riposte de l'ennemi
        if (!soldat.estMort()){
            blesser(soldat.attaque(this));
        }
        joueTour();
    }

    /**
     * Méthode ajoutant l'attaque d'unités.
     * @param soldat Soldat à attaquer.
     * @return Quantité de dégâts infligées.
     */
    private int attaque(Soldat soldat){
        int maxDegats = 0;
        /* Combat au CaC */
        if (distance(soldat) == 1){
            maxDegats = this.puissance;
        }
        /* Combat à distance */
        else if (distance(soldat) <= portee)
            maxDegats = this.tir;
        /* L'ennemi est hors de portée */
        else
            maxDegats = 0;
        
        // Jet de dégats sur 1d{maxDegats}
        int degats = (int) (Math.random() * maxDegats);
        return(degats);
    }
    
    /**
     * Méthode modifiant la quantité de PV d'une unité après avoir été attaquée.
     * @param degats Quantité de dégâts subies.
     */
    public void blesser(int degats){
        this.pv -= degats;
    }

    /**
     * Méthode rendant un certain montant aléatoire de PV à une unité.
     */
    public void guerir(){
        this.soigner((int) (Math.random() * ISoldat.MAX_HEAL) + 1);
    }

    /**
     * Méthode utilisée pour soigner une unité
     * @param soin Quantité de PV rendus.
     */
    public void soigner(int soin){
        this.pv += soin;
        if (this.pv > this.maxPv){
            this.pv = this.maxPv;
        }
    }
    
    /* POSITION */
    
    /**
     * Méthode utilisée pour déplacer une unité.
     * @param newPos Position désirée pour l'unité.
     */
    public void seDeplace(Position newPos){
        this.pos = new Position(newPos.getX(), newPos.getY());
    }

    /**
     *                  //TODO
     * @param soldat
     * @return 
     */
    public int distance(Soldat soldat){
        Position posEnnemi = soldat.getPos();
        double dx = Math.abs(pos.getX() - posEnnemi.getX());
        double dy = Math.abs(pos.getY() - posEnnemi.getY());
        int distance = (int) ((dx > dy)?dx:dy);
        return(distance);
    }
    
    /**
     *                  //TODO
     * @param C
     * @return 
     */
    public ArrayList<Element> getAPortee(Carte C){
        ArrayList<Element> L = new ArrayList<>();
        int xO = this.getPos().getX();
        int yO = this.getPos().getY();
        int numordre = 0;
        int i = this.getPortee();
        for(int x = -i; x<=i;x++){
            for(int y = -i; y<=i;y++){
                if(xO+x<IConfig.LARGEUR_CARTE && yO+y<IConfig.HAUTEUR_CARTE && xO+x>=0 && yO+y>=0){
                    numordre++;
                    L.add(C.getElement(xO+x, yO+y));
                }
            }
        }
        return L;
    }
    
    /* AUTRES... */
    
    /**
     * Méthode testant si une unité a déjà épuisé son tour.
     * @return Tour épuisé ?
     */
    public boolean joueTour(){
        if (aJoue())
            return(false);
        else{
            this.aJoue = true;
            return(true);
        }
    }
    
    /**
     * Méthode retournant le booléen de tour de jeu.
     * @return Bool de tour.
     */
    public boolean aJoue(){
        return(this.aJoue);
    }
    
    /**
     * Méthode vérifiant si l'unité est morte ou non.
     * @return 
     */
    public boolean estMort(){
        return(this.pv <= 0);
    }
    
    /**
     * Méthode exécutant le nouveau tour.
     */
    public void nouveauTour(){
        if (!aJoue() && !estMort()){
            this.guerir();
        }
        
        this.aJoue = false;
    }
    
    /* Accesseurs et mutateurs */

    /**
     * Accesseur PV.
     * @return Nombre de PV.
     */
    public int getPoints(){
        return(this.pv);
    }

    /**
     * Accesseur PVmax.
     * @return Nombre de PV Max.
     */
    public int getMaxPoints(){
        return(this.maxPv);
    }

    /**
     * Accesseur portée.
     * @return Portée de l'unité.
     */
    public int getPortee(){
        return(this.portee);
    }

    /**
     * Accesseur puissance d'attaque.
     * @return Puissance d'attaque de l'unité.
     */
    public int getPuissance(){
        return(this.puissance);
    }
    
    /**
     * Accesseur puissance d'attaque à distance.
     * @return Puissance d'attaque de l'unité.
     */
    public int getTir(){
        return(this.tir);
    }
    
    /**
     * Accesseur position de l'unité.
     * @return Position de l'unité.
     */
    public Position getPos(){
        return(this.pos);
    }

    /**
     * Accesseur sprite.
     * @return Sprite de l'unité.
     */
    public SoldatEnum getSprite() {
        return sprite;
    }
}