/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wargame;

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
     * 
     * @param url de type String
     */
    private BackgroundEnum(String url) {
        this.sprite = new ISprite(url);
    }
    
    /**
     * Constructeur
     * 
     * @param url de type String[]
     */
    private BackgroundEnum(String url[]) {
         this.sprite = new ISprite(url);
    }

    public ISprite getSprite() {
        return sprite;
    }
    
    
}
