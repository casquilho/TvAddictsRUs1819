/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package CGICompaniesPackage;

/**
 * The CGIcompany interface.
 */
public interface CGICompany {

    /**
     * Gets the name of the company.
     *
     * @return companyName
     */
    String getName();

    /**
     * Gets the company's total revenue from their cgi characters.
     *
     * @return profit
     */
    int getProfit();

    /**
     * Gets number characters produced by the company.
     *
     * @return numberOfCharacters
     */
    int getNumberChars();

    /**
     * Add a new character to the company.
     *
     * @param value the cost per season of the character
     */
    void addCharacter(int value);

    /**
     * Increment the company's total revenue from their cgi characters by value.
     *
     * @param value the number to add
     */
    void addProfit(int value);


}
