package new_objetos;

public class Parte {

    private boolean atingida;
    private final int index;
    private final int linha;
    private final int coluna;
    private final char veiculo;
    
    public Parte(boolean atingida, int index, int linha, int coluna, char veiculo){
        this.atingida = atingida;
        this.index = index;
        this.linha = linha;
        this.coluna = coluna;
        this.veiculo = veiculo;
    }
    
    public int getIndex() {
        return index;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isAtingida() {
        return atingida;
    }

    public void setAtingida(boolean atingida) {
        this.atingida = atingida;
    }

    public char getVeiculo() {
        return veiculo;
    }
   
}
