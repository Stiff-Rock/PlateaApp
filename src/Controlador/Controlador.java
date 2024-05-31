package Controlador;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import Modelo.Modelo;
import Modelo.Usuario;
import Vistas.Menus;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;
import Vistas._02_Reestablecer;
import Vistas._04_MiPerfil;
import Vistas._09_Publicacion;
import Vistas._10_Publicar;

public class Controlador {
	private Modelo modelo;
	private Usuario user;
	private Vista[] vistas;

	/**
	 * Establece las vistas que serán utilizadas por esta clase.
	 *
	 * @param vistas Un arreglo de objetos Vista que representan las vistas a
	 *               establecer.
	 */
	public void setVista(Vista[] vistas) {
		this.vistas = vistas;
	}

	/**
	 * Establece el modelo de datos que será utilizado por esta clase.
	 *
	 * @param modelo El objeto Modelo que se asignará como modelo de datos.
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Establece el usuario actual que interactúa con la interfaz.
	 *
	 * @param user El objeto Usuario que representa al usuario actual.
	 */
	public void setUsuario(Usuario user) {
		this.user = user;
	}

	/**
	 * Obtiene el captcha generado por el modelo de datos.
	 *
	 * @return Una cadena que representa el captcha generado.
	 */
	public String getCaptcha() {
		return modelo.generateCaptcha();
	}

	/**
	 * Obtiene un modelo de tabla basado en un campo específico del modelo de datos.
	 *
	 * @param campo El campo sobre el cual se desea obtener la tabla.
	 * @return Un objeto TableModel que representa la tabla correspondiente al campo
	 *         especificado.
	 */
	public TableModel getTabla2(String campo) {
		return modelo.getTabla2(campo);
	}

	/**
	 * Obtiene un modelo de ComboBox predeterminado que contiene preguntas.
	 *
	 * @return Un DefaultComboBoxModel que contiene preguntas del modelo de datos.
	 */
	public DefaultComboBoxModel getPreguntas() {
		String campo = "cuestion";
		String tabla = "pregunta";
		return modelo.obtenerComboBox(campo, tabla);
	}

	/**
	 * Obtiene un modelo de ComboBox predeterminado que contiene categorías.
	 *
	 * @return Un DefaultComboBoxModel que contiene categorías del modelo de datos.
	 */
	public DefaultComboBoxModel getCategorias() {
		String campo = "nombre";
		String tabla = "categoria";
		return modelo.obtenerComboBox(campo, tabla);
	}

	/**
	 * Obtiene el usuario actual que está interactuando con la aplicación.
	 *
	 * @return El objeto Usuario que representa al usuario actual.
	 */
	public Usuario getUser() {
		return modelo.getUser();
	}

	/**
	 * Cambia la ventana que se muestra en la interfaz gráfica.
	 *
	 * @param desde El índice de la ventana actual que se ocultará.
	 * @param hasta El índice de la ventana que se mostrará.
	 */
	public void cambiarVentana(int desde, int hasta) {
		((JFrame) vistas[desde]).setVisible(false);
		((JFrame) vistas[hasta]).setVisible(true);
		System.out.println("Movido a: " + hasta);

		if (vistas[hasta] instanceof Menus) {
			((Menus) vistas[hasta]).setIndiceActual(hasta);
		}
	}

	/**
	 * Realiza el proceso de inicio de sesión utilizando las credenciales
	 * proporcionadas. Muestra diferentes vistas dependiendo del resultado del
	 * inicio de sesión.
	 */
	public void login() {
		String usr = ((_00_Login) vistas[0]).getUsr();
		String pwd = ((_00_Login) vistas[0]).getPwd();
		String resultado = modelo.login(usr, pwd);

		switch (resultado) {
		case "Correcto":
			cambiarVentana(0, 3);
			break;
		case "Incorrecto":
			((_00_Login) vistas[0]).mostrarWarning();
			break;
		case "Cerrar":
			System.exit(0);
			break;
		}
	}

	/**
	 * Avanza en el proceso de registro, mostrando la siguiente parte del formulario
	 * si es posible. Muestra mensajes de error si hay algún problema con los datos
	 * proporcionados.
	 */
	public void siguienteSignIn() {
		String resultado = modelo.siguienteSingIn(((_01_Registrar) vistas[1]).getDatosRegistro());
		String mensaje = "";
		if (resultado.equals("Avanza")) {
			System.out.println("Registro avanzado");
			((_01_Registrar) vistas[1]).getRegisterPanel().setVisible(false);
			((_01_Registrar) vistas[1]).getRegisterPanel2().setVisible(true);
		} else {
			switch (resultado) {
			case "Nickname":
				mensaje = "Nickname no disponible";
				break;
			case "Contraseña":
				mensaje = "Las contraseñas no coinciden";
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}
			System.out.println("Error en el registro 1");
			((_01_Registrar) vistas[1]).mostrarWarning1(mensaje);
		}
	}

