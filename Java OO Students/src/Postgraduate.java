
public class Postgraduate extends Student{

    private Academic supervisor;

    public Postgraduate(String login, String name, String email, Academic supervisor) {
        super(login, name, email);
        this.supervisor = supervisor;
    }

    public Academic getSupervisor() {
        return supervisor;
    }

    @Override
    public String toString(){
        return getLogin() + ": " + getName() ;
    }
	
}
