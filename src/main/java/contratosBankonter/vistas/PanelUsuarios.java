package contratosBankonter.vistas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import contratosBankonter.controladores.ControladorUsuario;
import contratosBankonter.entidades.Usuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ButtonGroup;
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
	private ControladorUsuario cu = new ControladorUsuario();
	private List<Usuario> usuarios = (List<Usuario>)cu.findAll();
	private List<Usuario> usuariosFiltrados = new ArrayList<>();
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
		gbc_rbtnNombreUsu.anchor = GridBagConstraints.WEST;
		gbc_rbtnNombreUsu.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnNombreUsu.gridx = 0;
		gbc_rbtnNombreUsu.gridy = 1;
		add(rbtnNombreUsu, gbc_rbtnNombreUsu);
		
		rbtnEmail = new JRadioButton("Búsqueda sobre e-mail");
		GridBagConstraints gbc_rbtnEmail = new GridBagConstraints();
		gbc_rbtnEmail.anchor = GridBagConstraints.WEST;
		gbc_rbtnEmail.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnEmail.gridx = 0;
		gbc_rbtnEmail.gridy = 2;
		add(rbtnEmail, gbc_rbtnEmail);
		
		ButtonGroup grupoOpciones = new ButtonGroup();
		grupoOpciones.add(rbtnEmail);
		grupoOpciones.add(rbtnNombreUsu);
		
		chckSensitive = new JCheckBox("Case-sensitive");
		GridBagConstraints gbc_chckSensitive = new GridBagConstraints();
		gbc_chckSensitive.anchor = GridBagConstraints.WEST;
		gbc_chckSensitive.insets = new Insets(0, 0, 5, 5);
		gbc_chckSensitive.gridx = 0;
		gbc_chckSensitive.gridy = 3;
		add(chckSensitive, gbc_chckSensitive);
		
		list = new JList(this.getDefaultListModel(usuarios));
		this.list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		scrollPane = new JScrollPane(list);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		
		JButton btnSeleccion = new JButton("Seleccionar usuario");
		btnSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarUsuario();
			}
		});
		GridBagConstraints gbc_btnSeleccion = new GridBagConstraints();
		gbc_btnSeleccion.gridwidth = 2;
		gbc_btnSeleccion.insets = new Insets(0, 0, 0, 5);
		gbc_btnSeleccion.gridx = 0;
		gbc_btnSeleccion.gridy = 5;
		add(btnSeleccion, gbc_btnSeleccion);

	}
	
	/**
	 * 
	 */
	
	private void filtraUsuarios() {
		listModelUsuario.clear();
		usuariosFiltrados.clear();
		if(this.jtfFiltro.getText().isEmpty()) {
			for (Usuario u : usuarios) {
                listModelUsuario.addElement(u);
            }
		}else {
			for(Usuario u : usuarios) {
				if(this.rbtnEmail.isSelected()) {
					if(this.chckSensitive.isSelected()) {
						if(u.getEmail().contains(this.jtfFiltro.getText())) usuariosFiltrados.add(u);
					}else {
						if(u.getEmail().toLowerCase().contains(this.jtfFiltro.getText().toLowerCase())) usuariosFiltrados.add(u);
					}
				}
				if(this.rbtnNombreUsu.isSelected()) {
					if(this.chckSensitive.isSelected()) {
						if(u.getNombreUsuario().contains(this.jtfFiltro.getText())) usuariosFiltrados.add(u);
					}else {
						if(u.getNombreUsuario().toLowerCase().contains(this.jtfFiltro.getText().toLowerCase())) usuariosFiltrados.add(u);
					}
				}
			}
			for (Usuario u : usuariosFiltrados) {
                listModelUsuario.addElement(u);
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
	
	private void seleccionarUsuario() {
		contratosBankonter.vistas.Principal.setUser((usuarios.get(list.getSelectedIndex()).getId()));
		contratosBankonter.vistas.Principal.cerrarDialogo();
	}
}
