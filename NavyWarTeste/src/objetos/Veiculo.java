package objetos;

public abstract class Veiculo {
    
    private boolean partesVivas[];
    
    public Veiculo(int quadrados){
        partesVivas = new boolean[quadrados];
        
        for(int i = 0; i < quadrados; i++)
            partesVivas[i] = true;
    }

    public boolean[] getPartesVivas() {
        return partesVivas;
    }

    public void setPartesVivas(boolean[] partesVivas) {
        this.partesVivas = partesVivas;
    }
    
    public boolean isVivo(){        
        return (contarPartesVivas() > 0); //se tiver ao menos 1 parte não destruída, ainda tá vivo
    }
    
    public boolean podeAtirar(){
        return (contarPartesVivas() >= partesVivas.length); //se todas as partes estiverem vivas
    }
    
    private int contarPartesVivas(){
        int partes = 0;
        
        for(boolean r : partesVivas){
            if(r)
                partes++;
        }
        
        return partes;
    }
    
    public void atingirParte(int parte){
        partesVivas[parte] = false;
    }
    
    public abstract void disparo();
            
}
