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
import javax.swing.Icon;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField codec;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tele;
	private ResultSet rset;
	private Statement stmnt;
	private JTextField note;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client(0,true);
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
	public Client(int code,boolean isadmin) throws SQLException {
Connection conn=Connexion.connecter();
		
		
			stmnt=conn.createStatement();
			rset=stmnt.executeQuery("select * from client");
			rset.next();
		 
			
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 488);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		
		JMenu m = new JMenu("Menu");
		m.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\m.png"));
		menu.add(m);
		
		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Client.this.setVisible(false);
				
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
				 		Client.this.setVisible(false);
				 	}
				 });
		JMenuItem stocke=new JMenuItem(new ImageIcon("icons8-trolley-50-ConvertImage.png"));
		stocke.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
				 		Produit ap=new Produit(code,Methode.getFonction(code));
				 		 
				 		ap.setVisible(true);
				 		Client.this.setVisible(false);
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
				Client.this.setVisible(false);
				prof.setVisible(true);
			}
		});
		JMenuItem cmd=new JMenuItem(new ImageIcon("ncmdcopier.png"));
		cmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Commande c=new Commande(code,Methode.getFonction(code));
				Client.this.setVisible(false);
				c.setVisible(true);
			}
		});
	 m.add(profil);
	 m.add(stocke);
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
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\commercial_development_management_100px.png"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		codec = new JTextField();
		codec.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		
		tele = new JTextField();
		tele.setColumns(10);
		codec.setText(rset.getString(1));
		nom.setText(rset.getString(2));
		prenom.setText(rset.getString(3));
		JLabel lblNewLabel_1 = new JLabel("Code de Client :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Nom de Client :");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Prenom de Client:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Telephone du Client :");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel NOTE = new JLabel("Note :");
		NOTE.setFont(new Font("Arial", Font.BOLD, 12));
		
		note = new JTextField();
		note.setColumns(10);
		note.setEditable(false);
		JLabel ADRESSE = new JLabel("Adresse de Client :");
		ADRESSE.setFont(new Font("Arial", Font.BOLD, 12));
		
		JTextArea add = new JTextArea();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		add.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(codec, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
								.addComponent(nom, 153, 153, 153)
								.addComponent(prenom, 153, 153, 153))
							.addGap(70)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(NOTE)
									.addGap(99)
									.addComponent(note, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addComponent(tele, 109, 109, 109))))
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addComponent(ADRESSE, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(add, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(codec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(tele, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(NOTE)
						.addComponent(note, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(prenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(ADRESSE)
					.addGap(18)
					.addComponent(add, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41))
		);
		
		JButton btnNewButton = new JButton("precedente");
		btnNewButton.setBackground(new Color(51, 153, 204));
		
		
		JButton btnNewButton_2 = new JButton("Sauvegader");
		 
		btnNewButton_2.setBackground(new Color(51, 153, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fonction;
				
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Sauvegarder les Modification","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					try {
						Statement stmnt=conn.createStatement();
						int codeei=Integer.parseInt(codec.getText());
						int n=Integer.parseInt(note.getText());
						stmnt.execute( "update`client` set nom_c = '"+nom.getText()+"',prenom_c='"+prenom.getText()+"',tele_c="+tele.getText()+",adresse_c='"+add.getText()+"' where code_c = "+codeei);
						rset=stmnt.executeQuery("select * from client");
						 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
	});
		
		JButton btnAjouter = new JButton("Ajouter");
		JButton btnViderLesChamps = new JButton("Vider les Champs");
		
		btnViderLesChamps.setBackground(new Color(51, 153, 204));
		btnViderLesChamps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fonction;
				codec.setEditable(true);
				
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment vider les champs","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					btnAjouter.setVisible(true);
					try {
						Statement stmnt=conn.createStatement();
						int codeei=Integer.parseInt(codec.getText());
						 
						codec.setText("");
						nom.setText("");
						prenom.setText("");
						tele.setText("");
						note.setText("");
						add.setText("");
						
						 
						 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
			
		
	});
		
		
		
		btnAjouter.setVisible(false);
		btnAjouter.setBackground(new Color(51, 153, 204));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fonction;
				
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Sauvegarder les Modification","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					try {
						Statement stmnt=conn.createStatement();
						int codeei=Integer.parseInt(codec.getText());
						
						
						stmnt.execute("insert into client (`code_c`, `nom_c`, `prenom_c`, `tele_c`, `adresse_c`)  VALUES ("+codeei+",'"+nom.getText()+"', '"+prenom.getText()+"', "+tele.getText()+",'"+add.getText()+"')");	 
						rset=stmnt.executeQuery("select * from client");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
			
		
	});
		
	
		
		JButton btnNewButton_3 = new JButton("Supprimer");
		btnNewButton_3.setBackground(new Color(51, 153, 204));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fonction;
				
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Sauvegarder les Modification","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					try {
						Statement stmnt=conn.createStatement();
						int codeei=Integer.parseInt(codec.getText());
						stmnt.execute("delete from client where code_c="+codeei);
						 rset=stmnt.executeQuery("select * from client");
							codec.setEditable(false);
							 
							try {
								if(rset.isLast()) {
									rset.first();
									codec.setText(rset.getString(1));
								nom.setText(rset.getString(2));
								prenom.setText(rset.getString(3));
								tele.setText(""+rset.getInt(4));
							
								add.setText(rset.getString(5));
								
								note.setText(""+rset.getInt(6));
							 
								}else {
									rset.next();
									codec.setText(rset.getString(1));
									nom.setText(rset.getString(2));
									prenom.setText(rset.getString(3));
									tele.setText(""+rset.getInt(4));
								
									add.setText(rset.getString(5));
									
									note.setText(""+rset.getInt(6));
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
			
			
		
	});
		
		
		
		JButton btnNewButton_1 = new JButton("suivant");
		btnNewButton_1.setBackground(new Color(51, 153, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				codec.setEditable(false);
				 
					try {
						if(rset.isFirst()) {
							rset.last();
							codec.setText(rset.getString(1));
						nom.setText(rset.getString(2));
						prenom.setText(rset.getString(3));
						tele.setText(""+rset.getInt(4));
						 
						add.setText(rset.getString(5));
						 
						note.setText(""+rset.getInt(6));
						 
						}else {
							rset.previous();
						codec.setText(rset.getString(1));
					nom.setText(rset.getString(2));
					prenom.setText(rset.getString(3));
					tele.setText(""+rset.getInt(4));
				 
					add.setText(rset.getString(5));
					 
					note.setText(""+rset.getInt(6));
					 
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				 
				codec.setEditable(false);
			 
				try {
					if(rset.isLast()) {
						rset.first();
						codec.setText(rset.getString(1));
					nom.setText(rset.getString(2));
					prenom.setText(rset.getString(3));
					tele.setText(""+rset.getInt(4));
				
					add.setText(rset.getString(5));
					
					note.setText(""+rset.getInt(6));
				 
					}else {
						rset.next();
						codec.setText(rset.getString(1));
						nom.setText(rset.getString(2));
						prenom.setText(rset.getString(3));
						tele.setText(""+rset.getInt(4));
					
						add.setText(rset.getString(5));
						
						note.setText(""+rset.getInt(6));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		codec.setText(rset.getString(1));
		nom.setText(rset.getString(2));
		prenom.setText(rset.getString(3));
		tele.setText(""+rset.getInt(4));
		 
		add.setText(rset.getString(5));
		 
		note.setText(""+rset.getInt(6));
		panel_2.add(btnNewButton);
			panel_2.add(btnNewButton_1); 
		 if(isadmin)
		 {
			panel_2.add(btnNewButton);
			panel_2.add(btnNewButton_2); 
			
			panel_2.add(btnNewButton_3);
			panel_2.add(btnAjouter);
			panel_2.add(btnViderLesChamps);
			panel_2.add(btnNewButton_1);		
		 }
		 
		 
		panel_1.setLayout(gl_panel_1);
		
		
	}
}