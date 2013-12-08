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
 * Interface déclarant les variables
 * de configuration du jeu.
 * 
 * @author Team 7
 */
public interface IConfig {
    int LARGEUR_CARTE = 25; int HAUTEUR_CARTE = 15;                         // en nombre de cases
    int NB_PIX_CASE = 40;
    int POSITION_X = 100; int POSITION_Y = 50;                              // Position de la fenêtre
    int NB_HEROS = 15; int NB_MONSTRES = 30; int NB_OBSTACLES = 100;
    Color COULEUR_NEUTRE = Color.magenta, COULEUR_TEXTE = Color.black;
    Color COULEUR_HEROS = Color.red, COULEUR_HEROS_OFF = Color.pink;
    Color COULEUR_MONSTRES = Color.black;
    Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
}