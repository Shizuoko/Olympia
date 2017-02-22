import java.io.File
import java.util.*
import javax.swing.*;
import java.awt.*;

/**
 * Created by Shizuoko on 17.02.2017.
 */

fun LoadMap()
{
    val Size = 40

    val MapFile: String = File("assets/map.txt").readText(charset = Charsets.UTF_8)
    var Map:Array<Array<Cell>> = Array(Size,{Array(Size,{Cell("T","C")})})

    var MapFileIterator = 0

    for (Yindex: Int in 0..Size-1)
    {
        for (Xindex: Int in 0..Size-1)
        {
            var MapCharacter = MapFile.get(MapFileIterator).toString()
            var CurrentCoordinates: String = "Yindex" + "," + "Xindex"

            Map[Yindex][Xindex] = Cell(MapCharacter,CurrentCoordinates)
            MapFileIterator++
        }
    }
}

open class Cell(var TerrainType: String, var Coordinates: String)
{

}

class CityCell(var ArmySize: Int = 10000, TerrainType: String, Coordinates: String) : Cell(TerrainType, Coordinates)
{

}