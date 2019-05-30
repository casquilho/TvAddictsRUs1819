package TestsPackage;

import EpisodePackage.Episode;
import ShowPediaPackage.ShowPedia;
import ShowPediaPackage.ShowPediaClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class EpisodeTest {


    @Test
    public void testPrintMessage() {
        try {
            /*arrange*/
            ShowPedia showPedia = new ShowPediaClass();
            showPedia.addShow("firstShow");
            showPedia.switchShow("firstShow");
            showPedia.addRealCharacter("realCharName1", "realCharActor2", 20);
            showPedia.addSeasonToCurrentShow();
            showPedia.addEpisodeToGivenSeason(1,"theFirstEpisode");
            showPedia.addQuote(1, 1, "realCharName1", "realChar1 episode1 quote text");
            /*act*/
            Episode firstEpisode = showPedia.getCurrentShow().getSeason(1,1,1).getEpisode(1);
            boolean participates = firstEpisode.participatesIn("realCharName1");
            /*assert*/
            assertTrue(participates);
        }
        catch(Exception e){

        }
    }
}