	/**
	 * Proceso de registro de un nuevo usuario. Realiza la verificación de los datos
	 * proporcionados y muestra mensajes de error si es necesario.
	 */
	public void singIn() {
		String resultado = modelo.singIn(((_01_Registrar) vistas[1]).getDatosRegistro());
		String mensaje = "Ha ocurrido un error inesperado.";

		if (resultado.equals("Correcto")) {
			cambiarVentana(1, 0);
		} else {
			switch (resultado) {
			case "Politica":
				mensaje = "No has aceptado la política de privacidad";
				break;
			case "Mayor":
				mensaje = "Menores de 14 años no están permitidos";
				break;
			case "Captcha":
				mensaje = "Captcha incorrecto";
				break;
			case "Pregunta":
				mensaje = "No has escogido una pregunta";
				break;
			case "Respuesta":
				mensaje = "No has respondido a la pregunta";
				break;
			case "Admin":
				mensaje = "Código de admin incorrecto";
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}
			System.out.println("Error en el registro 2");
			((_01_Registrar) vistas[1]).mostrarWarning2(mensaje);
		}
	}

	/**
	 * Verifica y procesa el cambio de contraseña de un usuario. Muestra un mensaje
	 * de error si no se pueden verificar los datos correctamente.
	 */
	public void verificarCambio() {
		String nick = (((_02_Reestablecer) vistas[2]).getNick());
		String pwd = (((_02_Reestablecer) vistas[2]).getPwd());
		String usrConfirmado = (((_02_Reestablecer) vistas[2]).getPwdVer());
		String respuesta = (((_02_Reestablecer) vistas[2]).getRespuesta());
		String pregunta = modelo.obtenerPreguntaUsuario(nick);
		String preguntaIdex = (((_02_Reestablecer) vistas[2]).getPregunta());

		if (modelo.verificarCambio(nick, modelo.generarCodigo("PRE", preguntaIdex), respuesta, pwd, usrConfirmado)) {
			cambiarVentana(2, 0);
		} else {
			((_02_Reestablecer) vistas[2]).mostrarWarningcambio();
		}
	}

	/**
	 * Establece los datos del usuario actual en la vista de perfil. Actualiza los
	 * campos con la información del usuario.
	 */
	public void setDatosUsuario() {
		((_04_MiPerfil) vistas[4]).setNombre(user.getNombre());
		((_04_MiPerfil) vistas[4]).setApellido(user.getApellido());
		((_04_MiPerfil) vistas[4]).setCp(user.getCp());
		((_04_MiPerfil) vistas[4]).setNickname(user.getNickname());
		((_04_MiPerfil) vistas[4]).setImage(user.getFoto());

		String tipo = "";
		if (user.getEsAdmin().equals("S")) {
			tipo = "Admin";
		} else {
			tipo = "Usuario";
		}
		((_04_MiPerfil) vistas[4]).setTipo(tipo);
	}

	/**
	 * Actualiza los datos del usuario con la información proporcionada en la vista
	 * de perfil. Realiza validaciones sobre los datos proporcionados y muestra
	 * mensajes de éxito o error en la vista.
	 */
	public void actualizarDatosUsuario() {
		String datos[] = new String[4];

		datos[0] = ((_04_MiPerfil) vistas[4]).getNombre();
		datos[1] = ((_04_MiPerfil) vistas[4]).getApellido();
		datos[2] = ((_04_MiPerfil) vistas[4]).getCp();
		datos[3] = deImagenABase64(((_04_MiPerfil) vistas[4]).getImage());

		String resultado = modelo.actualizarDatosUsuario(datos);

		Color color;
		String mensaje = "";
		if (resultado.equals("Correcto")) {
			mensaje = "Los cambios se han aplicado";
			color = new Color(0, 128, 0);
			user.setNombre(datos[0]);
			user.setApellido(datos[1]);
			user.setCp(datos[2]);
			user.setFoto(deBase64AImagen(datos[3]));
			// Actualiza el registro de la BBDD
			modelo.updateUsuario();
			// Actualiza los datos del perfil
			setDatosUsuario();
		} else {
			color = new Color(255, 0, 0);
			if (resultado.equals("Nombre")) {
				mensaje = "El nombre solo puede contener letras";
			}

			if (resultado.equals("Apellido")) {
				mensaje = "El apellido solo puede contener letras";
			}

			if (resultado.equals("Cp")) {
				mensaje = "El codigo postal solo puede contener números";
			}
		}

		((_04_MiPerfil) vistas[4]).mostrarWarning(mensaje, color);
	}

