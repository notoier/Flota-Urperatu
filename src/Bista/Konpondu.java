package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import Eredua.Egoera;
import Eredua.Gelaxka;
import Eredua.Itsasontzia;
import Eredua.Player;
import Eredua.Tableroa;


//ESTA INTERFAZ SIRVE PARA COLOCAR LOS BARCOS
public class Konpondu extends JFrame implements ActionListener{

    //Aqui se guardan todos los botones para poder seleccionarlos luego
    private static JButton[][] jokalariarenBotoiak; 

    public static Color KONPONDUTA = new Color(192, 145, 250);

    //Este es el boton que esta fuera del panel
    private JButton button = new JButton("");
    private JPanel nagusia = new JPanel(new BorderLayout(0, 0));


    
    //Constructor de la clase
	public Konpondu() {
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
		this.setTitle("ITSASONTZIA KONPONDU");
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
                Itsasontzia itsasontzia = Player.getNirePlayer().getNireTableroa().getGelaxka(l, z).getItsasontzia();
				if(itsasontzia != null && itsasontzia.getEgoera() == Egoera.UKITUTA){      
                    jokalariarenBotoiak[l][z].setBackground(Gelaxka.ITSASONTZIA_COLOREA);
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

    //Este metodo nos va a arreglar un punto de vida al barco que digamos 
    @Override
    public void actionPerformed(ActionEvent arg0) {
        int i = 1;
        int ilara = Integer.parseInt(arg0.getActionCommand().split(":")[0]);
        int zutabea = Integer.parseInt(arg0.getActionCommand().split(":")[1]);
        Tableroa tableroa = Player.getNirePlayer().getNireTableroa();
        Itsasontzia itsasontzia = tableroa.getGelaxka(ilara, zutabea).getItsasontzia();
        File file = new File(".\\images\\bestelakoak\\barco.png");
        try {
            BufferedImage image = ImageIO.read(file);
            Image scaledImage = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		    ImageIcon imageIcon = new ImageIcon(scaledImage);
            i = JOptionPane.showConfirmDialog(this, "Jasan dezakeen tiro kopurua " + itsasontzia.getTiroKop() + " da", "Itsasontzi hau konpondu nahi duzu?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i == 0){
            itsasontzia.konpondu();
            Player.getNirePlayer().getItsasontzienZerrenda().kenduDirua(100);
            int[] hasCoord = itsasontzia.getProa();
            int[] amCoord = itsasontzia.getPopa();
            GUI.gui.ItsasontziaKonponduDa(hasCoord, amCoord, true);
            this.dispose();
        }
    }
}

