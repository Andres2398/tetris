package modelo;

public class Tablero {
	private boolean tableroJuego[][];
	private boolean[][] matrizFichaActual;
	private int posicionLogicaX;
	private int posicionLogicaY;

	private final static int POSICIONINICIOX = 4;
	private final static int POSICIONINICIOY = 0;

	public Tablero() {
		tableroJuego = new boolean[14][10];
		posicionLogicaX = POSICIONINICIOX;
		posicionLogicaY = POSICIONINICIOY;
	}

	public void setMatrizFichaActual(boolean[][] matrizFicha) {
		this.matrizFichaActual = matrizFicha;
	}

	public void setPosicionInicial() {
		this.posicionLogicaX = POSICIONINICIOX;
		this.posicionLogicaY = POSICIONINICIOY;
	}

	public boolean colocarFicha() {
		int y = posicionLogicaY;
		for (int i = 0; i < matrizFichaActual.length; i++) {
			int x = posicionLogicaX;
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					tableroJuego[y][x] = true;
				}

				x++;
			}
			y++;
		}

		return true;
	}

	public boolean ComprobarMoverDerecha() {
		int nuevaPosX = posicionLogicaX + 1;
		int anchoFicha = matrizFichaActual[0].length;
		int anchoTablero = tableroJuego[0].length;

		limpiarFichaActual(); // Limpia temporalmente

		boolean sePudoMover = true;

		if (nuevaPosX + anchoFicha > anchoTablero) {
			sePudoMover = false;
		} else {
			for (int i = 0; i < matrizFichaActual.length; i++) {
				for (int j = 0; j < matrizFichaActual[0].length; j++) {
					if (matrizFichaActual[i][j]) {
						int xTablero = nuevaPosX + j;
						int yTablero = posicionLogicaY + i;
						if (tableroJuego[yTablero][xTablero]) {
							sePudoMover = false;
							break;
						}
					}
				}
			}
		}

		if (sePudoMover) {
			posicionLogicaX = nuevaPosX;
		}

		colocarFicha(); // Siempre se recoloca

		return sePudoMover;
	}

	private void moverDerecha() {
		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xActual = posicionLogicaX + j;
					int yActual = posicionLogicaY + i;
					tableroJuego[yActual][xActual] = false;
				}
			}
		}

		posicionLogicaX++;

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

	private void pintar() {
		for (int i = 0; i < tableroJuego.length; i++) {
			System.out.println();
			for (int j = 0; j < tableroJuego[0].length; j++) {
				System.out.print("{" + tableroJuego[i][j] + "}");
			}
		}
		System.out.println();
		System.out.println();
	}

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

	private void moverAbajo() {
		limpiarFichaActual();
		posicionLogicaY++; // Baja una fila

		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xNuevo = posicionLogicaX + j;
					int yNuevo = posicionLogicaY + i;
					tableroJuego[yNuevo][xNuevo] = true;
				}
			}
		}
		pintar();
	}

	public boolean ComprobarMoverIzquierda() {
		int nuevaPosX = posicionLogicaX - 1;

		limpiarFichaActual();

		boolean sePudoMover = true;

		if (nuevaPosX < 0) {
			sePudoMover = false;
		} else {
			for (int i = 0; i < matrizFichaActual.length; i++) {
				for (int j = 0; j < matrizFichaActual[0].length; j++) {
					if (matrizFichaActual[i][j]) {
						int xTablero = nuevaPosX + j;
						int yTablero = posicionLogicaY + i;
						if (xTablero < 0 || tableroJuego[yTablero][xTablero]) {
							sePudoMover = false;
							break;
						}
					}
				}
			}
		}

		if (sePudoMover) {
			posicionLogicaX = nuevaPosX;
		}

		colocarFicha(); // Siempre recoloca

		return sePudoMover;
	}

	private void moverIzquierda() {
		posicionLogicaX--; // Mover una columna a la izquierda

		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xNuevo = posicionLogicaX + j;
					int yNuevo = posicionLogicaY + i;
					tableroJuego[yNuevo][xNuevo] = true;
				}
			}
		}
		ComprobarMoverAbajo();
	}

	public void limpiarFichaActual() {
		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int x = posicionLogicaX + j;
					int y = posicionLogicaY + i;
					tableroJuego[y][x] = false;
				}
			}
		}
	}

	public boolean hayColisionDebajo() {

		for (int i = 0; i < matrizFichaActual.length; i++) {
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				if (matrizFichaActual[i][j]) {
					int xTablero = posicionLogicaX + j;
					int yTablero = posicionLogicaY + i;

					// Ver si estamos en la última fila
					if (yTablero + 1 >= tableroJuego.length) {
						return true; // Colisión con el fondo
					}

					// Solo verificar debajo si la celda siguiente está libre en la ficha
					if (i == matrizFichaActual.length - 1 || !matrizFichaActual[i + 1][j]) {
						if (tableroJuego[yTablero + 1][xTablero]) {
							return true; // Colisión con una ficha fija abajo
						}
					}
				}
			}
		}

		return false; // No hay colisión abajo
	}

	public boolean puedeRotarFicha(boolean[][] nuevaMatriz) {
		limpiarFichaActual();
		for (int i = 0; i < nuevaMatriz.length; i++) {
			for (int j = 0; j < nuevaMatriz[0].length; j++) {
				if (nuevaMatriz[i][j]) {
					int xTablero = posicionLogicaX + j;
					int yTablero = posicionLogicaY + i;

					// ¿Está fuera del tablero?
					if (xTablero < 0 || xTablero >= tableroJuego[0].length || yTablero < 0
							|| yTablero >= tableroJuego.length) {
						System.out.println("es aqui2");
						return false;
					}

					
					if (tableroJuego[yTablero][xTablero]) {
						System.out.println("es aqui3");
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean rotarDerecha() {

		boolean[][] rotada = rotarMatrizDerecha(getMatrizFichaActual());

		if (puedeRotarFicha(rotada)) {
			limpiarFichaActual();
			setMatrizFichaActual(rotada);
			colocarFicha();
			return true;
		} else {
			for (int dx = -1; dx <= 1; dx += 2) {
				setPosicionLogicaX(posicionLogicaX + dx);
				if (puedeRotarFicha(rotada)) {
					limpiarFichaActual();
					setMatrizFichaActual(rotada);
					colocarFicha();
					pintar();
					return true;
				}
				setPosicionLogicaX(posicionLogicaX - dx);
			}
			return false;
		}
	}

	public int getPosicionLogicaX() {
		return posicionLogicaX;
	}

	public void setPosicionLogicaX(int posicionLogicaX) {
		this.posicionLogicaX = posicionLogicaX;
	}

	private boolean[][] rotarMatrizDerecha(boolean[][] matrizFichaActual) {

		System.out.println("inicio");

		boolean[][] rotada = new boolean[matrizFichaActual[0].length][matrizFichaActual.length];
		int k = matrizFichaActual.length - 1;
		for (int i = 0; i < matrizFichaActual.length; i++) {
			System.out.println("aaaa");
			for (int j = 0; j < matrizFichaActual[0].length; j++) {
				rotada[j][k] = matrizFichaActual[i][j];
			}
			k--;
		}

		System.out.println("fin");

		return rotada;

	}

	public boolean[][] getTableroJuego() {
		return tableroJuego;
	}

	public void setTableroJuego(boolean[][] tableroJuego) {
		this.tableroJuego = tableroJuego;
	}

	public boolean[][] getMatrizFichaActual() {
		return matrizFichaActual;
	}

}