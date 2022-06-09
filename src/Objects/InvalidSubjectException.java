package Objects;

public class InvalidSubjectException extends Exception {
    public InvalidSubjectException(String subject){
        System.out.println("The subject " + subject + " is not valid.. Please double check.");
    }
}