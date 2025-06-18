package modelo;

public class Logica {
	private Tablero tablero;
	private Borrados borrar;
	private int posicionXActual = 285;
	private int posicionYActual = 223;
	private int[][] matrizPiezaActual;
	private boolean piezaFija = false;
	private int posicionXAnterior;
	private int posicionYAnterior;
	private int puntuacion;

	// constructor
	/**
	 * Constructor que inicializa el tablero y el sistema de borrado
	 */
		public Logica() {
			tablero = new Tablero();
			borrar = new Borrados();
			puntuacion=0;
		}

	// getters/setters
	/**
	 * Metodo para obtener la puntuacion
	 * 
	 * @return Puntuación actual del jugador
	 */

	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Establece la puntuación del jugador.
	 * 
	 * @param puntuacion Nueva puntuación.
	 */

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * 
	 * @return true si la pieza está fija, false si aún puede moverse
	 */
	public boolean isPiezaFija() {
		return piezaFija;
	}

	/**
	 * Reinicia el estado de la pieza como no fija.
	 */

	public void resetPiezaFija() {
		piezaFija = false;
	}

	/**
	 * Guarda la matriz de la pieza actual y reinicia su posición
	 * 
	 * @param matrizPieza Matriz de la nueva pieza.
	 */

	public void setMatrizPiezaActual(int[][] matrizPieza) {
		this.matrizPiezaActual = matrizPieza;
		posicionXActual = 285;
		posicionYActual = 223;
	}

	/**
	 * @return Matriz de la pieza actual.
	 */

	public int[][] getMatrizPiezaActual() {
		return matrizPiezaActual;
	}

	/**
	 * Establece la matriz de la pieza ya rotada.
	 * 
	 * @param rotada Matriz rotada.
	 */
	public void setMatrizRotada(int[][] rotada) {
		this.matrizPiezaActual = rotada;
	}

	// metodos
	/**
	 * Coloca visualmente la pieza en la matriz del juego.
	 * 
	 * @param matrizImagenJuego Matriz donde se pinta.
	 * @param matrizPieza       Matriz de la pieza a colocar.
	 * @return Matriz del juego actualizada con la pieza.
	 */
	public int[][] coloCarPieza(int[][] matrizImagenJuego, int[][] matrizPieza) {

		matrizPiezaActual = matrizPieza;
		int y = posicionYActual;
		for (int i = 0; i < matrizPieza.length; i++) {
			int x = posicionXActual;
			for (int j = 0; j < matrizPieza[0].length; j++) {
				matrizImagenJuego[y][x] = matrizPieza[i][j];
				x++;
			}
			y++;
		}

		borrar.setPosicionFichaBorrarX(posicionXActual);
		borrar.setPosicionFichaBorrarY(posicionYActual);
		return matrizImagenJuego;

	}

	/**
	 * Coloca la ficha en la matriz lógica del tablero.
	 * 
	 * @param matrizFicha Ficha en forma booleana.
	 */
	public void colocarFichaTableroInterno(boolean[][] matrizFicha) {
		tablero.colocarFicha();
		tablero.setPosicionInicial();
	}

	/**
	 * Copia la matriz actual del juego como base para comparaciones futuras.
	 * 
	 * @param matrizJuego Matriz a copiar.
	 */
	public void copiarMatrizImagen(int[][] matrizJuego) {
		int[][] matrizJuegoBase = new int[matrizJuego.length][matrizJuego[0].length];
		for (int i = 0; i < matrizJuego.length; i++) {
			for (int j = 0; j < matrizJuego[0].length; j++) {
				matrizJuegoBase[i][j] = matrizJuego[i][j];
			}
		}
		borrar.setMatrizJuegoBase(matrizJuegoBase);
	}

	/**
	 * Borra las líneas completadas en la matriz del juego.
	 * 
	 * @param matrizImagen Matriz del juego.
	 * @return Matriz modificada con las líneas eliminadas.
	 */
	public int[][] borrarPuntuacion(int[][] matrizImagen) {
		return borrar.borrarPuntuacion(matrizImagen);
	}

	/**
	 * Borra la ficha actual de la matriz del juego.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return Matriz con la ficha eliminada.
	 */
	public int[][] borrarPieza(int[][] matrizJuego) {
		return borrar.borrarPieza(matrizPiezaActual, matrizJuego);
	}

	/**
	 * Intenta mover la pieza a la izquierda.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return true si pudo moverse, false si no.
	 */
	public boolean moverIzquierda(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		if (piezaFija)
			return false;
		boolean puedoMover = false;
		if (tablero.ComprobarMoverIzquierda()) {
			int nuevoX = posicionXActual - 52;
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			borrarPieza(matrizJuego);

			if (nuevoX >= 75) {
				posicionXActual = nuevoX;
				puedoMover = true;
			}
		}
		moverAbajo(matrizJuego);
		return puedoMover;
	}

	/**
	 * Intenta mover la pieza a la derecha.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return true si pudo moverse, false si no.
	 */
	public boolean moverDerecha(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		if (piezaFija)
			return false;
		boolean puedoMover = false;
		if (tablero.ComprobarMoverDerecha()) {
			int nuevoX = posicionXActual + 52;
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			borrarPieza(matrizJuego);

			if (nuevoX + matrizPiezaActual[0].length <= 595) {
				posicionXActual = nuevoX;
				puedoMover = true;
			}
		}
		moverAbajo(matrizJuego);
		return puedoMover;

	}

