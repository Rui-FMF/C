public class Interpreter extends CalculatorBaseVisitor<Object> {

   @Override public Object visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Object visitStat(CalculatorParser.StatContext ctx) {
      int res = (Integer)visit(ctx.expr());
      System.out.println("Result is: "+res+"\n");
      return res;
   }

   @Override public Object visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      int n1 = (Integer)visit(ctx.expr(0));
      int n2 = (Integer)visit(ctx.expr(1));
      int res = 0;
      String op = ctx.op.getText();
   
      switch(op){
   
         case "+":
            res = n1 + n2;
            break;
         case "-":
            res = n1 - n2;
            break;
      }
      return res;
   }

   @Override public Object visitUnaryPlus(CalculatorParser.UnaryPlusContext ctx) {
      int num = (Integer)visit(ctx.expr());
      return num;
   }

   @Override public Object visitExprParent(CalculatorParser.ExprParentContext ctx) {
      int inside_res = (Integer)visit(ctx.expr());
      return inside_res;
   }

   @Override public Object visitUnaryMinus(CalculatorParser.UnaryMinusContext ctx) {
      int num = (Integer)visit(ctx.expr());
      return num*(-1);
   }

   @Override public Object visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      int num = Integer.parseInt(ctx.Integer().getText());
      return num;
   }

   @Override public Object visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      int n1 = (Integer)visit(ctx.expr(0));
      int n2 = (Integer)visit(ctx.expr(1));
      int res = 0;
      String op = ctx.op.getText();
   
      switch(op){
   
         case "*":
            res = n1 * n2;
            break;
         case "/":
            res = n1 / n2;
            break;
         case "%":
            res = n1 % n2;
            break;
      }
      return res;
   }
}