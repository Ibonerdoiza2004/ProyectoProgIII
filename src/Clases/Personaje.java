package Clases;

import java.util.Random;

import javax.swing.ImageIcon;
enum NombrePersonaje{Rojo, Amarillo, Negro, Verde, Azul, Morado};
public class Personaje {
	
	protected Random r = new Random();
	//Atributos
	protected NombrePersonaje nombre;
	
	//Setters y Getters
	public NombrePersonaje getNombre() {
		return nombre;
	}
	public void setNombre(NombrePersonaje nombre) {
		this.nombre = nombre;
	}
	
	
	public Personaje() {
		NombrePersonaje personaje = NombrePersonaje.values()[r.nextInt((int)NombrePersonaje.values().length)];
		this.nombre = personaje;
	}
	
	public Personaje(NombrePersonaje nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return " Personaje [nombre=" + nombre + "]";
	}
	//Para probar que se coge un nombre de Personaje aleatorio 
//	public static void main(String[] args) {
//		Personaje p = new Personaje();
//		System.out.println(p.toString());
//	}
	
}
