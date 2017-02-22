import org.jetbrains.annotations.Mutable
import java.util.*

/**
 * Created by Shizuoko on 21.02.2017.
 */


var ArmyList: MutableList<Army> = arrayListOf()
var ArmyLeaderList: MutableList<ArmyLeader> = arrayListOf()


fun CreateNewArmy()
{
    if (ArmyList.size == 0)
    {
        println("How would you like to call it?")
        val NewArmyName: String = readLine()!!

        println("Where should we create an army?")
        val NewArmyLocation: String = readLine()!!

        val Army = Army(NewArmyName, NewArmyLocation)
        ArmyList.add(Army)
        println(ArmyList.get(0).ArmyName)

        println("Army " + NewArmyName + " in " + NewArmyLocation + " created successfully!")
    }
    else
    {
        println("How would you like to call it?")
        val NewArmyName: String = readLine()!!

        for (Iterator: Int in 0..ArmyList.size as Int) {
            if (ArmyList.get(Iterator).ArmyName == NewArmyName) {
                println("B-b-but Master, we already have an army with specified name!\n")
                CreateNewArmy()
            }
        }

        println("Where should we create an army?")
        val NewArmyLocation: String = readLine()!!

        val Army = Army(NewArmyName, NewArmyLocation)
        ArmyList.add(Army)
        println("Army " + NewArmyName + " in " + NewArmyLocation + " created successfully!")
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

        for (Iterator: Int in 0..ArmyList.size as Int) {
            if (ArmyList.get(Iterator).ArmyName == AssignArmyName) {
                ArmyList.get(Iterator).CommandingOfficer = AssignLeaderName
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

class Army(var ArmyName: String, var ArmyLocation: String, var ArmySize: Int = 0, var CommandingOfficer: String = "No general", var MovementOrder: String = "Nowhere")
{

}

class ArmyLeader(var LeaderName: String)
{

}