/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package MyExceptionsPackage;

public class InvalidSalaryExc extends Exception {

    private static  String Message = "Slavery is long gone and this is outrageous!";

    public InvalidSalaryExc(){
        super(Message);
    }
}
