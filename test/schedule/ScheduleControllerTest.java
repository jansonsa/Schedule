/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import schedule.models.Booking;

/**
 *
 * @author DELL
 */
public class ScheduleControllerTest {
    
    ArrayList<Booking> bookings;
    ScheduleController instance;
    
    public ScheduleControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = ScheduleController.getInstance();
        bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setTitle("Not matching title");
        bookings.add(booking);
        booking = new Booking();
        booking.setTitle("Title");
        bookings.add(booking);
        booking = new Booking();
        booking.setTitle("Not matching title");
        bookings.add(booking);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ScheduleController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ScheduleController result = ScheduleController.getInstance();
        assertNotEquals(null, result);
    }
    
    /**
     * Test of getInstance method, of class ScheduleController.
     */
    @Test
    public void testGetInstanceSingleton() {
        System.out.println("getInstance - Singleton");
        ScheduleController result = ScheduleController.getInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of filterByTitle method, of class ScheduleController.
     */
    @Test
    public void testFilterByTitleSize() {
        System.out.println("filterByTitle - Size");
        assertEquals(instance.filterByTitle(bookings, "Title").size(), 1);
    }
    
    /**
     * Test of filterByTitle method, of class ScheduleController.
     */
    @Test
    public void testFilterByTitleElement() {
        System.out.println("filterByTitle - Element");
        assertEquals(instance.filterByTitle(bookings, "Title").get(0).getTitle(), "Title");
    }
    
    /**
     * Test of filterByTitle method, of class ScheduleController.
     */
    @Test
    public void testFilterByTitleNotFound() {
        System.out.println("filterByTitle - Not found");
        assertEquals(instance.filterByTitle(bookings, "This title does not exist in list").isEmpty(), true);
    }
    
    /**
     * Test of filterByTitle method, of class ScheduleController.
     */
    @Test
    public void testFilterByTitlePartial() {
        System.out.println("filterByTitle - Partial");
        assertEquals(instance.filterByTitle(bookings, "Tit").isEmpty(), true);
    }
    
    /**
     * Test of convertToPositiveInt method, of class ScheduleController.
     */
    @Test
    public void testconvertToPositiveIntValid() {
        System.out.println("convertToPositiveInt - Valid input");
        assertEquals((int)instance.convertToPositiveInt("123"), 123);
    }
    
    /**
     * Test of convertToPositiveInt method, of class ScheduleController.
     */
    @Test
    public void testconvertToPositiveIntValidWithWhitespace() {
        System.out.println("convertToPositiveInt - Valid input with whitespace");
        assertEquals((int)instance.convertToPositiveInt("   123     "), 123);
    }
    
    /**
     * Test of convertToPositiveInt method, of class ScheduleController.
     */
    @Test
    public void testconvertToPositiveIntNotValid() {
        System.out.println("convertToPositiveInt - Not valid input");
        assertEquals(instance.convertToPositiveInt("123abc"), null);
    }
    
    /**
     * Test of convertToPositiveInt method, of class ScheduleController.
     */
    @Test
    public void testconvertToPositiveIntNegative() {
        System.out.println("convertToPositiveInt - Negative input");
        assertEquals(instance.convertToPositiveInt("-123"), null);
    }
    
    
    
    
}
