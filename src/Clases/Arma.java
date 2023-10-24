package Clases;

import java.util.Random;

import javax.swing.ImageIcon;

enum Armas{cuchillo, candelabro, pistola, porra, cuerda, llaveInglesa};

public class Arma extends Asesinato{
	protected Armas nombreArma;

	public Arma() {
		super();
		Random random = new Random();
		int indiceArma = random.nextInt(Armas.values().length);
		nombreArma = Armas.values()[indiceArma];
	}
	

	public Arma(Armas nombreArma, boolean bool, ImageIcon foto) {
		super(bool, foto);
		this.nombreArma = nombreArma;
		
	}


	@Override
	public String toString() {
		return "Arma [nombreArma=" + nombreArma + ", implicado=" + implicado + ", foto=" + foto + "]";
	}
	
//	public static void main(String[] args) {
//		Arma arma1 = new Arma();
//		Arma arma2 = new Arma();
//		System.out.println(arma1 + "" +arma2);
//	}
}
