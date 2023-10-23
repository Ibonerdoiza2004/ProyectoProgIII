package Clases;

import javax.swing.ImageIcon;

public class Asesinato {
	
	protected boolean implicado;
	protected ImageIcon foto;
	
	public boolean isImplicado() {
		return implicado;
	}
	public void setImplicado(boolean implicado) {
		this.implicado = implicado;
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
		this.foto = null;
	}
	
	@Override
	public String toString() {
		return "Asesinato [implicado=" + implicado + "]";
	}
}
