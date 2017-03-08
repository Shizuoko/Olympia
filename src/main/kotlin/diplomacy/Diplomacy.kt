package diplomacy

import battles.battleCheck
import countries.Countries
import countries.searchCountryNameByID
import countries.searchPlayerCountryID

/**
 * Created by Shizuoko on 01.03.2017.
 */

fun initCountryRelations()
{
    for (countryIterator in 0..Countries.size-1)
    {
        for (relationIterator in 0..Countries.size-1)
        {
            Countries[countryIterator].inPeaceWith.add(Countries[relationIterator].id)
        }
    }
}

fun declareWar(countryID: Int)
{
    for (Iterator in 0..Countries.size-1)
    {
        if (Countries[Iterator].id == searchPlayerCountryID())
        {
            for (peaceIterator in 0..Countries[Iterator].inPeaceWith.size-1)
            {
                if (Countries[Iterator].inPeaceWith[peaceIterator] == countryID)
                {
                    Countries[Iterator].inPeaceWith.remove(peaceIterator)
                    break
                }
            }
            break
        }
    }

    for (Iterator in 0..Countries.size-1)
    {
        if (Countries[Iterator].id == searchPlayerCountryID())
        {
            Countries[Iterator].inWarWith.add(countryID)
            println("We are at war with " + searchCountryNameByID(countryID))
            battleCheck()
        }
    }

    for (Iterator in 0..Countries.size-1)
    {
        if (Countries[Iterator].id == countryID)
        {
            for (peaceIterator in 0..Countries[Iterator].inPeaceWith.size-1)
            {
                if (Countries[Iterator].inPeaceWith[peaceIterator] == searchPlayerCountryID())
                {
                    Countries[Iterator].inPeaceWith.remove(peaceIterator)
                    break
                }
            }
            break
        }
    }

    for (Iterator in 0..Countries.size-1)
    {
        if (Countries[Iterator].id == countryID)
        {
            Countries[Iterator].inWarWith.add(searchPlayerCountryID())
        }
    }
}

fun declarePeace(countryID: Int)
{
    //THERE IS ONLY WAR IN THE GRIM DARKNESS OF THE PAST
}