package database;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SınıfBilgileri extends JFrame {
	
	
	  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost:3306/ogretmenler"; // Veritabanı adınızı buraya yazın
	    static final String USER = "root";
	    static final String PASS = "1187";
	
	
	
    private JPanel contentPane;
    private HashMap<String, HashMap<String, JComboBox<String>>> siniflar;

    public SınıfBilgileri(String name) {
        setTitle("Sınıf Bilgileri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 918, 514);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton BTN_10 = new JButton("10. SINIF");
        BTN_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
        BTN_10.setBounds(299, 158, 85, 55);
        contentPane.add(BTN_10);
        
        JButton BTN_11 = new JButton("11.SINIF");
        BTN_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
        BTN_11.setBounds(465, 158, 85, 55);
        contentPane.add(BTN_11);
        
        JButton BTN_12 = new JButton("12. SINIF");
        BTN_12.setFont(new Font("Times New Roman", Font.BOLD, 13));
        BTN_12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        BTN_12.setBounds(636, 158, 85, 55);
        contentPane.add(BTN_12);
        
        JButton BTN_9 = new JButton("9.SINIF");
        BTN_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 setVisible(false);

                 
                 
                 SINIF9 ogretmen1 = new SINIF9(name);
                 ogretmen1.setVisible(true);
        	}
        });
        BTN_9.setFont(new Font("Times New Roman", Font.BOLD, 13));
        BTN_9.setBounds(157, 158, 85, 55);
        contentPane.add(BTN_9);

        siniflar = new HashMap<>();

   
    }

   
    public static void main(String[] args) {
        SınıfBilgileri frame = new SınıfBilgileri("e");
        frame.setVisible(true);
    }
}
