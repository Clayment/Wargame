package wargame;

import java.awt.Graphics;

/**
 * Classe gérant les différents aspects de la carte, le Fog of War, le déplacement des soldats,
 * leur mort, la fin d'une partie et les différents tours de jeu.
 * 
 * @author Team 7
 */

public class Carte implements ICarte, IConfig {
    private Element[][] map;
    public Armee heros;
    public Armee monstres;
    private Soldat selected;
    
    
    /**
     * Constructeur de la carte, génére la map, ajoute les héros et les monstres dessus.
     */
    public Carte(){
        map = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
        // Génération de la map
        for (int x=0; x<LARGEUR_CARTE; x++)
            for (int y=0; y<HAUTEUR_CARTE; y++){
                map[x][y] = new Element(new Position(x, y),BackgroundEnum.values()[(int)(Math.random()*300)%4]);
            }
        
        // Ajout des Héros
        heros = new Armee(NB_HEROS);
        for (int i=0; i<=NB_HEROS; i++){
            int x, y;
            do{
                x = (int) (Math.random() * (IConfig.LARGEUR_CARTE / 2));
                y = (int) (Math.random() * IConfig.HAUTEUR_CARTE);
            }while (!map[x][y].estLibre());

            Heros recrue = new Heros(ISoldat.TypesH.getTypeHAlea(), new Position(x, y));
            heros.ajouteSoldat(recrue);
            map[x][y].ajouteSoldat(recrue);
        }

        // Ajout des monstres 
        monstres = new Armee(NB_MONSTRES);
        for (int i=0; i<=NB_MONSTRES; i++){
            int x, y;
            do{
                x = (int) ((Math.random() * (IConfig.LARGEUR_CARTE / 2)) + (LARGEUR_CARTE / 2));
                y = (int) (Math.random() * IConfig.HAUTEUR_CARTE);
            }while (!map[x][y].estLibre());
            
            Monstre recrue = new Monstre(ISoldat.TypesM.getTypeMAlea(), new Position(x, y));
            monstres.ajouteSoldat(recrue);
            map[x][y].ajouteSoldat(recrue);
        }
    }
    
    /* GESTION DES UNITES */
    
    /**
     * Méthode permettant de déplacer un soldat jusqu'à la position spécifiée.
     * 
     * @param pos Position désirée pour le soldat spécifié en argument.
     * 
     * @param soldat Soldat à déplacer.
     * 
     * @return Le déplacement a-t-il été possible ?
     */
    public boolean deplaceSoldat(Position pos, Soldat soldat){
        Position posSoldat = soldat.getPos();
        Element depart = getElement(posSoldat.getX(), posSoldat.getY());
        Element arrivee = getElement(pos);
        
        if (!arrivee.estLibre() || soldat.aJoue())                              // Si le terrain d'arrivee n'est pas libre ou si le soldat a déja employé son tour
            return(false);
        
        soldat.joueTour();
        soldat.seDeplace(pos);
        depart.enleveSoldat();
        cacher(depart.getPos(), soldat.getPortee());
        arrivee.ajouteSoldat(soldat);
        decouvrir(arrivee.getPos(), soldat.getPortee());
        return(true);
    }
    
    /**
     * Supprime un soldat du champ de bataille à sa mort.
     * 
     * @param perso Soldat à supprimer de la carte.
     */
    public void mort(Soldat perso){
        Position p = perso.getPos();
        getElement(p.getX(), p.getY()).enleveSoldat();
    }
    
    /* GESTION DU CHAMP DE BATAILLE */
    
    /**
     * Méthode testant si la partie doit être terminée ou pas.
     * 
     * @return La bataille est-elle finie ?
     */
    public boolean fini(){
        return(heros.vaincu() || monstres.vaincu());
    }
    
    /**
     * Méthode dévoilant les dessous du Fog of War.
     * 
     * @param pos Position de départ de la divulgation du Fog of War.
     * 
     * @param dist Distance max de divulgation.
     */
    public void decouvrir(Position pos, int dist){
        for (int x=pos.getX() - dist; x <= pos.getX() + dist; x++){
            for (int y=pos.getY() - dist; y <= pos.getY() + dist; y++){
                if ((x >= 0) && (x < IConfig.LARGEUR_CARTE) && (y >= 0) && (y < IConfig.HAUTEUR_CARTE)){
                        map[x][y].decouvrir();
                }
            }
        }
    }
    
