import java.util.Scanner;
import java.util.Stack;

public class calculadorav2 {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		
		while(sc1.hasNextLine()) {
			Scanner sc2 = new Scanner(sc1.nextLine());
			Stack<Double> stack = new Stack<Double>();
			
			while(sc2.hasNext()) {
				
				if(sc2.hasNextDouble()) {
					stack.push(sc2.nextDouble());
					
				} else if(sc2.hasNext()) {
					if(stack.size()<2) {
						error();
					}
					double n2 = stack.pop();
					double n1 = stack.pop();
					
					String op = sc2.next();
					double res = 0;
					
					switch(op) {
					
					case "+":
						res = n1 + n2;
						break;
					case "-":
						res = n1 - n2;
						break;
					case "*":
						res = n1 * n2;
						break;
					case "/":
						res = n1 / n2;
						break;
					default:
						error();
					}
					
					stack.push(res);
					
				}
				System.out.println(stack);
			}
			if(stack.size()!=1) {
				error();
			}
			System.out.println(stack.pop());
			sc2.close();
		}
		sc1.close();
	}
	
	public static void error() {
		System.err.println("ERROR");
		System.exit(1);
	}

}
