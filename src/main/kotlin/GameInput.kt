import jdk.internal.util.xml.impl.Input

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun ChooseCountry()
{
    println("What country do you want to choose? Type its ID, please:")
    printCountries()
    val playerID = readLine()!!.toInt()
    countrySearchByID(playerID)
}

fun GameInput()
{
    println("\nYour wish is my command:\n")
    var user_command = readLine()?.toLowerCase()

    when (user_command) {
/*-------------------------
_____MILITARY COMMANDS_____
--------------------------*/
        "create army" ->
        {
            println("How would you like to call it?")
            val newArmyName: String = readLine()!!
            CreateNewArmy(newArmyName)
        }

        "create general" -> CreateNewLeader()

        "assign general" -> AssignNewLeader()

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
            println("What army do you want to select?")
            val selectedName: String = readLine()!!
            val armyID = SelectArmy(selectedName)
            val command: String
            if (armyID != -1)
            {
                println("Where do you want to move it? Type direction")
                command = readLine()!!
                ArmyList.get(armyID).move(command)
            }
        }

        "recruit into army" ->
        {
            println("What army do you want to select?")
            val selectedName: String = readLine()!!

            val armyID = SelectArmy(selectedName)
            val recruits: Int
            if (armyID != -1)
            {
                println("How much do you want to recruit?")
                recruits = readLine()!!.toInt()
                ArmyList.get(armyID).increaseSize(recruits)
            }
        }

        "print army list" -> PrintArmyList()

/*-------------------------
______DEBUG COMMANDS______
--------------------------*/

        "show cities" -> PrintCityNames()
        "show cities coords" -> PrintCitiesCoords()
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
    }

/*------------------------
______REGEX COMMANDS______
-------------------------*/
    if (user_command!!.matches("create army (.+)".toRegex())) //e.g. create army abc
    {
        val Regex = "create army (.+)".toRegex()
        val newArmyName = Regex.find(user_command)!!.groupValues.get(1).toString()

        CreateNewArmy(newArmyName)
    }

    if (user_command!!.matches("recruit (\\d+) into army (.+)".toRegex())) //e.g. recruit 500 into army abc
    {
        val Regex = "recruit (\\d+) into army (.+)".toRegex()
        val recruits = Regex.find(user_command)!!.groupValues.get(1).toInt()
        val selectedName = Regex.find(user_command)!!.groupValues.get(2)

        var armyID = SelectArmy(selectedName)
        if (armyID != -1)
        {
            ArmyList.get(armyID).increaseSize(recruits)
        }
    }

    if (user_command!!.matches("tag (.+)".toRegex())) //debug command, e.g. tag Roman Empire
    {
        val Regex = "tag (.+)".toRegex()
        val countryName = Regex.find(user_command)!!.groupValues.get(1).toLowerCase()
        changePlayerCountry(countryName)
    }

}