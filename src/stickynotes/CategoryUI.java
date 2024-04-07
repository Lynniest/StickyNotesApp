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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CategoryUI extends JFrame {
    

    private ConnectDbMng cdb = new ConnectDbMng();
    private DateTimeMng dtmng = new DateTimeMng();
    
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 600;
    private final String col_name = "cate_name";
//    private final String table_name = "categorytable"; 


    public JList<String> itemList;
    public DefaultListModel<String> listModel;
    public ArrayList<Categories> items;
    private JButton deleteButton;
    private JButton renameButton;
    private JButton addButton;
    private JButton addNoteButton;
    private JButton searchNoteBtn, backBtn;
    

    public CategoryUI(ArrayList<Categories> items) {
        this.items = items;
        initializeComponents();
        setupListeners();
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        pack();
        centerFrameOnScreen();
    }
    
    public boolean duplicate_exist(Categories cate){
        for (Categories item : items){
            if (item.getCateName().equals(cate.getCateName())) {
                return true;
            }
        }
        return false;
    }
    public boolean duplicate_exist(String cateName){
        for (Categories item : items){
            if (item.getCateName().equals(cateName)) {
                return true;
            }
        }
        return false;
    }    
    
      private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);
    }

    private void initializeComponents() {
        listModel = new DefaultListModel<>();
        for (Categories item : items) {
            listModel.addElement(formatCategoryStr(item.getCateName(), item.getCateDate()));
            
        }
        itemList = new JList<>(listModel);
        
        backBtn = new JButton("Back");
        deleteButton = new JButton("Delete");
        addButton = new JButton("Add New Category");
        renameButton = new JButton("Rename");
        addNoteButton = new JButton("Add New Note");
        searchNoteBtn = new JButton("Search Notes");
        

        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(addButton);
        buttonPanel.add(renameButton);
        buttonPanel.add(deleteButton);
        
        
        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(backBtn);
        topButtonPanel.add(addNoteButton);
        topButtonPanel.add(searchNoteBtn);
        
        

        // Set layout and add components to the frame
        setLayout(new BorderLayout());
        add(topButtonPanel, BorderLayout.NORTH);
        add(new JScrollPane(itemList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public String formatCategoryStr(String cateName,String cateDate){
            String formattedItem = "<html><div style='font-size: 13px;'>" + cateName + "</div>" +
                       "<i style='font-size: 9px;'>" + cateDate + "</i><hr style='width: 10000px'></html>";
    return formattedItem;
    }

    private void setupListeners() {
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = itemList.locationToIndex(e.getPoint());
                    if (selectedIndex != -1) {
                        Categories selectedItem = items.get(selectedIndex);
                        openNextFrame(selectedItem);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                     int confirm = JOptionPane.showConfirmDialog(CategoryUI.this,
                            "Are you sure you want to delete this Category?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Categories selectedItem = items.get(selectedIndex);
                        System.out.println(selectedItem.getCateName());
                        cdb.deleteRowFromCateTable(col_name, selectedItem.getCateName());
                        items.remove(selectedIndex);
                        listModel.remove(selectedIndex);
                    }
                }
            }
        });
        
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNoteForm addNote = new AddNoteForm(null,"CUI");
                addNote.setSize(674, 456);
                addNote.setVisible(true);
                dispose();       
            }
        }); 
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeUI hui = new HomeUI();
                hui.setVisible(true);
                dispose();
            }
        }); 
        
         searchNoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterNotesForm fnf = new FilterNotesForm(null);
                fnf.setSize(674, 456);
                fnf.setVisible(true);
                dispose();       
            }
        });  
        
        

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Categories selectedItem = items.get(selectedIndex);
                    String oldName = selectedItem.getCateName();
                    String newName = JOptionPane.showInputDialog(CategoryUI.this, "Enter new Category Name:");
                    if (newName != null && newName.equals(oldName)==false && newName.length()<32) {
                        if (duplicate_exist(newName)==false) {
                            cdb.updateCateTable(selectedItem, newName);
                            listModel.set(selectedIndex, formatCategoryStr(selectedItem.getCateName(), selectedItem.getCateDate()));
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Category name '"+ newName +"' Already exists.");
                        }

                    }
                }
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather input from the user
                String itemName = JOptionPane.showInputDialog(null, "Enter Category name:");

                // Validate the input
                if (itemName != null && !itemName.isEmpty() && itemName.length()<32) {
                    // Perform the add operation
                    
                    Categories newItem = new Categories(itemName, dtmng.getFormattedCurrentDate());
                    if (duplicate_exist(newItem)==false) {
                        cdb.insertToCateTable(newItem.getCateName(), newItem.getCateDate());
                        items.add(newItem);
                        JOptionPane.showMessageDialog(null, "New Category added successfully!");
                        listModel.addElement(formatCategoryStr(newItem.getCateName(), newItem.getCateDate()));
                    }
                    else{
                       JOptionPane.showMessageDialog(null, "Category name '"+newItem.getCateName()+"' Already exists.");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid category name within 32 Characters.");
                }
            }
});

    }

    private void openNextFrame(Categories selectedItem) {
        StickyNotesUI sui = new StickyNotesUI(selectedItem);
        sui.setTitle("Sticky Notes");
        sui.setVisible(true);
        sui.setDefaultCloseOperation(3);
        dispose();
    }
}


