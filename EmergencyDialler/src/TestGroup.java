public class TestGroup {
    public TestGroup() {
    }

    public static void main(String[] var0) {
        Group var1 = new Group("Gp1");
        Group var2 = new Group("Gp2");
        var1.add(var2);
        if (!var1.remove(var2)) {
            System.out.println("Failed remove() or add()");
        } else if (!var1.getName().equals("Gp1")) {
            System.out.println("Failed getName()");
        } else if (var1.getPeople().size() != 0) {
            System.out.println("Failed getPeople()");
        } else {
            System.out.println("Succeeded test!");
        }
    }
}
