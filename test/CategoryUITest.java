/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lynniest
 */

import stickynotes.*;
import java.util.ArrayList;

public class CategoryUITest {
    public static void main(String[] args) {
        testDuplicateExist();
        testFormatCategoryStr();
        testInitializeComponents();
        
        System.out.println("All tests executed successfully!");
    }
    
    public static void testDuplicateExist() {
        // Create a sample list of categories
        ArrayList<Categories> categories = new ArrayList<>();
        categories.add(new Categories("Category1", ""));
        categories.add(new Categories("Category2", ""));
        categories.add(new Categories("Category3", ""));
        

        CategoryUI categoryUI = new CategoryUI(categories);
        

        Categories existingCategory = new Categories("Category2", "");
        boolean result1 = categoryUI.duplicate_exist(existingCategory);
        System.out.println("Duplicate exist test 1: " + result1);  // Expecting true
        
        Categories nonExistingCategory = new Categories("Category4", "");
        boolean result2 = categoryUI.duplicate_exist(nonExistingCategory);
        System.out.println("Duplicate exist test 2: " + result2);  // Expecting false
        
      
        String existingCategoryName = "Category3";
        boolean result3 = categoryUI.duplicate_exist(existingCategoryName);
        System.out.println("Duplicate exist test 3: " + result3);  // Expecting true
        
        String nonExistingCategoryName = "Category5";
        boolean result4 = categoryUI.duplicate_exist(nonExistingCategoryName);
        System.out.println("Duplicate exist test 4: " + result4);  // Expecting false
    }
    
    public static void testFormatCategoryStr() {
        // Create a sample category
        Categories category = new Categories("Category1", "2023-05-25");
        
        // Create an instance of CategoryUI with an empty category list
        CategoryUI categoryUI = new CategoryUI(new ArrayList<>());
        
        // Test the formatCategoryStr method
        String formattedStr = categoryUI.formatCategoryStr(category.getCateName(), category.getCateDate());
        System.out.println("Formatted Category String: " + formattedStr);
        // Expecting "<html><div style='font-size: 13px;'>Category1</div><i style='font-size: 9px;'>2023-05-25</i><hr style='width: 10000px'></html>"
    }
    
    public static void testInitializeComponents() {
        // Create a sample category list
        ArrayList<Categories> categories = new ArrayList<>();
        categories.add(new Categories("Category1", "2023-05-05 00:00:00"));
        categories.add(new Categories("Category2", "2023-05-05 00:00:00"));
        categories.add(new Categories("Category3", "2023-05-05 00:00:00"));
        

        CategoryUI categoryUI = new CategoryUI(categories);
        
       
        int expectedSize = categories.size();
        int actualSize = categoryUI.listModel.getSize();
        System.out.println("List Model Size: " + actualSize);
        System.out.println("initializeComponents test 1: " + (expectedSize == actualSize));  // Expecting true
        
        
        int expectedItemCount = categories.size();
        int actualItemCount = categoryUI.itemList.getModel().getSize();
        System.out.println("Item List Item Count: " + actualItemCount);
        System.out.println("initializeComponents test 2: " + (expectedItemCount == actualItemCount));  // Expecting true
    }
    

}

