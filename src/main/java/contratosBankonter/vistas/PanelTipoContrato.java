package contratosBankonter.vistas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import contratosBankonter.controladores.ControladorTipoContrato;
import contratosBankonter.entidades.Tipocontrato;
import contratosBankonter.tablas.MiTableModel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelTipoContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	List<Tipocontrato> tipos = new ArrayList<Tipocontrato>();
	ControladorTipoContrato ctc = new ControladorTipoContrato();
	private JTextField jtfFiltro;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public PanelTipoContrato() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 1;
		gbc_jtfFiltro.gridy = 1;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrarContratos();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltrar.gridx = 2;
		gbc_btnFiltrar.gridy = 1;
		add(btnFiltrar, gbc_btnFiltrar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		for(Tipocontrato tc : (List<Tipocontrato>)ctc.findAll()) {
			tipos.add(tc);
		}
		
		MiTableModel tableModel = new MiTableModel(tipos);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) { // Verifica si el clic es doble
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                contratosBankonter.vistas.Principal.setTipo((int) table.getValueAt(selectedRow, 0));
		                contratosBankonter.vistas.Principal.cerrarDialogo();
		            }
		        }
		    }
		});

		scrollPane.setViewportView(table);
	}
	
	/**
	 * 
	 */
	
	private void filtrarContratos() {
		
		if(!this.jtfFiltro.getText().equals("")) {
			List<Tipocontrato> tiposFiltrados = new ArrayList<Tipocontrato>();
			for(Tipocontrato tc : tipos) {
				if(tc.getDescripcion().toLowerCase().contains(this.jtfFiltro.getText().toLowerCase())) tiposFiltrados.add(tc);
			}
			MiTableModel tableModel = new MiTableModel(tiposFiltrados);
			table = new JTable(tableModel);
			scrollPane.setViewportView(table);
		}
	}

}
