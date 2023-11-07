package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ControladoraPersistencia controladora = new ControladoraPersistencia();
        List<Empleado> listEmpleados = new ArrayList<>();

        listEmpleados.add(new Empleado(1L, "Carlos", "Jaquez", "Cargo 1", 1000.0, stringToDate("6/11/2023")));
        listEmpleados.add(new Empleado(2L, "Miguel", "German", "Cargo 2", 1100.0, stringToDate("5/10/2022")));
        listEmpleados.add(new Empleado(3L, "Fernando", "Santos", "Cargo 1", 1200.0, stringToDate("10/11/2020")));
        listEmpleados.add(new Empleado(4L, "Anny", "Marte", "Cargo 3", 1300.0, stringToDate("15/11/2013")));
        listEmpleados.add(new Empleado(5L, "Marta", "Cuevas", "Cargo 4", 1400.0, stringToDate("6/1/2018")));

        for (Empleado empleado : listEmpleados)
            controladora.crearEmpleado(empleado);
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

    public static void mostrarEmpleado(List<Empleado> empleados) {
        for (Empleado empleado : empleados)
            System.out.println(empleado);
    }
}