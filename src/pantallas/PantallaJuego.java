package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Sprite;
import java.awt.Point;

import java.awt.event.KeyListener;

public class PantallaJuego implements Pantalla{

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
    private ArrayList<Sprite> circulos = new ArrayList<Sprite>();
    private Sprite bolaLanzamiento;
    private Sprite siguientebola;

    // COLORES
    private Color[] colorsBolas = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED };

    // Random
    private Random random;

    // Palo
    private Sprite palo;
    private int posX , posY;
    private Point p0, p1;

    private float x0, y0, x1, y1;

    private double angulo;

    public PantallaJuego(PanelJuego panelJuego, JFrame ventana) {
        this.panelJuego = panelJuego;
        this.ventana = ventana;
        posX = ventana.getWidth() / 2 +10;
        posY = ventana.getHeight() - 99;
        angulo = 90;
        



        //circulos = new Sprite[40];
        random = new Random();
    }

    @Override
    public void inicializarPantalla() {

        ventana.setBackground(new Color(180, 174, 244));
        int contador = 0;
        for (int i = 0; i < 40 / 10; i++) {
            for (int j = 0; j < 10; j++) {
                circulos.add(new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA,
                        ALTO_BOLA, ANCHO_BOLA * j, ALTO_BOLA * (i), 0, 0));

                contador++;
            }

        }

        x0 = ventana.getWidth()/2;
        y0 = ventana.getHeight() - 99;

        x1 = ventana.getWidth()/2;
        y1 = ventana.getHeight();
        float p0 = (x0 * (float) Math.cos(Math.toRadians(angulo))) - (y0 * (float) Math.sin(Math.toRadians(angulo)));
        float p1 = (x1 * (float) Math.cos(Math.toRadians(angulo))) - (y1 * (float) Math.sin(Math.toRadians(angulo)));

        palo = new Sprite(1, Color.BLACK, 10, 100, posX , posY , 0, 0);
        

        bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA, ALTO_BOLA,
                palo.getPosX() - ANCHO_BOLA/2 + palo.getAncho()/2, ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);

    }

    @Override
    public void pintarPantalla(Graphics g) {
        for (int i = 0; i < circulos.size(); i++) {
            circulos.get(i).estampar(g);
        }
        palo.estampar(g);
        bolaLanzamiento.estampar(g);
        
    }

    @Override
    public void ejecutarFrame() {
        boolean colicion = false;

       /*  try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */
        bolaLanzamiento.mover(ventana.getWidth(), ventana.getHeight());

        for (int i = 0; i < circulos.size(); i++) {
            if(bolaLanzamiento.colisiona(circulos.get(i))){
                    //TODO: Mejorar colisiones y movimientos
                if (circulos.get(i).getColor() == bolaLanzamiento.getColor()) {
                    System.out.println("Colision");
                    bolaLanzamiento = null;
                    circulos.remove(i);
                    System.out.println("Puntos: +1");
                    bolaLanzamiento = new Sprite(0, colorsBolas[random.nextInt(colorsBolas.length)], ANCHO_BOLA,
                            ALTO_BOLA, palo.getPosX() - ANCHO_BOLA / 2 + palo.getAncho() / 2,
                            ventana.getHeight() - ANCHO_BOLA * 2, 0, 0);

                } else {
                    bolaLanzamiento.setPosX(circulos.get(i).getPosX());
                    bolaLanzamiento.setPosY(circulos.get(i).getPosY() + ANCHO_BOLA);
                    circulos.add(bolaLanzamiento);
                }
                
            }
            
        }
        ventana.repaint();
        
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


    @Override
    public void moverFlecha(KeyEvent e) {
        switch(e.getKeyCode()){
			case KeyEvent.VK_A:

                /* int x = (int) Math.cos(angulo)*palo.getAlto();
                int y = (int) Math.sin(angulo)*palo.getAlto();
				palo.setPosX(x*y);
                palo.setPosY(posY); */
                    /* palo = null;
                    palo = new Sprite(1, Color.BLACK, 10, 100, posX, posY, 0, 0);
 */
                    
                    if(palo.getPosX() < 0){
                        System.out.println("No se puede mover más");
                        palo.setPosX(ANCHO_BOLA );
                        posX = palo.getPosX();
                        bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA/2+palo.getAncho()/2  );
                    } else{
                        palo.setPosX(palo.getPosX() - ANCHO_BOLA/2);
                        palo.setPosY(palo.getPosY());
                        posX = palo.getPosX();
                        posY = palo.getPosY();
                        bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA/2+palo.getAncho()/2);
                    }

               
                
                
				break;
			case KeyEvent.VK_D:
                /* palo = null;
                palo = new Sprite(1, Color.BLACK, 10, 100, posX, posY , 0, 0);
 */
                
                if(palo.getPosX() > ventana.getWidth()){
                    System.out.println("No se puede mover más");
                    palo.setPosX(ventana.getWidth() - ANCHO_BOLA);
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA/2+palo.getAncho()/2);
                } else {
                    palo.setPosX(palo.getPosX() + ANCHO_BOLA);
                    palo.setPosY(palo.getPosY());
                    posX = palo.getPosX();
                    posY = palo.getPosY();
                    bolaLanzamiento.setPosX(palo.getPosX() - ANCHO_BOLA/2+palo.getAncho()/2);
                }


				break;

            case KeyEvent.VK_SPACE:
                System.out.println("Disparo");
                bolaLanzamiento.setVelY(-5);
               
                break;
		}

        
        ventana.repaint();
        

    }

    @Override
    public void dejarDePulsar(KeyEvent e) {
        

    }

    

    
    
}
