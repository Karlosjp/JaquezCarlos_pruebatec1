package org.example.complements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Auxiliar {
    private static Scanner sc = new Scanner(System.in);

    public static void cerrarScanner() {
        sc.close();
    }

    // Recoge un String y comprueba que sea correcto
    public static String introducirDatosString() {

        Boolean repetir = true;
        String entrada = "";

        while (repetir) {
            entrada = sc.nextLine();

            if (!entrada.isEmpty() && !(entrada == null)) repetir = false;
            else System.out.println("---- El campo no puede ir vacio ---- \nIntroduce de nuevo: ");
        }

        return entrada;
    }

    // Recoge un double y comprueba que sea un numero correcto
    public static Double introducirDatosDouble() {
        Boolean repetir = true;
        String entrada;

        do {
            entrada = introducirDatosString();

            try {
                if (!Double.isNaN(Double.parseDouble(entrada))) repetir = false;
            } catch (NumberFormatException nfe) {
                System.err.println("El numero introducido es incorrecto.\nPor favor introduce de nuevo: ");
            }
        } while (repetir);

        return Double.parseDouble(entrada);
    }

    // Recoge un int y comprueba que sea un numero entero
    public static Integer introducirDatosInteger() {
        Boolean repetir = true;
        String entrada;

        do {
            entrada = introducirDatosString();

            try {
                if (!Double.isNaN(Double.parseDouble(entrada))) repetir = false;
            } catch (NumberFormatException nfe) {
                System.err.println("El numero introducido es incorrecto.\nPor favor introduce de nuevo: ");
            }
        } while (repetir);

        return Integer.parseInt(entrada);
    }

    // Pregunta por consola los datos necesarios para crear un objeto Date
    public static Date introducirDatosDate() {
        boolean repetir = true;
        Date fecha = null;

        while (repetir) {
            System.out.print("Año de inicio: ");
            int anno = introducirDatosInteger();

            System.out.print("Mes de inicio: ");
            int mes = introducirDatosInteger();

            System.out.print("Dia de inicio: ");
            int dia = introducirDatosInteger();

            fecha = stringToDate(dia + "/" + mes + "/" + anno);

            if (fecha != null) repetir = false;
        }

        return fecha;
    }

    // Transforma un String en objeto Date.
    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

}
