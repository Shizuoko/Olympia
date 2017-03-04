import java.io.File

/**
 * Created by Shizuoko on 01.03.2017.
 */

val battleHeight = 4
val battleWidth = 20
val squadSize = 500

val infantryPlacement1= 1
val infantryPlacement2 = 2

val cavalryPlacement1 = 1
val cavalryPlacement2 = 2

val archersPlacement1 = 0
val archersPlacement2 = 3

val archersDamage = 0.5F
val cavalryDamage = 2.0F
val infantryDamage = 1.0F

var report_every_atack: Boolean = true

var BattleMap:Array<Array<battleCell>> = Array(battleHeight,{Array(battleWidth,{battleCell(0,0,0,"")})})

fun battleCheck()
{
    if (ArmyList.size > 1)
    {
        for (Iterator in 0..ArmyList.size - 2)
        {
            if (ArmyList[Iterator].x == ArmyList[Iterator + 1].x && ArmyList[Iterator].y == ArmyList[Iterator + 1].y) //check if 2 armies are in the same province
            {
                if(Countries[ArmyList[Iterator].armyControllerID].inWarWith.size != 0) //check if there are any wars declared for the first army
                {
                    for (WarIterator in 0..Countries[ArmyList[Iterator].armyControllerID].inWarWith.size-1)
                    {

                        if(Countries[ArmyList[Iterator].armyControllerID].inWarWith[WarIterator] == ArmyList[Iterator + 1].armyControllerID) //check if these armies are at war
                        {
                            battle(Iterator, Iterator + 1)
                            println(Iterator)
                            println(Iterator + 1)
                        }
                    }
                }
            }
        }
    }
}

fun battle(firstSide: Int, secondSide: Int)
{
    var battleRound = 0

    loadBattleMap(firstSide, secondSide)

    while(ArmyList[firstSide].morale > 0 && ArmyList[secondSide].morale > 0)
    {
        for (Yindex: Int in 0..battleWidth - 1)
        {
            for (Xindex: Int in 0..battleHeight - 1)
            {
                BattleMap[Xindex][Yindex].atack()
            }
        }

        var moraleDamage1 = (Math.random() * ((ArmyList[secondSide].totalSize*2)-(ArmyList[secondSide].totalSize/2)+1) / 1000).toInt()
        ArmyList[firstSide].morale -= moraleDamage1

        var moraleDamage2 = (Math.random() * ((ArmyList[firstSide].totalSize*2)-(ArmyList[firstSide].totalSize/2)+1) / 1000).toInt()
        ArmyList[firstSide].morale -= moraleDamage2

        battleRound++
        println("Battle round: " + battleRound)
    }

    clearBattleMap()

}

