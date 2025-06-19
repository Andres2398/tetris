package controlador;

import modelo.Logica;
import vista.Interfaz;
/**
 * Clase Control que se dedica al control del flujo de juego
 */
public class Control {
	Logica logica;
	Interfaz interfaz;
	/**
	 * Constructor de la clase control
	 */
	public Control() {
		logica = new Logica();
		interfaz = new Interfaz();
	}
	/**
	 * Metodo que se dedica al control del juego
	 */
	public void start() {
		
		interfaz.saludo();
		interfaz.iniciarImagen();
		int matrizImagen[][] = interfaz.getMatrizImagenJuego();
	
		logica.copiarMatrizImagen(matrizImagen);
		interfaz.inicializarFuturaPieza();
		boolean fin = false;
		while (!fin) {
			
	
			
			interfaz.cambioDePieza();
			int[][]piezaPequena=interfaz.elegirFuturaPieza();
			matrizImagen=logica.pintarPiezaPequena(piezaPequena, matrizImagen);
			matrizImagen = logica.borrarPuntuacion(matrizImagen);
			
			// aqui se establece la puntuacion
			interfaz.repintarImagen(matrizImagen);
			interfaz.setMatrizImagenJuego(matrizImagen);
			interfaz.setPuntuacion(logica.getPuntuacion());
			// aqui se pinta la puntuacion
			interfaz.pintarPuntuacion();
			int matrizPieza[][] = interfaz.elegirPieza();
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
					try {
						Thread.sleep(300);
					} catch (Exception e) {
						// TODO: handle exception
					}
					interfaz.repintarImagen(matrizImagen);
				}
				
			}
			// no es lo mejor pero es la uncia forma que he conseguido
			// que se eliminen varias lineas a la vez
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen = logica.comprobarLinea(matrizImagen);
			matrizImagen=logica.borrarPiezaPequena(matrizImagen);
			interfaz.repintarImagen(matrizImagen);
			
			fin = logica.comprobarFin();
			if(movimiento.equals("t")||movimiento.equals("T"))
				fin=true;
			
		}
		interfaz.despedida();
	}
}
