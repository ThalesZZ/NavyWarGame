package objetos;

public class Celula {
    
    private boolean atingida;
    private Veiculo veiculo;
    private final int parteVeiculo;
    private final int linha;
    private final int coluna;
    
    public Celula(boolean atingida, Veiculo veiculo, int parteVeiculo, int linha, int coluna){
        this.atingida = atingida;
        this.veiculo = veiculo;
        this.parteVeiculo = parteVeiculo;
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean isAtingida() {
        return atingida;
    }

    public void setAtingida(boolean atingida) {
        this.atingida = atingida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getParteVeiculo() {
        return parteVeiculo;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    public void atingirCelula(){
        atingida = true;
        
        if(veiculo != null){ //acertou algo!
            veiculo.atingirParte(parteVeiculo);
        }
        
    }
    
}