    /**
     * Méthode rajoutant le Fog of War sur une zone où il n'y a plus de héros.
     * @param pos Position de départ de la propagation du Fog of War.
     * @param dist Distance max de propagation.
     */
    public void cacher(Position pos, int dist){
        for (int x=pos.getX() - dist; x <= pos.getX() + dist; x++){
            for (int y=pos.getY() - dist; y <= pos.getY() + dist; y++){
                if ((x >= 0) && (x < IConfig.LARGEUR_CARTE) && (y >= 0) && (y < IConfig.HAUTEUR_CARTE)){
                        map[x][y].cacher();
                }
            }
        }
    }
    
    /**
     * Méthode actualisant la carte après qu'une des deux parties ait terminé de jouer.
     * @see Element
     * @see Soldat
     */
    public void nouveauTour(){
        for (int x=0; x<IConfig.LARGEUR_CARTE; x++){
            for (int y=0; y<IConfig.HAUTEUR_CARTE; y++){
                map[x][y].nouveauTour();
            }
        }
    }
    
    /**
     * Méthode exécutant le tour des monstres.
     */
    public void tourDesMonstres(){
        if (!fini()){
            for (int i=0; i<monstres.recensement(); i++){
                Monstre m = (Monstre) monstres.getSoldat(i);
                    if (!m.estMort()){
                        // Chaque monstre joue....
                    }
            }
                /* Reset des paramètres de tour de la map */
            nouveauTour();
        }
    }
    
    /**
     * Méthode découvrant les zones autour des héros en fonction de leur portée.
     */
    public void setFog(){
        for (int i=0; i < heros.recensement(); i++){
            Soldat s = heros.getSoldat(i);
            if (!s.estMort()){
                decouvrir(s.getPos(), s.getPortee());
            }
        }
    }
    
    /* GESTION DES ÉLÉMENTS = CASES ET DES ACTION ASSOCIÉES */
    
    /**
     * Méthode permettant de sélectionner une unité dans la carte.
     * @param x Coordonnée horizontale de l'unité sélectionnée.
     * @param y Coordonnée verticale de l'unité sélectionnée.
     * @return La sélection s'est-elle bien passée ?
     */
    public boolean select(int x, int y){
        if (isSelected())
            if(this.getSelected()== map[x][y].getSoldat()){ 
                this.selected = null;
                return false;
            }else
                actOn(x, y);
        
        if (map[x][y].estLibre())
            return(false);
        else if (map[x][y].getSoldat() instanceof Monstre)
            return(false);
        
        this.selected = map[x][y].getSoldat();
        
        if (this.selected.aJoue()){
            this.selected = null;
            return(false);
        }
        
        return(true);
    }
    
    /**
     * Méthode vérifiant si l'unité est sélectionnée.
     * @return L'unité est-elle sélectionnée ?
     */
    public boolean isSelected(){
        return(!(this.selected == null));
    }

    /**
     * ???
     * @return 
     */
    public Soldat getSelected() {
        return selected;
    }
    
    /**
     * Méthode exécutant des opérations sur une unité, déplacement, échec si le déplacement est
     * trop grand pour la portée de l'unité, et désélectionnement.
     * @param x Coordonnée horizontale de l'unité sélectionnée.
     * @param y Coordonnée verticale de l'unité sélectionnée.
     * @return L'opération s'est-elle bien passée ?
     */
    public boolean actOn(int x, int y){
        if (this.selected.getPos().distance(x, y) > this.selected.getPortee()){
            return(false);
        }
        if (map[x][y].estLibre()){
            deplaceSoldat(new Position(x, y), this.selected);
        }
        else if (this.selected.getPos().equals(new Position(x, y))){
            this.selected = null;
            return(true);
        }
        else{
            Soldat cible = map[x][y].getSoldat();
            // Mais... il peut buter un allié, là ??? 0ô
            if (cible instanceof Heros){
                return(false);
            }
            this.selected.combat(map[x][y].getSoldat());
        }
        this.selected = null;
        return(true);
    }
    
    /* Accesseurs et mutateurs */
    
    /**
     * Getter pour un élément de la carte en ayant les coordonnées de sa position en arguments.
     * @param posX Coordonnée horizontale de l'élément demandé.
     * @param posY Coordonnée verticale de l'élément demandé.
     * @return L'élément demandé.
     */
    public Element getElement(int posX, int posY){
        return(map[posX][posY]);
    }
    
    /**
     * Getter pour un élément de la carte en ayant sa position en argument.
     * @param pos Position de l'élément désiré.
     * @return L'élément demandé.
     */
    public Element getElement(Position pos){
        return(getElement(pos.getX(), pos.getY()));
    }

    
    @Override
    public Position trouvePositionVide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position trouvePositionVide(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Heros trouveHeros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Heros trouveHeros(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actionHeros(Position pos, Position pos2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jouerSoldats(PanneauJeu pj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toutDessiner(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}