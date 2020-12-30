package exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String messages) {
        super(messages);
    }
}
