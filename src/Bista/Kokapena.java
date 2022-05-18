package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Eredua.Gelaxka;
import Eredua.ItsasontziFactory;
import Eredua.Itsasontzia;
import Eredua.Mota;
import Eredua.Player;

//ESTA INTERFAZ SIRVE PARA COLOCAR LOS BARCOS
public class Kokapena extends JFrame implements ActionListener{

    //Aqui se guardan todos los botones para poder seleccionarlos luego
    private static JButton[][] jokalariarenBotoiak; 

    //Este es el boton que esta fuera del panel
    private JButton button = new JButton("");
    private JPanel nagusia = new JPanel(new BorderLayout(0, 0));
    
    //Cantidad de veces que se ha pulsado el boton 
    private int botoiPultsaketaKop;

    //Todos los posibles tamaños del barco, añadiendo el 0 para mejor entendimiento del codigo
    private int[] itsasontziMotaKop = {0,4,3,2,1};
    
    //Cantidad total de BARCOS
    private int itsasontziKop;
    
    //Aqui guardamos las coordenadas de las dos casillas que pulsamos
    private int[] xCoord = new int[2];
    private int[] yCoord = new int[2]; 

    //Este es el color que usamos para marcar que hemos seleccionado una casilla
    private static final Color AUKERATUTA_KOLOREA = new Color(201, 38, 68);

    //Constructor de la clase
	public Kokapena() {
		this.botoiPultsaketaKop = 0;
        this.itsasontziKop = 10;
		panelaHasieratu();
	}

    //Desde aqui podemos comprobar si funciona el panel
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kokapena frame = new Kokapena();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    //Inicializa todos los componentes del panel
    public void panelaHasieratu(){
		
        //Ponemos todos los ajustes de la ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ZURE ITSASONTZIAK KOKATU");
		this.setSize(new Dimension(600,600));
		this.setResizable(false);

        //Inicializamos nuestra matriz de botones
		jokalariarenBotoiak = new JButton[10][10];
		
        //Ajustamos el panel principal
        nagusia.setBackground(new Color(51,102,0));
		nagusia.setPreferredSize(new Dimension(350,350));
		
        //Introducimos el panel secundario en el que iran los botones
        JPanel matrix = new JPanel(new GridLayout(10,10));
		nagusia.add(matrix);
        
        //Llamamos a la funcion que nos va a llenar el panel de botones
        matrizeaSortu(matrix);

        //Creamos y ajustamos el boton 'Kokatu', que servira para colocar los barcos
        JButton kokatu = new JButton("Kokatu");
        kokatu.setOpaque(true);
        kokatu.setBackground(Color.WHITE);
        kokatu.setBorder(new LineBorder(Color.BLACK));
        kokatu.setActionCommand("KOKATU");
        kokatu.addActionListener(this);

        //Lo introducimos al panel principal
        nagusia.add(kokatu, BorderLayout.SOUTH);

        //Añadimos este panel en el frame
        add(nagusia);
    }

    //Esta funcion nos llenara un panel de 10x10 de botones
    private void matrizeaSortu(JPanel pNirea){
		for(int l=0;l<10;l++) {
			for(int z=0;z<10;z++) {
					pNirea.add(getBtnNewButton(l, z));
			}
		}
	}

    //Crea y configura un nuevo boton, que tambien es guardado en nuestra matriz de botones
	private JButton getBtnNewButton(int x, int y) {
		button = new JButton();
        button.setOpaque(true);
		button.setBackground(Gelaxka.EZEZAGUNA_COLOREA);
		button.setBorder(new LineBorder(Color.BLACK));
		button.setActionCommand(x + ":" + y);
		button.addActionListener(this);

		jokalariarenBotoiak[x][y] = button;

		return jokalariarenBotoiak[x][y];
	}

