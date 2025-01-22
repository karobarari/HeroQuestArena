
import java.util.HashMap;

public class HashMapingKatas {

    /*    Kata 1: Basic Operations
    Create a HashMap that maps String keys to Integer values.
Add the following entries:
"Alice" -> 25
"Bob" -> 30
"Charlie" -> 35
    
** Print the size of the map.
** Retrieve and print the value associated with the key "Bob".
** Remove "Charlie" from the map and verify it no longer exists.
     */
    public static void main(String[] args) {
        new nameAndAge();
    }

    private static class nameAndAge {

        public nameAndAge() {
            HashMap<String, Integer> nameAndAge = new HashMap<>();

            nameAndAge.put("Alice", 25);
            nameAndAge.put("Bob", 30);
            nameAndAge.put("Charlie", 35);

            System.out.println("Size of the map should be 3. result: "+ nameAndAge.size());
            System.out.println("The value for key Bob should be 30. result: "+nameAndAge.get("Bob"));
            nameAndAge.remove("Charlie");
            System.out.println("varifing key Charlie doesn'nt exist in the map any more. result if map contains:" + nameAndAge.containsKey("Charlie"));
        }
    }

}
