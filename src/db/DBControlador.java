package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlpac.Paciente;
import utilidades.ComboBoxItem;


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
	    //System.out.println("Base de datos abierta correctamente");
	}

	public void close(){    
		try {
	     // this.con.close();
	    //  System.out.println("Base de datos cerrada correctamente");
	    } catch ( Exception e ) {
	    	//System.out.println("Error al cerrar la base de datos");
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
			//	System.out.println("Insercion correcta en tabla Paciente");
				this.close();
				return true;
			} catch (SQLException e) {
			//	System.out.println("Error al ejecutar la query: " + query);
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
			//System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			return "ERROR";
		}
	}
	
	public ResultSet getPacientes(){
		this.conectar();
		String query = "SELECT * FROM paciente;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
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
		//	System.out.println("Error al ejecutar la query: " + query);
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
		//	System.out.println("Error al ejecutar la query: " + query);
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
	public boolean insertarMedico(String codigoMedico, String nombre, Object esp1, Object esp2, Object esp3){
		this.conectar();

		String query = "INSERT INTO medico (codigo,nombre) values(?,?);";
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoMedico));
			pst.setString(2, nombre);			
			pst.execute();
			
		//	System.out.println("Medico insertado");
			
			if(!esp1.equals("")){
				ComboBoxItem cbi1 = (ComboBoxItem)esp1;
				this.insertarEspecialidad(codigoMedico, cbi1.getValue());
			}
			if(!esp2.equals("")){
				ComboBoxItem cbi2 = (ComboBoxItem)esp2;
				this.insertarEspecialidad(codigoMedico, cbi2.getValue());
			}
			if(!esp3.equals("")){
				ComboBoxItem cbi3 = (ComboBoxItem)esp3;
				this.insertarEspecialidad(codigoMedico, cbi3.getValue());
			}
				
			this.close();
			return true;
		} catch (SQLException e) {
			//System.out.println("Error al ejecutar la query: " + query);
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
			//	System.out.println("Especialidad insertada");
				this.close();
				return true;
			} catch (SQLException e) {
			//	System.out.println("Error al ejecutar la query: " + query);
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
	public boolean insertarDiagnostico(Object p, Object m, String diagnostico, String fecha){
		this.conectar();
		String query = "INSERT INTO atiende_a (medico,paciente,diagnostico,fecha) values(?,?,?,?);";
		
		ComboBoxItem medico = (ComboBoxItem) p;
		ComboBoxItem paciente = (ComboBoxItem) m;
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(medico.getValue()));
			pst.setInt(2, Integer.parseInt(paciente.getValue()));
			pst.setString(3, diagnostico);	
			pst.setString(4, fecha);
			pst.execute();
		//	System.out.println("Diagnostico insertado");
			this.close();
			return true;
		} catch (SQLException e) {
		//	System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			this.close();
			return false;
		}
					
	}
	
	public ResultSet getEspecialidades(){
		this.conectar();
		String query = "SELECT * FROM especialidad;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getPacientesPorMedico(String codigoMedico){
		this.conectar();
		String query = "SELECT p.codigo, p.nombre as paciente,a.fecha,a.diagnostico FROM Medico m INNER JOIN Atiende_a a ON m.codigo = a.medico INNER JOIN Paciente p ON a.paciente = p.codigo WHERE m.codigo = ? order by m.codigo";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoMedico));
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getEspecialidadesPorMedico(String codigoMedico){
		this.conectar();
		String query = "SELECT e.codigo, e.nombre as especialidad FROM  se_especializa_en s INNER JOIN especialidad e ON e.codigo = s.especialidad WHERE s.medico = ? order by s.medico";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoMedico));
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * USUARIOS
	 * 
	 * */
	
	public boolean validarUsuarioYPassword(String usuario, String password){
		this.conectar();
		String query = "select count(*) as filas from usuario where codigo = ? and password = ?;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, usuario);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			/*
			 * Si la cantidad de filas es > 0 es porque hay coincidencia
			 * */
			if(Integer.parseInt(rs.getString("filas"))>0){
				return true;
			} else {
				return false;
			}
				
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			return false;
		}
	}
	
}

