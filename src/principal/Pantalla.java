package principal;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;


/**
 * Métodos fundamentales de una pantalla
 */
public interface Pantalla {

    /**
     * Método que genera los elementos que se visualizan en la pantalla
     */
    public void inicializarPantalla();

    /**
     * Método que estampa los elementos para que sean visibles en la pantalla
     * @param g
     */
    public void pintarPantalla(Graphics g);
    
    /**
     * Método que contiene las acciones que va a ejecutar el hilo cuando se inicie
     */
    public void ejecutarFrame();

    /**
     * Método que redimensiona la pantalla para que nierda calidad
     */
    public void redimensionarPantalla(ComponentEvent e);

    /**
     * Método que contiene los controles del juego
     */
    public void controles(KeyEvent e);

}