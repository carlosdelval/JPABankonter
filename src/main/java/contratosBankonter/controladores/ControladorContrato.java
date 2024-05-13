package contratosBankonter.controladores;

import javax.persistence.EntityManager;

import contratosBankonter.entidades.Contrato;
import contratosBankonter.entidades.Usuario;


public class ControladorContrato extends SuperControladorJPA{

	public ControladorContrato() {
		super("contrato", Contrato.class);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Contrato p) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}
	
	/**
	 * 
	 */
	public void remove(Contrato c) {
		EntityManager em = getEntityManager();
		Contrato actual = null;
		em.getTransaction().begin();
		if (!em.contains(c)) {
			actual = em.merge(c);
		}
		em.remove(actual);
		em.getTransaction().commit();
		em.close();
	}
}
