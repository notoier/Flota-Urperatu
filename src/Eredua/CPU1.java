package Eredua;

import java.util.HashSet;
import java.util.Random;

import Bista.GUI;

public class CPU1 extends Jokalaria {

	private static CPU1 nCPU1;
	private HashSet<Integer> tiroak;
	private boolean radarraErabiliDu;
	
	private CPU1() {
		super();
		tiroak = new HashSet<Integer>();
	}
	
	public static CPU1 getNireCPU1() {
		if (CPU1.nCPU1== null) {
			CPU1.nCPU1 = new CPU1();
		}
		return (CPU1.nCPU1);
	}

	public HashSet<Integer> getTiroak() {
		return tiroak;
	}

	public boolean radarraErabiliDu(){
		return radarraErabiliDu;
	}

	public Tableroa getNireTableroa() {
		return super.getNireTableroa();
	}

	protected void aldatuNireTableroa(Tableroa tableroa) {
		super.aldatuNireTableroa(tableroa);
	}
	
	public void txandaJokatu() {
		
		Random random = new Random();
		int ezkutuRadarErosi = random.nextInt(9); //Si es 0 no se hace nada, si es 1 usamos un escudo y si es 2 usamos el radar
		
		//USO DEL ESCUDO
		if(ezkutuRadarErosi <= 2) {
			
			Ezkutu ezk = (Ezkutu) this.getItsasontzienZerrenda().getArmamentua().billatuArma("Ezkutu");
			Itsasontzia its = this.getItsasontzienZerrenda().ItsasontziEzHondoratuaLortu2();
			Tableroa tablero = getNireTableroa();
			if(its != null){
					ezk.akzioaBurutu(its, tablero, false);
				}
		}

		//USO DEL RADAR
		else if (ezkutuRadarErosi >= 7) {

			radarraErabiliDu = true;
			int ilara = random.nextInt(9);
			int zutabea = random.nextInt(9);
			
			Tableroa player = Player.getNirePlayer().getNireTableroa();
			Tableroa nirea = CPU1.getNireCPU1().getAurkariarenTableroa();
			Radar radar = (Radar) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Radar");
			radar.tiroEgin(ilara, zutabea, player, nirea, false, 4);
			radarraErabiliDu = false;	
		} 
		//COMPRA DE ARMAMENTO 
		else if(ezkutuRadarErosi > 2 && ezkutuRadarErosi < 5 ){
			int i = random.nextInt(3);
			switch (i){

				case 0: 
					Bonba bonba = (Bonba) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Bonba"); 
					bonba.erosi(i, 20, false);
					break;

				case 1: 
					Misil misil = (Misil) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Misil"); 
					misil.erosi(i, 20, false);
					break;

				case 2: 
					Ezkutu Ezkutu = (Ezkutu) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Ezkutu"); 
					Ezkutu.erosi(i, 20, false);
					break;

				case 3: 
					Radar Radar = (Radar) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Radar"); 
					Radar.erosi(i, 20, false);
					break;

			}
		
		//REPARAR EL BARCO
		} else if (ezkutuRadarErosi == 6){
			//AQUI SI ENTRA
			Itsasontzia its = this.getItsasontzienZerrenda().ItsasontziUkituaLortu();
			//Si el barco ha sufrido daños
			if(its != null && its.getEgoera() == Egoera.UKITUTA){
				//NO ENTRA AQUI
				its.konpondu();
				int[] hasCoord = its.getProa();
				int[] amCoord = its.getPopa();
				GUI.gui.ItsasontziaKonponduDa(hasCoord, amCoord, false);
			}
		}
		
		
		
		int misilBonba = random.nextInt(2);
		
		boolean egokia = true;
		int x = 0;
		int y = 0;
		
		
		//Bucle para mirar si las cordenadas generadas ya han salido antes
		while (egokia) {
		
			x = random.nextInt(10);
			y = random.nextInt(10);
			
			if (!tiroak.contains(x*10 + y)) {
				
				egokia = false;
				
			}
		
			
		}
	
	
		//Incluir las cordenadas en el HashSet

		Tableroa aurkariarenTableroa = Player.getNirePlayer().getNireTableroa();
		
		if(aurkariarenTableroa.getGelaxka(x, y).getMota() != Mota.EZKUTUA){
			tiroak.add(x*10 + y);
		}

		Tableroa nireAurkariarenTableroa = this.getAurkariarenTableroa();
		
		
		if (misilBonba == 0) { // MISIL
			
			Misil misil = (Misil) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Misil");
			
				

				misil.tiroEgin(x, y, aurkariarenTableroa, nireAurkariarenTableroa, false, 2);
				Player.getNirePlayer().txandaHasi();
			
			
		}
		else { //BONBA
			
			Bonba bonba = (Bonba) CPU1.getNireCPU1().getItsasontzienZerrenda().getArmamentua().billatuArma("Bonba");
			
				bonba.tiroEgin(x, y, aurkariarenTableroa, nireAurkariarenTableroa, false, 1);
				Player.getNirePlayer().txandaHasi();

				
	
		}
		
		Player.getNirePlayer().txandaHasi();
		
	}
	

