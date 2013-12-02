/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wargame;

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
public class ISprite {
    
    private ArrayList<URL> url;
    private ArrayList<BufferedImage> image;

       /**
     * Constructeur
     * 
     * @param url de type URL
     */
    public ISprite(URL url) {
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
     * Constructeur
     * 
     * @param url de type String
     */
    public ISprite(String url) {
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
     * Constructeur
     * 
     * @param url de type String
     */
    public ISprite(String[] url) {
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
     * Getter pour URL ???.
     * 
     * @return l'URL.
     */
    public URL getURL(){
        return this.url.get(0);
    }

    /**
     * Getter pour l'image dans le buffer ??.
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
