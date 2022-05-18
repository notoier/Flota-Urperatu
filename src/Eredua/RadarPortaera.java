package Eredua;

public class RadarPortaera implements ArmaPortaera {

    
    public void akzioaBurutu(int ilara, int zutabea, Tableroa aurkariarenTableroa, Tableroa nireAurkariarenTableroa, boolean jokalaria) {
        
        for(int x = ilara -1; x <= ilara +1; x++){
			if(x >= 0 && x <= 9){
				for(int y = zutabea -1; y <= zutabea +1; y++){
					if(y>= 0 && y <= 9){
						Itsasontzia itsasontzia = aurkariarenTableroa.getGelaxka(x,y).getItsasontzia();
						Mota pMota = aurkariarenTableroa.getGelaxka(x,y).getMota();
						nireAurkariarenTableroa.aldatuGelaxka(x,y, pMota, itsasontzia);	
					}
				}
			}
		}
        
    }
    
}
