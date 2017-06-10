package uib.programacion2.graficos.pictograma;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Toni on 01/6/17.
 */
public class Casilla {

    private boolean estado;
    private Rectangle2D.Float rectangle;

    /**
     * Constructor con el rectangulo donde pintar la Casilla
     *
     * @param rectangle
     */
    public Casilla(Rectangle2D.Float rectangle) {
        this.rectangle = rectangle;
        estado = false;
    }

    /**
     * paintComponent de la clase que pinta el rectángulo
     *
     * @param graphics
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        if (estado)
            g2d.setColor(Color.BLACK);
        else
            g2d.setColor(Color.WHITE);

        g2d.fill(rectangle);
    }

    /**
     * Método que cambiar el estado a modo de interruptor
     * Si el estado es true (Negro), se pone en false (Blanco) y viceversa
     */
    public void cambiarEstado() {
        estado = !estado;
    }

    /*
     *  - GETTERS Y SETTERS
     */

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}