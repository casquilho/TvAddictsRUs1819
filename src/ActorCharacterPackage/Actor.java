/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

import java.util.Iterator;

public interface Actor {

    String getName();

    Iterator<String> StarsOn();

    void addShowName(String showName);

    int getNumberOfroles();

    void incNumberOfRoles();

}
