package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pantallas.*;



/**
 * @author Sara Corrales Santos
 */
public class PanelJuego extends JPanel implements Runnable, MouseListener, MouseMotionListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    // Instancia de la pantalla que se ejuta en se momento
    private Pantalla pantallaActual;

    //Instancia de la ventana principal
    private JFrame ventana;





    /**
     * Constructor de lla clase
     */ 
    public PanelJuego(JFrame ventana) {
        this.ventana = ventana;
        //inicio del atributo pantallaActual con una referencia de la propia clase PanelJuego
        pantallaActual = new PantallaJuego(this, ventana);
        

        //Inicio de los listenners con la referencia a la clase PanelJuego
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //Inicio del hilo de la clase
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

    //MÉTODOS DE LA INTERFAZ MOUSELISTENER
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        pantallaActual.pulsarRaton(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    //MÉTODOS DE LA INTERFAZ MOUSEMOTIONLISTENER
    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        pantallaActual.moverRaton(e);
    }

    //MÉTODOS DE LA INTERFAZ COMPONENTLISTENER
    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}

    /**
     * Método que permite cambiar de pantalla en el juego
     * @param nuevaPantalla
     */
    public void cambiarPantalla(Pantalla nuevaPantalla) {
        nuevaPantalla.inicializarPantalla();
        pantallaActual = nuevaPantalla;
    }


    
}
