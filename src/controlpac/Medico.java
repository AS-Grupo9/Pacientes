package controlpac;

public class Medico {

	private int codigo;
	private String nombre, especialidad;
	
	public Medico (int codigo, String nombre, String especialidad)
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
}
