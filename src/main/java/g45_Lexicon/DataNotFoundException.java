package g45_Lexicon;

public class DataNotFoundException extends Exception{
    //fields
    private String message;
    private Integer errorCode;
    private String paramValue;

    //constructor
    public DataNotFoundException(String message, String paramValue) {
        super(message); // pass value to parent class : Exception where also has String message as field.
        //this.message = message;
        this.paramValue = paramValue;
    }
    public DataNotFoundException(String message, Integer errorCode, String paramValue) {
        this(message,paramValue);
        this.errorCode = errorCode;
    }

    //getter methods if needed


    //change getMessage if you want to have your own message,
    // otherwise we do not need to change it because getMessage already had in Exception class
    /* @Override
    public String getMessage() {
        return message;
    }*/

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getParamValue() {return paramValue;}
}
