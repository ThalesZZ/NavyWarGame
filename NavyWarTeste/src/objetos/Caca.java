package objetos;

public class Caca extends Veiculo{
    
    public Caca(){
        super(2);
    }
    
    @Override
    public void disparo() {
        System.out.println("Disparo - Caça");
    }
    
}
