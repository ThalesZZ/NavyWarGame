package new_objetos;

import interfaces.Celula;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utilidade {
       
    public static void construirMapa(Celula[][] mapa, JPanel panel){
        panel.removeAll();
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                panel.add(mapa[i][j]);
            }
        }
    }
    
    public static Celula[][] loadMap(Campo campo, JFrame frm, boolean inimigo, boolean inGame){
        Celula celulas[][] = new Celula[10][10];
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                char v = 'a';
                
                if(campo != null){
                    v = (campo.getPortaAviao() != null && campo.getPortaAviao().getLinha() == i && campo.getPortaAviao().getColuna() == j) ? 'p' : v;
                    v = (campo.getNavio() != null && campo.getNavio().getLinha() == i && campo.getNavio().getColuna() == j) ? 'n' : v;
                    v = (campo.getSubmarino() != null && campo.getSubmarino().getLinha() == i && campo.getSubmarino().getColuna() == j) ? 's' : v;
                    v = (campo.getCaca() != null && campo.getCaca().getLinha() == i && campo.getCaca().getColuna() == j) ? 'c' : v;
                }
                
                switch(v){
                    case 'a': 
                        celulas[i][j] = new Celula(campo, new Parte(false, 0, i, j, 'a'), inGame, frm, inimigo); //agua
                    break;
                    case 'p':
                        for(int k = j; k < j + 4; k++)
                            celulas[i][k] = new Celula(campo, new Parte(false, k - j, i, k, 'p'), inGame, frm, inimigo);
                        
                        j += 3;
                    break;
                    case 'n':
                        for(int k = j; k < j + 3; k++)
                            celulas[i][k] = new Celula(campo, new Parte(false, k - j, i, k, 'n'), inGame, frm, inimigo);
                        
                        j += 2;
                    break;
                    case 's':
                        for(int k = j; k < j + 2; k++)
                            celulas[i][k] = new Celula(campo, new Parte(false, k - j, i, k, 's'), inGame, frm, inimigo);
                        
                        j += 1;
                    break;
                    case 'c':
                        for(int k = j; k < j + 2; k++)
                            celulas[i][k] = new Celula(campo, new Parte(false, k - j, i, k, 'c'), inGame, frm, inimigo);
                        
                        j += 1;
                    break;
                }
                
            }
        }
        
        return celulas;
    }
    
    /*public static void setMap(Campo campo, JPanel panel, boolean inGame, JFrame frame, boolean inimigo){
        panel.removeAll();
        Parte celulas[][] = new Parte[10][10];
        
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                char v = 'a';
                
                if(campo != null){
                    v = (campo.getPortaAviao() != null && campo.getPortaAviao().getLinha() == i && campo.getPortaAviao().getColuna() == j) ? 'p' : v;
                    v = (campo.getNavio() != null && campo.getNavio().getLinha() == i && campo.getNavio().getColuna() == j) ? 'n' : v;
                    v = (campo.getSubmarino() != null && campo.getSubmarino().getLinha() == i && campo.getSubmarino().getColuna() == j) ? 's' : v;
                    v = (campo.getCaca() != null && campo.getCaca().getLinha() == i && campo.getCaca().getColuna() == j) ? 'c' : v;
                }
                
                switch(v){
                    case 'a': 
                        celulas[i][j] = new Parte(false, 0, i, j, 'a'); //agua
                    break;
                    case 'p':
                        for(int k = j; k < j + 4; k++)
                            celulas[i][k] = new Parte(false, k - j, i, k, 'p');
                        
                        j += 3;
                    break;
                    case 'n':
                        for(int k = j; k < j + 3; k++)
                            celulas[i][k] = new Parte(false, k - j, i, k, 'n');
                        
                        j += 2;
                    break;
                    case 's':
                        for(int k = j; k < j + 2; k++)
                            celulas[i][k] = new Parte(false, k - j, i, k, 's');
                        
                        j += 1;
                    break;
                    case 'c':
                        for(int k = j; k < j + 2; k++)
                            celulas[i][k] = new Parte(false, k - j, i, k, 'c');
                        
                        j += 1;
                    break;
                }
                
            }
        }
        
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                panel.add(new Celula(campo, celulas[i][j], inGame, frame, inimigo));
        
    }   */
    
    public static Campo gerarCampoAleatorio(){
        
        PortaAviao p;
        Navio n;
        Submarino s;
        Caca c;
        
        int linhaSorteada = random(0, 9);
        int colunaSorteada = random(0, 6);
        Parte partes[] = new Parte[4];
       
        //Porta Aviao
        for(int i = 0; i < 4; i++)
            partes[i] = new Parte(false, i, linhaSorteada, colunaSorteada + i, 'p');
        
        p = new PortaAviao(linhaSorteada, colunaSorteada, partes);
        
        //Navio
        do{
            linhaSorteada = random(0, 9);
            colunaSorteada = random(0, 7);

            partes = new Parte[3];

            for(int i = 0; i < 3; i++)
                partes[i] = new Parte(false, i, linhaSorteada, colunaSorteada + i, 'n');

            n = new Navio(linhaSorteada, colunaSorteada, partes);
        }while(colisao(p, 4, n, 3));
        
        //Submarino
        do{
            linhaSorteada = random(0, 9);
            colunaSorteada = random(0, 8);

            partes = new Parte[2];

            for(int i = 0; i < 2; i++)
                partes[i] = new Parte(false, i, linhaSorteada, colunaSorteada + i, 's');

            s = new Submarino(linhaSorteada, colunaSorteada, partes);
        }while(colisao(p, 4, s, 2) || colisao(n, 3, s, 2));
        
        //Caca
        do{
            linhaSorteada = random(0, 9);
            colunaSorteada = random(0, 8);

            partes = new Parte[2];

            for(int i = 0; i < 2; i++)
                partes[i] = new Parte(false, i, linhaSorteada, colunaSorteada + i, 'c');

            c = new Caca(linhaSorteada, colunaSorteada, partes);
        }while(colisao(p, 4, c, 2) || colisao(n, 3, c, 2) || colisao(s, 2, c, 2));
        
        return (new Campo(s, p, n, c));
    }
    
    public static int random(double min, double max){
        return (int)(Math.round(min + Math.random() * (max-min)));
    }
    
    public static boolean colisao(Veiculo v1, int t1, Veiculo v2, int t2){
        boolean colide = false;
        
        if(v1.getLinha() == v2.getLinha()){//estão na mesma linha
                
            int coP = v1.getColuna();
            int coN = v2.getColuna();

            int linha[] = new int[10];

            for(int j = 0; j < 10; j++){
                if(j == coP){
                    for(int k = j; k < j+t1; k++)
                        linha[k] = 1;

                    break;
                }
        }
                
        for(int j = coN; j < coN + t2; j++)
            if(linha[j] == 1){
                colide = true;
                break;
            }
        }
        
        return colide;
    }
    
}
