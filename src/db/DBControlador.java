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
	public boolean insertarPaciente(String codigoPaciente, String nombre){
		this.conectar();
		String query = "INSERT INTO paciente (codigo,nombre) values(?,?);";
		
		try{
				PreparedStatement pst = this.con.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(codigoPaciente));
				pst.setString(2, nombre);					
				pst.execute();
				System.out.println("Insercion correcta en tabla Paciente");
				this.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Error al ejecutar la query: " + query);
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
	public boolean insertarMedico(String codigoMedico, String nombre, String esp1, String esp2, String esp3){
		this.conectar();
		String query = "INSERT INTO medico (codigo,nombre) values(?,?);";
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoMedico));
			pst.setString(2, nombre);			
			pst.execute();
			System.out.println("Medico insertado");
			this.insertarEspecialidad(codigoMedico, esp1);
			this.insertarEspecialidad(codigoMedico, esp2);
			this.insertarEspecialidad(codigoMedico, esp3);			
			this.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			this.close();
			return false;
		}
	}
	
	public boolean insertarEspecialidad(String codigoMedico, String especialidad){
		this.conectar();
		String query = "INSERT INTO se_especializa_en (medico,especialidad) values(?,?);";
		if(especialidad != null) {
			try {
				PreparedStatement pst = this.con.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(codigoMedico));
				pst.setInt(2, Integer.parseInt(especialidad));
				pst.execute();
				System.out.println("Especialidad insertada");
				this.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Error al ejecutar la query: " + query);
				//e.printStackTrace();
				this.close();
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	/**
	 * DIAGNOSTICO
	 * */
	public boolean insertarDiagnostico(String codigoPaciente, String codigoMedico, String diagnostico){
		this.conectar();
		String query = "INSERT INTO atiende_a (medico,paciente,diagnostico) values(?,?,?);";
		
		
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoPaciente));
			pst.setInt(2, Integer.parseInt(codigoMedico));
			pst.setString(3, diagnostico);			
			pst.execute();
			System.out.println("Insercion correcta en tabla Atiende A");
			this.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			this.close();
			return false;
		}
					
	}
	
	public ResultSet getEspecialides(){
		this.conectar();
		String query = "SELECT * FROM especialidad;";
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
}