	/**
	 * Convierte una imagen representada por un ImageIcon a su representación en
	 * formato Base64.
	 *
	 * @param imagen El ImageIcon que representa la imagen a convertir.
	 * @return Una cadena que representa la imagen en formato Base64.
	 */
	public String deImagenABase64(ImageIcon imagen) {
		return modelo.deImagenABase64(imagen);
	}

	/**
	 * Convierte una imagen en formato Base64 a un objeto ImageIcon.
	 *
	 * @param fotoBase64 Una cadena que representa la imagen en formato Base64.
	 * @return Un ImageIcon que representa la imagen decodificada.
	 */
	public ImageIcon deBase64AImagen(String fotoBase64) {
		return modelo.deBase64AImagen(fotoBase64);
	}

	/**
	 * Crea una nueva publicación con la información proporcionada. Verifica la
	 * validez de los datos y muestra mensajes de error si es necesario.
	 */
	public void crearPublicacion() {
		String[] datosPublicar = new String[6];

		datosPublicar[0] = deImagenABase64(((_10_Publicar) vistas[10]).getFoto());
		datosPublicar[1] = ((_10_Publicar) vistas[10]).getFecha();
		datosPublicar[2] = ((_10_Publicar) vistas[10]).getCp();
		datosPublicar[3] = ((_10_Publicar) vistas[10]).getCategoria();
		datosPublicar[4] = ((_10_Publicar) vistas[10]).getDireccion();
		datosPublicar[5] = ((_10_Publicar) vistas[10]).getDescripcion();

		String[] resultados = modelo.comprobarInfoPublicacion(datosPublicar);

		String mensaje = "";
		if (resultados[0].equals("Correcto")) {
			modelo.obtenerPublicacion(resultados[1]);
			cambiarVentana(10, 9);
		} else {
			switch (resultados[0]) {
			case "Fecha":
				mensaje = "La fecha no es válida o no coincide con el formato";
				break;
			case "Cp":
				mensaje = "El código postal solo puede tener números";
				break;
			case "Categoria":
				mensaje = "No se ha seleccionado una categoría";
				break;
			case "DescripcionPoco":
				mensaje = "La descripción debe ser de al menos 120 caracteres";
				break;
			case "DescripcionMucho":
				mensaje = "La descripción no debe ser mayor a 300 caracteres";
				break;
			case "Faltan":
				mensaje = "Todos los campos son obligatorios";
				break;
			}
			((_10_Publicar) vistas[10]).mostrarWarning(mensaje);
		}
	}

	/**
	 * Carga los datos de una publicación desde el modelo y los muestra en la vista
	 * de publicación. Actualiza los campos de la vista con los datos obtenidos.
	 */
	public void cargarPublicacion() {
		String[] datosPublicacion = modelo.getDatosPublicacion();
		((_09_Publicacion) vistas[9]).setLblFoto(deBase64AImagen(datosPublicacion[0]));
		((_09_Publicacion) vistas[9]).setTxtFecha(datosPublicacion[1]);
		((_09_Publicacion) vistas[9]).setTxtCp(datosPublicacion[2]);
		((_09_Publicacion) vistas[9]).setTxtCategoria(datosPublicacion[3]);
		((_09_Publicacion) vistas[9]).setTxtDireccion(datosPublicacion[4]);
		((_09_Publicacion) vistas[9]).setTxtDescripcion(datosPublicacion[5]);
	}

	/**
	 * Carga una imagen desde el sistema de archivos y la devuelve como un ImageIcon
	 * escalado a la altura especificada.
	 *
	 * @param alturaImagen La altura deseada para la imagen escalada.
	 * @return Un ImageIcon escalado con la imagen seleccionada, o null si no se
	 *         seleccionó ninguna imagen.
	 */
	public ImageIcon cargarImagen(int alturaImagen) {
		ImageIcon imageIcon = null;
		// Crea un JFileChooser para seleccionar la imagen
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Elige una imagen");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(
				new FileNameExtensionFilter("Archivos de imagen", ImageIO.getReaderFileSuffixes()));

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				// Lee la imagen seleccionada
				BufferedImage imagen = ImageIO.read(selectedFile);
				if (imagen != null) {
					// Calcula la nueva anchura manteniendo la proporción
					double proporcion = (double) alturaImagen / imagen.getHeight();
					int nuevaAnchura = (int) (imagen.getWidth() * proporcion);
					// Escala la imagen al tamaño deseado
					BufferedImage imagenEscalada = new BufferedImage(nuevaAnchura, alturaImagen,
							BufferedImage.TYPE_INT_ARGB);
					imagenEscalada.createGraphics().drawImage(
							imagen.getScaledInstance(nuevaAnchura, alturaImagen, java.awt.Image.SCALE_SMOOTH), 0, 0,
							null);

					// Convierte la imagen escalada a ImageIcon
					imageIcon = new ImageIcon(imagenEscalada);

					// Llama al método setFoto con el ImageIcon
				} else {
					System.out.println("No se pudo leer la imagen seleccionada.");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return imageIcon;
	}

