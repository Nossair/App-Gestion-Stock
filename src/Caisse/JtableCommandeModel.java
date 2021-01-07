package Caisse;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class JtableCommandeModel extends AbstractTableModel {
	static ArrayList<JtableModel> list =new ArrayList();
	String[] titre= {"Code","Nom","Quantité","Prix"};
	public JtableCommandeModel() {
		list.clear();
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	public Object getValueAt(int i, int j) {
		switch (j) {
		case 0 :  return list.get(i).getCode_cmd();
		case 1 : return list.get(i).getNom_cmd();
		case 2 : return list.get(i).getQt_p_cmd();
		case 3 : return list.get(i).getPrix_cmd();
		}
		return 0;
			
	}
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	public String getColumnName(int col) {
		return titre[col];
		
	}
	public  JtableCommandeModel(String nom_cmd,int code_cmd, int qt_p_cmd, double prix_cmd) {
		list.add(new JtableModel(nom_cmd,code_cmd,qt_p_cmd,prix_cmd));
		
	}
	
	
	
}
