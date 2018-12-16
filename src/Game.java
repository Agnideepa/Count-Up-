/**
 * A class to create a view object and a model object and call the controller on them.
 */

public class Game {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();

        GameController controller = new GameController(view, model);

        view.setVisible(true);
    }
}
