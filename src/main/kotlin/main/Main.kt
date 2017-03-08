package main

/**
 * Created by Shizuoko on 17.02.2017.
 */

import armies.ArmiesReport
import armies.ReloadArmiesMP
import battles.battleCheck
import diplomacy.initCountryRelations
import gameInput.ChooseCountry
import gameInput.GameInput
import map.LoadCities
import map.LoadMap
import printStatus.initMapInfo
import printStatus.printStatus
import java.io.File
import kotlin.system.exitProcess

class Game

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
    println("What should I do? Play or main.exit?\n")
    val play = readLine()?.toLowerCase()
    if(play!!.contains("play"))
    {
        println("OK, let's start!")

        LoadMap()
        LoadCities()
        initMapInfo()
        initCountryRelations()

        ChooseCountry()

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


fun gameStart()
{
        LoadMap()
        LoadCities()
        initMapInfo()
        initCountryRelations()

        ChooseCountry()

        MainLoop(gameFlag)
}


fun MainLoop(gameFlag: Boolean)
{
    while (gameFlag == true)
    {
        printStatus()

        while(nextTurn == false)
        {
            GameInput()

            ArmiesReport()
        }

        nextTurn = false

        battleCheck()
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