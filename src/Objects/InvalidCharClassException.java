package Objects;

public class InvalidCharClassException extends Exception {
    public String getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    private String errorClass;

    public InvalidCharClassException(String classname){
        this.errorClass = classname;
        System.out.println("No! " + errorClass + " isn't a valid class!");
    }

}
