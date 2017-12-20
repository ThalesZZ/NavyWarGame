package new_objetos;
import java.io.*; 
import javax.swing.JOptionPane;
import interfaces.FrmMontarJogo;
public class Arquivo {

   public void ArquivoAbre() {
        try{
            String nome;
            nome = JOptionPane.showInputDialog(null,"Entre com o nome do arquivo");
	    FileInputStream entrada = new FileInputStream(nome);
           
	    InputStreamReader entradaFormatada = new InputStreamReader(entrada);
	    int c = entradaFormatada.read();
            boolean done = false;
            boolean num = true;
            boolean check = false;
            int li = 0,li1 = 0,li2 = 0,li3 = 0,li4 = 0;
            int co = 0,co1 = 0,co2 = 0,co3 = 0,co4 = 0;
            int pa = 0;
	    while( c!=-1){
	    	System.out.println( (char) c);
                
                char b = (char) c;
                boolean done2 = false;
                if(num == false){
                    
                    switch (b){
                        case '1' : System.out.println("coluna");
                        co = 1;
                        check = true;
                        num = true;
                        break;
                        case '2' : System.out.println("coluna");
                        co = 2;
                        check = true;
                        num = true;
                        break;
                        case '3' : System.out.println("coluna");
                        co = 3;
                        check = true;
                        num = true;
                        break;
                        case '4' : System.out.println("coluna");
                        co = 4;
                        check = true;
                        num = true;
                        break;
                        case '5' : System.out.println("coluna");
                        co = 5;
                        check = true;
                        num = true;
                        break;
                        case '6' : System.out.println("coluna");
                        co = 6;
                        check = true;
                        num = true;
                        break;
                        case '7' : System.out.println("coluna");
                        co = 7;
                        check = true;
                        num = true;
                        break;
                        case '8' : System.out.println("coluna");
                        co = 8;
                        check = true;
                        num = true;
                        break;
                        case '9' : System.out.println("coluna");
                        co = 9;
                        check = true;
                        num = true;
                        break;
                    
                    }
                   done2 = true;
                }
                if(done2 == false){
                    if(b == '2' && done == false && num == true) {
                        System.out.println("submarino");
                        pa = 1;
                        done = true;
                        num = false;

                    }
                    else if(b == '2' && done == true && num == true) {
                        System.out.println("caça");
                        pa = 2;
                        num = false;
                    }
                    else if(b == '3' && num == true) {
                        System.out.println("navio");
                        pa = 3;
                        num = false;
                    }
                    else if(b == '4' && num == true) {
                        System.out.println("portaAviao");
                        pa = 4;
                        num = false;
                    }
                }
                switch (b){
                    case 'a' : System.out.println("linha");
                    li = 1;
                    break;
                    case 'b' : System.out.println("linha");
                    li = 2;
                    break;
                    case 'c' : System.out.println("linha");
                    li = 3;
                    break;
                    case 'd' : System.out.println("linha");
                    li = 4;
                    break;
                    case 'e' : System.out.println("linha");
                    li = 5;
                    break;
                    case 'f' : System.out.println("linha");
                    li = 6;
                    break;
                    case 'g' : System.out.println("linha");
                    li = 7;
                    break;
                    case 'h' : System.out.println("linha");
                    li = 8;
                    break;
                    case 'i' : System.out.println("linha");
                    li = 9;
                    break;
                    case 'j' : System.out.println("linha");
                    li = 10;
                    break;
                    
                    
                }
                
                if(check == true){
                    
                    switch (pa){
                        case 1 : co1 = co - 1;
                        li1 = li - 1;
                        check = false;
                        break;
                        case 2 : co2 = co - 1;
                        li2 = li - 1;
                        check = false;
                        break;
                        case 3 : co3 = co - 1;
                        li3 = li - 1;
                        check = false;
                        break;
                        case 4 : co4 = co - 1;
                        li4 = li - 1;
                        check = false;
                        break;
                     }
                }
                
                c = entradaFormatada.read();
                
	    }
            
            Parte[] parte4 = new Parte[4];
            for(int j = co4; j - co4 < 4; j++){
                parte4[j - co4] = new Parte(false, j - co4, li4, j, 'p');
            }
            PortaAviao portaaviao = new PortaAviao(li4, co4, parte4);
            
            Parte[] parte3 = new Parte[3];
            for(int j = co3; j - co3 < 3; j++){
                parte3[j - co3] = new Parte(false, j - co3, li3, j, 'n');
            }
            Navio navio = new Navio(li3, co3, parte3);
            
            Parte[] parte2 = new Parte[2];
            for(int j = co2; j - co2 < 2; j++){
                parte2[j - co2] = new Parte(false, j - co2, li2, j, 'c');
            }
            Caca caca = new Caca(li2, co2, parte2);
            
            Parte[] parte1 = new Parte[2];
            for(int j = co1; j - co1 < 2; j++){
                parte1[j - co1] = new Parte(false, j - co1, li1, j, 's');
            }
            Submarino submarino = new Submarino(li1, co1, parte1);
            
            Campo campojogador = new Campo(submarino, portaaviao, navio, caca);
            
            new FrmMontarJogo(campojogador, '-').setVisible(true);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
	} 
        
	}

}
    

    

