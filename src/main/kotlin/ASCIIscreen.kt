import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.File

/**
 * Created by Shizuoko on 27.02.2017.
 */


    val InterfaceFile: String = File("assets/interface.txt").readText(charset = Charsets.UTF_8).replace("\n", "").replace("\r", "")
    val height = 43
    val width = 57
    var C: Char = 1.toChar()
    var screenArray: Array<Array<Char>> = Array(height,{Array(width,{C})})

fun initInterface()
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

        var armiesString:String = ""
        if (ArmyList.size > 0)
        {
            for (Iterator in 0..ArmyList.size - 1)
            {
                armiesString.plus(ArmyList[Iterator].armyName + " (" + ArmyList[Iterator].armySize + ")" + "\n")
            }
        }
        else{armiesString = "No armies"}

        var armiesStringIterator = 0

        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                if (Xiterator>1&&Xiterator<42&&Yiterator>43&&Yiterator<56&&armiesStringIterator<armiesString.length)
                {
                    screenArray[Xiterator][Yiterator] = armiesString.toCharArray().get(armiesStringIterator)
                    armiesStringIterator++
                }
            }
        }
    }

fun updateInterface()
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

    var armiesString:String = ""
    if (ArmyList.size > 0)
    {
        for (Iterator in 0..ArmyList.size - 1)
        {
            armiesString.plus("ArmyList[Iterator].armyName (ArmyList[Iterator].armySize)\n")
            print(armiesString)
        }
    } else
    {
        armiesString = "No armies"
    }

    var armiesStringIterator = 0

    for (Xiterator in 0..height-1)
    {
        for(Yiterator in 0..width-1)
        {
            if (Xiterator>1&&Xiterator<42&&Yiterator>43&&Yiterator<56&&armiesStringIterator<armiesString.length)
            {
                screenArray[Xiterator][Yiterator] = armiesString.toCharArray().get(armiesStringIterator)
                armiesStringIterator++
            }
        }
    }
}

fun printInterface()
    {
        updateInterface()
        for (Xiterator in 0..height-1)
        {
            for(Yiterator in 0..width-1)
            {
                print(screenArray[Xiterator][Yiterator])
            }
            print("\n")
        }
    }
