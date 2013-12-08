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
public class Wargame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialisation de la carte
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Carte map = new Carte();
        map.generateMap();
        map.initSoldats();
        FrameJeu F = new FrameJeu(map);
        map.setFog();
       
        /* Gestion des tours */
        //while(!map.fini()){
            /* Tour des Héros */
            // Attente de l'appui sur "fin du tour"
        //}
    }    
}
