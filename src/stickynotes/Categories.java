/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stickynotes;

/**
 *
 * @author Lynniest
 */
public class Categories {
    private String cate_name, cate_date;
    
    public Categories(String cate_name, String cate_date) {
        this.cate_name = cate_name;
        this.cate_date = cate_date;
        
    }
    public String getCateName() {
        return cate_name;
    }

    public String getCateDate() {
        return cate_date;
    }
    public void setCateName(String newName){
        this.cate_name = newName;
    }
    public void setCateDate(String newDate){
        this.cate_date = newDate;
    }
    
}
