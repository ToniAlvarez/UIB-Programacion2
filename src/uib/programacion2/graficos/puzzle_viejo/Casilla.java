package uib.programacion2.graficos.puzzle_viejo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tony on 12/4/17.
 */
public class Casilla {

    private boolean blanca;

    private BufferedImage imagen = null;

    private int numCasilla;

    public Casilla(int numCasilla) {
        blanca = false;
        this.numCasilla = numCasilla;

        try {
            imagen = ImageIO.read(new File("img/img" + numCasilla + ".jpg"));
        } catch (IOException e) {
            System.err.println("Error abriendo imagen de la casilla " + numCasilla);
            e.printStackTrace();
        }
    }

    public boolean getBlanca() {
        return blanca;
    }

    public int getNumCasilla() {
        return numCasilla;
    }

    public void setBlanca(boolean blanca) {
        this.blanca = blanca;
    }

    public BufferedImage getImagen() {
        return imagen;
    }
}