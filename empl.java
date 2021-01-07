package caissefin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class Emp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emp frame = new Emp();
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
	public Emp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel Code = new JLabel("CODE");
		panel_1.add(Code);
		
		JLabel code = new JLabel("*******");
		code.setEnabled(false);
		panel_1.add(code);
		
		JLabel Nom = new JLabel("NOM");
		panel_1.add(Nom);
		
		JLabel nom = new JLabel("*****");
		panel_1.add(nom);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();
		p1.setToolTipText("");
		tabbedPane.addTab("G\u00E9n\u00E9rale", null, p1, null);
		p1.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel dnn = new JLabel("Date de Naissance");
		p1.add(dnn);
		
		JLabel daten = new JLabel("***");
		p1.add(daten);
		
		JLabel tel = new JLabel("Telephone");
		p1.add(tel);
		
		JLabel telephone = new JLabel("*****");
		p1.add(telephone);
		
		JLabel ad = new JLabel("Adresse");
		p1.add(ad);
		
		JLabel adresse = new JLabel("****");
		p1.add(adresse);
		
		JPanel p2 = new JPanel();
		tabbedPane.addTab("D\u00E9tails", null, p2, null);
		p2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel Fonction = new JLabel("Fonction");
		p2.add(Fonction);
		
		JLabel fonction = new JLabel("********");
		p2.add(fonction);
		
		JLabel lblNewLabel_5 = new JLabel("Date d'embanche ");
		p2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("******");
		p2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre de Commande ");
		p2.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("****");
		p2.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton_5 = new JButton("Pr\u00E9c\u00E9dent");
		
		JButton btnNewButton_4 = new JButton("Supprimer");
		
		JButton btnNewButton_3 = new JButton("Modifier");
		
		JButton btnNewButton_2 = new JButton("Ajouter");
		
		JButton btnNewButton = new JButton("Suivant");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(14)
					.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
	}

}
