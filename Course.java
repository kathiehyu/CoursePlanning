/**
 * Course class.
 * @author Kathie Huynh
 * @version JDK 11.0.13
 */
public abstract class Course {
    protected String courseName;
    protected int id;
    protected String professorName;

    /**
     * creates a course object.
     * @param courseName the String of the cours name
     * @param id the int of the course id
     * @param professorName the String of the professor's name
     */
    public Course(String courseName,  int id, String professorName) {
        if (courseName == null || professorName == null
            || courseName.length() == 0 || professorName.length() == 0
            || Integer.toString(id).length() != 5 || id < 0) {
            throw new IllegalArgumentException();
        } else {
            this.courseName = courseName;
            this.id = id;
            this.professorName = professorName;
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%s", courseName, id, professorName);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Course) {
            Course o = (Course) other;
            return (courseName.equals(o.courseName) && id == o.id
                && professorName.equals(o.professorName));
        } else {
            return false;
        }
    }

    /**
     * getter for course name.
     * @return the String of the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * getter for the course id.
     * @return the int of the course id
     */
    public int getID() {
        return id;
    }

    /**
     * getter for the professor name.
     * @return the String of the professor name
     */
    public String getProfessorName() {
        return professorName;
    }
}