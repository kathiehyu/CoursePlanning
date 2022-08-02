/**
 * Lab Science course.
 * @author Kathie Huynh
 * @version JDK 11.0.13
 */
public class LabScience extends Course {
    private boolean labCoatRequired;

    /**
     * creates a lab science course.
     * @param courseName the String of the course name
     * @param id the int of the course id
     * @param professorName the String of the professor name
     * @param labCoatRequired the boolean of whether a lab coat is required or not
     */
    public LabScience(String courseName, int id,
        String professorName, boolean labCoatRequired) {
        super(courseName, id, professorName);
        this.labCoatRequired = labCoatRequired;
    }

    @Override
    public String toString() {
        return String.format("LabScience,%s,%b", super.toString(), labCoatRequired);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LabScience) {
            return (super.equals(other)
                && labCoatRequired == ((LabScience) other).labCoatRequired);
        } else {
            return false;
        }
    }

    /**
     * getter for if a lab coat is required or not.
     * @return the boolean of whether or not a lab coat is required
     */
    public boolean getLabCoat() {
        return labCoatRequired;
    }
}