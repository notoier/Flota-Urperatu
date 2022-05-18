package Eredua;

public abstract class Jokalaria {

	private Tableroa nireTableroa;
	private Tableroa aurkariarenTableroa;
	private ItsasontzienZerrenda itsasontziZerr;
	
	public Jokalaria() {
		
		this.nireTableroa= new Tableroa();
		this.aurkariarenTableroa= new Tableroa();
		this.itsasontziZerr = new ItsasontzienZerrenda(150,10); //Supongamos que empiezas con 100 de dinero y los 10 barcos 
		
	}

	protected Tableroa getNireTableroa(){
		return this.nireTableroa;
	}

	public Tableroa getAurkariarenTableroa(){
		return this.aurkariarenTableroa;
	}

	protected void gehituItsasontzia(Itsasontzia pItsasontzia){
		itsasontziZerr.gehituItsasontzia(pItsasontzia);
	}

    public ItsasontzienZerrenda getItsasontzienZerrenda() {
		return this.itsasontziZerr;
    }

	//Cambia el tablero por otro que le asignemos
	protected void aldatuNireTableroa(Tableroa tableroa){ 
		this.nireTableroa = tableroa;
	}
}