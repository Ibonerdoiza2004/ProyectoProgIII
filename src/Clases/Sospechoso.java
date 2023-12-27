package Clases;

import java.util.Random;

import javax.swing.ImageIcon;

enum Sospechosos{Miss_Scarlett, Colonel_Mustard, Mrs_White, Mr_Green, Mrs_Peacock, Profesor_Plum}

public class Sospechoso extends Asesinato {
	protected Random r;
	protected Sospechosos nombre;

	public Sospechosos getNombre() {
		return nombre;
	}

	public void setNombre(Sospechosos nombre) {
		this.nombre = nombre;
	}
	
	
	public Sospechoso(Sospechosos nombre, boolean implicado, ImageIcon foto) {
		super(implicado, foto);
		this.nombre = nombre;
	}


	public Sospechoso() {
		super();
		Sospechosos[] sospechoso = Sospechosos.values();
		int random = (int)(Math.random()*7);
		this.nombre = sospechoso[random];
		foto = new ImageIcon();
		implicado = false;
	}

	@Override
	public String toString() {
		return "Sospechoso [nombre=" + nombre + ", implicado=" + implicado + "]";
	}

	
	
	

	
	
	
	
	
	

}
