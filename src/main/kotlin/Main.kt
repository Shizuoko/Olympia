/**
 * Created by Shizuoko on 17.02.2017.
 */

import sun.plugin2.message.GetAppletMessage
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>)
{
    menu()
}

var gameFlag: Boolean = true
var nextTurn: Boolean = false
var cycleCount: Int = 0

fun menu()
{
    println(File("assets/logo.txt").readText(charset = Charsets.UTF_8))
    println("What should I do? Play or exit?\n")
    val play = readLine()?.toLowerCase()
    if(play!!.contains("play"))
    {
        println("OK, let's start!")
        LoadMap()
        LoadCities()
        MainLoop(gameFlag)
    }
    else if (play.contains("exit"))
    {
        exit()
    }
    else
    {
        println("Wrong command.\n")
        menu()
    }
}
fun MainLoop(gameFlag: Boolean)
{
    while (gameFlag == true)
    {

        while(nextTurn == false)
        {
            GameInput()

            ArmiesReport()
        }

        nextTurn = false

        ReloadArmiesMP() //MP = movement points

        cycleCount++
        println ("\n Turn number: " + cycleCount + "\n")
    }
}
fun exit()
{
    println("I will be waiting for you, Master.")
    exitProcess(1)
}