package Objects;

public class EmptyInputException extends Exception {
    public EmptyInputException(String input){
        System.out.println(input + " is not acceptable.. Please enter your name without whitespaces.");
    }

}
