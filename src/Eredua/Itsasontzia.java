package Eredua;

public abstract class Itsasontzia {

	private int tiroKop;
	private int babespena;
	private int eslora;
	private Egoera egoera;
	private int[] proa = new int[2];
	private int[] popa = new int[2];
	
	public Itsasontzia(int pEslora, int[] hasCoord, int[] amCoord) {
		this.egoera=Egoera.NORMAL;
		this.popa=amCoord;
		this.proa=hasCoord;
		this.eslora = pEslora;
		this.tiroKop = pEslora;
		this.babespena = 2;
	}

	protected int getEslora() {
		return eslora;
	}

	public Egoera getEgoera() {
		return egoera;
	}

	public int[] getProa() {
		return proa;
	}

	public int[] getPopa() {
		return popa;
	}

	protected void tiroaJasan(boolean misila){
		//Si tiene un escudo
		if(this.egoera == Egoera.BABESTUTA){
			//Si le ha dado un misil
			if(misila){
				this.babespena = 0;

			//Si le ha dado un bomba
			} else { 
				this.babespena--;
			}

			if(babespena <= 0){
				//Si le habian dado antes
				if(tiroKop < eslora){
					this.egoera = Egoera.UKITUTA;
				} else {
					this.egoera = Egoera.NORMAL;
				}
			}
			
		//Si no tiene un escudo
		} else { 
			//Si le ha dado un misil
			if(misila){
				this.tiroKop = 0;

			//Si le ha dado un bomba
			} else { 
				this.tiroKop--;
			}
		}
	}

	public void setEgoera(Egoera egoeraBerria){ 
		if(egoeraBerria == Egoera.BABESTUTA){
			babespena = eslora;
		}
		egoera = egoeraBerria;
	}

    public int getTiroKop() {
        return tiroKop;
    }

    public void konpondu() {

		this.tiroKop++;

		//Si el barco se ha recuperado completamente deja de estar 'Ukituta'
		if(this.tiroKop == this.eslora){
			this.egoera = Egoera.NORMAL;
		}
    }
}