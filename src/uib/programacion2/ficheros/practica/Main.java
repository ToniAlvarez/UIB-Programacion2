package uib.programacion2.ficheros.practica;

import java.io.*;

/**
 * Created by Tony on 5/4/17.
 *
 * Practica 1
 *
 */
public class Main {

    public static void main(String[] argumentos) {
        String ficheroJugadores = "jugadores.dat";

        boolean fin = false;

        while (!fin) {
            System.out.println();
            System.out.println("===== MENU =====");
            System.out.println("----------------------------------------------");
            System.out.println("1 - Insertar Jugadores");
            System.out.println("2 - Ver Jugadores");
            System.out.println("3 - Crear y mostrar fichero de Equipos");
            System.out.println("4 - Crear y mostrar fichero de Jugadas destacadas");
            System.out.println("----------------------------------------------");
            System.out.println("q - Salir");
            System.out.println();

            System.out.print("Introduzca la acción que desea ejecutar: ");
            char opcion = LT.llegirCaracter();

            switch (opcion) {
                case '1':
                    insertar(ficheroJugadores);
                    break;
                case '2':
                    lectura(ficheroJugadores);
                    break;
                case '3':
                    separarEquipos(ficheroJugadores);
                    break;
                case '4':
                    jugadasDestacadas();
                    break;
                default:
                    fin = true;
            }
        }
    }


    private static void borrarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void insertar(String ficheroJugadores) {
        try {
            System.out.println("[ Inserción de Jugadores ] ");
            System.out.println("\n¿Cuantos jugadores quieres insertar?");

            int num_jugadores = LT.llegirSencer();

            FileOutputStream fos = new FileOutputStream(ficheroJugadores);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("\nInserta los datos de los " + num_jugadores + " con las puntuaciones de mayor a menor.");

            for (int i = 1; i <= num_jugadores; i++) {
                System.out.println();
                System.out.println("Nick del jugador " + i + ":");
                String nick = LT.llegirLinia();

                System.out.println("Puntuacion del jugador " + i + ":");
                int puntuacion = LT.llegirSencer();

                System.out.println("Equipo del jugador " + i);
                System.out.println("\tATACANTE (1)");
                System.out.println("\tDEFENSOR (2)");

                int seleccionEquipo = LT.llegirSencer();

                System.out.println("Jugada destacada del jugador " + i);
                System.out.println("\tNINGUNA (1)");
                System.out.println("\tDEFENSA (2)");
                System.out.println("\tCAPTURA (3)");
                System.out.println("\tASESINO (4)");
                System.out.println("\tMEDICO  (5)");

                int seleccionJugada = LT.llegirSencer();

                Equipo equipo;

                switch (seleccionEquipo) {
                    case 1:
                        equipo = Equipo.ATACANTE;
                        break;
                    case 2:
                    default:
                        equipo = Equipo.DEFENSOR;
                        break;
                }

                Jugada jugada;

                switch (seleccionJugada) {
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
                    case 1:
                    default:
                        jugada = Jugada.NINGUNA;
                        break;
                }

                Jugador jugador = new Jugador(nick, puntuacion, equipo, jugada);

                oos.writeObject(jugador);

                System.out.println("\nJugador " + i + " insertado correctamente\n\n");
            }

            oos.writeObject(Jugador.CENTINELA);
            oos.close();

        } catch (Exception e) {
            System.err.println("Error al insertar Jugadores:");
            e.printStackTrace();
        }
    }

    public static void lectura(String ficheroJugadores) {

        System.out.println("[ Lectura de Jugadores ]");
        System.out.println();

        System.out.println(" Contenido del fichero de Jugadores:\n");

        try {
            FileInputStream fis = new FileInputStream(ficheroJugadores);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Jugador jugador = (Jugador) ois.readObject();
            int numJugador = 1;

            while (!jugador.esCentinela()) {
                System.out.println(numJugador + " - " + jugador.toString());
                jugador = (Jugador) ois.readObject();
                numJugador++;
            }

            ois.close();
        } catch (Exception e) {
            System.err.println("Error al leer fichero de Jugadores:");
            e.fillInStackTrace();
        }
    }

