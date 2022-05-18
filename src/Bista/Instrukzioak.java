package Bista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Instrukzioak extends JFrame implements ActionListener {

    private JPanel orrialdea;
	private JPanel nagusia;

	private int orria;

	CardLayout cardLayout;
	String[] vectorCadena = {"hurrengoa", "pag1", "pag2"};

	//Esto solo para hacer pruebas
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instrukzioak inst = new Instrukzioak();
					inst.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public Instrukzioak() {
        nagusia = new JPanel(new BorderLayout());
        this.add(nagusia);
		orria = 1;
        panelaHasieratu();
    }

    public void panelaHasieratu(){
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("JOKOAREN INSTRUKZIOAK");
		this.setSize(new Dimension(600,600));
		this.setResizable(false);
		
		//JPanel para las páginas
		orrialdea = new JPanel();

		//Layout para las páginas 
		cardLayout = new CardLayout();
		orrialdea.setLayout(cardLayout);

		//Panel inferior para los botones
		JPanel behekoa = new JPanel(new GridLayout(1,7));

		//CONFIGURACION DE LAS PÁGINAS
		JLabel pag1 = new JLabel();
		File file = new File(".\\images\\instrukzioak\\pag1.png");
		try {
			labelConfig(pag1, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag2 = new JLabel();
		File file2 = new File(".\\images\\instrukzioak\\pag2.png");
		try {
			labelConfig(pag2, file2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag3 = new JLabel();
		File file3 = new File(".\\images\\instrukzioak\\pag3.png");
		try {
			labelConfig(pag3, file3);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag4 = new JLabel();
		File file4 = new File(".\\images\\instrukzioak\\pag4.png");
		try {
			labelConfig(pag4, file4);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag5 = new JLabel();
		File file5 = new File(".\\images\\instrukzioak\\pag5.png");
		try {
			labelConfig(pag5, file5);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag6 = new JLabel();
		File file6 = new File(".\\images\\instrukzioak\\pag6.png");
		try {
			labelConfig(pag6, file6);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel pag7 = new JLabel();
		File file7 = new File(".\\images\\instrukzioak\\pag7.png");
		try {
			labelConfig(pag7, file7);
		} catch (IOException e) {
			e.printStackTrace();
		}

		orrialdea.add(pag1, "pag1");
		orrialdea.add(pag2, "pag2");
		orrialdea.add(pag3, "pag3");
		orrialdea.add(pag4, "pag4");
		orrialdea.add(pag5, "pag5");
		orrialdea.add(pag6, "pag6");
		orrialdea.add(pag7, "pag7");

		//CONFIGURACION DEL PANEL INFERIOR DE BOTONES
		JButton hurrengoa = new JButton("Hurrengoa");
        buttonConfig(hurrengoa, "Next");

		JButton amaiera = new JButton("Amaiera");
        buttonConfig(amaiera, "Last");

		JButton hasiera = new JButton("Hasiera");
        buttonConfig(hasiera, "First");

		JButton aurrekoa = new JButton("Aurrekoa");
		buttonConfig(aurrekoa, "Prev");

		JButton irten = new JButton("Irten");
		buttonConfig(irten, "Irten");

		JLabel espazio1 = new JLabel("      ");
		JLabel espazio2 = new JLabel("      ");

		behekoa.add(hasiera);
		behekoa.add(aurrekoa);
		behekoa.add(espazio1);
		behekoa.add(irten);
		behekoa.add(espazio2);
		behekoa.add(hurrengoa);
		behekoa.add(amaiera);

		nagusia.add(orrialdea, BorderLayout.CENTER);
		nagusia.add(behekoa, BorderLayout.SOUTH);

    }

    private void buttonConfig(JButton button, String actionCommand){
		
		button.addActionListener(this);
		button.setActionCommand(actionCommand);
		button.setMaximumSize(new Dimension(10,10));
		button.setSize(new Dimension(10,10));
		button.setMinimumSize(new Dimension(10,10));
		button.setBorder(new LineBorder(Color.BLACK));
		button.setBackground(new Color(192, 145, 250));
		button.setOpaque(true);
	
	}

	private void labelConfig(JLabel label, File file) throws IOException{ 
		
		BufferedImage image = ImageIO.read(file);
		Image scaledImage = image.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImage);
		
		label.setIcon(imageIcon);
	}

    @Override
    public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		
		//Siguiente página
		if(str.equals("Next")){
			if(orria + 1 <= orrialdea.getComponentCount()){
				orria = orria + 1;
				cardLayout.show(orrialdea, ("pag" + orria));
			}
		//Página anterior
		} else if (str.equals("Prev")){ 
			if(orria - 1 >= 1){
				orria = orria - 1;
				cardLayout.show(orrialdea, ("pag" + orria));
			} 

		//Última página
		} else if(str.equals("Last")){
			orria = orrialdea.getComponentCount();
			cardLayout.show(orrialdea, ("pag" + orria));
		
		//Primera página
		} else if(str.equals("First")){
			orria = 1;
			cardLayout.show(orrialdea, ("pag" + orria));
		
		//Salir de las instrucciones
		} else if(str.equals("Irten")){
			this.dispose();
			new Menu().setVisible(true);
		}
    }
}


