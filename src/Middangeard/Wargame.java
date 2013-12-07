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
public class Wargame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialisation de la carte
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Carte map = new Carte();
        FrameJeu F = new FrameJeu(map);
        map.setFog();
        
        
        /* Gestion des tours */
        while(!map.fini()){
            /* Tour des Héros */
            
            // Attente de l'appui sur "fin du tour"
        }
    }    
}
