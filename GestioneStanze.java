package finestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestioneStanze {
	  private static final String DB_URL = "jdbc:mysql://localhost:3306/utentihotel";
	  private static final String DB_USER = "root";
	  private static final String DB_PASSWORD = "Francesco9000";
	  
	  public static Stanza[] getStanza() {
		  Stanza [] stanze = new Stanza[0];
		  try {
			// CARICARE IL DRIVER
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			  // CREO UNO STATEMENT E MI SPOSTERÒ CON LAST E FIRST
			  Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			  ResultSet rs = stmt.executeQuery("SELECT * FROM stanze");
			  if(rs.last() != false) {
				  stanze = new Stanza[rs.getRow()];
				  rs.first();
				  int i = 0;
				  do{
					  stanze[i] = new Stanza(rs.getInt("id"), rs.getString("numeroStanza"), rs.getString("nomeStanza"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getBoolean("disponibile"));
					  i++;
				  }while(rs.next());
			  }
		  }catch(SQLException|ClassNotFoundException e) {
			  e.printStackTrace();
		  }
		  return stanze;
	  }
}
