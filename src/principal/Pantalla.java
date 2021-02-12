package principal;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;
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
     * Método que contiene las acciones que se realizan cuando el usiario hace click derecho o izquierdo
     */
    public void pulsarRaton(MouseEvent e);

    /**
     * Método que contiene las acciones que se realizan cuando el ratón cambia de posición
     * 
     * @param e
     */
    public void moverRaton(MouseEvent e);

    /**
     * Método que redimensiona la pantalla para que nierda calidad
     */
    public void redimensionarPantalla(ComponentEvent e);


    public void moverFlecha(KeyEvent e);

    public void dejarDePulsar(KeyEvent e);
}