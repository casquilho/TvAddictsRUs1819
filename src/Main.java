/**
 * @author Joao Casquilho 54440
 * @author Andre Lisboa 54393
 */
import java.util.*;

import ActorCharacterPackage.Actor;
import ActorCharacterPackage.Character;
import CGICompaniesPackage.CGICompany;
import EventPackage.Event;
import MyExceptionsPackage.*;
import SeasonPackage.Season;
import ShowPackage.Show;
import ShowPediaPackage.*;

public class Main {

    //Enumerator that define the user commands
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

    //Constants that define the output messages
    private static final String DEFAULT_COMMAND = "Unknown command. Type help to see available commands.";
    private static final String EXIT_MESSAGE = "> Bye!";
    private static final String PROMPT = "> ";
    private static final String PRINT_CURRENT_SHOW = "%s. Seasons: %d Episodes: %d";
    private static final String UNKNOWN_CATEGORY = "Unknown actor category!";
    private static final String NO_RELATION_OF_KIND = "None.";
    private static final String CGI_KING = "%s %d";
    private static final String EMPTY_STRING = "";
    private static final String COMA_SPACE = ", ";
    private static final String SEMICOLON_SPACE = "; ";

    private static final String ADD_SHOW_MESSAGE = "%s created.";
    private static final String ADD_REAL_CHAR_MESSAGE = "%s is now part of %s. This is %s role %d.";
    private static final String ADD_VIRTUAL_CHAR_MESSAGE = "%s is now part of %s. This is a virtual actor.";
    private static final String ADD_QUOTE_MESSAGE = "Quote added.";
    private static final String ADD_EVENT_MESSAGE = "Event added.";
    private static final String ADD_RELATIONSHIP_MESSAGE = "%s has now %d kids. %s has now %d parent(s).";
    private static final String ADD_ROMANCE_MESSAGE = "%s and %s are now a couple.";
    private static final String ADD_EPISODE_MESSAGE = "%s S%d, Ep%s:%s.";

    private static final String CHAR_RESUME_HEADER = "S%d EP%d:%s";
    private static final String PARENTS_HEADER = "Parents: ";
    private static final String KIDS_HEADER = "Kids: ";
    private static final String SIBLINGS_HEADER = "Siblings: ";
    private static final String ROMANCE_HEADER = "Romantic relationships: ";



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShowPedia showPedia = new ShowPediaClass();
        Command comm = getCommand(in);

        while (!comm.equals(Command.EXIT)) {
            System.out.print(PROMPT);
            switch (comm) {
                case CURRENTSHOW:           currentShow(showPedia);               break;
                case ADDSHOW:               addShow(in, showPedia);               break;
                case SWITCHTOSHOW:          switchToShow(in, showPedia);          break;
                case ADDSEASON:             addSeason(showPedia);                 break;
                case ADDEPISODE:            addEpisode(in, showPedia);            break;
                case ADDCHARACTER:          addCharacter(in, showPedia);          break;
                case ADDRELATIONSHIP:       addRelationship(in, showPedia);       break;
                case ADDROMANCE:            addRomance(in, showPedia);            break;
                case ADDEVENT:              addEvent(in, showPedia);              break;
                case ADDQUOTE:              addQuote(in, showPedia);              break;
                case SEASONSOUTLINE:        seasonsOutline(in,showPedia);         break;
                case CHARACTERRESUME:       characterResume(in, showPedia);       break;
                case HOWARETHESETWORELATED: howAreTheseTwoRelated(in, showPedia); break;
                case FAMOUSQUOTES:          famousQuotes(in, showPedia);          break;
                case ALSOAPPEARSON:         alsoAppearsOn(in, showPedia);         break;
                case MOSTROMANTIC:          mostRomantic(in, showPedia);          break;
                case KINGOFCGI:             kingOfCGI(showPedia);                 break;
                case HELP:                  help();                               break;

                default:
                    System.out.println(DEFAULT_COMMAND);
            }
            comm = getCommand(in);
        }
        System.out.println(EXIT_MESSAGE);
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


    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////COMMANDS////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

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
            System.out.println(String.format(ADD_SHOW_MESSAGE, showName));
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
            charName = in.nextLine();
            String actorName_company = in.nextLine();
            int cost = in.nextInt();in.nextLine();

