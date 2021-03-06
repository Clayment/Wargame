/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;

/**
 *
 * @author Clayment
 */
public class ElementGraph extends AbstractButton implements MouseListener{
    private Element e; //Element correspondant
    private GridBagConstraints c; //Contrainte pour le grid Bag du père
    private PanneauJeu panelPere; //Bijection du père
    private String tooltip; //Préfixe du Tooltip, peut etre utilisé pour construire la chaine du tooltip
    private boolean markedAccessible; //La case est elle accessible (sprite supplémentaire)
    private static Position lastPos; //Pour le Drag and Drop
    public int TEST;

    public ElementGraph(Element e,PanneauJeu panelPere) {
        super();
        /*Init */
        lastPos = null;
        this.panelPere = panelPere;
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE));
        this.setSize(new Dimension(IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE));
        this.e = e;
        this.c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridheight = c.gridwidth = 1;
        c.weightx = c.weighty = 0;
        c.gridx = e.getPos().getX();
        c.gridy = e.getPos().getY();
        this.tooltip="";
        this.markedAccessible=false;
        this.addMouseListener(this);
    }

    public Element getElement() {
        return e;
    }

    public GridBagConstraints getGridConstraint() {
        return c;
    }

    public boolean isMarkedAccessible() {
        return markedAccessible;
    }

    public void setMarkedAccessible(boolean markedAccessible) {
        if(this.e.getType() != BackgroundEnum.water || (!this.e.estLibre() && this.e.getSoldat() instanceof Heros))
        this.markedAccessible = markedAccessible;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* TOOLTIP */
        if(!this.e.isFoW() && !this.e.estLibre())
            this.setToolTipText(this.tooltip+this.e.getSoldat().toString());
        else 
            this.setToolTipText(this.tooltip);
        
        /* Draw terrain*/
        if(e.isFoW()){
            this.drawTerrain(g, BackgroundEnum.fow);  
        }else{
            this.drawTerrain(g, e.getType());
        }
        /* Draw Soldat*/
        if(!e.estLibre()){
            if(e.getSoldat().estMort()){
                this.drawSoldat(g, SoldatEnum.dead);
            }else{
                if(e.getSoldat() instanceof Heros)
                    this.drawSoldat(g, e.getSoldat()); //((Heros)e.getSoldat()).getSprite());
                else if(e.getSoldat() instanceof Monstre && !e.isFoW())
                    this.drawSoldat(g, e.getSoldat()); //((Monstre)e.getSoldat()).getSprite());
            }
        }
        /* Draw Accessible*/
        if(this.isMarkedAccessible()){
            this.drawAccessible(g);
        }
        
//        this.drawNumber();
        /* Draw Bord*/
        g.drawLine(0, 0, IConfig.NB_PIX_CASE, 0);
        g.drawLine(0, 0, 0, IConfig.NB_PIX_CASE);
        
    }
    
    private void drawAccessible(Graphics g){
        g.drawImage(BackgroundEnum.accessible.getSprite().getImage(), 0, 0, null);
    }
    
    
    private void drawSoldat(Graphics g,SoldatEnum s){
        g.drawImage(s.getSprite().getImage(),0,0, null);
    }
    
    public void drawNumber(){
        if(TEST>0)
            this.getGraphics().drawString(""+TEST, 10, 10);
    }
    
    private void drawSoldat(Graphics g,Soldat s){
        g.drawImage(s.sprite.getSprite().getImage(),0,0, null);
        
        g.setColor(Color.green);
        int pvCase = (int) ((float)((float)s.pv /(float) s.maxPv)* IConfig.NB_PIX_CASE);
        if(pvCase<= IConfig.NB_PIX_CASE/2)
            g.setColor(Color.yellow);
        if(pvCase <= IConfig.NB_PIX_CASE/4)
            g.setColor(Color.red);
        g.fillRect(0, 0, 3, pvCase);
        
        if (s instanceof Heros && !s.estMort()){
            g.setColor(Color.gray);
            g.drawLine(3, 0, 3, IConfig.NB_PIX_CASE);

            g.setColor(Color.green);
            if(s instanceof Heros && s.aJoue())
                g.setColor(Color.DARK_GRAY);
            g.fillOval(IConfig.NB_PIX_CASE-7, IConfig.NB_PIX_CASE-7, 6, 6);
        }
        
        g.setColor(Color.black);
    }
    
    private void drawTerrain(Graphics g,BackgroundEnum b){
        g.drawImage(b.getSprite().getImage(),0,0, null);
    }
    
    public boolean isFoW() {
        return e.isFoW();
    }
    
    @Override
    public String toString(){
        if (this.e.estLibre()){
            return("Nothing Here!");
        }
        return(this.e.getSoldat().toString());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Position pos = this.e.getPos();
        if(this.e.getSoldat()!= null && this.e.getSoldat() instanceof Heros)
            panelPere.select(this);
        else
            panelPere.select(this);
        panelPere.repaint();
//        ArrayList<Element> AE = null;
//        if(this.e.getSoldat()!= null && this.e.getSoldat() instanceof Heros){
//            AE = this.e.getSoldat().getAPortee(panelPere.getMap());
//            for(Element E : AE)
//                System.out.println(E.getPos());
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("drag:"+((ElementGraph)e.getSource()).getElement().getPos());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("drop:"+this.lastPos);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!this.isFoW()){
            this.getGraphics().setColor(Color.RED);
            this.getGraphics().draw3DRect(1, 1, IConfig.NB_PIX_CASE-2, IConfig.NB_PIX_CASE-2,true);
            this.getGraphics().setColor(Color.BLACK);
        }
        panelPere.ElementClic(this);
        lastPos = ((ElementGraph)e.getSource()).getElement().getPos();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!this.isFoW()){
            this.getGraphics().clearRect(0, 0, IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE);
            this.repaint();
        }
    }
    
    
}
