package uib.programacion2.ficheros.practica;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * Created by Tony on 5/4/17.
 */
public class JugadorInOut {

    private static final int CHARS_NICK = 40;
    static final int DIM_NICK = Character.SIZE / Byte.SIZE * CHARS_NICK;    // 40 chars
    static final int DIM_PUNTUACION = Integer.SIZE / Byte.SIZE;             // int
    static final int DIM_EQUIPO = Integer.SIZE / Byte.SIZE;                 // int
    static final int DIM_JUGADA = Integer.SIZE / Byte.SIZE;                 // int

    static final int DIM_JUGADOR = DIM_PUNTUACION + DIM_NICK + DIM_EQUIPO + DIM_JUGADA;

    private RandomAccessFile objetoInOut = null;

    private int numeroJugadores;

    public JugadorInOut(String nombre) {
        try {
            objetoInOut = new RandomAccessFile(nombre, "rw");

            numeroJugadores = (int) objetoInOut.length() / DIM_JUGADOR;
        } catch (IOException e) {
            System.err.println("Error al abrir el fichero de Jugadores por equipos:");
            e.printStackTrace();
        }
    }

    private void reiniciarPuntero() {
        try {
            objetoInOut.seek(0);
        } catch (IOException e) {
            System.err.println("Error al iniciar el puntero del fichero de Jugadores por equipos:");
            e.printStackTrace();
        }
    }

    public Jugador[] leerJugadores() {

        reiniciarPuntero();

        Jugador[] jugadores = new Jugador[numeroJugadores];

        try {

            for (int i = 0; i < numeroJugadores; ++i)
                jugadores[i] = leerJugador();

            return jugadores;
        } catch (IOException e) {
            System.err.println("Error al leer Jugadores por equipos:");
            e.printStackTrace();
        }

        return jugadores;
    }

    public void escrituraAdd(Jugador jugador) {
        try {
            objetoInOut.seek(objetoInOut.length());

            escribirJugador(jugador);

        } catch (IOException e) {
            System.err.println("Error al escribir Jugador en el fichero de Jugadores por equipos:");
            e.printStackTrace();
        }
    }

    private void escribirJugador(Jugador jugador) throws IOException {

        String nick = jugador.getNick();

        if (nick.length() > CHARS_NICK)
            nick = nick.substring(0, CHARS_NICK);

        //Escribir los caracteres del Nick
        for (int i = 0; i < nick.length(); i++)
            objetoInOut.writeChar(nick.charAt(i));

        //Rellenar con espacios hasta ocupar la dimensión del Nick
        for (int i = nick.length(); i < CHARS_NICK; i++)
            objetoInOut.writeChar(' ');

        //Escribir el int de la puntuación
        objetoInOut.writeInt(jugador.getPuntuacion());

        //Guardar el Equipo del Jugador como un int que servirá para identificar el Equipo
        int idEquipo = 0;
        Equipo equipo = jugador.getEquipo();

        switch (equipo) {
            case ATACANTE:
                idEquipo = 1;
                break;
            case DEFENSOR:
                idEquipo = 2;
                break;
        }

        objetoInOut.writeInt(idEquipo);

        //Guardar la Jugada del Jugador como un int que servirá para identificar la Jugada
        int idJugada = 0;
        Jugada jugada = jugador.getJugada();

        switch (jugada) {
            case NINGUNA:
                idJugada = 1;
                break;
            case DEFENSA:
                idJugada = 2;
                break;
            case CAPTURA:
                idJugada = 3;
                break;
            case ASESINO:
                idJugada = 4;
                break;
            case MEDICO:
                idJugada = 5;
                break;
        }

        objetoInOut.writeInt(idJugada);
    }

    private Jugador leerJugador() throws IOException {

        Jugador jugador = new Jugador();

        String nick = "";

        for (int i = 0; i < DIM_NICK / 2; i++)
            nick += objetoInOut.readChar();

        jugador.setNick(nick.trim());

        //Leer int de Puntuación
        jugador.setPuntuacion(objetoInOut.readInt());

        //Leer int de Equipo
        int idEquipo = objetoInOut.readInt();
        Equipo equipo = null;

        switch (idEquipo) {
            case 1:
                equipo = Equipo.ATACANTE;
                break;
            case 2:
                equipo = Equipo.DEFENSOR;
                break;
        }

        jugador.setEquipo(equipo);


        //Leer int de Jugada
        int idJugada = objetoInOut.readInt();
        Jugada jugada = null;

        switch (idJugada) {
            case 1:
                jugada = Jugada.NINGUNA;
                break;
            case 2:
                jugada = Jugada.DEFENSA;
                break;
            case 3:
                jugada = Jugada.CAPTURA;
                break;
            case 4:
                jugada = Jugada.ASESINO;
                break;
            case 5:
                jugada = Jugada.MEDICO;
                break;
        }

        //Leer int de Jugada
        jugador.setJugada(jugada);

        return jugador;
    }

    public void cierre() {
        if (objetoInOut != null) {
            try {
                objetoInOut.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero de Jugadores por equipos:");
                e.printStackTrace();
            }
        }

    }

}
    
