/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminchatapp;


import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.LoginHistory;
import utils.Utils;

/**
 *
 * @author HIEN
 */
public class MenuLoginHistory extends  MenuAdminLayout{

    JTable tableLoginHistory;
    JComboBox<String> sortFilter;


    
      

    public void filltableLoginHistory(){
        Utils.clearTable(tableLoginHistory);
        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<LoginHistory> allLogin = database.getAllLoginHistory();
        // tableFindFriend is JTable
        DefaultTableModel tableModel = (DefaultTableModel) tableLoginHistory.getModel();
        for(LoginHistory login : allLogin){
            String loginTime = login.getLoginTime();
            String userID = String.valueOf(login.getUserID());
            String username = login.getUserName();

            String row[] = {loginTime,userID,username};
            tableModel.addRow(row);
        }
    }

    public void filltableLoginHistory(String sort,String by){
        Utils.clearTable(tableLoginHistory);
        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<LoginHistory> allLogin = database.getAllLoginHistory(sort, by);
        // tableFindFriend is JTable
        DefaultTableModel tableModel = (DefaultTableModel) tableLoginHistory.getModel();
        for(LoginHistory login : allLogin){
            String loginTime = login.getLoginTime();
            String userID = String.valueOf(login.getUserID());
            String username = login.getUserName();

            String row[] = {loginTime,userID,username};
            tableModel.addRow(row);
        }
    }

    public void handleSortCb() {

        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) sortFilter.getSelectedItem();//get the selected item

                switch (s) {//check for a match
                    case "Mặc định":
                        filltableLoginHistory();
                        break;
                    case "Ngày đăng nhập mới":
                        filltableLoginHistory("LOGIN_TIME", "ASC");
                        break;
                    case "Ngày đăng nhập cũ":
                        filltableLoginHistory("LOGIN_TIME",  "DESC");
                        break;
                    default:
                        System.out.println("No match selected!");
                        break;
                }
            }
        };

        sortFilter.addActionListener(cbActionListener);
    }

    // Phần Nhật làm end



    public MenuLoginHistory(JFrame parentFrame) {
        super(parentFrame);
        initComponents();


        // sortFilter.addItemListener(new ItemListener(){
        //     @Override
        //     public void itemStateChanged(ItemEvent e) {
        //        filltableLoginHistory();
                
        //     }
            
        // });


        filltableLoginHistory();
        handleSortCb();
    }

    private void initComponents(){
        tableLoginHistory = new JTable();
        sortFilter = new JComboBox<>();
        
        JLabel jLabel_lichsudangnhap = new JLabel();
        JLabel jLabel_sapxeptheo = new JLabel();

        tableLoginHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
             
            },
            new String [] {
                "Thời gian đăng nhập","User ID", "Tên đăng nhập"
            }
        ));
        JScrollPane jScrollPane_tableLoginHisory = new JScrollPane();
        jScrollPane_tableLoginHisory.setViewportView(tableLoginHistory);

        this.add(jScrollPane_tableLoginHisory);
        jScrollPane_tableLoginHisory.setBounds(40, 90, 1180, 580);

        jLabel_lichsudangnhap.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel_lichsudangnhap.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_lichsudangnhap.setText("Lịch sử đăng nhập tài khoản");
        this.add(jLabel_lichsudangnhap);
        jLabel_lichsudangnhap.setBounds(60, 20, 370, 50);

        jLabel_sapxeptheo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_sapxeptheo.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_sapxeptheo.setText("Sắp xếp theo :");
        this.add(jLabel_sapxeptheo);
        jLabel_sapxeptheo.setBounds(520, 40, 120, 25);

        sortFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Ngày đăng nhập mới", "Ngày đăng nhập cũ" }));
        this.add(sortFilter);
        sortFilter.setBounds(660, 30, 190, 40);
    }
}
