/**
 * @author Joao Casquilho 54440
 */
import java.util.*;

import ActorCharacterPackage.Character;
import CGICompaniesPackage.CGICompany;
import EventPackage.Event;
import MyExceptionsPackage.*;
import ShowPackage.Show;
import ShowPediaPackage.*;
import java.lang.StringBuilder;




public class Main {

    //Enumerator that defines the user commands
    private enum Command {
        CURRENTSHOW,
        ADDSHOW,
        SWITCHTOSHOW,
        ADDSEASON,
        ADDEPISODE,
        ADDCHARACTER,
        ADDRELATIONSHIP,
        ADDROMANCE,
        ADDEVENT,
        ADDQUOTE,
        SEASONSOUTLINE,
        CHARACTERRESUME,
        HOWARETHESETWORELATED,
        FAMOUSQUOTES,
        ALSOAPPEARSON,
        MOSTROMANTIC,
        KINGOFCGI,
        HELP,
        UNKNOWN,
        EXIT
    }

    //Constantes que definem as mensagens para o utilizador
    public static final String CGI_KING = "%s %d";
    public static final String ADD_EPISODE_MESSAGE = "%s S%d, Ep%s:%s.";
    public static final String UNKNOWN_CATEGORY = "Unknown character category!";
    public static final String ADDSHOW = "%s created.";
    public static final String PRINT_CURRENT_SHOW = "%s. Seasons: %d Episodes: %d";
    public static final String UNKNOWN_CHARACTER = "Who is %s?";
    public static final String CHAR_RESUME_HEADER = "S%d Ep%d:";
    public static final String NONEXISTENT_EPISODE = "%s S%d does not have episode %d!";
    public static final String NONEXISTENT_SEASON = "%s does not have season %d!";
    public static final String EMPTY_STRING = "";
    public static final String COMA_SPACE = ", ";
    public static final String REAL_CHAR_CREATION = "%s is now part of %s. This is %s role %d.";
    public static final String VIRTUAL_CHAR_CREATION = "%s is now part of %s. This is a virtual actor.";
    public static final String ADD_QUOTE_MESSAGE = "Quote added.";
    public static final String ADD_EVENT_MESSAGE = "Event added.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShowPedia showPedia = new ShowPediaClass();
        Command comm = getCommand(in);

