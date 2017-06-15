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
	      System.out.println("Base de datos cerrada correctamente");
	    } catch ( Exception e ) {
	    	System.out.println("Error al cerrar la base de datos");
	    	//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
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
	
	/**
	 * PACIENTES
	 * */
	public boolean insertarPaciente(String codigoPaciente, String nombre, String codigoMedico, String diagnostico){
		this.conectar();
		String queryUno = "INSERT INTO paciente (codigo,nombre,diagnostico) values(?,?,?);";
		String queryDos = "INSERT INTO atiende_a (medico,paciente) values(?,?);";
		
		
		try{
				PreparedStatement pst = this.con.prepareStatement(queryUno);
				pst.setInt(1, Integer.parseInt(codigoPaciente));
				pst.setString(2, nombre);
				pst.setString(3, diagnostico);			
				pst.execute();
				System.out.println("Insercion correcta en tabla Paciente");
				
				PreparedStatement pst2 = this.con.prepareStatement(queryDos);
				pst2.setInt(1, Integer.parseInt(codigoMedico));
				pst2.setInt(2, Integer.parseInt(codigoPaciente));
				pst2.execute();
				System.out.println("Insercion correcta en tabla Atiende A");
				this.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Error al ejecutar la query: " + queryUno);
				//e.printStackTrace();
				this.close();
				return false;
			}
			
		
	}
	
	public void seleccionarDatosPaciente(String codigoPaciente){
		this.conectar();
		String query = "SELECT MAX(CODIGO) FROM ;";
	}
	
	public String getSiguientePaciente(){
		String siguienteValor = null;
		this.conectar();
		String query = "SELECT MAX(CODIGO)+1 AS SIGUIENTE FROM PACIENTE;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				siguienteValor = rs.getString("SIGUIENTE");
			}
			this.close();
			return siguienteValor;
		} catch (Exception e){
			System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			return "ERROR";
		}
	}
	
	/**
	 * MEDICO 
	 * 
	 * */
	
	public String getSiguienteMedico(){
		String siguienteValor = null;
		this.conectar();
		String query = "SELECT MAX(CODIGO)+1 AS SIGUIENTE FROM MEDICO;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				siguienteValor = rs.getString("SIGUIENTE");
			}
			this.close();
			return siguienteValor;
		} catch (Exception e){
			System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			return "ERROR";
		}
	}
	
	public ResultSet getMedicos(){
		this.conectar();
		String query = "SELECT * FROM MEDICO;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
			System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
		}
	}
	/*
	public String getNombreMedico(int idMedico){
		this.conectar();
		String query = "SELECT nombre FROM MEDICO where id=?;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1,idMedico);
			ResultSet rs = pst.executeQuery();
			return rs.getString("nombre");			
		} catch (Exception e){
			System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
		}
	}
	*/
	public boolean insertarMedico(String codigoPaciente, String nombre, String codigoMedico, String diagnostico){
		this.conectar();
		String query = "INSERT INTO paciente (codigo,nombre,diagnostico) values(?,?,?);";
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoPaciente));
			pst.setString(2, nombre);
			pst.setString(3, diagnostico);			
			pst.execute();
			System.out.println("Insercion correcta");
			this.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			this.close();
			return false;
		}
	}
}

