package Objects;

public class InvalidTargetException extends Exception{
    public InvalidTargetException(String target){
        System.out.println("The target " + target + " is not a valid one. Please double check.");
    }
}