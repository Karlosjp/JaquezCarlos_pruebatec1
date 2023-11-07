package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ControladoraPersistencia controladora = new ControladoraPersistencia();
    public static void main(String[] args) {
        /*
        List<Empleado> listEmpleados = new ArrayList<>();

        listEmpleados.add(new Empleado(1L, "Carlos", "Jaquez", "Cargo 1", 1000.0, stringToDate("6/11/2023")));
        listEmpleados.add(new Empleado(2L, "Miguel", "German", "Cargo 2", 1100.0, stringToDate("5/10/2022")));
        listEmpleados.add(new Empleado(3L, "Fernando", "Santos", "Cargo 1", 1200.0, stringToDate("10/11/2020")));
        listEmpleados.add(new Empleado(4L, "Anny", "Marte", "Cargo 3", 1300.0, stringToDate("15/11/2013")));
        listEmpleados.add(new Empleado(5L, "Marta", "Cuevas", "Cargo 4", 1400.0, stringToDate("6/1/2018")));

        mostrarEmpleado(listEmpleados);
        */

        mostrarEmpleado(buscarEmpleado());
        mostrarEmpleado(buscarEmpleado("Cargo 4"));
        mostrarEmpleado(buscarEmpleado("Cargo 1"));
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return new Date();
    }

    public static Empleado crearEmpleado() {
        Empleado nuevoEmpleado = null;
        String entradaDatos = "";

        do {
            System.out.println("Escribe 'exit' para terminar, si deseas crear un nuevo empleado escribe 1");
            entradaDatos = introducirDatosString();

            if (entradaDatos.equals("1")) {
                System.out.println("Para crear el nuevo empleado, introduce los siguientes datos: ");

                System.out.print("Nombre: ");
                String nombre = introducirDatosString();

                System.out.print("Apellido: ");
                String apellido = introducirDatosString();

                System.out.print("Cargo: ");
                String cargo = introducirDatosString();

                System.out.print("salario : ");
                Double salario = introducirDatosDouble();

                System.out.print("Fecha de inicio: ");
                Date fechaInicio = introducirDatosDate();

                nuevoEmpleado = new Empleado(1l, nombre, apellido, cargo, salario, fechaInicio);
            }

        } while (entradaDatos.equals("salir"));

        return nuevoEmpleado;
    }

    public static List<Empleado> buscarEmpleado() {
        return controladora.buscarEmpleados();
    }

    public static List<Empleado> buscarEmpleado(String campo) {
        return controladora.buscarEmpleadosByCargo(campo);
    }

    public static void mostrarEmpleado(List<Empleado> empleados) {
        for (Empleado empleado : empleados)
            System.out.println(empleado);
    }

    private static String introducirDatosString() {
        Scanner sc = new Scanner(System.in);
        Boolean repetir = true;
        String entrada = "";

        while (repetir) {
            entrada = sc.nextLine();

            if (!entrada.isEmpty() || !(entrada == null)) repetir = true;
            else System.out.println("---- El campo no puede ir vacio ----");
        }

        sc.close();

        return entrada;
    }

    private static Double introducirDatosDouble() {
        Boolean repetir = true;
        String entrada;

        do {
            entrada = introducirDatosString();

            if (!Double.isNaN(Double.parseDouble(entrada))) repetir = false;
            else System.out.print("El numero introducido es incorrecto.\nPor favor introduce de nuevo: ");
        } while (repetir);

        return Double.parseDouble(entrada);
    }

    private static Date introducirDatosDate() {
        return null;
    }
}