	/**
	 * Obtiene una tabla de datos desde el modelo para la página especificada.
	 *
	 * @param pagina El número de página de la tabla que se desea obtener.
	 * @return Un TableModel que contiene los datos de la tabla correspondiente a la
	 *         página especificada.
	 */
	public TableModel getTabla(int pagina) {
		return modelo.obtenerTabla(pagina);
	}

	/**
	 * Prepara la vista de una publicación utilizando el valor proporcionado como
	 * referencia. Obtiene los datos de la publicación correspondiente desde el
	 * modelo.
	 *
	 * @param valor El valor que se utilizará para identificar la publicación.
	 */
	public void prepararPublicacion(String valor) {
		modelo.obtenerPublicacion(valor);
	}

	/**
	 * Aprueba o deniega una denuncia según el código de denuncia y el tipo
	 * especificados.
	 *
	 * @param codigoDenuncia El código de la denuncia que se desea aprobar o
	 *                       denegar.
	 * @param tipo           El tipo de acción a realizar (aprobación o denegación).
	 */
	public void AprobarDenegar(String codigoDenuncia, int tipo) {
		modelo.AprobarDenegar(codigoDenuncia, tipo);
	}

	/**
	 * Obtiene los datos de la publicación actualmente mostrada en la vista.
	 *
	 * @return Un array de Strings que contiene los datos de la publicación actual.
	 */
	public String[] getDatosPublicacion() {
		return modelo.getDatosPublicacion();
	}

	/**
	 * Agrega la publicación actual a la lista de favoritos del usuario actual.
	 */
	public void meterFavoritos() {
		String nick = user.getNickname();
		String CodDenuncia = ((_09_Publicacion) vistas[9]).getCod();
		modelo.anadirFavorito(nick, CodDenuncia);
		((_09_Publicacion) vistas[9]).setWarning("Añadido a favoritos!");
	}

	/**
	 * Agrega el voto del usuario actual a la publicación actual.
	 */
	public void meterVoatar() {
		String nick = user.getNickname();
		String CodDenuncia = ((_09_Publicacion) vistas[9]).getCod();
		modelo.anadirVotar(nick, CodDenuncia);
		((_09_Publicacion) vistas[9]).setWarning("Votado!");
	}

	/**
	 * Modifica una columna específica de una denuncia según el código de denuncia,
	 * el nombre de la columna y el texto especificados.
	 *
	 * @param codigoDenuncia El código de la denuncia que se desea modificar.
	 * @param nombreColumna  El nombre de la columna que se desea modificar.
	 * @param text           El nuevo valor que se desea asignar a la columna
	 *                       especificada.
	 */
	public void modificar(String codigoDenuncia, String nombreColumna, String text) {
		modelo.modificar(codigoDenuncia, nombreColumna, text);
	}

	/**
	 * Genera un apodo (nickname) basado en el nombre y apellido obtenidos de la
	 * vista y lo establece en la vista.
	 */
	public void generarNick() {
		// Obtiene el nombre de la vista de registro
		String nombre = ((_01_Registrar) vistas[1]).getNombre();
		// Obtiene el apellido de la vista de registro
		String apellido = ((_01_Registrar) vistas[1]).getApellido();
		// Genera el apodo usando el modelo y lo establece en la vista de registro
		((_01_Registrar) vistas[1]).setNickname(modelo.generarNick(nombre, apellido));
	}

	/**
	 * Actualiza el campo de FAVORITO basado en el código de denuncia proporcionado.
	 *
	 * @param codigoDenuncia El código de denuncia cuyo registro de favoritos se va
	 *                       a borrar.
	 */
	public void borrarFavoritos(String codigoDenuncia) {
		modelo.borrarFavorito(codigoDenuncia);
	}

	/**
	 * Actualiza el campo de UPVOTE basado en el código de denuncia proporcionado.
	 *
	 * @param codigoDenuncia El código de denuncia cuyo registro de votados se va a
	 *                       borrar.
	 */
	public void borrarVotados(String codigoDenuncia) {
		modelo.borrarVotar(codigoDenuncia);
	}

}
