package new_objetos;

import interfaces.Celula;
import interfaces.FrmJogo;

public class PortaAviao extends Veiculo{

    public PortaAviao(int linha, int coluna, Parte partes[]){
        super(linha, coluna, partes);
    }

    @Override
    public void disparo(Celula c, FrmJogo frame, boolean inimigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
