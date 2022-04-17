public class TestEmergencyDialler {
    public TestEmergencyDialler() {
    }

    public static void main(String[] var0) {
        Class var1 = EmergencyDialler.class;
        if (checkMethods(var1.getMethods())) {
            if (checkConstructors(var1.getConstructors())) {
                System.out.println("Succeeded!");
            }
        }
    }

    public static boolean checkConstructors(Constructor[] var0) {
        boolean var1 = false;

        for(int var2 = 0; var2 < var0.length; ++var2) {
            Constructor var3 = var0[var2];
            System.out.println(var3.toString());
            if (var3.getParameterTypes().length == 2 && var3.getParameterTypes()[0] == Location.class && var3.getParameterTypes()[1] == Dialler.class) {
                var1 = true;
            }
        }

        if (!var1) {
            System.out.println("Constructor EmergencyDialler(Location, Dialler, PriorityQueue<Contact>) not found");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkMethods(Method[] var0) {
        boolean var1 = false;
        boolean var2 = false;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            Method var4 = var0[var3];
            System.out.println(var4.toString());
            if (var4.getName().equals("emergency")) {
                var1 = true;
                if (var4.getParameterTypes().length != 0) {
                    System.out.println("emergency: wrong number of parameters");
                    return false;
                }

                if (var4.getReturnType() != Contact.class) {
                    System.out.println("emergency: wrong return type");
                    return false;
                }
            } else if (var4.getName().equals("addToEmergencyContactList")) {
                var2 = true;
                if (!var4.getReturnType().getName().equals("void")) {
                    System.out.println("addToEmergencyContactList: incorrect return type");
                    return false;
                }

                if (var4.getParameterTypes().length != 1) {
                    System.out.println("addToEmergencyContactList: wrong number of parameters");
                    return false;
                }

                if (var4.getParameterTypes()[0] != Contact.class) {
                    System.out.println("addToEmergencyContactList: incorrect parameter type");
                    return false;
                }
            }
        }

        if (!var1) {
            System.out.println("Failed to find emergency method");
            return false;
        } else if (!var2) {
            System.out.println("Failed to find addToEmergencyContactList");
            return false;
        } else {
            return true;
        }
    }
}
