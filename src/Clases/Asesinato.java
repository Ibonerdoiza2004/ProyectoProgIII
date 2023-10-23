package Clases;

import javax.swing.ImageIcon;

public class Asesinato {
	
	protected boolean implicado;
	protected String nombre;
	protected ImageIcon foto;
	
	public boolean isImplicado() {
		return implicado;
	}
	public void setImplicado(boolean implicado) {
		this.implicado = implicado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ImageIcon getFoto() {
		return foto;
	}
	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}
	public Asesinato() {
		super();
		this.implicado = false;
		this.nombre = "";
		this.foto = null;
	}
	
	@Override
	public String toString() {
		return "Asesinato [implicado=" + implicado + ", nombre=" + nombre + "]";
	}
}