            if(type.equals(Character.REAL)){

                showName = showPedia.getCurrentShow().getName();

                showPedia.addRealCharacter(charName, actorName_company, cost);
                int roleNumber = showPedia.getActor(actorName_company).getNumberOfroles();

                System.out.println(String.format(ADD_REAL_CHAR_MESSAGE, charName, showName, actorName_company, roleNumber));
            }
            else
                if(type.equals(Character.VIRTUAL)){
                    showName = showPedia.getCurrentShow().getName();

                    showPedia.addCGICharacter(charName, actorName_company, cost);
                    System.out.println(String.format(ADD_VIRTUAL_CHAR_MESSAGE, charName, showName));
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
        List<String> aux = new LinkedList<>();

        try{
            parent = in.nextLine();
            child  = in.nextLine();

            showPedia.addRelationship(parent, child, aux);
            System.out.println(String.format(ADD_RELATIONSHIP_MESSAGE, parent, showPedia.getCurrentShow().getNumChildrenFromName(parent),
                                                                        child, showPedia.getCurrentShow().getNumParentsFromName(child)));
        }
        catch (NoShowSelectedExc | DuplicateRelationshipExc e){
            System.out.println(e.getMessage());
        } catch (SameCharacterExc e) {
            System.out.println(String.format(e.getMessage(), parent));
        } catch (UnknownCharacterExc e) {
            System.out.println(String.format(e.getMessage(), aux.get(0)));
        }
    }

