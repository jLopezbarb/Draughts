package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class CommandView extends ConsoleView {

    private static final String[] COLORS = {"blancas", "negras"};

    public CommandView(){
        super();
    }

    public void interact(PlayController playController) {
        String color = CommandView.COLORS[playController.getColor().ordinal()];
        Error error = null;
        GameView gameView = new GameView();
        int origin; 
        int target;
        do {
            String command = this.console.readString(getProposeMessage(color));
            origin = Integer.parseInt(command.substring(0, 2));
            target = Integer.parseInt(command.substring(3, 5));
            error = playController.getErrorFromMovement(getCoordinateFromInt(origin), getCoordinateFromInt(target));
            if (error != null){
                console.writeln(new ErrorView().getErrorMessage(error));
            }
        } while (error != null); 
        playController.move(getCoordinateFromInt(origin), getCoordinateFromInt(target));
        gameView.write(playController);
        if (playController.isBlocked()){
            this.console.write(MessageView.LOOSER.getMessage());
        }
    }

    private String getProposeMessage(String color){
        return MessageView.PROPOSE_MOVE.getMessage().replace("#color", color);
    }

    private Coordinate getCoordinateFromInt(int number){
        return new Coordinate(number/10-1, number%10-1);
    }
}