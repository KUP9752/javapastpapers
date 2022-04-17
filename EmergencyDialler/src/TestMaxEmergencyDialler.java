
public class TestMaxEmergencyDialler {
    public TestMaxEmergencyDialler() {
    }

    public static void main(String[] var0) {
        MaxEmergencyDialler var1 = new MaxEmergencyDialler(new Location(1, 2), new Dialler());
        var1.addToEmergencyContactList(new Group("gp1"));
        var1.emergency();
        System.out.println("Succeeded test!");
    }
}