    private static void addRomance(Scanner in, ShowPedia showPedia){

        String char1 = null;
        String char2  = null;
        List<String> aux = new LinkedList<>();

        try{
            char1 = in.nextLine();
            char2  = in.nextLine();

            showPedia.addRomance(char1, char2, aux);
            System.out.println(String.format(ADD_ROMANCE_MESSAGE, char1, char2));
        }
        catch (NoShowSelectedExc | DuplicateRelationshipExc e){
            System.out.println(e.getMessage());
        } catch (SameCharacterRomanceExc e) {
            System.out.println(String.format(e.getMessage(), char1));
        } catch (UnknownCharacterExc e) {
            System.out.println(String.format(e.getMessage(), aux.get(0)));
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
            System.out.println(String.format(e.getMessage(), showName, season));
        }
        catch (NonExistentEpisodeExc e){
            System.out.println(String.format(e.getMessage(), showName, season, episode));
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(e.getMessage(), aux.get(0)));
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
            System.out.println(String.format(e.getMessage(), showName, season));
        }
        catch (NonExistentEpisodeExc e){
            System.out.println(String.format(e.getMessage(), showName, season, episode));
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(e.getMessage(), charName));
        }
    }

    private static void seasonsOutline(Scanner in, ShowPedia showPedia){
        int seasonStart;
        int seasonEnd;
        Iterator<Event> it;
        Show currentShow = null;
        Season currentSeason = null;

        try{
            seasonStart = in.nextInt();
            seasonEnd = in.nextInt();in.nextLine();
            currentShow = showPedia.getCurrentShow();
            currentSeason = currentShow.getSeason(1, seasonStart, seasonEnd);

            System.out.println(currentShow.getName());

            for(int i = seasonStart; i < seasonEnd+1; i++){
                currentSeason = currentShow.getSeason(i, seasonStart, seasonEnd);

                for(int j = 1; j < currentSeason.getEpisodesNumber()+1; j++){
                    String key = i+""+j;

                    System.out.println(String.format(CHAR_RESUME_HEADER, i, j, currentShow.getEpisodeName(i-1, j-1)));

                    if((it = currentShow.getEventsFromEpisode(key)) != null){

                        while (it.hasNext()){
                            System.out.println(it.next().getEvent());
                        }
                    }
                }
            }
        }
        catch (NoShowSelectedExc | InvalidSeasonInterval e){
            System.out.println(e.getMessage());
        } catch (NonExistentEpisodeExc | NonExistentSeasonExc e) {
            e.printStackTrace();
        }
    }

    private static void characterResume(Scanner in, ShowPedia showPedia){
        boolean flag = true;                                        //used to control the printing of "," in the family information
        Event eventAux;
        Character auxChar;
        String charName = null;

        Iterator<Event> eventIt;                                    //iterates over the events of one episode
        Iterator<List<Event>> episodeIt;                            //iterates over the list of episodes where the given character participates in an event
        List<Iterator<Character>> auxList = new ArrayList<>();      //list used to pass the iterators for the family information the index is given by the number of 'i' in the for loop -1
        List<String> header = new ArrayList<>();                    //list used to store the headers for the family information the index is given by the number of 'i' in the for loop -1
        List<Event> episode;

        try {
            charName = in.nextLine();
            episodeIt = showPedia.characterResume(charName, auxList);

            header.add(0, PARENTS_HEADER);
            header.add(1, KIDS_HEADER);
            header.add(2, SIBLINGS_HEADER);
            header.add(3, ROMANCE_HEADER);

            //prints the character's family information
            for (int i = 1; i < 5; i++) {
                //parentsIt  = auxList.get(0); childrenIt = auxList.get(1);
                //siblingsIt = auxList.get(2); romanceIt  = auxList.get(3);
                Iterator<Character> it = auxList.get(i - 1);
                System.out.print(header.get(i - 1));

                if (!it.hasNext())
                    System.out.print(NO_RELATION_OF_KIND);
                else {
                    while (it.hasNext()) {
                        if (!flag)
                            System.out.print(COMA_SPACE);

                        flag = false;
                        auxChar = it.next();
                        System.out.print(auxChar.getCharName());
                    }
                }
                flag = true;
                System.out.println();
            }
            //prints the events in which the character participated in each episode
            while (episodeIt.hasNext()) {
                episode = episodeIt.next();
                eventIt = episode.iterator();
                while (eventIt.hasNext()) {
                    eventAux = eventIt.next();
                    if (flag) {
                        System.out.println(String.format(CHAR_RESUME_HEADER, eventAux.getSeason(), eventAux.getEpisode(), showPedia.getEpisodeName(eventAux.getSeason(), eventAux.getEpisode())));
                        flag = false;
                    }
                    System.out.println(eventAux.getEvent());
                }
                flag = true;
            }

        }
        catch (NonExistentEpisodeExc | NonExistentSeasonExc e){
            e.printStackTrace();
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format(e.getMessage(), charName));
        }
    }

    private static void howAreTheseTwoRelated(Scanner in, ShowPedia showPedia){

        String char1  = null;
        String char2  = null;
        boolean flag  = false;
        List<String> aux = new LinkedList<>();

        try{
            char1  = in.nextLine();
            char2  = in.nextLine();

            //Used a BFS algorithm to find the path between the character and return the
            //output to a stack so it can be printed in the right order by using pop
            Stack<Character> stack = showPedia.howAreTheseTwoRelated(char1, char2, aux);

            while(!stack.empty()){
                if(flag)
                    System.out.print(SEMICOLON_SPACE);
                flag = true;
                System.out.print(stack.pop().getCharName());
            }
            System.out.println(stack.pop().getCharName());
        }
        catch (NoShowSelectedExc | NotRelatedExc | DuplicateCharRelated e){
            System.out.println(e.getMessage());
        } catch (UnknownCharacterExc e) {
            System.out.println(String.format(e.getMessage(), aux.get(0)));
        }
    }

    private static void famousQuotes(Scanner in, ShowPedia showPedia){
        try{
            String quote = in.nextLine();
            String toPrint = EMPTY_STRING;
            Iterator<String> it = showPedia.getFamousQuotes(quote);
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
            Iterator<String> it = showPedia.alsoAppearsOn(charName);
            if(it != null)
                while(it.hasNext()){
                    System.out.println(it.next());
                }
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
        catch (UnknownCharacterExc | NotRealCharacterExc e){
            System.out.println(String.format(e.getMessage(), charName));
        }
    }

    private static void mostRomantic(Scanner in, ShowPedia showPedia){
        String actorName = null;
        try {
            actorName = in.nextLine();

            Actor auxActor;
            Iterator<Actor> it = showPedia.mostRomantic(actorName);
            while(it.hasNext()) {
                auxActor = it.next();
                if(auxActor.getName().compareTo(actorName)==0) {
                    System.out.println(String.format("%s %d", auxActor.getName(), auxActor.getTotalRomanticNum()));
                    break;
                }
                System.out.println(String.format("%s %d", auxActor.getName(), auxActor.getTotalRomanticNum()));
            }
        }
        catch(UnknownActorExc e) {
            System.out.println(String.format(e.getMessage(), actorName));
        }
        catch(LoveIsntInTheAirExc e) {
            System.out.println(e.getMessage());
        }
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
        System.out.println(  "currentShow - show the current show\n"
                            +"addShow - add a new show\n"
                            +"switchToShow - change the context to a particular show\n"
                            +"addSeason - add a new season to the current show\n"
                            +"addEpisode - add a new episode to a particular season of the current show\n"
                            +"addCharacter - add a new character to a show\n"
                            +"addRelationship - add a family relationship between characters\n"
                            +"addRomance - add a romantic relationship between characters\n"
                            +"addEvent - add a significant event involving at least one character\n"
                            +"addQuote - add a new quote to a character\n"
                            +"seasonsOutline - outline the contents of the selected seasons for the selected show\n"
                            +"characterResume - outline the main information on a specific character\n"
                            +"howAreTheseTwoRelated - find out if and how two characters may be related\n"
                            +"famousQuotes - find out which character(s) said a particular quote\n"
                            +"alsoAppearsOn - which other shows and roles is the same actor on?\n"
                            +"mostRomantic - find out who is at least as romantic as X\n"
                            +"kingOfCGI - find out which company has earned more revenue with their CGI virtual actors\n"
                            +"help - shows the available commands\n"
                            +"exit - terminates the execution of the program");
    }


    ///////////////////////////////////////////////////////////////////////
    ///////////////////////Auxiliary Methods///////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    private static void printCurrentShow(ShowPedia showPedia) throws NoShowSelectedExc{
        Show currentShow = showPedia.getCurrentShow();
        System.out.println(String.format(PRINT_CURRENT_SHOW,
                                            currentShow.getName(),
                                            currentShow.getSeasonsNumber(),
                                            currentShow.getTotalEpisodesNumber()));
    }








}