	/**
	 * Intenta mover la pieza hacia abajo.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return true si pudo moverse, false si no.
	 */
	public boolean moverAbajo(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		if (piezaFija)
			return false;
		boolean puedoMover = false;

		int nuevoY = posicionYActual + 51;
		posicionXAnterior = posicionXActual;
		posicionYAnterior = posicionYActual;

		borrar.setPosicionFichaBorrarX(posicionXAnterior);
		borrar.setPosicionFichaBorrarY(posicionYAnterior);

		borrarPieza(matrizJuego);

		if (tablero.ComprobarMoverAbajo() && nuevoY + matrizPiezaActual.length <= 940) {
			posicionYActual = nuevoY;
			puedoMover = true;
		} else
			piezaFija = false;
		pintarFicha(matrizJuego);
		return puedoMover;
	}

	/**
	 * Rota la pieza a la derecha si es posible.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return true si se rotó correctamente.
	 */
	public boolean rotarDerecha(int[][] matrizJuego) {
		if (piezaFija)
			return false;
		tablero.limpiarFichaActual();
		boolean rotada = false;

		boolean[][] fichaActual = tablero.getMatrizFichaActual();
		boolean[][] rotadaBoolean = new boolean[fichaActual[0].length][fichaActual.length];
		int k = fichaActual.length - 1;
		for (int i = 0; i < fichaActual.length; i++) {
			for (int j = 0; j < fichaActual[0].length; j++) {
				rotadaBoolean[j][k] = fichaActual[i][j];
			}
			k--;
		}
		if (tablero.puedeRotarFicha(rotadaBoolean)) {
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			borrarPieza(matrizJuego);
			int[][] rotadaInt = new int[matrizPiezaActual[0].length][matrizPiezaActual.length];
			k = matrizPiezaActual.length - 1;
			for (int i = 0; i < matrizPiezaActual.length; i++) {
				for (int j = 0; j < matrizPiezaActual[0].length; j++) {
					rotadaInt[j][k] = matrizPiezaActual[i][j];
				}
				k--;
			}
			setMatrizRotada(rotadaInt);
			tablero.setMatrizFichaActual(rotadaBoolean);
			rotada = true;
		}
		moverAbajo(matrizJuego);
		return rotada;
	}

	/**
	 * Dibuja la ficha en la matriz del juego.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return Matriz actualizada con la ficha pintada.
	 */

	public int[][] pintarFicha(int[][] matrizJuego) {
		int y = posicionYActual;
		for (int i = 0; i < matrizPiezaActual.length; i++) {
			int x = posicionXActual;
			for (int j = 0; j < matrizPiezaActual[0].length; j++) {
				if (matrizPiezaActual[i][j] != 0)
					matrizJuego[y][x] = matrizPiezaActual[i][j];
				x++;
			}
			y++;
		}
		return matrizJuego;

	}

	/**
	 * Inicializa una nueva ficha para jugar.
	 * 
	 * @param nuevaPieza        Matriz de la nueva pieza (visual).
	 * @param nuevaPiezaBoolean Matriz booleana (lógica).
	 */
	public void iniciarNuevaPieza(int[][] nuevaPieza, boolean[][] nuevaPiezaBoolean) {
		setMatrizPiezaActual(nuevaPieza);
		tablero.setMatrizFichaActual(nuevaPiezaBoolean);
		piezaFija = false;
	}

	public boolean rotarIzquierda(int[][] matrizJuego) {
		if (piezaFija)
			return false;
		tablero.limpiarFichaActual();
		boolean rotada = false;

		boolean[][] fichaActual = tablero.getMatrizFichaActual();
		boolean[][] rotadaBoolean = new boolean[fichaActual[0].length][fichaActual.length];
		int k = fichaActual[0].length - 1;
		for (int i = 0; i < fichaActual.length; i++) {
			for (int j = 0; j < fichaActual[0].length; j++) {
				rotadaBoolean[k - j][i] = fichaActual[i][j];
			}

		}
		if (tablero.puedeRotarFicha(rotadaBoolean)) {
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			borrarPieza(matrizJuego);

			int[][] rotadaInt = new int[matrizPiezaActual[0].length][matrizPiezaActual.length];
			k = matrizPiezaActual[0].length - 1;
			for (int i = 0; i < matrizPiezaActual.length; i++) {
				for (int j = 0; j < matrizPiezaActual[0].length; j++) {
					rotadaInt[k - j][i] = matrizPiezaActual[i][j];
				}

			}
			setMatrizRotada(rotadaInt);
			tablero.setMatrizFichaActual(rotadaBoolean);
			rotada = true;
		}
		moverAbajo(matrizJuego);
		return rotada;
	}

	/**
	 * Comprueba si se ha completado alguna línea y la elimina.
	 * 
	 * @param matrizJuego Matriz del juego.
	 * @return Matriz modificada tras borrar líneas completas.
	 */
	public int[][] comprobarLinea(int[][] matrizJuego) {
		int[] quitarLineas = tablero.comprobarLinea();
		boolean cambio = true;
		while (cambio) {
			cambio = false;
			for (int i = 0; i < quitarLineas.length - 1; i++) {
				if (quitarLineas[i] < quitarLineas[i + 1]) {
					int aux = quitarLineas[i];
					quitarLineas[i] = quitarLineas[i + 1];
					quitarLineas[i + 1] = aux;
					cambio=true;
				}
			}
		}
		for (int i = 0; i < quitarLineas.length; i++) {
			if(quitarLineas[i]!=-1) {
				tablero.eliminarLinea(quitarLineas[i]);
				borrar.eliminarLinea(quitarLineas[i], matrizJuego);
				puntuacion+=10;
			}
		}
		return matrizJuego;
	}
	/**
     * Comprueba si el juego ha llegado a su fin.
     * @return true si el juego ha terminado.
     */
	public boolean comprobarFin() {
		
		return tablero.fin();
	}
}

