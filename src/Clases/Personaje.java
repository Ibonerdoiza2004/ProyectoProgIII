package Clases;

import java.util.Random;

import javax.swing.ImageIcon;

public class Personaje { //TODO esta clase sirve para los 6 personajes que el jugador puede elegir
	
	enum NombrePersonaje{Miss_Scarlett, Colonel_Mustard, Mr_White, Mr_Green, Mrs_Peacock, Profesora_Plum};
	protected Random r = new Random();
	//Atributos
	protected String nombre;
	protected ImageIcon imgRight;
	protected ImageIcon imgLeft;
	protected ImageIcon imgFront;
	protected ImageIcon imgBack;
	
	//Setters y Getters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ImageIcon getImgRight() {
		return imgRight;
	}
	public void setImgRight(ImageIcon imgRight) {
		this.imgRight = imgRight;
	}
	public ImageIcon getImgLeft() {
		return imgLeft;
	}
	public void setImgLeft(ImageIcon imgLeft) {
		this.imgLeft = imgLeft;
	}
	public ImageIcon getImgFront() {
		return imgFront;
	}
	public void setImgFront(ImageIcon imgFront) {
		this.imgFront = imgFront;
	}
	public ImageIcon getImgBack() {
		return imgBack;
	}
	public void setImgBack(ImageIcon imgBack) {
		this.imgBack = imgBack;
	}
	
	public Personaje() {
		NombrePersonaje personaje = NombrePersonaje.values()[r.nextInt((int)NombrePersonaje.values().length)];
		this.nombre = personaje.toString();
		setImagenes(personaje);
	}
	
	public Personaje(NombrePersonaje nombre) {
		this.nombre = nombre.toString();
		setImagenes(nombre);
		
	}
	
	public void setImagenes(NombrePersonaje nombre) {
		switch(nombre) {
		case Colonel_Mustard:
			this.imgRight = null; //Aqui añadir las imagenes de cada personaje
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		case Miss_Scarlett:
			this.imgRight = null;
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		case Mr_Green:
			this.imgRight = null;
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		case Mr_White:
			this.imgRight = null;
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		case Mrs_Peacock:
			this.imgRight = null;
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		case Profesora_Plum:
			this.imgRight = null;
			this.imgLeft = null;
			this.imgFront = null;
			this.imgBack = null;
		}
	}
	
	@Override
	public String toString() {
		return " Personaje [nombre=" + nombre + ", imgRight=" + imgRight + ", imgLeft=" + imgLeft
				+ ", imgFront=" + imgFront + ", imgBack=" + imgBack + "]";
	}
	//Para probar que se coge un nombre de Personaje aleatorio 
//	public static void main(String[] args) {
//		Personaje p = new Personaje();
//		System.out.println(p.toString());
//	}
	
}
