package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

//@Autor: Anton Luo
public class _05_MisPublicaciones extends Menus {
	private JPanel filtrosPanel;
	private JLabel lblFiltrosTxt;
	private JTable table;
	private JComboBox comboBoxProximidad;
	private JLabel lblTitle;

	public _05_MisPublicaciones() {
		setTitle("Mis publicaciones");
		setContentPane(mainPanel);
		
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon borrar = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/Quitar.png")).getImage()
				.getScaledInstance(16, 16, Image.SCALE_SMOOTH));

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablePanel.setForeground(new Color(162, 196, 201));
		tablePanel.setBackground(new Color(207, 226, 243));
		tablePanel.setBounds(45, 50, 695, 595);
		contentPanel.add(tablePanel);
		tablePanel.setLayout(null);

		filtrosPanel = new JPanel();
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablePanel.add(filtrosPanel);
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setForeground(new Color(255, 255, 255));
		filtrosPanel.setLayout(null);

		lblFiltrosTxt = new JLabel("Filtros:");
		lblFiltrosTxt.setBounds(308, 17, 46, 14);
		filtrosPanel.add(lblFiltrosTxt);

		JComboBox comboBoxFecha = new JComboBox();
		comboBoxFecha.setModel(new DefaultComboBoxModel(new String[] { "Fecha" }));
		comboBoxFecha.setBounds(355, 13, 101, 22);
		filtrosPanel.add(comboBoxFecha);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Estado", "Pendiente", "Resuelto" }));
		comboBoxEstado.setBounds(466, 13, 101, 22);
		filtrosPanel.add(comboBoxEstado);

		comboBoxProximidad = new JComboBox();
		comboBoxProximidad.setModel(new DefaultComboBoxModel(new String[] { "Proximidad" }));
		comboBoxProximidad.setBounds(577, 13, 101, 22);
		filtrosPanel.add(comboBoxProximidad);

		lblTitle = new JLabel("Mis publicaciones");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(10, 12, 273, 25);
		filtrosPanel.add(lblTitle);

		JScrollPane tablaPane = new JScrollPane();
		tablaPane.setBounds(10, 60, 675, 525);
		tablePanel.add(tablaPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		tablaPane.setViewportView(table);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setIcon(mas);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(5, 10);
			}
		});
		btnPublicar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPublicar.setBounds(609, 660, 131, 35);
		contentPanel.add(btnPublicar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBorrar.setBounds(450, 660, 131, 35);
		btnBorrar.setIcon(borrar);
		contentPanel.add(btnBorrar);
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				table.setModel(controlador.getTabla(5));
			}
		});
	}
}
