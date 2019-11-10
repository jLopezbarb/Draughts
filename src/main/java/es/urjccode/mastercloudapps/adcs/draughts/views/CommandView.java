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
        do {
            String command = this.console.readString(getProposeMessage(color));
            int origin = Integer.parseInt(command.substring(0, 2));
            int target = Integer.parseInt(command.substring(3, 5));
            error = playController.move(new Coordinate(origin/10-1, origin%10-1), new Coordinate(target/10-1, target%10-1));
            if (error != null){
                console.writeln(new ErrorView().getErrorMessage(error));
            gameView.write(playController);
            }
        } while (error != null); 
        if (playController.isBlocked()){
            this.console.write(MessageView.LOOSER.getMessage());
        }
    }

    private String getProposeMessage(String color){
        return MessageView.PROPOSE_MOVE.getMessage().replace("#color", color);
    }

}