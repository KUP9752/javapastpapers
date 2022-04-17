import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Group implements Contact {
    private String name;
    private Set<Contact> contacts;

    public Group(String var1) {
        this.name = var1;
        this.contacts = new HashSet();
    }

    public boolean add(Contact var1) {
        return this.contacts.add(var1);
    }

    public boolean remove(Contact var1) {
        return this.contacts.remove(var1);
    }

    public String getName() {
        return this.name;
    }

    public Set<Person> getPeople() {
        HashSet var1 = new HashSet();
        Iterator var2 = this.contacts.iterator();

        while(var2.hasNext()) {
            Contact var3 = (Contact)var2.next();
            var1.addAll(var3.getPeople());
        }

        return var1;
    }

    public int size() {
        return this.getPeople().size();
    }
}