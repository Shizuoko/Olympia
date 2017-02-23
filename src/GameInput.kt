import jdk.internal.util.xml.impl.Input

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun GameInput(user_command: String?)
{
    when (user_command) {
/*-------------------------
_____MILITARY COMMANDS_____
--------------------------*/
        "create army" -> CreateNewArmy()
        "create an army" -> CreateNewArmy()
        "create an army worthy of Mordor!" -> CreateNewArmy()

        "create general" -> CreateNewLeader()
        "create a general" -> CreateNewLeader()
        "create leader" -> CreateNewLeader()
        "create a leader" -> CreateNewLeader()

        "assign general" -> AssignNewLeader()
        "assign a general" -> AssignNewLeader()
        "assign leader" -> AssignNewLeader()
        "assign a leader" -> AssignNewLeader()

/*-------------------------
______DEBUG COMMANDS______
--------------------------*/

        "show cities" -> ShowCitiesCoords()

/*-------------------------
_____GENERAL COMMANDS_____
--------------------------*/
        "wait" -> print("Waiting")
        "exit" ->
        {
            var gameFlag = false
            exit()
        }
        else -> print("Unkonown command, please try again")
    }
}