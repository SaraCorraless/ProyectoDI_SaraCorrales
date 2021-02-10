package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;

import java.awt.event.KeyListener;

public class PantallaJuego implements Pantalla, KeyListener {

    // Fondo
    private BufferedImage fondo;

    // Instancia panel juego
    private PanelJuego panelJuego;

    // referencia a la ventana
    private JFrame ventana;

    // CONSTANTES
    private final static int ANCHO_BOLA = 40;
    private final static int ALTO_BOLA = 40;

    // CIRCULOS
    private Sprite[] circulos;
    private Sprite bolaLanzamiento;
    private Sprite siguientebola;

    // COLORES
    private Color[] colorsBolas = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED };

    // Random
    private Random random;

    // Palo
    private Sprite palo;

    public PantallaJuego(PanelJuego panelJuego, JFrame ventana) {
        this.panelJuego = panelJuego;
        this.ventana = ventana;

        circulos = new Sprite[40];
        random = new Random();
    }

    @Override
    public void inicializarPantalla() {

        ventana.setBackground(new Color(180, 174, 244));
        int contador = 0;
        for (int i = 0; i < circulos.length / 10; i++) {
            for (int j = 0; j < 10; j++) {
                circulos[contador] = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA,
                        ALTO_BOLA, ANCHO_BOLA * j, ALTO_BOLA * (i), 0, 0);

                contador++;
            }

        }

        palo = new Sprite(1, Color.BLACK, 10, 100, ventana.getWidth() / 2, ventana.getHeight() - 99, 0, 0);

        bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                ventana.getWidth() / 2 - 25, ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);

    }

    @Override
    public void pintarPantalla(Graphics g) {
        for (int i = 0; i < circulos.length; i++) {
            circulos[i].estampar(g);
        }

        bolaLanzamiento.estampar(g);
        palo.estampar(g);
    }

    @Override
    public void ejecutarFrame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moverRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        // TODO Auto-generated method stub

    }


    //Listeners teclas
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
       if (e.) {
           
       }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    
    
}
