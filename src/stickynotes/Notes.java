/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stickynotes;

/**
 *
 * @author Swanm Htet Lynn
 */
public class Notes {
    
    
    private String title, content, category, date;
    private boolean completed, sticked;
    private final int noteID;
    
    public Notes(int id, String title, String content, String category, String date, boolean completed, boolean sticked) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
        this.completed = completed;
        this.sticked = sticked;
        this.noteID = id;
    }
        
    public int getNoteID(){
        return noteID;
}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSticked() {
        return sticked;
    }

    public void setSticked(boolean sticked) {
        this.sticked = sticked;
    }

    
}
