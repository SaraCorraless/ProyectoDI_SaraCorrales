package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;

/**
 * Pantalla de fin del juego
 * 
 * @author Sara Corrales Santos
 */

public class PantallaFin implements Pantalla {

    // ATRUBUTOS
    private PanelJuego panelJuego;
    private BufferedImage fondo;
    private int puntos;

    // CIRCULOS
    private ArrayList<Sprite> circulos = new ArrayList<Sprite>();

    // COLORES
    private Color[] colorsBolas = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED };

    // Random
    private Random random;

    // CONSTANTES
    private final static int ANCHO_BOLA = 40;
    private final static int ALTO_BOLA = 40;


    // CONSTRUCTOR
    public PantallaFin(PanelJuego panelJuego, int puntos) {
        this.panelJuego = panelJuego;
        this.puntos = puntos;
        random = new Random();
    }

    @Override
    public void inicializarPantalla() {
        for (int i = 0; i < 8; i++) {
            circulos.add(new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                        10, 10, random.nextInt(10), random.nextInt(10)));
        
        }
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        for (int i = 0; i < circulos.size(); i++) {
            circulos.get(i).estampar(g);
        }

    }

    @Override
    public void ejecutarFrame() {

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < circulos.size(); i++) {
            circulos.get(i).mover(panelJuego.getWidth(), panelJuego.getHeight());
        }
    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
    }

    /**
     * Método que genera el fondo en la pantalla
     * 
     * @param g
     */
    private void rellenarFondo(Graphics g) {
        fondo = null;

        try {
            fondo = ImageIO.read(new File("src\\Imagenes\\fon.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH), 0, 0,
                null);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        g.setColor(Color.RED);
        g.drawString("FINAL DEL JUEGO", panelJuego.getWidth() / 2 - 130, panelJuego.getHeight() / 2 - 30);
        g.drawString("PUNTOS: " + puntos, panelJuego.getWidth() / 2 - 130, panelJuego.getHeight() / 2 + 30);

    }

    @Override
    public void controles(KeyEvent e) {}

}
