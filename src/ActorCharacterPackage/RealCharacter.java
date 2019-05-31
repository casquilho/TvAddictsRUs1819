/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
package ActorCharacterPackage;

/**
 * The RealCharacter interface.
 */
public interface RealCharacter {

    /**
     * Gets character's name.
     *
     * @return characterName
     */
    String getCharName();

    /**
     * Gets the name of the actor who plays the character.
     *
     * @return actorName
     */
    String getActorName();

    /**
     * Gets the actor who plays the character.
     *
     * @return actor
     */
    Actor  getActor();

    /**
     * Add profit from appearance from the character's cost per episode.
     * This feature is not used for the project but it was implemented
     * anyway because it was required to do roughly the same for the
     * CGIcharacter and so it was done for this too.
     */
    void addProfitFromAppearance();
}