	//Comprueba las casillas asjacentes a la coordenada que le pasamos
	private boolean inguruneaKonprobatu(Tableroa tableroa, int x, int y){

		boolean itsasontziaEzDago = true;
		for(int i = (x-1); i <= (x+1); i++){ 

			if(i >= 0 && i <= 9){ //Si la coordenada x no esta fuera de rango
			
				for(int j = (y-1); j <= (y+1); j++){
			
					if(j >= 0 && j <= 9){ //Si la coordenada y no esta fuera de rango
						
						//Si ha encontrado un barco sale del segundo 'for'
						if(tableroa.getMota(i,j) == Mota.ITSASONTZIA){
							itsasontziaEzDago = false;
							break;
						}
					}
				}
				//Si ha encontrado un barco sale del primer 'for'
				if(!itsasontziaEzDago){
					break;
				}
			}
		}
		return itsasontziaEzDago;
	}

	private Tableroa gelaxkakAldatu(Tableroa tablero, int[] hasCoord, int[] amCoord, Itsasontzia itsasontzia){ 
		
		int x1 = hasCoord[0]; //Coordenada x del punto de inicio
		int y1 = hasCoord[1]; //Coordenada y del punto de inicio
		int x2 = amCoord[0]; //Coordenada x del punto final
		int y2 = amCoord[1]; //Coordenada y del punto final
		//Si son iguales implica que el barco esta en horizontal
		if(x1 == x2){
			for(int i = y1; i <= y2; i++){
				tablero.aldatuGelaxka(x1, i, Mota.ITSASONTZIA, itsasontzia); //Cambia la casilla del tablero a ITSASONTZIA
			}
		//Si no son iguales, significa que y1 e y2 si lo son, por tanto, el barco esta en vertical	
		} else {
			for(int j = x1; j <= x2; j++){
				tablero.aldatuGelaxka(j, y1, Mota.ITSASONTZIA, itsasontzia); //Cambia la casilla del tablero a ITSASONTZIA
			}
		}
		return tablero;
	}

