package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmpleadoController {
    private EntityManagerFactory emf = null;

    public EmpleadoController() {
        this.emf = Persistence.createEntityManagerFactory("empresaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;

            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enf) {
                throw new NonexistentEntityException("El empleado con el ID: " + id + " no existe.");
            }

            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            empleado = em.merge(empleado);

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                long id = empleado.getId();

                if (findEmpleadoById(id) == null)
                    throw new NonexistentEntityException("EL empleado con ID: " + id + " no existe");
            }

            throw ex;
        } finally {
            if (em != null) em.close();
        }

    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    // Recupera la lista completa de empleados de la BBDD
    public List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);

            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Empleado> findEmpleadoByCargo(String cargo) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Empleado> query = cb.createQuery(Empleado.class);
            Root<Empleado> empleado = query.from(Empleado.class);
            Query q = em.createQuery(query.where(cb.equal(empleado.get("cargo"), cargo)));

            List<Empleado> listaEmpleados = q.getResultList();

            return listaEmpleados;
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleadoById(long id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    // Busca y devuelve el ultimo id de los datos que hay en la tabla empleados
    public long findEmpleadoLastId() {
        EntityManager em = getEntityManager();

        try {
            List<Empleado> listEmpleados = findEmpleadoEntities();
            Long id = 0L;

            for (Empleado empleado : listEmpleados)
                if (empleado.getId() > id) id = empleado.getId();

            return id;
        } finally {
            em.close();
        }
    }
}
