package Eredua;

public class ItsasontziFactory {

	private static ItsasontziFactory nIF;
	
	private ItsasontziFactory() {
		
	}
	
	public static ItsasontziFactory getItsasontziFactory() {
		
		if (nIF == null) {
			
			nIF = new ItsasontziFactory();
			
		}
		
		return nIF;
		
	}
	
	
	public Itsasontzia sortuItsasontzia (int pLuzeera, int[] hasCoord, int[] amCoord ) {
		
		Itsasontzia itsasontzia = null;

		switch(pLuzeera) {
		
			case 1: 
			itsasontzia = new Fragata(hasCoord, amCoord);
			break;

			case 2: 
			itsasontzia = new Suntsitzailea(hasCoord, amCoord);
			break;
			
			case 3: 
			itsasontzia = new Itsaspekoa(hasCoord, amCoord);
			break;

			case 4: 
			itsasontzia = new HegazkinOntzia(hasCoord, amCoord);
			break;			
		}
		
		return itsasontzia;
		
		
	}
	
}
