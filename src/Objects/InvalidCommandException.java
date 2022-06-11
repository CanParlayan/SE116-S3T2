package Objects;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String command){
        System.out.println("The command " + command + " is invalid. Please double check.");
    }
}