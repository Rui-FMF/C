import java.util.Iterator;

public class ConvertToSuffix extends CalculatorBaseVisitor<String> {

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      String res = "";
      Iterator<CalculatorParser.StatContext> iter = ctx.stat().iterator();
      while(iter.hasNext())
         res += visit(iter.next()) + "\n";
      return res;
   }

   @Override public String visitProcessExpr(CalculatorParser.ProcessExprContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitProcessAssignment(CalculatorParser.ProcessAssignmentContext ctx) {
      return visit(ctx.assignment());
   }

   @Override public String visitAssignment(CalculatorParser.AssignmentContext ctx) {
      return ctx.ID().getText() + " = " + visit(ctx.expr());
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitUnaryPlus(CalculatorParser.UnaryPlusContext ctx) {
      return visit(ctx.expr()) + " !+ ";
   }

   @Override public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitUnaryMinus(CalculatorParser.UnaryMinusContext ctx) {
      return visit(ctx.expr()) + " !- ";
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprId(CalculatorParser.ExprIdContext ctx) {
      return ctx.ID().getText();
   }

   @Override public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }
}
