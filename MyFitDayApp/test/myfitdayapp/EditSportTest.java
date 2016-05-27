
package myfitdayapp;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for user input in EditSport.java
 * 
 * When a user enters a unsupported data, the text field is shown with red border
 * 
 * @author olgachristensen
 */
public class EditSportTest {
    
    // Correct data is entered in Name and Calories fields -> border == null
    @Test
    public void testAddCorrectInput() {
        System.out.println("add correct data");
        
        EditSport es = new EditSport("23.5.2016", null, false);
        
        es.btnAdd.setEnabled(true);
        es.nameField.setText("name");
        es.calField.setText("123");
        es.btnAdd.doClick();
                
        assertTrue(es.nameField.getBorder() == null);
        assertTrue(es.calField.getBorder() == null);
        
    }

    // Trying to add empty Name field -> error
    @Test
    public void testAddEmptyName() {
        System.out.println("add empty name field");
        
        EditSport es = new EditSport("23.5.2016", null, false);
        
        System.out.println(es.nameField.getBorder());
        
        es.btnAdd.setEnabled(true);
        es.nameField.setText(null);
        es.calField.setText("120");
        es.btnAdd.doClick();
                
        assertTrue(es.nameField.getBorder() != null);
        
    }
    
    // Trying to add empty Calorie field -> error
    @Test
    public void testAddEmptyCals() {
        System.out.println("add empty cal field");
        
        EditSport es = new EditSport("23.5.2016", null, false);
        
        es.btnAdd.setEnabled(true);
        es.nameField.setText("name");
        es.calField.setText("");
        es.btnAdd.doClick();
                
        assertTrue(es.calField.getBorder() != null);
        
    }
    
    // Trying to add text (not numbers) in Calorie field -> error
    @Test
    public void testAddNonIntegerCals() {
        System.out.println("add text cal field");
        
        EditSport es = new EditSport("23.5.2016", null, false);
        
        es.btnAdd.setEnabled(true);
        es.nameField.setText("name");
        es.calField.setText("name");
        es.btnAdd.doClick();
                
        assertTrue(es.calField.getBorder() != null);
        
    }
    
    
}
