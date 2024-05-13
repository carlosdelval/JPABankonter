package contratosBankonter.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario extends SuperEntidad{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nombreUsuario;
	String password;
	String email;
	String colorPreferido;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getColorPreferido() {
		return colorPreferido;
	}
	public void setColorPreferido(String colorPreferido) {
		this.colorPreferido = colorPreferido;
	}
	@Override
	public String toString() {
		return nombreUsuario;
	}
	
	
}
