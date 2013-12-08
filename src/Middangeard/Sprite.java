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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Team 7
 */
public class Sprite {
    
    private ArrayList<URL> url;
    private ArrayList<BufferedImage> image;

    /**
     * Constructeur                     //TODO
     * 
     * @param url de type URL
     */
    public Sprite(URL url) {
        this.url = new ArrayList<>();
        this.image = new ArrayList<>();
        this.url.add(url);
        try {
            this.image.add(ImageIO.read(url));
        } catch (IOException ex) {
            Logger.getLogger(BackgroundEnum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Constructeur                                 //TODO
     * 
     * @param url de type String
     */
    public Sprite(String url) {
        this.url = new ArrayList<>();
        this.image = new ArrayList<>();
        this.url.add(getClass().getResource(url));
        try {
            this.image.add(ImageIO.read(getClass().getResource(url)));
        } catch (IOException ex) {
            Logger.getLogger(SoldatEnum.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    /**
     * Constructeur                         //TODO
     * 
     * @param url de type String
     */
    public Sprite(String[] url) {
        for(int i = 0; i<url.length; i++){
            this.url.add(getClass().getResource(url[i]));
            try {
                this.image.add(ImageIO.read(getClass().getResource(url[i])));
            } catch (IOException ex) {
                Logger.getLogger(SoldatEnum.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    
    /**
     * Getter pour URL ???.                         //TODO
     * 
     * @return l'URL.
     */
    public URL getURL(){
        return this.url.get(0);
    }

    /**
     * Getter pour l'image dans le buffer ??.       //TODO
     * 
     * @return l'image.
     */
    public BufferedImage getImage() {
        return this.image.get(0);
    }

    public BufferedImage getRandImage() {
        return this.image.get((int)Math.random()*this.image.size());
    }
    
}
