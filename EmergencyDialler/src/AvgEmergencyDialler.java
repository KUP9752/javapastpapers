import java.util.Iterator;

public class AvgEmergencyDialler extends EmergencyDialler {

    public AvgEmergencyDialler(Location var1, Dialler var2) {
        super(var1, var2);
    }

    public void addToEmergencyContactList(Contact var1) {
        Iterator var2 = var1.getPeople().iterator();
        double var3 = 0.0D;

        int var5;
        for(var5 = 0; var2.hasNext(); ++var5) {
            var3 += ((Person)var2.next()).getAddress().distanceTo(this.location);
        }

        this.queue.add(var3 / (double)var5, var1);
    }
}
