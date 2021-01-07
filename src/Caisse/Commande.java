package Caisse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import javax.swing.JMenuItem;

public class Commande extends JFrame {
	JLabel message ;
	private JPanel contentPane;
	private JTextField codepr;
	private JTextField nompr;
	private JTextField qt;
	private JTextField prix;
	 String col[] = { "code", "Nom", "prix","quantite" };
     String cont[][] = new String[10][4];
     private JTextField total;
     private double ptotal=0;
     private JLabel erreur;
     private JComboBox codec;
     private int quant;
     private double pr;
     

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande frame = new Commande(0,true);
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
	public Commande(int code,boolean isadmin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 496);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.WHITE);
		setJMenuBar(menu);
		
		JMenu m = new JMenu("MENU");
		m.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\m.png"));
		menu.add(m);

		JMenuItem item=new JMenuItem(new ImageIcon("icons8-exit-20.png"));
		m.add(item);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login prof=new Login();
				prof.setVisible(true);
			Commande.this.setVisible(false);
				
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
				 	Commande.this.setVisible(false);
				 	}
				 });
		JMenuItem stocke=new JMenuItem(new ImageIcon("icons8-trolley-50-ConvertImage.png"));
		stocke.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
				 		Produit ap=new Produit(code,Methode.getFonction(code));
				 		 
				 		ap.setVisible(true);
				 	Commande.this.setVisible(false);
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
			Commande.this.setVisible(false);
				prof.setVisible(true);
			}
		});
		JMenuItem cmd=new JMenuItem(new ImageIcon("ncmdcopier.png"));
		cmd.addActionListener(new ActionListener() {
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
				Commande.this.setVisible(false);
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
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 204));
		JtableCommandeModel model = new JtableCommandeModel();
	      JTable table=new JTable(model);
		
		JLabel lblNewLabel_5 = new JLabel("Ajouter une Commande");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\eclipse-workspace\\ProjetIHM\\ncmd.png"));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Code produit");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Nom produit");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Quantite");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Prix");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		
		codepr = new JTextField();
		codepr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(codepr.getText().equals("")) {
					codepr.setBorder(BorderFactory.createLineBorder(Color.red));
					}else 
					{
						 Connection con=Connexion.connecter(); 
						 try {
							Statement st=con.createStatement();
							 ResultSet rest=st.executeQuery("select code_p From produit where code_p="+codepr.getText());
							 boolean b=true;
							 int i=0;
							 if(rest.next()) {
								rest=st.executeQuery("select nom_p,prix_p from produit where code_p="+codepr.getText());
								rest.next();
								nompr.setText(rest.getString("nom_p"));
								prix.setText(""+rest.getFloat("prix_p"));
								codepr.setBorder(BorderFactory.createLineBorder(Color.black));
							 }else {
								 codepr.setBorder(BorderFactory.createLineBorder(Color.red));
								 erreur.setText("Code Invalide");
							 }
							
							 
							 /*if(b) {
								 codepr.setBorder(BorderFactory.createLineBorder(Color.red));
							 }*/
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					}
			}
		});
		codepr.setColumns(10);
		
		nompr = new JTextField();
		nompr.setColumns(10);
		
		qt = new JTextField();
		qt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(qt.getText().equals("")||Integer.parseInt(qt.getText())<=0) {
					qt.setBorder(BorderFactory.createLineBorder(Color.red));
					}else {
						Connection con=Connexion.connecter();
						try {
							Statement stmnt=con.createStatement();
							ResultSet rset=stmnt.executeQuery("select stock_p from produit where code_p="+codepr.getText());
							rset.next();
							if(rset.getInt("stock_p")<Integer.parseInt(qt.getText())) {
								qt.setBorder(BorderFactory.createLineBorder(Color.red));
								erreur.setText("Stock insuffisant");
							}else {
								qt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								erreur.setText("");
							}
									
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
			}
		});
		qt.setColumns(10);
		
		prix = new JTextField();
		prix.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(erreur.getText().equals("")) {
				 quant=Integer.parseInt(qt.getText());
				pr=Double.parseDouble(prix.getText())*quant;
				
				
				JtableCommandeModel model1=new JtableCommandeModel(nompr.getText(), Integer.parseInt(codepr.getText()),quant,pr);
				table.setModel(model1);
				ptotal+=pr;
				total.setText(""+ptotal);
				}else {
					erreur.setText("Impossible");
				}
			}
		});
		btnNewButton.setBackground(new Color(51, 153, 204));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblCodeClient = new JLabel("Code Client");
		lblCodeClient.setFont(new Font("Arial", Font.BOLD, 12));
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quant=0;
				pr=0;
				ptotal=0;
				nompr.setText("");
				codepr.setText("");
				qt.setText("");
				prix.setText("");
				total.setText("");
				JtableCommandeModel model1=new JtableCommandeModel();
				table.setModel(model1);
				
				
			}
		});
		btnAnnuler.setFont(new Font("Arial", Font.BOLD, 12));
		btnAnnuler.setBackground(new Color(51, 153, 204));
		
		 codec = new JComboBox();
		
		//JComboBox codec = new JComboBox();
		
		codec=Methode.remplire(codec);
		codec.setBackground(Color.WHITE);
		
	      
	      
	//	pane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblCodeClient, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
									.addGap(98))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(codepr, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(nompr, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(prix, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnAnnuler, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(qt, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
								.addComponent(codec, Alignment.TRAILING, 0, 272, Short.MAX_VALUE))))
					.addGap(85))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodeClient, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(codec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(codepr, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nompr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(qt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(prix, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(btnAnnuler, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(49))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 70, Short.MAX_VALUE))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(lblNewLabel_5);
		
		JLabel lblVotreTotal = new JLabel("Votre Totale :");
		lblVotreTotal.setFont(new Font("Arial", Font.BOLD, 12));
		
		total = new JTextField();
		total.setEditable(false);
		total.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn=Connexion.connecter();
				if(!codec.getSelectedItem().equals("Choisir un Client")) {
				
				try {
					String n=Methode.getNote(Integer.parseInt(codec.getSelectedItem().toString()));
					 
					if(Integer.parseInt( n)>=10)
					{
						message.setText("vous avez beneficié d'une reduction de 10% ");
						total.setText((Double.parseDouble(total.getText())-Double.parseDouble(total.getText())*10/100)+"");
						Statement stmnt=conn.createStatement();
						 int not=Integer.parseInt( n)-10;
						 int cod=(int) codec.getSelectedItem();
						 stmnt.execute("update client set note_c ="+not+" where code_c="+cod);
						System.out.println("code "+cod+" note "+n);
						
					}
					else
					{
						message.setText("vous avez  :"+n+" points ");
					}
					Statement stmnt=conn.createStatement();
				 
					stmnt.executeUpdate("INSERT INTO commande( code_e, date_cmd,total,code_c) VALUES ("+code+",NOW(),"+total.getText()+","+codec.getSelectedItem()+")");
					ResultSet rset=stmnt.executeQuery("select num_cmd from commande ");
					rset.last();	
					int t=rset.getInt(1);
					ArrayList<JtableModel> l=JtableCommandeModel.list;
					for(int i=0;i<JtableCommandeModel.list.size();i++) {
						int c=l.get(i).getCode_cmd();
					stmnt.executeUpdate("INSERT INTO detail_commande (num_cmd, code_p, quantite) VALUES ("+t+","+c+","+qt.getText()+")");
					}
				
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				try {
					Statement stmnt=conn.createStatement();
					stmnt.executeUpdate("INSERT INTO commande( code_e, date_cmd,total) VALUES ("+code+",NOW(),"+total.getText()+")");
					ResultSet rset=stmnt.executeQuery("select num_cmd from commande ");
					rset.last();	
					int t=rset.getInt(1);
					ArrayList<JtableModel> l=JtableCommandeModel.list;
					for(int i=0;i<JtableCommandeModel.list.size();i++) {
						int c=l.get(i).getCode_cmd();
					stmnt.executeUpdate("INSERT INTO detail_commande (num_cmd, code_p, quantite) VALUES ("+t+","+c+","+qt.getText()+")");
					}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				}
		});
		btnNewButton_1.setBackground(new Color(51, 153, 204));
		
		erreur = new JLabel("");
		erreur.setFont(new Font("Arial", Font.BOLD, 12));
		erreur.setForeground(Color.RED);
		
		message= new JLabel("");
		message.setForeground(new Color(46, 139, 87));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(40)
							.addComponent(erreur))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(message)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblVotreTotal, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
									.addGap(40)
									.addComponent(total, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
									.addGap(89)
									.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(message)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVotreTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(erreur)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		panel_2.setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane Pane = new JScrollPane(table);
		panel_2.add(Pane);
		contentPane.setLayout(gl_contentPane);
	}
}

