import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Created by Shizuoko on 17.02.2017.
 */

val MapSize = 40

val MapFile: String = File("assets/map.txt").readText(charset = Charsets.UTF_8).replace("\n", "").replace("\r", "")
var Map:Array<Array<Cell>> = Array(MapSize,{Array(MapSize,{Cell("T",0,0,0)})})

var Cities: Array<CityCell> = Reader.JSON.readValue(File("assets/cities.json"))

fun LoadMap()
{
    var MapFileIterator = 0

    for (Yindex: Int in 0..MapSize-1)
    {
        for (Xindex: Int in 0..MapSize-1)
        {
            val MapCharacter = MapFile.get(MapFileIterator).toString()
            val x = Xindex
            val y = Yindex
            var MovementCost: Int = 0

            when(MapCharacter)
            {
                "C"-> MovementCost = 50 //city
                "I"-> MovementCost = 70 //field
                "F"-> MovementCost = 100 //forest
                "M"-> MovementCost = 150 //mountains
            }

            Map[Yindex][Xindex] = Cell(MapCharacter,x,y,MovementCost)
            MapFileIterator++
        }
    }
}

fun LoadCities()
{
    for (Iterator in 0..Cities.size-1)
    {
        var x = Cities.get(Iterator).x
        var y = Cities.get(Iterator).y

        Map[x][y] = CityCell(Cities.get(Iterator).name,Cities.get(Iterator).id,Cities.get(Iterator).controller,Map[x][y].terrain,x,y,Map[x][y].movementCost)
    }
}

fun PrintCitiesCoords()
{
    for (Yindex: Int in 0..MapSize-1)
    {
        for (Xindex: Int in 0..MapSize-1)
        {
            if(Map[Yindex][Xindex].terrain == "C")
            {
                println("X:" + Xindex + " Y:" + Yindex)
            }
        }
    }
}

fun PrintMap()
{
    for (Yindex: Int in 0..MapSize-1)
    {
        for (Xindex: Int in 0..MapSize-1)
        {
                print(Map[Yindex][Xindex].terrain)
        }
        print("\n")
    }
}

fun PrintCityNames()
{
    for(Iterator in 0..Cities.size-1)
    {
        println(Cities.get(Iterator).name + " (" + Cities.get(Iterator).armySize + " recruits)")
    }
}

open class Cell(var terrain: String, var x: Int, var y: Int, var movementCost: Int)
{

}

class CityCell(var name: String, var id: Int, var controller: String, terrain: String, x: Int, y: Int, movementCost: Int) : Cell(terrain, x, y, movementCost)
{
    var armySize: Int = 10000
}