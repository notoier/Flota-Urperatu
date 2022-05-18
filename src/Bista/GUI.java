package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Eredua.Bonba;
import Eredua.CPU1;
import Eredua.Gelaxka;
import Eredua.Misil;
import Eredua.Mota;
import Eredua.Player;
import Eredua.Radar;
import Eredua.Tableroa;



public class GUI extends javax.swing.JFrame implements Observer, ActionListener{

	public static GUI gui = new GUI();

	private static JPanel Nagusia;
	private static JPanel NireTableroEzkerra;
	private static JPanel AurkariarenTableroEskuina;

	private static JRadioButton MisilBotoia;
	private static JRadioButton BonbaBotoia;
	private static JRadioButton RadarBotoia;

	private static JButton[][] jokalariarenBotoiak; 
	private static JButton[][] aurkariarenBotoiak;  
	
	private static Color JOKALARIAREN_ITSASONTZIA = new Color(192, 145, 250);
	/**
	 * @wbp.nonvisual location=-23,689
	 */
	
	private final JButton button = new JButton(" ");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */

	//Esto es solo para probar el JFrame, el main() principal es el de'Nagusia'
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CPU1.getNireCPU1().kokatu();
					GUI guia = new GUI();
					guia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Crea el JFrame
	public GUI() {

		button.setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 354*2);
		Nagusia = new JPanel();
		Nagusia.setPreferredSize(new Dimension(600, 00));
        Nagusia.setMaximumSize(Nagusia.getPreferredSize()); 
        Nagusia.setMinimumSize(Nagusia.getPreferredSize());
		Nagusia.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Nagusia);
		Nagusia.setLayout(new BorderLayout(0, 0));
		
		//Tablero del jugador
		NireTableroEzkerra = new JPanel();
		Nagusia.add(NireTableroEzkerra, BorderLayout.WEST);
		NireTableroEzkerra.setLayout(new GridLayout(10, 10, 0, 0)); //columna, fila, espacio, espacio
		NireTableroEzkerra.setPreferredSize(new Dimension(600, 600));
		
		//Tablero del rival
		AurkariarenTableroEskuina = new JPanel();
		Nagusia.add(AurkariarenTableroEskuina, BorderLayout.EAST);
		AurkariarenTableroEskuina.setLayout(new GridLayout(10, 10, 0, 0));
		AurkariarenTableroEskuina.setPreferredSize(new Dimension(600, 600));
		
		//Inicializamos las matrices de botones
		jokalariarenBotoiak = new JButton[10][10];
		aurkariarenBotoiak = new JButton[10][10];

		JPanel erdikoa = new JPanel(new GridLayout(2,1));
		erdikoa.setPreferredSize(new Dimension(10,10));
		Nagusia.add(erdikoa, BorderLayout.CENTER);

		//Panel donde se seleccionan las armas
		JPanel ArmakBehean = new JPanel();
		Nagusia.add(ArmakBehean, BorderLayout.SOUTH);

		//Boton del misil
		MisilBotoia = new JRadioButton("Misil");
		buttonGroup.add(MisilBotoia);
		ArmakBehean.add(MisilBotoia);
		
		//Boton de la bomba
		BonbaBotoia = new JRadioButton("Bonba");
		buttonGroup.add(BonbaBotoia);
		ArmakBehean.add(BonbaBotoia);
		
		//Boton del radar
		RadarBotoia = new JRadioButton("Radar");
		buttonGroup.add(RadarBotoia);
		ArmakBehean.add(RadarBotoia);
		


		//Boton para abrir el panel del escudo
        JButton ezkutua = new JButton("Ezkutua jarri");
        ezkutua.setOpaque(true);
        ezkutua.setBackground(Color.WHITE);
        ezkutua.setBorder(new LineBorder(Color.BLACK));
        ezkutua.setActionCommand("EZKUTUA");
        ezkutua.addActionListener(this);
		ezkutua.setMaximumSize(new Dimension(50,50));
		ezkutua.setSize(new Dimension(50,50));
		erdikoa.add(ezkutua);

		//Boton para armamento
		JButton denda = new JButton("Denda");
		denda.setOpaque(true);
        denda.setBackground(Color.WHITE);
        denda.setBorder(new LineBorder(Color.BLACK));
        denda.setActionCommand("Denda");
        denda.addActionListener(this);
		denda.setMaximumSize(new Dimension(50,50));
		denda.setSize(new Dimension(50,50));
		erdikoa.add(denda);

		JPanel Goiburua = new JPanel();
		Nagusia.add(Goiburua, BorderLayout.NORTH);
		
		//Titulo de la ventana
		JLabel lblNewLabel = new JLabel("Zure txanda");
		Goiburua.add(lblNewLabel);
		
		//Se crean los botones del jugador y del rival
		matrizeakSortu(NireTableroEzkerra, AurkariarenTableroEskuina);
	}
	
	//Crea una matriz de botones en un panel
	private void matrizeakSortu(JPanel pNirea, JPanel pAurkaria){
		for(int l=0;l<10;l++) {
			for(int z=0;z<10;z++) {

					pNirea.add(getJokalariarenBotoia(l, z));
					pAurkaria.add(getAurkariarenBotoia(l, z));
			}
		}
	}

	public void jokalariarenItsasontziakAgertu(){
		Tableroa tablero = Player.getNirePlayer().getNireTableroa();
		for(int l=0;l<10;l++) {
			for(int z=0;z<10;z++) {
				if(tablero.getMota(l,z) == Mota.ITSASONTZIA){
					jokalariarenBotoiak[l][z].setBackground(GUI.JOKALARIAREN_ITSASONTZIA);
				}
			}
		}
	}

	//Crea un boton que guarda en una matriz de botones, despues devuelve el boton de la matriz para añadirlo a la interfaz
	private JButton getJokalariarenBotoia(int x, int y) {
		JButton botoia = new JButton();
		botoia.addActionListener(this);
		botoia.setOpaque(true);
		botoia.setActionCommand(x + ":" + y); //Con esto luego podemos saber en que posicion esta
		botoia.setBackground(Gelaxka.EZEZAGUNA_COLOREA);
		botoia.setBorder(new LineBorder(Color.BLACK)); //Quita esta linea para que tenga el tamaño grande
		botoia.setEnabled(false);
		jokalariarenBotoiak[x][y] = botoia;
		return jokalariarenBotoiak[x][y];
	}

	
	//Crea un boton que guarda en una matriz de botones, despues devuelve el boton de la matriz para añadirlo a la interfaz
	private JButton getAurkariarenBotoia(int x, int y) {
		JButton botoia = new JButton();
		botoia.addActionListener(this);
		botoia.setOpaque(true);
		botoia.setBackground(Gelaxka.EZEZAGUNA_COLOREA);
		botoia.setActionCommand(x + ":" + y); //Con esto luego podemos saber en que posicion esta
		botoia.setBorder(new LineBorder(Color.BLACK)); //Quita esta linea para que tenga el tamaño grande
		aurkariarenBotoiak[x][y] = botoia;
		
		return aurkariarenBotoiak[x][y];
	}

	public void ItsasontziaKonponduDa(int[] hasCoord, int[] amCoord, boolean jokalaria){
		
		HashSet<Integer> tiroak = CPU1.getNireCPU1().getTiroak();

		int x1 = hasCoord[0];
		int y1 = hasCoord[1];
		
		int x2 = amCoord[0];
		int y2 = amCoord[1];

		//Si es el jugador quien ha reparado un barco entonces quitamos el tiro del HashSet de la CPU para que pueda volver ha disparar
		if(jokalaria){
			if(x1 == x2){
				if(y2 > y1){
					//Faltar sacar estas coordenadas del HashSet de la CPU
					for (int i = y1; i <= y2; i++){	
						
						jokalariarenBotoiak[x1][i].setBackground(Konpondu.KONPONDUTA);
						tiroak.remove((x1*10 + i));

					}

				} else {
					for (int i = y2; i <= y1; i++){	
							
						jokalariarenBotoiak[x1][i].setBackground(Konpondu.KONPONDUTA);
						tiroak.remove((x1*10 + i));
					}
				}

			} else if (y1 == y2) {
				if(x2 > x1){
					for (int i = x1; i <= x2; i++){		
					
						jokalariarenBotoiak[i][y1].setBackground(Konpondu.KONPONDUTA);
						tiroak.remove((i*10 + y1));
					}
				} else {
					for (int i = x2; i <= x1; i++){		
				
						jokalariarenBotoiak[i][y1].setBackground(Konpondu.KONPONDUTA);
						tiroak.remove((i*10 + y1));
					}
				}
			}
		//Si es la CPU quien ha reparado un barco, entonces volvemos a activar el boton para el jugador
		} else {
			if(x1 == x2){
				if(y2 > y1){
					
					for (int i = y1; i <= y2; i++){		
						if (aurkariarenBotoiak[x1][i].getBackground() == Gelaxka.ITSASONTZIA_COLOREA){		
							aurkariarenBotoiak[x1][i].setBackground(Konpondu.KONPONDUTA);
							aurkariarenBotoiak[x1][i].setEnabled(true);
						}

					}

				} else {
					for (int i = y2; i <= y1; i++){	
						if (aurkariarenBotoiak[x1][i].getBackground() == Gelaxka.ITSASONTZIA_COLOREA){			
							aurkariarenBotoiak[x1][i].setBackground(Konpondu.KONPONDUTA);
							aurkariarenBotoiak[x1][i].setEnabled(true);
						}
					}
				}

			} else if (y1 == y2) {
				if(x2 > x1){
					for (int i = x1; i <= x2; i++){	
						if (aurkariarenBotoiak[x1][i].getBackground() == Gelaxka.ITSASONTZIA_COLOREA){					
							aurkariarenBotoiak[i][y1].setBackground(Konpondu.KONPONDUTA);
							aurkariarenBotoiak[i][y1].setEnabled(true);
						}
					}
				} else {
					for (int i = x2; i <= x1; i++){					
						if (aurkariarenBotoiak[x1][i].getBackground() == Gelaxka.ITSASONTZIA_COLOREA){	
							aurkariarenBotoiak[i][y1].setBackground(Konpondu.KONPONDUTA);
							aurkariarenBotoiak[i][y1].setEnabled(true);
						}
					}
				}
			}
		}
	}

	//Pasa por todo el tablero y pone el color del boton dependiendo del estado de cada casilla
	//NO SE SI ES ASI, NO LO HE PODIDO COMPROBAR
	@Override
	public void update(Observable o, Object arg) {
		
		int[] posizioa = (int[]) arg;
		int x = posizioa[0];
		int y = posizioa[1];
			if (Player.getNirePlayer().nireTxandaDa()) {
				
				if(RadarBotoia.isSelected()){
					if(aurkariarenBotoiak[x][y].getBackground() == Gelaxka.EZEZAGUNA_COLOREA){
						switch(Player.getNirePlayer().getAurkariarenTableroa().getMota(x,y)){
			
							case ITSASONTZIA:
							aurkariarenBotoiak[x][y].setBackground(Radar.ITSASONTZIA_KOLOREA);
							break;
				
							case URA:
							aurkariarenBotoiak[x][y].setBackground(Radar.URA_KOLOREA);
							break;
				
							case EZKUTUA:
							aurkariarenBotoiak[x][y].setBackground(Radar.ITSASONTZIA_KOLOREA);
							break;
							
							default:
							aurkariarenBotoiak[x][y].setBackground(Gelaxka.EZEZAGUNA_COLOREA);
							break;
						}
					}
		
				} else {

					switch(Player.getNirePlayer().getAurkariarenTableroa().getMota(x,y)){
		
						case ITSASONTZIA:
						aurkariarenBotoiak[x][y].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
						aurkariarenBotoiak[x][y].setEnabled(false); //Desactiva el boton para no poder disparar dos veces al mismo sitio
						break;
			
						case URA:
						aurkariarenBotoiak[x][y].setBackground(Gelaxka.URA_COLOREA);
						aurkariarenBotoiak[x][y].setEnabled(false); //Desactiva el boton para no poder disparar dos veces al mismo sitio
						break;
			
						case EZKUTUA:
						aurkariarenBotoiak[x][y].setBackground(Gelaxka.EZKUTUA_COLOREA);
						break;
						
						default:
						aurkariarenBotoiak[x][y].setBackground(Gelaxka.EZEZAGUNA_COLOREA);
						break;
					}
				}
			//	Player.getNirePlayer().txandaBukatu();
			}
			
			else {
				
				if(CPU1.getNireCPU1().radarraErabiliDu()){
					
					if( jokalariarenBotoiak[x][y].getBackground() == GUI.JOKALARIAREN_ITSASONTZIA || jokalariarenBotoiak[x][y].getBackground() == Gelaxka.EZEZAGUNA_COLOREA){

						switch(CPU1.getNireCPU1().getAurkariarenTableroa().getMota(x,y)){
			
							case ITSASONTZIA:
							jokalariarenBotoiak[x][y].setBackground(Radar.ITSASONTZIA_KOLOREA);
							break;
				
							case URA:
							jokalariarenBotoiak[x][y].setBackground(Radar.URA_KOLOREA);
							break;
				
							case EZKUTUA:
							jokalariarenBotoiak[x][y].setBackground(Radar.ITSASONTZIA_KOLOREA);
							break;
							
							default:
							jokalariarenBotoiak[x][y].setBackground(Gelaxka.EZEZAGUNA_COLOREA);
							break;
						}
					}

				}else {

					switch(CPU1.getNireCPU1().getAurkariarenTableroa().getMota(x,y)){
					
						case ITSASONTZIA:
						jokalariarenBotoiak[x][y].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
						break;
			
						case URA:
						jokalariarenBotoiak[x][y].setBackground(Gelaxka.URA_COLOREA);
						break;
			
						case EZKUTUA:
						jokalariarenBotoiak[x][y].setBackground(Gelaxka.EZKUTUA_COLOREA);
						break;
						
						default:
						jokalariarenBotoiak[x][y].setBackground(Gelaxka.EZEZAGUNA_COLOREA);
						break;
					}	
				}
				
			}
			o.hasChanged();
		}

	@Override //Este es el metodo que tiene que cambiar los valores de una matriz al pulsar un boton
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("EZKUTUA")){
			Babestu.ezkutua = new Babestu();
			Babestu.ezkutua.setVisible(true);
			
		} else if(arg0.getActionCommand().equals("Denda")){
			Denda.denda.hasieratuDirua(Player.getNirePlayer().getItsasontzienZerrenda().getDirua());	
			Denda.denda.setVisible(true);

		}else {
			int ilara = Integer.parseInt(arg0.getActionCommand().split(":")[0]);
			int zutabea = Integer.parseInt(arg0.getActionCommand().split(":")[1]);

			if(MisilBotoia.isSelected()){

				//Busca el misil
				Misil misil = (Misil) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Misil");

				Tableroa CPU = CPU1.getNireCPU1().getNireTableroa(); //Cogemos el tablero del rival
				Tableroa nirea = Player.getNirePlayer().getAurkariarenTableroa(); //Cogemos nuestro tablero que representa el del rival

				//Si no hay cantidad suficiente
				if(!(misil.tiroEgin(ilara, zutabea, CPU, nirea, true,2))){
					JOptionPane.showMessageDialog(this, "Ez dago kopuru nahikorik");
				} else {
					Player.getNirePlayer().txandaBukatu();
				}
			

			} else if(BonbaBotoia.isSelected()){
				//Busca la bomba
				Bonba bonba = (Bonba) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Bonba");
				
				Tableroa CPU = CPU1.getNireCPU1().getNireTableroa(); //Cogemos el tablero del rival
				Tableroa nirea = Player.getNirePlayer().getAurkariarenTableroa(); //Cogemos nuestro tablero que representa el del rival

				//Si no hay cantidad suficiente
				if(!(bonba.tiroEgin(ilara, zutabea, CPU, nirea, true,1))){
					JOptionPane.showMessageDialog(this, "Ez dago kopuru nahikorik");
				} else {
					Player.getNirePlayer().txandaBukatu();
				}

			} else if(RadarBotoia.isSelected()){

				if(!Player.getNirePlayer().radarraErabiliDu()){
					Radar radar = (Radar) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Radar");
					Tableroa CPU = CPU1.getNireCPU1().getNireTableroa();
					Tableroa nirea = Player.getNirePlayer().getAurkariarenTableroa();
					radar.tiroEgin( ilara, zutabea, CPU, nirea, true,4);
					Player.getNirePlayer().setRadarra(true);	
					}
				
				} else {
					JOptionPane.showMessageDialog(this, "Ezin duzu radarra erabili");
				}
			}	
		}
	}
