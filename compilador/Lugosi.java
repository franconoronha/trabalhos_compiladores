/* Generated By:JavaCC: Do not edit this line. Lugosi.java */
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Lugosi implements LugosiConstants {

  public static void main(String args[]) throws ParseException,IOException {
    Lugosi analisador = new Lugosi(new FileInputStream(args[0]));
    LugosiA arvore = analisador.Lugosi();
    String programString = arvore.toString();
    String filename = args[0].split("\\.")[0] + ".go";
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(programString);
      myWriter.close();
      System.out.println("Arquivo gerado: " + filename);
    } catch(IOException e) {
      System.out.println("Erro na gera\u00e7\u00e3o de arquivo.");
    }
  }

  public static String getListString(ArrayList list) {
    String result = "";
    for(Object o : list) {
      result += o.toString();
    }
    return result;
  }

  public static String getCommaSeparatedList(ArrayList list) {
    String result = "";
    for(int i = 0; i < list.size(); i++) {
      result += list.get(i).toString();
      if(list.size() > 2 && i != list.size() - 1) result += ", ";
    }
    return result;
  }

  public static String prepTipo(String tipo) {
    return tipo.equals("float") ? "float64" : tipo;
  }

  static final public LugosiA Lugosi() throws ParseException {
                      ArrayList funcoes = new ArrayList(); Main main;
    main = Main();
    Func(funcoes);
    jj_consume_token(0);
   {if (true) return new LugosiA(main, funcoes);}
    throw new Error("Missing return statement in function");
  }

  static final public Main Main() throws ParseException {
               ArrayList decls = new ArrayList(); ArrayList seqC = new ArrayList();
    jj_consume_token(MAIN);
    jj_consume_token(ACHAVES);
    Vardecl(decls);
    SeqComandos(seqC);
    jj_consume_token(FCHAVES);
   {if (true) return new Main(decls, seqC);}
    throw new Error("Missing return statement in function");
  }

  static final public void Vardecl(ArrayList decls) throws ParseException {
                                 Vardecl decl=null; Token id=null; String tipo=null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(VAR);
      tipo = Tipo();
      id = jj_consume_token(ID);
      jj_consume_token(PTVRG);
   decl = new Vardecl(tipo, id.image);
  decls.add(decl);
    }
  }

  static final public String Tipo() throws ParseException {
                 Token t=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      t = jj_consume_token(INT);
      break;
    case BOOL:
      t = jj_consume_token(BOOL);
      break;
    case FLOAT:
      t = jj_consume_token(FLOAT);
      break;
    case VOID:
      t = jj_consume_token(VOID);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  static final public void SeqComandos(ArrayList comandos) throws ParseException {
                                        Comando c;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RETURN:
      case DO:
      case IF:
      case WHILE:
      case PRINT:
      case ID:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      c = Comando();
                comandos.add(c);
    }
  }

  static final public Comando Comando() throws ParseException {
                     Token t=null; ArrayList comandos = new ArrayList(); Exp exp=null; Comando c;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      t = jj_consume_token(ID);
      c = IdL(t.image);
                         {if (true) return c;}
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(APAR);
      exp = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
      jj_consume_token(PTVRG);
                                                                                    {if (true) return new If(exp, comandos);}
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(APAR);
      exp = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(DO);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
      jj_consume_token(PTVRG);
                                                                                            {if (true) return new While(exp, comandos);}
      break;
    case DO:
      jj_consume_token(DO);
      jj_consume_token(ACHAVES);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
      jj_consume_token(WHILE);
      jj_consume_token(APAR);
      exp = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(PTVRG);
                                                                                            {if (true) return new DoWhile(exp, comandos);}
      break;
    case RETURN:
      jj_consume_token(RETURN);
      exp = Exp();
      jj_consume_token(PTVRG);
                                {if (true) return new Return(exp);}
      break;
    case PRINT:
      jj_consume_token(PRINT);
      jj_consume_token(APAR);
      exp = Exp();
      jj_consume_token(FPAR);
      jj_consume_token(PTVRG);
                                             {if (true) return new Print(exp);}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Comando IdL(String t) throws ParseException {
                         Exp e = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ATRIB:
      jj_consume_token(ATRIB);
      e = Exp();
      jj_consume_token(PTVRG);
                           {if (true) return new Atrib(t, e);}
      break;
    case APAR:
     ArrayList listaExp = new ArrayList();
      jj_consume_token(APAR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case APAR:
      case TRUE:
      case FALSE:
      case NUM:
      case ID:
        e = Exp();
                   listaExp.add(e);
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VIRGULA:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
        jj_consume_token(VIRGULA);
        e = Exp();
                                                           listaExp.add(e);
      }
      jj_consume_token(FPAR);
      jj_consume_token(PTVRG);
                                                                                               {if (true) return new FuncCall(t, listaExp, e);}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp Exp() throws ParseException {
             String op=null; Exp exp1=null; Exp exp2=null; Fator f1=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case APAR:
      jj_consume_token(APAR);
      exp1 = Exp();
      op = Op();
      exp2 = Exp();
      jj_consume_token(FPAR);
                                               {if (true) return new Exp(exp1, op, exp2);}
      break;
    case TRUE:
    case FALSE:
    case NUM:
    case ID:
      f1 = Fator();
                {if (true) return f1;}
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Fator Fator() throws ParseException {
                 ArrayList listaExp = new ArrayList(); Token t=null; Exp e=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      t = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case APAR:
        jj_consume_token(APAR);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case APAR:
        case TRUE:
        case FALSE:
        case NUM:
        case ID:
          e = Exp();
                            listaExp.add(e);
          break;
        default:
          jj_la1[8] = jj_gen;
          ;
        }
        label_4:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case VIRGULA:
            ;
            break;
          default:
            jj_la1[9] = jj_gen;
            break label_4;
          }
          jj_consume_token(VIRGULA);
          e = Exp();
                                                                    listaExp.add(e);
        }
        jj_consume_token(FPAR);
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
    case NUM:
      t = jj_consume_token(NUM);
      break;
    case TRUE:
      t = jj_consume_token(TRUE);
      break;
    case FALSE:
      t = jj_consume_token(FALSE);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   {if (true) return new Fator(t.image, listaExp);}
    throw new Error("Missing return statement in function");
  }

  static final public String Op() throws ParseException {
               Token t=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MAIS:
      t = jj_consume_token(MAIS);
      break;
    case SUB:
      t = jj_consume_token(SUB);
      break;
    case MULT:
      t = jj_consume_token(MULT);
      break;
    case DIV:
      t = jj_consume_token(DIV);
      break;
    case AND:
      t = jj_consume_token(AND);
      break;
    case OR:
      t = jj_consume_token(OR);
      break;
    case MENOR:
      t = jj_consume_token(MENOR);
      break;
    case MAIOR:
      t = jj_consume_token(MAIOR);
      break;
    case IGUAL:
      t = jj_consume_token(IGUAL);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  static final public void Func(ArrayList funcoes) throws ParseException {
                                String tipo=null; Token id=null;
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
    ArrayList comandos = new ArrayList(); ArrayList argumentos = new ArrayList();ArrayList decls = new ArrayList();
      jj_consume_token(FUNCTION);
      tipo = Tipo();
      id = jj_consume_token(ID);
      jj_consume_token(APAR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
      case BOOL:
      case FLOAT:
      case VOID:
        ListaArg(argumentos);
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      jj_consume_token(FPAR);
      jj_consume_token(ACHAVES);
      Vardecl(decls);
      SeqComandos(comandos);
      jj_consume_token(FCHAVES);
   funcoes.add(new Funcao(tipo, id.image, argumentos, comandos, decls));
    }
  }

  static final public void ListaArg(ArrayList listaArg) throws ParseException {
                                     String tipo=null; Token id=null;
    tipo = Tipo();
    id = jj_consume_token(ID);
                       listaArg.add(new Argumento(tipo, id.image));
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_6;
      }
      jj_consume_token(VIRGULA);
      tipo = Tipo();
      id = jj_consume_token(ID);
                                  listaArg.add(new Argumento(tipo, id.image));
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public LugosiTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200,0x80300000,0x3e000,0x3e000,0x800,0x40000,0xc00,0x800,0x800,0x40000,0x800,0x0,0x7fc00000,0x80000,0x80300000,0x40000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x1,0x10,0x10,0x1e,0x0,0x0,0x1e,0x1e,0x0,0x0,0x1e,0x0,0x0,0x1,0x0,};
   }

  /** Constructor with InputStream. */
  public Lugosi(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Lugosi(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LugosiTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Lugosi(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LugosiTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Lugosi(LugosiTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LugosiTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[37];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 37; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}

class LugosiA {
  ArrayList<Funcao> funcoes;
  Main main;
  LugosiA(Main main, ArrayList funcoes) {
    this.funcoes = funcoes;
    this.main = main;
  }

  public String toString() {
    String functionsString = Lugosi.getListString(funcoes);
    return main.toString() + functionsString;
  }
}

class Main {
  ArrayList<Comando> comandos;
  ArrayList<Vardecl> vardecls;
  Main(ArrayList vardecls, ArrayList comandos) {
    this.comandos = comandos;
    this.vardecls = vardecls;
  }

  public String toString() {
    String declString = Lugosi.getListString(vardecls);
    String comString = Lugosi.getListString(comandos);
    return "package main\nimport (\"fmt\")\nvar _ = fmt.Println\n\nfunc main() {\n" + declString + comString + "}\n\n";
  }
}

class Funcao {
  String tipo;
  String id;
  ArrayList<Argumento> argumentos;
  ArrayList<Vardecl> vardecls;
  ArrayList<Comando> comandos;
  Funcao(String tipo, String id, ArrayList argumentos, ArrayList comandos, ArrayList vardecls) {
    this.tipo = tipo;
    this.id = id;
    this.argumentos = argumentos;
    this.vardecls = vardecls;
    this.comandos = comandos;
  }

  public String toString() {
    String argString = Lugosi.getCommaSeparatedList(argumentos);
    String funcType = tipo.equals("void") ? "" : Lugosi.prepTipo(tipo);
    String declString = Lugosi.getListString(vardecls);
    String comString = Lugosi.getListString(comandos);
    return "func " + id +  " (" + argString + ") " + funcType + " {\n" + declString + comString + "}\n\n";
  }
}

class Argumento {
  String tipo;
  String id;
  Argumento(String tipo, String id) {
    this.tipo = tipo;
    this.id = id;
  }
  public String toString() {
    return id + " " + Lugosi.prepTipo(tipo);
  }
}

class Vardecl {
  String tipo;
  String id;
  Vardecl(String tipo, String id) {
    this.tipo = tipo;
    this.id = id;
  }
  public String toString() {
    return "var " + id + " " + Lugosi.prepTipo(tipo) + "\n";
  }
}

class Exp {
  Exp exp1;
  Exp exp2;
  String op;
  Exp(Exp exp1, String op, Exp exp2) {
    this.exp1 = exp1;
    this.op = op;
    this.exp2 = exp2;
  }
  public String toString() {
    return exp1.toString() + " " + op + " " + exp2.toString();
  }
}

class Fator extends Exp {
  String fator;
  ArrayList<Exp> listaExp;
  Fator(String fator, ArrayList listaExp) {
    super(null,null,null);
    this.listaExp = listaExp;
    this.fator = fator;
  }
  public String toString() {
    if(listaExp.isEmpty()) {
      return fator;
    } else {
      String expListString = Lugosi.getCommaSeparatedList(listaExp);
      return fator + "(" + expListString + ")";
    }
  }
}

class Comando {
  Exp exp;
  Comando(Exp exp) {
    this.exp = exp;
  }
}

class Atrib extends Comando {
  String id;
  Atrib(String id, Exp exp) {
    super(exp);
    this.id = id;
  }
  public String toString() {
    return id + " = " + exp.toString() + "\n";
  }
}

class FuncCall extends Comando {
  String id;
  ArrayList<Exp> listaExp;
  FuncCall(String id, ArrayList listaExp, Exp exp) {
    super(null);
    this.id = id;
    this.listaExp = listaExp;
  }

  public String toString() {
    String expLString = Lugosi.getCommaSeparatedList(listaExp);
    return id + "(" + expLString + ")\n";
  }
}
class If extends Comando {
  ArrayList<Comando> comandos;
  If(Exp exp, ArrayList comandos) {
    super(exp);
    this.comandos = comandos;
  }

  public String toString() {
    String comString = Lugosi.getListString(comandos);
    return "if " + exp.toString() + " " + "{\n" + comString + "}\n";
  }
}

class While extends Comando {
  ArrayList<Comando> comandos;
  While(Exp exp, ArrayList comandos) {
    super(exp);
    this.comandos = comandos;
  }

  public String toString() {
    String comString = Lugosi.getListString(comandos);
    return "for " + exp.toString() + "{\n" + comString + "}\n";
  }
}

class DoWhile extends Comando {
  ArrayList<Comando> comandos;
  DoWhile(Exp exp, ArrayList comandos) {
    super(exp);
    this.comandos = comandos;
  }
  public String toString() {
    String comString = Lugosi.getListString(comandos);
    return "for {\n" + comString + "if !(" + exp.toString() + ") {break}\n}\n";
  }
}

class Print extends Comando {
  Print(Exp exp){
    super(exp);
  }
  public String toString() {
    return "fmt.Println(" + exp.toString() +")\n";
  }
}

class Return extends Comando {
  Return(Exp exp){
    super(exp);
  }
  public String toString() {
    return "return " + exp.toString() + "\n";
  }
}
