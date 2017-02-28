import org.jetbrains.annotations.Mutable
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
            println("ARMY LIST \n" + ":" + ArmyList.get(Iterator).armyName + " ----- " + ArmyList.get(Iterator).armySize + " soldiers")
        }
    }
    else {
        println("You don't have any armies!")
    }
}

fun CreateNewArmy(newArmyName: String)
{
    var isDouble = false

    if (ArmyList.size != 0)
    {
        for (Iterator: Int in 0..ArmyList.size-1)
        {
            if (ArmyList.get(Iterator).armyName == newArmyName)
            {
                println("B-b-but Master, we already have an army with specified name!\n")
                isDouble = true
                CreateNewArmy(newArmyName)
            }
        }
    }

    if (isDouble == false)
    {
        println("Where should we create an army? Pick a city from the list:")
        PrintPlayerCities()
        val cityName = readLine()!!

        var x: Int = 0
        var y: Int = 0

        var searchResult: String = "There is no such city on the list"

        for(Iterator in 0..Cities.size-1)
        {
            if(Cities.get(Iterator).name.equals(cityName) && Cities.get(Iterator).controller.equals(searchPlayerCountryName()))
            {
                x = Cities.get(Iterator).x
                y = Cities.get(Iterator).y
                searchResult = Cities.get(Iterator).name
            }
        }

        if(searchResult.equals("There is no such city in the list")) {
            println(searchResult)
        }
        else {
            val Army = Army(newArmyName, x, y)
            ArmyList.add(Army)
            println("Army " + newArmyName + " in " + searchResult + " created successfully!")
        }
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
            ArmyList.get(Iterator).report()
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
           var x: Int,
           var y: Int)
{
    var armySize: Int = 0
    var commandingOfficer: String = "No general"
    var movementPoints: Int = 200

    fun move(direction: String)
    {
            //should've used 'when', but oh well
            if (direction.toLowerCase() == "north" && y>0){
                if (Map.get(x).get(y-1).movementCost <= movementPoints) {
                    y--
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "south" && y<MapSize-1){
                if (Map.get(x).get(y+1).movementCost <= movementPoints) {
                    y++
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "west" && x>0){
                if (Map.get(x).get(x-1).movementCost <= movementPoints) {
                    x--
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "east" && x<MapSize-1){
                if (Map.get(x).get(x+1).movementCost <= movementPoints) {
                    x++
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "north-west" && x>0 && y>0) {
                if (Map.get(x-1).get(y-1).movementCost <= movementPoints) {
                    y--
                    x--
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "north-east" && x<MapSize-1 && y>0) {
                if (Map.get(x+1).get(y-1).movementCost <= movementPoints) {
                    y--
                    x++
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "south-west" && x>0 && y<MapSize-1) {
                if (Map.get(x-1).get(y+1).movementCost <= movementPoints) {
                    y++
                    x--
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

            if (direction.toLowerCase() == "south-east" && x<MapSize-1 && y<MapSize-1) {
                if (Map.get(x+1).get(y+1).movementCost <= movementPoints) {
                    y++
                    x++
                    movementPoints -= Map.get(x).get(y).movementCost
                }
            }

    }

    fun increaseSize(recruits: Int)
    {
        if (Map[x][y] is CityCell)
        {
            var CityCell: CityCell = Map[x][y] as CityCell
            if (recruits < CityCell.armySize)
            {
                CityCell.armySize = CityCell.armySize - recruits
                armySize = armySize + recruits
                Map[x][y] = CityCell
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
        println("We have " + armySize + " recruits\n")
    }
}

class ArmyLeader(var leaderName: String)
{

}