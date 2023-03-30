import java.io.*;

enum TokenType{ NUM,SOMA,MULT,SUB,DIV,APar,FPar, EOF}

class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;
		String resultString = "";
		String charString;

			do{
				currchar1 =  arquivo.read();
				currchar = (char)currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10) {
				charString = String.valueOf(currchar);
				if (currchar >= '0' && currchar <= '9') {
					while(currchar >= '0' && currchar <= '9') {
						arquivo.mark(0);
						resultString += currchar;
						currchar = (char) arquivo.read();
					}
					arquivo.reset();

					return (new Token (resultString, TokenType.NUM));
				} else 
					switch (currchar) {
						case '(':
							return (new Token (charString,TokenType.APar));
						case ')':
							return (new Token (charString,TokenType.FPar));
						case '+':
							return (new Token (charString,TokenType.SOMA));
						case '-':
							return (new Token (charString,TokenType.SUB));
						case '*':
							return (new Token (charString,TokenType.MULT));
						case '/':
							return (new Token (charString,TokenType.DIV));

						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
		return (new Token(String.valueOf(currchar),TokenType.EOF));
		
	}
}
