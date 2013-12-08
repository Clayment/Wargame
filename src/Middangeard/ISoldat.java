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
 * Interface configurant tout ce qui a trait
 * aux soldats, leur puissance d'attaque au CàC, à distance
 * leur portée, leurs PV.
 * 
 * @author Team 7
 */
public interface ISoldat {
    int MAX_HEAL = 5;
    
    public static enum TypesH {
        HUMAIN (50,3,10,2,SoldatEnum.humain), 
        NAIN (80,3,20,0,SoldatEnum.nain), 
        ELF (40,5,10,6,SoldatEnum.elfe), 
        HALFELIN (20,3,5,2,SoldatEnum.halfelin);

        private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
        
        private final SoldatEnum sprite;
        
        TypesH(int points, int portee, int puissance, int tir, SoldatEnum sprite) {
                POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
                PUISSANCE = puissance; TIR = tir; this.sprite = sprite;
        }
        
        public int getPoints() {
            return POINTS_DE_VIE;
        }
        
        public int getPortee() {
            return PORTEE_VISUELLE;
        }
        
        public int getPuissance() {
            return PUISSANCE;
        }
        
        public int getTir() {
            return TIR;
        }

        public SoldatEnum getSprite() {
            return sprite;
        }

        public static TypesH getTypeHAlea() {
                return values()[(int)(Math.random()*values().length)];
        }
    }
    
    public static enum TypesM {
        TROLL (80,1,30,0,SoldatEnum.troll),
        ORC (60,2,10,3,SoldatEnum.orc),
        GOBELIN (20,4,5,2,SoldatEnum.gobelin),
        UNDEAD (20,2,5,2,SoldatEnum.undead);

        private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
        private final SoldatEnum sprite;
        TypesM(int points, int portee, int puissance, int tir, SoldatEnum sprite) {
            POINTS_DE_VIE = points; 
            PORTEE_VISUELLE = portee;
            PUISSANCE = puissance;
            TIR = tir;
            this.sprite = sprite;
        }
        
        public int getPoints() {
            return POINTS_DE_VIE; 
        }
        
        public int getPortee() {
            return PORTEE_VISUELLE;
        }
        
        public int getPuissance() {
            return PUISSANCE;
        }
        
        public int getTir() {
            return TIR;
        } 

        public SoldatEnum getSprite() {
            return sprite;
        }

        public static TypesM getTypeMAlea() {
            return values()[(int)(Math.random()*values().length)];
        }
    }
    int getPoints();
    int getPortee();
    boolean joueTour();
    void combat(Soldat soldat);
    void seDeplace(Position newPos);
}