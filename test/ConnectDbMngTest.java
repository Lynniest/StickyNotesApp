/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lynniest
 */

import java.sql.Connection;
import stickynotes.*;
import java.util.ArrayList;
public class ConnectDbMngTest {
    public static void main(String[] args) {
        ConnectDbMngTest dbMngTest = new ConnectDbMngTest();
        
        dbMngTest.testMakeConnection();
        dbMngTest.testGetAllCategories();
        dbMngTest.testGetAllNotes();
        dbMngTest.testGetNotesInCategory();
        dbMngTest.testGetCategoryformCateName();

        System.out.println("All tests executed successfully!");
    }
    
    public void testMakeConnection() {
        ConnectDbMng dbManager = new ConnectDbMng();
        
        // Test the makeConnection method
        Connection connection = dbManager.makeConnection();
        System.out.println("Connection: " + connection);
        // Expecting a non-null connection object
    }
    
    public void testGetAllCategories() {
        ConnectDbMng dbManager = new ConnectDbMng();
        
        // Test the getAllCategories method
        ArrayList<Categories> categories = dbManager.getAllCategories();
        System.out.println("All Categories: " + categories);
        // Expecting a list of categories from the database
    }
    
    public void testGetAllNotes() {
        ConnectDbMng dbManager = new ConnectDbMng();
        
        // Test the getAllNotes method
        ArrayList<Notes> notes = dbManager.getAllNotes();
        System.out.println("All Notes: " + notes);
        // Expecting a list of notes from the database
    }
    
    public void testGetNotesInCategory() {
        ConnectDbMng dbManager = new ConnectDbMng();
        String categoryName = "Test Category";
          
        
        // Test the getNotesInCategory method
        ArrayList<Notes> notesInCategory = dbManager.getNotesInCategory(categoryName);
        System.out.println("Notes in Category '" + categoryName + "': " + notesInCategory);
        // Expecting a list of notes in the specified category
        
        categoryName = "Some kind of Category";
        notesInCategory = dbManager.getNotesInCategory(categoryName);
        System.out.println("Category for Name '" + categoryName + "': " + notesInCategory);
        // Expecting an empty list
    }
    
    public void testGetCategoryformCateName() {
        ConnectDbMng dbManager = new ConnectDbMng();
        String categoryName = "Test Category";
        
        // Test the getCategoryformCateName method
        Categories category = dbManager.getCategoryformCateName(categoryName);
        System.out.println("Category for Name '" + categoryName + "': " + category);
        // Expecting a category object with the specified name
        
        categoryName = "Some kind of Category";
        category = dbManager.getCategoryformCateName(categoryName);
        System.out.println("Category for Name '" + categoryName + "': " + category);
        // Expecting null value
    }
    
}
