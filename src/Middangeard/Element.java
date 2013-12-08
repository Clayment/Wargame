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
 * Classe gérant les éléments sur une carte, obstacles, "biomes", soldats.
 * 
 * @author Team 7
 */

public class Element implements IConfig {
    protected Position pos;
    private Soldat soldat;                                                      // Si un soldat se trouve au niveau de cet élément
    protected BackgroundEnum type;
    protected ElementGraph graph;
    protected int vu = 0;
    private boolean tombstone = false;
    
    
    /**
     * Constructeur d'élément par défaut dans une position précisée en argument.
     * @param pos Position de l'élément à créer sur la carte.
     */
    public Element(Position pos){
        this.pos = new Position(pos.getX(), pos.getY());
        this.type = BackgroundEnum.forest;
        this.graph = null;
    }
    
    /**
     * Constructeur d'élément plus détaillé, avec position et type d'élément à spécifier en argument.
     * @param pos Position de l'élément à créer sur la carte.
     * @param type Type de l'élément à créer.
     */
    public Element(Position pos,BackgroundEnum type){
        this.pos = new Position(pos.getX(), pos.getY());
        this.type = type;
        this.graph = null;
    }
    
    /* GESTION DES SOLDATS */
    
    /**
     * Méthode ajoutant un soldat sur le champ de bataille.
     * @param soldat Soldat à ajouter sur la carte.
     * @return L'ajout s'est-il bien déroulé ?
     */
    public boolean ajouteSoldat(Soldat soldat){
        if (!estLibre())
            return(false);
        this.soldat = soldat;
        return(true);
    }
    
    /**
     * Méthode retirant un soldat de la carte.
     */
    public void enleveSoldat(){
        this.soldat = null;
    }
    
    /**
     * Vérifie si la case ne contient pas de soldat.
     * @return Cette case contient-elle un soldat ?
     */
    public boolean estLibre(){
        return(this.soldat == null);
    }
    
    /**
     * Méthode pour obtenir le soldat d'une case de la carte.
     * @return Le soldat, rien si il n'y a pas de soldat sur la case.
     */
    public Soldat getSoldat(){
        if(this.estLibre())
            return null;
        return(this.soldat);
    }

    /* AUTRES */
    
    /**
     * Getter permettant d'obtenir une position.
     * @return La position demandée.
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Getter permettant d'obtenir un type.         //TODO
     * @return Le type demandé.
     */
    public BackgroundEnum getType() {
        return type;
    }

    /**
     * Getter permettant d'obtenir un graphe ???.   //TODO
     * @return Le graphe demandé ???.
     */
    public ElementGraph getGraph() {
        return graph;
    }

    /**
     * Setter fixant le graphe ???.         //TODO
     * @param graph 
     */
    public void setGraph(ElementGraph graph) {
        this.graph = graph;
    }

    /**
     * Setter fixant le type d'un élément.
     * @param type Type désiré.
     */
    public void setType(BackgroundEnum type) {
        this.type = type;
    }
    
    /**
     * Méthode fixant la valeur de vu, servant pour le Fog of War.
     */
    public void decouvrir(){
        this.vu ++;
    }
    
    /**
     * Méthode fixant la valeur de vu, servant pour le Fog of War.
     */
    public void cacher(){
        this.vu --;
    }
    
    /**
     * Méthode vérifiant si le Fog of War est présent sur la case.
     * @return FoW présent ?
     */
    public boolean isFoW() {
        return (this.vu == 0);
    }
    
    /**
     * Méthode appliquant les nouveaux tours ?.     //TODO
     * @see Soldat
     */
    public void nouveauTour(){
        if (this.soldat != null){
            this.soldat.nouveauTour();
        }
    }
}
