package Eredua;

import java.util.*;

public class ItsasontzienZerrenda {

	private ArrayList<Itsasontzia> itsasontziak;
	private Armamentua armamentua;
	private int dirua;
	private int ontziBiziKop;

	
	public ItsasontzienZerrenda(int pDirua, int pOntziBiziKop) {
		this.dirua=pDirua;
		this.ontziBiziKop=pOntziBiziKop;
		this.armamentua= new Armamentua();
		this.itsasontziak=new ArrayList<Itsasontzia>();
	}

	//Añade un barco a la flota
	public void gehituItsasontzia(Itsasontzia pItsasontzia){
		this.itsasontziak.add(pItsasontzia);
		
	}

	//Devuelve el armamento de la flota
	public Armamentua getArmamentua(){
		return this.armamentua;
	}

	//Si hay dinero suficiente lo resta y devuelve 'True', si no devuelve 'False'
	public boolean diruNahikoa(int pDirua){	
		if (this.dirua >= pDirua){
			this.dirua = this.dirua - pDirua;
			return true;
		} else {
			return false;
		}
	}

	public int getOtziBiziKop(){
		return this.ontziBiziKop;
	}

	public int getDirua(){
		return this.dirua;
	}
/*	
	private Iterator<Itsasontzia> getIterator(){
		
		return this.itsasontziak.iterator();
		
	}
	
	//Busca un barco que no este hundido
	public Itsasontzia ItsasontziEzHondoratuaLortu() {
		
		Iterator<Itsasontzia> itr = this.getIterator();
		boolean aurkitua = false;
		Itsasontzia i = null;
		
		while (itr.hasNext() && !aurkitua) {
			
			i = itr.next();
			
			if (i.getEgoera().equals(Egoera.NORMAL)) {
				
				aurkitua = true;
				
			}
			
		}
		
		if (!aurkitua) {
			
			i = null;
		}
		
		return i;
	}
*/	
	public Itsasontzia ItsasontziEzHondoratuaLortu2() {
		
		Random random = new Random();
		
		int ind = random.nextInt(this.itsasontziak.size());
		
		int kop = 0;
		
		boolean ondo = false;
		
		Itsasontzia i = null;
		
		while(kop < this.itsasontziak.size() && !ondo) {
			
			i = this.itsasontziak.get(ind);
			
			kop ++;
			
			if (i.getEgoera().equals(Egoera.NORMAL)) {
				
				ondo = true;
				
			}
			
		}
		
		if (!ondo) {
			
			i = null;
			
		}
		
		return i;
		
	}

	public Itsasontzia ItsasontziUkituaLortu() {

		Iterator<Itsasontzia> itr = this.getIterator();
		boolean aurkitua = false;
		Itsasontzia i = null;
		
		while (itr.hasNext() && !aurkitua) {
			
			i = itr.next();
			
			if (i.getEgoera().equals(Egoera.UKITUTA)) {
				
				aurkitua = true;
				
			}
			
		}
		
		if (!aurkitua) {
			
			i = null;
		}
		
		return i;
		
	}
	

	private Iterator<Itsasontzia> getIterator() {
		return this.itsasontziak.iterator();
	}

	//Le baja la "vida" al barco
    public Mota ukituItsasontzia(Tableroa tablero, Tableroa aurkaria, boolean misila, int x, int y, Mota pMota, boolean jokalaria) {
		
		Gelaxka gelaxka = tablero.getGelaxka(x,y);
		Itsasontzia itsasontzia = gelaxka.getItsasontzia();
		if (itsasontzia != null){
			//Si al barco aun le queda 'vida'
			if(itsasontzia.getTiroKop() > 0){  
					itsasontzia.tiroaJasan(misila);
			}

			if(itsasontzia.getTiroKop() < itsasontzia.getEslora()){
				itsasontzia.setEgoera(Egoera.UKITUTA);
			}

			//Si al barco no le queda 'vida' y no habia sido hundido previamente
			if(itsasontzia.getTiroKop() == 0 && itsasontzia.getEgoera() != Egoera.HONDORATUTA){
				itsasontzia.setEgoera(Egoera.HONDORATUTA);
				if(Player.getNirePlayer().nireTxandaDa()){
					Player.getNirePlayer().getItsasontzienZerrenda().gehituDirua(itsasontzia.getEslora() * 20);
				} else {
					CPU1.getNireCPU1().getItsasontzienZerrenda().gehituDirua(itsasontzia.getEslora() * 20);
				}
				pMota = Mota.ITSASONTZIA;
				int[] hasCoord = itsasontzia.getProa();
				int[] amCoord = itsasontzia.getPopa();

				aurkaria.gelaxkakAldatu(hasCoord, amCoord);

				this.ontziBiziKop--;

				if (jokalaria){

					CPU1.getNireCPU1().tiroakGehitu(itsasontzia);

				}

				tablero.itsasontziaKendu(itsasontzia);
			}
		}
		return pMota;
	}

	private void gehituDirua(int i) {
		this.dirua = dirua + i;
	}

    public void kenduDirua(int i) {
		this.dirua = dirua - i;
    }
}