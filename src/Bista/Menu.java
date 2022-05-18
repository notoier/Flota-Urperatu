package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Eredua.Gelaxka;


public class Menu extends JFrame implements ActionListener {
    
    private JPanel nagusia;




	//Esto solo para hacer pruebas
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu();
					menu.setVisible(true);
                    System.out.println(JOptionPane.showInputDialog("Marico"));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public Menu() {
        nagusia = new JPanel();
        this.add(nagusia);
        panelaHasieratu();
    }

    public void panelaHasieratu(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("FLOTA URPERTATU JOKOA");
		this.setSize(new Dimension(600,600));
		this.setResizable(false);

        nagusia.setBackground(Gelaxka.EZEZAGUNA_COLOREA);
        nagusia.setLayout(new BorderLayout());
        
        JLabel banner = new JLabel();
        File file = new File(".\\images\\bestelakoak\\banner.jpg");
        try {
            BufferedImage image = ImageIO.read(file);
            Image scaledImage = image.getScaledInstance(600, 150, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            banner.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nagusia.add(banner, BorderLayout.NORTH);

        JPanel erdikoa = new JPanel(new GridLayout(4,1));

        JButton berria = new JButton("Partida Berria");
        buttonConfig(berria, "Berria");

        JButton instrukzioak = new JButton("Instrukzioak");
        buttonConfig(instrukzioak, "Instrukzioak");

        JButton irten = new JButton("Irten");
        buttonConfig(irten, "Irten");

        JLabel espazio1 = new JLabel("FLOTA URPERATU", SwingConstants.CENTER);
        espazio1.setFont(new Font("DialogInput", Font.BOLD, 30));
        espazio1.setForeground(Color.BLACK);
        espazio1.setBackground(Gelaxka.EZEZAGUNA_COLOREA);
        espazio1.setOpaque(true);
        
        erdikoa.add(espazio1);
        erdikoa.add(berria);
        erdikoa.add(instrukzioak);
        erdikoa.add(irten);


        nagusia.add(erdikoa, BorderLayout.CENTER);

        JLabel espazio2 = new JLabel("                                            ");
        JLabel espazio3 = new JLabel("                                            ");
        JLabel espazio4 = new JLabel(" ");

        nagusia.add(espazio2, BorderLayout.EAST);
        nagusia.add(espazio3, BorderLayout.WEST);
        nagusia.add(espazio4, BorderLayout.SOUTH);
    
    }
    
    private void buttonConfig(JButton button, String actionCommand){
		
		button.addActionListener(this);
		button.setActionCommand(actionCommand);

		button.setMaximumSize(new Dimension(10,10));
		button.setSize(new Dimension(10,10));
		button.setMinimumSize(new Dimension(10,10));
        button.setBackground(new Color(192, 145, 250));

		button.setBorder(new LineBorder(Color.BLACK));
		button.setOpaque(true);
	
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String str = e.getActionCommand();

        //Empieza el juego
        if(str.equals("Berria")){
            new Kokapena().setVisible(true);
            this.dispose();
        }else if(str.equals("Instrukzioak")){
            new Instrukzioak().setVisible(true);
            this.dispose();
        //Salimos del juego
        } else if(str.equals("Irten")){
            this.dispose();
        }
        
    }
}
