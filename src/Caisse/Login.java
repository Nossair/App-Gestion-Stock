package Caisse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	JPanel panel;
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 413);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		 panel = new JPanel();
		panel.setBackground(SystemColor.text);
		contentPane.add(panel, BorderLayout.CENTER);
		
		login = new JTextField();
		login.setForeground(Color.LIGHT_GRAY);
		login.setText("Entrer UserName");
		login.setColumns(10);
		JLabel test = new JLabel("");
		test.setForeground(Color.RED);
		
		pass = new JPasswordField();
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Methode.verifier(Integer.parseInt(login.getText()), pass.getText())) {
					Connection con=Connexion.connecter();
					try {
						Statement stm=con.createStatement();
						ResultSet rset=stm.executeQuery("select isadmin from emp where code_e="+login.getText());
						rset.next();
						Menu m=new Menu(Integer.parseInt(login.getText()),rset.getBoolean("isadmin"));
					m.setVisible(true);
					m.pack();
					Login.this.dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else {
					test.setText("UserName ou Password Incorrect");
				}
			}
		});
		btnConnexion.setBackground(SystemColor.scrollbar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		
		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(SystemColor.activeCaptionBorder);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
										.addComponent(test, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(30)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(pass, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
											.addComponent(login, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
										.addContainerGap()))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(62)
									.addComponent(btnConnexion, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(150)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(79))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(test)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(login, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(pass, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(31)
					.addComponent(btnConnexion, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(132))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Gebruiker\\Downloads\\icons8-customer-64 (1).png"));
		
		JLabel lblBienvenue = new JLabel("Bienvenue");
		lblBienvenue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBienvenue.setForeground(SystemColor.text);
		lblBienvenue.setFont(new Font("Verdana", Font.PLAIN, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblBienvenue, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(73)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBienvenue, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
					.addGap(186))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		login.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(login.getText().equals("Entrer UserName"))
				login.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(login.getText().equals("")) {
					login.setText("Entrer UserName");
				}
				
			}

			
		});
		pass.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(pass.getText().equals("*************"))
				pass.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(pass.getText().equals("")) {
					pass.setText("*************");
				}
				
			}

			
		});
		pass.setText("*************");
	}
}
