package contratosBankonter.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipocontrato")
public class Tipocontrato extends SuperEntidad{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String descripcion;
	
	
	@Override
	public String toString() {
		return descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
