package Caisse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	public JButton stock;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(0,true);
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu(int code,boolean isadmin) {
		panel=new JPanel(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		JMenu m=new JMenu("MENU");
		menu.add(m);
		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Menu.this.setVisible(false);
			}
		});
		
		JLabel lblNewLabel = new JLabel("                                                          ");
		lblNewLabel.setBackground(Color.WHITE);
		menu.add(lblNewLabel);
		
		JLabel enom = new JLabel("SBAIBI Nossair");
		enom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Menu.this.setVisible(false);
				Profil p=null;
				try {
					p = new Profil(code,true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setVisible(true);
			}
			
		});
		menu.add(enom);
		
	
		
		 panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		

		
		 JButton profil = new JButton(new ImageIcon("emp.png"));
	 
		 profil.setToolTipText("Votre Profil");
		 profil.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		 profil.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		Profil prf = null;
		 		try {
		 			prf = new Profil(code,true);
		 		} catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		 		//prf.affiche();
		 		prf.setVisible(true);
		 	}
		 });
		 profil.setBackground(new Color(0x3399cc));
		 
		 			
		 			
		 				 stock = new JButton(new ImageIcon("ss.png"));
		 				 
		 				 stock.setToolTipText("Stocke");
		 				 stock.setBackground(new Color(0x3399cc));
		 				 stock.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		 				 
		 				 
		 				 
		JButton client = new JButton(new ImageIcon("cll.png"));
		 
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client prf = null;
				try {
					prf = new Client(code,isadmin);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//prf.affiche();
				prf.setVisible(true);
			}
		});
		client.setToolTipText("les Clients");
		client.setBackground(new Color(0x3399cc));
		client.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		
		JButton ajoutee = new JButton(new ImageIcon("ajoutclient.png"));
		 
		ajoutee.setToolTipText("les employ\u00E9s");
		ajoutee.setBackground(new Color(0x3399cc));
		ajoutee.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		
		
		
		JButton commande = new JButton(new ImageIcon("cmd.png"));
	 
		commande.setToolTipText("les commandes");
		commande.setBackground(new Color(0x3399cc));
		commande.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		
		
		JButton dec = new JButton(new ImageIcon("dec.png"));
	 
		dec.setToolTipText("D\u00E9connexion");
		dec.setBackground(new Color(0x3399cc));
		dec.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		dec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Menu.this.setVisible(false);
			}
		});
		//panel.add(dec,BorderLayout.SOUTH);
		
		
		JButton ajoutc = new JButton(new ImageIcon("ncmd.png"));
		 
		ajoutc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Commande c=new Commande(code,Methode.getFonction(code));
				Menu.this.setVisible(false);
				c.setVisible(true);
			}
		});
		ajoutc.setToolTipText("ajouter une commande");
		ajoutc.setBackground(new Color(0x3399cc));
		ajoutc.setBorder(BorderFactory.createMatteBorder(3,  3, 3, 3,new Color(0xFBF0F0)));
		ajoutee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Emp prof = null;
				try {
					prof = new Emp(code ,isadmin);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				prof.setVisible(true);
			}
		});
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		 				 stock.addActionListener(new ActionListener() {
		 				 	public void actionPerformed(ActionEvent arg0) {
		 				 		Produit ap=new Produit(code,isadmin);
		 				 		Menu.this.setVisible(false);
		 				 		ap.setVisible(true);
		 				 	}
		 				 });
		 				panel.add(panel_2,BorderLayout.CENTER);
		 			
		if(isadmin) {
			panel_2.setLayout(new GridLayout(3,2));
			
			panel_2.add(stock);
			panel_2.add(profil);
			panel_2.add(client);
			
			 
			panel_2.add(ajoutc);
			panel_2.add(ajoutee);
			 panel_2.add(dec);
			 
			
			
			 
		 
		}else {
			panel_2.setLayout(new GridLayout(3, 2));
			panel_2.add(stock);
			panel_2.add(profil);
			panel_2.add(client);
			panel_2.add(commande);
			panel_2.add(ajoutc);
			panel_2.add(dec);
		 
		}
		
		Connection con=Connexion.connecter();
		try {
			Statement stmnt=con.createStatement();
			ResultSet rset = stmnt.executeQuery("select nom_e,prenom_e from emp where code_e="+code);
			rset.next();
			enom.setText(rset.getString("nom_e").toUpperCase()+"  "+rset.getString("prenom_e"));
			
			JLabel lblNewLabel_1 = new JLabel("                                                       ");
			menu.add(lblNewLabel_1);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Menu.this.setVisible(false);
					Login l=new Login();
					l.setVisible(true);
				}
			});
			mntmNewMenuItem.setBackground(Color.WHITE);
			mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\icons8-shutdown-20.png"));
			menu.add(mntmNewMenuItem);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	 
	}

}
