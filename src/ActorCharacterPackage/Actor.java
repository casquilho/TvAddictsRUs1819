/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.util.Iterator;
import java.util.Map;

public interface Actor {

    String getName();

    Iterator<String> StarsOn();

    void addShowName(String showName);

    int getNumberOfroles();

    void incNumberOfRoles();

    void setNumOfRomRelByShow(String showName);

    int getNumberOfRomanticShows();

    int getTotalRomanticNum();

    Iterator<Map.Entry<String, Integer>> getNumOfRomRelByShowIt();

}
