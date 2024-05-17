package contratosBankonter.vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import contratosBankonter.controladores.ControladorContrato;
import contratosBankonter.controladores.ControladorTipoContrato;
import contratosBankonter.controladores.ControladorUsuario;
import contratosBankonter.entidades.Contrato;
import contratosBankonter.entidades.Tipocontrato;
import contratosBankonter.entidades.Usuario;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfDescripcion;
	private static JTextField jtfTipoContrato;
	private static JTextField jtfUser;
	private static Contrato actual;
	private JSlider sliderSaldo;
	private JFormattedTextField jftfFecha;
	private JSpinner jspinnerLimite;
	private JLabel lblValorSaldo;
	private DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
	private static JDialog dialogo = new JDialog();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblGestinContratos = new JLabel("Gestión Contratos");
		GridBagConstraints gbc_lblGestinContratos = new GridBagConstraints();
		gbc_lblGestinContratos.gridwidth = 6;
		gbc_lblGestinContratos.insets = new Insets(0, 0, 5, 5);
		gbc_lblGestinContratos.gridx = 2;
		gbc_lblGestinContratos.gridy = 0;
		contentPane.add(lblGestinContratos, gbc_lblGestinContratos);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 0;
		gbc_lblDescripcin.gridy = 2;
		contentPane.add(lblDescripcin, gbc_lblDescripcin);
		
		jtfDescripcion = new JTextField();
		GridBagConstraints gbc_jtfDescripcion = new GridBagConstraints();
		gbc_jtfDescripcion.gridwidth = 7;
		gbc_jtfDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_jtfDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDescripcion.gridx = 1;
		gbc_jtfDescripcion.gridy = 2;
		contentPane.add(jtfDescripcion, gbc_jtfDescripcion);
		jtfDescripcion.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 3;
		contentPane.add(lblFecha, gbc_lblFecha);
		
		jftfFecha = new JFormattedTextField();
		GridBagConstraints gbc_jftfFecha = new GridBagConstraints();
		gbc_jftfFecha.gridwidth = 7;
		gbc_jftfFecha.insets = new Insets(0, 0, 5, 5);
		gbc_jftfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftfFecha.gridx = 1;
		gbc_jftfFecha.gridy = 3;
		contentPane.add(jftfFecha, gbc_jftfFecha);
		
		JLabel lblLmite = new JLabel("Límite:");
		GridBagConstraints gbc_lblLmite = new GridBagConstraints();
		gbc_lblLmite.insets = new Insets(0, 0, 5, 5);
		gbc_lblLmite.gridx = 0;
		gbc_lblLmite.gridy = 4;
		contentPane.add(lblLmite, gbc_lblLmite);
		
		jspinnerLimite = new JSpinner();
		jspinnerLimite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sliderSaldo.setMaximum(((Integer)jspinnerLimite.getValue()).intValue());
				sliderSaldo.setValue((int)actual.getSaldo());
			}
		});
		GridBagConstraints gbc_jspinnerLimite = new GridBagConstraints();
		gbc_jspinnerLimite.fill = GridBagConstraints.HORIZONTAL;
		gbc_jspinnerLimite.gridwidth = 7;
		gbc_jspinnerLimite.insets = new Insets(0, 0, 5, 5);
		gbc_jspinnerLimite.gridx = 1;
		gbc_jspinnerLimite.gridy = 4;
		contentPane.add(jspinnerLimite, gbc_jspinnerLimite);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		GridBagConstraints gbc_lblSaldo = new GridBagConstraints();
		gbc_lblSaldo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaldo.gridx = 0;
		gbc_lblSaldo.gridy = 5;
		contentPane.add(lblSaldo, gbc_lblSaldo);
		
		sliderSaldo = new JSlider();
		GridBagConstraints gbc_sliderSaldo = new GridBagConstraints();
		gbc_sliderSaldo.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderSaldo.gridwidth = 7;
		gbc_sliderSaldo.insets = new Insets(0, 0, 5, 5);
		gbc_sliderSaldo.gridx = 1;
		gbc_sliderSaldo.gridy = 5;
		contentPane.add(sliderSaldo, gbc_sliderSaldo);
		
		lblValorSaldo = new JLabel("");
		GridBagConstraints gbc_lblValorSaldo = new GridBagConstraints();
		gbc_lblValorSaldo.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorSaldo.gridx = 8;
		gbc_lblValorSaldo.gridy = 5;
		contentPane.add(lblValorSaldo, gbc_lblValorSaldo);
		
		JLabel lblTipoContrato = new JLabel("Tipo Contrato:");
		GridBagConstraints gbc_lblTipoContrato = new GridBagConstraints();
		gbc_lblTipoContrato.anchor = GridBagConstraints.EAST;
		gbc_lblTipoContrato.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoContrato.gridx = 0;
		gbc_lblTipoContrato.gridy = 6;
		contentPane.add(lblTipoContrato, gbc_lblTipoContrato);
		
		jtfTipoContrato = new JTextField();
		jtfTipoContrato.setEnabled(false);
		GridBagConstraints gbc_jtfTipoContrato = new GridBagConstraints();
		gbc_jtfTipoContrato.gridwidth = 6;
		gbc_jtfTipoContrato.insets = new Insets(0, 0, 5, 5);
		gbc_jtfTipoContrato.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfTipoContrato.gridx = 1;
		gbc_jtfTipoContrato.gridy = 6;
		contentPane.add(jtfTipoContrato, gbc_jtfTipoContrato);
		jtfTipoContrato.setColumns(10);
		
		JButton btnTipoContrato = new JButton("Seleccionar tipo contrato");
		btnTipoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelTipoContrato panelContrato = new PanelTipoContrato();
				contratosBankonter.recursos.Utils.abrirNuevoDialogo(panelContrato, "Tipos de contrato", dialogo);
			}
		});
		GridBagConstraints gbc_btnTipoContrato = new GridBagConstraints();
		gbc_btnTipoContrato.gridwidth = 3;
		gbc_btnTipoContrato.insets = new Insets(0, 0, 5, 0);
		gbc_btnTipoContrato.gridx = 7;
		gbc_btnTipoContrato.gridy = 6;
		contentPane.add(btnTipoContrato, gbc_btnTipoContrato);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 7;
		contentPane.add(lblUsuario, gbc_lblUsuario);
		
		jtfUser = new JTextField();
		GridBagConstraints gbc_jtfUser = new GridBagConstraints();
		gbc_jtfUser.gridwidth = 6;
		gbc_jtfUser.insets = new Insets(0, 0, 5, 5);
		gbc_jtfUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUser.gridx = 1;
		gbc_jtfUser.gridy = 7;
		contentPane.add(jtfUser, gbc_jtfUser);
		jtfUser.setColumns(10);
		
		JButton btnUser = new JButton("Seleccionar usuario ");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelUsuarios panelUsu = new PanelUsuarios();
				contratosBankonter.recursos.Utils.abrirNuevoDialogo(panelUsu, "Selección de usuario", dialogo);
			}
		});
		btnUser.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/conectado.png")));
		GridBagConstraints gbc_btnUser = new GridBagConstraints();
		gbc_btnUser.insets = new Insets(0, 0, 5, 0);
		gbc_btnUser.gridwidth = 3;
		gbc_btnUser.gridx = 7;
		gbc_btnUser.gridy = 7;
		contentPane.add(btnUser, gbc_btnUser);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cargaPrimero();
				cargaInfo(actual);
			}
		});
		btnFirst.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/gotostart.png")));
		GridBagConstraints gbc_btnFirst = new GridBagConstraints();
		gbc_btnFirst.insets = new Insets(0, 0, 5, 5);
		gbc_btnFirst.gridx = 3;
		gbc_btnFirst.gridy = 9;
		contentPane.add(btnFirst, gbc_btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cargaAnterior(actual.getId());
				cargaInfo(actual);
			}
		});
		btnPrevious.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/previous.png")));
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevious.gridx = 4;
		gbc_btnPrevious.gridy = 9;
		contentPane.add(btnPrevious, gbc_btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cargaSiguiente(actual.getId());
				cargaInfo(actual);
			}
		});
		btnNext.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/next.png")));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 5);
		gbc_btnNext.gridx = 5;
		gbc_btnNext.gridy = 9;
		contentPane.add(btnNext, gbc_btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cargaUltimo();
				cargaInfo(actual);
			}
		});
		btnLast.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/gotoend.png")));
		GridBagConstraints gbc_btnLast = new GridBagConstraints();
		gbc_btnLast.insets = new Insets(0, 0, 5, 5);
		gbc_btnLast.gridx = 6;
		gbc_btnLast.gridy = 9;
		contentPane.add(btnLast, gbc_btnLast);
		
		JButton btnNew = new JButton("");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clean();
			}
		});
		btnNew.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/nuevo.png")));
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.insets = new Insets(0, 0, 5, 5);
		gbc_btnNew.gridx = 7;
		gbc_btnNew.gridy = 9;
		contentPane.add(btnNew, gbc_btnNew);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					save();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/guardar.png")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 8;
		gbc_btnSave.gridy = 9;
		contentPane.add(btnSave, gbc_btnSave);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					delete();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(Principal.class.getResource("/contratosBankonter/recursos/eliminar.png")));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.anchor = GridBagConstraints.WEST;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 9;
		gbc_btnDelete.gridy = 9;
		contentPane.add(btnDelete, gbc_btnDelete);
		actual = cargaPrimero();
		cargaInfo(actual);
	}
	
	/**
	 * 
	 * @param c
	 */
	
	private void cargaInfo(Contrato c) {
		ControladorTipoContrato ctc = new ControladorTipoContrato();
		Tipocontrato tipo = (Tipocontrato) ctc.findById(c.getIdTipoContrato());
		ControladorUsuario cu = new ControladorUsuario();
		Usuario user = (Usuario) cu.findById(c.getIdUsuario());
		this.jtfDescripcion.setText(c.getDescripcion());
		this.jftfFecha.setText(sdf.format(c.getFechaFirma()));
		this.jspinnerLimite.setValue((int)c.getLimite());
		this.sliderSaldo.setValue((int)c.getSaldo());
		this.lblValorSaldo.setText("" + (int)c.getSaldo() + " €");
		this.jtfTipoContrato.setText(tipo.getId() + " - " + tipo.getDescripcion());
		this.jtfUser.setText(user.getId() + " - " + user.getNombreUsuario());
	}
	
	/**
	 * 
	 */
	
	private Contrato cargaPrimero() {
		ControladorContrato cc = new ControladorContrato();
		return (Contrato)cc.findFirst();
	}
	
	/**
	 * 
	 */
	
	private Contrato cargaUltimo() {
		ControladorContrato cc = new ControladorContrato();
		return (Contrato)cc.findLast();
	}
	
	/**
	 * 
	 */
	
	private Contrato cargaSiguiente(int idActual) {
		ControladorContrato cc = new ControladorContrato();
		if(idActual != ((Contrato)cc.findLast()).getId()) {
			return (Contrato)cc.findNext(idActual);
		}else {
			return actual;
		}
	}
	
	/**
	 * 
	 */
	
	private Contrato cargaAnterior(int idActual) {
		ControladorContrato cc = new ControladorContrato();
		if(idActual != ((Contrato)cc.findFirst()).getId()) {
			return (Contrato)cc.findPrevious(idActual);
		}else {
			return actual;
		}
	}
	
	/**
	 * 
	 */
	
	private void clean() {
		this.jtfDescripcion.setText("");
		this.jtfTipoContrato.setText("");
		this.jtfUser.setText("");
		this.jftfFecha.setText("");
		this.jspinnerLimite.setValue(0);
		this.sliderSaldo.setValue(0);
	}
	
	/**
	 * @throws ParseException 
	 * 
	 */
	
	private void save() throws ParseException {
		ControladorContrato cc = new ControladorContrato();
		actual.setDescripcion(this.jtfDescripcion.getText());
		actual.setFechaFirma(sdf.parse(this.jftfFecha.getText()));
		actual.setLimite((int)this.jspinnerLimite.getValue());
		actual.setSaldo(this.sliderSaldo.getValue());
		cc.update(actual);
	}
	
	/**
	 * 
	 */
	
	public static void setUser(int id) {
		ControladorUsuario cu = new ControladorUsuario();
		jtfUser.setText(id + " - " + ((Usuario)cu.findById(id)).getNombreUsuario());
		actual.setIdUsuario(id);
	}
	
	/**
	 * 
	 */
	
	public static void setTipo(int id) {
		ControladorTipoContrato ctc = new ControladorTipoContrato();
		jtfTipoContrato.setText(id + " - " + ((Tipocontrato)ctc.findById(id)).getDescripcion());
		actual.setIdUsuario(id);
	}
	
	/**
	 * @throws ParseException 
	 * 
	 */
	
	private void delete() throws ParseException {
		ControladorContrato cc = new ControladorContrato();
		actual.setDescripcion(this.jtfDescripcion.getText());
		actual.setFechaFirma(sdf.parse(this.jftfFecha.getText()));
		actual.setLimite((int)this.jspinnerLimite.getValue());
		actual.setSaldo(this.sliderSaldo.getValue());
		cc.remove(actual);
	}
	
	/**
	 * 
	 */
	
	public static void cerrarDialogo() {
		dialogo.dispose();
	}
}
