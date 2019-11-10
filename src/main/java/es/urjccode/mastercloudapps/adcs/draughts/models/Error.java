package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Error {
    OUT_COORDINATE("Coodenada fuera del tablero"), 
    EMPTY_ORIGIN("El origen está vacío"), 
    OPPOSITE_PIECE("La pieza es del contrario"), 
    NOT_DIAGONAL("El movimiento no está en diagonal"), 
    BAD_DISTANCE("La distancia no es correcta"),
    NOT_EMPTY_TARGET("El destino no está vacío"), 
    NOT_ADVANCED("No se avanza"), 
    EATING_EMPTY("No se puede comer a nadie");

    private String error;
	
	Error(String error) {
		this.error = error;
	}
	
	public String getMessage() {
		return this.error;
	}
}