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
 *
 * @author Team 7
 */
public enum SoldatEnum{
    nain ("sprite/Nain.png"),
    humain ("sprite/Humain.png"),
    elfe ("sprite/Elfe.png"),
    halfelin ("sprite/Halfelin.png"),
    troll ("sprite/Troll.png"),
    orc ("sprite/Orc.png"),
    gobelin ("sprite/Gobelin.png"),
    undead ("sprite/Undead.png"),
    dead ("sprite/Dead.png")
    ;
    
    
     private Sprite sprite;
    /**
     * Constructeur qui crée un sprite avec l'image
     * située à l'URL spécifiée.
     * @param url URL de l'image désirée en sprite.
     */
    private SoldatEnum(String url) {
        this.sprite = new Sprite(url);
    }
    
    /**
     * Constructeur qui crée un sprite avec les images
     * dont les URLs sont données dans le tableau en argument.
     * @param url de type String[]
     */
    private SoldatEnum(String url[]) {
         this.sprite = new Sprite(url);
    }

    /**
     * Accesseur du sprite.
     * @return Le sprite demandé.
     */
    public Sprite getSprite() {
        return sprite;
    }
    
    
}
