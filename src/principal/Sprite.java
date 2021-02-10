package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage buffer;
    private Color color;
    //dimensiones
    private int ancho;
    private int alto;
    //posición
    private int posX;
    private int posY;
    // velocidades
    private int velX;
    private int velY;


    /**
     * Constructor que crea un sprite de un color
     * @param color
     * @param ancho
     * @param alto
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     */
    public Sprite(int forma, Color color, int ancho, int alto, int posX, int posY, int velX, int velY) {

        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;

        switch (forma) {
            case 0:
                iniciarBuffer();
                break;
            case 1: 
                iniciarBufferDisparador();
                break;
        }
        

    }


    /**
     * Constructor que crea un sprite con una imagen
     * @param rutaAsteroide
     * @param ancho
     * @param alto
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     */
    public Sprite(String rutaAsteroide, int ancho, int alto, int posX, int posY, int velX, int velY) {

        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        iniciarBuffer(rutaAsteroide);

    }

    /**
     * crea una imagen(buffer) con una imagen
     */
    private void iniciarBuffer(String ruta) {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        BufferedImage imagenSprite = null;
        try {
            imagenSprite = ImageIO.read(new File(ruta));
            Graphics g = buffer.getGraphics();
            g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * crea una imagen(buffer) vacio
     */
    private void iniciarBuffer() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        g.setColor(color);
        g.fillOval(0, 0, ancho, alto);
        g.dispose();
    }

    private void iniciarBufferDisparador() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, ancho, alto);
        g.dispose();
    }

    //GETTERS Y SETTERS
    public BufferedImage getBuffer() {
        return this.buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return this.velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }


    /**
     * Muestra en la ventana el sprite
     */
    public void estampar(Graphics g) {
        g.drawImage(buffer, posX, posY, null);
    }

    /**
     * Método que genera la velocidad del sprite de forma aleatoria
     * @param max
     */
    public void velocidad(int max) {
        Random aleatorio = new Random();
        int velXa = aleatorio.nextInt(max);

        if (aleatorio.nextInt(2) == 0) {
            velXa = velXa * -1;
        }

        int velYa;
        do {
            velYa = aleatorio.nextInt(max);
        } while (velYa == 0 && velYa == 0);
        if (aleatorio.nextInt(2) == 0) {
            velYa *= -1;
        }

        if (aleatorio.nextInt(2) == 0) {
            velX = velXa;
            velY = velYa;
        } else {
            velX = velYa;
            velY = velXa;
        }

    }


    /**
     * Método que hace que el sprite se mueva por la ventana
     */
    public void mover(int width, int height) {
        // Mover el cuadrado y repintra
        posX += velX;
        posY += velY;
        // Comprobar si choca con los bordes
        if (posX + ancho >= width) {
            velX = -Math.abs(velX);

        }

        if (posY + alto >= height) {
            velY = -Math.abs(velY);
        }

        // Por la izquierda
        if (posX < 0) {
            velX = Math.abs(velX);

        }

        if (posY < 0) {
            velY = Math.abs(velY);

        }
    }


    /**
     * Método que comprueba si hay una colisión entre dos sprites
     * @param otroStrite
     * @return
     */
    public boolean colisiona(Sprite otroStrite) {
        boolean colisioEjeX = false;
        // calcular como en os apuntes
        Sprite izquierda, derecha, arriba, abajo;
        if (this.getPosX() < otroStrite.getPosX()) {
            izquierda = this;
            derecha = otroStrite;
        } else {
            derecha = this;
            izquierda = otroStrite;
        }

        colisioEjeX = izquierda.getPosX() + izquierda.getAncho() >= derecha.getPosX();

        boolean colisionEjeY = false;
        // Calcular como en los apuntes

        if (!colisioEjeX) {
            return false;
        }

        if (this.getPosY() < otroStrite.getPosY()) {
            arriba = this;
            abajo = otroStrite;
        } else {
            abajo = this;
            arriba = otroStrite;
        }
        colisionEjeY = arriba.getPosY() + arriba.getAlto() >= abajo.getPosY();
        return colisioEjeX && colisionEjeY;
    }

    /**
     * Método que mueve el sprite sin que choque 
     */
    public void moverSinBordes() {
        posX = posX + velX;
        posY = posY + velY;
    }
}
