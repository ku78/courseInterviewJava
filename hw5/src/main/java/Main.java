import dao.Students;
import entity.Student;

public class Main {

    private final static java.util.Random rand = new java.util.Random();

    public static void main(String[] args) {
        Students studentDAO = new Students();
        for (int i = 0; i <= 1000; i++) {
            Student student = new Student("Student_" + i, rand.nextInt((5 - 1) + 1) + 1);
            studentDAO.addNewStudent(student);
        }
    }
}
