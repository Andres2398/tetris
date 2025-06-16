package modelo;

public class Logica {
	private Tablero tablero;
	private Borrados borrar;

	private int posicionXActual = 285; // Posición inicial X en píxeles
	private int posicionYActual = 223; // Posición inicial Y en píxeles
	private int[][] matrizPiezaActual;
	private boolean piezaFija = false;
	private int posicionXAnterior;
	private int posicionYAnterior;

	public Logica() {
		tablero = new Tablero();
		borrar = new Borrados();
	}

	public boolean isPiezaFija() {
		return piezaFija;
	}

	public void resetPiezaFija() {
		piezaFija = false;
	}

	// Guarda la pieza actual para poder moverla y pintarla
	public void setMatrizPiezaActual(int[][] matrizPieza) {
		this.matrizPiezaActual = matrizPieza;
		posicionXActual = 285;
		posicionYActual = 223;
	}

	public int[][] getMatrizPiezaActual() {
		return matrizPiezaActual;
	}

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

	public void colocarFichaTableroInterno(boolean[][] matrizFicha) {
		tablero.colocarFicha();
		tablero.setPosicionInicial();
	}

	public void copiarMatrizImagen(int[][] matrizJuego) {
		int[][] matrizJuegoBase = new int[matrizJuego.length][matrizJuego[0].length];
		for (int i = 0; i < matrizJuego.length; i++) {
			for (int j = 0; j < matrizJuego[0].length; j++) {
				matrizJuegoBase[i][j] = matrizJuego[i][j];
			}
		}
		borrar.setMatrizJuegoBase(matrizJuegoBase);
	}

	public int[][] borrarPuntuacion(int[][] matrizImagen) {
		return borrar.borrarPuntuacion(matrizImagen);
	}

	public int[][] borrarPieza(int[][] matrizJuego) {
		return borrar.borrarPieza(matrizPiezaActual, matrizJuego);
	}

	// Movimientos devuelven true si se pudo mover, false si no (ej. choca con
	// borde)
	public boolean moverIzquierda(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		if (piezaFija)
			return false;
		boolean pudoMover = false;
		if (tablero.ComprobarMoverIzquierda()) {
			int nuevoX = posicionXActual - 52;

			// Guarda posición anterior SIEMPRE
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			// Informa a Borrados
			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			// Borra antes de mover
			borrarPieza(matrizJuego);

			// Intenta mover
			if (nuevoX >= 75) {
				posicionXActual = nuevoX;
				pudoMover = true;
			}
		}
		moverAbajo(matrizJuego);
		return pudoMover;
	}

	public boolean moverDerecha(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		if (piezaFija)
			return false;

		boolean pudoMover = false;
		if (tablero.ComprobarMoverDerecha()) {
			int nuevoX = posicionXActual + 52;
			int anchoPieza = matrizPiezaActual[0].length;

			// Guarda antes SIEMPRE
			posicionXAnterior = posicionXActual;
			posicionYAnterior = posicionYActual;

			borrar.setPosicionFichaBorrarX(posicionXAnterior);
			borrar.setPosicionFichaBorrarY(posicionYAnterior);

			// Borra antes de mover
			borrarPieza(matrizJuego);

			if (nuevoX + anchoPieza <= 595) {
				posicionXActual = nuevoX;
				pudoMover = true;
			}
		}
		moverAbajo(matrizJuego);
		return pudoMover;
	}

	public boolean moverAbajo(int[][] matrizJuego) {
		tablero.limpiarFichaActual();
		
		if (piezaFija)
			return false;

		int nuevoY = posicionYActual + 51;
		int altoPieza = matrizPiezaActual.length;

		// Guardar posición anterior
		posicionXAnterior = posicionXActual;
		posicionYAnterior = posicionYActual;
		borrar.setPosicionFichaBorrarX(posicionXAnterior);
		borrar.setPosicionFichaBorrarY(posicionYAnterior);
		borrarPieza(matrizJuego); // Borrar SIEMPRE

		boolean pudoMover = false;

		if (tablero.ComprobarMoverAbajo() && nuevoY + altoPieza <= 935) {
			posicionYActual = nuevoY;
			pudoMover = true;
		} else {
			piezaFija = true;
		}

		return pudoMover;
	}

	public boolean rotarDerecha(int[][] matrizJuego) {
	    if (piezaFija) 
	    	return false;
	    tablero.limpiarFichaActual();
	    boolean rotada = false;
	   
	    boolean[][] fichaActual = tablero.getMatrizFichaActual();
	    boolean[][] rotadaBoolean = new boolean[fichaActual[0].length][fichaActual.length];
	    for (int i = 0; i < fichaActual.length; i++) {
	        for (int j = 0; j < fichaActual[0].length; j++) {
	            rotadaBoolean[j][fichaActual.length - 1 - i] = fichaActual[i][j];
	        }
	    }

	    // Verifica con lógica del tablero si se puede rotar
	    if (tablero.puedeRotarFicha(rotadaBoolean)) {
	    	System.err.println("es aqui");
	    	posicionXAnterior = posicionXActual;
	    	posicionYAnterior = posicionYActual;

	    	borrar.setPosicionFichaBorrarX(posicionXAnterior);
	    	borrar.setPosicionFichaBorrarY(posicionYAnterior);
	        borrarPieza(matrizJuego);

	        // Rota visual (int[][])
	        int[][] rotadaInt = new int[matrizPiezaActual[0].length][matrizPiezaActual.length];
	        for (int i = 0; i < matrizPiezaActual.length; i++) {
	            for (int j = 0; j < matrizPiezaActual[0].length; j++) {
	                rotadaInt[j][matrizPiezaActual.length - 1 - i] = matrizPiezaActual[i][j];
	            }
	        }

	        // Asigna ambas matrices
	        setMatrizRotada(rotadaInt);
	        tablero.setMatrizFichaActual(rotadaBoolean);

	      
	        rotada=true;
	    }
	    moverAbajo(matrizJuego);
	    return rotada;
	}

	

	public int[][] pintarFicha(int[][] matrizJuego) {
		int y = posicionYActual;
		for (int i = 0; i < matrizPiezaActual.length; i++) {
			int x = posicionXActual;
			for (int j = 0; j < matrizPiezaActual[0].length; j++) {
				if (matrizPiezaActual[i][j] != 0) { // Solo pintar donde la pieza tiene color
					matrizJuego[y][x] = matrizPiezaActual[i][j];
				}
				x++;
			}
			y++;
		}
		return matrizJuego;
	}

	public void iniciarNuevaPieza(int[][] nuevaPieza, boolean[][] nuevaPiezaBoolean) {
		setMatrizPiezaActual(nuevaPieza);
		tablero.setMatrizFichaActual(nuevaPiezaBoolean);
		piezaFija = false;
	}
	public void setMatrizRotada(int [][] rotada){
		this.matrizPiezaActual=rotada;
	}
	public void rotarIzquierda() {

	}
}
