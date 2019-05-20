/**
 * @author Joao Casquilho 54440
 */

import java.util.*;


public class Main {

    //Enumerators that define the user commands
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



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShowPedia showPedia = new ShowPediaClass();
        Command comm = getCommand(in);

        while (!comm.equals(Command.EXIT)) {
            System.out.print("> ");
            switch (comm) {
                case CURRENTSHOW:           currentShow(in);           break;
                case ADDSHOW:               addShow(in);               break;
                case SWITCHTOSHOW:          switchToShow(in);          break;
                case ADDSEASON:             addSeason(in);             break;
                case ADDEPISODE:            addEpisode(in);            break;
                case ADDCHARACTER:          addCharacter(in);          break;
                case ADDRELATIONSHIP:       addRelationship(in);       break;
                case ADDROMANCE:            addRomance(in);            break;
                case ADDEVENT:              addEvent(in);              break;
                case ADDQUOTE:              addQuote(in);              break;
                case SEASONSOUTLINE:        seasonsOutline(in);        break;
                case CHARACTERRESUME:       characterResume(in);       break;
                case HOWARETHESETWORELATED: howAreTheseTwoRelated(in); break;
                case FAMOUSQUOTES:          famousQuotes(in);          break;
                case ALSOAPPEARSON:         alsoAppearsOn(in);         break;
                case MOSTROMANTIC:          mostRomantic(in);          break;
                case KINGOFCGI:             kingOfCGI(in);             break;
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

    private static void currentShow(Scanner in){

    }

    private static void addShow(Scanner in){
    }

    private static void switchToShow(Scanner in){
    }

    private static void addSeason(Scanner in){
    }

    private static void addEpisode(Scanner in){
    }

    private static void addCharacter(Scanner in){
    }

    private static void addRelationship(Scanner in){
    }

    private static void addRomance(Scanner in){
    }

    private static void addEvent(Scanner in){
    }

    private static void addQuote(Scanner in){
    }

    private static void seasonsOutline(Scanner in){
    }

    private static void characterResume(Scanner in){
    }

    private static void howAreTheseTwoRelated(Scanner in){
    }

    private static void famousQuotes(Scanner in){
    }

    private static void alsoAppearsOn(Scanner in){
    }

    private static void mostRomantic(Scanner in){
    }

    private static void kingOfCGI(Scanner in){
    }

    private static void help(){
        System.out.println(
            "currentShow - show the current show\n"
            +"addShow - add a new show\n"
            +"switchToShow - change the context to a particular show\n"
            +"addSeason - add a new season to the current show\n"
            +"addEpisode - add a new episode to a particular season of the current show\n"
            +"addCharacter - add a new character to a show\n"
            +"addRelationship - add a family relationship between characters\n"
            +"addRomance - add a romantic relationship between characters\n"
            +"addEvent - add a significant event involving at least one character\n"
            +"addQuote - add a new quote to a character\n"
            +"seasonsOutline - outline the contents of the selected seasons for a selected show\n"
            +"characterResume - outline the main information on a specific character\n"
            +"howAreTheseTwoRelated - find out if and how two characters may be related\n"
            +"famousQuotes - find out which character(s) said a particular quote\n"
            +"alsoAppearsOn - which other shows and roles is the same actor on?\n"
            +"mostRomantic - find out who is at least as romantic as X\n"
            +"kingOfCGI - find out which company has earned more revenue with their CGI virtual actors\n"
            +"help - shows the available commands\n"
            +"exit - terminates the execution of the program");
    }








}