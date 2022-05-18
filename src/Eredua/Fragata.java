package Eredua;

public class Fragata extends Itsasontzia {

	public Fragata(int[] hasCoord, int[] amCoord) {
		super(1, hasCoord, amCoord);
	
	}
	
	public void setEgoera(Egoera egoera){ 
		super.setEgoera(egoera);
	}

	public void tiroaJasan(boolean misila){
		super.tiroaJasan(misila);
	}

	public int getTiroKop() {
		return super.getTiroKop();
	}

	public int[] getProa() {
		return super.getProa();
	}

	public int[] getPopa() {
		return super.getPopa();
	}

}
