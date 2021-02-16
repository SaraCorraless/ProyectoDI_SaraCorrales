package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import principal.PanelJuego;
import principal.Pantalla;

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

    // CONSTRUCTOR
    public PantallaFin(PanelJuego panelJuego, int puntos) {
        this.panelJuego = panelJuego;
        this.puntos = puntos;
    }

    @Override
    public void inicializarPantalla() {
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
    }

    @Override
    public void ejecutarFrame() {
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
    }

    @Override
    public void moverRaton(MouseEvent e) {
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
        g.drawString("PUNTOS: "+puntos, panelJuego.getWidth() / 2 - 130, panelJuego.getHeight() / 2 +30);

    }

    @Override
    public void moverFlecha(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dejarDePulsar(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
