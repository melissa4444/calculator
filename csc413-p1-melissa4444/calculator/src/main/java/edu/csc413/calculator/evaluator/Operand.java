package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a vald mathematical expression.
 */




public class Operand {
  /**
   * construct operand from integer
   */
  public int operandValue;
  public Operand(String token){
    operandValue = Integer.parseInt(token);
  }


  public Operand( int value ) {
    operandValue=value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {
      return operandValue;
  }
  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {
    try{
      Integer.parseInt(token);

    } catch (NumberFormatException e){
      return false;
   }
    return true;
    //https://beginnersbook.com/2013/04/try-catch-in-java/ how to use the try catch exception
  }

}
// public static int parseInt(String s) throws NumberFormatException {
//        return parseInt(s,10);
//    }
//static NumberFormatException forInputString(String s) {
//        return new NumberFormatException("For input string: \"" + s + "\"");
//    }
// public String toString() {
//        return toString(value);
//    }
//
//    /**
//     * Returns a hash code for this {@code Integer}.
//     *
//     * @return  a hash code value for this object, equal to the
//     *          primitive {@code int} value represented by this
//     *          {@code Integer} object.
//