/**
 * Created by Shizuoko on 01.03.2017.
 */

fun battleCheck()
{

    if (ArmyList.size > 1)
    {
        for (Iterator in 0..ArmyList.size - 1)
        {
            if (ArmyList[Iterator].x == ArmyList[Iterator + 1].x && ArmyList[Iterator].y == ArmyList[Iterator + 1].y) //check if 2 armis are in the same province
            {
                if(Countries[ArmyList[Iterator].armyControllerID].inWarWith.size != 0) //check if there are any wars declared for the first army
                {
                    for (WarIterator in 0..Countries[ArmyList[Iterator].armyControllerID].inWarWith.size)
                    {
                        if(Countries[ArmyList[Iterator].armyControllerID].inWarWith[WarIterator] == ArmyList[Iterator + 1].armyControllerID) //check if these armies are at war
                        {
                            battle(ArmyList[Iterator].armyControllerID, ArmyList[Iterator + 1].armyControllerID)
                        }
                    }
                }
            }
        }
    }
}

fun battle(firstSide: Int, secondSide: Int)
{

}

fun printBattleResults()
{

}