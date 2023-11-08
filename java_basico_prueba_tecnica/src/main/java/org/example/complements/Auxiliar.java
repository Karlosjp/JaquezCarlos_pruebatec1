package org.example.complements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Auxiliar {
    public static Scanner sc = new Scanner(System.in);

    public static String introducirDatosString() {

        Boolean repetir = true;
        String entrada = "";

        while (repetir) {
            entrada = sc.nextLine();

            if (!entrada.isEmpty() && !(entrada == null)) repetir = false;
            else System.out.println("---- El campo no puede ir vacio ---- \nIntroduce de nuevo");
        }

        return entrada;
    }

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

            if (fecha != null)
                repetir = false;
        }

        return fecha;
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

}
