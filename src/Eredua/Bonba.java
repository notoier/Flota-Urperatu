package Eredua;

public class Bonba extends Arma {
	
	public Bonba(int pKopuru, String pDeskripzio) {
		super(pKopuru, pDeskripzio);
		this.armPort = new BonbaPortaera();
	}

	public String getDeskripzioa(){
		return super.getDeskripzioa();
	}
}