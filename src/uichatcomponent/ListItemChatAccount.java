
package uichatcomponent;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import datastructure.UserAccount;


public class ListItemChatAccount<E extends Object> extends JList<E>{

    private final DefaultListModel model;
    private Color seletedColor;

    public Color getSeletedColor() {
        return seletedColor;
    }

    public void setSeletedColor(Color seletedColor) {
        this.seletedColor = seletedColor;
    }
    
    
    public ListItemChatAccount() {
        model = new DefaultListModel();
        setModel(model);
    }

    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
               ItemChatAccountUI itemChatAccountUI = new ItemChatAccountUI();
               
               itemChatAccountUI.setItem(value);
               itemChatAccountUI.setSeleted(isSelected);
               if(isSelected){
                   itemChatAccountUI.setBackground(seletedColor);
               }
               return itemChatAccountUI;
            }
            
        };
    }
    
    public void addItem(ItemChatAccount item){
        model.addElement(item);
    }
    

    public void clearList(){
        model.removeAllElements();
    }

    public ArrayList<UserAccount> getAllItem(){
        ArrayList<UserAccount> itemList = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            ItemChatAccount item =(ItemChatAccount) model.getElementAt(i);
            UserAccount account = new UserAccount();
            account.setUsername(item.getName());
            account.setID(item.getID());
            account.setOnline(item.getStatus());
            itemList.add(account);
        }
        return itemList;
    }

    public int getIDSelected(int index){
        ItemChatAccount item =(ItemChatAccount) model.getElementAt(index);
        return item.getID();
    }
    
    
}
