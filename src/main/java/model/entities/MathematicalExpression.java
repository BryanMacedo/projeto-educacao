package model.entities;

import java.util.List;

public class MathematicalExpression {
    private TypeMathOperation operation;
    private int result;
    private int earlyExpression;
    private List<Integer> answers;

    public MathematicalExpression(TypeMathOperation operation, int result, int earlyExpression, List<Integer> answers) {
        this.operation = operation;
        this.result = result;
        this.earlyExpression = earlyExpression;
        this.answers = answers;
    }

    public TypeMathOperation getOperation() {
        return operation;
    }

    public int getResult() {
        return result;
    }

    public int getEarlyExpression() {
        return earlyExpression;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
}
