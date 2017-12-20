package new_objetos;

import interfaces.Celula;
import interfaces.FrmJogo;

public class Submarino extends Veiculo{
    
    public Submarino(int linha, int coluna, Parte partes[]){
        super(linha, coluna, partes);
    }

    @Override
    public void disparo(Celula c, FrmJogo frame, boolean inimigo) {
        c.atingirParte(c);
    }
    
}
