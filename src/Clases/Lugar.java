package Clases;

import javax.swing.ImageIcon;


enum Sitio{CERO_UNO, BAÑO, SALA_DE_ORDENADORES, CAFETERIA, LABORATORIO, DECANATO, TREINTA_Y_TRES, GIMNASIO, CAMPO_DE_FUTBOL, CLAUSTRO}
public class Lugar extends Asesinato{
	
	protected Sitio nombre;
	
	public Sitio getNombre() {
		return nombre;
	}

	public void setNombre(Sitio nombre) {
		this.nombre = nombre;
	}

	public Lugar(Sitio nombre, boolean implicado, ImageIcon foto) {
		super(implicado, foto);
		this.nombre = nombre;
	}
	public Lugar() {
		super(); 
		Sitio[] sitios = Sitio.values();
		int random = (int)(Math.random()*10);
		this.nombre = sitios[random];
		//Crear un hashmap con los nombres del enum y las fotos correspondientes
		foto = new ImageIcon();
		implicado = false;
	}
	
	@Override
	public String toString() {
		return "Lugar [nombre=" + nombre + ", implicado="+ this.implicado +"]";
	}
	public static void main(String[] args) {
		Lugar lugar = new Lugar();
		System.out.println(lugar);
	}
	
}
