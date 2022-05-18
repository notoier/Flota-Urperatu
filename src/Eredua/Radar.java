package Eredua;

import java.awt.Color;

public class Radar extends Arma {
	
	public static final Color URA_KOLOREA = new Color(69,254,0);
	public static final Color ITSASONTZIA_KOLOREA = new Color(11,107,2);
	

	public Radar(int pKopuru, String pDeskripzio) {
		super(pKopuru, pDeskripzio);
		this.armPort = new RadarPortaera();
	}

	public String getDeskripzioa(){
		return super.getDeskripzioa();
	}

}