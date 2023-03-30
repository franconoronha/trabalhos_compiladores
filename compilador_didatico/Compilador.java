import java.io.FileWriter;
import java.io.IOException;

class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			Interpretador interpretador = new Interpretador();
			float resultado = interpretador.executa(arv);
			String codigo = backend.geraCodigo(arv);
			System.out.println("------ Codigo Gerado ------");
			System.out.println(codigo);
			System.out.println("---------------------------");
			try {
				FileWriter myWriter = new FileWriter("codigo_gerado.txt");
				myWriter.write(codigo);
				myWriter.close();
				System.out.println("Arquivo gerado: codigo_gerado.txt");
			} catch(IOException e) {
				System.out.println("Erro");
			}
			System.out.println("Resultado do interpretador: " + resultado);

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}
