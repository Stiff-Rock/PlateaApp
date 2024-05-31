package Modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import Vistas.Vista;
import Vistas._01_Registrar;

public class Modelo {
	private Properties datosDB;
	private File miFichero;
	private InputStream entrada;
	private final String FILE = "platea.ini";
	private String login;
	private String pwd;
	private String url;

	private Usuario user;
	private Vista[] vistas;

	private Connection conexion;
	private String resultado;
	private int fallos;
	private String[] datosPublicacion;
	private DefaultTableModel miTabla;

	public Modelo() {
		datosDB = new Properties();
		try {
			miFichero = new File(FILE);
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				datosDB.load(entrada);
				login = datosDB.getProperty("login");
				pwd = datosDB.getProperty("pwd");
				url = datosDB.getProperty("url");
			} else {
				System.err.println("Fichero no encontrado");
				System.exit(1);
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("-> Conexion con ORACLE establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC No encontrado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la BD");
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error general de Conexion");
			e.printStackTrace();
		}
	}

	/**
	 * Verifica si las credenciales de usuario son válidas.
	 * 
	 * @param usuario    El nombre de usuario a verificar.
	 * @param contrasena La contraseña asociada al nombre de usuario.
	 * @return Un array de cadenas que contiene el nombre de usuario y la contraseña
	 *         si las credenciales son válidas, o null si no se encontró un usuario
	 *         con las credenciales proporcionadas.
	 */
	public String[] verificarUsuario(String usuario, String contrasena) {
		String[] usuarioEncontrado = new String[2];

		String query = "SELECT NICK, PWD FROM platea.usuario WHERE NICK = ? AND PWD = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, usuario);
			statement.setString(2, contrasena);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				usuarioEncontrado[0] = resultSet.getString("NICK");
				usuarioEncontrado[1] = resultSet.getString("PWD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarioEncontrado;
	}

	/**
	 * Verifica si un nombre de usuario está disponible para su registro.
	 * 
	 * @param nickname El nombre de usuario a verificar.
	 * @return true si el nombre de usuario está disponible, false si ya está en
	 *         uso.
	 */
	public boolean comprobarDisponibilidadNick(String nickname) {
		boolean isNickAvailable = true;

		String query = "SELECT COUNT(*) AS total FROM platea.usuario WHERE NICK = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nickname);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int totalUsuarios = resultSet.getInt("total");
				// Si el total de usuarios con ese nickname es mayor que 0 (1), ya está en uso.
				if (totalUsuarios > 0) {
					isNickAvailable = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isNickAvailable;
	}

	/**
	 * Obtiene el valor de configuración correspondiente a una clave específica.
	 * 
	 * @param condicion La clave de configuración a consultar.
	 * @return El valor asociado a la clave, o una cadena vacía si no se encuentra
	 *         ninguna coincidencia.
	 */
	private String comprobarConfiguracion(String condicion) {
		String query = "SELECT valor FROM platea.configuracion WHERE clave = ?";
		String codigoAdmin = "";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, condicion);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					codigoAdmin = resultSet.getString("VALOR");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(codigoAdmin);
		return codigoAdmin;
	}

	/**
	 * Obtiene un modelo de combo box a partir de los datos de un campo específico
	 * en una tabla.
	 * 
	 * @param campo El nombre del campo que se utilizará para llenar el combo box.
	 * @param tabla El nombre de la tabla que contiene los datos.
	 * @return Un modelo de combo box con los datos del campo especificado.
	 */
	public DefaultComboBoxModel obtenerComboBox(String campo, String tabla) {
		ArrayList<String> listaResultados = new ArrayList<>();
		listaResultados.add("Elige:");
		String query = "SELECT " + campo + " FROM platea." + tabla;

		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				String datos = resultSet.getString(campo);
				listaResultados.add(datos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] objetoResultado = listaResultados.toArray(new String[listaResultados.size()]);
		DefaultComboBoxModel preguntas = new DefaultComboBoxModel(objetoResultado);
		return preguntas;
	}

	/**
	 * Obtiene el nombre de la categoría correspondiente al código proporcionado.
	 * 
	 * @param codigo El código de la categoría.
	 * @return El nombre de la categoría.
	 */
	public String obtenerCategoria(String codigo) {
		String categoria = "";
		String query = "SELECT NOMBRE FROM platea.CATEGORIA WHERE CODIGO = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, codigo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					categoria = rs.getString("NOMBRE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}

	/**
	 * Método que sirve para generar el contnido de una tabla, dependiendo del
	 * índice de página que sea te genera una query u otra.
	 * 
	 * @param pagina indice de las diferentes pestañas.
	 * @return Devuelve la configuracion de la tabla
	 */
	public DefaultTableModel obtenerTabla(int pagina) {
		String query = null;

		if (pagina == 3) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO, DESCRIPCION FROM PLATEA.DENUNCIA WHERE ESTADO != 'Nueva'";
		}
		if (pagina == 5) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO, DESCRIPCION FROM PLATEA.DENUNCIA WHERE USUARIO_NICK = ?";
		}

