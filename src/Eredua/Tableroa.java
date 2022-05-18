package Eredua;
import java.util.Observable;
import Bista.GUI;


public class Tableroa extends Observable {

	private Gelaxka[][] tableroa;
	
	public Tableroa() {
		this.tableroa=new Gelaxka[10][10];
	
		for(int l=0;l<10;l++) {
			for(int z=0;z<10;z++) {
					this.tableroa[l][z]= new Gelaxka(l, z);
			}
		}

		//Añadimos el observer
		this.addObserver(GUI.gui);
	}

	public Mota getMota(int x, int y) {
		return this.tableroa[x][y].getMota();
	}

	//Cambia el estado de la casilla y avisa a los observers
	public boolean aldatuGelaxka(int x, int y, Mota pMota, Itsasontzia itsasontzia){

		this.tableroa[x][y].aldatuMota(pMota);
		this.tableroa[x][y].itsasontziaSartu(itsasontzia);
		int[] posizioa = {x,y};
		setChanged();
		notifyObservers(posizioa);

		return true;
	}

	//Cambia el estado de la casilla y avisa a los observers
	public boolean aldatuGelaxkaHau(int x, int y, Mota pMota, Itsasontzia itsasontzia){

		this.tableroa[x][y].aldatuMota(pMota);
		this.tableroa[x][y].itsasontziaSartu(itsasontzia);
		return true;
	}

	public void gehituItsasontzia(int x, int y, Itsasontzia pItsasontzia)
	{
		this.tableroa[x][y].itsasontziaSartu(pItsasontzia);
	}

	public void itsasontziaGelaxkanSartu(int[] hasCoord, int[] amCoord, Itsasontzia itsasontzia){
        
        int x1 = hasCoord[0];
        int y1 = hasCoord[1];

        int x2 = amCoord[0];
        int y2 = amCoord[1];

        //Horizontal
        if(x1 == x2){
            for (int i = y1; i <= y2; i++){
                Player.getNirePlayer().getNireTableroa().gehituItsasontzia(x1, i, itsasontzia);
            }
        //Vertical
        } else {
            for (int j = x1; j <= x2; j++){
                Player.getNirePlayer().getNireTableroa().gehituItsasontzia(j, y1, itsasontzia);
            }
        }
    }

	public Gelaxka getGelaxka(int x, int y) {
		return tableroa[x][y];
	}

	public void itsasontziaKendu(Itsasontzia pItsasontzia){

		int[] hasCoord = pItsasontzia.getProa();
		int[] amCoord = pItsasontzia.getPopa();

		int x1 = hasCoord[0]; //Coordenada x del punto de inicio
		int y1 = hasCoord[1]; //Coordenada y del punto de inicio
		int x2 = amCoord[0]; //Coordenada x del punto final
		int y2 = amCoord[1]; //Coordenada y del punto final
		//Si son iguales implica que el barco esta en horizontal
		if(x1 == x2){
			if(y2 > y1){
				//Faltar sacar estas coordenadas del HashSet de la CPU
				for (int i = y1; i <= y2; i++){				
					this.getGelaxka(x1, i).itsasontziaKendu();
				}

			} else {
				for (int i = y2; i <= y1; i++){				
					this.getGelaxka(x1, i).itsasontziaKendu();
				}
			}

		} else if (y1 == y2) {
			if(x2 > x1){
				for (int i = x1; i <= x2; i++){						
					this.getGelaxka(i, y1).itsasontziaKendu();
				}
			} else {
				for (int i = x2; i <= x1; i++){						
					this.getGelaxka(i, y1).itsasontziaKendu();
				}
			}
		}

	}

	public void gelaxkakAldatu(int[] hasCoord, int[] amCoord){ 
    	
        int x1 = hasCoord[0];
        int y1 = hasCoord[1];

        int x2 = amCoord[0];
        int y2 = amCoord[1];

        //Horizontal
        if(x1 == x2){
			if(y1 < y2){
				for (int i = y1; i <= y2; i++){
					aldatuGelaxka(x1, i, Mota.ITSASONTZIA, null);
				}
			} else {
				for (int i = y2; i <= y1; i++){
					aldatuGelaxka(x1, i, Mota.ITSASONTZIA, null);
				}
			}
        //Vertical
        } else {
			if(x1 < x2){
				for (int j = x1; j <= x2; j++){
					aldatuGelaxka(j, y1, Mota.ITSASONTZIA, null);
				}
			} else {
				for (int j = x2; j <= x1; j++){
					aldatuGelaxka(j, y1, Mota.ITSASONTZIA, null);
				}
			}

        }
    }
}