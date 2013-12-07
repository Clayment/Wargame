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

import java.awt.Color;

/**
 * Classe gérant les obstacles.
 * 
 * @author Team 7
 */

public class Obstacle extends Element {
    public enum TypeObstacle {
        ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
        private final Color COULEUR;
        TypeObstacle(Color couleur) {
            COULEUR = couleur;
        }
        public static TypeObstacle getObstacleAlea() {
            return values()[(int)(Math.random()*values().length)];
        }
    }
    private TypeObstacle TYPE;
    
    public Obstacle(TypeObstacle type, Position pos) {
        super(pos); 
        TYPE = type;
    }
    
    public String toString() {
        return ""+TYPE;
    }

}