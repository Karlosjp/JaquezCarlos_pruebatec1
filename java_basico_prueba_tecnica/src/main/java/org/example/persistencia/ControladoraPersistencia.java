package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonExistentEntityException;

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

    public void modificarEmpleado(Empleado empleado) throws NonExistentEntityException {
        try {
            empleadoJPA.edit(empleado);
        } catch (NonExistentEntityException exception) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, exception.getMessage());
            throw new NonExistentEntityException(exception.getMessage());
        }
    }

    public void eliminarEmpleado(long id) throws NonExistentEntityException {
        try {
            empleadoJPA.destroy(id);
        } catch (NonExistentEntityException exception) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, exception.getMessage());
            throw new NonExistentEntityException(exception.getMessage());
        }
    }

}
