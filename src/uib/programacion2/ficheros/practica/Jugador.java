package uib.programacion2.ficheros.practica;


import java.io.Serializable;

enum Equipo {ATACANTE, DEFENSOR}

enum Jugada {NINGUNA, DEFENSA, CAPTURA, ASESINO, MEDICO}


/**
 * Created by Tony on 5/4/17.
 */
public class Jugador implements Serializable {

    private String nick;
    private int puntuacion;
    private Equipo equipo;
    private Jugada jugada;

    /**
     * Centinela, se identifica por una puntuación de -99999
     */
    public static final Jugador CENTINELA = new Jugador(-99999);

    /**
     * Constructor de Jugador con todos los atributos
     *
     * @param nick
     * @param puntuacion
     * @param equipo
     * @param jugada
     */
    public Jugador(String nick, int puntuacion, Equipo equipo, Jugada jugada) {
        this.nick = nick;
        this.puntuacion = puntuacion;
        this.equipo = equipo;
        this.jugada = jugada;
    }


    /**
     * Constructor privado con puntuación como único parametro, usado para el Centinela
     *
     * @param puntuacion
     */
    private Jugador(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    /**
     * Constructor vacio
     *
     */
    public Jugador() {
    }

    /**
     * Método para saber si el objeto Jugador es Centinela
     */
    public boolean esCentinela() {
        return this.puntuacion == -99999;
    }

    /*
     * GETTERS AND SETTERS
     */

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugada getJugada() {
        return jugada;
    }

    public void setJugada(Jugada jugada) {
        this.jugada = jugada;
    }

    @Override
    public String toString() {
        return "Jugador { " +
                "nick = '" + nick + '\'' +
                ", puntuacion = " + puntuacion +
                ", equipo = " + equipo +
                ", jugada destacada = " + jugada +
                " }";
    }
}
