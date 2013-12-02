package wargame;

import java.util.ArrayList;

public class Soldat implements ISoldat{
    protected final int maxPv;                                                    // Vie maximale de l'unité                                                             // Nombre de points de vie actuels de l'unité (<= maxPv)
    private final int portee;                                                         // Portée maximale, 0 pour les unités de CaC
    private final int puissance;                                                      // Dégats maximaux au CaC
    private final int tir;                                                            // Dégats maximaux à distance
    protected int pv;
    private Position pos;
    private boolean aJoue;                                                        // Indique si le tour est consommé ou non
    protected SoldatEnum sprite;
    
    /* CONSTRUCTEUR*/
    public Soldat(int maxPv, int portee, int puissance, int tir, Position
     coords,SoldatEnum sprite){
        this.maxPv = maxPv;
        this.pv = maxPv;                                                        // Initialisation des points de vie au maximum
        this.portee = portee;
        this.puissance = puissance;
        this.tir = tir;
        this.seDeplace(coords);                                                 // Placement à la position de départ
        this.sprite = sprite;
    }
    
    /* COMBAT */
    public void combat(Soldat soldat){
        // Attaque
        soldat.blesser(attaque(soldat));
        
        //riposte de l'ennemi
        if (!soldat.estMort()){
            blesser(soldat.attaque(this));
        }
        joueTour();
    }
    private int attaque(Soldat soldat){
        int maxDegats = 0;
        /* Combat au CaC */
        if (distance(soldat) == 1){
            maxDegats = this.puissance;
        }
        /* Combat à distance */
        else if (distance(soldat) <= portee)
            maxDegats = this.tir;
        /* L'ennemi est hors de portée */
        else
            maxDegats = 0;
        
        // Jet de dégats sur 1d{maxDegats}
        int degats = (int) (Math.random() * maxDegats) + 1;
        return(degats);
    }
    public void blesser(int degats){
        this.pv -= degats;
    }
    public void guerir(){
        this.soigner((int) (Math.random() * ISoldat.MAX_HEAL) + 1);
    }
    public void soigner(int soin){
        this.pv += soin;
        if (this.pv > this.maxPv){
            this.pv = this.maxPv;
        }
    }
    
    /* POSITION */
    public void seDeplace(Position newPos){
        this.pos = new Position(newPos.getX(), newPos.getY());
    }
    public int distance(Soldat soldat){
        Position posEnnemi = soldat.getPos();
        double dx = Math.abs(pos.getX() - posEnnemi.getX());
        double dy = Math.abs(pos.getY() - posEnnemi.getY());
        int distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return(distance);
    }
    
    public ArrayList<Element> getAPortee(Carte C){
        ArrayList<Element> L = new ArrayList<>();
        int xO = this.getPos().getX();
        int yO = this.getPos().getY();
        int numordre = 0;
        for(int i = this.getPortee();i>=1;i--){
            for(int x = -i; x<=i;x++)
                for(int y = -i; y<=i;y++)
                    if(xO+x<IConfig.LARGEUR_CARTE && yO+y<IConfig.HAUTEUR_CARTE && xO+x>=0 && yO+y>=0){
                        numordre++;
                        L.add(C.getElement(xO+x, yO+y));
                        C.getElement(xO+x, yO+y).getGraph().TEST=numordre;
                    }
            for(int x = -i+1; x<=i-1;x++)
                for(int y = -i+1; y<=i-1;y++)
                    if(xO+x<IConfig.LARGEUR_CARTE && yO+y<IConfig.HAUTEUR_CARTE && xO+x>=0 && yO+y>=0){
                        numordre--;
                        L.remove(C.getElement(xO+x, yO+y));
                        C.getElement(xO+x, yO+y).getGraph().TEST=0;
                    }
       }
       
        
        return L;
    }
    
    /* AUTRES... */
    public boolean joueTour(){
        if (aJoue())
            return(false);
        else{
            this.aJoue = true;
            return(true);
        }
    }
    public boolean aJoue(){
        return(this.aJoue);
    }
    public boolean estMort(){
        return(this.pv <= 0);
    }
    public void nouveauTour(){
        if (!aJoue() && !estMort()){
            this.guerir();
        }
        this.aJoue = false;
    }
    
    /* Accesseurs et mutateurs */
    public int getPoints(){
        return(this.pv);
    }
    public int getPortee(){
        return(this.portee);
    }
    public int getPuissance(){
        return(this.puissance);
    }
    public int getTir(){
        return(this.tir);
    }
    public Position getPos(){
        return(this.pos);
    }

    public SoldatEnum getSprite() {
        return sprite;
    }

    
}
