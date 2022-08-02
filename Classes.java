import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class to create, read, and write files containing courses.
 * @author Kathie Huynh
 * @version JDK 11.0.13
 */
public class Classes {
    /**
     * reads the courses from a file and puts the Course objects into an ArrayList.
     * @param fileName the String of the file to read from
     * @return the ArrayList of the Courses in the file
     * @throws FileNotFoundException if the file does not exist or is not a file
     */
    public static ArrayList<Course> outputCourses(String fileName) throws FileNotFoundException {
        try {
            ArrayList<Course> courses = new ArrayList<Course>();
            if (fileName.equals(null)) {
                throw new FileNotFoundException();
            }
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                throw new FileNotFoundException();
            }
            Scanner input = new Scanner(file);
            input.useDelimiter(",|\\n");
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] line1 = line.split(",");
                String course = line1[0];
                String courseName = line1[1];
                int id = Integer.valueOf(line1[2]);
                String profName = line1[3];
                if (course.equals("ComputerScience")) {
                    String language = line1[4];
                    ComputerScience c = new ComputerScience(courseName, id, profName, language);
                    courses.add(c);
                } else if (course.equals("LabScience")) {
                    boolean labCoat = Boolean.parseBoolean(line1[4]);
                    LabScience c = new LabScience(courseName, id, profName, labCoat);
                    courses.add(c);
                } else {
                    throw new InvalidCourseException();
                }
            }
        } catch (FileNotFoundException FNFE) {
            FNFE.printStackTrace();
            courses.remove(0);
        } catch (InvalidCourseException ICE) {
            ICE.printStackTrace();
        }
        return courses;
    }

    /**
     * writes courses onto a file.
     * @param fileName the String of the file name
     * @param courses the ArrayList of the Courses to write
     * @return the boolean of whether or not the file is able to be written to
     * @throws FileNotFoundException if the file cannot be written to or found
     */
    public static boolean writeCourses(String fileName,
        ArrayList<Course> courses) throws FileNotFoundException {
        try {
            File sourceFile = new File(fileName);
            File tempFile = new File("tempFile.csv");
            PrintWriter write = new PrintWriter(tempFile);
            if (sourceFile.exists()) { //if file already exists, append
                Scanner scan = new Scanner(sourceFile);
                scan.useDelimiter(",|\\n");

                while (scan.hasNext()) {
                    String lines = scan.nextLine(); //scan lines in source
                    write.println(lines); //write to temp file
                }
                write.close();
            }
            PrintWriter pWriter = new PrintWriter(sourceFile);
            Scanner s = new Scanner(tempFile);
            while (s.hasNext()) {
                String line = s.nextLine(); //scan lines in temp file
                pWriter.println(line); //overrwrite to source file
            }
            for (Course course: courses) {
                String line;
                if (course instanceof ComputerScience) {
                    line = String.format("ComputerScience,%s,%d,%s,%s", course.getCourseName(),
                    course.getID(), course.getProfessorName(),
                    ((ComputerScience) course).getLanguage());
                    pWriter.println(line);
                } else if (course instanceof LabScience) {
                    line = String.format("LabScience,%s,%d,%s,%b", course.getCourseName(),
                    course.getID(), course.getProfessorName(),
                    ((LabScience) course).getLabCoat());
                    pWriter.println(line);
                }
            }
            pWriter.close();
            return true;
        } catch (FileNotFoundException FNFE) {
            FNFE.printStackTrace();
            return false;
        }
    }

    /**
     * reads the courses on a file to determine which lines a course is found on.
     * @param fileName the String of the file name
     * @param course the Course to look for in the file
     * @return an ArrayList of the Integers representing the lines that the course is on
     * @throws FileNotFoundException if the file cannot be found
     */
    public static ArrayList<Integer> readCourses(String fileName, Course course) throws FileNotFoundException {
        ArrayList<Integer> lines = new ArrayList<>();
        int line = 1;
        try {
            ArrayList<Course> list = Classes.outputCourses(fileName);
            for (Course c: list) {
                if (c.equals(course)) {
                    lines.add(Integer.valueOf(line));
                }
                line++;
            }
        } catch (FileNotFoundException FNFE) {
            FNFE.printStackTrace();
        }
        return lines;
    }

    /**
     * main method to create and interact with courses, test code.
     * @param args the main method arguments
     * @throws FileNotFoundException if a file cannot be found
     */
    public static void main(String[] args) throws FileNotFoundException {
        ComputerScience cs1301 = new ComputerScience("cs1301",
            25794, "McDaniel", "Python");
        ComputerScience cs1331 = new ComputerScience("cs1331",
            34522, "Landry", "Java");
        ComputerScience cs1332 = new ComputerScience("cs1332",
            43287, "next Prof", "Java");

        LabScience chem1211 = new LabScience("chem1211", 12492, "finn", true);
        LabScience chem1212 = new LabScience("chem1212", 24138, "sherry", true);
        LabScience phys2211 = new LabScience("phys2211", 32145, "proff", false);

        ArrayList<Course> list = new ArrayList<>();
        list.add(cs1301);
        list.add(phys2211);
        list.add(cs1331);
        list.add(chem1212);
        list.add(cs1332);
        list.add(chem1211);
        ComputerScience cs2050 = new ComputerScience("discrete",
            52482, "math prof", "math language");
        ArrayList<Course> list1 = new ArrayList<>();
        list1.add(cs2050);
        System.out.println(Classes.readCourses("TestCourses.csv", cs2050));
        // Classes.writeCourses("TestCourses.csv", list1);
        // System.out.println(Classes.readCourses("TestCourses.csv", cs2050));
        // Classes.writeCourses("TestCourses.csv", list1);

        for (Course c: (Classes.outputCourses("TestCourses.csv"))) {
            System.out.println(c);
        }

        ArrayList<Course> list2 = new ArrayList<>();
        list2.add(chem1211);
        list2.add(chem1212);
        list2.add(phys2211);
        // Classes.writeCourses("TextCourses.csv", list2);
        // Classes.writeCourses("Single Line.csv", list1);
        // System.out.println(Classes.readCourses("Single Line.csv", chem1211));
        // Classes.outputCourses("Courses.csv");

        // Classes.writeCourses("TestCourses.csv", list);


        // for (Course c: (Classes.outputCourses("TestCourses.csv"))) {
        //     System.out.println(c);
        // }
    }
}