		if (pagina == 8) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO, DESCRIPCION FROM PLATEA.DENUNCIA WHERE ESTADO = 'Nueva'";
		}

		int numColumnas = getNumColumnas(query, pagina);
		int numFilas = getNumFilas(query, pagina);

		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];

		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			if (pagina == 5) {
				pstmt.setString(1, user.getNickname());
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= numColumnas; i++) {
				cabecera[i - 1] = rsmd.getColumnName(i);
			}
			int filas = 0;
			while (rs.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[filas][col - 1] = rs.getString(col);
				}
				filas++;
			}
			rs.close();
			pstmt.close();

			miTabla = new DefaultTableModel(contenido, cabecera);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miTabla;
	}

	/**
	 * Método que obtiene la cantidad de columnas que tiene una tabla en base a la
	 * query anterior.
	 * 
	 * @param query  Es el String que indica la sentencia de SQL
	 * @param pagina Indice de las diferentes ventanas
	 * @return devuelve un número entero de la cantidad de columnas
	 */
	private int getNumColumnas(String query, int pagina) {
		int numColumnas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			if (query.contains("?") && pagina == 5) {
				pstmt.setString(1, user.getNickname());
			}
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			numColumnas = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numColumnas;
	}

	/**
	 * Método que obtiene la cantidad de filas que tiene una tabla en base a la
	 * query anterior.
	 * 
	 * @param query  Es el String que indica la sentencia de SQL.-
	 * @param pagina Indice de las diferentes ventanas.
	 * @return devuelve un número entero de la cantidad de filas.
	 */
	private int getNumFilas(String query, int pagina) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			if (query.contains("?") && pagina == 5) {
				pstmt.setString(1, user.getNickname());
			}
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	/**
	 * Método para cargar tablas de la base de datos en el JTable de favoritos y
	 * votados
	 * 
	 * @param campo hace referencia a la condición restante en la query base.
	 * @return devuelve la tabla cargada
	 */
	private DefaultTableModel obtenerTabla2(String campo) {
		String query = "SELECT DENUNCIA.CODIGO, DENUNCIA.DIRECCION, DENUNCIA.CP, DENUNCIA.ESTADO, "
				+ "DENUNCIA.FECHA, DENUNCIA.USUARIO_NICK AS USUARIO, DENUNCIA.CATEGORIA_CODIGO AS CATEGORIA FROM PLATEA.DENUNCIA "
				+ "JOIN PLATEA.VOTAR ON DENUNCIA.CODIGO = VOTAR.DENUNCIA_CODIGO WHERE VOTAR.USUARIO_NICK = ? AND VOTAR."
				+ campo + " = 'S'";

		int numColumnas = getNumColumnas(query);
		int numFilas = getNumFilas(query);

		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];

		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, user.getNickname());

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= numColumnas; i++) {
				cabecera[i - 1] = rsmd.getColumnName(i);
			}
			int filas = 0;
			while (rs.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[filas][col - 1] = rs.getString(col);
				}
				filas++;
			}
			rs.close();
			pstmt.close();

			miTabla = new DefaultTableModel(contenido, cabecera);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miTabla;
	}

	/**
	 * Sobrecarga del método getNumColumnas
	 * 
	 * @param query Sentencia sql
	 * @return Devuelve el numero de columnas
	 */
	private int getNumColumnas(String query) {
		int numColumnas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);

			if (query.contains("?")) {
				pstmt.setString(1, user.getNickname());
			}
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			numColumnas = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numColumnas;
	}

	/**
	 * Sobrecarga del método getNumFilas
	 * 
	 * @param query Sentencia sql
	 * @return Devuelve el numero de filas
	 */
	private int getNumFilas(String query) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);

			if (query.contains("?")) {
				pstmt.setString(1, user.getNickname());
			}
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	/**
	 * Método que dependiendo del código de denuncia te carga una información u otra
	 * dependiendo de la públicación que deseas proyectar en la pestaña publicación.
	 * 
	 * @param codigoDen Código de denuncia
	 */
	public void obtenerPublicacion(String codigoDen) {
		String query = "SELECT * FROM PLATEA.DENUNCIA WHERE CODIGO = ?";
		datosPublicacion = new String[7];
		datosPublicacion[6] = codigoDen;
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, codigoDen); // Establecer el valor del parámetro en la consulta SQL
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					if (rs.getBytes("IMAGEN") != null) {
						datosPublicacion[0] = Base64.getEncoder().encodeToString(rs.getBytes("IMAGEN"));
					} else {
						datosPublicacion[0] = null;
					}
					datosPublicacion[1] = rs.getString("FECHA");
					datosPublicacion[2] = rs.getString("CP");
					datosPublicacion[3] = obtenerCategoria(rs.getString("CATEGORIA_CODIGO"));
					datosPublicacion[4] = rs.getString("DIRECCION");
					datosPublicacion[5] = rs.getString("DESCRIPCION");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que mediante toda la información obtenida a través del array de la
	 * condición hace un insert en la base de datos para crear un nuevo usuario.
	 * 
	 * @param datosRegistro Array que contiene toda la información de todos los
	 *                      campos del "Registro".
	 */
	public void crearUsuario(String[] datosRegistro) {
		String query = "INSERT INTO platea.usuario (NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, PREGUNTA_CODIGO, RESPUESTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, datosRegistro[0]);
			statement.setString(2, datosRegistro[1]);
			statement.setString(3, datosRegistro[2]);
			statement.setString(4, datosRegistro[3]);
			statement.setString(5, datosRegistro[4]);
			statement.setString(6, datosRegistro[6]);
			statement.setString(7, datosRegistro[7]);
			statement.setString(8, datosRegistro[8]);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("El usuario ha sido creado correctamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para devolver todos los datos del Usuario sacados de las base de datos
	 * 
	 * @param nick El nombre del usuario que figura como PK en la base de datos
	 * @return devuelve un array con todos los datos
	 */
	public String[] cargarUsuario(String nick) {
		String[] datos = new String[9];

		String sql = "SELECT NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, FOTO, PREGUNTA_CODIGO, RESPUESTA FROM platea.usuario WHERE NICK = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setString(1, nick);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					datos[0] = rs.getString("NICK");
					datos[1] = rs.getString("APELLIDO");
					datos[2] = rs.getString("NOMBRE");
					datos[3] = rs.getString("CP");
					datos[4] = rs.getString("PWD");
					datos[5] = rs.getString("ADMIN");
					datos[6] = Base64.getEncoder().encodeToString(rs.getBytes("FOTO"));
					datos[7] = rs.getString("PREGUNTA_CODIGO");
					datos[8] = rs.getString("RESPUESTA");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return datos;
	}

	/**
	 * Actualiza la información de un usuario en la base de datos.
	 *
	 * Este método toma los datos actuales del usuario y los actualiza en la base de
	 * datos utilizando una consulta SQL UPDATE. La foto del usuario se convierte de
	 * una cadena Base64 a un array de bytes antes de ser almacenada.
	 *
	 * @param user     El objeto usuario que contiene los datos actualizados.
	 * @param conexion La conexión a la base de datos.
	 */
	public void updateUsuario() {
		String sql = "UPDATE platea.usuario SET nombre = ?, apellido = ?, cp = ?, foto = ? WHERE nick = ?";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			// Convertir el valor long a un array de bytes
			byte[] fotoBytes = Base64.getDecoder().decode(deImagenABase64(user.getFoto()));

			pstmt.setString(1, user.getNombre());
			pstmt.setString(2, user.getApellido());
			pstmt.setInt(3, Integer.valueOf(user.getCp()));
			pstmt.setBytes(4, fotoBytes);
			pstmt.setString(5, user.getNickname());

			pstmt.executeUpdate();
			System.out.println("Datos cambiados correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene la pregunta de seguridad asociada a un usuario específico.
	 *
	 * Este método toma el nombre de usuario, ejecuta una consulta SQL para obtener
	 * la pregunta de seguridad asociada y devuelve el código de la pregunta.
	 *
	 * @param nombreUsuario El nombre de usuario del cual se quiere obtener la
	 *                      pregunta de seguridad.
	 * @return El código de la pregunta de seguridad asociada al usuario, o una
	 *         cadena vacía si no se encuentra.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public String obtenerPreguntaUsuario(String nombreUsuario) {
		String pregunta = "";
		String query = "SELECT pregunta.Codigo FROM platea.USUARIO "
				+ "INNER JOIN platea.PREGUNTA ON platea.USUARIO.PREGUNTA_CODIGO = PREGUNTA.CODIGO "
				+ "WHERE USUARIO.NICK = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nombreUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					pregunta = resultSet.getString("Codigo");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pregunta;
	}

	/**
	 * Genera un CAPTCHA aleatorio de 6 caracteres.
	 *
	 * Este método crea un CAPTCHA de 6 caracteres que incluye letras minúsculas,
	 * letras mayúsculas y números. Utiliza un `StringBuilder` para construir el
	 * CAPTCHA y un `Random` para seleccionar los caracteres aleatorios.
	 *
	 * @return Una cadena que representa el CAPTCHA generado aleatoriamente.
	 */
	public String generateCaptcha() {
		String letras = "abcdefghijklmnopqrstuvwxyz";
		String numeros = "1234567890";
		StringBuilder captcha = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int option = random.nextInt(4);
			if (option == 0) {
				captcha.append(numeros.charAt(random.nextInt(numeros.length())));
			} else {
				int mayus = random.nextInt(2);
				if (mayus == 0) {
					captcha.append(letras.charAt(random.nextInt(letras.length())));
				} else {
					captcha.append(String.valueOf(letras.charAt(random.nextInt(letras.length()))).toUpperCase());
				}
			}
		}
		return String.valueOf(captcha);
	}

	/**
	 * Comprueba los datos de registro proporcionados y devuelve el estado del
	 * registro.
	 *
	 * Este método verifica que las contraseñas coincidan, que todos los campos
	 * requeridos estén completos, y que el apodo esté disponible. Devuelve una
	 * cadena que indica si el registro puede continuar o cuál es el problema.
	 *
	 * @param datosRegistro Un array de cadenas que contiene los datos de registro
	 *                      del usuario. Se espera que los índices sean los
	 *                      siguientes: - 0: Nickname - 4: Contraseña - 5:
	 *                      Confirmación de contraseña
	 * @return Una cadena que indica el estado del registro: - "Contraseña" si las
	 *         contraseñas no coinciden. - "Faltan" si faltan campos obligatorios. -
	 *         "Nickname" si el nickname no está disponible. - "Avanza" si todos los
	 *         datos son correctos.
	 */
	public String siguienteSingIn(String[] datosRegistro) {
		String resultado = "";

		if (!datosRegistro[4].equals(datosRegistro[5])) {
			resultado = "Contraseña";
		}

		if (resultado.equals("")) {
			resultado = "Avanza";
		}

		// Comprueba si faltan campos por rellenar
		if ((datosRegistro[0].equals(null) || datosRegistro[0].equals(""))
				|| (datosRegistro[4].equals(null) || datosRegistro[4].equals(""))
				|| (datosRegistro[5].equals(null) || datosRegistro[5].equals(""))) {
			resultado = "Faltan";
		}

		if (!(datosRegistro[0].equals(null) || datosRegistro[0].equals(""))
				&& !comprobarDisponibilidadNick(datosRegistro[0])) {
			resultado = "Nickname";
		}

		return resultado;
	}

	/**
	 * Realiza el proceso de registro de un usuario.
	 * 
	 * @param datosRegistro Un array que contiene los datos del registro, como
	 *                      nombre de usuario, contraseña, etc.
	 * @return Una cadena que indica el resultado del proceso de registro: -
	 *         "Politica" si el usuario no aceptó la política de privacidad. -
	 *         "Mayor" si el usuario no es mayor de edad. - "Admin" si el código
	 *         administrativo proporcionado no es válido. - "Captcha" si la
	 *         validación del captcha falló. - "Pregunta" si no se seleccionó una
	 *         pregunta de seguridad. - "Respuesta" si no se proporcionó una
	 *         respuesta a la pregunta de seguridad. - "Correcto" si el registro se
	 *         completó con éxito.
	 */
	public String singIn(String[] datosRegistro) {
		resultado = "";

		if (datosRegistro[9].equals("N")) {
			resultado = "Politica";
		}

		if (datosRegistro[10].equals("N")) {
			resultado = "Mayor";
		} else {
			if (!datosRegistro[12].equals(comprobarConfiguracion("Codigo Admin"))
					&& !(datosRegistro[12].equals(null) || datosRegistro[12].equals(""))) {
				resultado = "Admin";
			}
		}

		if (!datosRegistro[11].equals(((_01_Registrar) vistas[1]).getCaptcha()))

		{
			resultado = "Captcha";
		}

		if (datosRegistro[7].equals("0") || datosRegistro[7].equals("-1")) {
			resultado = "Pregunta";
		}

		if (datosRegistro[8].equals("") || datosRegistro[8].equals(null)) {
			resultado = "Respuesta";
		}

		if (resultado.equals("")) {
			resultado = "Correcto";

			datosRegistro[7] = generarCodigo("PRE", datosRegistro[7]);

			crearUsuario(datosRegistro);
		}
		return resultado;
	}

	/**
	 * Verifica los datos de registro y registra al usuario si son válidos.
	 * 
	 * @param datosRegistro Los datos de registro del usuario.
	 * @return El resultado del registro ("Correcto" si tiene éxito, de lo
	 *         contrario, el motivo del fallo).
	 */
	public String login(String usr, String pwd) {
		resultado = "";
		String userData[] = verificarUsuario(usr, pwd);
		if ((userData[0] != null && userData[1] != null) && (userData[0].equals(usr) && userData[1].equals(pwd))) {
			user.cargarDatos(cargarUsuario(usr));
			resultado = "Correcto";
			fallos = 0;
		} else {
			fallos++;
			if (fallos == 3) {
				resultado = "Cerrar";
			} else {
				resultado = "Incorrecto";
			}
		}
		return resultado;
	}

	/**
	 * Genera un código utilizando un prefijo y un índice.
	 *
	 * @param prefijo El prefijo del código.
	 * @param indice  El índice utilizado para generar el código.
	 * @return El código generado.
	 */
	public String generarCodigo(String prefijo, String indice) {
		String codigo = "";

		// Generar codigo pregunta a partir del índice dado por parámetro
		for (int i = indice.length(); i < 3; i++) {
			codigo += "0";
		}
		codigo = prefijo + codigo + indice;

		return codigo;
	}

	/**
	 * Verifica y actualiza los datos de un usuario.
	 *
	 * @param datos Un array que contiene los datos a verificar y actualizar. Se
	 *              espera que los índices sean los siguientes: - 0: Nombre - 1:
	 *              Apellido - 2: Código Postal
	 * @return El resultado de la verificación ("Correcto" si los datos son válidos,
	 *         de lo contrario, el motivo del fallo).
	 */
	public String actualizarDatosUsuario(String[] datos) {
		String resultado = "";
		String numeros = "1234567890";

		// Comprueba que el nombre no tenga números
		for (int i = 0; i < numeros.length(); i++) {
			if (datos[0].contains(String.valueOf(numeros.charAt(i)))) {
				resultado = "Nombre";
			}
		}
		// Comprueba que el apellido no tenga números
		for (int i = 0; i < numeros.length(); i++) {
			if (datos[1].contains(String.valueOf(numeros.charAt(i)))) {
				resultado = "Apellido";
			}
		}
		// Comprueba que el codigo postal solo tenga números
		for (int i = 0; i < datos[2].length(); i++) {
			if (!numeros.contains(String.valueOf(datos[2].charAt(i)))) {
				resultado = "Cp";
			}
		}

		if (resultado == "") {
			resultado = "Correcto";
		}

		return resultado;
	}

	/**
	 * Crea una nueva publicación en la base de datos utilizando los datos
	 * proporcionados.
	 *
	 * @param datosPublicacion Los datos de la publicación a crear. Se espera que
	 *                         los índices sean los siguientes: - 0: Fecha de la
	 *                         publicación en formato "dd/MM/yyyy". - 1: Código de
	 *                         la categoría. - 2: Código Postal. - 3: Dirección. -
	 *                         4: Descripción de la publicación.
	 * @return El código de la nueva publicación creada.
	 */
	private String crearPublicacion(String[] datosPublicacion) {
		String sqlCount = "SELECT COUNT(*) FROM platea.denuncia";
		String sqlInsert = "INSERT INTO platea.denuncia (CODIGO, IMAGEN, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String codigoDenuncia = "";
		try {
			// Paso 1: Ejecutar la consulta para obtener la cantidad de denuncias
			PreparedStatement countStmt = conexion.prepareStatement(sqlCount);
			ResultSet resultSet = countStmt.executeQuery();
			resultSet.next(); // Mover al primer resultado

			String numeroDenuncias = String.valueOf(resultSet.getInt(1) + 1); // Sumar 1 para crear el nuevo código
			countStmt.close();

			// Paso 2: Generar el nuevo código
			codigoDenuncia = generarCodigo("DEN", numeroDenuncias);
			String codigoCategoria = generarCodigo("CAT", datosPublicacion[3]);

			// Convertir el String de fecha al formato "yyyy-MM-dd"
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaDate = null;
			try {
				fechaDate = dateFormat.parse(datosPublicacion[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Paso 3: Ejecutar la inserción con el nuevo código
			try (PreparedStatement pstmt = conexion.prepareStatement(sqlInsert)) {
				pstmt.setString(1, codigoDenuncia);
				pstmt.setBytes(2, Base64.getDecoder().decode(datosPublicacion[0]));
				pstmt.setString(3, datosPublicacion[4]);
				pstmt.setInt(4, Integer.valueOf(datosPublicacion[2]));
				pstmt.setString(5, "Nueva");
				pstmt.setDate(6, new java.sql.Date(fechaDate.getTime())); // Convertir la fecha a java.sql.Date
				pstmt.setString(7, user.getNickname());
				pstmt.setString(8, codigoCategoria);
				pstmt.setString(9, datosPublicacion[5]);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al crear la denuncia: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener la cantidad de denuncias: " + e.getMessage());
		}
		System.out.println("Denuncia '" + codigoDenuncia + "' creada correctamente");
		return codigoDenuncia;
	}

	/**
	 * Obtiene una imagen de la base de datos y la devuelve como un ImageIcon
	 * escalado a una altura específica.
	 *
	 * @param tabla        La tabla de la base de datos donde se encuentra la
	 *                     imagen.
	 * @param campoFoto    El nombre del campo que contiene la imagen en la tabla.
	 * @param campoId      El nombre del campo que representa el ID de la fila en la
	 *                     tabla.
	 * @param id           El ID de la fila que contiene la imagen.
	 * @param alturaImagen La altura deseada para la imagen escalada.
	 * @return Un ImageIcon de la imagen obtenida, escalada a la altura
	 *         especificada.
	 */
	public ImageIcon obtenerImagen(String tabla, String campoFoto, String campoId, int id, int alturaImagen) {
		String query = "SELECT " + campoFoto + " FROM platea." + tabla + " WHERE " + campoId + " = ?";
		ImageIcon imageIcon = null;

		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Blob blob = resultSet.getBlob(campoFoto);
				InputStream inputStream = blob.getBinaryStream();

				// Convertir el BLOB a BufferedImage
				BufferedImage originalImage = ImageIO.read(inputStream);

				// Calcular el nuevo tamaño de la imagen
				int anchuraOriginal = originalImage.getWidth();
				int alturaOriginal = originalImage.getHeight();
				int anchuraEscalada = (alturaImagen * anchuraOriginal) / alturaOriginal;

				// Redimensionar la imagen
				Image scaledImage = originalImage.getScaledInstance(anchuraEscalada, alturaImagen, Image.SCALE_SMOOTH);

				// Convertir la imagen escalada a BufferedImage
				BufferedImage bufferedScaledImage = new BufferedImage(anchuraEscalada, alturaImagen,
						BufferedImage.TYPE_INT_ARGB);
				bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);

				// Convertir BufferedImage a byte[]
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bufferedScaledImage, "png", outputStream);
				byte[] imageBytes = outputStream.toByteArray();

				// Crear ImageIcon
				imageIcon = new ImageIcon(imageBytes);

				inputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageIcon;
	}

	/**
	 * Comprueba la validez de la información proporcionada para una publicación.
	 * 
	 * @param datosPublicar Un array que contiene los datos de la publicación a
	 *                      comprobar. Se espera que los índices sean los
	 *                      siguientes: - 0: Fecha de la publicación en formato
	 *                      "dd/MM/yyyy". - 1: Código Postal. - 2: Código de la
	 *                      categoría. - 3: Descripción de la publicación.
	 * @return Un array de String con dos elementos: - El primer elemento indica el
	 *         resultado de la comprobación ("Correcto" si es válido, de lo
	 *         contrario, el motivo del fallo). - El segundo elemento contiene el
	 *         código de la nueva publicación, si se creó correctamente.
	 */
	public String[] comprobarInfoPublicacion(String[] datosPublicar) {
		String[] resultados = new String[2];
		resultados[0] = "";
		resultados[1] = "";

		if (datosPublicar[1] != null && !datosPublicar[1].matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}")) {
			resultados[0] = "Fecha";
		}

		String numeros = "1234567890";
		for (int i = 0; i < datosPublicar[2].length(); i++) {
			if (!numeros.contains(String.valueOf(datosPublicar[2].charAt(i)))) {
				resultados[0] = "Cp";
			}
		}

		if (datosPublicar[3].equals("0") || datosPublicar[3].equals("-1")) {
			resultados[0] = "Categoria";
		}

		if (datosPublicar[5].length() < 50) {
			resultados[0] = "DescripcionPoco";
		}

		if (datosPublicar[5].length() > 300) {
			resultados[0] = "DescripcionMucho";
		}

		for (int i = 0; i < datosPublicar.length; i++) {
			if (datosPublicar[i].equals(null) || datosPublicar[i].equals("")) {
				resultados[0] = "Faltan"; // Todos los campos son obligatorios
			}
		}

		if (resultados[0].equals("")) {
			resultados[0] = "Correcto";
			resultados[1] = crearPublicacion(datosPublicar);
		}
		return resultados;
	}

	/**
	 * Convierte una imagen representada como ImageIcon a una cadena de texto en
	 * formato Base64.
	 * 
	 * @param imagen La imagen a convertir.
	 * @return Una cadena de texto en formato Base64 que representa la imagen, o una
	 *         cadena vacía si la imagen es nula.
	 */
	public String deImagenABase64(ImageIcon imagen) {
		String fotoBase64 = "";
		if (imagen != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(imagen);
				fotoBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fotoBase64;
	}

	/**
	 * Convierte una cadena de texto en formato Base64 a una imagen representada
	 * como ImageIcon.
	 * 
	 * @param fotoBase64 La cadena en formato Base64 que representa la imagen.
	 * @return Una instancia de ImageIcon que representa la imagen, o null si la
	 *         cadena Base64 es nula o vacía.
	 */
	public ImageIcon deBase64AImagen(String fotoBase64) {
		ImageIcon imagen = null;
		if (fotoBase64 != null && !fotoBase64.isEmpty()) {
			byte[] bytes = Base64.getDecoder().decode(fotoBase64);
			try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
					ObjectInputStream ois = new ObjectInputStream(bais)) {
				imagen = (ImageIcon) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return imagen;
	}

	/**
	 * Verifica y realiza el cambio de contraseña para un usuario dado si se cumplen
	 * las condiciones.
	 * 
	 * @param nick       El nombre de usuario del usuario.
	 * @param pregunta   La pregunta de seguridad del usuario.
	 * @param respuesta  La respuesta a la pregunta de seguridad del usuario.
	 * @param nuevaPwd   La nueva contraseña que se desea establecer.
	 * @param confirmPwd La confirmación de la nueva contraseña.
	 * @return true si se verifican las condiciones y se realiza el cambio de
	 *         contraseña con éxito, false en caso contrario.
	 */
	public boolean verificarCambio(String nick, String pregunta, String respuesta, String nuevaPwd, String confirmPwd) {
		String selectQuery = "SELECT 1 FROM platea.usuario WHERE NICK = ? AND PREGUNTA_CODIGO = ? AND RESPUESTA = ?";
		String updateQuery = "UPDATE platea.usuario SET PWD = ? WHERE NICK = ?";

		try (PreparedStatement selectStatement = conexion.prepareStatement(selectQuery)) {
			selectStatement.setString(1, nick);
			selectStatement.setString(2, pregunta);
			selectStatement.setString(3, respuesta);
			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				if (nuevaPwd.equals(confirmPwd)) {
					try (PreparedStatement updateStatement = conexion.prepareStatement(updateQuery)) {
						updateStatement.setString(1, nuevaPwd);
						updateStatement.setString(2, nick);
						updateStatement.executeUpdate();
						return true;
					}
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Aprueba o deniega una denuncia según el tipo especificado.
	 * 
	 * @param codigoDenuncia El código de la denuncia a aprobar o denegar.
	 * @param tipo           El tipo de acción a realizar: - 1 para aprobar la
	 *                       denuncia. - 2 para denegar la denuncia y borrarla.
	 */
	public void AprobarDenegar(String codigoDenuncia, int tipo) {
		if (tipo == 1) {
			String sql = "{CALL PLATEA.APROBAR(?)}";
			try {
				CallableStatement cst = conexion.prepareCall(sql);

				cst.setString(1, codigoDenuncia.trim());
				cst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (tipo == 2) {
			String sql = "{CALL PLATEA.BORRAR_DENUNCIA(?)}";
			try {
				CallableStatement cst = conexion.prepareCall(sql);

				cst.setString(1, codigoDenuncia.trim());
				cst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Llama a un procedimiento almacenado en la base de datos para registrar un
	 * voto. El procedimiento se llama PLATEA.VOTACION y se le pasan cuatro
	 * parámetros: el nick del usuario, el código de la denuncia, un indicador de
	 * voto (S) y un indicador de favorito (N).
	 *
	 * @param nick        El nick del usuario que vota.
	 * @param codDenuncia El código de la denuncia que se está votando.
	 */
	public void anadirVotar(String nick, String codDenuncia) {
		String sql = "{CALL PLATEA.VOTACION(?, ?, ?, ?)}";
		try {
			CallableStatement cst = conexion.prepareCall(sql);

			cst.setString(1, nick.trim());
			cst.setString(2, codDenuncia.trim());
			cst.setString(3, "S".trim());
			cst.setString(4, "N".trim());
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Llama a un procedimiento almacenado en la base de datos para marcar una
	 * denuncia como favorita. El procedimiento se llama PLATEA.Favoritito y se le
	 * pasan cuatro parámetros: el nick del usuario, el código de la denuncia, un
	 * indicador de voto (N) y un indicador de favorito (S).
	 *
	 * @param nick        El nick del usuario que marca la denuncia como favorita.
	 * @param codDenuncia El código de la denuncia que se está marcando como
	 *                    favorita.
	 */
	public void anadirFavorito(String nick, String codDenuncia) {
		String sql = "{CALL PLATEA.Favoritito(?, ?, ?, ?)}";
		try {
			CallableStatement cst = conexion.prepareCall(sql);

			cst.setString(1, nick.trim());
			cst.setString(2, codDenuncia.trim());
			cst.setString(3, "N".trim());
			cst.setString(4, "S".trim());
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica los datos de una denuncia especificada.
	 * 
	 * @param codigoDenuncia El código de la denuncia a modificar.
	 * @param nombreColumna  El nombre de la columna que se desea modificar.
	 * @param text           El nuevo valor que se desea establecer en la columna
	 *                       especificada.
	 */
	public void modificar(String codigoDenuncia, String nombreColumna, String text) {
		String sql = "{CALL PLATEA.CAMBIAR_DATOS(?, ?, ?)}";
		try {
			CallableStatement cst = conexion.prepareCall(sql);

			cst.setString(1, codigoDenuncia.trim());
			cst.setString(2, nombreColumna.trim());
			cst.setString(3, text.trim());
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera un apodo (nick) a partir del nombre y el apellido proporcionados. El
	 * apodo se crea tomando la mitad del nombre y la mitad del apellido, y luego
	 * concatenándolos. Si el apodo resultante tiene más de 20 caracteres, se trunca
	 * a 20 caracteres.
	 *
	 * @param nombre   El nombre del usuario.
	 * @param apellido El apellido del usuario.
	 * @return El apodo generado, limitado a un máximo de 20 caracteres.
	 */
	public String generarNick(String nombre, String apellido) {
		String nick = "";
		// Verificar si el nombre y el apellido no son nulos o vacíos
		if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
			// Tomar la mitad del nombre y del apellido
			String mitadNombre = nombre.substring(0, nombre.length() / 2);
			String mitadApellido = apellido.substring(0, apellido.length() / 2);

			// Concatenar las letras para formar el apodo
			nick = mitadNombre + mitadApellido;

			// Limitar el apodo a un máximo de 20 caracteres
			if (nick.length() > 20) {
				nick = nick.substring(0, 20);
			}
		}
		return nick;
	}

	/**
	 * Marca una denuncia como no favorita en la base de datos. Actualiza el campo
	 * FAVORITO a 'N' en la tabla platea.votar para la denuncia especificada por su
	 * código.
	 *
	 * @param codigoDenuncia El código de la denuncia a actualizar.
	 */
	public void borrarFavorito(String codigoDenuncia) {
		try {
			String sql = "UPDATE platea.votar SET FAVORITO = 'N' WHERE DENUNCIA_CODIGO = ?";
			try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
				pstmt.setString(1, codigoDenuncia);
				int filasActualizadas = pstmt.executeUpdate();
				System.out.println("Filas actualizadas: " + filasActualizadas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Votar '" + codigoDenuncia + "' borrado de favoritos");
	}

	/**
	 * Marca una denuncia como no votada en la base de datos. Actualiza el campo
	 * UPVOTE a 'N' en la tabla platea.votar para la denuncia especificada por su
	 * código.
	 *
	 * @param codigoDenuncia El código de la denuncia a actualizar.
	 */
	public void borrarVotar(String codigoDenuncia) {
		try {
			String sql = "UPDATE platea.votar SET UPVOTE = 'N' WHERE DENUNCIA_CODIGO = ?";
			try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
				pstmt.setString(1, codigoDenuncia);
				int filasActualizadas = pstmt.executeUpdate();
				System.out.println("Filas actualizadas: " + filasActualizadas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Votar '" + codigoDenuncia + "' borrado de votados");
	}

	// Métodos getter y setter del modelo
	public void setVistas(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public String getResultado() {
		return resultado;
	}

	public Usuario getUser() {
		return user;
	}

	public DefaultTableModel getTabla2(String campo) {
		return obtenerTabla2(campo);
	}

	public String[] getDatosPublicacion() {
		return datosPublicacion;
	}

}
