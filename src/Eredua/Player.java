package Eredua;

public class Player extends Jokalaria {

	private static Player nPlayer;
	private boolean nireTxanda;
	private boolean radarraErabiliDu;
	private boolean ezkutuaErabiliDu;
	
	private Player() {
		super();
		nireTxanda = true;
		radarraErabiliDu = false;
	}
	
	public static Player getNirePlayer() {
		if (Player.nPlayer== null) {
			Player.nPlayer = new Player();
		}
		return (Player.nPlayer);
	}

	public Tableroa getNireTableroa() {
		return super.getNireTableroa();
	}
	
	
	public boolean nireTxandaDa() {
		
		return nireTxanda;
	}
	
	public void txandaBukatu() {
		radarraErabiliDu = false;
		ezkutuaErabiliDu = false;
		nireTxanda = false;
	}
	
	public void txandaHasi() {
		
		nireTxanda = true;
		
	}

	public boolean radarraErabiliDu(){
		return this.radarraErabiliDu;
	}

	public void setRadarra(Boolean pRadar){
		this.radarraErabiliDu = pRadar;
	}

	public boolean ezkutuaErabiliDu(){
		return this.ezkutuaErabiliDu;
	}

	public void setEzkutua(Boolean pRadar){
		this.ezkutuaErabiliDu = pRadar;
	}

	public ItsasontzienZerrenda getiItsasontzienZerrenda() {
		return super.getItsasontzienZerrenda();
	}
}