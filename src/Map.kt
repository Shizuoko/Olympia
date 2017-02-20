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
    var Map = Array(Size, { IntArray(Size) })

    var MapFileIterator = 0

    for (Yindex: Int in 0..39)
    {
        for (Xindex: Int in 0..39)
        {
            var MapCharacter = MapFile.get(MapFileIterator).toInt()
            Map[Yindex][Xindex] = MapCharacter
            MapFileIterator++
        }
    }
}