package Clases;

import java.util.Random;

enum Armas{puñal, candelabro, pistola, porra, cuerda, llaveInglesa};

public class Arma extends Asesinato{
	protected Armas nombreArma;

	public Arma() {
		super();
		Random random = new Random();
		int indiceArma = random.nextInt(Armas.values().length);
		nombreArma = Armas.values()[indiceArma];
	}

	@Override
	public String toString() {
		return "Arma [nombreArma=" + nombreArma + ", implicado=" + implicado + ", foto=" + foto + "]";
	}
	
//	public static void main(String[] args) {
//		Arma arma1 = new Arma();
//		System.out.println(arma1);
//	}
}
