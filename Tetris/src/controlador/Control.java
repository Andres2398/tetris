package controlador;

import modelo.Logica;
import vista.Interfaz;

public class Control {
	Logica logica;
	Interfaz interfaz;

	public Control() {
		logica = new Logica();
		interfaz = new Interfaz();
	}

	public void start() {

		interfaz.saludo();
		interfaz.iniciarImagen();
		int matrizImagen[][] = interfaz.getMatrizImagenJuego();
		logica.copiarMatrizImagen(interfaz.getMatrizImagenJuego());
		boolean fin = false;
		while (!fin) {
			matrizImagen = logica.borrarPuntuacion(matrizImagen);
			interfaz.setMatrizImagenJuego(matrizImagen);
			interfaz.pintarPuntuacion();

			int matrizPieza[][] = interfaz.elegirPiezaRandom();
			logica.iniciarNuevaPieza(matrizPieza, interfaz.getMatrizBoolean());

			matrizImagen = logica.coloCarPieza(interfaz.getMatrizImagenJuego(), matrizPieza);
			logica.colocarFichaTableroInterno(interfaz.getMatrizBoolean());
			interfaz.setMatrizImagenJuego(matrizImagen);
			interfaz.repintarImagen(matrizImagen);

			boolean piezaColocada = false;

			while (!piezaColocada) {
				matrizImagen = logica.borrarPieza(matrizImagen);
				interfaz.setMatrizImagenJuego(matrizImagen);

				String movimiento = interfaz.pedirMovimientos();

				if (logica.isPiezaFija()) {
					piezaColocada = true;

				} else {

					switch (movimiento.toLowerCase()) {
					case "a":
						logica.moverIzquierda(matrizImagen);
						break;
					case "d":
						logica.moverDerecha(matrizImagen);
						break;
					case "x":
						logica.rotarIzquierda();

						logica.moverAbajo(matrizImagen);
						break;
					case "c": // Rotar derecha

						logica.rotarDerecha(matrizImagen);

						break;
					case "s": // Mover abajo explícitamente
						if (!logica.moverAbajo(matrizImagen)) {
							piezaColocada = true;
							logica.colocarFichaTableroInterno(interfaz.getMatrizBoolean());
						}
						break;
					}

					// Si el movimiento no fue "s" (abajo) ni rotación, ya bajó dentro de
					// moverIzquierda o moverDerecha.
					// Pero si fue rotación, ya se agregó moverAbajo dentro.
					// Aquí no haría falta llamar moverAbajo otra vez.

					if (logica.isPiezaFija()) {
						piezaColocada = true;
						logica.colocarFichaTableroInterno(interfaz.getMatrizBoolean());

					}
					matrizImagen = logica.pintarFicha(matrizImagen);
					interfaz.repintarImagen(matrizImagen);
				}
			}
		}
	}
}
