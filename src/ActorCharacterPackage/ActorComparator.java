/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.util.Comparator;

/*
 * the goal is to compare and sort an ArrayList by different criteria
 *
 */
public class ActorComparator implements Comparator<Actor> {

    public ActorComparator(){
        super();
    }

    @Override
    public int compare(Actor m, Actor n) throws ClassCastException{

        int relation = n.getTotalRomanticNum()-m.getTotalRomanticNum();

        if(relation == 0)
            relation = m.getNumberOfroles()-n.getNumberOfroles();
        if(relation == 0)
            relation = n.getNumberOfRomanticShows() -m.getNumberOfRomanticShows();
        if(relation == 0)
            return m.getName().compareTo(n.getName());

        return relation;
    }
}
