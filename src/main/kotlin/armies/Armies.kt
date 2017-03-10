package armies

import countries.searchPlayerCountryID
import map.Cities
import map.CityCell
import map.Map
import map.MapSize
//import org.jetbrains.annotations.Mutable
import printStatus.PrintPlayerCities
import java.util.*

/**
 * Created by Shizuoko on 21.02.2017.
 */


var ArmyList: MutableList<Army> = arrayListOf()
var ArmyLeaderList: MutableList<ArmyLeader> = arrayListOf()

fun SelectArmy(selectedName: String): Int {
    var armyID: Int = 0
    var searchResult = 0

    if (ArmyList.size != 0) {
        for (Iterator: Int in 0..ArmyList.size - 1) {
            if (ArmyList.get(Iterator).armyName == selectedName) {
                armyID = Iterator
                searchResult++
                break
            }
        }
    }
    else {
        println("You don't have any armies!")
        armyID = -1
    }

    if (searchResult == 0 && ArmyList.size != 0){
        println("There is no army with specified name!")
        armyID = -1
    }
    return armyID
}

fun PrintArmyList(){
    if (ArmyList.size != 0) {
        for (Iterator: Int in 0..ArmyList.size - 1) {
            println("ARMY LIST \n" + ":" + ArmyList.get(Iterator).armyName +
                    " ----- " + ArmyList.get(Iterator).infantrySize + " infantry, " +
                    ArmyList.get(Iterator).cavalrySize + "cavalry, " +
                    ArmyList.get(Iterator).archersSize + "archers\n")
        }
    }
    else {
        println("You don't have any armies!")
    }
}

fun CreateNewArmy(newArmyName: String)
{
    println("Where should we create an army? Pick a city from the list:")
    PrintPlayerCities()
    val cityName = readLine()!!

    var armyID = 0
    if (ArmyList.size != 0)
    {
        armyID = ArmyList.last().armyID + 1
    }

    var x: Int = 0
    var y: Int = 0

    var searchResult: Int = 0

    for(Iterator in 0..Cities.size-1)
    {
        if(Cities.get(Iterator).name.equals(cityName) && Cities.get(Iterator).controller.equals(searchPlayerCountryID()))
        {
            x = Cities.get(Iterator).x
            y = Cities.get(Iterator).y
            searchResult++
        }
    }

    if(searchResult != 1)
    {
            println("There is no such city in the list")
    }
    else
    {
        val Army = Army(newArmyName, armyID, searchPlayerCountryID(), x, y)
        ArmyList.add(Army)
        println("armies.Army " + newArmyName + " in " + cityName + " created successfully!")
    }
}

fun CreateNewLeader()
{
    println("How would you like to call him/her/zir?")
    val NewLeaderName: String = readLine()!!

    val Leader = ArmyLeader(NewLeaderName)
    ArmyLeaderList.add(Leader)
    println(NewLeaderName + " created successfully!")
}

fun AssignNewLeader()
{
    if (ArmyLeaderList.size == 0)
    {
        println("We don't have any leaders, Master! You should create one first!")
    }

    else {
        println("Which leader do you wish to assign?")
        val AssignLeaderName: String = readLine()!!

        println("Which army should receive this glorious leader?")
        val AssignArmyName: String = readLine()!!

        var SearchResult: Int = 0

        for (Iterator: Int in 0..ArmyList.size-1) {
            if (ArmyList.get(Iterator).armyName == AssignArmyName) {
                ArmyList.get(Iterator).commandingOfficer = AssignLeaderName
                SearchResult++
                break
            }
        }

        if (SearchResult == 0) {
            println("We don't have an army with specified name, sir!")
            AssignNewLeader()
        }
    }
}

fun ArmiesReport()
{
    if (ArmyList.size != 0) {
        for (Iterator: Int in 0..ArmyList.size - 1) {
            if(ArmyList.get(Iterator).armyControllerID == searchPlayerCountryID())
            {
                ArmyList.get(Iterator).report()
            }
        }
    }
}

fun ReloadArmiesMP()
{
    if (ArmyList.size != 0) {
        for (Iterator: Int in 0..ArmyList.size - 1) {
            ArmyList.get(Iterator).movementPoints = 200
        }
    }
}

class Army(var armyName: String,
           var armyID: Int,
           var armyControllerID: Int,
           var x: Int,
           var y: Int)
{
    var commandingOfficer: String = "No general"
    var movementPoints: Int = 200

    var morale = 100

    var infantrySize: Int = 0
    var cavalrySize: Int = 0
    var archersSize: Int = 0
    var totalSize: Int = infantrySize + cavalrySize + archersSize

    fun move(direction: String)
    {
        if (totalSize > 0) {
            if (armyControllerID == searchPlayerCountryID()) {
                //should've used 'when', but oh well
                if (direction.toLowerCase() == "north" && y > 0) {
                    if (Map.get(x).get(y - 1).movementCost <= movementPoints) {
                        y--
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "south" && y < MapSize - 1) {
                    if (Map.get(x).get(y + 1).movementCost <= movementPoints) {
                        y++
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "west" && x > 0) {
                    if (Map.get(x).get(x - 1).movementCost <= movementPoints) {
                        x--
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "east" && x < MapSize - 1) {
                    if (Map.get(x).get(x + 1).movementCost <= movementPoints) {
                        x++
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "north-west" && x > 0 && y > 0) {
                    if (Map.get(x - 1).get(y - 1).movementCost <= movementPoints) {
                        y--
                        x--
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "north-east" && x < MapSize - 1 && y > 0) {
                    if (Map.get(x + 1).get(y - 1).movementCost <= movementPoints) {
                        y--
                        x++
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "south-west" && x > 0 && y < MapSize - 1) {
                    if (Map.get(x - 1).get(y + 1).movementCost <= movementPoints) {
                        y++
                        x--
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }

                if (direction.toLowerCase() == "south-east" && x < MapSize - 1 && y < MapSize - 1) {
                    if (Map.get(x + 1).get(y + 1).movementCost <= movementPoints) {
                        y++
                        x++
                        movementPoints -= Map.get(x).get(y).movementCost
                    }
                }
            }
        }
        else
        {
            println("This army is empty, how can it move?")
        }
    }

    fun increaseSize(unitType: String, recruits: Int)
    {
        if (Map[x][y] is CityCell)
        {
            var CityCell: CityCell = Map[x][y] as CityCell
            if (recruits <= CityCell.armySize && CityCell.controller == searchPlayerCountryID())
            {
                CityCell.armySize = CityCell.armySize - recruits
                when(unitType)
                {
                    "infantry" -> {infantrySize = infantrySize + recruits}
                    "cavalry" -> {cavalrySize = cavalrySize + recruits}
                    "archers" -> {archersSize = archersSize + recruits}
                }
                Map[x][y] = CityCell
                updateTotalSize()
            }
            else
            {
                println("This city doesn't have this much recruits!")
            }
        }
        else
        {
            println("There are no cities near!")
        }
    }

    fun updateTotalSize()
    {
        totalSize = infantrySize + cavalrySize + archersSize
    }

    fun report()
    {
        if (Map[x][y] is CityCell) {
            val CityCell = Map[x][y] as CityCell
            println("\n" + armyName + " is at " + CityCell.name)
        }
        else {
            println("\n" + armyName + " is at: " + x + "," + y)
        }
        println("We have " + movementPoints + " movement points")
        println("We have " + infantrySize + " infantry, " + cavalrySize + " cavalry, " + archersSize + " archers")
        println("" + totalSize + " soldiers in total\n")
    }
}

class ArmyLeader(var leaderName: String)
{

}