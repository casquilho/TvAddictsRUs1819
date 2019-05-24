/**
 * @author Joao Casquilho 54440
 */
import java.util.*;

import ActorCharacterPackage.Character;
import CGICompaniesPackage.CGICompany;
import MyExceptionsPackage.*;
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
    public static final String HEADER             = "data | assunto | email";
    public static final String HEADER2            = "data | assunto | email | texto";
    public static final String MESSAGE_REGISTERED = "Mensagem registada.";
    public static final String CGI_KING = "%s. %d";
    public static final String ADD_EPISODE_MESSAGE = "%s S%d, Ep%s: %s.";
    public static final String UNKNOW_CATEGORY = "Unknown character category!";



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
                case CHARACTERRESUME:       characterResume(in);       break;
                case HOWARETHESETWORELATED: howAreTheseTwoRelated(in); break;
                case FAMOUSQUOTES:          famousQuotes(in, showPedia);          break;
                case ALSOAPPEARSON:         alsoAppearsOn(in, showPedia);         break;
                case MOSTROMANTIC:          mostRomantic(in);          break;
                case KINGOFCGI:             kingOfCGI(showPedia);             break;
                case HELP:                  help();                    break;

                default:
                    System.out.println("Unknown command. Type help to see available commands.");
            }
            System.out.println();
            comm = getCommand(in);
        }
        System.out.println("Bye!");
        System.out.println();
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
            System.out.println(showPedia.printCurrentShow());
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addShow(Scanner in, ShowPedia showPedia){
        try{
            String showName = in.nextLine();
            showPedia.addShow(showName);
            System.out.println(showName + " created.");
        }
        catch (ExistentShowExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void switchToShow(Scanner in, ShowPedia showPedia){
        try{
            String showName = in.nextLine();
            showPedia.switchShow(showName);
            System.out.println(showPedia.printCurrentShow());
        }
        catch(UnknownShowExc | NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void addSeason(ShowPedia showPedia){
        try{
            showPedia.addSeasonToCurrentShow();
            System.out.println(showPedia.printCurrentShow());
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
        try{
            String type = in.nextLine();

            if(type.equals(Character.REAL)){
                String charName = in.nextLine();
                String actorName = in.nextLine();
                int cost = in.nextInt();in.nextLine();

                showPedia.addRealCharacter(charName, actorName, cost);
            }
            else
                if(type.equals(Character.VIRTUAL)){
                    String charName = in.nextLine();
                    String company = in.nextLine();
                    int cost = in.nextInt();in.nextLine();

                    showPedia.addCGICharacter(charName, company, cost);
                }
                else
                    System.out.println(UNKNOW_CATEGORY);
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
        try{
            String eventDescription = in.nextLine();
            int season = in.nextInt();
            int episode = in.nextInt();
            int nChars = in.nextInt();in.nextLine();

            List<String> aux = new LinkedList<>();

            for(int i = 0; i < nChars;i++){
                aux.add(in.nextLine());
            }
            showPedia.addEvent(episode, season, aux, eventDescription);
        }
        catch (NoShowSelectedExc e){

        }
    }

    private static void addQuote(Scanner in, ShowPedia showPedia){

        try{
            int season = in.nextInt();
            int episode = in.nextInt();in.nextLine();
            String charName = in.nextLine();
            String quoteText = in.nextLine();
            showPedia.addQuote(season, episode, charName, quoteText);
        }
        catch (NoShowSelectedExc e){  //tratar da exceçoes individualmente
            System.out.println(e.getMessage());
        }
        catch (NonExistentSeasonExc e){
            System.out.println(String.format("%s does not have season %d!" ));
        }
        catch (NonExistentEpisodeExc e){
            System.out.println(String.format("%s S%d does not have episode %d!"));
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format("Who is %s?"));
        }
    }

    private static void seasonsOutline(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void characterResume(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void howAreTheseTwoRelated(Scanner in){
        System.out.println("not implemented yet");
    }

    private static void famousQuotes(Scanner in, ShowPedia showPedia){
        try{
            String quote = in.nextLine();
            String toPrint = "";
            Iterator it = showPedia.getFamousQuotes(quote);
            while (it.hasNext()){
                if(!toPrint.equals(""))
                    toPrint += ", ";
                toPrint += it.next();
            }
            System.out.println(toPrint);
        }
        catch (NoShowSelectedExc | UnknownQuoteExc e){
            System.out.println(e.getMessage());
        }
    }

    private static void alsoAppearsOn(Scanner in, ShowPedia showPedia){
        try{
            String charName = in.nextLine();

            Iterator it = showPedia.alsoAppearsOn(charName);
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
        catch (NoShowSelectedExc e){
            System.out.println(e.getMessage());
        }
        catch (UnknownCharacterExc e){
            System.out.println(String.format("Who is %s?"));
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
            +"addEvent - add a significant event involving at least one character\n"
                        /*done*/    +"addQuote - add a new quote to a character\n"
            +"seasonsOutline - outline the contents of the selected seasons for a selected show\n"
            +"characterResume - outline the main information on a specific character\n"
            +"howAreTheseTwoRelated - find out if and how two characters may be related\n"
                        /*done*/    +"famousQuotes - find out which character(s) said a particular quote\n"
                        /*done*/    +"alsoAppearsOn - which other shows and roles is the same actor on?\n"  //solve exceptions
            +"mostRomantic - find out who is at least as romantic as X\n"
                        /*done*/    +"kingOfCGI - find out which company has earned more revenue with their CGI virtual actors\n"
                        /*done*/    +"help - shows the available commands\n"
                        /*done*/   +"exit - terminates the execution of the program");
    }








}