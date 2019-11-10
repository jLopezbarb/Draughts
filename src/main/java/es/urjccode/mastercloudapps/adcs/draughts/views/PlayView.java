package es.urjccode.mastercloudapps.adcs.draughts.views;

import java.util.regex.Pattern;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class PlayView extends ConsoleView {

    private static final String[] COLORS = {"blancas", "negras"};

    public PlayView(){
        super();
    }

    public void interact(PlayController playController) {
        String color = PlayView.COLORS[playController.getColor().ordinal()];
        Error error = null;
        GameView gameView = new GameView();
        Coordinate origin; 
        Coordinate target;
        do {
            String userInput = readProposedMovement(color);
            origin = getCoordinateFromString(userInput.substring(0, 2));
            target = getCoordinateFromString(userInput.substring(3, 5));
            error = playController.getErrorFromMovement(origin, target);
            if (error != null){
                console.writeln(new ErrorView().getErrorMessage(error));
            }
        } while (error != null); 
        playController.move(origin, target);
        gameView.write(playController);
        if (playController.isBlocked()){
            this.console.write(MessageView.LOOSER.getMessage());
        }
    }

    private String getProposeMessage(String color){
        return MessageView.PROPOSE_MOVE.getMessage().replace("#color", color);
    }

    private Coordinate getCoordinateFromString(String number){
        int coordinate = Integer.parseInt(number);
        return new Coordinate(coordinate/10-1, coordinate%10-1);
    }

    private String readProposedMovement(String color){
        String regex = "[1-8]{2}.[1-8]{2}";
        boolean correctInput;
        String userInput;
        do{
            userInput = this.console.readString(getProposeMessage(color));
            correctInput = Pattern.matches(regex, userInput);
            if (!correctInput) {
                console.writeln(new ErrorView().getErrorMessage(Error.BAD_INPUT));
            }
        } while (correctInput != true);
        return userInput;
    }
}