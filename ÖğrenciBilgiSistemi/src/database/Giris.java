package database;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Giris extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField mudur_sifre_textbox;
    private JTextField ogretmenkullanici;
    private JTextField ogretmensifre;
    private JTextField öğrenci_ad_textbox;
    private JTextField ogrencisifre;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Giris frame = new Giris();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Connection Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url0 = "jdbc:mysql://localhost:3306/kurumlar";
            String url1 = "jdbc:mysql://localhost:3306/mudurler";
            String url2 = "jdbc:mysql://localhost:3306/ogretmenler";
            String url3 = "jdbc:mysql://localhost:3306/ogrenciler";

            String kullaniciAdi = "root";
            String sifre = "1187";
            try (Connection baglanti0 = DriverManager.getConnection(url0, kullaniciAdi, sifre)) {
			}
            Connection baglanti1 = DriverManager.getConnection(url1, kullaniciAdi, sifre);
           
            try (Connection baglanti2 = DriverManager.getConnection(url2, kullaniciAdi, sifre)) {
			}

            try (Connection baglanti3 = DriverManager.getConnection(url3, kullaniciAdi, sifre)) {
			}

            return baglanti1;
        } catch (Exception e) {
            System.out.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
            return null;
        }
    }

    public Giris() {
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 918, 514);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 118, 707, 349);
        contentPane.add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Müdür Giriş", null, panel, null);
        panel.setLayout(null);

        JComboBox mudur_kurumsecme = new JComboBox();
        mudur_kurumsecme.setBounds(243, 41, 87, 21);
        panel.add(mudur_kurumsecme);

        JLabel lblNewLabel = new JLabel("Kurum Seçiniz");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(69, 34, 118, 32);
        panel.add(lblNewLabel);

        JPanel mudur_giris_formu = new JPanel();
        mudur_giris_formu.setBounds(69, 95, 523, 199);
        panel.add(mudur_giris_formu);
        mudur_giris_formu.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Kullanıcı Kimliğinizi Giriniz");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
        lblNewLabel_1.setBounds(48, 10, 181, 13);
        mudur_giris_formu.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(48, 33, 96, 19);
        mudur_giris_formu.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Kullanıcı Şifrenizi Giriniz");
        lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
        lblNewLabel_2.setBounds(48, 62, 181, 13);
        mudur_giris_formu.add(lblNewLabel_2);

        mudur_sifre_textbox = new JTextField();
        mudur_sifre_textbox.setBounds(48, 85, 96, 19);
        mudur_giris_formu.add(mudur_sifre_textbox);
        mudur_sifre_textbox.setColumns(10);

        JButton mudur_giris_buton = new JButton("Giriş Yap");
        mudur_giris_buton.setForeground(Color.WHITE);
        mudur_giris_buton.setBackground(new Color(0, 128, 192));
        mudur_giris_buton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String mudurAdi = textField.getText(); // Veya mudur_kurumsecme'den alınan değer
        		String mudurSifre=new String (mudur_sifre_textbox.getText());
        		
        		 try {
                     Connection baglanti = Connection();
                     String sorgu = "SELECT COUNT(*) AS giris FROM mudurler WHERE username = ? AND password = ?";
                     PreparedStatement statement = baglanti.prepareStatement(sorgu);
                     statement.setString(1, mudurAdi);
                     statement.setString(2, mudurSifre);
                     ResultSet sonuc = statement.executeQuery();
                     if (sonuc.next()) {
                         int girisSayisi = sonuc.getInt("giris");
                         if (girisSayisi  > 0) {
                             JOptionPane.showMessageDialog(null, "Giriş başarılı!");
                             setVisible(false);
                             String databaseName = mudurAdi + "		_VERİTABANI";
                             String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
                             Statement createDatabaseStatement = baglanti.createStatement();
                             createDatabaseStatement.executeUpdate(createDatabaseQuery);

                             String createTableQuery = "CREATE TABLE IF NOT EXISTS " + databaseName + ".bilgiler (" +
                                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                                     "Ogretmenler VARCHAR(50) NOT NULL," +
                                     "Branslar VARCHAR(50) NOT NULL," +
                                     "Maas VARCHAR(50) NOT NULL)";
                                                      
                             
                             
                             Statement createTableStatement = baglanti.createStatement();
                             createTableStatement.executeUpdate(createTableQuery);
                            
	                           
                             
                             Mudur mudur = new Mudur(mudurAdi);
                             mudur.setVisible(true);
                             
                             
                             
                         } else {
                             JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre yanlış!");
                         }
                     }
                     statement.close();
                    

                 } catch (Exception ex) {
                     System.out.println("Veritabanı hatası: " + ex.getMessage());
                 }
        	
        		
        		
        		
        		
        		
        	}
        });
        mudur_giris_buton.setBounds(232, 139, 89, 33);
        mudur_giris_formu.add(mudur_giris_buton);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Öğretmen Giriş", null, panel_1, null);
        panel_1.setLayout(null);

        JPanel mudur_giris_formu_1 = new JPanel();
			mudur_giris_formu_1.setLayout(null);
			mudur_giris_formu_1.setBounds(68, 96, 523, 199);
			panel_1.add(mudur_giris_formu_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Kullanıcı Kimliğinizi Giriniz");
			lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel_1_1.setBounds(48, 10, 181, 13);
			mudur_giris_formu_1.add(lblNewLabel_1_1);
			
			ogretmenkullanici = new JTextField();
			ogretmenkullanici.setColumns(10);
			ogretmenkullanici.setBounds(48, 33, 96, 19);
			mudur_giris_formu_1.add(ogretmenkullanici);
			
			JLabel lblNewLabel_2_1 = new JLabel("Kullanıcı Şifrenizi Giriniz");
			lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel_2_1.setBounds(48, 62, 181, 13);
			mudur_giris_formu_1.add(lblNewLabel_2_1);
			
			ogretmensifre = new JTextField();
			ogretmensifre.setColumns(10);
			ogretmensifre.setBounds(48, 85, 96, 19);
			mudur_giris_formu_1.add(ogretmensifre);
			
			JButton ogretmen_giris_btn = new JButton("Giriş Yap");
			ogretmen_giris_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 String kullaniciAdi = "root";
			            String sifre = "1187";
					
					String ogretmenadi= ogretmenkullanici.getText();
	        		String ogretmensifree=new String (ogretmensifre.getText());
	        		
	        		 try {
	        			 Connection baglanti2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ogretmenler", kullaniciAdi, sifre);


	                     String sorgu = "SELECT COUNT(*) AS giris FROM accounts WHERE username = ? AND password = ?";
	                     PreparedStatement statement = baglanti2.prepareStatement(sorgu);
	                     statement.setString(1, ogretmenadi);
	                     statement.setString(2, ogretmensifree);
	                     ResultSet sonuc = statement.executeQuery();
	                     if (sonuc.next()) {
	                         int girisSayisi = sonuc.getInt("giris");
	                         if (girisSayisi  > 0) {
	                             JOptionPane.showMessageDialog(null, "Giriş başarılı!");
	                             setVisible(false);

	                           
	                             
	                             Ogretmen ogretmen = new Ogretmen(ogretmenadi);
	                             ogretmen.setVisible(true);
	                             
	                             
	                             
	                         } else {
	                             JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre yanlış!");
	                         }
	                     }
	                     statement.close();

	                 } catch (Exception ex) {
	                     System.out.println("Veritabanı hatası: " + ex.getMessage());
	                 }
					
					
				}
			});
			ogretmen_giris_btn.setBounds(232, 139, 85, 21);
			mudur_giris_formu_1.add(ogretmen_giris_btn);
			
			JLabel lblNewLabel_3 = new JLabel("Kurum Seçiniz");
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_3.setBounds(68, 36, 118, 32);
			panel_1.add(lblNewLabel_3);
			
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setBounds(255, 43, 87, 21);
			panel_1.add(comboBox_1);
			
			JPanel panel_2 = new JPanel();
			tabbedPane.addTab("Öğrenci Giriş", null, panel_2, null);
			panel_2.setLayout(null);
			
			JPanel mudur_giris_formu_1_1 = new JPanel();
			mudur_giris_formu_1_1.setLayout(null);
			mudur_giris_formu_1_1.setBounds(66, 97, 523, 199);
			panel_2.add(mudur_giris_formu_1_1);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Öğrenci Adınızı Giriniz");
			lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel_1_1_1.setBounds(48, 10, 181, 13);
			mudur_giris_formu_1_1.add(lblNewLabel_1_1_1);
			
			öğrenci_ad_textbox = new JTextField();
			öğrenci_ad_textbox.setColumns(10);
			öğrenci_ad_textbox.setBounds(48, 33, 96, 19);
			mudur_giris_formu_1_1.add(öğrenci_ad_textbox);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("Öğrenci Şifrenizi Giriniz");
			lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel_2_1_1.setBounds(48, 62, 181, 13);
			mudur_giris_formu_1_1.add(lblNewLabel_2_1_1);
			
			ogrencisifre = new JTextField();
			ogrencisifre.setColumns(10);
			ogrencisifre.setBounds(48, 85, 96, 19);
			mudur_giris_formu_1_1.add(ogrencisifre);
			
			JButton ogrencigirisbtn = new JButton("Giriş Yap");
			ogrencigirisbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ogrenciadi = textField.getText(); // Veya mudur_kurumsecme'den alınan değer
	        		String ogrencisifre=new String (mudur_sifre_textbox.getText());
	        		
	        		 try {
	        			 String kullaniciAdi = "root";
				            String sifre = "1187";
	        			 Connection baglanti3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ogrenciler", kullaniciAdi, sifre);
	                     String sorgu = "SELECT COUNT(*) AS giris FROM accounts WHERE username = ? AND password = ?";
	                     PreparedStatement statement = baglanti3.prepareStatement(sorgu);
	                     statement.setString(1, ogrenciadi);
	                     statement.setString(2, ogrencisifre);
	                     ResultSet sonuc = statement.executeQuery();
	                     if (sonuc.next()) {
	                         int girisSayisi = sonuc.getInt("giris");
	                         if (girisSayisi  > 0) {
	                             JOptionPane.showMessageDialog(null, "Giriş başarılı!");
	                             setVisible(false);
	                           
	                             
	                             Ogrenci ogr = new Ogrenci(ogrenciadi);
	                             ogr.setVisible(true);
	                             
	                             
	                             
	                             
	                         } else {
	                             JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre yanlış!");
	                         }
	                     }
	                     statement.close();

	                 } catch (Exception ex) {
	                     System.out.println("Veritabanı hatası: " + ex.getMessage());
	                 }
				}
			});
			ogrencigirisbtn.setBounds(232, 139, 85, 21);
			mudur_giris_formu_1_1.add(ogrencigirisbtn);
		
		}

}
