package bingo.odata.expression;

public interface ConcatMethodCallExpression extends MethodCallExpression {

    Expression getLHS();

    Expression getRHS();

}
