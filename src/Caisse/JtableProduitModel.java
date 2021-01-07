package Caisse;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class JtableProduitModel extends AbstractTableModel {
	ArrayList<JtableModel> list =new ArrayList();
	String[] titre= {"Code","Nom","Prix","Quantite"};
	public JtableProduitModel() {
		
		try {
			Connection con=Connexion.connecter();
			Statement stmnt=con.createStatement();
			ResultSet rset =stmnt.executeQuery("select * from produit");
			while(rset.next()) {
				list.add(new JtableModel(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4)));
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
		case 0 :  return list.get(i).getCode_p();
		case 1 : return list.get(i).getNom_p();
		case 2 : return list.get(i).getPrix_p();
		case 3 : return list.get(i).getQuantite_p();
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
		if(j==3) {
		return true;
		}else {
			return false;
		}
	}
	public void setValueAt(final Object value, final int row, final int column) { 
	    if(column==3) {
	    	int r=JOptionPane.showConfirmDialog(null, "Voulez-vous Vraiment modifier la quantité","Confirmation",JOptionPane.YES_NO_OPTION);
	    	if(r==0) {
	    	list.get(row).setQuantite_p(Integer.parseInt(""+value));
	    	Connection con=Connexion.connecter();
	    	try {
				Statement stmnt=con.createStatement();
				boolean requet=stmnt.execute("UPDATE `produit` SET `stock_p`="+list.get(row).getQuantite_p()+" WHERE code_p="+getValueAt(row,0));
				fireTableCellUpdated(row, column);
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    	
	    	
	    	
	    }
		
	    
	    
	} 
	
	
}
