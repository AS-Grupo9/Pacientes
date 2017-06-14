package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlpac.Paciente;


public class DBControlador
{
	private Connection con = null;
	
	public void conectar(){    
		try {
	      Class.forName("org.sqlite.JDBC");
	      this.con = DriverManager.getConnection("jdbc:sqlite:laureles.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Base de datos abierta correctamente");
	}

	public void close(){    
		try {
	      this.con.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection c) {
		this.con = c;
	}
	
	/*public static void main(String args[]){
		DBControlador conector = new DBControlador();
		conector.conectar();
	}
	 */
	
	public void insertarPaciente(String codigoPaciente, String nombre, String codigoMedico, String diagnostico){
		this.conectar();
		String query = "INSERT INTO paciente (codigo,nombre,diagnostico) values(?,?,?);";
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoPaciente));
			pst.setString(2, nombre);
			//pst.setInt(3, Integer.parseInt(codigoMedico));
			pst.setString(3, diagnostico);
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
		
}

