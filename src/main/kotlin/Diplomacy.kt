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
                }
            }
        }
    }

    for (Iterator in 0..Countries.size-1)
    {
        if (Countries[Iterator].id == searchPlayerCountryID())
        {
            Countries[Iterator].inWarWith.add(countryID)
            println("We are at war with " + searchCountryNameByID(countryID))
        }
    }
}

fun declarePeace(countryID: Int)
{

}