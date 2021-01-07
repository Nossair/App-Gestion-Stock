package Caisse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class Profil extends JFrame {

	private JPanel contentPane;
	private JTextField codee;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tele;
	private ResultSet rset;
	private Statement stmnt;
	private JTextField nbrcmd;
	private JTextField fonction;
 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profil frame = new Profil(0,true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Profil(int code,boolean isadmin) throws SQLException {
Connection conn=Connexion.connecter();
		isadmin=Methode.getFonction(code);		
			stmnt=conn.createStatement();
			rset=stmnt.executeQuery("select * from emp");
			rset.next();
			
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 488);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		
		JMenu m = new JMenu("MENU");
		menu.add(m);
		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Profil.this.setVisible(false);
				
			}
		});
		JMenuItem stocke=new JMenuItem(new ImageIcon("icons8-trolley-50-ConvertImage.png"));
		stocke.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
				 		Produit ap=new Produit(code,Methode.getFonction(code));
				 		 
				 		ap.setVisible(true);
				 		Profil.this.setVisible(false);
				 	}
				 });
		JMenuItem client=new JMenuItem(new ImageIcon("cllcopier.png"));
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Client prf = null;
				try {
					prf = new Client(code,Methode.getFonction(code));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//prf.affiche();
				prf.setVisible(true);
				Profil.this.setVisible(false);
			}
		});
		JMenuItem emp=new JMenuItem(new ImageIcon("contcopier.png"));
		emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Emp prof = null;
				try {
					prof = new Emp(code ,Methode.getFonction(code));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Profil.this.setVisible(false);
				prof.setVisible(true);
			}
		});
		JMenuItem cmd=new JMenuItem(new ImageIcon("ncmdcopier.png"));
		cmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Commande c=new Commande(code,Methode.getFonction(code));
				Profil.this.setVisible(false);
				c.setVisible(true);
			}
		});
	 m.add(stocke);
	 m.add(client);
	 m.add(cmd);
	 m.add(emp);
	 m.add(item);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Profil");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("commercial_development_management_100px.png"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		codee = new JTextField();
		codee.setEditable(false);
		codee.setColumns(10);
		codee.setText(code+"");
		
		nom = new JTextField();
		nom.setText(Methode.getNom(code));
		nom.setEditable(false);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setEditable(false);
		prenom.setColumns(10);
		prenom.setText(Methode.getPrenom(code));
		
		tele = new JTextField();
		tele.setEditable(false);
		tele.setColumns(10);
		tele.setText(Methode.getTele(code));
		 
		JLabel lblNewLabel_1 = new JLabel("Code de l'Employer :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Nom de l'Employer :");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Prenom de l'Employer :");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Telephone du Employer :");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_5 = new JLabel("Fonction :");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 12));
		
		JFormattedTextField ddn = new JFormattedTextField();
		ddn.setText(Methode.getDdn(code));
		ddn.setEditable(false);
		
		JLabel lblDateDeNaissance = new JLabel("Date de Naissance :");
		lblDateDeNaissance.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblDateDempoche = new JLabel("Date d'embauche :");
		lblDateDempoche.setFont(new Font("Arial", Font.BOLD, 12));
		
		JFormattedTextField dde = new JFormattedTextField();
		dde.setEditable(false);
		dde.setText(Methode.getDde(code));
		JLabel lblNombreDesCommandes = new JLabel("Nombre des Commandes :");
		lblNombreDesCommandes.setFont(new Font("Arial", Font.BOLD, 12));
		
		nbrcmd = new JTextField();
		nbrcmd.setEditable(false);
		nbrcmd.setColumns(10);
		nbrcmd.setText(Methode.getNbrc(code));
		
		JLabel lblAdresse = new JLabel("Adresse d'Employer :");
		lblAdresse.setFont(new Font("Arial", Font.BOLD, 12));
		
		JTextArea add = new JTextArea();
		add.setEditable(false);
		add.setText(Methode.getAdress(code));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		add.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add.setBackground(Color.WHITE);
		
		fonction = new JTextField();
		fonction.setEditable(false);
		fonction.setColumns(10);
		if(isadmin)
		{
			fonction.setText("Administrateur ");
		}
		else
		{
			fonction.setText("Employé");
		}
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(48)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(tele, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(nom, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(codee, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(fonction, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(prenom, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
							.addGap(70)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNombreDesCommandes)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nbrcmd, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblDateDempoche)
											.addGap(26)
											.addComponent(dde, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblDateDeNaissance)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(ddn, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))))))
						.addComponent(add, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(codee)
						.addComponent(lblDateDeNaissance)
						.addComponent(ddn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(nom)
						.addComponent(lblDateDempoche)
						.addComponent(dde))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(lblNewLabel_4))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(prenom)
								.addComponent(nbrcmd)
								.addComponent(lblNombreDesCommandes))
							.addGap(12)
							.addComponent(tele)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(fonction)
						.addComponent(lblNewLabel_5))
					.addGap(19)
					.addComponent(lblAdresse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(add, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
	 
		
		
	}
}
