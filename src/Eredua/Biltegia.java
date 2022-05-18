package Eredua;

public class Biltegia {

	private static Biltegia nBiltegia=null;
	private Armamentua armamentua;

	private Biltegia() {
		this.armamentua= new Armamentua();
		armamentua.hasieratuArmamentua(25, 40, 15, 10); //Supongamos que el biltegia empieza con 10 de cada arma
	}
	
	public static Biltegia getNireBiltegia() {
		if (Biltegia.nBiltegia== null) {
			Biltegia.nBiltegia = new Biltegia();
		}
		return (Biltegia.nBiltegia);
	}

    public int getBombaKop() {
        return armamentua.getBombaKop();
    }

	public int getMisilKop() {
		return armamentua.getMisilKop();
	}

	public int getEzkutuKop() {
		return armamentua.getEzkutuKop();
	}

	public int getRadarKop(){
		return armamentua.getRadarKop();
	}
}