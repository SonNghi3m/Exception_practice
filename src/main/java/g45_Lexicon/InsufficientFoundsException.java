package g45_Lexicon;

public class InsufficientFoundsException extends Exception {
    //fields
    private Integer errorCode;

    public InsufficientFoundsException(String message) {
        super(message);
       // this.errorCode = errorCode;
    }

    //Getters
    public Integer getErrorCode() {
        return errorCode;
    }

}
