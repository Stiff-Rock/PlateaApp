package Vistas;

//@Autor: Anton Luo
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class _03_Home extends Menus {
	private JTable table;
	private JTextField txtBuscador;

	public void cargarTabla(DefaultTableModel tablaInicio) {
		table.setModel(tablaInicio);
	}

	public _03_Home() {
		setTitle("Inicio");
		setContentPane(mainPanel);

		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		JPanel tablaPanel = new JPanel();
		tablaPanel.setBounds(45, 50, 695, 595);
		contentPanel.add(tablaPanel);
		tablaPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablaPanel.setForeground(new Color(162, 196, 201));
		tablaPanel.setBackground(new Color(207, 226, 243));
		tablaPanel.setLayout(null);

		JScrollPane tablaPane = new JScrollPane();
		tablaPane.setBounds(10, 60, 675, 525);
		tablaPanel.add(tablaPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila y columna donde se hizo clic
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				// Obtener el valor del primer campo de la fila donde se hizo clic
				String valor = table.getValueAt(fila, 0).toString();
				controlador.prepararPublicacion(valor);
				controlador.cambiarVentana(3, 9);
			}
		});
		
		tablaPane.setViewportView(table);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablaPanel.add(filtrosPanel);

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		lblLupa.setIcon(lupa);
		filtrosPanel.add(lblLupa);

		txtBuscador = new JTextField();
		txtBuscador.setColumns(10);
		txtBuscador.setBounds(27, 11, 271, 26);
		filtrosPanel.add(txtBuscador);

		JLabel lblFiltrosTxt = new JLabel("Filtros:");
		lblFiltrosTxt.setBounds(308, 17, 46, 14);
		filtrosPanel.add(lblFiltrosTxt);

		JComboBox comboBoxFecha = new JComboBox();
		comboBoxFecha.setModel(new DefaultComboBoxModel(new String[] { "Fecha" }));
		comboBoxFecha.setBounds(355, 13, 101, 22);
		filtrosPanel.add(comboBoxFecha);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Estado" }));
		comboBoxEstado.setBounds(466, 13, 101, 22);
		filtrosPanel.add(comboBoxEstado);

		JComboBox comboBoxProximidad = new JComboBox();
		comboBoxProximidad.setModel(new DefaultComboBoxModel(new String[] { "Proximidad" }));
		comboBoxProximidad.setBounds(577, 13, 101, 22);
		filtrosPanel.add(comboBoxProximidad);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setBounds(609, 660, 131, 35);
		contentPanel.add(btnPublicar);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(3, 10);
			}
		});
		btnPublicar.setIcon(mas);
		btnPublicar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				String campo = "ESTADO";
				String operador = "!=";
				String valor = "Nueva";
				table.setModel(controlador.getTabla1(campo, operador, valor));
			}
		});
	}
}
