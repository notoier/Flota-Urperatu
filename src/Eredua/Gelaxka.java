package Eredua;

import java.awt.*;

public class Gelaxka {

	public static final Color URA_COLOREA= new Color(37, 191, 230);
	public static final Color ITSASONTZIA_COLOREA = new Color(209, 59, 64);
	public static final Color EZKUTUA_COLOREA = new Color(219, 179, 114);
	public static final Color EZEZAGUNA_COLOREA = new Color(226, 208, 247);

	private final int x;
	private final int y;

	private Itsasontzia itsasontzia;

	private Mota mota;
	
	//Crea una casilla con el estado EZEZAGUNA de base
	public Gelaxka(int x, int y) {
		this.mota=Mota.URA;
		this.x = x;
		this.y = y;
	}

	public Mota getMota() {

		return this.mota;
	}

	public void aldatuMota(Mota pMota){
		this.mota = pMota;
	}

	public void itsasontziaSartu(Itsasontzia pItsasontzia){
		this.itsasontzia = pItsasontzia;
	}

	public void itsasontziaKendu(){
		this.itsasontzia = null;
	}

	public Itsasontzia getItsasontzia(){
		return this.itsasontzia;
	}

	public int[] getCoordenatuak(){
		int[] coord = {x,y};
		return coord;
	}
}