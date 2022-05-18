package Bista;

import Eredua.Arma;
import Eredua.Biltegia;
import Eredua.Bonba;
import Eredua.CPU1;
import Eredua.Ezkutu;
import Eredua.Misil;
import Eredua.Player;
import Eredua.Radar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;

public class Denda extends JFrame implements ActionListener, Observer{

    private static JPanel Nagusia;
	public static Denda denda = new Denda();

	private static JLabel[] kopuruak;
	private JLabel dirua;

	//Esto solo para hacer pruebas
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CPU1.getNireCPU1().kokatu();
					Denda denda = new Denda();
					denda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public Denda(){
        
        //Ajustes de la ventana
        setTitle("ONGI ETORRI DENDARA!!");
        setLocationRelativeTo(null);
        setBounds(100, 100, 800,350);
        this.setResizable(false);
        Nagusia = new JPanel();
        
		setContentPane(Nagusia);
		Nagusia.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		Nagusia.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		//CREACION Y AJUSTES DE LA PRIMERA FILA DE JLABELS

		JLabel BONBA = new JLabel();
		
		//Ajustes de bomba
		
		File fileBonba = new File(".\\images\\denda\\bomba.png");
		try {
			labelConfig(BONBA, fileBonba);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.add(BONBA);
		 
		JLabel MISILA = new JLabel("");
		
		//Ajustes de misil
		File fileMisila = new File(".\\images\\denda\\misil.png");
		try {
			labelConfig(MISILA, fileMisila);
		} catch (IOException e) {

			e.printStackTrace();
		}
		panel.add(MISILA);
		
		JLabel EZKUTUA = new JLabel("");
		
		//Ajustes de escudo
		File fileEzkutua = new File(".\\images\\denda\\escudo.png");
		try {
			labelConfig(EZKUTUA, fileEzkutua);
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		panel.add(EZKUTUA);
		
		JLabel RADARRA = new JLabel("");
		
		//Ajustes de radar
		File fileRadar = new File(".\\images\\denda\\radar.png");
		try {
			labelConfig(RADARRA, fileRadar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.add(RADARRA);
		
		JLabel KONPONDU = new JLabel("");
		
		//Ajustes de reparacion del barco
		File fileKonpondu = new File(".\\images\\denda\\reparar.png");
		try {
			labelConfig(KONPONDU, fileKonpondu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.add(KONPONDU);
		

		//CREACION Y AJUSTES DE LOS BOTONES

		JButton BONBABTN = new JButton("20\u20AC");
		
		//Ajustes del boton bonba
		buttonConfig(BONBABTN, "Bonba");
		panel.add(BONBABTN);
		
		JButton MISILABTN = new JButton("35\u20AC");
		
		//Ajustes del boton misila
		buttonConfig(MISILABTN, "Misila");
		panel.add(MISILABTN);

		
		JButton EZKUTUABTN = new JButton("60\u20AC");
		
		//Ajustes del boton ezkutua
		buttonConfig(EZKUTUABTN, "Ezkutua");
		panel.add(EZKUTUABTN);

		
		JButton RADARRABTN = new JButton("75\u20AC");
		
		//Ajustes del boton radar
		buttonConfig(RADARRABTN, "Radar");
		panel.add(RADARRABTN);

		
		JButton KONPONDUBTN = new JButton("100\u20AC");
		
		//Ajustes del boton konpondu
		buttonConfig(KONPONDUBTN, "Konpondu");
		panel.add(KONPONDUBTN);

		kopuruak = new JLabel[5];

		JLabel BONBAKOP = new JLabel("Dituzun kopurua: 125");
		labelKopConfig(BONBAKOP);
		kopuruak[0] = BONBAKOP;
		panel.add(kopuruak[0]);
		
		JLabel MISILKOP = new JLabel("Dituzun kopurua: 5");
		labelKopConfig(MISILKOP);
		kopuruak[1] = MISILKOP;
		panel.add(kopuruak[1]);
		
		JLabel EZKUTUKOP = new JLabel("Dituzun kopurua: 2");
		labelKopConfig(EZKUTUKOP);
		kopuruak[2] = EZKUTUKOP;
		panel.add(kopuruak[2]);
		
		JLabel RADARKOP = new JLabel("Dituzun kopurua: 5");
		labelKopConfig(RADARKOP);
		kopuruak[3] = RADARKOP;
		panel.add(kopuruak[3]);
		
		dirua = new JLabel("ZURE DIRUA : 150");
		dirua.setFont(new Font("Tahoma", Font.PLAIN, 36));
		dirua.setHorizontalAlignment(SwingConstants.CENTER);
		Nagusia.add(dirua, BorderLayout.NORTH);
		
		JLabel espacio = new JLabel("         ");
		Nagusia.add(espacio, BorderLayout.WEST);
		
		JLabel espacio2 = new JLabel("         ");
		Nagusia.add(espacio2, BorderLayout.EAST);
		
		JButton irten = new JButton("Irten");
		buttonConfig(irten, "Irten");
		Nagusia.add(irten, BorderLayout.SOUTH);
        

    }

	public void hasieratuDirua(int diru){
		dirua.setText("ZURE DIRUA: " + diru + "€"); 
	}

	private void labelConfig(JLabel label, File file) throws IOException{ 
		
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		BufferedImage image = ImageIO.read(file);
		Image scaledImage = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImage);
		
		label.setIcon(imageIcon);
	}

	private void labelKopConfig(JLabel label){

		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		

	}


	private void buttonConfig(JButton button, String actionCommand){
		
		button.addActionListener(this);
		button.setActionCommand(actionCommand);

		button.setMaximumSize(new Dimension(10,10));
		button.setSize(new Dimension(10,10));
		button.setMinimumSize(new Dimension(10,10));

		button.setBorder(new LineBorder(Color.BLACK));
		button.setOpaque(true);
	
	}


    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
       
		if(str.equals("Konpondu")){
            if(Player.getNirePlayer().getItsasontzienZerrenda().getDirua() >= 100) {
				Konpondu k = new Konpondu();
				this.dispose();
				k.setVisible(true);	
			} else { 
				JOptionPane.showMessageDialog(this, "Ez duzu diru nahikorik");
			}

        } else if (str.equals("Bonba")){
			Bonba bonba = (Bonba) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Bonba");
			if (Biltegia.getNireBiltegia().getBombaKop() >= 1){ 
				bonba.erosi(1, 20, true);
			} else { 
				JOptionPane.showMessageDialog(this, "Ezin dira bonba gehiago erosi, biltegiko bonba kopurua 0 da");
			}
		} else if (str.equals("Misila")){
			Misil misil = (Misil) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Misil");
			if (Biltegia.getNireBiltegia().getMisilKop() >= 1){ 
				misil.erosi(2, 35, true);	
			} else { 
				JOptionPane.showMessageDialog(this, "Ezin dira misil gehiago erosi, biltegiko misil kopurua 0 da");
			}
		} else if (str.equals("Ezkutua")){
			Ezkutu ezkutua = (Ezkutu) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Ezkutu");
			if (Biltegia.getNireBiltegia().getEzkutuKop() >= 1){ 
				ezkutua.erosi(3, 60, true);	
			} else { 
				JOptionPane.showMessageDialog(this, "Ezin dira ezkutu gehiago erosi, biltegiko ezkutu kopurua 0 da");
			}
		} else if (str.equals("Radar")){
			Radar radar = (Radar) Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Radar");
			if (Biltegia.getNireBiltegia().getMisilKop() >= 1){ 
				radar.erosi(4, 75, true);		
			} else { 
				JOptionPane.showMessageDialog(this, "Ezin dira radar gehiago erosi, biltegiko radar kopurua 0 da");
			}
		} else if (str.equals("Irten")){
			this.dispose();
		}
    }

	@Override
    public void update(Observable o, Object arg) {
        
        int arma = (Integer) arg;
        Integer kopurua; //Importante que sea un Integer para poder hacer toString()
		int diru = Player.getNirePlayer().getItsasontzienZerrenda().getDirua();
		dirua.setText("ZURE DIRUA: " + diru + "€");
        switch (arma) {

            //BOMBA
            case 1: 
                Arma bonba = Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Bonba");
                kopurua = (Integer) bonba.getKopurua();
				kopuruak[0].setText("Dituzun kopurua:" + kopurua.toString());
                break;
            
            //MISIL
            case 2: 
                Arma misil = Player.getNirePlayer().getItsasontzienZerrenda().getArmamentua().billatuArma("Misil");
                kopurua = misil.getKopurua();
                kopuruak[1].setText("Dituzun kopurua:" + kopurua.toString());
				break;
        
            //ESCUDO 
            case 3: 
                Arma ezkutua = Player.getNirePlayer().getiItsasontzienZerrenda().getArmamentua().billatuArma("Ezkutu");
                kopurua = ezkutua.getKopurua();
				kopuruak[2].setText( "Dituzun kopurua:" + kopurua.toString());
                break;

            //RADAR 
            case 4: 
				Arma radar = Player.getNirePlayer().getiItsasontzienZerrenda().getArmamentua().billatuArma("Radar");
                kopurua = radar.getKopurua();
				kopuruak[3].setText("Dituzun kopurua:" + kopurua.toString());
                break;
        
        }
    }
}
