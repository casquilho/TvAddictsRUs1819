/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package CGICompaniesPackage;

public interface CGICompany {

    String getName();

    int getProfit();

    int getNumberChars();

    void addCharacter(int value);

    void addProfit(int value);


}
