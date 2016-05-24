/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitdayapp;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olgachristensen
 */
public class EditSportTest {
    
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
    
    
    
    
// nameField.getBorder != null;
    
    
}
