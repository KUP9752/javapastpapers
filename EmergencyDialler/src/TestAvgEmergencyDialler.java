public class TestAvgEmergencyDialler {
    public TestAvgEmergencyDialler() {
    }

    public static void main(String[] var0) {
        AvgEmergencyDialler var1 = new AvgEmergencyDialler(new Location(1, 2), new Dialler());
        var1.addToEmergencyContactList(new Group("gp1"));
        var1.emergency();
        System.out.println("Succeeded test!");
    }
}