package principal;

import java.awt.EventQueue;

/**
 * Ejercicio 
 * @author Sara Corrales Santos
 */
public class Principal {
    
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.inicializar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});	
	}
}
