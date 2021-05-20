import java.util.Scanner;

public class calculadora {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

			if(!sc.hasNextDouble()){
				System.err.println("First element should be a number");
				System.exit(1);
			}

			double n1 = sc.nextDouble();
			
			String op = sc.next();
			
			if(!sc.hasNextDouble()){
				System.err.println("Third element should be a number");
				System.exit(1);
			}
			
			
			double n2 = sc.nextDouble();
			
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
				System.err.println("Operand not valid");
				System.exit(1);
		}
			System.out.println(res);
			sc.close();
	}

}