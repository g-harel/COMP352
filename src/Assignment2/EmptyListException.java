package Assignment2;

public class EmptyListException extends Exception {

    public EmptyListException() {
        super("EmptyListException : The list you are trying to manipulate is empty");
    }
}
