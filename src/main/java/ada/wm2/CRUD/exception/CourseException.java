package ada.wm2.CRUD.exception;

public class CourseException extends Exception{

    Integer  errorCode;

    public CourseException(Integer errorC, String message){
        super(message);
        errorCode = errorC;
    }

    @Override
    public String getMessage() {
        return "Entity error (" + errorCode  +") : " + super.getMessage();
    }
}
