/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wargame;

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
    
    
     private ISprite sprite;
    /**
     * Constructeur
     * 
     * @param url de type String
     */
    private SoldatEnum(String url) {
        this.sprite = new ISprite(url);
    }
    
    /**
     * Constructeur
     * 
     * @param url de type String[]
     */
    private SoldatEnum(String url[]) {
         this.sprite = new ISprite(url);
    }

    public ISprite getSprite() {
        return sprite;
    }
    
    
}
