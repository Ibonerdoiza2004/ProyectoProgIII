package Clases;

import java.util.Random;

import javax.swing.ImageIcon;

public class Personaje { //TODO esta clase sirve para los 6 personajes que el jugador puede elegir
	
	enum nombrePersonaje{MissScarlett, ColonelMustard, MrWhite, MrGreen, MrsPeacock, ProfesoraPlum};
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
		this.nombre = nombrePersonaje.values()[r.nextInt((int)nombrePersonaje.values().length)].toString();
		this.imgRight = null;
		this.imgLeft = null;
		this.imgFront = null;
		this.imgBack = null;
	}
	
	public Personaje(ImageIcon imgRight, ImageIcon imgLeft, ImageIcon imgFront, ImageIcon imgBack) {
		this.nombre = nombrePersonaje.values()[r.nextInt((int)nombrePersonaje.values().length)].toString();;
		this.imgRight = imgRight;
		this.imgLeft = imgLeft;
		this.imgFront = imgFront;
		this.imgBack = imgBack;
	}
	
	
	
	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + ", imgRight=" + imgRight + ", imgLeft=" + imgLeft
				+ ", imgFront=" + imgFront + ", imgBack=" + imgBack + "]";
	}
	//Para probar que se coge un nombre de Personaje aleatorio 
//	public static void main(String[] args) {
//		Personaje p = new Personaje();
//		System.out.println(p.toString());
//	}
	
}
