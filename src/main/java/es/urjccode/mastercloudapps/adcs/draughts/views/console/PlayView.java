package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class PlayView {

    private Console console;

    private PlayController playController;

    private static final String[] COLORS = {"blancas", "negras"};

    public PlayView(PlayController playController){
        this.playController = playController;
        this.console = new Console();
    }

    public void interact() {
        String option;
        do{
            playMenu();
            option = this.console.readString("Elige una opcion: ");
            if ("1".equals(option)){
                move();
            } else if ("2".equals(option)){
                cancel();
            } else{
                this.console.writeln("Error!!!" + Error.BAD_INPUT);
            }
        }while (option == "1" || option == "2"); 
  
    }

    public void playMenu(){
        this.console.writeln();
        this.console.writeln("---PlayMenu---");
		this.console.writeln("1.- Mover");
        this.console.writeln("2.- Cancelar");
    }

    private void move(){
        String color = PlayView.COLORS[playController.getColor().ordinal()];
        Error error = null;
        do {
            String command = this.console.readString("Mueven las " + color + ": ");
            int origin = Integer.parseInt(command.substring(0, 2));
            int target = Integer.parseInt(command.substring(3, 5));
            error = playController.move(new Coordinate(origin/10, origin%10), new Coordinate(target/10, target%10));
            if (error != null){
                this.console.writeln("Error!!!" + error.name());
            }
        } while (error != null);
    }

    private void cancel(){
        playController.finish();
    }
}