    public static void separarEquipos(String ficheroJugadores) {

        System.out.println("[ Separar Jugadores por Equipo ]");
        System.out.println();

        eliminarFicherosEquipos();

        JugadorInOut jugadoresDefensaInOut = new JugadorInOut("defensores.dat");
        JugadorInOut jugadoresAtaqueInOut = new JugadorInOut("atacantes.dat");

        try {
            FileInputStream fis = new FileInputStream(ficheroJugadores);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Jugador jugador = (Jugador) ois.readObject();

            while (!jugador.esCentinela()) {

                switch (jugador.getEquipo()) {
                    case DEFENSOR:
                        jugadoresDefensaInOut.escrituraAdd(jugador);
                        break;
                    case ATACANTE:
                        jugadoresAtaqueInOut.escrituraAdd(jugador);
                        break;
                }

                jugador = (Jugador) ois.readObject();
            }

            ois.close();
        } catch (Exception e) {
            System.err.println("Error al leer fichero de Jugadores:");
            e.printStackTrace();
        }

        //Cerrar los ficheros para guardar los cambios
        jugadoresDefensaInOut.cierre();
        jugadoresAtaqueInOut.cierre();

        //Abrir los ficheros para leer los datos guardados
        jugadoresDefensaInOut = new JugadorInOut("defensores.dat");
        jugadoresAtaqueInOut = new JugadorInOut("atacantes.dat");

        System.out.println("Jugadores del equipo DEFENSA:\n");

        for (Jugador defensa : jugadoresDefensaInOut.leerJugadores())
            System.out.println(defensa.toString());

        System.out.println("\nJugadores del equipo ATAQUE:\n");

        for (Jugador atacante : jugadoresAtaqueInOut.leerJugadores())
            System.out.println(atacante.toString());

        //Cerrar ficheros
        jugadoresDefensaInOut.cierre();
        jugadoresAtaqueInOut.cierre();
    }

    public static void jugadasDestacadas() {

        if (!new File("defensores.dat").exists() || !new File("defensores.dat").exists()) {
            System.err.println("Aún no se han creado los ficheros de Jugadores por equipos");
            return;
        }

        System.out.println("[ Jugadores con Jugadas destacadas ]");
        System.out.println();

        //Abrir archivo TXT donde se guardarán los datos
        JugadorTextOut ficheroTextoOut = new JugadorTextOut("destacadas.txt");

        //Abrir fichero aleatorio del equipo Defensa
        JugadorInOut jugadoresDefensaInOut = new JugadorInOut("defensores.dat");

        System.out.println("Guardando jugadas destacadas del equipo DEFENSA..");

        //Escribir cabecera
        ficheroTextoOut.escribir("Jugadas destacadas del equipo DEFENSA:");
        ficheroTextoOut.escribir("");

        System.out.println("\nJugadas destacadas del equipo DEFENSA:\n");

        for (Jugador defensa : jugadoresDefensaInOut.leerJugadores())
            if (defensa.getJugada() != Jugada.NINGUNA) {
                System.out.println(defensa.toString());
                ficheroTextoOut.escribir(defensa.toString());
            }

        jugadoresDefensaInOut.cierre();


        JugadorInOut jugadoresAtaqueInOut = new JugadorInOut("atacantes.dat");

        System.out.println("Guardando jugadas destacadas del equipo ATAQUE..");

        //Escribir cabecera
        ficheroTextoOut.escribir("");
        ficheroTextoOut.escribir("Jugadas destacadas del equipo ATAQUE:");
        ficheroTextoOut.escribir("");

        System.out.println("\nJugadas destacadas del equipo DEFENSA:\n");

        for (Jugador atacante : jugadoresAtaqueInOut.leerJugadores())
            if (atacante.getJugada() != Jugada.NINGUNA) {
                System.out.println(atacante.toString());
                ficheroTextoOut.escribir(atacante.toString());
            }

        jugadoresAtaqueInOut.cierre();

        //Cerrar fichero TXT
        ficheroTextoOut.cierre();

        System.out.println("\nLas jugadas destacadas han sido guardadas en el fichero jugadas.txt satisfactoriamente!");
    }

    /**
     * Elimina los ficheros de atacantes y defensores generados anteriormente
     */
    private static void eliminarFicherosEquipos() {
        File ficheroDefensores = new File("defensores.dat");
        File ficheroAtacantes = new File("atacantes.dat");

        if (ficheroDefensores.exists())
            ficheroDefensores.delete();

        if (ficheroAtacantes.exists())
            ficheroAtacantes.delete();
    }


}
