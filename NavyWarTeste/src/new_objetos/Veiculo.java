package new_objetos;

import interfaces.Celula;
import interfaces.FrmJogo;

public abstract class Veiculo {

    private final int linha;
    private final int coluna;
    private Parte partes[];

    public Veiculo(int linha, int coluna, Parte partes[]){
        this.linha = linha;
        this.coluna = coluna;
        this.partes = partes;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
    public Parte[] getPartes() {
        return partes;
    }

    public void setPartes(Parte[] partes) {
        this.partes = partes;
    }
    
    public void setParte(Parte parte, int index){
        partes[index] = parte;
    }
    
    public boolean isVivo(){
        return (partesVivas() > 0); //se houver ao menos 1 parte viva, o veiculo ainda está vivo
    }
    
    private int partesVivas(){
        int partesVivas = 0;
        for(Parte p : partes){
            if(!p.isAtingida())
                partesVivas++;
        }
        
        return partesVivas;
    }
    
    public abstract void disparo(Celula c, FrmJogo frame, boolean inimigo);
            
}
