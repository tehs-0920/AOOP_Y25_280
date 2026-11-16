
import java.util.*;

public class CollectionsDemo {

    public static void main(String[] args) {

        // List: allows duplicates and maintains insertion order
        List<String> names = new ArrayList<>();
        names.add("Tehseen");
        names.add("Aisha");
        names.add("Tehseen");

        System.out.println("LIST: " + names);

        // Set: stores unique elements
        Set<String> cities = new LinkedHashSet<>();
        cities.add("Hyderabad");
        cities.add("Delhi");
        cities.add("Hyderabad");

        System.out.println("SET: " + cities);

        // Map: stores key-value pairs
        Map<Integer, String> students = new HashMap<>();
        students.put(101, "Tehseen");
        students.put(102, "Aisha");

        System.out.println("MAP: " + students);

        // Queue: follows FIFO order
        Queue<String> queue = new LinkedList<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        System.out.println("QUEUE: " + queue);
        System.out.println("Removed from queue: " + queue.poll());

        // Stack: follows LIFO order
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("STACK: " + stack);
        System.out.println("Removed from stack: " + stack.pop());
    }
}

