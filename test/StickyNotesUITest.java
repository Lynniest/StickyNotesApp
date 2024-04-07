/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lynniest
 */

import stickynotes.*;

public class StickyNotesUITest {

    public static void main(String[] args) {
        StickyNotesUITest stickyNotesUITest = new StickyNotesUITest();
        
        stickyNotesUITest.testFormattedNotesStr();
        stickyNotesUITest.testUpdateNoteDb();
        
        System.out.println("All tests executed successfully!");
    }
    
    
    public void testFormattedNotesStr() {
        StickyNotesUI stickyNotesUI = new StickyNotesUI(null); // Create an instance of StickyNotesUI
        int note_id = (int) Math.floor(Math.random()*10000);
        Notes note = new Notes(note_id, "Some Title", "Some Content", "Category Name", "2023-05-08 12:30:00", true, true);
        note.setCompleted(true);
        note.setSticked(true);
        String expectedFormattedStr = "<html><div style = 'text-decoration: wavy line-through green 3px;'><p style='font-size: 14px;'>Some Title</p><p style='font-size: 10px;'>Some Content</p><div style='font-size: 9px;'><i> Category: Category Name</i></div><i style= 'font-size: 8px;'>2023-05-08 12:30:00&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=' width: 200px; font-size:9px; text-align:right;'>sticked</span></i></div><hr style='width: 7000px;'></html>";
        // Test the formattedNotesStr method
        String actualFormattedStr = stickyNotesUI.formattedNotesStr(note);
        
        // Verify that the actual formatted string matches the expected formatted string
        System.out.println("Expected formatted string: " + expectedFormattedStr);
        System.out.println("Actual formatted string: " + actualFormattedStr);
        if (!actualFormattedStr.equals(expectedFormattedStr)) {
            System.out.println("Test failed: formattedNotesStr method");
        }
    }
    
    public void testUpdateNoteDb() {
        StickyNotesUI stickyNotesUI = new StickyNotesUI(null); // Create an instance of StickyNotesUI
        
        int note_id = (int) Math.floor(Math.random()*10000);
        Notes note = new Notes(note_id, "Some Title", "Some Content", "Category Name", "2023-05-08 12:30:00", true, true);
        
        stickyNotesUI.updateNoteDb(note);
        
        // You can add additional assertions here to verify if the note was successfully updated in the database
    }
}

