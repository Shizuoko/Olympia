/**
 * Created by Shizuoko on 17.02.2017.
 */

import sun.plugin2.message.GetAppletMessage
import kotlin.system.exitProcess

fun main(args: Array<String>)
{
    menu()
}
fun menu()
{
    println("What should I do? Play or exit?\n")
    val play = readLine()?.toLowerCase()
    if(play!!.contains("play"))
    {
        println("OK, let's start!")
        var gameFlag: Boolean = true
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
    var cycleCount: Int = 0

    while (gameFlag == true)
    {
        println("\nYour wish is my command:\n")
        val user_command = readLine()?.toLowerCase()

        GameInput(user_command)

        cycleCount++
    }
}
fun exit()
{
    println("I will be waiting for you, Master.")
    exitProcess(1)
}
