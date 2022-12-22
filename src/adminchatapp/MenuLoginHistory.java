/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminchatapp;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.LoginHistory;

/**
 *
 * @author HIEN
 */
public class MenuLoginHistory extends  MenuAdminLayout{

    JTable tableLoginHistory;
    JComboBox<String> sortFilter;


    //TODO 1: nạp dữ liệu từ database vào tableLoginHistory, dựa vào sortFilter
    // để lọc 
    public void filltableLoginHistory(){
        tableLoginHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Thời gian đăng nhập", "Tên đăng nhập", "Họ và tên"
            }
        ));

        DatabaseManagment database = DatabaseManagment.getInstance();

        ArrayList<LoginHistory> allLogin = database.getAllLoginHistory();
        // tableFindFriend is JTable
        DefaultTableModel tableModel = (DefaultTableModel) tableLoginHistory.getModel();
        for(LoginHistory login : allLogin){
            String username = String.valueOf(login.getID());
            String fullname = String.valueOf(login.getUserID());
            String email = String.valueOf(login.getLoginTime());

            String row[] = {username,fullname,email};
            tableModel.addRow(row);
        }
    }

    public MenuLoginHistory(JFrame parentFrame) {
        super(parentFrame);
        tableLoginHistory = new JTable();
        sortFilter = new JComboBox<>();
        
        JLabel jLabel_lichsudangnhap = new JLabel();
        JLabel jLabel_sapxeptheo = new JLabel();

        tableLoginHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Thời gian đăng nhập", "Tên đăng nhập", "Họ và tên"
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
        filltableLoginHistory();
    }
}
