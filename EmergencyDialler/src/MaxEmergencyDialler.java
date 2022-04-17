import java.util.Iterator;

public class MaxEmergencyDialler extends EmergencyDialler {
    public MaxEmergencyDialler(Location var1, Dialler var2) {
        super(var1, var2);
    }

    public void addToEmergencyContactList(Contact var1) {
        Iterator var2 = var1.getPeople().iterator();
        double var3 = 0.0D;

        while(var2.hasNext()) {
            double var5 = ((Person)var2.next()).getAddress().distanceTo(this.location);
            if (var5 > var3) {
                var3 = var5;
            }
        }

        this.queue.add(var3, var1);
    }
}
