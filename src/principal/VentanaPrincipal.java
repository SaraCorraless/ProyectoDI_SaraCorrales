package principal;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
/**
 * Ventana Principal
 * @author Sara Corrales Santos
 */
public class VentanaPrincipal {

	//La ventana principal, en este caso, guarda todos los componentes:
	private JFrame ventana;
	private PanelJuego panelJuego;



	/**
     * Constructor, marca el tamaño y el cierre del frame
     */
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(20, 20, 416, 600);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	/**
     * Inicializa todos los componentes del frame
     */
	public void inicializarComponentes(){
		//Definimos el layout:
		ventana.setLayout(new GridLayout(1,1));
		
		//PANEL JUEGO
		panelJuego = new PanelJuego(ventana);
		ventana.add(panelJuego);
		ventana.setResizable(false);
	}
	
	/**
     * Inicializa todos los lísteners del frame
     */
	public void inicializarListeners(){

	}

	/**
	 * Método que realiza todas las llamadas necesarias para inicializar la ventana correctamente.
	 */
	public void inicializar(){
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners();		
	}
}
