import java.util.HashMap;
import java.util.Scanner;

public class calculadorav3 {

    static HashMap<String,Double> vars = new HashMap<String,Double>();
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            processLine(sc2);
            sc2.close();
        }

        sc.close();
    }

    public static void processLine(Scanner sc){
        double op1;
        String var1,var2;

        if(sc.hasNextDouble()){                 // First element NUM

            op1 = sc.nextDouble();
            System.out.println(solveExpression(sc,op1));

        } else if(sc.hasNext()){
            var1 = sc.next();
            if(!validateVar(var1)){             //First element isn't NUM or VAR
                error("First element should be a number or a variable");

            } else {                            //First element is VAR
                if(sc.hasNext("=")){            //Atribution of value to VAR
                    sc.next();          //skip equals
                    if(sc.hasNextDouble()){
                        op1 = sc.nextDouble();
                        double val = solveExpression(sc,op1);
                        vars.put(var1, val);
                    } else if(sc.hasNext()){
                        var2 = sc.next();
                        if(!vars.containsKey(var2)){
                            error("Variable isn't defined");
                        }
                        double val = solveExpression(sc,vars.get(var2));
                        vars.put(var1, val);
                    } else {
                        error("Variable needs value after =");
                    }

                } else {                        //Normal expression with VAR
                    if(!vars.containsKey(var1)){
                        error("Variable isn't defined");
                    }
                    System.out.println(solveExpression(sc, vars.get(var1)));
                }
            }
        }
    }

    public static boolean validateVar(String var){
        if(!Character.isLetter(var.charAt(0))){
            return false;
        }

        for(int i = 1; i<var.length(); i++){
            if(!Character.isDigit(var.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean validateOpr(String opr){
        return (opr.equals("+") || opr.equals("-") || opr.equals("*") || opr.equals("/"));
    }

    public static double solveExpression(Scanner sc, double op1){   // First element is NUM or VAR (with no =)
        double op2=0;
        String opr,var1;
            
            if(!sc.hasNext()){      
                return op1;        // no Second element
            }
            
            opr = sc.next();

            if(!validateOpr(opr)){              // Second element is OPR
                error("Invalid operator used");
            }

            if(sc.hasNextDouble()){             //Third element is NUM
                op2 = sc.nextDouble();
            } else if(sc.hasNext()){            //Third element is VAR
                var1 = sc.next();
                if(!vars.containsKey(var1)){
                    error("Variable isn't defined");
                }
                op2 = vars.get(var1);
            } else {                            //no Third element
                error("Missing second operand");
            }
            switch(opr) {
			
                case "+":
                    return(op1 + op2);
                case "-":
                    return(op1 - op2);
                case "*":
                    return(op1 * op2);
                case "/":
                    return(op1 / op2);
            }
            return op1;
    }

    public static void error(String str) {
		System.err.println("ERROR "+str);
		System.exit(1);
	}
}