package App;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Home extends JFrame {
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JPanel panel_2;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable table;
	private JLabel lblNewLabel_3;

	public Home() {

		ImageIcon logo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(170, 65, Image.SCALE_SMOOTH));
		ImageIcon casa = new ImageIcon(new ImageIcon(this.getClass().getResource("/casa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon usuario = new ImageIcon(new ImageIcon(this.getClass().getResource("/usuario.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon libro = new ImageIcon(new ImageIcon(this.getClass().getResource("/libro-abierto.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon estrella = new ImageIcon(new ImageIcon(this.getClass().getResource("/estrella.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon pulgar = new ImageIcon(
				new ImageIcon(this.getClass().getResource("/pulgar-hacia-arriba-simbolo-de-la-mano.png")).getImage()
						.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon info = new ImageIcon(new ImageIcon(this.getClass().getResource("/informacion.png")).getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		getContentPane().setForeground(new Color(162, 196, 201));
		setBounds(100, 100, 1024, 760);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(162, 196, 201));
		panel_1.setBounds(0, 0, 202, 721);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setForeground(new Color(162, 196, 201));
		panel.setBackground(new Color(207, 226, 243));
		panel.setBounds(257, 65, 695, 595);
		getContentPane().add(panel);
		panel.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(0, 0, 695, 49);
		panel.add(panel_2);
		panel_2.setBackground(new Color(162, 196, 201));
		panel_2.setForeground(new Color(255, 255, 255));
		panel_2.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(lupa);
		lblNewLabel_1.setBounds(36, 17, 46, 14);
		panel_2.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(29, 11, 274, 26);
		panel_2.add(textField);
		textField.setColumns(10);

		lblNewLabel_2 = new JLabel("Filtros:");
		lblNewLabel_2.setBounds(319, 17, 46, 14);
		panel_2.add(lblNewLabel_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Fecha" }));
		comboBox.setBounds(375, 13, 101, 22);
		panel_2.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Estado", "Pendiente", "Resuelto" }));
		comboBox_1.setBounds(521, 13, 101, 22);
		panel_2.add(comboBox_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 675, 525);
		panel.add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "\u00C1rbol ca\u00EDdo", "2024-05-15", "Madrid", "Pendiente",
						"Un \u00E1rbol ha ca\u00EDdo sobre la acera bloqueando el paso peatonal" },
				{ "Fuga de agua", "2024-05-18", "Barcelona", "En progreso",
						"Se ha detectado una fuga de agua en una tuber\u00EDa subterr\u00E1nea, se est\u00E1 trabajando en su reparaci\u00F3n" },
				{ "Accidente de tr\u00E1fico", "2024-05-20", "Valencia", "Resuelto",
						"Se ha producido un choque entre dos veh\u00EDculos en una intersecci\u00F3n, ya se han despejado los veh\u00EDculos involucrados y se ha restablecido el tr\u00E1fico" },
				{ "Corte de energ\u00EDa", "2024-05-23", "Sevilla", "Pendiente",
						"Se ha producido un corte de energ\u00EDa en varios edificios del centro de la ciudad, se est\u00E1 investigando la causa" },
				{ "Inundaci\u00F3n", "2024-05-28", "Bilbao", "En progreso",
						"Las intensas lluvias han causado inundaciones en varias calles del barrio, equipos de rescate est\u00E1n ayudando a los residentes afectados" },
				{ "Deslizamiento de tierra", "2024-06-02", "Granada", "Pendiente",
						"Un deslizamiento de tierra ha bloqueado una carretera rural en las afueras de la ciudad, se est\u00E1 evaluando la situaci\u00F3n para iniciar labores de limpieza" },
				{ "Incendio forestal", "2024-06-05", "M\u00E1laga", "En progreso",
						"Se ha declarado un incendio forestal en las monta\u00F1as cercanas a la ciudad, los bomberos est\u00E1n combatiendo las llamas y evacuando \u00E1reas cercanas" },
				{ "Interrupci\u00F3n del servicio de transporte p\u00FAblico", "2024-06-08", "Zaragoza", "Resuelto",
						"Una aver\u00EDa en la red de transporte p\u00FAblico ha causado la interrupci\u00F3n del servicio de autobuses en varias rutas principales, la situaci\u00F3n ha sido solventada y el servicio ha sido restablecido" },
				{ "Escape de gas", "2024-06-11", "Palma de Mallorca", "Pendiente",
						"Se ha detectado un escape de gas en una zona residencial, los equipos de emergencia est\u00E1n trabajando para controlar la situaci\u00F3n y evacuar a los residentes" },
				{ "Árbol caído", "2024-05-15", "Madrid", "Pendiente",
						"Un árbol ha caído sobre la acera bloqueando el paso peatonal" },
				{ "Fuga de agua", "2024-05-18", "Barcelona", "En progreso",
						"Se ha detectado una fuga de agua en una tubería subterránea, se está trabajando en su reparación" },
				{ "Accidente de tráfico", "2024-05-20", "Valencia", "Resuelto",
						"Se ha producido un choque entre dos vehículos en una intersección, ya se han despejado los vehículos involucrados y se ha restablecido el tráfico" },
				{ "Corte de energía", "2024-05-23", "Sevilla", "Pendiente",
						"Se ha producido un corte de energía en varios edificios del centro de la ciudad, se está investigando la causa" },
				{ "Inundación", "2024-05-28", "Bilbao", "En progreso",
						"Las intensas lluvias han causado inundaciones en varias calles del barrio, equipos de rescate están ayudando a los residentes afectados" },
				{ "Deslizamiento de tierra", "2024-06-02", "Granada", "Pendiente",
						"Un deslizamiento de tierra ha bloqueado una carretera rural en las afueras de la ciudad, se está evaluando la situación para iniciar labores de limpieza" },
				{ "Incendio forestal", "2024-06-05", "Málaga", "En progreso",
						"Se ha declarado un incendio forestal en las montañas cercanas a la ciudad, los bomberos están combatiendo las llamas y evacuando áreas cercanas" },
				{ "Interrupción del servicio de transporte público", "2024-06-08", "Zaragoza", "Resuelto",
						"Una avería en la red de transporte público ha causado la interrupción del servicio de autobuses en varias rutas principales, la situación ha sido solventada y el servicio ha sido restablecido" },
				{ "Escape de gas", "2024-06-11", "Palma de Mallorca", "Pendiente",
						"Se ha detectado un escape de gas en una zona residencial, los equipos de emergencia están trabajando para controlar la situación y evacuar a los residentes" },
				{ "Socavón en la carretera", "2024-06-14", "Murcia", "En progreso",
						"Un socavón ha aparecido en una carretera principal, causando desviaciones en el tráfico mientras se realizan las reparaciones" },
				{ "Fuga de químicos", "2024-06-17", "Vigo", "Pendiente",
						"Se ha informado de una fuga de productos químicos en una fábrica local, las autoridades han ordenado una evacuación preventiva de la zona" },
				{ "Árbol caído", "2024-06-19", "Santander", "Resuelto",
						"Un árbol caído ha bloqueado una vía secundaria, pero ha sido removido rápidamente por las autoridades" },
				{ "Inundación de sótanos", "2024-06-21", "Alicante", "Pendiente",
						"Las fuertes lluvias han causado la inundación de varios sótanos en el centro de la ciudad, los bomberos están trabajando para bombear el agua" },
				{ "Atasco de tráfico", "2024-06-24", "Mérida", "Resuelto",
						"Un accidente menor ha causado un gran atasco de tráfico en una carretera principal, pero la situación ya ha sido resuelta" },
				{ "Falla en el suministro de agua", "2024-06-27", "Burgos", "En progreso",
						"Una falla en una tubería principal ha dejado sin agua a varios barrios, los equipos de reparación están trabajando para resolverlo" },
				{ "Incendio en edificio", "2024-06-30", "Córdoba", "Pendiente",
						"Un incendio ha estallado en un edificio de apartamentos, los bomberos están en el lugar tratando de controlar el fuego" },
				{ "Desprendimiento de rocas", "2024-07-02", "León", "En progreso",
						"Un desprendimiento de rocas ha cerrado una carretera montañosa, las cuadrillas están trabajando para limpiar el área" },
				{ "Manifestación", "2024-07-05", "Pamplona", "Resuelto",
						"Una manifestación ha causado el cierre temporal de varias calles en el centro de la ciudad, pero el tráfico ya ha sido restablecido" },
				{ "Accidente laboral", "2024-07-08", "Salamanca", "Pendiente",
						"Un trabajador ha sufrido un accidente en una obra de construcción, se están investigando las causas del incidente" },
				{ "Interrupción del servicio eléctrico", "2024-07-11", "Huesca", "Resuelto",
						"Un fallo en la red eléctrica ha dejado sin suministro a varios barrios durante varias horas, pero el servicio ya ha sido restablecido" },
				{ "Árbol caído", "2024-05-15", "Madrid", "Pendiente",
						"Un árbol ha caído sobre la acera bloqueando el paso peatonal" },
				{ "Fuga de agua", "2024-05-18", "Barcelona", "En progreso",
						"Se ha detectado una fuga de agua en una tubería subterránea, se está trabajando en su reparación" },
				{ "Accidente de tráfico", "2024-05-20", "Valencia", "Resuelto",
						"Se ha producido un choque entre dos vehículos en una intersección, ya se han despejado los vehículos involucrados y se ha restablecido el tráfico" },
				{ "Corte de energía", "2024-05-23", "Sevilla", "Pendiente",
						"Se ha producido un corte de energía en varios edificios del centro de la ciudad, se está investigando la causa" },
				{ "Inundación", "2024-05-28", "Bilbao", "En progreso",
						"Las intensas lluvias han causado inundaciones en varias calles del barrio, equipos de rescate están ayudando a los residentes afectados" },
				{ "Deslizamiento de tierra", "2024-06-02", "Granada", "Pendiente",
						"Un deslizamiento de tierra ha bloqueado una carretera rural en las afueras de la ciudad, se está evaluando la situación para iniciar labores de limpieza" },
				{ "Incendio forestal", "2024-06-05", "Málaga", "En progreso",
						"Se ha declarado un incendio forestal en las montañas cercanas a la ciudad, los bomberos están combatiendo las llamas y evacuando áreas cercanas" },
				{ "Interrupción del servicio de transporte público", "2024-06-08", "Zaragoza", "Resuelto",
						"Una avería en la red de transporte público ha causado la interrupción del servicio de autobuses en varias rutas principales, la situación ha sido solventada y el servicio ha sido restablecido" },
				{ "Escape de gas", "2024-06-11", "Palma de Mallorca", "Pendiente",
						"Se ha detectado un escape de gas en una zona residencial, los equipos de emergencia están trabajando para controlar la situación y evacuar a los residentes" },
				{ "Socavón en la carretera", "2024-06-14", "Murcia", "En progreso",
						"Un socavón ha aparecido en una carretera principal, causando desviaciones en el tráfico mientras se realizan las reparaciones" },
				{ "Fuga de químicos", "2024-06-17", "Vigo", "Pendiente",
						"Se ha informado de una fuga de productos químicos en una fábrica local, las autoridades han ordenado una evacuación preventiva de la zona" },
				{ "Árbol caído", "2024-06-19", "Santander", "Resuelto",
						"Un árbol caído ha bloqueado una vía secundaria, pero ha sido removido rápidamente por las autoridades" },
				{ "Inundación de sótanos", "2024-06-21", "Alicante", "Pendiente",
						"Las fuertes lluvias han causado la inundación de varios sótanos en el centro de la ciudad, los bomberos están trabajando para bombear el agua" },
				{ "Atasco de tráfico", "2024-06-24", "Mérida", "Resuelto",
						"Un accidente menor ha causado un gran atasco de tráfico en una carretera principal, pero la situación ya ha sido resuelta" },
				{ "Falla en el suministro de agua", "2024-06-27", "Burgos", "En progreso",
						"Una falla en una tubería principal ha dejado sin agua a varios barrios, los equipos de reparación están trabajando para resolverlo" },
				{ "Incendio en edificio", "2024-06-30", "Córdoba", "Pendiente",
						"Un incendio ha estallado en un edificio de apartamentos, los bomberos están en el lugar tratando de controlar el fuego" },
				{ "Desprendimiento de rocas", "2024-07-02", "León", "En progreso",
						"Un desprendimiento de rocas ha cerrado una carretera montañosa, las cuadrillas están trabajando para limpiar el área" },
				{ "Manifestación", "2024-07-05", "Pamplona", "Resuelto",
						"Una manifestación ha causado el cierre temporal de varias calles en el centro de la ciudad, pero el tráfico ya ha sido restablecido" },
				{ "Accidente laboral", "2024-07-08", "Salamanca", "Pendiente",
						"Un trabajador ha sufrido un accidente en una obra de construcción, se están investigando las causas del incidente" },
				{ "Interrupción del servicio eléctrico", "2024-07-11", "Huesca", "Resuelto",
						"Un fallo en la red eléctrica ha dejado sin suministro a varios barrios durante varias horas, pero el servicio ya ha sido restablecido" },
				{ "Incidente en parque infantil", "2024-07-14", "Toledo", "Pendiente",
						"Un columpio se ha roto en un parque infantil, causando heridas leves a un niño, el área ha sido cerrada para reparaciones" },
				{ "Avería en semáforo", "2024-07-17", "Logroño", "Pendiente",
						"Un semáforo fuera de servicio está causando problemas de tráfico en una intersección concurrida, los técnicos están en camino para repararlo" },
				{ "Huelga de transporte público", "2024-07-20", "Oviedo", "En progreso",
						"Los trabajadores del transporte público están en huelga, afectando los servicios de autobús y tren en la ciudad" },
				{ "Contaminación del río", "2024-07-23", "Guadalajara", "Pendiente",
						"Se ha detectado una alta concentración de contaminantes en el río local, las autoridades están investigando el origen y tomando medidas para limpiar el agua" },
				{ "Rotura de tubería", "2024-07-26", "Badajoz", "Resuelto",
						"Una tubería rota ha causado una gran fuga de agua en una calle principal, la situación ha sido controlada y la tubería reparada" },
				{ "Incidente en aeropuerto", "2024-07-29", "A Coruña", "Pendiente",
						"Un fallo técnico ha causado retrasos y cancelaciones de vuelos en el aeropuerto, los ingenieros están trabajando para resolver el problema" },
				{ "Plaga de insectos", "2024-08-01", "Valladolid", "En progreso",
						"Una plaga de insectos ha afectado varios parques y jardines, se están aplicando tratamientos para controlarla" },
				{ "Cierre de puente", "2024-08-04", "Almería", "Pendiente",
						"Un puente ha sido cerrado debido a problemas estructurales detectados durante una inspección rutinaria, se están planificando las reparaciones necesarias" },
				{ "Problema de alcantarillado", "2024-08-07", "Cádiz", "En progreso",
						"Un bloqueo en el sistema de alcantarillado está causando inundaciones en algunas calles, las cuadrillas están trabajando para despejar el bloqueo" },
				{ "Interrupción de Internet", "2024-08-10", "Gijón", "Resuelto",
						"Un corte en la red de fibra óptica ha dejado sin Internet a varias áreas, el servicio ha sido restablecido después de varias horas" },
				{ "Desalojo por peligro de derrumbe", "2024-08-13", "Tarragona", "Pendiente",
						"Un edificio ha sido desalojado debido a un riesgo inminente de derrumbe, los residentes han sido reubicados temporalmente" },
				{ "Caída de señal de móvil", "2024-08-16", "Castellón", "Pendiente",
						"Una falla en una torre de telecomunicaciones ha dejado sin servicio de móvil a varias zonas, los técnicos están trabajando para reparar la torre" },
				{ "Huelga de limpieza", "2024-08-19", "San Sebastián", "En progreso",
						"Los trabajadores de limpieza están en huelga, causando acumulación de basura en las calles, se están llevando a cabo negociaciones para resolver el conflicto" },
				{ "Fuga de gas en escuela", "2024-08-22", "Vitoria", "Resuelto",
						"Se ha detectado una fuga de gas en una escuela, los estudiantes han sido evacuados y la situación ha sido controlada" },
				{ "Incendio en mercado", "2024-08-25", "Las Palmas", "En progreso",
						"Un incendio ha estallado en un mercado local, los bomberos están combatiendo las llamas y evacuando a los comerciantes y clientes" },
				{ "Problema de suministro de agua potable", "2024-08-28", "Huelva", "Pendiente",
						"Un problema en la planta de tratamiento ha afectado el suministro de agua potable, se están tomando medidas para solucionar el problema" },
				{ null, null, null, null, null }, },
				new String[] { "Titulo", "Fecha", "Localizacion", "Estado", "Descripcion" }));

		scrollPane.setViewportView(table);

		btnNewButton = new JButton("Home");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(casa);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(35, 156, 135, 40);
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("Mi perfil");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(usuario);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnNewButton_1.setBounds(35, 229, 135, 40);
		panel_1.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Publicaciones");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(libro);
		btnNewButton_2.setBounds(35, 304, 135, 40);
		panel_1.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Favoritos");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(estrella);
		btnNewButton_3.setBounds(35, 380, 135, 40);
		panel_1.add(btnNewButton_3);

		btnNewButton_4 = new JButton("Votados");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setIcon(pulgar);
		btnNewButton_4.setBounds(35, 459, 135, 40);
		panel_1.add(btnNewButton_4);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(logo);
		lblNewLabel.setBounds(10, 40, 192, 82);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(info);
		lblNewLabel_3.setBounds(156, 670, 46, 40);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(info);

		JButton btnNewButton_5 = new JButton("Publicar");
		btnNewButton_5.setIcon(mas);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(821, 671, 131, 35);
		getContentPane().add(btnNewButton_5);

		setVisible(true);
	}
}
