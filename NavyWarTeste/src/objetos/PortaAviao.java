package objetos;

public class PortaAviao extends Veiculo{

    public PortaAviao(){
        super(4);
    }
    
    @Override
    public void disparo() {
        System.out.println("Disparo - Porta avião");
    }
    
}
