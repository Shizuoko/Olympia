package battles

import armies.ArmyList
import countries.Countries
import countries.searchCountryNameByID
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

var report_every_attack: Boolean = true

var BattleMap:Array<Array<battleCell>> = Array(battleHeight,{Array(battleWidth,{ battleCell(0,0,0,0,"") })})

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

    var cellDamage: Int = 0
    var totalDamage1: Int = 0
    var totalDamage2: Int = 0

    loadBattleMap(firstSide, secondSide)

    while(ArmyList[firstSide].morale > 0 && ArmyList[secondSide].morale > 0)
    {
        for (Yindex: Int in 0..battleWidth - 1)
        {
            for (Xindex: Int in 0..battleHeight - 1)
            {
                if (BattleMap[Xindex][Yindex].squadSize > 0)
                {
                    if(BattleMap[Xindex][Yindex].side == 1)
                    {
                        cellDamage = BattleMap[Xindex][Yindex].atack()
                        totalDamage2 = totalDamage2 + cellDamage
                    }
                    else
                    {
                        cellDamage = BattleMap[Xindex][Yindex].atack()
                        totalDamage1 = totalDamage1 + cellDamage
                    }
                }
            }
        }

        var moraleDamage1 = (Math.random() * ((ArmyList[secondSide].totalSize*2)-(ArmyList[secondSide].totalSize/2)+1) / 1000).toInt()
        ArmyList[firstSide].morale -= moraleDamage1

        var moraleDamage2 = (Math.random() * ((ArmyList[firstSide].totalSize*2)-(ArmyList[firstSide].totalSize/2)+1) / 1000).toInt()
        ArmyList[firstSide].morale -= moraleDamage2

        battleRound++
        println("Battle round: " + battleRound)
    }

    println("" + searchCountryNameByID(ArmyList[firstSide].armyControllerID) + " lost: " + totalDamage1 + " soldiers, " +
            searchCountryNameByID(ArmyList[secondSide].armyControllerID) + " lost: " + totalDamage2 + " soldiers")
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
            var squad = 0

            when(Xindex)
            {
                infantryPlacement1 ->
                {
                    side = 1
                    type = "infantry"
                    if(ArmyList[firstSide].infantrySize > 0)
                    {
                        ArmyList[firstSide].infantrySize = ArmyList[firstSide].infantrySize - squadSize
                        squad = squad + squadSize
                    }
                }
                infantryPlacement2 ->
                {
                    side = 2
                    type = "infantry"
                    if(ArmyList[secondSide].infantrySize > 0)
                    {
                        ArmyList[secondSide].infantrySize = ArmyList[secondSide].infantrySize - squadSize
                        squad = squad + squadSize
                    }
                }
                archersPlacement1 ->
                {
                    side = 1
                    type = "archers"
                    if(ArmyList[firstSide].archersSize > 0)
                    {
                        ArmyList[firstSide].archersSize = ArmyList[firstSide].archersSize - squadSize
                        squad = squad + squadSize
                    }
                }
                archersPlacement2 ->
                {
                    side = 2
                    type = "archers"
                    if(ArmyList[secondSide].archersSize > 0)
                    {
                        ArmyList[secondSide].archersSize = ArmyList[secondSide].archersSize - squadSize
                        squad = squad + squadSize
                    }
                }
            }

            if (Xindex == cavalryPlacement1 && (Yindex < 3 || Yindex < battleWidth - 3))
            {
                side = 1
                type = "cavalry"
                if(ArmyList[firstSide].cavalrySize > 0)
                {
                    ArmyList[firstSide].cavalrySize = ArmyList[firstSide].cavalrySize - squadSize
                    squad = squad + squadSize
                }
            }
            if (Xindex == cavalryPlacement2 && (Yindex < 3 || Yindex < battleWidth - 3))
            {
                side = 2
                type = "cavalry"
                if(ArmyList[secondSide].cavalrySize > 0)
                {
                    ArmyList[secondSide].cavalrySize = ArmyList[secondSide].cavalrySize - squadSize
                    squad = squad + squadSize
                }
            }

            BattleMap[Xindex][Yindex] = battleCell(x, y, side, squad, type)
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
            BattleMap[Xindex][Yindex].side = 0
            BattleMap[Xindex][Yindex].squadSize = 0
            BattleMap[Xindex][Yindex].squadType = ""
        }
    }
}

class battleCell(var x: Int,
                 var y: Int,
                 var side: Int,
                 var squadSize: Int,
                 var squadType: String)
{

    fun atack(): Int
    {
        var attackDamage: Int = 0

        if (side == 1)
        {
            if(squadType.equals("archers"))
            {
                attackDamage = (Math.random() * ((archersDamage *2)-(archersDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x + 2][y].squadSize = BattleMap[x + 2][y].squadSize - attackDamage
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x + 2][y].squadType)}
            }

            if(squadType.equals("infantry"))
            {
                attackDamage = (Math.random() * ((infantryDamage *2)-(infantryDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x + 1][y].squadSize = BattleMap[x + 1][y].squadSize - attackDamage
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x + 1][y].squadType)}
            }

            if(squadType.equals("cavalry"))
            {
                attackDamage = (Math.random() * ((cavalryDamage *2)-(cavalryDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x + 1][y].squadSize = BattleMap[x + 1][y].squadSize - attackDamage
                if(y > 0)
                {
                    BattleMap[x + 1][y - 1].squadSize = BattleMap[x + 1][y - 1].squadSize - (attackDamage/2)
                }
                if(y < battleWidth - 1)
                {
                    BattleMap[x + 1][y + 1].squadSize = BattleMap[x + 1][y + 1].squadSize - (attackDamage/2)
                }
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage * 2 + "of the enemy soldiers")}
            }
        }

        if (side == 2)
        {
            if(squadType.equals("archers"))
            {
                attackDamage = (Math.random() * ((archersDamage *2)-(archersDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x - 2][y].squadSize = BattleMap[x - 2][y].squadSize - attackDamage
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x - 2][y].squadType)}
            }

            if(squadType.equals("infantry"))
            {
                attackDamage = (Math.random() * ((infantryDamage *2)-(infantryDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x - 1][y].squadSize = BattleMap[x - 1][y].squadSize - attackDamage
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage + "of enemy " + BattleMap[x - 1][y].squadType)}
            }

            if(squadType.equals("cavalry"))
            {
                attackDamage = (Math.random() * ((cavalryDamage *2)-(cavalryDamage /2)+1) * squadSize/5).toInt()
                BattleMap[x - 1][y].squadSize = BattleMap[x - 1][y].squadSize - attackDamage
                if(y > 0)
                {
                    BattleMap[x - 1][y - 1].squadSize = BattleMap[x - 1][y - 1].squadSize - (attackDamage/2)
                }
                if(y < battleWidth - 1)
                {
                    BattleMap[x - 1][y + 1].squadSize = BattleMap[x - 1][y + 1].squadSize - (attackDamage/2)
                }
                if (report_every_attack == true){println("" + squadType + " killed " + attackDamage * 2 + "of the enemy soldiers")}
            }
        }

        return attackDamage

    }

}