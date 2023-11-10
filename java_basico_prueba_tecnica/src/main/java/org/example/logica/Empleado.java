package org.example.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private Date fechaInicio;

    public Empleado() {

    }

    public Empleado(long id, String nombre, String apellido, String cargo, double salario, Date fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    public Empleado(Empleado clon) {
        this.id = clon.id;
        this.nombre = clon.nombre;
        this.apellido = clon.apellido;
        this.cargo = clon.cargo;
        this.salario = clon.salario;
        this.fechaInicio = clon.fechaInicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Empleado{" + ", ID = '" + id + '\'' + ", Nombre = '" + nombre + '\'' + ", Apellido = '" + apellido + '\'' + ", Cargo = '" + cargo + '\'' + ", Salario = " + salario + ", Fecha de inicio = " + fechaInicio + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return getId() == empleado.getId() && Double.compare(empleado.getSalario(), getSalario()) == 0 && getNombre().equals(empleado.getNombre()) && getApellido().equals(empleado.getApellido()) && getCargo().equals(empleado.getCargo()) && getFechaInicio().equals(empleado.getFechaInicio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getCargo(), getSalario(), getFechaInicio());
    }
}
