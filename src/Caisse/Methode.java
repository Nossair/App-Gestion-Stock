package Caisse;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
	
	
public class Methode  {

	static Statement	stx;
	static String query;
	static Connection con=Connexion.connecter();
	static ResultSet rset ;
	
	
	public static boolean verifier(int login,String password){
		boolean test=false;
		int log =0;
		String pass=null;
		try{  
		
			stx=con.createStatement();
			query="SELECT code_e,pass_word FROM emp";
			rset=stx.executeQuery(query);
			while(rset.next()|| (test==false)){
				log=rset.getInt(1);
				pass=rset.getString(2);
				if((log==login) && (pass.equals(password)))
					test=true;		
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		System.out.println(test);
		System.out.println(log);
		System.out.println(pass);
		return test;

	}
	public int getid(int i)
	{
		return i;
	}
	
	public static ResultSet afficherCaissier(int id){
		try{
			stx=con.createStatement();
			query="SELECT * FROM emp WHERE code_e="+id;
			rset=stx.executeQuery(query);
			
		}
		catch(SQLException ex){
			ex.printStackTrace();
			
			
		}
		return rset;
	}
	public static ResultSet afficherClient(int id){
		try{
			stx=con.createStatement();
			query="SELECT * FROM client WHERE code_c="+id;
			rset=stx.executeQuery(query);
			
		}
		catch(SQLException ex){
			ex.printStackTrace();
			
			
		}
		return rset;
	}
	public static String getNote(int code) {
		ResultSet r=afficherClient(code);
		String re = null;
		try {
			r.next();
			re= ""+r.getInt(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getTele(int code) {
		ResultSet r=afficherCaissier(code);
		String tele = null;
		try {
			r.next();
			tele= r.getString(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tele;
	}
	
	public static boolean getFonction(int code) {
		ResultSet r=afficherCaissier(code);
		boolean re =  true;
		try {
			r.next();
			re= r.getBoolean(10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	
	public static String getAdress(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= r.getString(7);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getDdn(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= r.getString(5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getDde(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= r.getString(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getNbrc(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= ""+r.getInt(9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getNom(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= r.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static String getPrenom(int code) {
		ResultSet r=afficherCaissier(code);
		String re = null;
		try {
			r.next();
			re= r.getString(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	public static JComboBox remplire(JComboBox b) {
		Connection conn=Connexion.connecter();
		
		try {
			Statement stmnt=conn.createStatement();
			ResultSet rset=stmnt.executeQuery("select code_c from client");
			b.addItem("Choisir un Client");
			while(rset.next()) {
				b.addItem(rset.getInt("code_c"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}