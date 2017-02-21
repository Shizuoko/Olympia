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

fun CreateNewLeader()
{
    println("How would you like to call him/her/zir?")
    val NewLeaderName: String = readLine()!!

    val Leader = ArmyLeader(NewLeaderName)
}

fun AssignNewLeader()
{
    println("Which leader do you wish to assign?")
    val AssignLeaderName: String = readLine()!!

    println("Which army should receive this glorious leader?")
    val AssignArmyName: String = readLine()!!

    //Probably should finish this :)
}

class Army(var ArmyName: String, var ArmyLocation: String, var ArmySize: Int = 0, var CommandingOfficer: String = "No general", var MovementOrder: String = "Nowhere")
{

}

class ArmyLeader(var LeaderName: String)
{

}