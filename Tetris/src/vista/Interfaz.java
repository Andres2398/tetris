package vista;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase interfaz que se dedica a la comunicacion con el usuario y a la
 * comunicacion con las clases imagen, Numero y Pieza para ir mostrandoselas al
 * usuario
 * 
 */
public class Interfaz {
	private Scanner sc;
	private Imagen imagenJuego;
	private int puntuacion;
	private final static int PUNTUACIONFILA = 120;
	private int puntuacionColumna;
	private int[][] matrizImagenJuego;

	private Imagen imagenPieza;
	private boolean matrizBoolean[][];

	// Constructor

	/**
	 * Constructor de la clase interfaz en el que iniciamos el scanner la columna
	 * donde se colocara la puntuacion y la puntuacion que empezará a 0
	 */
	public Interfaz() {
		sc = new Scanner(System.in);
		puntuacionColumna = 80;
		puntuacion = 0;

	}

	// geters/setters
	/**
	 * metodo para obtener la matriz booleana de la pieza
	 * 
	 * @return matriz boolean
	 */
	public boolean[][] getMatrizBoolean() {
		return matrizBoolean;
	}

	/**
	 * establecer la matriz boolean de la pieza que se esta jugando
	 * 
	 * @param matrizBoolean matriz boolean de la pieza en juego
	 */
	public void setMatrizBoolean(boolean[][] matrizBoolean) {
		this.matrizBoolean = matrizBoolean;
	}

	/**
	 * Metodo para establecer la puntuacion, tambien se resetea desde donde
	 * pintaremos la puntuacion
	 * 
	 * @param puntuacion que lleva el usuario
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
		puntuacionColumna = 80;
	}

	/**
	 * meetodo para obtener desde donde pintamos la puntuacion
	 * 
	 * @return int que representa la columa desde donde pintar la puntuacion
	 */
	public int getPuntuacionColumna() {
		return puntuacionColumna;
	}

	/**
	 * metodo para establecer desde donde pintamos la puntuacion
	 * 
	 * @param puntuacionColumna int que representa la columa desde donde pintar la
	 *                          puntuacion
	 */

	public void setPuntuacionColumna(int puntuacionColumna) {
		this.puntuacionColumna = puntuacionColumna;
	}

	/**
	 * metodo para obtener la matriz de pixeles del tablero
	 * 
	 * @return matriz de int con todos los pixeles
	 */

	public int[][] getMatrizImagenJuego() {
		return matrizImagenJuego;
	}

	public void setMatrizImagenJuego(int[][] matrizImagenJuego) {
		this.matrizImagenJuego = matrizImagenJuego;
	}

	// metodos

	/**
	 * Muestra un mensaje de bienvenida e instrucciones básicas para jugar. Espera a
	 * que el usuario presione 'c' o 'C' para comenzar el juego.
	 */

	public void saludo() {
		System.out.println("Saludos jugador, bienvenido al Juego de tetris realizado por"
				+ "Andres, por terminal tendrás todas las posibles teclas que el juego permite para mover, \nda igual que sea en mayuscula o minuscula"
				+ "además como adicion cuando una ficha este en el limite y no pueda bajar mas tendras que pulsar la 'S'\n"
				+ "para confirmar que quieres dejar la ficha hay, si no podras seguir moviendola a izquierda y derecha"
				+ "esto se realizo así ya que tienes solo un movimiento \npermitido por cada linea que se baje y eso puede resultar poco, espero que lo disfrutes"
				+ "\npulse c para comenzar el juego");
		boolean buena = false;
		while (!buena) {
			String input = sc.nextLine();
			if (input.equals("c") || input.equals("C")) {
				buena = true;
			} else
				System.out.println("Caracter erroneo pulse c para comenzar");
		}

	}

	/**
	 * Inicia la imagen del tablero principal del juego cargando el archivo
	 * correspondiente y establece la matriz de pixeles de la imagen del juego
	 */
	public void iniciarImagen() {
		imagenJuego = new Imagen("./img/Tableros/tierra.png");
		setMatrizImagenJuego(imagenJuego.getArray2D());

	}

	/**
	 * Pinta la puntuación actual del jugador en la imagen del tablero Convierte la
	 * puntuación a dígitos individuales y utiliza la clase Numero para representar
	 * cada uno visualmente
	 */
	public void pintarPuntuacion() {

		int copia = puntuacion;
		int longitud = 0;

		if (copia == 0) {
			longitud = 1;
		} else {
			while (copia > 0) {
				copia /= 10;
				longitud++;
			}
		}
		int punt = puntuacion;

		for (int i = longitud - 1; i >= 0; i--) {
			int divisor = (int) Math.pow(10, i);
			int digito = punt / divisor;
			pintarNumero(digito);
			punt = punt % divisor;
			setPuntuacionColumna(getPuntuacionColumna() + 40);

		}
		setPuntuacionColumna(80);
	}

