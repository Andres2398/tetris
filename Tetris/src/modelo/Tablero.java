package modelo;

/**
 * Clase interfaz que se dedica a la comunicacion con el usuario y a la
 * comunicacion con las clases imagen, Numero y Pieza para ir mostrandoselas al
 * usuario
 * 
 */
public class Tablero {
	private boolean tableroJuego[][];
	private boolean[][] matrizFichaActual;
	private int posicionLogicaX;
	private int posicionLogicaY;

	private final static int POSICIONINICIOX = 4;
	private final static int POSICIONINICIOY = 0;

	/**
	 * Constructor de la clase interfaz en el que iniciamos el scanner la columna
	 * donde se colocara la puntuacion y la puntuacion que empezará a 0
	 */
	public Tablero() {
		tableroJuego = new boolean[14][10];
		posicionLogicaX = POSICIONINICIOX;
		posicionLogicaY = POSICIONINICIOY;
	}

	// geters/setters
	/**
	 * metodo para obtener la matriz booleana de la pieza
	 * 
	 * @return matriz boolean
	 */
	public void setMatrizFichaActual(boolean[][] matrizFicha) {
		this.matrizFichaActual = matrizFicha;
	}
	/**
	 * Metodo para resetear las posiciones al iniciar una nueva pieza
	 */
	public void setPosicionInicial() {
		this.posicionLogicaX = POSICIONINICIOX;
		this.posicionLogicaY = POSICIONINICIOY;
	}
	
	/**
	 * Metodo para obtener la matriz de booleanos con la que se esta jugando
	 * @return la matriz booleana actual de la pieza
	 */

	public boolean[][] getMatrizFichaActual() {
		return matrizFichaActual;
	}
	
