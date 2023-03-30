import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

class MaquinaPilha  {
    public static void main(String[]args) throws IOException {	
        String linha;
        Stack<Float> pilha = new Stack<Float>(); 
        ArrayList<String> codigo = new ArrayList<String>();
        BufferedReader buffRead = new BufferedReader(new FileReader("./codigo_gerado.txt"));
        while (true) {
            linha = buffRead.readLine();
            if(linha==null)break;
            codigo.add(linha);
        }
        buffRead.close();
        
        for(String instrucao : codigo) {
            if(instrucao.equals("PRINT")) {
                System.out.println(pilha.pop());
            } else if(instrucao.startsWith("PUSH")) {
                pilha.push(Float.parseFloat(instrucao.split(" ")[1]));
            } else {
                Float R1 = pilha.pop();
                Float R2 = pilha.pop();
                if(instrucao.equals("SUM")) {
                    pilha.push(R2 + R1);
                }
                else if(instrucao.equals("SUB")) {
                    pilha.push(R2 - R1);
                }
                else if(instrucao.equals("MULT")) {
                    pilha.push(R2 * R1);
                }
                else if(instrucao.equals("DIV")) {
                    pilha.push(R2 / R1);
                }
            }
        }
    }
}
