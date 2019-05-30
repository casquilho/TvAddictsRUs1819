/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.util.Iterator;
import java.util.Map;

public interface Actor {

    String getName();

    int getNumberOfRomanticShows();

    int getTotalRomanticNum();

    int getNumberOfroles();

    Iterator<Map.Entry<String, Integer>> getNumOfRomRelByShowIt();

    Iterator<String> StarsOn();

    void setNumOfRomRelByShow(String showName);

    void addShowName(String showName);










}
