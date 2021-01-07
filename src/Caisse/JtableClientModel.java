package Caisse;

import java.sql.*;
import java.util.*;

import javax.swing.table.AbstractTableModel;


public class JtableClientModel extends AbstractTableModel {
	ArrayList<JtableModel> list =new ArrayList();
	String[] titre= {"Code","Nom","Prenom","Telephone","Adresse","Note"};
	public JtableClientModel() {
		
		try {
			Connection con=Connexion.connecter();
			Statement stmnt=con.createStatement();
			ResultSet rset =stmnt.executeQuery("select * from Client");
			while(rset.next()) {
				list.add(new JtableModel(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getInt(4),rset.getString(5),rset.getInt(6)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("hna");
		}
	
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public Object getValueAt(int i, int j) {
		switch (j) {
		case 0 :  return list.get(i).getCode_c();
		case 1 : return list.get(i).getNom_c();
		case 2 : return list.get(i).getPrenom_c();
		case 3 : return list.get(i).getTele_c();
		case 4 : return list.get(i).getAdresse_c();
		case 5 : return list.get(i).getNote();
		}
		return 0;
			
	}
	/*public void setValueAt(Object o,int i, int j) {
		switch (j) {
		case 0 :  list.get(i).setCne((String) o);
		case 1 : list.get(i).setNom((String) o);
		case 2 : list.get(i).setFillier((String) o);
		case 3 : list.get(i).setAge((int) o);
		case 4 : list.get(i).setTele((int) o);
		}
		fireTableCellUpdated(i, j);
			
	}*/
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public String getColumnName(int col) {
		return titre[col];
		
	}
	public boolean isCellEditable(int i,int j) {
		
		return true;
	}
	
	
}

