/**
 * custom unchecked exception, invalid course.
 * @author Kathie Huynh
 * @version JDK 11.0.13
 */
public class InvalidCourseException extends RuntimeException {
    private String message;

    /**
     * creates an invalid course exception.
     * @param message the String of the exception's message
     */
    public InvalidCourseException(String message) {
        this.message = message;
    }

    /**
     * creates an invalid course exception.
     */
    public InvalidCourseException() {
        this("Invalid Course Type!");
    }
}