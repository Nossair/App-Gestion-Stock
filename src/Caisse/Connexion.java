package Caisse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connexion {
	public static Connection connecter()
	{
		String url="jdbc:mysql://localhost:3306/gestion_caisse";
		Connection	con=null;
		try {
				con=DriverManager.getConnection(url,"root","");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	

}