    //Este metodo define lo que hara un boton al ser pulsado
    @Override
    public void actionPerformed(ActionEvent arg0) {

    //SI NO ES EL BOTON DE KOKATU ENTONCES QUE MARQUE LAS CASILLAS
    if(arg0.getActionCommand() != "KOKATU")
        {
            int ilara = Integer.parseInt(arg0.getActionCommand().split(":")[0]); //Consigue la coordenada x
            int zutabea = Integer.parseInt(arg0.getActionCommand().split(":")[1]); //Consigue la coordenada y
            
            //Si la casilla que estamos pulsando no esta ya marcada, entonces la coloreamos
            if(jokalariarenBotoiak[ilara][zutabea].getBackground() != Gelaxka.ITSASONTZIA_COLOREA){
                jokalariarenBotoiak[ilara][zutabea].setBackground(AUKERATUTA_KOLOREA);

            }
            botoiPultsaketaKop++;

            //Si es la primera vez que pulsamos entonces nos guarda este punto como coordenada de inicio
            if(botoiPultsaketaKop == 1){
                xCoord[0] = ilara;
                yCoord[0] = zutabea;
                

            //Si es la segunda vez que pulsamos entonces nos guarda este punto como coordenada final
            } else if (botoiPultsaketaKop == 2){
                xCoord[1] = ilara;
                yCoord[1] = zutabea;
                
                
                //Si habia casillas marcadas antes las borra y deja solo el primer y segundo punto
                for(int l=0;l<10;l++) {
                    for(int z=0;z<10;z++) {

                        //Ponemos como excepcion las dos casillas que nosotros hemos marcado
                        if(!(jokalariarenBotoiak[l][z].getBackground() == Gelaxka.ITSASONTZIA_COLOREA)){
                            jokalariarenBotoiak[l][z].setBackground(Gelaxka.EZEZAGUNA_COLOREA);
                            jokalariarenBotoiak[l][z].setText("");
                        } 
                    }
                }

                //Comprobamos si esta en vertical o en horizontal y rellenamos las casillas entre los dos puntos
                //Esto se puede extraer a un metodo aparte, pero no creo que sea necesario
                if(xCoord[0] == xCoord[1]){
                    if(yCoord[1] > yCoord[0]){
                        for (int i = yCoord[0]; i <= yCoord[1]; i++){
                            //Si en las casillas que estamos marcando no hay un barco entonces las coloreamos
                            if(jokalariarenBotoiak[xCoord[0]][i].getBackground() != Gelaxka.ITSASONTZIA_COLOREA){
                                jokalariarenBotoiak[xCoord[0]][i].setBackground(AUKERATUTA_KOLOREA);
                            }
                        }

                    } else {
                        for (int i = yCoord[1]; i <= yCoord[0]; i++){
                            //Si en las casillas que estamos marcando no hay un barco entonces las coloreamos
                            if(jokalariarenBotoiak[xCoord[0]][i].getBackground() != Gelaxka.ITSASONTZIA_COLOREA){
                                jokalariarenBotoiak[xCoord[0]][i].setBackground(AUKERATUTA_KOLOREA);
                            }
                        }
                    }

                } else if (yCoord[0] == yCoord[1]) {
                    if(xCoord[1] > xCoord[0]){
                        for (int i = xCoord[0]; i <= xCoord[1]; i++){
                            //Si en las casillas que estamos marcando no hay un barco entonces las coloreamos
                            if(jokalariarenBotoiak[i][yCoord[0]].getBackground() != Gelaxka.ITSASONTZIA_COLOREA){
                                jokalariarenBotoiak[i][yCoord[0]].setBackground(AUKERATUTA_KOLOREA);
                            }
                        }
                    } else {
                        for (int i = xCoord[1]; i <= xCoord[0]; i++){
                            //Si en las casillas que estamos marcando no hay un barco entonces las coloreamos
                            if(jokalariarenBotoiak[i][yCoord[0]].getBackground() != Gelaxka.ITSASONTZIA_COLOREA){
                                jokalariarenBotoiak[i][yCoord[0]].setBackground(AUKERATUTA_KOLOREA);
                            }
                        }
                    }
                
                }
                
                botoiPultsaketaKop = 0;
            }

        //HEMOS PULSADO EL BOTON 'KOKATU'
        } else {
            //Comprobamos si el barco esta en horizontal o en vertical
            int luzera;
            String kokapena;
            //Si solo hemos pulsado una vez, entonces estamos colocando un barco de 1
            if(botoiPultsaketaKop == 1){
                xCoord[1] = xCoord[0];
                yCoord[1] = yCoord[0];
                botoiPultsaketaKop = 0;
            }
            //Se usa el valor absoluto por si acaso se ha colocado el barco al reves, de abajo a arriba o de derecha a izquierda
            if(xCoord[0] == xCoord[1]){
                luzera = Math.abs(yCoord[1] - yCoord[0]) + 1;
                kokapena = "Horizontala";

            } else{
                luzera = Math.abs(xCoord[1] - xCoord[0]) + 1; 
                kokapena = "Bertikala";
            }
            //Creamos el barco dependiendo de su tamaño
                switch(luzera){

                    //Barco de una casilla
                    case 1:
                    if(itsasontziMotaKop[1] > 0){
                        int[] hasCoord = {xCoord[0], yCoord[0]};
                        int[] amCoord = {xCoord[1], yCoord[1]};
                        Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera, hasCoord, amCoord); 
                        if(ondoJarritaDago(kokapena, itsasontzia)) {
                        	Player.getNirePlayer().getItsasontzienZerrenda().gehituItsasontzia(itsasontzia);
                            
                            
                            itsasontziMotaKop[1]--; //Restamos 1 a la cantidad de barcos de este tamaño que quedan
                            itsasontziKop--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Bateko itsasontzi kopurua bete duzu");
                    }
                    break;

                    //Barco de dos casillas
                    case 2:
                    if(itsasontziMotaKop[2] > 0){
                        int[] hasCoord = {xCoord[0], yCoord[0]};
                        int[] amCoord = {xCoord[1], yCoord[1]}; 
                        Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera, hasCoord, amCoord);
                        if(ondoJarritaDago(kokapena, itsasontzia)) {
                        	Player.getNirePlayer().getItsasontzienZerrenda().gehituItsasontzia(itsasontzia);
                            
                            
                            itsasontziMotaKop[2]--; //Restamos 1 a la cantidad de barcos de este tamaño que quedan
                            itsasontziKop--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Biko itsasontzi kopurua bete duzu");
                    }
                    break;

                    //Barco de tres casillas
                    case 3:
                    if(itsasontziMotaKop[3] > 0){
                        int[] hasCoord = {xCoord[0], yCoord[0]};
                        int[] amCoord = {xCoord[1], yCoord[1]}; 
                        Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera, hasCoord, amCoord);
                        if(ondoJarritaDago(kokapena, itsasontzia)) {
                        	Player.getNirePlayer().getItsasontzienZerrenda().gehituItsasontzia(itsasontzia);
                            
                            
                            itsasontziMotaKop[3]--; //Restamos 1 a la cantidad de barcos de este tamaño que quedan
                            itsasontziKop--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Hiruko itsasontzi kopurua bete duzu");
                    }
                    break;
 
                    //Barco de cuatro casillas
                    case 4:
                    if(itsasontziMotaKop[4] > 0){
                        int[] hasCoord = {xCoord[0], yCoord[0]};
                        int[] amCoord = {xCoord[1], yCoord[1]};
                        Itsasontzia itsasontzia = ItsasontziFactory.getItsasontziFactory().sortuItsasontzia(luzera, hasCoord, amCoord);
                        if(ondoJarritaDago(kokapena, itsasontzia)) {
                        	Player.getNirePlayer().getItsasontzienZerrenda().gehituItsasontzia(itsasontzia);
                            
                            
                            itsasontziMotaKop[4]--; //Restamos 1 a la cantidad de barcos de este tamaño que quedan
                            itsasontziKop--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Lauko itsasontzi kopurua bete duzu");
                    }
                    break;

                    //Si no se ha cumplido ninguna de las anteriores salta este mensaje de error
                    default:
                        JOptionPane.showMessageDialog(this, "Itsasontzia luzeegia da");
            }

            if(itsasontziKop == 0){
                JOptionPane.showMessageDialog(this, "Itsasontzi guztiak kokatu dituzu");
                this.dispose(); //Este metodo sirve para que cuando salte el mensaje anterior, al cerrarlo, se cierre tambien la ventana principal
                GUI.gui = new GUI();
                GUI.gui.jokalariarenItsasontziakAgertu();
                GUI.gui.setVisible(true); //Una vez hemos cerrado la ventana de los barcos nos abre la interfaz de la partida
            }
        }
    }

    //Nos va a comprobar las casillas adjacentes de nuestro barco dependiendo de su orientacion
    private boolean ondoJarritaDago(String kokapena, Itsasontzia itsasontzia){
        boolean ondoDago = false;

        if (kokapena == "Horizontala"){

            if(yCoord[1] > yCoord[0]){
                ondoDago = ingurunekoGelaxkakKonprobatu(kokapena, yCoord[1], xCoord[0], yCoord[0], itsasontzia); //Orientacion, casilla final, x, primera casilla 
            } else {
                ondoDago = ingurunekoGelaxkakKonprobatu(kokapena, yCoord[0], xCoord[0], yCoord[1], itsasontzia); //Orientacion, casilla final, x, primera casilla 
            }

        } else if (kokapena == "Bertikala"){

            if(xCoord[1] > xCoord[0]){
                ondoDago = ingurunekoGelaxkakKonprobatu(kokapena, xCoord[1], xCoord[0], yCoord[0], itsasontzia); //Orientacion, casilla final, primera casilla, y

            } else {
                ondoDago = ingurunekoGelaxkakKonprobatu(kokapena, xCoord[0], xCoord[1], yCoord[0], itsasontzia); //Orientacion, casilla final, primera casilla, y
            }
        }
        return ondoDago;
    }

    //Vamos a comprobar si hay algun barco ya colocado alrededor
    private boolean ingurunekoGelaxkakKonprobatu(String kokapena, int amaiera, int x, int y, Itsasontzia itsasontzia){
        boolean itsasontziaDago = false;
        Color c1 = null;
        Color c2 = null;
        Color c3 = null;

        //La orientacion del barco es horizontal
        if (kokapena == "Horizontala"){
            int hasiera = y;//Guardamos la posicion inicial del barco
            //Si la casilla no esta fuera del tablero
            if(!(y-1 < 0)){ 
            y = y-1; //Iniciamos una casilla mas atras de la que nos encontramos
            c1 = jokalariarenBotoiak[x][y].getBackground();
            if(c1 == Gelaxka.ITSASONTZIA_COLOREA){
                itsasontziaDago = true;
            }
           } 

           //Miramos los alrededores del barco en busca de otros barcos
            while(y <= amaiera + 1 && !itsasontziaDago){

                if(!(y > 9)){
                    c1 = jokalariarenBotoiak[x][y].getBackground();
                    if(!(x-1 < 0)){
                        c2 = jokalariarenBotoiak[x-1][y].getBackground();
                    } 
    
                    if(!(x+1 > 9)){
                        c3 = jokalariarenBotoiak[x+1][y].getBackground();
                    }
                }

                //Si el color de la casilla es 'ITSASONTZIA_COLOREA' es que hay un barco, por tanto, dejamos de buscar
                if(c1 == Gelaxka.ITSASONTZIA_COLOREA || c2 == Gelaxka.ITSASONTZIA_COLOREA || c3 == Gelaxka.ITSASONTZIA_COLOREA){
                    itsasontziaDago = true;
                }
                y++;
            }

            //Si no hay ningun barco donde habiamos marcado, entonces pintamos las casillas y creamos un barco en ese sitio
            if(!itsasontziaDago){
                for (int j = hasiera; j <= amaiera; j++){
                    jokalariarenBotoiak[x][j].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
                    Player.getNirePlayer().getNireTableroa().aldatuGelaxkaHau(x, j, Mota.ITSASONTZIA, itsasontzia);
                }
            }

        //La orientacion del barco es vertical
        }else{
            int hasiera = x; //Guardamos la posicion inicial del barco
            //Si la casilla no esta fuera del tablero
            if(!(x-1 < 0)){ 
                x = x-1; //Iniciamos una casilla mas atras de la que nos encontramos
                c1 = jokalariarenBotoiak[x][y].getBackground();
                if(c1 == Gelaxka.ITSASONTZIA_COLOREA){
                    itsasontziaDago = true;
                }
            }
            while(x <= amaiera + 1 && !itsasontziaDago){

                //Si las casillas no estan fuera del tablero, las comprueba
                if(!(x > 9)){
                    c1 = jokalariarenBotoiak[x][y].getBackground();
                    if(!(y-1 < 0)){
                        c2 = jokalariarenBotoiak[x][y-1].getBackground();
                    }
    
                    if(!(y +1 > 9)){
                        c3 = jokalariarenBotoiak[x][y+1].getBackground();
                    }
                }

                if(c1 == Gelaxka.ITSASONTZIA_COLOREA || c2 == Gelaxka.ITSASONTZIA_COLOREA || c3 == Gelaxka.ITSASONTZIA_COLOREA){
                    itsasontziaDago = true;
                } 
                x++;
            }

            //Si no hay ningun barco donde habiamos marcado, entonces pintamos las casillas y creamos un barco en ese sitio
            if(!itsasontziaDago){
                for (int i = hasiera; i <= amaiera; i++){
                    jokalariarenBotoiak[i][y].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
                    Player.getNirePlayer().getNireTableroa().aldatuGelaxkaHau(i, y, Mota.ITSASONTZIA, itsasontzia);
                }
            }
          }
        
        //Si ya hay un barco donde hemos marcado entonces suelta este mensaje
        if (itsasontziaDago){
            JOptionPane.showMessageDialog(this, "Itsasontziak txarto kokatu dituzu, hasi berriro");
        }

        return !itsasontziaDago;
    }

}