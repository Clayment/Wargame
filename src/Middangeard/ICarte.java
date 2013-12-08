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
 * Interface de la carte, éléments, déplacements et mort.
 * 
 * @author Team 7
 */
public interface ICarte {
    Element getElement(Position pos);
    boolean deplaceSoldat(Position pos, Soldat soldat);
    void mort(Soldat perso);
}