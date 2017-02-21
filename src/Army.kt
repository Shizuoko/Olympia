/**
 * Created by Shizuoko on 21.02.2017.
 */

fun CreateNewArmy()
{
    println("How would you like to call it?")
    val NewArmyName: String = readLine()!!
    println("Where should we create an army?")
    val NewArmyLocation: String = readLine()!!

    val Army = Army(NewArmyName,NewArmyLocation)
}

class Army(var ArmyName: String, var ArmyLocation: String, var ArmySize: Int = 0, var CommandingOfficer: String = "No general", var MovementOrder: String = "Nowhere")
{

}

class ArmyLeader(var LeaderName: String)
{

}