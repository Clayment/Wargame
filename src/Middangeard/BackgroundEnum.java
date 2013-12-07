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
 * Classe servant à l'énumération des sprites des obstacles, du Fog of War, et des cases accessibles.
 * 
 * @author Team 7
 */

/**
 * Enumération des différents sprites.
 */
public enum BackgroundEnum{
    water ("sprite/Eau.png"),
    plain ("sprite/Plaine.png"),
    forest ("sprite/Foret.png"),
    mountain ("sprite/Montagne.png"),
    fow ("sprite/FOW.png"),
    accessible ("sprite/Accessible.png");
    
    private ISprite sprite;
    /**
     * Constructeur
     * @param url de type String
     */
    private BackgroundEnum(String url) {
        this.sprite = new ISprite(url);
    }
    
    /**
     * Constructeur
     * @param url de type String[]
     */
    private BackgroundEnum(String url[]) {
         this.sprite = new ISprite(url);
    }

    /**
     * ???
     * @return 
     */
    public ISprite getSprite() {
        return sprite;
    }
    
    
}
