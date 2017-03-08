package Input;

import static sun.misc.Version.println;

/**
 * Created by Shizuoko on 05.03.2017.
 */
public class Controller {
    public void playButtonClicked(){
        new main.Game();
        main.MainKt.gameStart();
    }

}
