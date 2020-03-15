package calcular;
import java.util.Stack;
import javax.swing.JOptionPane;  

class inf_post {
     //entra la expresion infija
    public static String Infijo2PosfijoTxt(String infijo){
        //Inicializando variable postfijo, será usada al final
        String postfij = null; 
        //Depurando la expresion infija, por si tiene espacios o algo asi
        String expr = depurar(infijo); 
        //medodo split meteremos cada caracter al array por espacio encontrado
        String[] arrayInfix = expr.split(" "); 
        
        //Declaración de las pilas
        Stack <String> E = new Stack <String> (); //Pila entrada
        Stack <String> O = new Stack <String> (); //Pila temporal de operadores
        Stack <String> S = new Stack <String> (); //Pila salida
        
        //Añadiendo el array a la Pila de entrada (E)
        for (int i=arrayInfix.length-1; i>=0;i--){
          E.push(arrayInfix[i]);
        }

    try {
      //Algoritmo Infijo a Postfijo
      while (!E.isEmpty()) { //Mientras que la pila E contenga algo

        switch (pref(E.peek())){
          //Caso para apertura de parentesis  
          case 1: 
               O.push(E.pop());//apilar en P lo desapilado por E (
               break;
          //Caso para suma o resta     
          case 3:
                while(pref(O.peek()) >= pref(E.peek())) {
                  S.push(O.pop());//apilar en S lo desapiplado en P
                }
                O.push(E.pop());//apilar en P lo desapilado en E
               break; 
          //Caso para productos y cocientes     
          case 4:
                while(pref(O.peek()) >= pref(E.peek())) {
                  S.push(O.pop());//apilar en S lo desapiplado en P
                }
                O.push(E.pop());//apilar en P lo desapilado en E
               break; 
          //Caso para parentesis cerrados     
          case 2:
                while(!O.peek().equals("(")) {
                  S.push(O.pop());//apilar en S lo desapilado en P )
                }
                O.pop();//Desapilar en P
                E.pop();//Desapilar en E
                break; 
          //Caso para potencias      
          case 5:
                while(pref(O.peek()) >= pref(E.peek())) {
                  S.push(O.pop());//apilar en S lo desapiplado en P
                }
                O.push(E.pop());//apilar en P lo desapilado en E
               break; 
          //Cuaquier otro caso     
          default:
             S.push(E.pop());  //apilar en S lo desapilado en E
        } 
          }        
          //Eliminacion de `impurezas´ en la expresiones algebraicas
          String infij = expr.replace(" ", ""); 
          postfij = S.toString().replaceAll("[\\]\\[,]", "");

        }catch(Exception ex){ 
          //<Avisamos si la expresion esta mal escrita
         JOptionPane.showMessageDialog(null, "Error en la expresión algebraica"+ ex); 
        }
    return postfij;	
 }
  
  //Depurando la expresión infija
  private static String depurar(String s) {
    //Elimina espacios en blanco
    s = s.replaceAll("\\s+", ""); 
    s = "(" + s + ")";
    String simbols = "+-*/^()";
    String str = "";
    
    //Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      }else str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  } 

  //Jerarquia de los operadores, metodo solo importa el numero, 
  //será usado en el switch
  private static int pref(String oper) {
    int prf=0;  
    
    if (oper.equals("^")){
        prf = 5;
    }    
    if (oper.equals("*") || oper.equals("/")){
        prf = 4;
    }
    if (oper.equals("+") || oper.equals("-")){
        prf = 3;
    }
    if (oper.equals(")")){
        prf=2;
    } 
    if (oper.equals("(")){
        prf = 1;
    }  
    return prf;
  }
    
}
