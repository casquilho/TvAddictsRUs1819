package ShowPediaPackage;

import MyExceptionsPackage.*;

public interface ShowPedia {

    String getCurrentShow() throws NoShowSelectedExc;

    void addShow(String showName) throws ExistentShowExc;

    void swtichShow(String showName)throws UnknownShowExc;

}
