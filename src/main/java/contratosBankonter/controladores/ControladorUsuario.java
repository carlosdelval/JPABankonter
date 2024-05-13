package contratosBankonter.controladores;

import javax.persistence.EntityManager;

import contratosBankonter.entidades.Usuario;



public class ControladorUsuario extends SuperControladorJPA{

	public ControladorUsuario() {
		super("usuario", Usuario.class);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Usuario p) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}
	
	/**
	 * 
	 */
	public void remove(Usuario u) {
		EntityManager em = getEntityManager();
		Usuario actual = null;
		em.getTransaction().begin();
		if (!em.contains(u)) {
			actual = em.merge(u);
		}
		em.remove(actual);
		em.getTransaction().commit();
		em.close();
	}

}
