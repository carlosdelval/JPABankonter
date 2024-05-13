package contratosBankonter.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import contratosBankonter.entidades.SuperEntidad;



public class SuperControladorJPA {

	private static EntityManager em = null;
	private String nombreTabla = "";
	private Class tipoEntidad;
	
	
	public SuperControladorJPA(String nombreTabla, Class tipoEntidad) {
		this.nombreTabla = nombreTabla;
		this.tipoEntidad = tipoEntidad;
	}
	
	
	protected EntityManager getEntityManager() {
		if (em == null) {
			return Persistence.createEntityManagerFactory("bankonter")
					.createEntityManager();
		}
		return em;
	}
	
	
	@SuppressWarnings("unchecked")
	public SuperEntidad findById(int id) {
		try {
			return (SuperEntidad) getEntityManager()
				.find(this.tipoEntidad, id);
		} catch (Exception ex) {
			return null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<? extends SuperEntidad> findAll () {
		return (List<SuperEntidad>) 
		getEntityManager()
		.createNativeQuery("SELECT * FROM " + this.nombreTabla, this.tipoEntidad)
		.getResultList();
	}
	
	
	public SuperEntidad findFirst() {
		
		try {
			return (SuperEntidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " order by id", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
	public SuperEntidad findLast() {
		
		try {
			return (SuperEntidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " order by id desc", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
	public SuperEntidad findNext(int idActual) {
		
		try {
			return (SuperEntidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " where id > " + idActual 
				+ " order by id", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		
	}
	

	public SuperEntidad findPrevious(int idActual) {
		
		try {
			return (SuperEntidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " where id < " + idActual 
				+ " order by id desc", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
}