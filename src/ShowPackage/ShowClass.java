package ShowPackage;

public class ShowClass implements Show {

    private String ShowName;
    private int seasonsNumber;
    private int totalEpisodesNumber;

    public ShowClass(String ShowName){
        this.ShowName = ShowName;
        this.seasonsNumber = 0;
        this.totalEpisodesNumber = 0;
    }

    public String getName() {
        return ShowName;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public int getTotalEpisodesNumber() {
        return totalEpisodesNumber;
    }
}