	/**
	 * Metodo para colocar la ficha en el tablero 
	 * 
	 */
	public void colocarFicha() {
		int y = posicionLogicaY;
		for (int i = 0; i < matrizFichaActual.length; i++) {
			int x = posicionLogicaX;
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j])
					tableroJuego[y][x] = true;
				x++;
			}
			y++;
		}
	

	}
	/**
	 * metodo para comprobar si se puede mover una ficha a la derecha 
	 * @return true si se puede mover false si no
	 */
	public boolean ComprobarMoverDerecha() {
		int nuevaPosX = posicionLogicaX + 1;

		limpiarFichaActual();
		boolean sePudoMover = true;

		if (nuevaPosX + matrizFichaActual[0].length > tableroJuego[0].length) {
			sePudoMover = false;
		} else {
			for (int i = 0; i < matrizFichaActual.length; i++) {
				for (int j = 0; j < matrizFichaActual[0].length; j++) {
					if (matrizFichaActual[i][j] && sePudoMover) {
						int xTablero = nuevaPosX + j;
						int yTablero = posicionLogicaY + i;
						if (tableroJuego[yTablero][xTablero]) {
							sePudoMover = false;

						}
					}
				}
			}
		}

		if (sePudoMover) {
			posicionLogicaX = nuevaPosX;
		}

		colocarFicha();

		return sePudoMover;
	}
	/**
	 * metodo para comprobar si se puede mover una ficha hacia abajo 
	 * @return true si se puede mover false si no
	 */
	public boolean ComprobarMoverAbajo() {
		int nuevaPosY = posicionLogicaY + 1;

		int altoFicha = matrizFichaActual.length;
		int altoTablero = tableroJuego.length;
		int anchoFicha = matrizFichaActual[0].length;

		// 1. Limpiar la pieza actual de la matriz lógica
		limpiarFichaActual();

		// 2. Comprobar que no se salga por abajo
		if (nuevaPosY + altoFicha > altoTablero) {
			// Volver a colocar la pieza porque no se pudo mover
			colocarFicha();
			return false;
		}

		// 3. Comprobar colisiones
		for (int i = 0; i < altoFicha; i++) {
			for (int j = 0; j < anchoFicha; j++) {
				if (matrizFichaActual[i][j]) {
					int xTablero = posicionLogicaX + j;
					int yTablero = nuevaPosY + i;

					if (tableroJuego[yTablero][xTablero]) {
						// Volver a colocar la pieza porque colisionó
						colocarFicha();
						return false;
					}
				}
			}
		}

		// 4. Mover la ficha y volver a colocarla
		moverAbajo();
		return true;

	}
	/**
	 * Mueve la ficha hacia abajo 
	 */
	private void moverAbajo() {
		limpiarFichaActual();
		posicionLogicaY++;

		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xNuevo = posicionLogicaX + j;
					int yNuevo = posicionLogicaY + i;
					tableroJuego[yNuevo][xNuevo] = true;
				}
			}
		}

	}
	/**
	 * metodo para comprobar si se puede mover una ficha a la izquierda 
	 * @return true si se puede mover false si no
	 */
	public boolean ComprobarMoverIzquierda() {
		int nuevaPosX = posicionLogicaX - 1;

		limpiarFichaActual();

		boolean sePudoMover = true;

		if (nuevaPosX < 0) {
			sePudoMover = false;
		} else {
			for (int i = 0; i < matrizFichaActual.length; i++) {
				for (int j = 0; j < matrizFichaActual[0].length; j++) {
					if (matrizFichaActual[i][j] && sePudoMover) {
						int xTablero = nuevaPosX + j;
						int yTablero = posicionLogicaY + i;
						if (xTablero < 0 || tableroJuego[yTablero][xTablero])
							sePudoMover = false;

					}
				}
			}
		}

		if (sePudoMover)
			posicionLogicaX = nuevaPosX;

		colocarFicha();

		return sePudoMover;
	}
	/**
	 * Metodo que se llama para limpiar la ficha actual de la matriz en este caso la ponemos
	 * todo a false donde estaba la ficha
	 */
	public void limpiarFichaActual() {
		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j])
					tableroJuego[posicionLogicaY + i][posicionLogicaX + j] = false;

			}
		}
	}
	/**
	 * Metodo para saber si hay colisiones debajo de la ficha
	 * @return true si hay, false si no hay
	 */
	public boolean hayColisionDebajo() {

		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xTablero = posicionLogicaX + j;
					int yTablero = posicionLogicaY + i;

					if (yTablero + 1 >= tableroJuego.length) {
						return true;
					}

					if (i == matrizFichaActual.length - 1 || !matrizFichaActual[i + 1][j]) {
						if (tableroJuego[yTablero + 1][xTablero]) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	/**
	 * Metodo para saber si se puede rotar una ficha
	 * @param nuevaMatriz la matriz rotada 
	 * @return true si se puede colocar la matriz rotada o no
	 */
	public boolean puedeRotarFicha(boolean[][] nuevaMatriz) {
		limpiarFichaActual();
		for (int i = 0; i < nuevaMatriz.length; i++) {
			for (int j = 0; j < nuevaMatriz[0].length; j++) {
				if (nuevaMatriz[i][j]) {
					int xTablero = posicionLogicaX + j;
					int yTablero = posicionLogicaY + i;

					
					if (xTablero < 0 || xTablero >= tableroJuego[0].length || yTablero < 0
							|| yTablero >= tableroJuego.length)

						return false;

					if (tableroJuego[yTablero][xTablero])

						return false;

				}
			}
		}
		return true;
	}

	
	/**
	 * Metodo para comprobar si una linea esta llena y asi borrarla
	 * @return un array con las lineas que pueden estar llenas si no es -1
	 */
	public int[] comprobarLinea() {
		int[] quitarLineas = new int[4];
		for (int i = 0; i < quitarLineas.length; i++) {
			quitarLineas[i] = -1;
		}

		int contadorTrue = 0;
		int k = 0;

		for (int i = tableroJuego.length - 1; i >= 0; i--) {

			contadorTrue = 0;
			for (int j = tableroJuego[0].length - 1; j >= 0; j--) {
				if (tableroJuego[i][j])
					contadorTrue++;
			}
			if (contadorTrue == tableroJuego[0].length) {
				quitarLineas[k] = i;
				k++;

			}

		}
		return quitarLineas;
	}
	/**
	 * metodo para eliminar la llena y bajar las de arriba, tambien borra la primera linea siempre
	 * ya que a veces se quedaba en true 
	 * @param fila que se quiere eliminar 
	 */
	public void eliminarLinea(int fila) {
		for (int i = fila; i > 0; i--) {
			for (int j = 0; j < tableroJuego[0].length; j++) {
				tableroJuego[i][j] = tableroJuego[i - 1][j];
			}
		}
		
		for (int j = 0; j < tableroJuego[0].length; j++) {
			tableroJuego[0][j] = false;
		}
	
	}
	/**
	 * Metodo para comprobar si se ha acabado el juego
	 * @return true si si, false si no
	 */
	public boolean fin() {

		if (tableroJuego[2][4] || tableroJuego[2][5])
			return true;
		else
			return false;

	}

}