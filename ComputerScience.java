/**
 * a Computer Science course.
 * @author Kathie Huynh
 * @version JDK 11.0.13
 */
public class ComputerScience extends Course {
    private String language;

    /**
     * creates a computer science course.
     * @param courseName the String of the course name
     * @param id the int of the course id
     * @param professorName the String of the professor name
     * @param language the String of the language used in the class
     */
    public ComputerScience(String courseName, int id,
        String professorName, String language) {
        super(courseName, id, professorName);
        this.language = language;
    }

    @Override
    public String toString() {
        return String.format("ComputerScience,%s,%s", super.toString(),
            language);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ComputerScience) {
            return (super.equals(other)
                && language.equals(((ComputerScience) other).language));
        } else {
            return false;
        }
    }

    /**
     * getter for the language of a computer science class.
     * @return the String of the class's language
     */
    public String getLanguage() {
        return language;
    }
}