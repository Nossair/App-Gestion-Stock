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
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class Emp extends JFrame {

	private JPanel contentPane;
	private JTextField codee;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tele;
	private ResultSet rset;
	private Statement stmnt;
	private JTextField nbrc;
	private JRadioButton radioAdmin;
	private JRadioButton radiocaissier;
	private JTextArea adresse;
	private JFormattedTextField dem;
	private JFormattedTextField ddn;
	private JButton btnAjouter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emp frame = new Emp(0,true);
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
	public Emp(int code,boolean isadmin) throws SQLException {
Connection conn=Connexion.connecter();
radioAdmin = new JRadioButton("Admin");
radiocaissier = new JRadioButton("Caissier");
adresse = new JTextArea();
dem = new JFormattedTextField();
nbrc = new JTextField();
btnAjouter = new JButton("Ajouter");
btnAjouter.setVisible(false);
btnAjouter.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		
			Connection conn=Connexion.connecter();
			int fonction;
		if(radioAdmin.isSelected()) {
			fonction=1;
		}else {
			fonction=0;
		}
		int codeei=Integer.parseInt(codee.getText());
		String noms=nom.getText();
		String prenoms=prenom.getText();
		int telei=Integer.parseInt(tele.getText());
		String adrs=adresse.getText();
		String ddns=ddn.getText();
		String dems=dem.getText();
		int nbr=Integer.parseInt(nbrc.getText());
		String pass=JOptionPane.showInputDialog("Entrer le Mote de Pass");
		Statement stmnt;
		try {
			stmnt = conn.createStatement();
			stmnt.execute("INSERT INTO `emp`(`code_e`, `nom_e`, `prenom_e`, `pass_word`, `dnn`, `Telephone`, `Adresse`, `dde`,nbrcmd, `isadmin`) VALUES ("+codeei+",'"+noms+"','"+prenoms+"','"+pass+"','"+ddns+"',"+telei+",'"+adrs+"','"+dems+"',0,"+fonction+")");
			rset=stmnt.executeQuery("select * from emp");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	}
});
nbrc.setEditable(false);
ddn = new JFormattedTextField();
ButtonGroup bg=new ButtonGroup();
bg.add(radioAdmin);
bg.add(radiocaissier);
		
			stmnt=conn.createStatement();
			rset=stmnt.executeQuery("select * from emp");
			rset.next();
			
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 488);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		
		JMenu m = new JMenu("MENU");
		m.setBackground(Color.WHITE);
		m.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\m.png"));
		menu.add(m);
		menu.add(m);
		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Emp.this.setVisible(false);
				
			}
		});
		JMenuItem profil=new JMenuItem(new ImageIcon("empcopier.png"));
		profil.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
				 		Profil ap = null;
						try {
							ap = new Profil(code,Methode.getFonction(code));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 		 
				 		ap.setVisible(true);
				 		Emp.this.setVisible(false);
				 	}
				 });
		JMenuItem stocke=new JMenuItem(new ImageIcon("icons8-trolley-50-ConvertImage.png"));
		stocke.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
				 		Produit ap=new Produit(code,Methode.getFonction(code));
				 		 
				 		ap.setVisible(true);
				 		Emp.this.setVisible(false);
				 	}
				 });
	 
		JMenuItem emp=new JMenuItem(new ImageIcon("cllcopier.png"));
		emp.addActionListener(new ActionListener() {
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
				Emp.this.setVisible(false);
			}
		});		JMenuItem cmd=new JMenuItem(new ImageIcon("ncmdcopier.png"));
		cmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Commande c=new Commande(code,Methode.getFonction(code));
				Emp.this.setVisible(false);
				c.setVisible(true);
			}
		});
	 m.add(profil);
	 m.add(stocke);
	 m.add(cmd);
	 m.add(emp);
	 m.add(item);
			if(isadmin) {
				
			}
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Employer");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\commercial_development_management_100px.png"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		codee = new JTextField();
		codee.setEditable(false);
		codee.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		
		tele = new JTextField();
		tele.setColumns(10);
		codee.setText(rset.getString(1));
		nom.setText(rset.getString(2));
		prenom.setText(rset.getString(3));
		tele.setText(""+rset.getInt(6));
		if(rset.getBoolean(10)) {
			radioAdmin.setSelected(true);
		}else {
			 radiocaissier.setSelected(true);
		}
		adresse.setText(rset.getString(7));
		dem.setText(rset.getString(8));
		nbrc.setText(""+rset.getInt(9));
		ddn.setText(rset.getString(5));
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
		
		 
		radioAdmin.setSelected(true);
		radioAdmin.setEnabled(false);
		radioAdmin.setBackground(Color.WHITE);
		
		
		radiocaissier.setEnabled(false);
		radiocaissier.setBackground(Color.WHITE);
		
		
		
		JLabel lblDateDeNaissance = new JLabel("Date de Naissance :");
		lblDateDeNaissance.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblDateDempoche = new JLabel("Date d'embauche :");
		lblDateDempoche.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		
		JLabel lblNombreDesCommandes = new JLabel("Nombre des Commandes :");
		lblNombreDesCommandes.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		nbrc.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse d'Employer :");
		lblAdresse.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		adresse.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		adresse.setBackground(Color.WHITE);
		
		JPanel panelb = new JPanel();
		panelb.setBackground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(adresse, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelb, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(12))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(radioAdmin, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(radiocaissier))
								.addComponent(codee, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(nom)
								.addComponent(prenom)
								.addComponent(tele))
							.addGap(70)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDateDempoche)
										.addComponent(lblDateDeNaissance))
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(ddn, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(dem, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNombreDesCommandes)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(nbrc, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
						.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(codee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDateDeNaissance)
						.addComponent(ddn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDateDempoche)
								.addComponent(dem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreDesCommandes)
								.addComponent(nbrc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(prenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(tele, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(radioAdmin)
								.addComponent(radiocaissier))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAdresse)))
					.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
					.addComponent(adresse, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("precedente");
		btnNewButton.setBackground(new Color(51, 153, 204));
		panelb.add(btnNewButton);
		
		JButton sauv = new JButton("Sauvegader");
		sauv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fonction;
				
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Sauvegarder les Modification","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					if(radioAdmin.isSelected()) {
						fonction=1;
					}else {
						fonction=0;
					}
					Connection conn=Connexion.connecter();
					
					try {
						Statement stmnt=conn.createStatement();
						int codeei=Integer.parseInt(codee.getText());
						String noms=nom.getText();
						String prenoms=prenom.getText();
						int telei=Integer.parseInt(tele.getText());
						String adrs=adresse.getText();
						String ddns=ddn.getText();
						String dems=dem.getText();
						stmnt.execute("UPDATE emp SET nom_e='"+noms+"',prenom_e='"+prenoms+"',dnn='"+ddns+"',Telephone="+telei+",Adresse='"+adrs+"',dde='"+dems+"' WHERE code_e="+codeei);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		});
		sauv.setBackground(new Color(51, 153, 204));
		
		
		JButton vide = new JButton("Vider les Champs");
		vide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codee.setEditable(true);
				btnAjouter.setVisible(true);
				codee.setText("");
				nom.setText("");
				prenom.setText("");
				tele.setText("");
				adresse.setText("");
				ddn.setText("");
				dem.setText("");
				nbrc.setText(""+0);
				radioAdmin.setEnabled(true);
				radiocaissier.setEnabled(true);
			}
		});
		vide.setBackground(new Color(51, 153, 204));
		
		btnAjouter.setBackground(new Color(51, 153, 204));
		panelb.add(btnAjouter);
		
		JButton supp = new JButton("Supprimer");
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioAdmin.isSelected()) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de supprimer un admin","Tache Impossible", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Supprimer ce Employer","Confirmation",JOptionPane.YES_NO_OPTION);
					if(r==0) {
						try {
							Statement stmnt=conn.createStatement();
							int codeei=Integer.parseInt(codee.getText());
							stmnt.execute("delete from emp where code_e="+codeei);
							rset=stmnt.executeQuery("select * from emp");
							try {
								if(rset.isLast()) {
									rset.first();
									rset.next();
									codee.setText(rset.getString(1));
								nom.setText(rset.getString(2));
								prenom.setText(rset.getString(3));
								tele.setText(""+rset.getInt(6));
								if(rset.getBoolean(10)) {
									radioAdmin.setSelected(true);
								}else {
									 radiocaissier.setSelected(true);
								}
								adresse.setText(rset.getString(7));
								dem.setText(rset.getString(8));
								nbrc.setText(""+rset.getInt(9));
								ddn.setText(rset.getString(5));
								}else {
									rset.next();
								codee.setText(rset.getString(1));
							nom.setText(rset.getString(2));
							prenom.setText(rset.getString(3));
							tele.setText(""+rset.getInt(6));
							if(rset.getBoolean(10)) {
								radioAdmin.setSelected(true);
							}else {
								 radiocaissier.setSelected(true);
							}
							adresse.setText(rset.getString(7));
							dem.setText(rset.getString(8));
							nbrc.setText(""+rset.getInt(9));
							ddn.setText(rset.getString(5));
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				}
				
				
			}
		});
		supp.setBackground(new Color(51, 153, 204));
		
		
		JButton btnNewButton_1 = new JButton("suivant");
		btnNewButton_1.setBackground(new Color(51, 153, 204));
		panelb.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioAdmin.setEnabled(false);
				radiocaissier.setEnabled(false);
				codee.setEditable(false);
				btnAjouter.setEnabled(false);
				try {
					if(rset.isLast()) {
						rset.first();
						codee.setText(rset.getString(1));
					nom.setText(rset.getString(2));
					prenom.setText(rset.getString(3));
					tele.setText(""+rset.getInt(6));
					if(rset.getBoolean(10)) {
						radioAdmin.setSelected(true);
					}else {
						 radiocaissier.setSelected(true);
					}
					adresse.setText(rset.getString(7));
					dem.setText(rset.getString(8));
					nbrc.setText(""+rset.getInt(9));
					ddn.setText(rset.getString(5));
					}else {
						rset.next();
					codee.setText(rset.getString(1));
				nom.setText(rset.getString(2));
				prenom.setText(rset.getString(3));
				tele.setText(""+rset.getInt(6));
				if(rset.getBoolean(10)) {
					radioAdmin.setSelected(true);
				}else {
					 radiocaissier.setSelected(true);
				}
				adresse.setText(rset.getString(7));
				dem.setText(rset.getString(8));
				nbrc.setText(""+rset.getInt(9));
				ddn.setText(rset.getString(5));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioAdmin.setEnabled(false);
				radiocaissier.setEnabled(false);
				codee.setEditable(false);
				btnAjouter.setEnabled(false);
					try {
						if(rset.isFirst()) {
							rset.last();
							codee.setText(rset.getString(1));
						nom.setText(rset.getString(2));
						prenom.setText(rset.getString(3));
						tele.setText(""+rset.getInt(6));
						if(rset.getBoolean(10)) {
							radioAdmin.setSelected(true);
						}else {
							 radiocaissier.setSelected(true);
						}
						adresse.setText(rset.getString(7));
						dem.setText(rset.getString(8));
						nbrc.setText(""+rset.getInt(9));
						ddn.setText(rset.getString(5));
						}else {
							rset.previous();
						codee.setText(rset.getString(1));
					nom.setText(rset.getString(2));
					prenom.setText(rset.getString(3));
					tele.setText(""+rset.getInt(6));
					if(rset.getBoolean(10)) {
						radioAdmin.setSelected(true);
					}else {
						 radiocaissier.setSelected(true);
					}
					adresse.setText(rset.getString(7));
					dem.setText(rset.getString(8));
					nbrc.setText(""+rset.getInt(9));
					ddn.setText(rset.getString(5));
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}
		});
		panel_1.setLayout(gl_panel_1);
		if(isadmin) {
			panelb.add(sauv);
			panelb.add(supp);
			panelb.add(vide);
		}
		
	}
}