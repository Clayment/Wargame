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
 * Classe gérant tout ce qui a trait aux positions dans la map, distance, égalité etc.
 * 
 * @author Team 7
 */

public class Position implements IConfig {
    private int x, y;
	
    /**
     * Constructeur de position.
     * @param x Coordonnée horizontale.
     * @param y Coordonnée verticale.
     */    
    Position(int x, int y) {
        this.x = x; this.y = y;
    }
    
    /**
     * Accesseur pour x, la coordonnée horizontale.
     * @return La coordonnée horizontale.
     */
    public int getX() {
        return x;
    }

    /**
     * Accesseur pour y, la coordonnée verticale.
     * @return La coordonnée verticale.
     */
    public int getY() {
        return y;
    }

    /**
     * Mutateur pour x, la coordonnée horizontale.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Mutateur pour y, la coordonnée verticale.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Vérifie si la position est bien comprise dans la map.
     * @return La position est-elle bien dans la carte ?
     */
    public boolean estValide() {
            if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) 
                return false; 
            else 
                return true;
    }

    /**
     * Réécriture de la méthode toString pour afficher les coordonnées.
     * @return L'affichage des coordonnées.
     */
    public String toString() {
        return "("+x+","+y+")";
    }

    /**
     * Méthode vérifiant si la position spécifiée en argument est à côté de la case spécifiée.
     * @param pos Position à tester.
     * @return Cette position est-elle bien voisine à la position en argument ?
     */
    public boolean estVoisine(Position pos) {
            return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
    }

    /**
     * Méthode surchargant la méthode suivante en lui permettant de prendre une position en argument.
     * @param pos Position à partir de laquelle on calcule la distance.
     * @return ???          //TODO
     */
    public int distance(Position pos){
        return(distance(pos.getX(), pos.getY()));
    }

    /**
     * Méthode calculant la distance entre deux points.
     * @param x1 Coordonnée horizontale
     * @param y1 Coordonnée verticale.
     * @return La distance la plus grande entre l'horizontale et la verticale.
     */
    public int distance(int x1, int y1){
        int dx = Math.abs(x - x1);
        int dy = Math.abs(y - y1);
        return((int) ((dx > dy)?dx:dy));
    }

    /**
     * Réécriture de la méthode equals vérifiant que deux positions sont égales.
     * @param p Deuxième position.
     * @return Sont-elles égales ?
     */
    public boolean equals(Position p){
        return(x == p.getX() && y == p.getY());
    }
}