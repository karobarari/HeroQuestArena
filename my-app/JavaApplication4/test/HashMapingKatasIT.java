/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karo
 */
public class HashMapingKatasIT {
    
    public HashMapingKatasIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nameAndAgeTest() {
        // Arrange
        HashMap<String, Integer> nameAndAge = new HashMap<>();
        nameAndAge.put("Alice", 25);
        nameAndAge.put("Bob", 30);
        nameAndAge.put("Charlie", 35);

        // Act & Assert
        assertEquals("The size of the map should be 3.", 3, nameAndAge.size());
        assertEquals("The value for 'Bob' should be 30.", Integer.valueOf(30), nameAndAge.get("Bob"));

        nameAndAge.remove("Charlie");
        assertFalse("The map should no longer contain the key 'Charlie'.", nameAndAge.containsKey("Charlie"));
        assertEquals("The size of the map should now be 2.", 2, nameAndAge.size());
    }

    /**
     * Test of main method, of class HashMapingKatas.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        HashMapingKatas.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
