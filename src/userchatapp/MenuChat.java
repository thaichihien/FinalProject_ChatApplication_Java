
package userchatapp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import uichatcomponent.SearchBar;


public class MenuChat extends JPanel{
    
    public SearchBar searchBarFriendList;
    public ListItemChatAccount<String> listFriendJlist;
    public JTabbedPane chatLayout;


    // TODO : Viết hàm lấy dữ liệu từ database ==> nạp vào listFriendJlist
    // ứng với ItemChatAccountUI là một chatbox
    // HIỆN làm
    public void fillFriendList(){

    }


    
    public MenuChat() {
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setLayout(null);
        searchBarFriendList = new SearchBar();
        chatLayout = new JTabbedPane();
        chatLayout.setBounds(410, -30, 880, 880);
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new java.awt.Color(235, 235, 235));
        searchBarFriendList.setPlaceHolder("Tìm kiếm bạn bè...");
        JScrollPane jScrollPaneListFriend = new JScrollPane();
        listFriendJlist = new ListItemChatAccount<>();
        listFriendJlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                listFriendJlistValueChanged(evt);
            }
        });
        jScrollPaneListFriend.setViewportView(listFriendJlist);
        
        
        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(searchBarFriendList, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPaneListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(searchBarFriendList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jScrollPaneListFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        
        
        sidePanel.setBounds(0, 0, 408, 846);
        this.add(sidePanel);
        this.add(chatLayout);
    }
    
    public void addToListFriendJlist(ItemChatAccount item){
        listFriendJlist.addItem(item);
    }
    
    public void listFriendJlistValueChanged(ListSelectionEvent e){
        int index = listFriendJlist.getSelectedIndex();
        chatLayout.setSelectedIndex(index);
        
    }
    
    
    
    
    
}
