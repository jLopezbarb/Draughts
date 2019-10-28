package models;

public class Logic{

    private Game game;
	private State state;
	
	public Logic() {
		this.game = new Game();
		this.state = new State();
    }
    
    public StateValue getStateValue() {
		return this.state.getValue();
	}
	
	public void next() {
		this.state.next();
	}
	
}