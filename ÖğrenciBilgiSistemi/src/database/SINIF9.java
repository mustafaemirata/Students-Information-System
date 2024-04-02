
package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SINIF9 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String isim;

	/**
	 * Launch the application.
	 */ public static Connection Connection() {
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
	    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SINIF9 frame = new SINIF9("e");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SINIF9(String name) {
		isim=name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setBounds(100, 100, 918, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 64, 884, 360);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("9A", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Türkkçe");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(271, 69, 67, 13);
		panel_2.add(lblNewLabel);
		
		JLabel lblMatematik = new JLabel("Matematik");
		lblMatematik.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMatematik.setBounds(271, 94, 96, 13);
		panel_2.add(lblMatematik);
		
		JLabel lblFizik = new JLabel("Fizik");
		lblFizik.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFizik.setBounds(271, 119, 67, 13);
		panel_2.add(lblFizik);
		
		JLabel lblBiyoloji = new JLabel("Biyoloji");
		lblBiyoloji.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBiyoloji.setBounds(271, 144, 67, 13);
		panel_2.add(lblBiyoloji);
		
		JLabel lblKimya = new JLabel("Kimya");
		lblKimya.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblKimya.setBounds(271, 169, 67, 13);
		panel_2.add(lblKimya);
		
		JComboBox türkçe9 = new JComboBox();
		türkçe9.setBounds(475, 66, 96, 21);
		panel_2.add(türkçe9);
		
	    try {
            Connection conn = Connection();
            String sql = "SELECT `Öğretmen İsim-Soyisim` FROM ogretmenbilgileri WHERE `ÖğretmenBranş` = 'Türkçe'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isim = resultSet.getString("Öğretmen İsim-Soyisim");
                türkçe9.addItem(isim);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
		
		JComboBox matematik9_a = new JComboBox();
		matematik9_a.setBounds(475, 91, 96, 21);
		panel_2.add(matematik9_a);
		
		JComboBox fizik9_a = new JComboBox();
		fizik9_a.setBounds(475, 116, 96, 21);
		panel_2.add(fizik9_a);
		
		JComboBox biyoloji9_a = new JComboBox();
		biyoloji9_a.setBounds(475, 141, 96, 21);
		panel_2.add(biyoloji9_a);
		
		JComboBox Kimya9_a = new JComboBox();
		Kimya9_a.setBounds(475, 166, 96, 21);
		panel_2.add(Kimya9_a);
		
		JButton Kaydet_btn = new JButton("Kaydet");
		Kaydet_btn.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Kaydet_btn.setBounds(365, 224, 104, 37);
		panel_2.add(Kaydet_btn);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("9B", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(0, 0, 879, 333);
		panel.add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Türkkçe");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(271, 69, 67, 13);
		panel_2_1.add(lblNewLabel_1);
		
		JLabel lblMatematik_1 = new JLabel("Matematik");
		lblMatematik_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMatematik_1.setBounds(271, 94, 96, 13);
		panel_2_1.add(lblMatematik_1);
		
		JLabel lblFizik_1 = new JLabel("Fizik");
		lblFizik_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFizik_1.setBounds(271, 119, 67, 13);
		panel_2_1.add(lblFizik_1);
		
		JLabel lblBiyoloji_1 = new JLabel("Biyoloji");
		lblBiyoloji_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBiyoloji_1.setBounds(271, 144, 67, 13);
		panel_2_1.add(lblBiyoloji_1);
		
		JLabel lblKimya_1 = new JLabel("Kimya");
		lblKimya_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblKimya_1.setBounds(271, 169, 67, 13);
		panel_2_1.add(lblKimya_1);
		
		JComboBox türkçe9b = new JComboBox();
		türkçe9b.setBounds(475, 66, 96, 21);
		panel_2_1.add(türkçe9b);
		
		JComboBox matematik9b = new JComboBox();
		matematik9b.setBounds(475, 91, 96, 21);
		panel_2_1.add(matematik9b);
		
		JComboBox fizik9b = new JComboBox();
		fizik9b.setBounds(475, 116, 96, 21);
		panel_2_1.add(fizik9b);
		
		JComboBox biyoloji9b = new JComboBox();
		biyoloji9b.setBounds(475, 141, 96, 21);
		panel_2_1.add(biyoloji9b);
		
		JComboBox Kimya9b = new JComboBox();
		Kimya9b.setBounds(475, 166, 96, 21);
		panel_2_1.add(Kimya9b);
		
		JButton Kaydet_btnb = new JButton("Kaydet");
		Kaydet_btnb.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Kaydet_btnb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Kaydet_btnb.setBounds(365, 224, 104, 37);
		panel_2_1.add(Kaydet_btnb);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("9C", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBounds(0, 0, 879, 333);
		panel_1.add(panel_2_2);
		
		JLabel lblNewLabel_2 = new JLabel("Türkkçe");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(271, 69, 67, 13);
		panel_2_2.add(lblNewLabel_2);
		
		JLabel lblMatematik_2 = new JLabel("Matematik");
		lblMatematik_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMatematik_2.setBounds(271, 94, 96, 13);
		panel_2_2.add(lblMatematik_2);
		
		JLabel lblFizik_2 = new JLabel("Fizik");
		lblFizik_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFizik_2.setBounds(271, 119, 67, 13);
		panel_2_2.add(lblFizik_2);
		
		JLabel lblBiyoloji_2 = new JLabel("Biyoloji");
		lblBiyoloji_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblBiyoloji_2.setBounds(271, 144, 67, 13);
		panel_2_2.add(lblBiyoloji_2);
		
		JLabel lblKimya_2 = new JLabel("Kimya");
		lblKimya_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblKimya_2.setBounds(271, 169, 67, 13);
		panel_2_2.add(lblKimya_2);
		
		JComboBox türkçe9c = new JComboBox();
		türkçe9c.setBounds(475, 66, 96, 21);
		panel_2_2.add(türkçe9c);
		
		JComboBox matematik9c = new JComboBox();
		matematik9c.setBounds(475, 91, 96, 21);
		panel_2_2.add(matematik9c);
		
		JComboBox fizik9c = new JComboBox();
		fizik9c.setBounds(475, 116, 96, 21);
		panel_2_2.add(fizik9c);
		
		JComboBox biyoloji9c = new JComboBox();
		biyoloji9c.setBounds(475, 141, 96, 21);
		panel_2_2.add(biyoloji9c);
		
		JComboBox Kimya9c = new JComboBox();
		Kimya9c.setBounds(475, 166, 96, 21);
		panel_2_2.add(Kimya9c);
		
		JButton Kaydet9c = new JButton("Kaydet");
		Kaydet9c.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Kaydet9c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Kaydet9c.setBounds(365, 224, 104, 37);
		panel_2_2.add(Kaydet9c);
	}
}