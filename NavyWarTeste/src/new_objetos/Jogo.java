package new_objetos;

public class Jogo {

    private Campo campoJogador;
    private Campo campoComputador;

    public Jogo(Campo campoJogador, Campo campoComputador){
        this.campoJogador = campoJogador;
        this.campoComputador = campoComputador;
    }
    
    public Campo getCampoJogador() {
        return campoJogador;
    }

    public void setCampoJogador(Campo campoJogador) {
        this.campoJogador = campoJogador;
    }

    public Campo getCampoComputador() {
        return campoComputador;
    }

    public void setCampoComputador(Campo campoComputador) {
        this.campoComputador = campoComputador;
    }
    
}
