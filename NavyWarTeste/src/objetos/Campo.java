package objetos;

public class Campo {
    
    //private Celula celulas[][];
    private PortaAviao portaAviao;
    private Submarino submarino;
    private Navio navio;
    private Caca caca;
    
    /*public Campo(){
        celulas = new Celula[10][10];
    }

    public Celula[][] getCelulas() {
        return celulas;
    }

    public void setCelulas(Celula[][] celulas) {
        this.celulas = celulas;
    }
    
    public Celula getCelula(int linha, int coluna){
        return celulas[linha][coluna];
    }*/

    public PortaAviao getPortaAviao() {
        return portaAviao;
    }

    public void setPortaAviao(PortaAviao portaAviao) {
        this.portaAviao = portaAviao;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    public Submarino getSubmarino() {
        return submarino;
    }

    public void setSubmarino(Submarino submarino) {
        this.submarino = submarino;
    }

    public Caca getCaca() {
        return caca;
    }

    public void setCaca(Caca caca) {
        this.caca = caca;
    }
    
    
    
    public void atingirCampo(int linha, int coluna){
        
    }
    
}
