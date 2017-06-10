package uib.programacion2.graficos.pictograma;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by Toni on 01/6/17.
 */
public class Pictograma extends JFrame implements MouseListener {

    private Tablero tablero;

    public Pictograma() {
        super("Taller 2 - Puzzle");
        tablero = new Tablero();
        tablero.addMouseListener(this);
        initComponents();

        this.getContentPane().add(tablero);
        this.setSize(tablero.getPreferredSize());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Pictograma pictograma = new Pictograma();
        pictograma.setVisible(true);
    }

    //Configurar menú
    private void initComponents() {

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu();
        JMenu menuEditar = new JMenu();

        JMenuItem menuItemReiniciar = new JMenuItem();
        JMenuItem menuItemInvertir = new JMenuItem();
        JMenuItem menuItemGuardar = new JMenuItem();
        JMenuItem menuItemSalir = new JMenuItem();

        getContentPane().add(menuBar);

        menuItemReiniciar.setText("Borrar");
        menuItemReiniciar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                tablero.borrarTablero();
            }
        });

        menuItemInvertir.setText("Invertir");
        menuItemInvertir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                tablero.invertirTablero();
            }
        });

        menuItemGuardar.setText("Guardar imagen..");
        menuItemGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                showSaveFileDialog();
            }
        });

        menuItemSalir.setText("Salir");
        menuItemSalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        menuArchivo.setText("Archivo");
        menuArchivo.add(menuItemGuardar);
        menuArchivo.add(new JSeparator());
        menuArchivo.add(menuItemSalir);

        menuEditar.setText("Tablero");
        menuEditar.add(menuItemReiniciar);
        menuEditar.add(menuItemInvertir);

        menuBar.add(menuArchivo);
        menuBar.add(menuEditar);

        setJMenuBar(menuBar);
    }

    //Mostrar dialogo de selección de ruta, para guardar el fichero
    private void showSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación");
        fileChooser.setSelectedFile(new File("pictograma.jpg"));
        //Set an extension filter, so the user sees other XML files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagen", "jpg"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Guardar imagen como: " + fileToSave.getAbsolutePath());
            tablero.exportarJPG(fileToSave.getAbsolutePath());
        }
    }

    /*
     * Eventos de MouseListener
     */

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            tablero.clickCasilla(me.getX(), me.getY());
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}

