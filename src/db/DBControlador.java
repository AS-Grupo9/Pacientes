package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlpac.Paciente;
import utilidades.Loggin;

public class DBControlador {

	private String bdd = "laureles.db";
	Connection connect;

	public void connect() {
		try {
			connect = DriverManager.getConnection("jdbc:sqlite:" + bdd);
			if (connect != null) {
				// Loggin.getInstance().info("Conectado");
			}
		} catch (SQLException ex) {
			// Loggin.getInstance().error("No se ha podido conectar a la base de datos\n" + ex.getMessage());
		}
	}

	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			Loggin.getInstance().error("Error al cerrar conexion: " + e.getMessage());
		}
	}

	public boolean registrarPaciente(Paciente p) {
		ResultSet result = null;
		int idPaciente;
		
		try {
			PreparedStatement st = connect.prepareStatement("select * from paciente where codigo= ?");
			st.setString(1, p.getCodigo());
			result = st.executeQuery();

			if (!result.next()) {

				// 1) INSERTO EL USUARIO
				PreparedStatement st1 = connect.prepareStatement("insert into paciente (nombre, codmedico) values (?,?)");
				st1.setString(1, user.getNombre_usuario().toUpperCase());
				st1.setString(2, user.getPassword_usuario());
				st1.execute();
				ResultSet rs = st1.getGeneratedKeys();
				if(rs != null){
				
					// 2) OBTENGO SU ID
					st1 = connect.prepareStatement("select ID_USUARIO from USUARIO where NOMBRE = ?");
					st1.setString(1, user.getNombre_usuario().toUpperCase());
					result = st1.executeQuery();
					idPaciente = result.getInt("ID_USUARIO");
					
					// 3) GENERO UN PERSONAJE CON ESE ID
					st1 = connect.prepareStatement(
							"insert into PERSONAJE (ID_PERSONAJE, ID_USUARIO, NIVEL, EXPERIENCIA, VIDA, ENERGIA, ATAQUE, DEFENSA, "
									+ "MAGIA, PUNTOS, DESTREZA, VELOCIDAD, POTENCIA  ) values ("+idPersonaje+","+idPersonaje+",1,0,100,100,0,0,0,0,0,0,0)");
					st1.execute();
					rs = st1.getGeneratedKeys();
					if(rs != null){
						// 4) ACTUALIZO LA TABLA USUARIO CON EL ID DEL PERSONAJE (MISMO QUE EL USUARIO)
						
						st1 = connect.prepareStatement("UPDATE USUARIO SET ID_PERSONAJE= "+ idPersonaje +" WHERE ID_USUARIO = "+ idPersonaje);
						st1.execute();
						rs = st1.getGeneratedKeys();
						
						if(rs != null){
							return true;	
						}
						else 
							return false;
					}
					else 
						return false;				
				}
				else
					return false;
			} else {			
				return false;
			}
		} catch (SQLException e) {
			Loggin.getInstance().error("Error al registrar usuario: " + e.getMessage());
			return false;
		}
	}
	
	
	
	

	/*public boolean registrarPersonaje(Personaje p1, Usuario u1) {

		try {

			PreparedStatement st = connect.prepareStatement(
					"insert into PERSONAJE (ID_PERSONAJE, ID_USUARIO, NIVEL, EXPERIENCIA, VIDA, ENERGIA, ATAQUE, DEFENSA, "
							+ "MAGIA, PUNTOS, DESTREZA, VELOCIDAD, POTENCIA, RAZA, CASTA) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setInt(1, u1.getIdPj());
			st.setInt(2, u1.getIdPj());
			st.setInt(3, p1.getNivel());
			st.setInt(4, p1.getExperiencia());
			st.setInt(5, p1.getVida());
			st.setInt(6, p1.getEnergia());
			st.setInt(7, p1.getAtaque());
			st.setInt(8, p1.getDefensa());
			st.setInt(9, p1.getMagia());
			st.setInt(10, p1.getPuntos());
			st.setInt(11, p1.getDestreza());
			st.setInt(12, p1.getVelocidad());
			st.setInt(13, p1.getPotencia());
			st.setString(14, p1.getClase().getNombre());
			st.setString(15, p1.getRaza());

			st.execute();

			ResultSet rs = st.getGeneratedKeys();

			if (rs != null && rs.next()) {
				int idPersonaje = rs.getInt(1);

				p1.setIdPersonaje(idPersonaje);////

				PreparedStatement st3 = connect
						.prepareStatement("update USUARIO set ID_PERSONAJE=? where NOMBRE=? and CONTRASENA=?");
				st3.setInt(1, idPersonaje);
				st3.setString(2, u1.getNombre_usuario());
				st3.setString(3, u1.getPassword_usuario());
				st3.execute();
				if (this.registrarInventarioMochila(idPersonaje))
					return true;
			}
			return false;

		} catch (SQLException e) {
			Loggin.getInstance().error(e.getSQLState());
			return false;
		}

	}

	 A MODIFICAR 
	public boolean registrarInventarioMochila(int idInventarioMochila) {
		ResultSet result = null;
		try {

			PreparedStatement st1 = connect.prepareStatement(
					"insert into inventario(idInventario,manos1,manos2,pie,cabeza,pecho,accesorio) values (?,-1,-1,-1,-1,-1,-1)");
			st1.setInt(1, idInventarioMochila);
			PreparedStatement st2 = connect.prepareStatement(
					"insert into mochila(idMochila,item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,item14,item15,item16,item17,item18,item19,item20) values(?,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1)");
			st2.setInt(1, idInventarioMochila);

			st1.execute();
			st2.execute();

			PreparedStatement st3 = connect.prepareStatement("update personaje set idInventario=? where idPersonaje=?");
			st3.setInt(1, idInventarioMochila);
			st3.setInt(2, idInventarioMochila);

			PreparedStatement st4 = connect.prepareStatement("update personaje set idMochila=? where idPersonaje=?");
			st4.setInt(1, idInventarioMochila);
			st4.setInt(2, idInventarioMochila);

			st3.execute();
			st4.execute();
			return true;

		} catch (SQLException e) {
			Loggin.getInstance().error(e.getMessage());
			return false;
		}
	}

	public boolean loguearUsuario(Usuario user) {
		ResultSet result = null;
		try {
			PreparedStatement st = connect
					.prepareStatement("select * from USUARIO where NOMBRE = ? AND CONTRASENA = ? ");
			st.setString(1, user.getNombre_usuario().toUpperCase());
			st.setString(2, user.getPassword_usuario());
			result = st.executeQuery();

			if (result.next()) {
				return true;
			}

			return false;

		} catch (SQLException e) {
			Loggin.getInstance().error(e.getMessage());
			return false;
		}
	}

	// OJO CON ESTA QUERY QUE BORRA TODOS LOS REGISTROS DE LA TABLA USUARIO
	public void borrarRegistros() {
		try {
			PreparedStatement st = connect.prepareStatement("delete from USUARIO");
			st.executeQuery();
		} catch (SQLException e) {
			Loggin.getInstance().error("Error deleteUsuario: " + e.getMessage());
		}
	}

	public MensajePersonaje getPersonajeEdit(Usuario user) {
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("select ID_USUARIO from USUARIO where NOMBRE = ?");
			st.setString(1, user.getNombre_usuario().toUpperCase());
			result = st.executeQuery();
			int idPersonaje = result.getInt("ID_USUARIO");

			st = connect.prepareStatement("SELECT P.RAZA,P.CASTA FROM PERSONAJE P WHERE p.id_usuario=?");
			st.setInt(1, idPersonaje);
			result = st.executeQuery();
			String casta = result.getString("CASTA");
			String raza = result.getString("RAZA");

			MensajePersonaje personaje = new MensajePersonaje();

			personaje.setIdPersonaje(idPersonaje);
			personaje.setRaza(raza);
			personaje.setCasta(casta);

			return personaje;

		} catch (SQLException ex) {
			Loggin.getInstance().error("Error en conector: " + ex.getMessage());
		}
		return new MensajePersonaje(-1, null, null, -1, -1, -1, null);
	}
	
	public MensajePersonaje getPersonaje(Usuario user) {
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("select ID_USUARIO from USUARIO where NOMBRE = ?");
			st.setString(1, user.getNombre_usuario().toUpperCase());
			result = st.executeQuery();
			int idUsuario = result.getInt("ID_USUARIO");

			st = connect.prepareStatement("SELECT P.RAZA,P.CASTA FROM PERSONAJE P WHERE p.id_usuario=?");
			st.setInt(1, idUsuario);
			result = st.executeQuery();
			String casta = result.getString("CASTA");
			String raza = result.getString("RAZA");

			MensajePersonaje personaje = new MensajePersonaje();

			personaje.setIdPersonaje(idUsuario);
			personaje.setRaza(raza);
			personaje.setCasta(casta);

			return personaje;

		} catch (SQLException ex) {
			Loggin.getInstance().error("Error en conector: " + ex.getMessage());
		}
		return new MensajePersonaje(-1, null, null, -1, -1, -1, null);
	}

	public boolean actualizarElPersonaje(MensajePersonaje miPersonaje) {
		int result = 0;
		try {
			PreparedStatement st = connect.prepareStatement("update PERSONAJE set CASTA=? , RAZA=? where ID_USUARIO = ?");
			st.setString(1, miPersonaje.getCasta().toUpperCase());
			st.setString(2, miPersonaje.getRaza().toUpperCase());
			st.setInt(3, miPersonaje.getIdPersonaje());
			result = st.executeUpdate();
			return result==1;
		} catch (SQLException ex) {
			Loggin.getInstance().error("Error en actualizarElPersonaje: " + ex.getMessage());
			return false;
		}
	}*/
}
