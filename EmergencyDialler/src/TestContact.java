import java.lang.reflect.Method;
import java.util.Set;

public class TestContact {
    public TestContact() {
    }

    public static void main(String[] var0) {
        Class var1 = Contact.class;
        if (checkMethods(var1.getMethods())) {
            System.out.println("Succeeded!");
        }
    }

    public static boolean checkMethods(Method[] var0) {
        boolean var1 = false;
        boolean var2 = false;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            Method var4 = var0[var3];
            System.out.println(var4.toString());
            if (var4.getName().equals("getPeople")) {
                var1 = true;
                if (var4.getParameterTypes().length != 0) {
                    System.out.println("getPeople: wrong number of parameters");
                    return false;
                }

                if (var4.getReturnType() != Set.class) {
                    System.out.println("emergency: wrong return type");
                    return false;
                }
            }

            if (var4.getName().equals("getName")) {
                var2 = true;
                if (var4.getParameterTypes().length != 0) {
                    System.out.println("getName: wrong number of parameters");
                    return false;
                }

                if (var4.getReturnType() != String.class) {
                    System.out.println("getName: wrong return type");
                    return false;
                }
            }
        }

        if (!var1) {
            System.out.println("Failed to find emergency method");
            return false;
        } else if (!var2) {
            System.out.println("Failed to find getName");
            return false;
        } else {
            return true;
        }
    }
}
