package uib.programacion2.graficos.pictograma;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Toni on 01/6/17.
 */
public class Tablero extends JPanel {

    private static final int DIMENSION = 50;
    private static final int MAXIMO = 601;

    private static final int IMG_SIZE = 100;

    private static final int LADO = MAXIMO / DIMENSION;

    private Casilla[][] casillas = new Casilla[DIMENSION][DIMENSION];

    public Tablero() {

        int x = 0;

        for (int i = 0; i < DIMENSION; i++) {
            //Reiniciar Y en cada fila
            int y = 0;

            for (int j = 0; j < DIMENSION; j++) {
                Rectangle2D.Float rectangle = new Rectangle2D.Float(x, y, LADO, LADO);

                //Rellenar array de casillas
                Casilla casilla = new Casilla(rectangle);

                casillas[i][j] = casilla;

                //Aumentar origen vertical en la dimension del lado de una casilla
                y += LADO;
            }

            x += LADO;
            //Aumentar origen horizontal en la dimension del lado de una casilla
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Dibujar casillas
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                casillas[i][j].paintComponent(g2d);
            }
        }


        //Dibujar cuadrícula negra encima de las casillas
        g2d.setColor(Color.BLACK);
        int origen = 0;

        for (int i = 0; i <= DIMENSION; i++) {

            //Lineas horizontales
            g.drawLine(0, origen, MAXIMO, origen);

            //Lineas verticales
            g.drawLine(origen, 0, origen, MAXIMO);

            //Aumentar ambos orígenes en la dimension del lado de una casilla
            origen += LADO;
        }

    }

    public void exportarJPG(String ruta) {
        //Generar imagen a partir de graphics
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2g = bufferedImage.createGraphics();
        paintAll(g2g);

        //Escalar imagen al tamaño deseado
        Image pictograma = bufferedImage.getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_SMOOTH);
        BufferedImage imgOut = toBufferedImage(pictograma);

        try {
            //Si se guarda correctamente, mostrar mensaje de confirmación
            if (ImageIO.write(imgOut, "jpg", new File(ruta))) {
                JOptionPane.showMessageDialog(this,
                        "Puzzle guardado como JPG correctamente",
                        null,
                        JOptionPane.PLAIN_MESSAGE);
                System.out.println("Puzzle guardado correctamente");
            }

        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Error al guardar el pictograma");
        }
    }

    /**
     * Al hacer click en una casilla, cambiar su estado
     *
     * @param x
     * @param y
     */
    public void clickCasilla(int x, int y) {
        int i = ((int) Math.floor(x / LADO));
        int j = ((int) Math.floor(y / LADO));

        //Invertir estado de la casilla clicada
        casillas[i][j].cambiarEstado();
    }

    /**
     * Invertir el estado de todas las casillas
     */
    public void invertirTablero() {

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                casillas[i][j].cambiarEstado();
                repaint();
            }
        }
    }

    /**
     * Borrar todas las casillas
     */
    public void borrarTablero() {

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                casillas[i][j].setEstado(false);
                repaint();
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMO, MAXIMO);
    }


    /**
     * Método que genera una BufferedImage a partir de una Image
     *
     * @param img
     *
     * @return BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
            return (BufferedImage) img;

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);

        return bufferedImage;
    }
}
