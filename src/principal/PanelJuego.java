package principal;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pantallas.PantallaInicio;

/**
 * @author Sara Corrales Santos
 */
public class PanelJuego extends JPanel implements Runnable, ComponentListener, KeyListener {

    private static final long serialVersionUID = 1L;

    // Instancia de la pantalla que se ejuta en se momento
    private Pantalla pantallaActual;

    /**
     * Constructor de lla clase
     */
    public PanelJuego(JFrame ventana) {
        // inicio del atributo pantallaActual con una referencia de la propia clase
        // PanelJuego
        pantallaActual = new PantallaInicio(this, ventana);

        // Inicio de los listenners con la referencia a la clase PanelJuego
        this.addComponentListener(this);
        /*
         * this.addMouseListener(this); this.addMouseMotionListener(this);
         */
        setFocusable(true);
        this.addKeyListener(this);

        // Inicio del hilo de la clase
        new Thread(this).start();

    }

    // Método que se llama automáticamente para pintar el componente.
    @Override
    public void paintComponent(Graphics g) {
        pantallaActual.pintarPantalla(g);
    }

    /**
     * Método que contiene el comportamiento del hilo
     */
    @Override
    public void run() {
        pantallaActual.inicializarPantalla();

        while (true) {
            pantallaActual.ejecutarFrame();
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    // MÉTODOS DE LA INTERFAZ COMPONENTLISTENER
    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    /**
     * Método que permite cambiar de pantalla en el juego
     * 
     * @param nuevaPantalla
     */
    public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicializarPantalla();
        pantallaActual = nuevaPantalla;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pantallaActual.controles(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
