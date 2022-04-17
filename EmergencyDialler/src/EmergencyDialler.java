import java.util.Iterator;
import java.util.PriorityQueue;

public abstract class EmergencyDialler {
    protected PriorityQueue<Contact> queue = new LinkedListPriorityQueue();
    protected Location location;
    private Dialler d;

    public EmergencyDialler(Location var1, Dialler var2) {
        this.d = var2;
        this.location = var1;
    }

    public Contact emergency() {
        boolean var1 = false;

        Contact var2;
        for(var2 = (Contact)this.queue.dequeue(); var2 != null && !var1; var2 = (Contact)this.queue.dequeue()) {
            Iterator var3 = var2.getPeople().iterator();
            Person var4;
            if (var3.hasNext()) {
                for(var1 = true; var3.hasNext() && var1; var1 = this.d.call(var4.getTelephoneNumber(), var4.getName())) {
                    var4 = (Person)var3.next();
                }
            }
        }

        return var2;
    }

    public abstract void addToEmergencyContactList(Contact var1);
}