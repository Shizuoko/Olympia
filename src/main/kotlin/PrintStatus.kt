import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.File

/**
 * Created by Shizuoko on 27.02.2017.
 */


    val InterfaceFile: String = File("assets/interface.txt").readText(charset = Charsets.UTF_8).replace("\n", "").replace("\r", "")
    val height = 43
    val width = 42
    var C: Char = 1.toChar()
    var screenArray: Array<Array<Char>> = Array(height,{Array(width,{C})})

fun initMapInfo() //loading map and placing army locations on it
    {
        var InterfaceIterator = 0

        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                screenArray[Xiterator][Yiterator] = InterfaceFile.get(InterfaceIterator)
                InterfaceIterator++
            }
        }

        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                if (Xiterator>0&&Xiterator<40&&Yiterator>0&&Yiterator<40)
                {
                    screenArray[Xiterator][Yiterator] = Map[Xiterator-1][Yiterator-1].terrain.toCharArray().get(0)
                }
            }
        }

        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                if (Xiterator>0&&Xiterator<40&&Yiterator>0&&Yiterator<40)
                {
                    if(ArmyList.size != 0)
                    {
                        for (ArmyIterator in 0..ArmyList.size - 1)
                        {
                            screenArray[ArmyList[ArmyIterator].x+1][ArmyList[ArmyIterator].y+1] = "A".toCharArray().get(0)
                        }
                    }
                }
            }
        }
    }

fun updateMapInfo() //updating army locations
{
    for (Xiterator in 0..height-1)
    {
        for(Yiterator in 0..width-1)
        {
            if (Xiterator>0&&Xiterator<40&&Yiterator>0&&Yiterator<40)
            {
                if(ArmyList.size != 0)
                {
                    for (ArmyIterator in 0..ArmyList.size - 1)
                    {
                        screenArray[ArmyList[ArmyIterator].y+1][ArmyList[ArmyIterator].x+1] = "A".toCharArray().get(0)
                    }
                }
            }
        }
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

fun PrintPlayerCities()
{
    for(Iterator in 0..Cities.size-1)
    {
        if(Cities.get(Iterator).controller.equals(searchPlayerCountryName()))
        {
            println(Cities.get(Iterator).name + " (" + Cities.get(Iterator).armySize + " recruits)")
        }
    }
}

fun printStatus()
    {
        updateMapInfo()

        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                print(screenArray[Xiterator][Yiterator])
            }
            print("\n")
        }

        println("Playing as: *** " + searchPlayerCountryName() + " ***")
        for (Iterator in 0..Countries.size-1)
        {
            if (Countries[Iterator].player == true) {
                println("We have " + Countries[Iterator].cash + " gold in our treasury")
                break
            }
        }
    }
