package contratosBankonter.tablas;

import java.util.List;

import contratosBankonter.controladores.ControladorTipoContrato;
import contratosBankonter.entidades.Tipocontrato;


public class DatosDeTabla {

	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Id", "Descripción"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla() {
		ControladorTipoContrato cp = new ControladorTipoContrato();
		List<Tipocontrato> tipos = (List<Tipocontrato>) cp.findAll();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[tipos.size()][2];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < tipos.size(); i++) {
			Tipocontrato p = tipos.get(i);
			datos[i][0] = p.getId();
			datos[i][1] = p.getDescripcion();
		}
		
		return datos;
	}
	
	
}
