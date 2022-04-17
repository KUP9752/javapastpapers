import java.util.HashSet;
import java.util.Set;

public class ProgrammingTest {

    public static void main(String[] args) {
        Academic rr = new Academic("Ricardo Rodriguez");
        Academic ib = new Academic("Ismael Bento");

        Set<Student> students = new HashSet<>();
        students.add(new Undergraduate("gg4", "GG", "gg4@", rr));
        students.add(new Undergraduate("pr3", "PR", "pr3@", ib));
        students.add(new Postgraduate("te2", "TE", "te2@", rr));
        students.add(new Postgraduate("yj34", "YJ", "yj34@", ib));
        students.add(new Postgraduate("jj8", "JJ", "jj8@", ib));

        Course COMP = new Course("40009", students);
        System.out.println(COMP.getPostGraduates(ib.getName()));

        Notifier notifier = new Notifier(students);
        notifier.doNotifyAll("You have been notified!");

    }

}
