package ada.wm2.CRUD.exception;

public class StudentException extends Exception{

    Integer  errorCode;

    public StudentException(Integer errorC, String message){
        super(message);
        errorCode = errorC;
    }

    @Override
    public String getMessage() {
        return "Entity error (" + errorCode  +") : " + super.getMessage();
    }
}
