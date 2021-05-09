package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {

    public int priority(){
        return 3;
    }

    public Operand execute(Operand op1, Operand op2){
        return new Operand(pow(op1.getValue(), op2.getValue()));
    }
    public int pow(int i, int j){
        int result =i;
        for(int c=2 ; c <= j; c++){
            result = result*i;
        }return result;
    }
}
//error: at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//throw NumberFormatException.forInputString(s);
// public static int parseInt(String s) throws NumberFormatException {
//        return parseInt(s,10);
//