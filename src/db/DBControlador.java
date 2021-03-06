package db;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controlpac.Paciente;
import utilidades.ComboBoxItem;


public class DBControlador
{
	private Connection con = null;
	private static final Logger log = LogManager.getLogger(Log4J.class.getName());
	public void conectar(){    
		try {
	      Class.forName("org.sqlite.JDBC");
	      this.con = DriverManager.getConnection("jdbc:sqlite:laureles.db");
	    } catch ( Exception e ) {
	    	log.error(e.getMessage());
	    	System.exit(0);
	    }
	  //  System.out.println("Base de datos abierta correctamente");
	}

	public void close(){    
		try {
	      this.con.close();
	     // System.out.println("Base de datos cerrada correctamente");
	    } catch ( Exception e ) {
	    	log.error(e.getMessage());
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
		System.out.println(codigoPaciente);
		System.out.println(nombre);
		try{
				PreparedStatement pst = this.con.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(codigoPaciente));
				pst.setString(2, nombre);					
				pst.execute();
				this.close();
			//	System.out.println("Insercion correcta en tabla Paciente");
				
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage());
				//	System.out.println("Error al ejecutar la query: " + query);
				//e.printStackTrace();
				this.close();
				return false;
			}	
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
			log.error(e.getMessage());
			//System.out.println("Error al ejecutar la query: " + query);
			//e.printStackTrace();
			this.close();
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
			log.error(e.getMessage());
			
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
			log.error(e.getMessage());
			this.close();
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
			log.error(e.getMessage());
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
			log.error(e.getMessage());
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
				log.error(e.getMessage());
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
			log.error(e.getMessage());
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
			log.error(e.getMessage());
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
			log.error(e.getMessage());
			return null;
		}
	}
	
	public ResultSet getEspecialidadesPorMedico(String codigoMedico){
		this.conectar();
		String query = "SELECT e.codigo, e.nombre as especialidad FROM se_especializa_en s INNER JOIN especialidad e ON e.codigo = s.especialidad WHERE s.medico = ? order by s.medico";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(codigoMedico));
			ResultSet rs = pst.executeQuery();
			return rs;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * USUARIOS
	 * 
	 * */
	
	public boolean validarUsuario(String usuario){
		this.conectar();
		String query = "select count(*) as filas from usuario where codigo = ? ;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, usuario);
			ResultSet rs = pst.executeQuery();
			
			/*
			 * Si la cantidad de filas es > 0 es porque hay coincidencia
			 * */
			int cantidad = Integer.parseInt(rs.getString("filas"));
			this.close();
			if(cantidad > 0){
				
				return true;
			} else {
				
				return false;
			}
				
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			log.error(e.getMessage());
			
			return false;
		}
	}
	
	
	public String obtenerSalt(String codigo){
		this.conectar();
		String query = "SELECT salt FROM usuario where codigo = ?;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, codigo);	
			ResultSet rs = pst.executeQuery();
			String salt = rs.getString("salt");
			this.close();
			
			if(salt == null){		
				return null;
			} else {
				return salt;
			}
			
			
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			log.error(e.getMessage());
			this.close();
			return null;
		}
	}
	
	public String obtenerPassword(String codigo){
		this.conectar();
		String query = "SELECT password FROM usuario where codigo = ?;";
		try{
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, codigo);	
			ResultSet rs = pst.executeQuery();
			String password = rs.getString("password");
			this.close();
			return password;
		} catch (Exception e){
		//	System.out.println("Error al ejecutar la query: " + query);
			e.printStackTrace();
			this.close();
			return null;
		}
	}
	
	public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
    	String generatedPassword = null;
    	    try {
    	         MessageDigest md = MessageDigest.getInstance("SHA-512");
    	         md.update(salt.getBytes("UTF-8"));
    	         byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
    	         StringBuilder sb = new StringBuilder();
    	         for(int i=0; i< bytes.length ;i++){
    	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    	         }
    	         generatedPassword = sb.toString();
    	        } 
    	       catch (NoSuchAlgorithmException e){
    	        e.printStackTrace();
    	       } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	    return generatedPassword;
    	}
	
	public boolean insertarUsuario(String codigo, String nombre, String pass){
		this.conectar();

		String salt = "oijsdljaslkj33";
		String nuevoPass = this.get_SHA_512_SecurePassword(pass, salt);
		String query = "INSERT INTO usuario (codigo,nombre,password,salt) values(?,?,?,?);";
		try {
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setString(1, codigo);
			pst.setString(2, nombre);
			pst.setString(3, nuevoPass);
			pst.setString(4, salt);
			pst.execute();
						
			this.close();
			return true;
		} catch (SQLException e) {
			//System.out.println("Error al ejecutar la query: " + query);
			log.error(e.getMessage());
			this.close();
			return false;
		}
	}
}

