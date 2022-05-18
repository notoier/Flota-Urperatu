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

import Eredua.Egoera;
import Eredua.Ezkutu;
import Eredua.Gelaxka;
import Eredua.Itsasontzia;

import Eredua.Mota;
import Eredua.Player;
import Eredua.Tableroa;


//ESTA INTERFAZ SIRVE PARA COLOCAR LOS BARCOS
public class Babestu extends JFrame implements ActionListener{

    public static Babestu ezkutua;

    //Aqui se guardan todos los botones para poder seleccionarlos luego
    private static JButton[][] jokalariarenBotoiak; 

    //Este es el boton que esta fuera del panel
    private JButton button = new JButton("");
    private JPanel nagusia = new JPanel(new BorderLayout(0, 0));
    
    //Constructor de la clase
	public Babestu() {
		panelaHasieratu();
	}

    //Desde aqui podemos comprobar si funciona el panel
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Babestu frame = new Babestu();
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
		this.setTitle("EZKUTUA KOKATU");
		this.setSize(new Dimension(600,600));
		this.setResizable(false);

        //Inicializamos nuestra matriz de botones
		jokalariarenBotoiak = new JButton[10][10];
		
        //Ajustamos el panel principal
        nagusia.setBackground(new Color(51,102,0));
		nagusia.setPreferredSize(new Dimension(350,350));
		
        //Introducimos el panel secundario en el que iran los botones
        JPanel matrix = new JPanel(new GridLayout(10,10, 0, 0));
		nagusia.add(matrix);
        
        //Llamamos a la funcion que nos va a llenar el panel de botones
        matrizeaSortu(matrix);
        matrizeaMargoztu(matrix);

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

    private void matrizeaMargoztu(JPanel pNirea){
        for(int l=0;l<10;l++) {
			for(int z=0;z<10;z++) {
                Mota mota = Player.getNirePlayer().getNireTableroa().getMota(l, z);
                Itsasontzia itsasontzia = Player.getNirePlayer().getNireTableroa().getGelaxka(l, z).getItsasontzia();
                if(itsasontzia != null){
                    if(mota == Mota.ITSASONTZIA && itsasontzia.getEgoera() != Egoera.HONDORATUTA){
                        jokalariarenBotoiak[l][z].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
                    } else if(mota == Mota.EZKUTUA){
                        jokalariarenBotoiak[l][z].setBackground(Gelaxka.EZKUTUA_COLOREA);
                    }
                           
                }else{
                    jokalariarenBotoiak[l][z].setBackground(Gelaxka.URA_COLOREA);
                    jokalariarenBotoiak[l][z].setEnabled(false);
                    }
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

    public static void gelaxkakMargoztu(int[] hasCoord, int[] amCoord, Tableroa tablero){ 
    	
        int x1 = hasCoord[0];
        int y1 = hasCoord[1];

        int x2 = amCoord[0];
        int y2 = amCoord[1];

        //Horizontal
        if(x1 == x2){
            if(y1 < y2){
                for (int i = y1; i <= y2; i++){
                    tablero.getGelaxka(x1,i).aldatuMota(Mota.EZKUTUA);
                    jokalariarenBotoiak[x1][i].setBackground(Gelaxka.EZKUTUA_COLOREA);
                }
            } else {
                for (int i = y2; i <= y1; i++){
                    tablero.getGelaxka(x1,i).aldatuMota(Mota.EZKUTUA);
                    jokalariarenBotoiak[x1][i].setBackground(Gelaxka.EZKUTUA_COLOREA);
                }
            }
        //Vertical
        } else {
            if(x1 < x2){
                for (int j = x1; j <= x2; j++){
                    tablero.getGelaxka(j,y1).aldatuMota(Mota.EZKUTUA);
                    jokalariarenBotoiak[j][y1].setBackground(Gelaxka.EZKUTUA_COLOREA);
                }
            } else {
                for (int j = x2; j <= x1; j++){
                    tablero.getGelaxka(j,y1).aldatuMota(Mota.EZKUTUA);
                    jokalariarenBotoiak[j][y1].setBackground(Gelaxka.EZKUTUA_COLOREA);
                }
            }
        }
    }

    //Este metodo nos va a colocar un escudo en el barco que digamos 
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(!Player.getNirePlayer().ezkutuaErabiliDu()){
            int ilara = Integer.parseInt(arg0.getActionCommand().split(":")[0]);
            int zutabea = Integer.parseInt(arg0.getActionCommand().split(":")[1]);
            Tableroa tablero = Player.getNirePlayer().getNireTableroa();
            Itsasontzia itsasontzia = tablero.getGelaxka(ilara, zutabea).getItsasontzia();
            Ezkutu ezkutua = (Ezkutu) Player.getNirePlayer().getiItsasontzienZerrenda().getArmamentua().billatuArma("Ezkutu");
            //Si tenemos al menos un escudo
            ezkutua.akzioaBurutu(itsasontzia, tablero, true);
            Player.getNirePlayer().setEzkutua(true);
            
       } else {
           JOptionPane.showMessageDialog(this, "Ezin duzu ezkutu gehiago jarri");
           this.dispose();
       }
    }



}
