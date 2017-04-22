package controlpac;

public class Paciente {
	
	private int codigo, codigoMedico;
	private String nombre;
	
	
	public Paciente(int codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getCodigoMedico() {
		return codigoMedico;
	}


	public void setCodigoMedico(int codigoMedico) {
		this.codigoMedico = codigoMedico;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
