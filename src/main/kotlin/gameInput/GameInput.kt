package gameInput

import armies.*
import countries.changePlayerCountry
import countries.printCountries
import countries.searchCountryIDByName
import countries.searchStartingCountry
import diplomacy.declareWar
import jdk.internal.util.xml.impl.Input
import game.exit
import game.gameFlag
import game.nextTurn
import printStatus.PrintCitiesCoords
import printStatus.PrintCityNames
import printStatus.PrintMap

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun ChooseCountry()
{
    println("What country do you want to choose? Type its ID, please:")
    printCountries()
    val playerID = readLine()!!.toInt()
    searchStartingCountry(playerID)
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
            var armyID = armies.SelectArmy()
            var command: String
            if (armyID != -1)
            {
                println("Where do you want it to move?")
                println("x: ")
                var x = readLine()!!
                println("y: ")
                var y = readLine()!!
                armies.getArmyList.get(armyID).move()
            }
        }*/

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

    if (user_command!!.matches("recruit (\\d+) (.+) into army (.+)".toRegex())) //e.g. recruit 500 archers into army abc
    {
        val Regex = "recruit (\\d+) (.+) into army (.+)".toRegex()
        val recruits = Regex.find(user_command)!!.groupValues.get(1).toInt()
        val selectedUnitType = Regex.find(user_command)!!.groupValues.get(2)
        val selectedName = Regex.find(user_command)!!.groupValues.get(3)

        if(selectedUnitType.equals("infantry") || selectedUnitType.equals("cavalry") || selectedUnitType.equals("archers"))
        {
            var armyID = SelectArmy(selectedName)
            if (armyID != -1)
            {
                ArmyList.get(armyID).increaseSize(selectedUnitType, recruits)
            }
        }
    }

    if (user_command!!.matches("recruit (\\d+)i (\\d+)a (\\d+)c into army (.+)".toRegex())) //e.g. recruit 500i 700a 200c into army abc
    {
        val Regex = "recruit (\\d+)i (\\d+)a (\\d+)c into army (.+)".toRegex()
        val recruitsI = Regex.find(user_command)!!.groupValues.get(1).toInt()
        val recruitsA = Regex.find(user_command)!!.groupValues.get(2).toInt()
        val recruitsC = Regex.find(user_command)!!.groupValues.get(3).toInt()
        val selectedName = Regex.find(user_command)!!.groupValues.get(4)

        var armyID = SelectArmy(selectedName)
        if (armyID != -1)
        {
            ArmyList.get(armyID).increaseSize("infantry", recruitsI)
            ArmyList.get(armyID).increaseSize("archers", recruitsA)
            ArmyList.get(armyID).increaseSize("cavalry", recruitsC)
        }
    }

    if (user_command!!.matches("tag (.+)".toRegex())) //debug command, e.g. tag Roman Empire
    {
        val Regex = "tag (.+)".toRegex()
        val countryName = Regex.find(user_command)!!.groupValues.get(1).toLowerCase()
        changePlayerCountry(countryName)
    }

    if (user_command!!.matches("declare war on (.+)".toRegex())) //e.g. declare war on Roman Republic
    {
        val Regex = "declare war on (.+)".toRegex()
        val countryName = Regex.find(user_command)!!.groupValues.get(1).toLowerCase()
        val countryID = searchCountryIDByName(countryName)
        if(countryID != -1)
        {
            declareWar(countryID)
        }
        else
        {
            println("There is no country with specified name")
        }
    }

    if (user_command!!.matches("move army (.+) to the (.+)".toRegex())) //e.g. move army 123 to the west
    {
        val Regex = "move army (.+) to the (.+)".toRegex()
        val selectedName = Regex.find(user_command)!!.groupValues.get(1).toLowerCase()
        val command = Regex.find(user_command)!!.groupValues.get(2).toLowerCase()

        val armyID = SelectArmy(selectedName)
        if (armyID != -1)
        {
            ArmyList.get(armyID).move(command)
        }

    }

    if (user_command!!.matches("teleport (.+) to (\\d+) (\\d+)".toRegex())) //e.g. teleport 123 to 4 5
    {
        val Regex = "teleport (.+) to (\\d+) (\\d+)".toRegex()
        val selectedName = Regex.find(user_command)!!.groupValues.get(1).toLowerCase()
        val x = Regex.find(user_command)!!.groupValues.get(2).toInt()
        val y = Regex.find(user_command)!!.groupValues.get(3).toInt()

        val armyID = SelectArmy(selectedName)
        if (armyID != -1)
        {
            ArmyList.get(armyID).x = x
            ArmyList.get(armyID).y = y
        }
    }

}