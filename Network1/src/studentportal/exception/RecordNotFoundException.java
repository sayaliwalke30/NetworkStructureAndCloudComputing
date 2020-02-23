package studentportal.exception;

public class RecordNotFoundException extends Exception {
	private String errorMessage;
	  
    public String getErrorMessage() {
        return errorMessage;
    }
    public RecordNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public RecordNotFoundException() {
        super();
    }
}
