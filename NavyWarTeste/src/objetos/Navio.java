package objetos;

public class Navio extends Veiculo{
    
    public Navio(){
        super(3);
    }
    
    @Override
    public void disparo() {
        System.out.println("Disparo - Navio de Escolta");
    }
    
}
