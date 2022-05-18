package Nagusia;

import javax.swing.JOptionPane;

import Bista.GUI;
import Bista.Menu;
import Eredua.CPU1;
import Eredua.Player;

public class Nagusia {


public static void main(String[] args) throws InterruptedException{
		hasieratu();
		CPU1.getNireCPU1().getItsasontzienZerrenda();
		Thread mainThread = new Thread(new Runnable(){
		
			@Override
			public void run(){ 
				boolean irabazlea = false;
				while(!irabazlea){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					irabazlea = jokatu();
				}
			}
		});
		mainThread.start();
	}
	
/*
	public static void main(String[] args){
		hasieratu();
		CPU1.getNireCPU1().getItsasontzienZerrenda();
		boolean irabazlea = false;
		while(!irabazlea){
			System.out.println(" ");
			irabazlea = jokatu();
		}
	}
 */

	private static void hasieratu(){ 
		//Inicia el jugador
		Player.getNirePlayer();

		//Inicia la CPU y hace que coloque sus barcos
		CPU1.getNireCPU1().kokatu();

		//Abre la ventana en la que colocaremos los barcos
		new Menu().setVisible(true);
	}

	private static boolean jokatu(){ 
		boolean irabazlea = false;
		boolean txanda = Player.getNirePlayer().nireTxandaDa();
		
		if(!txanda){ 

			CPU1.getNireCPU1().txandaJokatu();
		}
		
		int jokalariarenOntziak = Player.getNirePlayer().getiItsasontzienZerrenda().getOtziBiziKop();
		int cpuOntziak = CPU1.getNireCPU1().getItsasontzienZerrenda().getOtziBiziKop();
		
		//Comprobamos si hay algun ganador
		if (cpuOntziak <= 0) {
		
			JOptionPane.showMessageDialog(GUI.gui,"JOKALARIAK IRABAZI DU, ZORIONAK!!");
			irabazlea = true;
			GUI.gui.dispose();
		}
		else if (jokalariarenOntziak <= 0) {
			
			JOptionPane.showMessageDialog(GUI.gui,"CPU-AK IRABAZI DU, SAIATU BERRIRO");
			irabazlea = true;
			GUI.gui.dispose();
		}

		return irabazlea;
	}
}

