import jdk.internal.util.xml.impl.Input

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun GameInput(user_command: String?)
{
    when (user_command) {
        "wait" -> print("Waiting")
        "exit" ->
        {
            var gameFlag = false
            exit()
        }
        else -> print("Unkonown command, please try again")
    }
}