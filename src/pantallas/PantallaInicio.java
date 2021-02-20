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
 * Pantalla de inicio
 * 
 * @author Sara Corrales Santos
 */

public class PantallaInicio implements Pantalla {

    // ATRUBUTOS
    private Color colorIntro = Color.RED;
    private BufferedImage fondo;
    private PanelJuego panelJuego;

    // referencia a la ventana
    private JFrame ventana;


    // CONSTRUCTOR
    public PantallaInicio(PanelJuego panelJuego, JFrame ventana) {
        this.panelJuego = panelJuego;
        this.ventana = ventana;
    }

    @Override
    public void inicializarPantalla() {}

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
    }

    /**
     * Método que genera el fondo en la pantalla
     * 
     * @param g
     */
    private void rellenarFondo(Graphics g) {
        fondo = null;

        try {
            fondo = ImageIO.read(new File("src\\Imagenes\\burbujasInicio.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH), 0, 0,
                null);

        //TÍTULO        
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        g.setColor(new Color(112, 11, 200));
        g.drawString("PUZZLE BUBBLE", panelJuego.getWidth() / 2 - 120, panelJuego.getHeight() / 2 - 30);

        //Mensaje para empezar a jugar
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
        g.setColor(colorIntro);
        g.drawString("Pulsa espacio para jugar", panelJuego.getWidth() / 2 - 110, panelJuego.getHeight() / 2 + 20);

        //Nombre del creador
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g.setColor(new Color(255, 212, 40));
        g.drawString("Sara Corrales Santos", panelJuego.getWidth() - 160, panelJuego.getHeight() -20);        

    }

    @Override
    public void ejecutarFrame() {
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        colorIntro = colorIntro == Color.PINK ? Color.RED : Color.PINK;

    }
   

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
    }

    @Override
    public void controles(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            panelJuego.cambiarPantalla(new PantallaJuego(panelJuego, ventana));
        }

    }

}
