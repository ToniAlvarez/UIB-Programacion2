package uib.programacion2.graficos.puzzle_viejo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tony on 12/4/17.
 */
public class Dibujar extends JComponent {

    private Casilla[][] casillas;

    private int dim_total;
    private int dim_casilla;

    public Dibujar(Casilla[][] casillas, int dim_total, int dim_casilla) {
        this.casillas = casillas;
        this.dim_total = dim_total;
        this.dim_casilla = dim_casilla;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);

        for (int fila = 0; fila < casillas.length; fila++) {
            for (int columna = 0; columna < casillas[fila].length; columna++) {

                int coordX = (columna * dim_casilla);
                int coordY = (fila * dim_casilla);

                if (casillas[fila][columna].getBlanca())
                    g.fillRect(coordX, coordY, (coordX + dim_casilla), (coordY + dim_casilla));
                else
                    g.drawImage(casillas[fila][columna].getImagen(), coordX, coordY, dim_casilla, dim_casilla, null);


            }
        }

        g.setColor(Color.BLACK);

        //Dibujar cuadrícula
        int y = 0;

        for (int i = 1; i <= dim_total + 1; i++) {
            int x = 0;

            for (int j = 1; j <= dim_total + 1; j++) {
                g.drawLine(x, y, x, dim_total);
                g.drawLine(x, y, dim_total, y);
                x += dim_casilla;
            }

            y += dim_casilla;
        }
    }


}