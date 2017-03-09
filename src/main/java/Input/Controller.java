package Input;

/**
 * Created by Shizuoko on 05.03.2017.
 */
public class Controller {
    public void playButtonClicked(){
        new game.Game();
        game.GameKt.gameStart();
    }

}
