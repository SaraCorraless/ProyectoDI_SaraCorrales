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
import javax.swing.JFrame;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;


/**
 * Clase donde se controla toda la pantalla de juego 
 * 
 * @author Sara Corrales Santos
 */
public class PantallaJuego implements Pantalla {

    // Fondo
    private BufferedImage fondo;
    private Image redimension;

    // Instancia panel juego
    private PanelJuego panelJuego;

    // referencia a la ventana
    private JFrame ventana;

    // CONSTANTES
    private final static int ANCHO_BOLA = 40;
    private final static int ALTO_BOLA = 40;

    // CIRCULOS
    private ArrayList<Sprite> circulos = new ArrayList<Sprite>();
    private Sprite bolaLanzamiento;

    // COLORES
    private Color[] colorsBolas = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED };

    // Random
    private Random random;

    // Palo
    private Sprite palo;
    private int posX, posY;

    // Puntos
    private int puntos = 0;

    // Constructor
    public PantallaJuego(PanelJuego panelJuego, JFrame ventana) {
        this.panelJuego = panelJuego;
        this.ventana = ventana;
        posX = ventana.getWidth() / 2 - 30;
        posY = ventana.getHeight() - 99;
        random = new Random();
    }

    @Override
    public void inicializarPantalla() {

        int contador = 0;
        for (int i = 0; i < 40 / 10; i++) {
            for (int j = 0; j < 10; j++) {
                circulos.add(new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                        ANCHO_BOLA * j, ALTO_BOLA * i, 0, 0));

                contador++;
            }

        }

        palo = new Sprite(1, Color.BLACK, 10, 100, posX, posY, 0, 0);

        bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2, ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);

    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        for (int i = 0; i < circulos.size(); i++) {
            circulos.get(i).estampar(g);
        }
        palo.estampar(g);
        bolaLanzamiento.estampar(g);

    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bolaLanzamiento.moverSinBordes();

        if (bolaLanzamiento.getPosY() < 0) {
            System.out.println("Esta fuera");
            bolaLanzamiento.setPosY(0);
            circulos.add(bolaLanzamiento);
            bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                    palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2, ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);
        }

        for (int i = 0; i < circulos.size(); i++) {
            if (bolaLanzamiento.colisiona(circulos.get(i))) {
                // si la bola con la que se colisiona es del mismo color que la que se ha
                // lanzado se eliminan las dos
                if (circulos.get(i).getColor() == bolaLanzamiento.getColor()) {
                    System.out.println("Colision");
                    bolaLanzamiento = null;
                    circulos.remove(i);
                    System.out.println("Puntos: +1");
                    puntos++;
                    bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA,
                            ALTO_BOLA, palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2,
                            ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);

                } else {
                    // si no es del mismo color la bola se coloca debajo de ella
                    bolaLanzamiento.setPosX(circulos.get(i).getPosX());
                    bolaLanzamiento.setPosY(circulos.get(i).getPosY() + ANCHO_BOLA);
                    circulos.add(bolaLanzamiento);
                    bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA,
                            ALTO_BOLA, palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2,
                            ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);
                }

            }

        }
        ventana.repaint();

        // si se eliminan 15 bolas obtenemos 15 puntos y se acaba el juego
        if (puntos == 15) {
            panelJuego.cambiarPantalla(new PantallaFin(panelJuego, puntos));
        }

    }

    // Método que pone la imagen de fondo y el contador de puntos
    private void rellenarFondo(Graphics g) {
        fondo = null;

        try {
            fondo = ImageIO.read(new File("src\\Imagenes\\fondoBolas.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH), 0, 0,
                null);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("PUNTOS: " + puntos, panelJuego.getWidth() - 130, panelJuego.getHeight() - 10);

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        redimensionarFondo();

    }

    @Override
    public void controles(KeyEvent e) {
        switch (e.getKeyCode()) {
            // si se pulsa la tecla A se mueve una posición a la izquierda el disparador con
            // la bola que se va a lanzar
            case KeyEvent.VK_A:

                if (palo.getPosX() < 0) {
                    System.out.println("No se puede mover más");
                    palo.setPosX(ANCHO_BOLA / 2);
                    posX = palo.getPosX();
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2);
                } else {
                    palo.setPosX(palo.getPosX() - ANCHO_BOLA);
                    palo.setPosY(palo.getPosY());
                    posX = palo.getPosX();
                    posY = palo.getPosY();
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2);
                }

                break;

            // si se pulsa la tecla D se mueve ima posición a la derecha el disparador con
            // la bola
            case KeyEvent.VK_D:

                if (bolaLanzamiento.getPosX() > ventana.getWidth() - ANCHO_BOLA) {
                    System.out.println("No se puede mover más");
                    palo.setPosX(ventana.getWidth() - ANCHO_BOLA);
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2);
                } else {
                    palo.setPosX(palo.getPosX() + ANCHO_BOLA);
                    palo.setPosY(palo.getPosY());
                    posX = palo.getPosX();
                    posY = palo.getPosY();
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2);

                }

                break;
            // si se pulsa la barra espaciadora la bola se disparara en la dirección a la
            // que apunta el disparador
            case KeyEvent.VK_SPACE:
                System.out.println("Disparo");
                bolaLanzamiento.setVelY(-5);

                break;
        }

        ventana.repaint();

    }

    /**
     * Método que permite redimensionar el fondo
     */
    public void redimensionarFondo() {
        redimension = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
    }

}
