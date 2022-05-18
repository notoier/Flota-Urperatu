package Eredua;

public class BonbaPortaera implements ArmaPortaera{

    
    public void akzioaBurutu(final int x, final int y, Tableroa aurkariarenTableroa, Tableroa nireAurkariarenTableroa, boolean jokalaria){
       
        Mota pMota = aurkariarenTableroa.getMota(x, y); //Busca el estado de la casilla del tablero rival

        //Si en esa casilla hay un barco
        if(pMota == Mota.ITSASONTZIA || pMota == Mota.EZKUTUA){
            //Si ha disparado el jugador
            if(jokalaria){
                pMota = CPU1.getNireCPU1().getItsasontzienZerrenda().ukituItsasontzia(aurkariarenTableroa, nireAurkariarenTableroa, false, x, y, pMota, jokalaria); //False si dispara la bomba, True si dispara el misil
            } else { 
                pMota = Player.getNirePlayer().getItsasontzienZerrenda().ukituItsasontzia(aurkariarenTableroa, nireAurkariarenTableroa, false, x, y, pMota, jokalaria); //False si dispara la bomba, True si dispara el misil
            }
        }

        nireAurkariarenTableroa.aldatuGelaxka(x, y, pMota, null); //y la cambia en nuestro tablero del rival


    }

    

}
