import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Course {

    private String name;
    private Set<Student> students;

    public Course(String name, Set<Student> setStudents) {
        this.name = name;
        this.students = setStudents;
    }

    private Set<Postgraduate> postGraduates() {
        Set<Postgraduate> postGrads = new HashSet<>();

        for (Student student : students) {
            if (student instanceof Postgraduate) {
                postGrads.add((Postgraduate) student);
            }
        }

        return postGrads;
    }

    public Set<Student> getPostGraduates(String nameOfAcademic) {
        Set<Postgraduate> postGrads = postGraduates();
        return postGrads.stream().filter(
                postGrad -> postGrad.getSupervisor().getName().equals(nameOfAcademic))
                .collect(Collectors.toSet());
    }

	/* WRITE YOUR CODE HERE */

}
