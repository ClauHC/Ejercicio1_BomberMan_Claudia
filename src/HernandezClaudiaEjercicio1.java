//Ejercicio 1 Bomber man
//Claudia Hernandez Colomer
//Fp DAW La Salle
//Profesora Marta Bella
//noviembre 2024

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//import java.util.Collections;

public class HernandezClaudiaEjercicio1 {
    public static void main(String[] args) {

        /* Haz un programa en Java que solicite por consola al usuario dos números (filas, columnas) enteros que
        representarán las filas y columnas que tendrá el terreno del juego. Si el usuario escoge un valor no válido,
        le indicaremos que este valor es incorrecto y le volveremos a pedir la información */

        System.out.println("¡Bienvenido a Bomber man! Para empezar, vamos a crear el terreno.");
        Scanner input = new Scanner(System.in);
        final int MENOR = 1;
        final int MAYOR = 10;
        int filas = 0;
        int columnas = 0;

        //Pedir numeros (válidos) al usuario
        do {
            System.out.println("Introduce el número de filas que quieras. Tiene que ser un número entero, diferente de cero, positivo y no mayor " + MAYOR);
            if (input.hasNextInt()){
                filas = input.nextInt();
                input.nextLine();
                if (filas < MENOR || filas > MAYOR) {
                    System.out.println("Selección no válida. Debes elegir un número entre " + MENOR + " y " + MAYOR);
                    filas = 0;
                } else {
                    System.out.println("Tenemos " + filas + " filas");
                }
            } else {
                System.out.println("Selección no válida. Debes elegir un número entero");
                input.nextLine();
            }
        } while (filas == 0);

        do {
            System.out.println("Ahora introduce el número de columnas que quieras. Nuevamente tiene que ser un número entero, diferente de cero, positivo y no mayor " + MAYOR);
                if (input.hasNextInt()){
                columnas = input.nextInt();
                input.nextLine();
                if (columnas < MENOR || columnas > MAYOR) {
                    System.out.println("Selección no válida. Debes elegir un número entre " + MENOR + " y " + MAYOR);
                    columnas = 0;
                } else {
                    System.out.println("Tenemos " + columnas + " columnas");
                }
            } else {
                System.out.println("Selección no válida. Debes elegir un número entero");
                input.nextLine();
            }
        } while (columnas == 0);

        //Genera la matriz filas*columnas con números aleatorios del 1 al 9 (ambos incluidos).
        final int LOW = 1;
        final int HIGH = 9;
        Random random = new Random();

        int [][] matriz = new int [filas][columnas];
        System.out.println("Esta es tu matriz");

        for (int i=0; i<matriz.length; i++){
            for (int j=0; j<matriz[0].length; j++){
                int contenidoMatriz = random.nextInt(HIGH + 1 - LOW) + LOW;
                matriz [i][j] = contenidoMatriz;
                System.out.print(" | " + matriz [i][j]);
            }
            System.out.println(" | ");
        }

        /*
         Cuando hayas generado la matriz presenta al usuario el siguiente menú de opciones
         [2] Poner bomba
         [1] Mostrar matriz
         [0] Salir
         El usuario podrá escoger 2 si quiere proporcionar unas coordenadas para la bomba, 1 si quiere ver el
         contenido de la matriz o 0 si quiere salir del programa. Si el usuario escoge cualquier otro valor (incluido
         un valor no numérico), le indicaremos que esta opción no existe y le volveremos a mostrar el menú.
         */

        System.out.print("Ahora que ya está lista la matriz selecciona qué quieres hacer");

        int seleccionUsuario;

        //Reto ranking de explosiones. Uso un arraylist porque necesito que deje ir introduciendo datos nuevos sin tener cantidad fija.
        ArrayList <Integer> boomArraylist = new ArrayList<>();

        do {
            // menú de opciones
            System.out.println("¿Qué quieres hacer?");
            System.out.println("[2] Poner bomba");
            System.out.println("[1] Mostrar matriz");
            System.out.println("[0] Salir");

            if (input.hasNextInt()) {
                seleccionUsuario = input.nextInt();
                input.nextLine();

                switch (seleccionUsuario) {

                    // Poner bomba
                    case 2:

                        int filaBomba = -1;
                        int columnaBomba = -1;

                        // Fila
                        do {
                            System.out.print("Introduce la fila para poner la bomba (debe ser un valor entre 0 - " + (matriz.length - 1) + "): ");
                            if (input.hasNextInt()) {
                                filaBomba = input.nextInt();
                                input.nextLine();
                                if (filaBomba < 0 || filaBomba >= matriz.length) {
                                    System.out.println("Coordenada de fila fuera de los límites. Inténtalo de nuevo.");
                                    filaBomba = -1;
                                }
                            } else {
                                System.out.println("Entrada no válida. Por favor, ingresa un número entero.");
                                input.nextLine();
                            }
                        } while (filaBomba == -1);

                        // Columna
                        do {
                            System.out.print("Introduce la columna para poner la bomba (debe ser un valor entre 0 - " + (matriz[0].length - 1) + "): ");
                            if (input.hasNextInt()) {
                                columnaBomba = input.nextInt();
                                input.nextLine();
                                if (columnaBomba < 0 || columnaBomba >= matriz[0].length) {
                                    System.out.println("Coordenada de columna fuera de los límites. Inténtalo de nuevo.");
                                    columnaBomba = -1;
                                }
                            } else {
                                System.out.println("Entrada no válida. Por favor, ingresa un número entero.");
                                input.nextLine();
                            }
                        } while (columnaBomba == -1);

                        /*
                        El programa mostrará por consola el valor de la explosión, esto es la suma de todos los valores que haya
                        en la fila x + columna y. A continuación, el programa pondrá a 0 todos estos valores.
                        */

                        int calculoBoom = 0;

                        // Fila (para num de fila = filaBomba num de columna recorre todos los valores de i de iz a der)
                        for (int i = 0; i < matriz[0].length; i++) {
                            calculoBoom += matriz[filaBomba][i];
                            matriz[filaBomba][i] = 0;
                        }

                        // Columna (para num de columna = columnaBomba num de fila recorre todos los valores de j de arriba a abajo. Se salta la de fila del paso anterior)
                        for (int j = 0; j < matriz.length; j++) {
                            if (j != filaBomba) {
                                calculoBoom += matriz[j][columnaBomba];
                                matriz[j][columnaBomba] = 0;
                            }
                        }

                        System.out.println("Haz hecho un BOOM de: " + calculoBoom + " puntos");

                        // Si la matriz contiene algún valor que no sea 0, regresaremos al menú, en caso contrario terminará el programa informando al usuario
                        boolean matrizVacia = true;
                        for (int i = 0; i < filas; i++) {
                            for (int j = 0; j < columnas; j++) {
                                if (matriz[i][j] != 0) {
                                    matrizVacia = false;
                                    break;
                                }
                            }
                            if (!matrizVacia) break;
                        }

                        if (matrizVacia) {
                            System.out.println("Game over! Ya no queda nada que explotar.");
                            seleccionUsuario = 0;
                        }

                        //Reto ranking de explosiones. Recoger y guardar todas las explosiones y que se impriman en orden.
                        boomArraylist.add(calculoBoom);
                        //Collections.sort(boomArraylist, Collections.reverseOrder());
                        System.out.println("Tu rankin es: ");
                        for (int i = 0; i < boomArraylist.size(); i++){
                            System.out.println((i + 1) + ". " + boomArraylist.get(i) + " puntos");
                        }

                        break;

                    // Mostrar matriz
                    case 1:
                        System.out.println("Este es el estado actual de tu matriz:");
                        for (int i = 0; i < matriz.length; i++) {
                            for (int j = 0; j < matriz[0].length; j++) {
                                System.out.print(" | " + matriz[i][j]);
                            }
                            System.out.println(" | ");
                        }
                        break;

                    // Salir
                    case 0:
                        System.out.println("Game over!");
                        break;

                    default:
                        System.out.println("Opción no válida. Debes seleccionar una opción del menú.");
                }
            } else {
                System.out.println("Entrada no válida. Debes ingresar uno de los números del menú.");
                input.nextLine();
                seleccionUsuario = -1;
            }

        } while (seleccionUsuario != 0);
    }
}