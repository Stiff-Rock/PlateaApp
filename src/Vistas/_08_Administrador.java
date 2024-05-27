package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;
import Modelo.Modelo;

//@Autor:Anton Luo
public class _08_Administrador extends JFrame implements Vista {
	private int indice = 8;
	private JPanel mainPanel;
	private JTable table;
	private JButton btnAprobar;
	private JButton btnDenegar;
	private JTextField textCambio;
	private JTextField textField;

	private Controlador controlador;
	private Modelo modelo;
	private NavPanel nav;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void configurarNav() {
		nav.setControlador(controlador);
		nav.setIndiceActual(indice);
	}

	public _08_Administrador() {
		// Panel principal que contendrá todos los componentes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Gestionar");
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		// Agregar el panel de navegación
		nav = new NavPanel();
		mainPanel.add(nav);

		getContentPane().setForeground(new Color(162, 196, 201));
		setBounds(100, 100, 1024, 760);
		getContentPane().setLayout(null);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(212, 11, 786, 695);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(null);

		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon tick = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/crux.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		ImageIcon cruz = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/cerrar.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		ImageIcon lapiz = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lapiz.png")).getImage()
				.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		JPanel tablaPanel = new JPanel();
		tablaPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablaPanel.setForeground(new Color(162, 196, 201));
		tablaPanel.setBackground(new Color(207, 226, 243));
		tablaPanel.setBounds(45, 50, 695, 595);
		contentPanel.add(tablaPanel);
		tablaPanel.setLayout(null);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablaPanel.add(filtrosPanel);
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		lblLupa.setIcon(lupa);
		filtrosPanel.add(lblLupa);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(27, 11, 271, 26);
		filtrosPanel.add(textField);

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

		JScrollPane tablePane = new JScrollPane();
		tablePane.setBounds(10, 60, 675, 453);
		tablaPanel.add(tablePane);

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
				new String[] { "Codigo", "Fecha", "Direccion", "Estado", "Descripcion" }));

		tablePane.setViewportView(table);

		JPanel adminPanel = new JPanel();
		adminPanel.setBounds(0, 547, 695, 48);
		tablaPanel.add(adminPanel);
		adminPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		adminPanel.setBackground(new Color(162, 196, 201));
		adminPanel.setLayout(null);

		btnAprobar = new JButton("Aprobar");
		btnAprobar.setIcon(tick);
		btnAprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAprobar.setBounds(442, 9, 109, 29);
		adminPanel.add(btnAprobar);

		btnDenegar = new JButton("Denegar");
		btnDenegar.setIcon(cruz);
		btnDenegar.setBounds(567, 9, 109, 29);
		adminPanel.add(btnDenegar);

		textCambio = new JTextField();
		textCambio.setBounds(129, 10, 159, 28);
		adminPanel.add(textCambio);
		textCambio.setColumns(10);

		JButton btnModificar = new JButton("Modificar\r\n");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(317, 9, 109, 29);
		btnModificar.setIcon(lapiz);
		adminPanel.add(btnModificar);

		JLabel lblCambio = new JLabel("Introduce el cambio:");
		lblCambio.setBounds(16, 17, 110, 14);
		adminPanel.add(lblCambio);
	}
}
