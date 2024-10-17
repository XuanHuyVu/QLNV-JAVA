/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GLuong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class GUI_updateNV extends JFrame implements MouseListener,ActionListener{
    private JTextField tfMaNV,tfHoten,tfLuong;
    private JComboBox<String> cbDiachi;
    private JButton btSearch,btUpdate;
    
    public GUI_updateNV() {
        setTitle("DALuong");
        setResizable(true);
        setSize(500, 400);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BuildGUI();
    }
    
    private void BuildGUI() {
        JPanel pn = new JPanel(new GridBagLayout());
        pn.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        //MaNV
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lbMaNV = new JLabel("Ma Nhan Vien: ");
        pn.add(lbMaNV,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfMaNV = new JTextField();
        tfMaNV.setPreferredSize(new Dimension(300,30));
        pn.add(tfMaNV,gbc);
        
        //Hoten
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lbHoten = new JLabel("Ho ten: ");
        pn.add(lbHoten,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        tfHoten = new JTextField();
        tfHoten.setPreferredSize(new Dimension(300,30));
        pn.add(tfHoten,gbc);
        
        //Diachi
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lbDiachi = new JLabel("Dia chi: ");
        pn.add(lbDiachi,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        cbDiachi = new JComboBox<>(new String[] {"Hải Phòng","Hà Nội","Nam Định"});
        cbDiachi.setPreferredSize(new Dimension(300,30));
        pn.add(cbDiachi,gbc);
        
        //Luong
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lbLuong = new JLabel("Luong: ");
        pn.add(lbLuong,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        tfLuong = new JTextField();
        tfLuong.setPreferredSize(new Dimension(300,30));
        pn.add(tfLuong,gbc);
        
        //btSearch,btUpdate
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth =2;
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        btSearch = new JButton("Tìm kiếm nhân viên");
        btUpdate = new JButton("Cập nhật nhân viên");
        btSearch.addActionListener(this);
        btUpdate.addActionListener(this);
        pnBottom.add(btSearch);
        pnBottom.add(btUpdate);
        pn.add(pnBottom,gbc);
        this.add(pn);
    }
    
    public static void main(String[] args) {
        new GUI_updateNV().setVisible(true);
        XLLuong.getCon();
    }
    
    private void searchNV() {
        String searchMaNV = tfMaNV.getText().trim().toLowerCase();
        if(searchMaNV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống Mã NV");
            return;
        }
        
        try {
            XLLuong.getCon();
            ResultSet res = XLLuong.getNVbyMa(searchMaNV);
            if(res.next()) {
                tfHoten.setText(res.getString("Hoten"));
                cbDiachi.setSelectedItem(res.getString("Diachi"));
                tfLuong.setText(res.getString("Luong"));
            }
            else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có mã "+searchMaNV);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm: "+ e.getMessage());
        }
    }
    
    
    private void updateNV() {
        String searchMaNV = tfMaNV.getText().trim().toLowerCase();
        String Hoten = tfHoten.getText().trim();
        String Diachi = cbDiachi.getSelectedItem().toString();
        String Luong = tfLuong.getText().trim();
        
        boolean isUpdate = XLLuong.updateNV(searchMaNV, new Nhanvien(searchMaNV, Hoten, Diachi, Luong));
        if(isUpdate) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSearch) {
            searchNV();
        } else if(e.getSource()==btUpdate) {
            updateNV();
        }
    }
    
}
