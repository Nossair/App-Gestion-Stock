package Caisse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Produit extends JFrame {

	private JPanel contentPane;
	private JTextField codep;
	private JTextField nom;
	private JTextField prix;
	private JTextField quantite;
	private JButton prec;
	private JTextArea desci;
	private ResultSet rset;
	private Connection conn;
	private JButton ajoute;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produit frame = new Produit(0,true);
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
	public Produit(int code,boolean isadmin) {
		 prec = new JButton("precedente");
		 nom = new JTextField();
		 codep = new JTextField();
		 codep.setEditable(false);
		 prix = new JTextField();
		 quantite = new JTextField();
		 desci = new JTextArea();
		 ajoute = new JButton("Ajouter");
		 ajoute.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Ajouter ce Produit","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					String noms=nom.getText();
					double prixd=Double.parseDouble(prix.getText());
					int q=Integer.parseInt(quantite.getText());
					String d=desci.getText();
					int c=Integer.parseInt(codep.getText());
					try {
						Statement stmnt=conn.createStatement();
						stmnt.execute("INSERT INTO `produit`(`code_p`, `nom_p`, `prix_p`, `stock_p`, `Desc`) VALUES ("+c+",'"+noms+"',"+prixd+","+q+",'"+d+"')");
						rset=stmnt.executeQuery("select * from produit");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
				}
		 		
		 	}
		 });
		 conn=Connexion.connecter();
		 try {
			Statement stmnt=conn.createStatement();
			rset=stmnt.executeQuery("select * from produit");
			rset.next();
			codep.setText(""+rset.getInt(1));
			nom.setText(rset.getString(2));
			prix.setText(""+rset.getInt(3));
			quantite.setText(""+rset.getInt(4));
			desci.setText(""+rset.getString(5));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 390);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		
		JMenu m = new JMenu("MENU");
		m.setIcon(new ImageIcon("m.png"));
		menu.add(m);
		
		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
				Produit.this.setVisible(false);
				
			}
		});
		JMenuItem stocke=new JMenuItem(new ImageIcon("icons8-trolley-50-ConvertImage.png"));
		stocke.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		Profil ap = null;
				try {
					ap = new Profil(code,Methode.getFonction(code));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 		 
		 		ap.setVisible(true);
		 		Produit.this.setVisible(false);
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
				Produit.this.setVisible(false);
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
				Produit.this.setVisible(false);
				prof.setVisible(true);
			}
		});
		JMenuItem cmd=new JMenuItem(new ImageIcon("ncmdcopier.png"));
		cmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Commande c=new Commande(code,Methode.getFonction(code));
				Produit.this.setVisible(false);
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
		
		JLabel lblAjouter = new JLabel("Produit");
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\prod.png"));
		lblAjouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(169)
					.addComponent(lblAjouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(193))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAjouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Code du Produit :");
		lblNewLabel.setForeground(new Color(51, 153, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Nom du Produit :");
		lblNewLabel_1.setForeground(new Color(51, 153, 204));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Quantit\u00E9 du Produit");
		lblNewLabel_2.setForeground(new Color(51, 153, 204));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_3 = new JLabel("Prix du Produit");
		lblNewLabel_3.setForeground(new Color(51, 153, 204));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		codep.setColumns(10);
		
		
		nom.setColumns(10);
		
		
		prix.setColumns(10);
		
		
		quantite.setColumns(10);
		JLabel test = new JLabel("");
		
	
		test.setForeground(Color.RED);
		test.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel desc = new JLabel("Description :");
		desc.setFont(new Font("Tahoma", Font.BOLD, 13));
		desc.setForeground(new Color(51, 153, 204));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		desci.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		desci.setColumns(25);
		desci.setRows(3);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(255)
							.addComponent(test))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(desc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(prix)
										.addComponent(codep, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(quantite, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
										.addComponent(nom, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
								.addComponent(desci, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(13)
					.addComponent(test)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(codep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(prix, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(quantite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(desc)
						.addComponent(desci, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(104))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		
		prec.setBackground(new Color(51, 153, 204));
		prec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codep.setEditable(false);
				try {
					
					if(rset.isFirst()) {
						rset.last();
						codep.setText(""+rset.getInt(1));
					nom.setText(rset.getString(2));
					prix.setText(""+rset.getInt(3));
					quantite.setText(""+rset.getInt(4));
					desci.setText(""+rset.getString(5));
					}else {
						rset.previous();
						codep.setText(""+rset.getInt(1));
						nom.setText(rset.getString(2));
						prix.setText(""+rset.getInt(3));
						quantite.setText(""+rset.getInt(4));
						desci.setText(""+rset.getString(5));
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton sauv = new JButton("Sauvegader");
		sauv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Sauvegarder les Modification","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					String noms=nom.getText();
					double prixd=Double.parseDouble(prix.getText());
					int q=Integer.parseInt(quantite.getText());
					String d=desci.getText();
					int c=Integer.parseInt(codep.getText());
					try {
						Statement stmnt=conn.createStatement();
						stmnt.execute("UPDATE `produit` SET `nom_p`='"+noms+"',`prix_p`="+prixd+",`stock_p`="+q+",`Desc`='"+d+"' WHERE code_p= "+c);
						rset=stmnt.executeQuery("select * from produit");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
				}
			}
		});
		sauv.setBackground(new Color(51, 153, 204));
		
		JButton vide = new JButton("Vider les Champs");
		vide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codep.setEditable(true);
				codep.setText("");
				nom.setText("");
				prix.setText("");
				quantite.setText("");
				desci.setText("");
				ajoute.setVisible(true);
			}
		});
		vide.setBackground(new Color(51, 153, 204));
		
		
		ajoute.setBackground(new Color(51, 153, 204));
		ajoute.setVisible(false);
		JButton supp = new JButton("Supprimer");
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment Supprimer ce Employer","Confirmation",JOptionPane.YES_NO_OPTION);
				if(r==0) {
					try {
						Statement stmnt=conn.createStatement();
						int codepi=Integer.parseInt(codep.getText());
						stmnt.execute("delete from produit where code_p="+codepi);
						rset=stmnt.executeQuery("select * from produit");
						if(rset.isLast()) {
							rset.first();
							codep.setText(""+rset.getInt(1));
						nom.setText(rset.getString(2));
						prix.setText(""+rset.getInt(3));
						quantite.setText(""+rset.getInt(4));
						desci.setText(""+rset.getString(5));
						}else {
							rset.next();
							codep.setText(""+rset.getInt(1));
							nom.setText(rset.getString(2));
							prix.setText(""+rset.getInt(3));
							quantite.setText(""+rset.getInt(4));
							desci.setText(""+rset.getString(5));
							
						}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				}
				}
		});
		supp.setBackground(new Color(51, 153, 204));
		
		JButton suiv = new JButton("suivant");
		suiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codep.setEditable(false);
				try {
					
					if(rset.isLast()) {
						rset.first();
						codep.setText(""+rset.getInt(1));
					nom.setText(rset.getString(2));
					prix.setText(""+rset.getInt(3));
					quantite.setText(""+rset.getInt(4));
					desci.setText(""+rset.getString(5));
					}else {
						rset.next();
						codep.setText(""+rset.getInt(1));
						nom.setText(rset.getString(2));
						prix.setText(""+rset.getInt(3));
						quantite.setText(""+rset.getInt(4));
						desci.setText(""+rset.getString(5));
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		 suiv.setBackground(new Color(51, 153, 204));
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		panel_2.add(prec);
		panel_2.add(sauv);
			panel_2.add(vide);
			panel_2.add(supp);
		panel_2.add(ajoute);
		
		panel_2.add(suiv);
		sauv.setVisible(false);
		vide.setVisible(false);
		supp.setVisible(false);
		if(isadmin) {
			sauv.setVisible(true);
			vide.setVisible(true);
			supp.setVisible(true);
		}
	}
}