	private void itsasontziaIpini(Tableroa tablero, int kopurua, int luzera){ 
		Random random = new Random();
		boolean itsasontziaEzDago = true;

		while(kopurua > 0)
		{
			int x = random.nextInt(10); //Saca la coordenada x de manera aleatoria. De 0 a 9
			int y = random.nextInt(10); //Saca la coordenada y de manera aleatoria. De 0 a 9
			
			//Probamos en horizontal
			int i = y;
			int amaiera = y + luzera;

			//Si el barco no se sale del tablero
			if(amaiera <=9){
				//Intentamos colocarlo hacia la derecha
				while(i <= amaiera){
					itsasontziaEzDago = inguruneaKonprobatu(tablero, x, i);
					if(!itsasontziaEzDago){
						break;
					}
					i++;
				}
			}else{ 
				itsasontziaEzDago = false;
			}

			//Si no hemos podido en horizontal, se intenta en vertical
			if(!itsasontziaEzDago){
				int j = x;
				amaiera = x+luzera;
				if(amaiera <=9){
					//Lo intentamos colocar hacia abajo
					while(j <= amaiera){
						itsasontziaEzDago = inguruneaKonprobatu(tablero, j, y);
						if(!itsasontziaEzDago){
							break;
						}
						j++;
					}
				}else{ 
					itsasontziaEzDago = false;
				}
				//Si lo hemos podido colocar en vertical, lo añadimos y cambiamos las casillas
				if(itsasontziaEzDago){ 
					int[] coordenatuHasiera = {x, y};
					int[] coordenatuAmaiera = {amaiera, y};

					Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera + 1, coordenatuHasiera, coordenatuAmaiera);
					CPU1.getNireCPU1().gehituItsasontzia(itsasontzia);
					
					gelaxkakAldatu(tablero, coordenatuHasiera, coordenatuAmaiera, itsasontzia);
					kopurua--;
				}
			//Si lo hemos podido colocar en horizontal, lo añadimos y cambiamos las casillas
			
			} else {

				int[] coordenatuHasiera = {x, y};
				int[] coordenatuAmaiera = {x, amaiera};

				Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera + 1, coordenatuHasiera, coordenatuAmaiera);
				
				CPU1.getNireCPU1().gehituItsasontzia(itsasontzia);

				gelaxkakAldatu(tablero, coordenatuHasiera, coordenatuAmaiera, itsasontzia);
				kopurua--;
			}
		}
	}

	public void tiroakGehitu(Itsasontzia pItsasontzia){

		int[] hasCoord = pItsasontzia.getProa();
		int[] amCoord =  pItsasontzia.getPopa();

		int x1 = hasCoord[0]; //Coordenada x del punto de inicio
		int y1 = hasCoord[1]; //Coordenada y del punto de inicio
		int x2 = amCoord[0]; //Coordenada x del punto final
		int y2 = amCoord[1]; //Coordenada y del punto final
		//Si son iguales implica que el barco esta en horizontal
		if(x1 == x2){
			if(y1 > y2){
				for(int i = y1; i <= y2; i++){
					tiroak.add(x1*10 + i);
				}
			} else {
				for(int i = y2; i <= y1; i++){
					tiroak.add(x1*10 + i);
				}
			}
		//Si no son iguales, significa que y1 e y2 si lo son, por tanto, el barco esta en vertical	
		} else {
			if(x1 > x2){
				for(int j = x1; j <= x2; j++){
					tiroak.add(j*10 + y1);
				}
			} else {
				for(int j = x2; j <= x1; j++){
					tiroak.add(j*10 + y1);
				}
			}
		}

	}

	public void kokatu(){

		Random random = new Random();
		Tableroa tablero = CPU1.getNireCPU1().getNireTableroa();
		int kopurua;
		int x,y;

		for(int luzera = 1; luzera <= 4 ; luzera++){ //Vamos a entra en este bucle para colocar todos los barcos

			switch(luzera){
				
				case 1: 

				kopurua = 4; //Son 4 barcos de uno de tamaño
				while(kopurua > 0){
					x = random.nextInt(10); //Saca la coordenada x de manera aleatoria. De 0 a 9
					y = random.nextInt(10); //Saca la coordenada y de manera aleatoria. De 0 a 9
					if(inguruneaKonprobatu(tablero, x, y)){
						int[] hasCoord = {x,y};
						int[] amCoord = {x,y};
						Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(1, hasCoord, amCoord);
						CPU1.getNireCPU1().gehituItsasontzia(itsasontzia);
						tablero.aldatuGelaxka(x, y, Mota.ITSASONTZIA, itsasontzia);
						kopurua--;
					}
				}
				break;

				case 2: 
				
					kopurua = 3; //Son 3 barcos de dos de tamaño
					itsasontziaIpini(tablero, kopurua, 1);

				break;

				case 3: 

					kopurua = 2; //Son 2 barcos de tres de tamaño
					itsasontziaIpini(tablero, kopurua, 2);

				break;

				case 4: 
				
					kopurua = 1; //Es un barco de 4 de tamaño
					itsasontziaIpini(tablero, kopurua,3);

				break;

			}
		}
	}
}