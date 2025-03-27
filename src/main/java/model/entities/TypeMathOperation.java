package model.entities;

public enum TypeMathOperation {
    ADICAO("+"),
    SUBTRACAO("-"),
    DIVISAO("%"),
    MULTIPLICACAO("x");

    private String strOperation;

    TypeMathOperation(String strOperation) {
        this.strOperation = strOperation;
    }

    public String getStrOperation() {
        return strOperation;
    }
}
