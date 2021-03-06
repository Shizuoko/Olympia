package countries

import com.fasterxml.jackson.module.kotlin.readValue
import gameInput.ChooseCountry
import reader.Reader
import java.io.File

/**
 * Created by Shizuoko on 28.02.2017.
 */

var Countries: Array<Country> = Reader.JSON.readValue(File("assets/countries.json"))

fun printCountries()
{
    for (Iterator in 0..Countries.size-1)
    {
        println("" + Countries[Iterator].id + ". " + Countries[Iterator].name)
    }
}

fun searchStartingCountry(id: Int)
{
    var searchResult = 0

    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].id == id)
        {
            Countries[Iterator].player = true
            searchResult++
            break
        }
    }
    if (searchResult == 0)
    {
        println("There is no country with specified ID")

        ChooseCountry()
    }
}

fun searchCountryNameByID(id: Int): String
{
    var countryName = "There is no country with specified IDy"

    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].id == id)
        {
            countryName = Countries[Iterator].name
            break
        }
    }

    return countryName
}

fun searchCountryIDByName(name: String): Int
{
    var countryID = -1

    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].name.toLowerCase().equals(name.toLowerCase()))
        {
            countryID = Countries[Iterator].id
            break
        }
    }

    return countryID
}

fun searchPlayerCountryID(): Int
{
    var playerID: Int = 0
    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].player == true)
        {
            playerID = Countries[Iterator].id
            break
        }
    }

    return playerID
}

fun searchPlayerCountryName(): String
{
    var playerCountryName: String = ""
    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].player == true)
        {
            playerCountryName = Countries[Iterator].name
            break
        }
    }

    return playerCountryName
}

fun changePlayerCountry(newCountry: String)
{
    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].player == true)
        {
            Countries[Iterator].player = false
            break
        }
    }

    var searchResult = 0

    for (Iterator in 0..Countries.size-1)
    {
        if(Countries[Iterator].name.toLowerCase().equals(newCountry))
        {
            Countries[Iterator].player = true
            println("Playing as: " + Countries[Iterator].name)
            searchResult++
            break
        }
    }

    if (searchResult == 0)
    {
        println("There is no such country!")
    }
}

class Country(var name: String,
              var id: Int,
              var player: Boolean)
{
    var cash: Int = 100
    var inPeaceWith: MutableList<Int> = arrayListOf()
    var inWarWith: MutableList<Int> = arrayListOf()
}