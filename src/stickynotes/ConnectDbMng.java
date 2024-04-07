/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stickynotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swanm Htet Lynn
 */

public class ConnectDbMng {
    
    public Connection makeConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/to_do_list_sys","root","");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }    
    
    }
    
    public ArrayList<Categories> getAllCategories() {
        ArrayList toReturn = new ArrayList<Categories>();
        ArrayList alcategories = new ArrayList<Categories>();
        try {
            Connection con = makeConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categorytable");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                alcategories.add(new Categories(rs.getString(1), rs.getString(2)));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        
        for (int i = alcategories.size()-1; i >= 0; i--) {
            toReturn.add(alcategories.get(i));
        }
        
        return toReturn;
    }
    
    public ArrayList<Notes> getAllNotes(){
        
        ArrayList toReturn = new ArrayList<Notes>();
        ArrayList alNotes = new ArrayList<Notes>();
        try {
            Connection con = makeConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stickynotestable");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                alNotes.add(new Notes(rs.getInt("note_id"),rs.getString("title"), rs.getString("content"), 
                        rs.getString("category"),rs.getString("date_created"), 
                        rs.getBoolean("completed"), rs.getBoolean("sticked")));
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = alNotes.size()-1; i >= 0; i--) {
            toReturn.add(alNotes.get(i));
        }
        
        return toReturn;
    }
    
    public ArrayList<Notes> getNotesInCategory(String cate_name){
        
        ArrayList toReturn = new ArrayList<Notes>();
        ArrayList alNotes = new ArrayList<Notes>();
        try {
            Connection con = makeConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stickynotestable WHERE category = '"+ cate_name + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                alNotes.add(new Notes(rs.getInt("note_id"), rs.getString("title"), rs.getString("content"), 
                        rs.getString("category"),rs.getString("date_created"), 
                        rs.getBoolean("completed"), rs.getBoolean("sticked")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = alNotes.size()-1; i >= 0; i--) {
            toReturn.add(alNotes.get(i));
        }
        
        return toReturn;
    }
    
    public Categories getCategoryformCateName(String categoryName){
        
        try {

            Connection con = makeConnection();
            PreparedStatement ps = con.prepareCall("SELECT * FROM categorytable WHERE cate_name = '"+ categoryName + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Categories category = new Categories(rs.getString("cate_name"), rs.getString("cate_created_date"));   
                return category;
            }   
        } 
        catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;  
    }

    public void deleteRowFromNoteTable(Notes note) {
    Connection con = makeConnection();
    int id = note.getNoteID();
    try {
        PreparedStatement ps = con.prepareStatement("DELETE FROM `stickynotestable` WHERE note_id = ?");
        ps.setInt(1, id);
        int records = ps.executeUpdate();
        System.out.println(records + " row(s) deleted successfully");
        ps.close();
        con.close();
    } catch (NullPointerException | SQLException ex) {
        ex.printStackTrace();
    }
}

    public void deleteRowFromCateTable(String row_name, String row_id){
        Connection con = makeConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM categorytable WHERE "+ row_name +" = ?");
            ps.setString(1, row_id);
            System.out.println();
            int records = ps.executeUpdate();
            System.out.println(records + " row(s) deleted successfully.");
            ps.close();
            con.close();
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
    public void insertToCateTable(String category_str, String date_str){
        Connection con = makeConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO categorytable (cate_name, cate_created_date) VALUES (?,?)");
            ps.setString(1, category_str);
            ps.setString(2, date_str);
            int records = ps.executeUpdate();
            System.out.println(records + "record(s) inserted successfully.");
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertToNoteTable(String title, String content, String date, boolean completed, boolean sticked, String category){
        Connection con = makeConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO stickynotestable (title, content, date_created, completed, sticked, category) VALUES (?,?,?,?,?,?)");
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, date);
            ps.setBoolean(4, completed);
            ps.setBoolean(5, sticked);
            ps.setString(6, category);
            int records = ps.executeUpdate();
            System.out.println(records + "record(s) inserted successfully.");
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateCateTable(Categories to_update, String newCateName){
        try {
            String oldCateName = to_update.getCateName();
            String current_date = new DateTimeMng().getFormattedCurrentDate();
            Connection con = makeConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE categorytable SET cate_name = ?, cate_created_date = ? WHERE cate_name = '"+oldCateName+"'");
            ps.setString(1, newCateName);
            ps.setString(2, current_date);
            int records = ps.executeUpdate();
            if (records>0) {
                System.out.println(records + "record(s) updated successfully.");
                to_update.setCateName(newCateName);
                to_update.setCateDate(current_date);
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateNoteTable(Notes to_update){
        try {
            int id = to_update.getNoteID();
//            String current_date = new DateTimeMng().getFormattedCurrentDate();
            Connection con = makeConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE stickynotestable SET title = ?,"
                    + " content = ?, date_created = ?, completed = ?, sticked = ?, category = ? "
                    + "WHERE note_id = " +id);
            ps.setString(1, to_update.getTitle());
            ps.setString(2, to_update.getContent());
            ps.setString(3, to_update.getDate());
            ps.setBoolean(4, to_update.isCompleted());
            ps.setBoolean(5, to_update.isSticked());
            ps.setString(6, to_update.getCategory());
            int records = ps.executeUpdate();
            if (records>0) {
                System.out.println(records + " record(s) updated successfully.");
                
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Notes> filterNotes(Categories category, String col_to_filter, String filter_value){
        
        ArrayList<Notes> toReturn = new ArrayList();
        ArrayList<Notes> alNotes = new ArrayList();
        
        try {
            Connection con = makeConnection();
            String include_category = "AND category = ?;";
            if (category==null) {
                include_category = "";
            }
            PreparedStatement ps;
            
            if ("completed".equals(col_to_filter) || "sticked".equals(col_to_filter)) {
                
                ps = con.prepareStatement("SELECT * FROM stickynotestable WHERE " + col_to_filter+ " = ? " + include_category);
                System.out.println("SELECT * FROM stickynotestable WHERE " + col_to_filter+ " = ? "  + include_category);
                ps.setBoolean(1, Boolean.parseBoolean(filter_value));
            }
            else{
                String like_filter_value = "%"+filter_value+"%";
                 ps = con.prepareStatement("SELECT * FROM stickynotestable WHERE " + col_to_filter+ " LIKE ? " + include_category);
//                 System.out.println("SELECT * FROM stickynotestable WHERE " + col_to_filter+ " LIKE ?"  + include_category);
                 ps.setString(1, like_filter_value);   
            }
            if (category!=null) {
                ps.setString(2, category.getCateName());
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                alNotes.add(new Notes(rs.getInt("note_id"),rs.getString("title"), rs.getString("content"), 
                        rs.getString("category"),rs.getString("date_created"), 
                        rs.getBoolean("completed"), rs.getBoolean("sticked")));
            }
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDbMng.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = alNotes.size()-1; i >= 0; i--) {
            toReturn.add(alNotes.get(i));
        }
        return toReturn;
    }
    
//    public static void main(String[] args) {
//        ConnectDbMng cdb = new ConnectDbMng();
//        
//        System.out.println(cdb.filterNotes(cdb.getAllCategories().get(0), "sticked", "true"));
//    }

}