	/**
	 * Método auxiliar para pintar un número específico en la puntuación visual del
	 * juego
	 * 
	 * @param digito Número a pintar (entre 0 y 9).
	 */
	private void pintarNumero(int digito) {

		int matriznumero[][] = null;
		switch (digito) {
		case 0:
			matriznumero = Numero.NUMERO0.getImagen().getArray2D();
			break;
		case 1:
			matriznumero = Numero.NUMERO1.getImagen().getArray2D();
			break;
		case 2:
			matriznumero = Numero.NUMERO2.getImagen().getArray2D();
			break;
		case 3:
			matriznumero = Numero.NUMERO3.getImagen().getArray2D();
			break;
		case 4:
			matriznumero = Numero.NUMERO4.getImagen().getArray2D();
			break;
		case 5:
			matriznumero = Numero.NUMERO5.getImagen().getArray2D();
			break;
		case 6:
			matriznumero = Numero.NUMERO6.getImagen().getArray2D();
			break;
		case 7:
			matriznumero = Numero.NUMERO7.getImagen().getArray2D();
			break;
		case 8:
			matriznumero = Numero.NUMERO8.getImagen().getArray2D();
			break;
		case 9:
			matriznumero = Numero.NUMERO9.getImagen().getArray2D();
			break;
		}
		int k = PUNTUACIONFILA;
		int f = puntuacionColumna;

		for (int i = 0; i < matriznumero.length; i++) {
			f = puntuacionColumna;
			for (int j = 0; j < matriznumero[0].length; j++) {
				matrizImagenJuego[k][f] = matriznumero[i][j];
				f++;
			}
			k++;
		}

	}

	/**
	 * Elige aleatoriamente una de las siete piezas posibles del juego Tetris
	 * Establece la imagen y la forma (matriz booleana) correspondiente
	 * 
	 * @return matriz de pixeles de la imagen de la pieza seleccionada
	 */
	public int[][] elegirPiezaRandom() {
		Random r = new Random();
		int piezaRandom = r.nextInt(7);

		switch (piezaRandom) {
		case 0:
			imagenPieza = Pieza.PIEZACUADRADO.getImagen();
			setMatrizBoolean(Pieza.PIEZACUADRADO.getForma());
			break;
		case 1:
			imagenPieza = Pieza.PIEZAI.getImagen();
			setMatrizBoolean(Pieza.PIEZAI.getForma());
			break;
		case 2:
			imagenPieza = Pieza.PIEZAJ.getImagen();
			setMatrizBoolean(Pieza.PIEZAJ.getForma());
			break;
		case 3:
			imagenPieza = Pieza.PIEZAL.getImagen();
			setMatrizBoolean(Pieza.PIEZAL.getForma());
			break;
		case 4:
			imagenPieza = Pieza.PIEZAS.getImagen();
			setMatrizBoolean(Pieza.PIEZAS.getForma());
			break;
		case 5:
			imagenPieza = Pieza.PIEZAT.getImagen();
			setMatrizBoolean(Pieza.PIEZAT.getForma());
			break;
		case 6:
			imagenPieza = Pieza.PIEZAZ.getImagen();
			setMatrizBoolean(Pieza.PIEZAZ.getForma());
			break;

		}

		return imagenPieza.getArray2D();

	}

	/**
	 * Actualiza la imagen del tablero principal con una nueva matriz de pixeles y
	 * la muestra en pantalla
	 * 
	 * @param matrizImagen Nueva matriz de pixeles para actualizar la imagen
	 */
	public void repintarImagen(int[][] matrizImagen) {
		imagenJuego.setArray2D(matrizImagen);
		imagenJuego.ver();

	}

	/**
	 * Solicita al jugador que introduzca un movimiento válido (izquierda, derecha,
	 * rotación, bajar, terminar) Valida que el input ingresado sea una de las
	 * opciones permitidas
	 * 
	 * @return String con la opción seleccionada por el jugador
	 */
	public String pedirMovimientos() {
		boolean buena = false;
		String input = "";
		while (!buena) {
			System.out.println(
					"Selecciona: \nA para mover a la izquierda \nD para mover a la derecha \nX para rotar a la izquierda \nC para rotar a la derecha \nS para mover hacia abajo \n T para terminar el juego");
			input = sc.nextLine();
			if (input.equals("A") || input.equals("a") || input.equals("D") || input.equals("d") || input.equals("X")
					|| input.equals("x") || input.equals("C") || input.equals("c") || input.equals("S")
					|| input.equals("s") || input.equals("T") || input.equals("t"))
				buena = true;
			else
				System.out.println("Caracter incorrecto recuerde: ");
		}
		return input;
	}

	/**
	 * Muestra un mensaje de despedida al jugador y cierra la imagen del tablero
	 */

	public void despedida() {
		System.out.println("Fin de la partida");
		System.out.println("Gracias por jugar a tetris tu puntuacion ha sido de " + puntuacion);
		imagenJuego.cerrar();

	}
}
