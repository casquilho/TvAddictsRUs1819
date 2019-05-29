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




@SuppressWarnings("Duplicates")
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
    public static final String UNKNOWN_CATEGORY = "Unknown actor category!";
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
    public static final String ADD_RELATIONSHIP_MESSAGE = "%s has now %d kids. %s has now %d parent(s).";
    public static final String SAME_CHARACTER_RELATIONSHIP = "%s cannot be parent and child at the same time!";
    public static final String SAME_CHARACTER_ROMANCE = "%s cannot be in a single person romantic relationship!";
    public static final String ADD_ROMANCE_MESSAGE = "%s and %s are now a couple.";


    public static final String PARENTS_HEADER = "Parents: ";
    public static final String KIDS_HEADER = "Kids: ";
    public static final String SIBLINGS_HEADER = "Siblings:";
    public static final String ROMANCE_HEADER = "Romantic relationships: ";
    public static final String NO_RELATION_OF_KIND = "None.";



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
                case ADDRELATIONSHIP:       addRelationship(in, showPedia);       break;
                case ADDROMANCE:            addRomance(in, showPedia);            break;
                case ADDEVENT:              addEvent(in, showPedia);              break;
                case ADDQUOTE:              addQuote(in, showPedia);              break;
                case SEASONSOUTLINE:        seasonsOutline(in);        break;
                case CHARACTERRESUME:       characterResume(in, showPedia);       break;
                case HOWARETHESETWORELATED: howAreTheseTwoRelated(in, showPedia); break;
                case FAMOUSQUOTES:          famousQuotes(in, showPedia);          break;
                case ALSOAPPEARSON:         alsoAppearsOn(in, showPedia);         break;
                case MOSTROMANTIC:          mostRomantic(in);          break;
                case KINGOFCGI:             kingOfCGI(showPedia);             break;
                case HELP:                  help();                    break;

                default:
                    System.out.println("Unknown command. Type help to see available commands.");
            }
            comm = getCommand(in);
        }
        System.out.println("> Bye!");
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
        catch (NoShowSelectedExc | DuplicateCharacterExc | InvalidSalaryExc e ){
            System.out.println(e.getMessage());
        }
    }

    private static void addRelationship(Scanner in, ShowPedia showPedia){
        String parent = null;
        String child  = null;
        List<String> aux = new LinkedList<>();//ver se ha outra forma de passar o nome para a excecao

        try{
            parent = in.nextLine();
            child  = in.nextLine();

            showPedia.addRelationship(parent, child, aux);
            System.out.println(String.format(ADD_RELATIONSHIP_MESSAGE, parent, showPedia.getCurrentShow().getNumChildrenFromName(parent),
                                                                        child, showPedia.getCurrentShow().getNumParentsFromName(child)));
        }
        catch (NoShowSelectedExc | DuplicateRelationshipExc e){
            System.out.println(e.getMessage());
        } catch (SameCharacterExc sameCharacterExc) {
            System.out.println(String.format(SAME_CHARACTER_RELATIONSHIP, parent));
        } catch (UnknownCharacterExc unknownCharacterExc) {
            System.out.println(String.format(UNKNOWN_CHARACTER, aux.get(0)));
        }
    }

    private static void addRomance(Scanner in, ShowPedia showPedia){

        String char1 = null;
        String char2  = null;
        List<String> aux = new LinkedList<>();//ver se ha outra forma de passar o nome para a excecao

        try{
            char1 = in.nextLine();
            char2  = in.nextLine();

            showPedia.addRomance(char1, char2, aux);
            System.out.println(String.format(ADD_ROMANCE_MESSAGE, char1, char2));
        }
        catch (NoShowSelectedExc | DuplicateRelationshipExc e){
            System.out.println(e.getMessage());
        } catch (SameCharacterExc sameCharacterExc) {
            System.out.println(String.format(SAME_CHARACTER_ROMANCE, char1));
        } catch (UnknownCharacterExc unknownCharacterExc) {
            System.out.println(String.format(UNKNOWN_CHARACTER, aux.get(0)));
        }



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

    private static void seasonsOutline(Scanner in){//TODO

        int season = in.nextInt();
        int episode = in.nextInt();in.nextLine();





    }

    private static void characterResume(Scanner in, ShowPedia showPedia){
        boolean flag = true;
        Event eventAux;
        List episode;
        Iterator eventIt, episodeIt;
        List<Iterator> auxList = new ArrayList<>();
        List<String> header = new ArrayList<>();
        Character auxChar;
        try{
            String charName = in.nextLine();
            showPedia.characterResume(charName, auxList);


            header.add(0, PARENTS_HEADER);
            header.add(1, KIDS_HEADER);
            header.add(2, SIBLINGS_HEADER);
            header.add(3, ROMANCE_HEADER);
            //parentsIt  = auxList.get(1); childrenIt = auxList.get(2);
            //siblingsIt = auxList.get(3); romanceIt  = auxList.get(4);
            for(int i = 1; i < 5; i++){
                Iterator it = auxList.get(i);
                System.out.print(header.get(i-1));

                if(!it.hasNext())
                    System.out.print(NO_RELATION_OF_KIND);
                else {
                    while (it.hasNext()) {
                        if(!flag)
                            System.out.print(", ");

                        flag = false;
                        auxChar = (Character) it.next();
                        System.out.print(auxChar.getCharName());
                    }
                }
                flag = true;
                System.out.println();
            }
            episodeIt  = auxList.get(0);

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

    private static void howAreTheseTwoRelated(Scanner in, ShowPedia showPedia){

        String char1 = null;
        String char2  = null;
        boolean flag = false;
        List<String> aux = new LinkedList<>();//ver se ha outra forma de passar o nome para a excecao

        try{
            char1 = in.nextLine();
            char2  = in.nextLine();

            Stack stack = showPedia.howAreTheseTwoRelated(char1, char2, aux);
            while(!stack.empty()){
                if(flag)
                    System.out.print("; ");
                flag = true;
                System.out.print(stack.pop());
            }
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        } catch (SameCharacterExc e) {
            System.out.println(String.format(SAME_CHARACTER_ROMANCE, char1));
        } catch (UnknownCharacterExc e) {
            System.out.println(String.format(UNKNOWN_CHARACTER, aux.get(0)));
        }
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
            if(it != null)
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
        catch (NotRealCharacterExc e){
            System.out.println(String.format(e.getMessage(), charName));
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
                        /*done*/    +"addEpisode - add a new episode to a particular season of the current show\n"
                        /*done*/    +"addCharacter - add a new character to a show\n"
                        /*done not tested*/    +"addRelationship - add a family relationship between characters\n"
                        /*done not tested*/ +"addRomance - add a romantic relationship between characters\n"
                        /*done*/   +"addEvent - add a significant event involving at least one character\n"
                        /*done*/    +"addQuote - add a new quote to a character\n"
                        /*done not tested*/+"seasonsOutline - outline the contents of the selected seasons for the selected show\n"
                        /*done*/+"characterResume - outline the main information on a specific character\n"
                        /*done not tested*/+"howAreTheseTwoRelated - find out if and how two characters may be related\n"
                        /*done*/    +"famousQuotes - find out which character(s) said a particular quote\n"
                        /*done*/    +"alsoAppearsOn - which other shows and roles is the same actor on?\n"
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