import java.lang.reflect.Method;

public class TestLinkedListPriorityQueue {
    public TestLinkedListPriorityQueue() {
    }

    public static void main(String[] var0) {
        Class var1 = LinkedListPriorityQueue.class;
        if (checkMethods(var1.getMethods())) {
            if (var1.getInterfaces().length != 1) {
                System.out.println("PriorityQueue does not extend the right number of interfaces");
            } else if (var1.getInterfaces()[0] != PriorityQueue.class) {
                System.out.println("PriorityQueue does not extend the right interfaces");
            } else {
                System.out.println("Succeeded!");
            }
        }
    }

    public static boolean checkMethods(Method[] var0) {
        boolean var1 = false;
        boolean var2 = false;
        System.out.println(var0.length);

        for(int var3 = 0; var3 < var0.length; ++var3) {
            Method var4 = var0[var3];
            System.out.println(var4.toString());
            if (var4.getName().equals("add")) {
                var1 = true;
                if (var4.getParameterTypes().length != 2) {
                    System.out.println("add: wrong number of parameters");
                    return false;
                }

                if (!var4.getReturnType().getName().equals("void")) {
                    System.out.println("add: wrong return type");
                    return false;
                }

                if (var4.getParameterTypes()[0] != Double.TYPE || var4.getParameterTypes()[1] != Object.class) {
                    System.out.println("add: incorrect parameter type");
                    return false;
                }
            }

            if (var4.getName().equals("dequeue")) {
                var2 = true;
                if (var4.getParameterTypes().length != 0) {
                    System.out.println("dequeue: wrong number of parameters");
                    return false;
                }

                if (var4.getReturnType() != Object.class) {
                    System.out.println("dequeue: wrong return type");
                    return false;
                }
            }
        }

        if (!var1) {
            System.out.println("Failed to find add method");
            return false;
        } else if (!var2) {
            System.out.println("Failed to find remove");
            return false;
        } else {
            return true;
        }
    }
}