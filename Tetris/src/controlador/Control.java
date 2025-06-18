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
		//se que hay metodos que sobran los tengo para probar cosas 
		interfaz.saludo();
		interfaz.iniciarImagen();
		int matrizImagen[][] = interfaz.getMatrizImagenJuego();
		//aqui se copia la imagen del tablero para tener uno vacio
		logica.copiarMatrizImagen(matrizImagen);
		boolean fin = false;
		while (!fin) {
			// aqui se borra la puntuacion
			matrizImagen = logica.borrarPuntuacion(matrizImagen);
			// aqui se establece la puntuacion
			interfaz.setMatrizImagenJuego(matrizImagen);
			interfaz.setPuntuacion(logica.getPuntuacion());
			// aqui se pinta la puntuacion
			interfaz.pintarPuntuacion();
			int matrizPieza[][] = interfaz.elegirPiezaRandom();
			logica.iniciarNuevaPieza(matrizPieza, interfaz.getMatrizBoolean());

			matrizImagen = logica.coloCarPieza(interfaz.getMatrizImagenJuego(), matrizPieza);
			logica.colocarFichaTableroInterno(interfaz.getMatrizBoolean());
			interfaz.setMatrizImagenJuego(matrizImagen);
			interfaz.repintarImagen(matrizImagen);

			boolean piezaColocada = false;
			String movimiento="";
			while (!piezaColocada) {
				matrizImagen = logica.borrarPieza(matrizImagen);
				interfaz.setMatrizImagenJuego(matrizImagen);

				movimiento = interfaz.pedirMovimientos();

				if (logica.isPiezaFija()) {
					piezaColocada = true;

				} else {

					switch (movimiento) {
					case "a":
					case "A":	
						logica.moverIzquierda(matrizImagen);
						break;
					case "d":
					case "D":
						logica.moverDerecha(matrizImagen);
						break;
					case "x":
					case "X":
						logica.rotarIzquierda(matrizImagen);
						break;
					case "c":
					case "C":
						logica.rotarDerecha(matrizImagen);
						break;
					case "s":
					case "S":
						if (!logica.moverAbajo(matrizImagen)) {
							piezaColocada = true;
							logica.colocarFichaTableroInterno(interfaz.getMatrizBoolean());
						}
						break;
					case "t":
					case "T":
						piezaColocada = true;
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
			// no es lo mejor pero es la uncia forma que he conseguido
			// que se eliminen varias lineas a la vez
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			interfaz.repintarImagen(matrizImagen);
			fin = logica.comprobarFin();
			if(movimiento.equals("t")||movimiento.equals("T"))
				fin=true;
		}
		interfaz.despedida();
	}
}
