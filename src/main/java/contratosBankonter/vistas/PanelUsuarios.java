package contratosBankonter.vistas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import contratosBankonter.controladores.ControladorUsuario;
import contratosBankonter.entidades.Usuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PanelUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JCheckBox chckSensitive;
	private JRadioButton rbtnEmail;
	private JRadioButton rbtnNombreUsu;
	private List<Usuario> usuarios = new ArrayList<>();
	private DefaultListModel<Usuario> listModelUsuario = null;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnRecargar;

	/**
	 * Create the panel.
	 */
	public PanelUsuarios() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 0;
		gbc_jtfFiltro.gridy = 0;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtraUsuarios();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 1;
		gbc_btnFiltrar.gridy = 0;
		add(btnFiltrar, gbc_btnFiltrar);
		
		rbtnNombreUsu = new JRadioButton("Búsqueda sobre nombre usuario");
		GridBagConstraints gbc_rbtnNombreUsu = new GridBagConstraints();
		gbc_rbtnNombreUsu.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnNombreUsu.gridx = 0;
		gbc_rbtnNombreUsu.gridy = 1;
		add(rbtnNombreUsu, gbc_rbtnNombreUsu);
		
		btnRecargar = new JButton("Recargar");
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargaUsuarios();
			}
		});
		GridBagConstraints gbc_btnRecargar = new GridBagConstraints();
		gbc_btnRecargar.insets = new Insets(0, 0, 5, 0);
		gbc_btnRecargar.gridx = 1;
		gbc_btnRecargar.gridy = 1;
		add(btnRecargar, gbc_btnRecargar);
		
		rbtnEmail = new JRadioButton("Búsqueda sobre e-mail");
		GridBagConstraints gbc_rbtnEmail = new GridBagConstraints();
		gbc_rbtnEmail.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnEmail.gridx = 0;
		gbc_rbtnEmail.gridy = 2;
		add(rbtnEmail, gbc_rbtnEmail);
		
		chckSensitive = new JCheckBox("Case-sensitive");
		GridBagConstraints gbc_chckSensitive = new GridBagConstraints();
		gbc_chckSensitive.insets = new Insets(0, 0, 5, 5);
		gbc_chckSensitive.gridx = 0;
		gbc_chckSensitive.gridy = 3;
		add(chckSensitive, gbc_chckSensitive);
		
		cargaUsuarios();
		
		scrollPane = new JScrollPane(list);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		
		JButton btnSeleccion = new JButton("Seleccionar usuario");
		GridBagConstraints gbc_btnSeleccion = new GridBagConstraints();
		gbc_btnSeleccion.insets = new Insets(0, 0, 0, 5);
		gbc_btnSeleccion.gridx = 0;
		gbc_btnSeleccion.gridy = 5;
		add(btnSeleccion, gbc_btnSeleccion);

	}
	
	private void filtraUsuarios() {
		ControladorUsuario cu = new ControladorUsuario();
		for(Usuario u : (List<Usuario>)cu.findAll()) {
			if(this.rbtnEmail.isSelected()) {
				if(this.chckSensitive.isEnabled()) {
					if(!u.getEmail().contains(this.jtfFiltro.getText())) listModelUsuario.removeElement(u);
				}else {
					if(!u.getEmail().toLowerCase().contains(this.jtfFiltro.getText().toLowerCase())) listModelUsuario.removeElement(u);
				}
			}else {
				if(this.chckSensitive.isEnabled()) {
					if(!u.getNombreUsuario().contains(this.jtfFiltro.getText())) listModelUsuario.removeElement(u);
				}else {
					if(!u.getNombreUsuario().toLowerCase().contains(this.jtfFiltro.getText().toLowerCase())) listModelUsuario.removeElement(u);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	
	private DefaultListModel getDefaultListModel (List<Usuario> lista) {
		this.listModelUsuario = new DefaultListModel<Usuario>();
		for(Usuario u : lista) {
			this.listModelUsuario.addElement(u);
		}
		return this.listModelUsuario;
	}
	
	/**
	 * 
	 */
	
	private void cargaUsuarios() {
		ControladorUsuario cu = new ControladorUsuario();
		usuarios.clear();
		for(Usuario u : (List<Usuario>)cu.findAll()) {
			usuarios.add(u);
		}
		list = new JList(this.getDefaultListModel(usuarios));
		this.list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	/**
	 * 
	 */
	
	private void seleccionarUsuario() {
		
	}
}
