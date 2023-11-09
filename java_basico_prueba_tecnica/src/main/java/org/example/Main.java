package org.example;

import org.example.complements.Auxiliar;
import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ControladoraPersistencia controladora = new ControladoraPersistencia();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Bienbenido al programa.");

        menu();

        System.out.println("El programa ha finalizado");
        sc.close();
       /*
        List<Empleado> listEmpleados = new ArrayList<>();
        Empleado empleado1 = new Empleado(controladora.buscarEmpleadoLastId(), "Carlos", "Jaquez", "Cargo 1", 1000.0, stringToDate("6/11/2023"));
        Empleado empleado2 = new Empleado(controladora.buscarEmpleadoLastId(), "Miguel", "German", "Cargo 2", 1100.0, stringToDate("5/10/2022"));
        Empleado empleado3 = new Empleado(controladora.buscarEmpleadoLastId(), "Fernando", "Santos", "Cargo 1", 1200.0, stringToDate("10/11/2020"));
        Empleado empleado4 = new Empleado(controladora.buscarEmpleadoLastId(), "Anny", "Marte", "Cargo 3", 1300.0, stringToDate("15/11/2013"));
        Empleado empleado5 = new Empleado(controladora.buscarEmpleadoLastId(), "Marta", "Cuevas", "Cargo 4", 1400.0, stringToDate("6/1/2018"));


        mostrarEmpleado(buscarEmpleado());
        mostrarEmpleado(buscarEmpleado("Cargo 4"));
        mostrarEmpleado(buscarEmpleado("Cargo 1"));

        */
    }


    public static void registrarEmpleado(Empleado empleado) {
        controladora.agregarEmpleado(empleado);
        System.out.println("Empleado registrado");
    }

    public static List<Empleado> buscarEmpleado() {
        return controladora.buscarEmpleados();
    }

    public static Empleado buscarEmpleado(long id) {
        return controladora.buscarEmpleadoById(id);
    }

    public static List<Empleado> buscarEmpleado(String campo) {
        return controladora.buscarEmpleadosByCargo(campo);
    }

    public static void actualizarEmpleado(Empleado empleado) {
        controladora.modificarEmpleado(empleado);
        System.out.println("Actualizacion realizada");
    }

    public static void eliminarEmpleado(long id) {
        controladora.eliminarEmpleado(id);
    }

    private static void mostrarEmpleado(List<Empleado> empleados) {
        for (Empleado empleado : empleados)
            System.out.println(empleado);
    }

    private static Empleado crearEmpleado() {
        Empleado nuevoEmpleado = null;
        String entradaDatos;

        do {
            System.out.println("Escribe 'exit' para terminar, si deseas crear un nuevo empleado escribe 1");
            entradaDatos = Auxiliar.introducirDatosString();

            if (entradaDatos.equals("1")) {
                System.out.println("Para crear el nuevo empleado, introduce los siguientes datos: ");

                System.out.print("Nombre: ");
                String nombre = Auxiliar.introducirDatosString();

                System.out.print("Apellido: ");
                String apellido = Auxiliar.introducirDatosString();

                System.out.print("Cargo: ");
                String cargo = Auxiliar.introducirDatosString();

                System.out.print("salario : ");
                Double salario = Auxiliar.introducirDatosDouble();

                System.out.println("Fecha de inicio");
                Date fechaInicio = Auxiliar.introducirDatosDate();

                Long id = controladora.buscarEmpleadoLastId();

                nuevoEmpleado = new Empleado(id, nombre, apellido, cargo, salario, fechaInicio);
            }

        } while (entradaDatos.equals("salir"));

        return nuevoEmpleado;
    }

    private static Empleado modificarEmpleado() {
        mostrarEmpleado(buscarEmpleado());

        System.out.print("Cual es el ID del empleado que quieres modificar?: ");
        Empleado empleado = buscarEmpleado(Auxiliar.introducirDatosInteger());

        if (empleado != null) {
            System.out.print("Que parametro quieres modificar: ");
            String parametro = Auxiliar.introducirDatosString();

            switch (parametro) {
                case "nombre":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleado.setNombre(Auxiliar.introducirDatosString());
                    break;
                case "apellido":
                    System.out.print("Escribe el nuevo Apellido: ");
                    empleado.setApellido(Auxiliar.introducirDatosString());
                    break;
                case "cargo":
                    System.out.print("Escribe el nuevo cargo: ");
                    empleado.setCargo(Auxiliar.introducirDatosString());
                    break;
                case "salario":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleado.setSalario(Auxiliar.introducirDatosDouble());
                    break;
                case "fechaInicio":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleado.setFechaInicio(Auxiliar.introducirDatosDate());
                    break;
                default:
                    System.err.println("El parametro indicado no existe");
            }
        } else
            System.out.println("El ID de empleado no existe");

        return empleado;
    }

    private static void menu() {
        String msg = "Que opcion desea hacer?";
        String opciones = "1 - Mostrar empleados de la lista." +
                "\n2 - Mostrar empleados en un cargo" +
                "\n3 - Agregar un nuevo empleado." +
                "\n4 - Modificar un empleado" +
                "\n5 - Eliminar un empleado" +
                "\n6 - Salir";
        Boolean repetir = true;

        do {
            System.out.println(msg);
            System.out.println(opciones);
            System.out.printf("Introduce opcion: ");
            Integer respuesta = Auxiliar.introducirDatosInteger();

            if (respuesta > 0 && respuesta <= 6) switch (respuesta) {
                case 1:
                    mostrarEmpleado(buscarEmpleado());
                    break;
                case 2:
                    System.out.println("Que cargo desea buscar?");
                    System.out.printf("Introduce cargo: ");
                    mostrarEmpleado(buscarEmpleado(Auxiliar.introducirDatosString()));
                    break;
                case 3:
                    registrarEmpleado(crearEmpleado());
                    break;
                case 4:
                    Empleado empleado = modificarEmpleado();

                    if (empleado != null)
                        actualizarEmpleado(empleado);
                    else
                        System.out.println("No se han realizado cambios");
                    break;
                case 5:
                    System.out.print("Cual es el id del empleado que quieres borrar?: ");
                    eliminarEmpleado(Auxiliar.introducirDatosInteger());
                    break;
                default:
                    repetir = false;
            }
        } while (repetir);
    }
}