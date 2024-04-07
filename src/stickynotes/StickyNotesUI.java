/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stickynotes;

/**
 *
 * @author Swanm Htet Lynn
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StickyNotesUI extends JFrame {
    
    
    ConnectDbMng cdb = new ConnectDbMng();
    DateTimeMng dtmng = new DateTimeMng();
    private Categories cateObj = null;
    private ArrayList<Notes> notes; 
    private DefaultListModel<String> listModel;
    private JList<String> noteList;
    private JButton addNoteButton;
    private JButton markCompletedButton;
    private JButton stickNoteButton;
    private JButton deleteNoteButton;
    private JButton backButton;
    private JButton showOnlyStickies, showAll, showOnlyCompleted, searchBtn;

    public StickyNotesUI(Categories category) {
        cateObj = category;
        if (category == null) {
            notes = new ArrayList<>();
        }
        else{
            String category_name = category.getCateName();
            notes = cdb.getNotesInCategory(category_name);
        }
        initComponents();
        setUpEvtListeners(category);
    }
    
    public StickyNotesUI(Categories category, ArrayList<Notes> alNotes){
        notes = alNotes;
        initComponents();
        setUpEvtListeners(category);
        
    }
    
    private void initComponents(){
        listModel = new DefaultListModel<>();
        updateNoteList();
        
        noteList = new JList<>(listModel);
        
        searchBtn = new JButton("Search Notes");
        addNoteButton = new JButton("Add New Note");
        markCompletedButton = new JButton("Mark as Completed");
        stickNoteButton = new JButton("Stick");
        deleteNoteButton = new JButton("Delete");
        backButton = new JButton("Back");
        showOnlyCompleted = new JButton("Show Completed Notes");
        showOnlyStickies = new JButton("Show Sticky Notes");
        showAll = new JButton("Show All Notes");
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());
        
        JPanel topButtonPanel = new JPanel(new FlowLayout());
        topButtonPanel.add(searchBtn);
        topButtonPanel.add(showOnlyStickies);
        topButtonPanel.add(showOnlyCompleted);
        topButtonPanel.add(showAll);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addNoteButton);
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(stickNoteButton);
        buttonPanel.add(deleteNoteButton);
        buttonPanel.add(backButton);
        
        
        JScrollPane noteScrollPane = new JScrollPane(noteList);

        add(topButtonPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(noteScrollPane, BorderLayout.CENTER);
    }
    
    public String formattedNotesStr(Notes note){
        
        String title = note.getTitle();
        String content = note.getContent();
        String cate_name = note.getCategory();
        String created_date = note.getDate();
        boolean is_completed = note.isCompleted();
        boolean note_sticked = note.isSticked();
        String formattedStr = "<p style='font-size: 14px;'>"+title+"</p>"
                    + "<p style='font-size: 10px;'>"+content+"</p>";
        
        String contentSpace = "";
        
        String space = "&nbsp;";
        for (int i = 0; i < 128-created_date.length(); i++) {
            contentSpace += space;
        }
        
        if (cateObj == null) {
            formattedStr = formattedStr + "<div style='font-size: 9px;'><i> Category: "+cate_name+"</i></div>";
        }
        
        if (note_sticked) {
            formattedStr = formattedStr + "<i style= 'font-size: 8px;'>"+created_date
                    +contentSpace +"<span style=' width: 200px; font-size:9px; text-align:right;'>sticked</span></i>";
        }
        else{
            formattedStr = formattedStr + "<i style= 'font-size: 8px;'>"+created_date+"</i></p>";
        }
        if (is_completed){
            formattedStr = "<div style = 'text-decoration: wavy line-through green 3px;'>"
                    + formattedStr + "</div>";
        }
        
        else{
            formattedStr = "<div style = 'text-decoration: none;' >"
                    + formattedStr + "</p></div>";
        }
        
        
        
        return "<html>"+ formattedStr +"<hr style='width: 7000px;'></html>";
    }
    
    public void updateNoteDb(Notes note){
        cdb.updateNoteTable(note);
    }
    
    private void setUpEvtListeners(Categories category){
        
        
    searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterNotesForm fnf = new FilterNotesForm(category);
                fnf.setVisible(true);
                dispose();
            }
        });
        
        
    showOnlyStickies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notes = cdb.filterNotes(category, "sticked", "true");
                updateNoteList();
            }
        });
        showOnlyCompleted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notes = cdb.filterNotes(category, "completed", "true");
                updateNoteList();
            }
        });    
        
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notes = cdb.getNotesInCategory(category.getCateName());
                updateNoteList();
            }
        });   
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryUI cui = new CategoryUI(cdb.getAllCategories());
                cui.setVisible(true);
                dispose();
            }
        });
        
        

        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNoteForm addNewNote = new AddNoteForm(category,"SUI");
                addNewNote.setSize(674, 456);
                addNewNote.setVisible(true);
                dispose();
            }
        });

        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected note
                
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Notes selectedNote = notes.get(selectedIndex);
                    
                    boolean current_completed = selectedNote.isCompleted();
                    System.out.println(current_completed);
                    selectedNote.setCompleted(!current_completed);
//                    System.out.println(selectedNote.isCompleted());
                    updateNoteDb(selectedNote);
                    updateNoteList();
                }
            }
        });

        stickNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected note
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Notes selectedNote = notes.get(selectedIndex);
                    boolean current_sticked = selectedNote.isSticked();
                    selectedNote.setSticked(!current_sticked);
                    updateNoteDb(selectedNote);
                    updateNoteList();
                }
            }
        });

        deleteNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected note
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove the selected note from the list and update the display
                    Notes selectedNote = notes.get(selectedIndex);
                    cdb.deleteRowFromNoteTable(selectedNote);
                    notes.remove(selectedIndex);
                    updateNoteList();
                }
            }
        });
    }
    
//    public static void main(String[] args) {
//        StickyNotesUI sui = new StickyNotesUI(new ConnectDbMng().getAllCategories().get(0));
//        sui.setVisible(true);
//    }

    public void updateNoteList() {
        listModel.clear();
        for (Notes note : notes) {
            listModel.addElement(formattedNotesStr(note));
        }
    }
    
    public static void main(String[] args) {
       
        new StickyNotesUI(new ConnectDbMng().getAllCategories().get(0)).setVisible(true);
    }
    

}
