package Eredua;

import javax.swing.JOptionPane;

import Bista.Babestu;

public class Ezkutu extends Arma {
	
	public Ezkutu(int pKopuru, String pDeskripzio) {
		super(pKopuru, pDeskripzio);
		
	}

	public String getDeskripzioa(){
		return super.getDeskripzioa();
	}

	
	public void akzioaBurutu(Itsasontzia itsasontzia, Tableroa tablero, boolean jokalaria){ 
		
		if(super.kopNahikoa()){
			super.kopuruaAldatu(3, jokalaria);
			
			//Si el barco no tiene ya un escudo
			if(itsasontzia.getEgoera() != Egoera.BABESTUTA){
						
				//Si el barco no esta hundido
				if(itsasontzia.getEgoera() != Egoera.HONDORATUTA){
					//Cambia el estado del barco a 'BABESTUTA'
					itsasontzia.setEgoera(Egoera.BABESTUTA);
					
					//Cambia el tipo de la gelaxka a 'EZKUTUA'
					int[] hasCoord = itsasontzia.getProa();
					int[] amCoord = itsasontzia.getPopa();

					if(jokalaria){

						Babestu.gelaxkakMargoztu(hasCoord, amCoord, tablero);
					} else {
						
						int x1 = hasCoord[0];
						int y1 = hasCoord[1];
		
						int x2 = amCoord[0];
						int y2 = amCoord[1];

						if(x1 == x2){
							for (int i = y1; i <= y2; i++){
								tablero.getGelaxka(x1,i).aldatuMota(Mota.EZKUTUA);
								
							}
						//Vertical
						} else {
							for (int j = x1; j <= x2; j++){
								tablero.getGelaxka(j,y1).aldatuMota(Mota.EZKUTUA);
								
							}
						}
					}

				} else if(jokalaria){
					JOptionPane.showMessageDialog(Babestu.ezkutua, "Itsasontzia hondoratuta dago, saiatu beste itsasontzi batekin");
				}
			
			} else if(jokalaria){
				JOptionPane.showMessageDialog(Babestu.ezkutua, "Itsasontzia jadanik babestuta dago");
			}	
			
		} else if(jokalaria) {
			JOptionPane.showMessageDialog(Babestu.ezkutua, "Ez duzu ezkutu nahikorik");
		}
	}
}