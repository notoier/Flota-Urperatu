package Eredua;

import java.util.Observable;

import javax.swing.JOptionPane;

import Bista.Denda;

public abstract class Arma extends Observable {

	private int kopurua;
	private String deskripzioa;
	protected ArmaPortaera armPort;

	protected Arma(int pKopuru, String pDeskripzio){
		this.kopurua=pKopuru;
		this.deskripzioa=pDeskripzio;
		this.addObserver(Denda.denda);
	}

	public boolean kopNahikoa(){
		return this.kopurua >= 1;
	}

	public void kopuruaAldatu(final int armaMota, boolean jokalaria) { // 1 = Bonba, 2 = Misil, 3 = Escudo, 4 = Radar
		this.kopurua = this.kopurua - 1; 
		//Actualizamos el panel solo si el cambio ha sido del jugador
		if(jokalaria){ 
			setChanged();
			notifyObservers(armaMota);
		}
	}

	public void erosi(int armaMota, int prezioa, boolean jokalaria){
		if(jokalaria){
			if(Player.getNirePlayer().getItsasontzienZerrenda().diruNahikoa(prezioa)){
				kopurua++;
				setChanged();
				notifyObservers(armaMota);
			} else {
				JOptionPane.showMessageDialog(Denda.denda, "Ezin duzu gehiago erosi");
			}
		} else {
			if(CPU1.getNireCPU1().getItsasontzienZerrenda().diruNahikoa(prezioa)){
				kopurua ++;
			}
		}
	}

	public boolean tiroEgin(final int x, final int y, Tableroa aurkariarenTableroa, Tableroa nireAurkariarenTableroa, boolean jokalaria, int armaMota){
		boolean ondo = false;
		if(this.kopNahikoa()){
			this.kopuruaAldatu(armaMota, jokalaria);
				this.armPort.akzioaBurutu(x, y, aurkariarenTableroa, nireAurkariarenTableroa, jokalaria);
			ondo = true;
		} 
		return ondo;
	}

	public int getKopurua(){
		return this.kopurua;
	}

	protected String getDeskripzioa(){
		return this.deskripzioa;
	}
}
