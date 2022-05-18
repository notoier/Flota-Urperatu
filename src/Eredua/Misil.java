package Eredua;

public class Misil extends Arma {
	
	public Misil(int pKopuru, String pDeskripzio) {
		super(pKopuru, pDeskripzio);
		this.armPort = new MisilPortaera();
	}

	public String getDeskripzioa(){
		return super.getDeskripzioa();
	}
}