        while (!comm.equals(Command.EXIT)) {
            System.out.print("> ");
            switch (comm) {
                case CURRENTSHOW:           currentShow(showPedia);    break;
                case ADDSHOW:               addShow(in, showPedia);    break;
                case SWITCHTOSHOW:          switchToShow(in, showPedia);          break;
                case ADDSEASON:             addSeason(showPedia);             break;
                case ADDEPISODE:            addEpisode(in, showPedia);            break;
                case ADDCHARACTER:          addCharacter(in, showPedia);          break;
                case ADDRELATIONSHIP:       addRelationship(in);       break;
                case ADDROMANCE:            addRomance(in);            break;
                case ADDEVENT:              addEvent(in, showPedia);              break;
                case ADDQUOTE:              addQuote(in, showPedia);              break;
                case SEASONSOUTLINE:        seasonsOutline(in);        break;
                case CHARACTERRESUME:       characterResume(in, showPedia);       break;
                case HOWARETHESETWORELATED: howAreTheseTwoRelated(in); break;
                case FAMOUSQUOTES:          famousQuotes(in, showPedia);          break;
                case ALSOAPPEARSON:         alsoAppearsOn(in, showPedia);         break;
                case MOSTROMANTIC:          mostRomantic(in);          break;
                case KINGOFCGI:             kingOfCGI(showPedia);             break;
                case HELP:                  help();                    break;

                default:
                    System.out.println("Unknown command. Type help to see available commands.");
            }
            //System.out.println();
            comm = getCommand(in);
        }
        System.out.println("> Bye!");
        //System.out.println();
        in.close();
    }



    private static Command getCommand(Scanner in) {
        try {
            String input = in.nextLine().toUpperCase();
            return Command.valueOf(input);
        }
        catch (IllegalArgumentException e){
            return Command.UNKNOWN;
        }
    }

    private static void currentShow(ShowPedia showPedia){
        try{
            printCurrentShow(showPedia);
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addShow(Scanner in, ShowPedia showPedia){
        try{
            String showName = in.nextLine();
            showPedia.addShow(showName);
            System.out.println(String.format(ADDSHOW, showName));
        }
        catch (ExistentShowExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void switchToShow(Scanner in, ShowPedia showPedia){
        try{
            String showName = in.nextLine();
            showPedia.switchShow(showName);
            printCurrentShow(showPedia);
        }
        catch(UnknownShowExc | NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addSeason(ShowPedia showPedia){
        try{
            showPedia.addSeasonToCurrentShow();
            printCurrentShow(showPedia);
        }
        catch(NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addEpisode(Scanner in, ShowPedia showPedia){
        try{
            int seasonNumber = in.nextInt();
            String epiName = in.nextLine();
            int epiNumber = showPedia.addEpisodeToGivenSeason(seasonNumber, epiName);
            System.out.println(String.format(ADD_EPISODE_MESSAGE, showPedia.getCurrentShow().getName(), seasonNumber, epiNumber, epiName));
        }
        catch(NoShowSelectedExc | UnknownSeasonExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addCharacter(Scanner in, ShowPedia showPedia){

        String type = null;
        String charName = null;
        String showName = null;

        try{
            type = in.nextLine();

            if(type.equals(Character.REAL)){
                charName = in.nextLine();
                String actorName = in.nextLine();
                int cost = in.nextInt();in.nextLine();
                showName = showPedia.getCurrentShow().getName();

                showPedia.addRealCharacter(charName, actorName, cost);
                int roleNumber = showPedia.getActor(actorName).getNumberOfroles();

                System.out.println(String.format(REAL_CHAR_CREATION, charName, showName, actorName, roleNumber));
            }
            else
                if(type.equals(Character.VIRTUAL)){
                    charName = in.nextLine();
                    String company = in.nextLine();
                    int cost = in.nextInt();in.nextLine();
                    showName = showPedia.getCurrentShow().getName();

                    showPedia.addCGICharacter(charName, company, cost);
                    System.out.println(String.format(VIRTUAL_CHAR_CREATION, charName, showName));
                }
                else
                    System.out.println(UNKNOWN_CATEGORY);
        }
        catch (NoShowSelectedExc | DuplicateCharacterExc e ){
            System.out.println(e.getMessage());
        }
    }

    private static void addRelationship(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void addRomance(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void addEvent(Scanner in, ShowPedia showPedia){
        String eventDescription = null;
        String showName = null;
        int season  = 0;
        int episode = 0;
        int nChars  = 0;
        List<String> aux = new LinkedList<>();

        try{
            eventDescription = in.nextLine();
            season = in.nextInt();
            episode = in.nextInt();
            nChars = in.nextInt();in.nextLine();
            showName = showPedia.getCurrentShow().getName();

            for(int i = 0; i < nChars;i++){
                aux.add(in.nextLine());
            }
            showPedia.addEvent(episode, season, aux, eventDescription);
            System.out.println(ADD_EVENT_MESSAGE);
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
        catch (NonExistentSeasonExc e){
            System.out.println(String.format(NONEXISTENT_SEASON, showName, season));
        }
        catch (NonExistentEpisodeExc e){
            System.out.println(String.format(NONEXISTENT_EPISODE, showName, season, episode));
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(UNKNOWN_CHARACTER, aux.get(0)));
        }
    }

    private static void addQuote(Scanner in, ShowPedia showPedia){

        int season = 0;
        int episode = 0;
        String charName = null;
        String quoteText = null;
        String showName = null;

        try{
            season = in.nextInt();
            episode = in.nextInt();in.nextLine();
            charName = in.nextLine();
            quoteText = in.nextLine();
            showName = showPedia.getCurrentShow().getName();

            showPedia.addQuote(season, episode, charName, quoteText);
            System.out.println(ADD_QUOTE_MESSAGE);
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
        catch (NonExistentSeasonExc e){
            System.out.println(String.format(NONEXISTENT_SEASON, showName, season));
        }
        catch (NonExistentEpisodeExc e){
            System.out.println(String.format(NONEXISTENT_EPISODE, showName, season, episode));
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(UNKNOWN_CHARACTER, charName));
        }
    }

    private static void seasonsOutline(Scanner in){

        int season = in.nextInt();
        int episode = in.nextInt();in.nextLine();





    }

    private static void characterResume(Scanner in, ShowPedia showPedia){
        try{
            String charName = in.nextLine();

            boolean flag = true;
            Event eventAux;
            List episode;
            Iterator eventIt;
            Iterator episodeIt = showPedia.characterResume(charName);

            while (episodeIt.hasNext()){
                episode = (List) episodeIt.next();
                eventIt = episode.iterator();
                while (eventIt.hasNext()){
                    eventAux = (Event) eventIt.next();
                    if(flag) {
                        System.out.println(String.format(CHAR_RESUME_HEADER, eventAux.getSeason(), eventAux.getEpisode()));
                        flag = false;
                    }
                    System.out.println(eventAux.getEvent());
                }
                flag = true;
            }
        }
        catch (UnknownCharacterExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void howAreTheseTwoRelated(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void famousQuotes(Scanner in, ShowPedia showPedia){
        try{
            String quote = in.nextLine();
            String toPrint = EMPTY_STRING;
            Iterator it = showPedia.getFamousQuotes(quote);
            while (it.hasNext()){
                if(!toPrint.equals(EMPTY_STRING))
                    toPrint += COMA_SPACE;
                toPrint += it.next();
            }
            System.out.println(toPrint);
        }
        catch (NoShowSelectedExc | UnknownQuoteExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void alsoAppearsOn(Scanner in, ShowPedia showPedia){
        String charName = null;

        try{
            charName = in.nextLine();
            Iterator it = showPedia.alsoAppearsOn(charName);
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(UNKNOWN_CHARACTER, charName));
        }

    }

    private static void mostRomantic(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void kingOfCGI(ShowPedia showPedia){
        try{
            CGICompany aux = showPedia.kingOfCgi();
            System.out.println(String.format(CGI_KING, aux.getName(), aux.getProfit()));
        }
        catch (NoVirtualCharactersExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void help(){
        System.out.println(
                        /*done*/     "currentShow - show the current show\n"
                        /*done*/    +"addShow - add a new show\n"
                        /*done*/    +"switchToShow - change the context to a particular show\n"
                        /*done*/    +"addSeason - add a new season to the current show\n"
                        /*done*/   +"addEpisode - add a new episode to a particular season of the current show\n"
                        /*done*/    +"addCharacter - add a new character to a show\n"
            +"addRelationship - add a family relationship between characters\n"
            +"addRomance - add a romantic relationship between characters\n"
                        /*done*/   +"addEvent - add a significant event involving at least one character\n"
                        /*done*/    +"addQuote - add a new quote to a character\n"
            +"seasonsOutline - outline the contents of the selected seasons for a selected show\n"
                        /*done*/+"characterResume - outline the main information on a specific character\n"
            +"howAreTheseTwoRelated - find out if and how two characters may be related\n"
                        /*done*/    +"famousQuotes - find out which character(s) said a particular quote\n"
                        /*done*/    +"alsoAppearsOn - which other shows and roles is the same actor on?\n"  //solve exceptions
            +"mostRomantic - find out who is at least as romantic as X\n"
                        /*done*/    +"kingOfCGI - find out which company has earned more revenue with their CGI virtual actors\n"
                        /*done*/    +"help - shows the available commands\n"
                        /*done*/   +"exit - terminates the execution of the program");
    }

    private static void printCurrentShow(ShowPedia showPedia) throws NoShowSelectedExc{
        Show currentShow = showPedia.getCurrentShow();
        System.out.println(String.format(PRINT_CURRENT_SHOW,
                                            currentShow.getName(),
                                            currentShow.getSeasonsNumber(),
                                            currentShow.getTotalEpisodesNumber()));
    }








}