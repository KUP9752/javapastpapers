public class BuildEmergencyDialler {
    public BuildEmergencyDialler() {
    }

    public static void main(String[] var0) {
        Person var1 = new Person("Jensen", 4, 2, -9);
        Group var2 = new Group("contact2");
        var2.add(new Person("Jamil", 3, 0, 32));
        Group var3 = new Group("");
        var3.add(new Person("Ji", 5, -4, -9));
        var3.add(new Person("Jane", 2, -4, 1));
        var2.add(var3);
        Group var4 = new Group("contact3");
        var4.add(new Person("Joe", 1, 2, 3));
        AvgEmergencyDialler var5 = new AvgEmergencyDialler(new Location(1, 1), new Dialler());
        var5.addToEmergencyContactList(var1);
        var5.addToEmergencyContactList(var2);
        var5.addToEmergencyContactList(var4);
        var5.emergency();
    }
}