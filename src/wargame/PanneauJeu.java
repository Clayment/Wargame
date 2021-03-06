/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wargame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Classe gérant le panneau de jeu.
 * 
 * @author Team 7
 */
public class PanneauJeu extends JPanel implements ActionListener{
    
    private final Carte map;
    private ElementGraph[][] mapGraph;
    private FrameJeu Fenetre;

    /**
     * Constructeur du panneau.
     * @param map La map à rajouter sur le panneau.
     * @param fenetre La fenêtre (frame) sur laquelle sera le panneau.
     * @throws HeadlessException 
     */
    public PanneauJeu(Carte map, FrameJeu fenetre) throws HeadlessException {
        super();
        /* map du jeu */
        this.map = map;
        this.Fenetre = fenetre;
        this.setFocusable(true);
        
        /* Configuration du panel : taille, layout*/
        this.setPreferredSize(new Dimension(
                IConfig.LARGEUR_CARTE*IConfig.NB_PIX_CASE,
                IConfig.HAUTEUR_CARTE*IConfig.NB_PIX_CASE));
        this.setLayout(new GridBagLayout());
        
        /* Tableau^2 des ElementGraph : initialisation et ajout au panel */
        this.mapGraph = new ElementGraph[IConfig.LARGEUR_CARTE][IConfig.HAUTEUR_CARTE];
        for(int x = 0; x< IConfig.LARGEUR_CARTE;x++ ){
            for(int y = 0; y< IConfig.HAUTEUR_CARTE;y++ ){
                mapGraph[x][y] = new ElementGraph(map.getElement(x,y),this);
                map.getElement(x,y).setGraph(mapGraph[x][y]);
            }
        }
        this.addElements(mapGraph);
    }
    
    /**
     * Méthode ajoutant les éléments ???.
     * @param E La matrice d'éléments.
     */
    public final void addElements(ElementGraph[][] E){
        for(int x = 0; x< IConfig.LARGEUR_CARTE;x++ ){
            for(int y = 0; y< IConfig.HAUTEUR_CARTE;y++ ){
                this.add(E[x][y],E[x][y].getGridConstraint());
            }
        }
    }
    
    public void setAllAccessible(boolean b){
        for(int x = 0; x< IConfig.LARGEUR_CARTE;x++ ){
            for(int y = 0; y< IConfig.HAUTEUR_CARTE;y++ ){
                this.mapGraph[x][y].setMarkedAccessible(b);
            }
        }
    }

    public void ElementClic(ElementGraph E){
        if(E.isFoW())
            this.Fenetre.setInfoText("Fog of War, Even the elven eyes of Legolas can't see over here!");
        else
            this.Fenetre.setInfoText(E.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for(int x = 0; x< IConfig.LARGEUR_CARTE;x++ ){
            for(int y = 0; y< IConfig.HAUTEUR_CARTE;y++ ){
                this.mapGraph[x][y].repaint();
            }
        }
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(((ElementGraph)e.getSource()));
    }
    
    public Carte getMap() {
        return map;
    }    

    public void select(ElementGraph e) {
        this.getMap().select(e.getElement().getPos().getX(),e.getElement().getPos().getY());
        for(int x = 0; x< IConfig.LARGEUR_CARTE;x++ ){
            for(int y = 0; y< IConfig.HAUTEUR_CARTE;y++ ){
                this.mapGraph[x][y].setMarkedAccessible(false);
            }
        }
        if( map.isSelected()){
            int portee = map.getSelected().getPortee();
            int xO = map.getSelected().getPos().getX();
            int yO = map.getSelected().getPos().getY();
            for(int x = -portee; x<= portee; x++){
                for(int y = -portee; y<= portee; y++){
                    if(xO+x<IConfig.LARGEUR_CARTE && yO+y<IConfig.HAUTEUR_CARTE && xO+x>=0 && yO+y>=0)
                        mapGraph[xO+x][yO+y].setMarkedAccessible(true);
                }
            }
//            this.getMap().select(map.getSelected().getPos().getX(),map.getSelected().getPos().getY());
        }
        this.repaint();
    }
    
}
