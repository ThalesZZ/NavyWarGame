package new_objetos;

import interfaces.Celula;
import interfaces.FrmJogo;

public class Navio extends Veiculo{
    
    public Navio(int linha, int coluna, Parte partes[]){
        super(linha, coluna, partes);
    }

    @Override
    public void disparo(Celula c, FrmJogo frame, boolean inimigo) {
        c.atingirParte(c);
        Celula mapa[][] = (inimigo ? frame.getMapaComputador() : frame.getMapaJogador());
        
        if(c.getColuna() < 9 && !mapa[c.getLinha()][c.getColuna() + 1].getParte().isAtingida())
            mapa[c.getLinha()][c.getColuna() + 1].atingirParte(mapa[c.getLinha()][c.getColuna() + 1]);
    }
    
}
