package new_objetos;

import interfaces.Celula;
import interfaces.FrmJogo;

public class Caca extends Veiculo{
    
    public Caca(int linha, int coluna, Parte partes[]){
        super(linha, coluna, partes);
    }

    @Override
    public void disparo(Celula c, FrmJogo frame, boolean inimigo) {
        c.atingirParte(c);
        Celula mapa[][] = (inimigo ? frame.getMapaComputador() : frame.getMapaJogador());
        
        if(c.getColuna() > 0 && !mapa[c.getLinha()][c.getColuna() - 1].getParte().isAtingida())
            mapa[c.getLinha()][c.getColuna() - 1].atingirParte(mapa[c.getLinha()][c.getColuna() - 1]);
        
        if(c.getColuna() < 9 && !mapa[c.getLinha()][c.getColuna() + 1].getParte().isAtingida())
            mapa[c.getLinha()][c.getColuna() + 1].atingirParte(mapa[c.getLinha()][c.getColuna() + 1]);
                
        if(c.getLinha() > 0 && !mapa[c.getLinha() - 1][c.getColuna()].getParte().isAtingida())
            mapa[c.getLinha() - 1][c.getColuna()].atingirParte(mapa[c.getLinha() - 1][c.getColuna()]);
        
        if(c.getLinha() < 9 && !mapa[c.getLinha() + 1][c.getColuna()].getParte().isAtingida())
            mapa[c.getLinha() + 1][c.getColuna()].atingirParte(mapa[c.getLinha() + 1][c.getColuna()]);
        
    }
    
}
