package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.Operator;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }


  /*@Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Evaluator evaluator = (Evaluator) o;
    return Objects.equals(operandStack, evaluator.operandStack) &&
            Objects.equals(operatorStack, evaluator.operatorStack) &&
            Objects.equals(tokenizer, evaluator.tokenizer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operandStack, operatorStack, tokenizer);*/


  public int eval(String expression ) {

    String token;
    int result;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );



    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" );
            throw new RuntimeException("*****invalid token******");
          }
          //https://www.geeksforgeeks.org/expression-evaluation/ used this website to understand
          // the algorithm more in depth

          if (token.equals("(")){                             //
            operatorStack.push(Operator.getOperator("("));
            continue;
          }
          if (token.equals(")")){
            while (operatorStack.peek().priority() >= 1){
              Operator oldOpr = operatorStack.pop();
              Operand op2 = operandStack.pop();
              Operand op1 = operandStack.pop();
              operandStack.push(oldOpr.execute(op1, op2));
            }
            operatorStack.pop();
            continue;
          }




          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.
          //
          Operator newOperator = Operator.getOperator(token);




        if (!(operatorStack.isEmpty())){

          while (operatorStack.peek().priority() >= newOperator.priority() ) {
            // note that when we eval the expression 1 - 2 we will
            // push the 1 then the 2 and then do the subtraction operation
            // This means that the first number to be popped is the
            // second operand, not the first operand - see the following code
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push( oldOpr.execute( op1, op2 ));
            if (operatorStack.isEmpty()){
                break;
            }
          }
          operatorStack.push(newOperator);
          }else{

          operatorStack.push( newOperator );
          }

        }
      }
    }


    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop.



    while(!operatorStack.isEmpty()){
      Operator currentOpr = operatorStack.pop();
      Operand op2 = operandStack.pop();
      Operand op1 = operandStack.pop();
      operandStack.push(currentOpr.execute(op1, op2));

    }

    Operand total = operandStack.pop();
    result = total.getValue();

    return result;


  }
  /*private void process (Operator operator) {

    Operand op2 = operandStack.pop();
    Operand op1 = operandStack.pop();
    operandStack.push(operator.execute(op1, op2));*/

  }


/*

if (nothing left) {
token = null;
} else {
initialize token to the next queue character.
while (next queue character is part of this token) {
token = token + (next character from the queue).
}
}
while (!characters.isEmpty() &&
Character.isWhitespace(characters.peek())) {
characters.remove();
}

*/