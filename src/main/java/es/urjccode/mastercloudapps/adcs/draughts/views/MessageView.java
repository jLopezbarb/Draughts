package es.urjccode.mastercloudapps.adcs.draughts.views;

public enum MessageView {
	RESUME("¿Queréis jugar otra"),
	PROPOSE_MOVE("Mueven las #color: "),
	TITLE("Draughts"),
	LOOSER("Derrota!!! No puedes mover tus fichas!!!");

	private String message;
	
	MessageView(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}