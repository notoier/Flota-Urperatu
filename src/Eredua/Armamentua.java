package Eredua;
import java.util.*;

public class Armamentua {

	private ArrayList<Arma> armaKol;
	
	
	public Armamentua() {
		this.armaKol = new ArrayList<Arma>();
		hasieratuArmamentua(5, 125, 5, 2); //Ajustar cantidad, esta es de prueba
	}

	public void hasieratuArmamentua(int misilKop, int bonbaKop, int radarKop, int ezkutuKop){
		
		//Supongamos que se empieza con dos de cada arma
		armaKol.add(new Misil(misilKop,"Misil"));
		armaKol.add(new Bonba(bonbaKop,"Bonba"));
		armaKol.add(new Radar(radarKop,"Radar"));
		armaKol.add(new Ezkutu(ezkutuKop,"Ezkutu")); 
	}

	private Iterator<Arma> getIterator() {
		return armaKol.iterator();
	}

	public Arma billatuArma(final String arma){
		Iterator<Arma> itr = this.getIterator();
		boolean aurkitua = false;
		Arma a = null;
		while (itr.hasNext() && !aurkitua) {
			a = itr.next();
			switch(arma){

				case "Bonba":
					if (a instanceof Bonba) {
						aurkitua = true;
						break;
					} else {
						continue;
					}
					
				case "Misil":
					if (a instanceof Misil){
						aurkitua = true;
						break;
					} else {
						continue;
					}

				case "Radar":
					if (a instanceof Radar){
						aurkitua = true;
						break;
					} else {
						continue;
					}
				case "Ezkutu":
					if (a instanceof Ezkutu){
						aurkitua = true;
						break;
					} else {
						continue;
					}
				default:
					
					a = null;
					break;	
			}
		}
		return a;	
	}

	protected int getBombaKop() {
		return billatuArma("Bonba").getKopurua();
	}

	protected int getMisilKop(){
		return billatuArma("Misil").getKopurua();
	}

	protected int getEzkutuKop(){
		return billatuArma("Ezkutu").getKopurua();
	}

	protected int getRadarKop(){
		return billatuArma("Radar").getKopurua();
	}
	
}
