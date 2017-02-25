import jdk.internal.util.xml.impl.Input

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun GameInput()
{
    println("\nYour wish is my command:\n")
    var user_command = readLine()?.toLowerCase()

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


        /*"move army" ->
        {
            var armyID = SelectArmy()
            var command: String
            if (armyID != -1)
            {
                println("Where do you want it to move?")
                println("x: ")
                var x = readLine()!!
                println("y: ")
                var y = readLine()!!
                ArmyList.get(armyID).move()
            }
        }*/

        "move army" -> //manually for now
        {
            var armyID = SelectArmy()
            var command: String
            if (armyID != -1)
            {
                println("Where do you want to move it? Type direction")
                command = readLine()!!
                ArmyList.get(armyID).move(command)
            }
        }

        "print army list" -> PrintArmyList()

/*-------------------------
______DEBUG COMMANDS______
--------------------------*/

        "show cities" -> PrintCitiesCoords()
        "show map" -> PrintMap()

/*-------------------------
_____GENERAL COMMANDS_____
--------------------------*/
        "next turn" -> nextTurn = true
        "exit" ->
        {
            gameFlag = false
            exit()
        }
        else -> print("Unkonown command, please try again")
    }
}