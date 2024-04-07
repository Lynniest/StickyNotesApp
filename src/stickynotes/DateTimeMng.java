 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stickynotes;

import java.util.GregorianCalendar;

/**
 *
 * @author Swanm Htet Lynn
 */

public class DateTimeMng {

    public String getFormattedDate(int yr, int mon, int d, int hr, int min, int sec){
        String date = yr + "-"+String.format("%02d", mon) +"-" + 
                String.format("%02d", d) +" " + String.format("%02d", hr) 
                +":"+ String.format("%02d", min) + ":"+String.format("%02d", sec);
        return date;
    }
    public String getFormattedCurrentDate(){
        GregorianCalendar gc = new GregorianCalendar();
        return getFormattedDate(gc.get(gc.YEAR), gc.get(gc.MONTH), 
                gc.get(gc.DAY_OF_MONTH), gc.get(gc.HOUR),
                gc.get(gc.MINUTE), gc.get(gc.SECOND));
    }
}
