package objetos;

public class Submarino extends Veiculo{
    
    public Submarino(){
        super(2);
    }
    
    @Override
    public void disparo() {
        System.out.println("Disparo - Submarino");
    }
    
}
