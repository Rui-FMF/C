import java.util.HashMap;

public class Interpreter extends CalculatorBaseVisitor<Object> {

   HashMap<String,Integer> vars = new HashMap<String,Integer>();

   @Override public Object visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Object visitProcessExpr(CalculatorParser.ProcessExprContext ctx) {
      int res = (Integer)visit(ctx.expr());
      System.out.println("Result is: "+res);
      return res;
   }

   @Override public Object visitProcessAssignment(CalculatorParser.ProcessAssignmentContext ctx) {
      String var = (String)visit(ctx.assignment());
      System.out.println("Variable "+var+" stored");
      return var;
   }

   @Override public Object visitAssignment(CalculatorParser.AssignmentContext ctx) {
      String id = ctx.ID().getText();
      int val = (Integer)visit(ctx.expr());
      vars.put(id, val);
      return id;
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

   @Override public Object visitExprId(CalculatorParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      int res = 0;
      if(vars.containsKey(id)){
         res = vars.get(id);
      } else{
         System.err.println("Variavél não existe");
         System.exit(1);
      }
      return res;
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