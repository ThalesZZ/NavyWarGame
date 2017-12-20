package new_objetos;

public class Campo {

    private Submarino submarino;
    private PortaAviao portaAviao;
    private Navio navio;
    private Caca caca;

    public Campo(Submarino submarino, PortaAviao portaAviao, Navio navio, Caca caca){
        this.submarino = submarino;
        this.portaAviao = portaAviao;
        this.navio = navio;
        this.caca = caca;
    }
    
    public Submarino getSubmarino() {
        return submarino;
    }

    public void setSubmarino(Submarino submarino) {
        this.submarino = submarino;
    }

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

    public Caca getCaca() {
        return caca;
    }

    public void setCaca(Caca caca) {
        this.caca = caca;
    }
    
}
