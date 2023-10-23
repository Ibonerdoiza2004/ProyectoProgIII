package Clases;

import java.util.Random;

import javax.swing.ImageIcon;

enum sospechosos{Andoni, Jenny, Carlos, Asier, Nekane, Iñaki}

public class Sospechoso extends Asesinato {
	protected Random r;
	protected sospechosos nombre;

	public sospechosos getNombre() {
		return nombre;
	}

	public void setNombre(sospechosos nombre) {
		this.nombre = nombre;
	}
	
	
	public Sospechoso(sospechosos nombre, boolean implicado, ImageIcon foto) {
		super(implicado, foto);
		this.nombre = nombre;
	}


	public Sospechoso() {
		super();
		sospechosos[] sospechoso = sospechosos.values();
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
