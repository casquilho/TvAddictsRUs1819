package TestsPackage;

import EpisodePackage.Episode;
import ShowPediaPackage.ShowPedia;
import ShowPediaPackage.ShowPediaClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class EpisodeTest {
    @Test
    public void characterParticipationValid() {
        try {
            /*arrange*/
            ShowPedia showPedia = new ShowPediaClass();
            showPedia.addShow("firstShow");
            showPedia.switchShow("firstShow");
            showPedia.addRealCharacter("realCharName1", "realActorName1", 20);
            showPedia.addSeasonToCurrentShow();
            showPedia.addEpisodeToGivenSeason(1,"theFirstEpisode");
            showPedia.addQuote(1, 1, "realCharName1", "realChar1 episode1 quote text");
            /*act*/
            Episode firstEpisode = showPedia.getCurrentShow().getSeason(1,1,1).getEpisode(0);
            boolean participates = firstEpisode.participatesIn("realCharName1");
            /*assert*/
            assertTrue(participates);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void characterParticipationInvalid() {
        try {
            /*arrange*/
            ShowPedia showPedia = new ShowPediaClass();
            showPedia.addShow("otherShow");
            showPedia.switchShow("otherShow");
            showPedia.addRealCharacter("realCharName2", "realActorName2", 20);
            showPedia.addSeasonToCurrentShow();
            showPedia.addEpisodeToGivenSeason(1,"theFirstEpisode");
            // if the character participates in an event or quote, then it is considered to actively participate in the show
            //by running the exact same sequence from the previous test but without adding a quote, the test fails
            // showPedia.addQuote(1, 1, "realCharName1", "realChar1 episode1 quote text");
            /*act*/
            Episode firstEpisode = showPedia.getCurrentShow().getSeason(1,1,1).getEpisode(0);
            boolean participates = firstEpisode.participatesIn("realCharName2");
            /*assert*/
            assertTrue(participates);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