fun loadBattleMap(firstSide: Int, secondSide: Int)
{
    var MapFileIterator = 0

    for (Yindex: Int in 0..battleWidth - 1) {
        for (Xindex: Int in 0..battleHeight - 1) {
            val x = Xindex
            val y = Yindex
            var side = 0
            var type: String = ""

            when(Xindex)
            {
                infantryPlacement1 ->
                {
                    side = 1
                    type = "infantry"
                    if(ArmyList[firstSide].infantrySize > 0)
                    {
                        ArmyList[firstSide].infantrySize -= squadSize
                        BattleMap[Xindex][Yindex].squadSize += squadSize
                    }
                }
                infantryPlacement2 ->
                {
                    side = 2
                    type = "infantry"
                    if(ArmyList[secondSide].infantrySize > 0)
                    {
                        ArmyList[secondSide].infantrySize -= squadSize
                        BattleMap[Xindex][Yindex].squadSize += squadSize
                    }
                }
                archersPlacement1 ->
                {
                    side = 1
                    type = "archers"
                    if(ArmyList[firstSide].archersSize > 0)
                    {
                        ArmyList[firstSide].archersSize -= squadSize
                        BattleMap[Xindex][Yindex].squadSize += squadSize
                    }
                }
                archersPlacement2 ->
                {
                    side = 2
                    type = "archers"
                    if(ArmyList[secondSide].archersSize > 0)
                    {
                        ArmyList[secondSide].archersSize -= squadSize
                        BattleMap[Xindex][Yindex].squadSize += squadSize
                    }
                }
            }

            if (Xindex == cavalryPlacement1 && (Yindex < 3 || Yindex < battleWidth - 3))
            {
                side = 1
                type = "cavalry"
                if(ArmyList[firstSide].cavalrySize > 0)
                {
                    println("Yindex " + Yindex + "Xindex " + Xindex)
                    ArmyList[firstSide].cavalrySize -= squadSize
                    BattleMap[Xindex][Yindex].squadSize += squadSize
                }
            }
            if (Xindex == cavalryPlacement2 && (Yindex < 3 || Yindex < battleWidth - 3))
            {
                side = 2
                type = "cavalry"
                if(ArmyList[secondSide].cavalrySize > 0)
                {
                    println("Yindex " + Yindex + "Xindex " + Xindex)
                    ArmyList[secondSide].cavalrySize -= squadSize
                    BattleMap[Xindex][Yindex].squadSize += squadSize
                }
            }

            BattleMap[Xindex][Yindex] = battleCell(x, y, side, type)
            MapFileIterator++
        }
    }
}

fun clearBattleMap()
{
    for (Yindex: Int in 0..battleWidth - 1)
    {
        for (Xindex: Int in 0..battleHeight - 1)
        {
            BattleMap[Yindex][Xindex].side = 0
            BattleMap[Yindex][Xindex].squadSize = 0
            BattleMap[Yindex][Xindex].squadType = ""
        }
    }
}

fun printBattleResults()
{

}

class battleCell(var x: Int,
                 var y: Int,
                 var side: Int,
                 var squadType: String)
{
    var squadSize = 0

    fun atack()
    {
        if (side == 1)
        {
            if(squadType.equals("archers"))
            {
                var attackDamage = (Math.random() * ((archersDamage*2)-(archersDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y + 2].squadSize -= attackDamage
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x][y + 2].squadType)}
            }

            if(squadType.equals("infantry"))
            {
                var attackDamage = (Math.random() * ((infantryDamage*2)-(infantryDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y + 1].squadSize -= attackDamage
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x][y + 2].squadType)}
            }

            if(squadType.equals("cavalry"))
            {
                var attackDamage = (Math.random() * ((cavalryDamage*2)-(cavalryDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y + 1].squadSize -= attackDamage
                if(x != 0){BattleMap[x - 1][y + 1].squadSize -= attackDamage/2}
                if(x != BattleMap.size - 1){BattleMap[x + 1][y + 1].squadSize -= attackDamage/2}
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage * 2 + "of the enemy soldiers")}
            }
        }

        if (side == 2)
        {
            if(squadType.equals("archers"))
            {
                var attackDamage = (Math.random() * ((archersDamage*2)-(archersDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y - 2].squadSize -= attackDamage
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x][y - 2].squadType)}
            }

            if(squadType.equals("infantry"))
            {
                var attackDamage = (Math.random() * ((infantryDamage*2)-(infantryDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y - 1].squadSize -= attackDamage
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x][y - 2].squadType)}
            }

            if(squadType.equals("cavalry"))
            {
                var attackDamage = (Math.random() * ((cavalryDamage*2)-(cavalryDamage/2)+1) * squadSize/5).toInt()
                BattleMap[x][y - 1].squadSize -= attackDamage
                if(x != 0){BattleMap[x + 1][y - 1].squadSize -= attackDamage/2}
                if(x != BattleMap.size - 1){BattleMap[x - 1][y - 1].squadSize -= attackDamage/2}
                if (report_every_atack == true){println("Our " + squadType + " killed " + attackDamage * 2 + "of the enemy soldiers")}
            }
        }
    }

}