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

import java.net.URI;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Classe mettant en place la frame du jeu.
 * 
 * @author Team 7
 */
public class FrameJeu extends JFrame{
    
    private PanneauJeu panneau;
    private JLabel infoText;
    private JLabel stateText;
    private JMenuBar menuBar;
    private JMenu menuFichier;
    private JButton endOfTurnButton;
    private JMenu menuPropos;
    private JMenuItem Nouveau;
    private JMenuItem Sauvegarder;
    private JMenuItem Charger;
    private JMenuItem Quitter;
    private JMenuItem Remerciements;
    private JFrame Propos;
    
    
    /**
     * Constructeur du frame.
     * @param map Carte à rajouter sur le frame.
     */
    public FrameJeu(Carte map){
        /* Components de la fenetre */
        endOfTurnButton = new JButton("Fin du tour");
        stateText = new JLabel("Label de Texte");
        menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuPropos = new JMenu("A propos");
        Nouveau = new JMenuItem("Nouveau");
        Sauvegarder = new JMenuItem("Sauvegarder");
        Charger = new JMenuItem("Charger");
        Quitter = new JMenuItem("Quitter");
        Remerciements = new JMenuItem("A propos");
        infoText = new JLabel("Passer sur une case pour obtenir des informations.");
        
        
        
        /* Layout et Contraints */
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        /* Reglage Frame (fenetre) */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(IConfig.POSITION_X, IConfig.POSITION_Y);
        
        
        /* Creation du panel */
        this.panneau = new PanneauJeu(map,this);
        
        /* Menu bar */
        menuBar.setOpaque(true);
        menuBar.add(menuFichier);
        menuBar.add(menuPropos);
        menuFichier.add(Nouveau);
        menuFichier.add(Sauvegarder);
        menuFichier.add(Charger);
        menuFichier.addSeparator();
        menuFichier.add(Quitter);
        menuPropos.add(Remerciements);
        this.setJMenuBar(menuBar);
        
        /* Menu Fichier, Nouveau */
        Nouveau.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a0){
                Carte map = new Carte();
                newPanneau(map);
                map.setFog();
                repaint();
            }
        });
        
        /* Menu A propos, A propos */
        Remerciements.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {
            JOptionPane jop = new JOptionPane();      
            String mess = "Middangeard 1.0\n Créé par la Team 7\n ";
            mess += "Développeurs :\n - Alexis Braine\n - Clément Horgues\n - Arslen Remaci\n";
            mess += "Avec la contribution de Nathan Ingrao, alias Narouherallaman pour la création des sprites\n";
            mess += "Son DeviantArt : http://narouherallaman.deviantart.com \n";
            mess += "Bon jeu sur Middangeard !";        
            jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE, null);        
         }            
        });
        
        /* Menu Fichier, Quitter */
        Quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        
        /* Button et Text */
        endOfTurnButton.setPreferredSize(new Dimension(200,50));
        endOfTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carte map = panneau.getMap();
                map.tourDesMonstres();
                panneau.repaint();
           }
        });
        stateText.setPreferredSize(new Dimension(200,30));
        infoText.setPreferredSize(new Dimension(200,30));
        
        /* Contrainte générale */
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        
        /* Boutton fin du tour */
        c.gridx=0;
        c.gridy=0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(endOfTurnButton, c);
        
        /* Label de Texte */
        c.gridx=0;
        c.gridy=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(stateText, c);
        
        /* Label d'info */
        c.gridx=0;
        c.gridy=3;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(infoText, c);
        
        /* Panel du plateau de jeu */
        c.gridx=0;
        c.gridy=2;
        c.gridheight = c.gridwidth = GridBagConstraints.RELATIVE;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(panneau, c);
        
        /* Affichage */
        this.pack();
        this.setVisible(true);
    }

    /**
     * 
     * @return 
     */
    public JLabel getInfoText() {
        return infoText;
    }

    /**
     * 
     * @param text 
     */
    public void setInfoText(String text) {
        this.infoText.setText(text);
    }

    /**
     * Méthode servant à créer une nouvelle map, utilisée par Nouveau dans le Menu Fichier.
     * @param map On reprend l'ancienne map pour la modifier.
     */
     public void newPanneau(Carte map) {
        this.remove(this.panneau);
        this.panneau = new PanneauJeu(map, this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=2;
        c.gridheight = c.gridwidth = GridBagConstraints.RELATIVE;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(panneau, c);
        this.pack();
     }
}