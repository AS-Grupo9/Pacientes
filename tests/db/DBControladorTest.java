package db;

import org.junit.Test;

public class DBControladorTest {

	@Test
	public void pruebaConexion() throws ClassNotFoundException{
		DBControlador bdd = new DBControlador();
		bdd.conectar();
	}
}
