import java.io.File

/**
 * Created by Shizuoko on 17.02.2017.
 */

val MapSize = 40

val MapFile: String = File("assets/map.txt").readText(charset = Charsets.UTF_8)
var Map:Array<Array<Cell>> = Array(MapSize,{Array(MapSize,{Cell("T","C")})})

fun LoadMap()
{
    var MapFileIterator = 0

    for (Xindex: Int in 0..MapSize-1)
    {
        for (Yindex: Int in 0..MapSize-1)
        {
            var MapCharacter = MapFile.get(MapFileIterator).toString()
            var CurrentCoordinates: String = "Xindex" + "," + "Yindex"

            Map[Xindex][Yindex] = Cell(MapCharacter,CurrentCoordinates)
            /*if(Map[Xindex][Yindex].TerrainType == "C") //we'll probably need some exceptions here in the future
            {
                Map[Xindex][Yindex] = CityCell()
            }*/
            MapFileIterator++
        }
    }
}

fun ShowCitiesCoords()
{
    for (Xindex: Int in 0..MapSize-1)
    {
        for (Yindex: Int in 0..MapSize-1)
        {
            if(Map[Xindex][Yindex].TerrainType == "C")
            {
                println("X:" + Xindex + " Y:" + Yindex)
            }
        }
    }
}

open class Cell(var TerrainType: String, var Coordinates: String)
{

}

class CityCell(var CityName: String, var id: Int, var Controller: String, var ArmySize: Int = 10000, TerrainType: String, Coordinates: String) : Cell(TerrainType, Coordinates)
{

}