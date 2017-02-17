import java.io.File

/**
 * Created by Shizuoko on 17.02.2017.
 */
fun LoadMap()
{
    val Map: String = File("assets/map.txt").readText(charset = Charsets.UTF_8)
}