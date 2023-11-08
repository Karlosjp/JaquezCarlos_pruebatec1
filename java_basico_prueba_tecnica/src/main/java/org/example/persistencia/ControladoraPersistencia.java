package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    EmpleadoController empleadoJPA = new EmpleadoController();

    public void agregarEmpleado(Empleado empleado) {
        empleadoJPA.create(empleado);
    }

    public List<Empleado> buscarEmpleados() {
        return empleadoJPA.findEmpleadoEntities();
    }

    public List<Empleado> buscarEmpleadosByCargo(String cargo) {
        return empleadoJPA.findEmpleadoByCargo(cargo);
    }

    public Empleado buscarEmpleadoById(Long id) {
        return empleadoJPA.findEmpleadoById(id);
    }

    public Long buscarEmpleadoLastId() {
        return empleadoJPA.findEmpleadoLastId();
    }

    public void modificarEmpleado(Empleado empleado) {
        try {
            empleadoJPA.edit(empleado);
        } catch (NonexistentEntityException e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void eliminarEmpleado(long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
