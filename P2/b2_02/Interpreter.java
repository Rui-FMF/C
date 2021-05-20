public class Interpreter extends SuffixCalculatorBaseVisitor<Object> {

   @Override public Object visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Object visitStat(SuffixCalculatorParser.StatContext ctx) {
      double res = (Double)visit(ctx.expr());
      System.out.print("Result is: "+res+"\n");
      return res;
   }

   @Override public Object visitExprNumber(SuffixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }

   @Override public Object visitExprSuffix(SuffixCalculatorParser.ExprSuffixContext ctx) {
      double n1 = (Double)visit(ctx.expr(0));
      double n2 = (Double)visit(ctx.expr(1));
      double res = 0;
      String op = ctx.op.getText();

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
      }
      return res;
   }
}
