package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Mudur extends JFrame implements ActionListener {
    private JPanel panel1, panel2, panel3,aktifPanel;
    private JButton button1, button2, button3;
    private String isim;
    private JTextField ogrenci_ad_soyad_textbox;
    private JTextField vrli_ad_soyad_textboc;
    private JTextField ogrenci_telefon;
    private JTextField veli_telefon;
    private JTextField ogretmen_ad_soyad;
    private JTextField ogretmen_telefon;
    
    
    public static Connection Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ogretmenler";

            String kullaniciAdi = "root";
            String sifre = "1187";
            Connection baglanti = DriverManager.getConnection(url, kullaniciAdi, sifre);
            return baglanti;
        } catch (Exception e) {
            System.out.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
            return null;
        }
    }
    

    

    public Mudur(String ad) {
    	 setBounds(100, 100, 918, 514);
    	 aktifPanel = panel1; 
    	isim=ad;
        setTitle("Menu Example");
        setSize(705, 451);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        // Menü oluşturma
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(119, 136, 153));
        JMenu menu = new JMenu("Menü");
        menu.setBackground(new Color(47, 79, 79));
        menu.setForeground(new Color(240, 255, 240));
        JMenuItem menuItem1 = new JMenuItem("Öğrenci - Öğretmen Ekle");
        JMenuItem menuItem2 = new JMenuItem("Sınıf Bilgileri");
        JMenuItem menuItem3 = new JMenuItem("Ders Programı");
        
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        // Panelleri oluşturma
        panel1 = new JPanel();
        panel1.setBackground(new Color(189, 183, 107));
        getContentPane().add(panel1, BorderLayout.CENTER);
        panel1.setLayout(null);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(39, 102, 578, 256);
        panel1.add(tabbedPane);
        
        JPanel ogrenci_ekle_panel = new JPanel();
        ogrenci_ekle_panel.setBackground(new Color(176, 224, 230));
        tabbedPane.addTab("Öğrenci Ekle", null, ogrenci_ekle_panel, null);
        ogrenci_ekle_panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Öğrenci Ad-Soyadı:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel.setBounds(45, 28, 128, 13);
        ogrenci_ekle_panel.add(lblNewLabel);
        
        ogrenci_ad_soyad_textbox = new JTextField();
        ogrenci_ad_soyad_textbox.setBounds(271, 26, 96, 19);
        ogrenci_ekle_panel.add(ogrenci_ad_soyad_textbox);
        ogrenci_ad_soyad_textbox.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Öğrenci Sınıfı");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1.setBounds(45, 54, 128, 19);
        ogrenci_ekle_panel.add(lblNewLabel_1);
        
    

     
        JComboBox<String> ogrenci_sinif_combo = new JComboBox<>();
        ogrenci_sinif_combo.setBounds(271, 55, 96, 21);
        ogrenci_ekle_panel.add(ogrenci_sinif_combo);

     
        ogrenci_sinif_combo.addItem("Sınıf Seç");
        ogrenci_sinif_combo.addItem("9.Sınıf");
        ogrenci_sinif_combo.addItem("10.Sınıf");
        ogrenci_sinif_combo.addItem("11.Sınıf");
        ogrenci_sinif_combo.addItem("12.Sınıf");

       
        
        JLabel lblNewLabel_2 = new JLabel("Veli Ad-Soyad:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_2.setBounds(47, 127, 126, 13);
        ogrenci_ekle_panel.add(lblNewLabel_2);
        
        vrli_ad_soyad_textboc = new JTextField();
        vrli_ad_soyad_textboc.setColumns(10);
        vrli_ad_soyad_textboc.setBounds(271, 125, 96, 19);
        ogrenci_ekle_panel.add(vrli_ad_soyad_textboc);
        
        JLabel lblNewLabel_3 = new JLabel("Öğrenci Telefon:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_3.setBounds(45, 83, 128, 13);
        ogrenci_ekle_panel.add(lblNewLabel_3);
        
        ogrenci_telefon = new JTextField();
        ogrenci_telefon.setColumns(10);
        ogrenci_telefon.setBounds(271, 81, 96, 19);
        ogrenci_ekle_panel.add(ogrenci_telefon);
        
        JLabel lblNewLabel_4 = new JLabel("Veli Telefon:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_4.setBounds(45, 150, 128, 19);
        ogrenci_ekle_panel.add(lblNewLabel_4);
        
        veli_telefon = new JTextField();
        veli_telefon.setColumns(10);
        veli_telefon.setBounds(271, 154, 96, 19);
        ogrenci_ekle_panel.add(veli_telefon);
        
        JButton ogrenci_ekle_btn = new JButton("KAYIT ET");
        ogrenci_ekle_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
            	String ogrenciAdi = ogrenci_ad_soyad_textbox.getText();
                String ogrenciSinif=(String) ogrenci_sinif_combo.getSelectedItem();
                String ogrenciTelefon = ogrenci_telefon.getText();
                String ogrenciVeliAdi=vrli_ad_soyad_textboc.getText();
                String ogrenciVeliTelefon=veli_telefon.getText();

                
                
                try {
                    Connection baglanti = DriverManager.getConnection("jdbc:mysql://localhost:3306/ogrenciler", "root", "1187");

                    // Öğrenci tablosuna veri ekleme sorgusu
                    String ekleQuery = "INSERT INTO ogrencibilgileri (`Öğrenci Ad-Soyad`, `Öğrenci Sınıf`, `Öğrenci Telefon`, `Veli Ad-Soyad`, `Veli Telefon`) VALUES (?, ?, ?, ?, ?)";

                    // Veritabanına bağlı bir PreparedStatement oluşturuyoruz
                    PreparedStatement preparedStatement = baglanti.prepareStatement(ekleQuery);

                    // Parametreleri ayarlıyoruz
                    preparedStatement.setString(1, ogrenciAdi);
                    preparedStatement.setString(2, ogrenciSinif);
                    preparedStatement.setString(3, ogrenciTelefon);
                    preparedStatement.setString(4, ogrenciVeliAdi);
                    preparedStatement.setString(5, ogrenciVeliTelefon);

                    // Sorguyu çalıştırıyoruz
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        // Eğer ekleme başarılıysa, başarılı ekleme mesajı gösterin
                        JOptionPane.showMessageDialog(null, "Öğrenci başarıyla eklendi!");

                        // Şifre oluşturma ve hesap eklemeyi buraya ekleyebilirsiniz...
                      
                        

                        // Öğrenci hesabını eklemek için bir sorgu oluşturun
                        String accountsEkleQuery = "INSERT INTO accounts (username, password) VALUES (?, ?)";
                        PreparedStatement accountsStatement = baglanti.prepareStatement(accountsEkleQuery);
                        accountsStatement.setString(1, ogrenciAdi);
                        accountsStatement.setString(2, ogrenciAdi);
                        accountsStatement.executeUpdate();
                    }

                    // Bağlantıyı kapat
                    baglanti.close();
                } catch (SQLIntegrityConstraintViolationException ex) {
                    // Benzersizlik kısıtlaması istisnası
                    JOptionPane.showMessageDialog(null, "Bu öğrenci zaten mevcut!");
                } catch (SQLException ex) {
                    // Diğer SQL istisnaları
                    JOptionPane.showMessageDialog(null, "Öğrenci eklenirken bir hata oluştu: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        		
  
        		
        	
        });
        ogrenci_ekle_btn.setForeground(new Color(240, 255, 240));
        ogrenci_ekle_btn.setBackground(new Color(70, 130, 180));
        ogrenci_ekle_btn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        ogrenci_ekle_btn.setBounds(220, 198, 113, 21);
        ogrenci_ekle_panel.add(ogrenci_ekle_btn);
        
        JPanel ogretmen_ekle_panel = new JPanel();
        ogretmen_ekle_panel.setBackground(new Color(176, 224, 230));
        tabbedPane.addTab("Öğretmen Ekle", null, ogretmen_ekle_panel, null);
        ogretmen_ekle_panel.setLayout(null);
        
        JLabel lblretmenAdsoyad = new JLabel("Öğretmen Ad-Soyadı:");
        lblretmenAdsoyad.setBounds(44, 33, 152, 18);
        lblretmenAdsoyad.setFont(new Font("Times New Roman", Font.BOLD, 15));
        ogretmen_ekle_panel.add(lblretmenAdsoyad);
        
        ogretmen_ad_soyad = new JTextField();
        ogretmen_ad_soyad.setColumns(10);
        ogretmen_ad_soyad.setBounds(280, 34, 96, 19);
        ogretmen_ekle_panel.add(ogretmen_ad_soyad);
        
        JLabel lblretmenTelefon = new JLabel("Öğretmen Telefon:");
        lblretmenTelefon.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblretmenTelefon.setBounds(44, 74, 152, 18);
        ogretmen_ekle_panel.add(lblretmenTelefon);
        
        ogretmen_telefon = new JTextField();
        ogretmen_telefon.setColumns(10);
        ogretmen_telefon.setBounds(280, 75, 96, 19);
        ogretmen_ekle_panel.add(ogretmen_telefon);
        
        JLabel lblretmenBran = new JLabel("Öğretmen Branş:");
        lblretmenBran.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblretmenBran.setBounds(44, 114, 152, 18);
        ogretmen_ekle_panel.add(lblretmenBran);
        
        JComboBox<String> ogretmen_brans = new JComboBox<>();
        ogretmen_brans.setBounds(280, 114, 96, 21);
        ogretmen_ekle_panel.add(ogretmen_brans);

     
        ogretmen_brans.addItem("Branş Seç");
        ogretmen_brans.addItem("Türkçe/Edebiyat");
        ogretmen_brans.addItem("Matematik");
        ogretmen_brans.addItem("Geometri");
        ogretmen_brans.addItem("Fizik");
        ogretmen_brans.addItem("Kimya");
        ogretmen_brans.addItem("Biyoloji");
        ogretmen_brans.addItem("Tarih");
        ogretmen_brans.addItem("Coğrafya");
       
        
        JButton ogretmen_kayıt_et = new JButton("KAYIT ET");
        ogretmen_kayıt_et.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String ogretmenAdi = ogretmen_ad_soyad.getText();

                String ogretmenTelefon = ogretmen_telefon.getText();
                String ogretmenBrans = (String) ogretmen_brans.getSelectedItem();
                
                try {
                    Connection baglanti = DriverManager.getConnection("jdbc:mysql://localhost:3306/ogretmenler", "root", "1187");

                 // Öğretmen tablosunu sorgulama sorgusu
                    String sorgu = "SELECT * FROM ogretmenbilgileri WHERE `Öğretmen İsim-Soyisim`=?";
                    PreparedStatement sorguStatement = baglanti.prepareStatement(sorgu);
                    sorguStatement.setString(1, ogretmenAdi);
                    ResultSet sonuc = sorguStatement.executeQuery();

                    
                    if (sonuc.next()) {
                        JOptionPane.showMessageDialog(null, "Bu öğretmen zaten mevcut!");
                        sonuc.close(); // Sonucun kapatılması
                        baglanti.close(); // Bağlantının kapatılması
                        return;
                    } else {
                        sonuc.close(); // Sonucun kapatılması
                    }

                    // Öğretmen tablosuna veri ekleme sorgusu
                    String ekleQuery = "INSERT INTO ogretmenbilgileri (`Öğretmen İsim-Soyisim`, `Öğretmen Branş`, `Öğretmen Telefon`) VALUES (?, ?, ?)";

                    // Veritabanına bağlı bir PreparedStatement oluşturuyoruz
                    PreparedStatement preparedStatement = baglanti.prepareStatement(ekleQuery);

                    // Parametreleri ayarlıyoruz
                    preparedStatement.setString(1, ogretmenAdi);
                    preparedStatement.setString(2, ogretmenBrans);
                    preparedStatement.setString(3, ogretmenTelefon);

                    // Sorguyu çalıştırıyoruz
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                    	 String bosluksuzAd = ogretmenAdi.replaceAll("\\s", "");
                    	 String dort=bosluksuzAd.substring(0,4);
                    	 String son=bosluksuzAd.substring(bosluksuzAd.length()-2);
                    	 String password=dort+son;
                        
                    	
                        // Başarılı ekleme mesajı
                        JOptionPane.showMessageDialog(null, "Öğretmen başarıyla eklendi!");
                        String accountsEkleQuery = "INSERT INTO accounts (username, password) VALUES (?, ?)";
                        PreparedStatement accountsStatement = baglanti.prepareStatement(accountsEkleQuery);
                        accountsStatement.setString(1, ogretmenAdi); 
                        accountsStatement.setString(2, password); 
                        accountsStatement.executeUpdate();
                    }

                    // Bağlantıyı kapat
                    baglanti.close();
                    
                } catch (SQLIntegrityConstraintViolationException ex) {
                    // Benzersizlik kısıtlaması istisnası
                    JOptionPane.showMessageDialog(null, "Bu öğretmen zaten mevcut!");
                } catch (SQLException ex) {
                    // Diğer SQL istisnaları
                    JOptionPane.showMessageDialog(null, "Öğretmen eklenirken bir hata oluştu: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        ogretmen_kayıt_et.setForeground(new Color(240, 255, 240));
        ogretmen_kayıt_et.setFont(new Font("Times New Roman", Font.BOLD, 15));
        ogretmen_kayıt_et.setBackground(new Color(70, 130, 180));
        ogretmen_kayıt_et.setBounds(221, 198, 113, 21);
        ogretmen_ekle_panel.add(ogretmen_kayıt_et);
        
        JLabel lblNewLabel_5_1 = new JLabel("*Şifre girilen Ad-Soyad Bilgisinin ilk dört hanesiyle son 2 hanesinin birleşimidir.");
        lblNewLabel_5_1.setForeground(new Color(220, 20, 60));
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_5_1.setBounds(80, 175, 450, 13);
        ogretmen_ekle_panel.add(lblNewLabel_5_1);
        
        panel2 = new JPanel();
        panel2.setBackground(new Color(255, 255, 255));
        getContentPane().add(panel2, BorderLayout.LINE_END);
        
        panel3 = new JPanel();
        panel3.setBackground(new Color(255, 255, 255));
        getContentPane().add(panel3, BorderLayout.PAGE_END);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Öğrenci - Öğretmen Ekle")) {
            panel1.setVisible(true);
            panel2.setVisible(false);
            panel3.setVisible(false);
            aktifPanel = panel1;
        } else if (e.getActionCommand().equals("Sınıf Bilgileri")) {
        	   setVisible(false);
               
               
        	   setVisible(false);
               
               SınıfBilgileri sinif = new SınıfBilgileri(isim);
               sinif.setVisible(true);
            
        } else if (e.getActionCommand().equals("Ders Programı")) {
            panel1.setVisible(false);
            panel2.setVisible(false);
            panel3.setVisible(true);
            aktifPanel = panel3;
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Mudur("e");
            }
        });
    }
}
