package vista;

public enum Pieza {
	    PIEZAT("./img/PiezasTetris/PiezaT.png",
	    		new boolean[][] {
	            	{ false, true, false },
	            	{ true, true, true }
	        }
	    ),
	    PIEZACUADRADO("./img/PiezasTetris/PiezaCuadrado.png",
	    		new boolean[][] {
	    			{ true, true },
	    			{ true, true }
	        }
	    ),
	    PIEZAI("./img/PiezasTetris/PiezaI.png", 
	    		new boolean [][] {
	    			{true},
	    			{true},
	    			{true},
	    			{true},
	    	}
	    ),
	    PIEZAJ("./img/PiezasTetris/PiezaJ.png",
	    		new boolean [][] {
	    			{false, true},
	    			{false, true},
	    			{true, true}
	    	}
	    ),
	    PIEZAL("./img/PiezasTetris/PiezaL.png",
	    		new boolean [][] {
	    			{true, false},
	    			{true, false},
	    			{true, true},
	    				
	    	}
	    ),
	    PIEZAS("./img/PiezasTetris/PiezaS.png",
	    		new boolean [][] {
					{false,true,true},
					{true,true,false},	
	    	}
	    ),
	    PIEZAZ("./img/PiezasTetris/PiezaZ.png",
	    		new boolean [][] {
	    			{true,true,false},	
	    			{false,true,true},
	    	}
				
	    );
	    
	    
	

	private final Imagen imagen;
	
	private final boolean[][] forma;

	Pieza(String ruta, boolean[][] forma) {
		this.imagen =  new Imagen(ruta);
		imagen.cerrar();
		this.forma = forma;
	}

	public Imagen getImagen() {
		
		return imagen;
	}

	public boolean[][] getForma() {
		return forma;
	}

}
