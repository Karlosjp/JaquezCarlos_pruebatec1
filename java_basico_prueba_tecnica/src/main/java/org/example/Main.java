package org.example;

import org.example.complements.Auxiliar;
import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;
import org.example.persistencia.exceptions.NonExistentEntityException;

import java.util.Date;
import java.util.List;

public class Main {
    private static ControladoraPersistencia controladora = new ControladoraPersistencia();

    public static void main(String[] args) {
        System.out.println("Bienbenido al programa.");

        menu();

        System.out.println("El programa ha finalizado");
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
        try {
            controladora.modificarEmpleado(empleado);
            System.out.println("Actualizacion realizada");
        } catch (NonExistentEntityException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static void eliminarEmpleado(long id) {
        try {
            controladora.eliminarEmpleado(id);
            System.out.println("El empleado ha sido");
        } catch (NonExistentEntityException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static void mostrarEmpleado(List<Empleado> empleados) {
        if (empleados.size() > 0) for (Empleado empleado : empleados)
            System.out.println(empleado);
        else System.err.println("No hay empleados para mostrar ");
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

    /*
        Crea un clon del empleado seleccionado y luego lo compara con el original. Si no se han producido cambios no es
        necesario continuar
     */
    private static Empleado modificarEmpleado() {
        mostrarEmpleado(buscarEmpleado());

        System.out.println("Cual es el ID del empleado que quieres modificar?: ");
        Empleado empleado = buscarEmpleado(Auxiliar.introducirDatosInteger());
        Empleado empleadoEditado = null;

        if (empleado != null) {
            System.out.print("Que parametro quieres modificar: ");
            String parametro = Auxiliar.introducirDatosString();
            empleadoEditado = new Empleado(empleado);

            switch (parametro) {
                case "nombre":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleadoEditado.setNombre(Auxiliar.introducirDatosString());
                    break;
                case "apellido":
                    System.out.print("Escribe el nuevo Apellido: ");
                    empleadoEditado.setApellido(Auxiliar.introducirDatosString());
                    break;
                case "cargo":
                    System.out.print("Escribe el nuevo cargo: ");
                    empleadoEditado.setCargo(Auxiliar.introducirDatosString());
                    break;
                case "salario":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleadoEditado.setSalario(Auxiliar.introducirDatosDouble());
                    break;
                case "fechaInicio":
                    System.out.print("Escribe el nuevo nombre: ");
                    empleadoEditado.setFechaInicio(Auxiliar.introducirDatosDate());
                    break;
                default:
                    System.err.println("El parametro indicado no existe");
            }

            if (!empleado.equals(empleadoEditado)) return empleadoEditado;
        } else System.out.println("El ID de empleado no existe");

        return null;
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

                    if (empleado != null) actualizarEmpleado(empleado);
                    else System.out.println("No se han realizado cambios");
                    break;
                case 5:
                    System.out.print("Cual es el id del empleado que quieres borrar?: ");
                    eliminarEmpleado(Auxiliar.introducirDatosInteger());
                    break;
                default:
                    repetir = false;
            }
            else System.out.println("La opcion elegida no existe. (Las opciones son del 1 al 6)");
        } while (repetir);
    }
}