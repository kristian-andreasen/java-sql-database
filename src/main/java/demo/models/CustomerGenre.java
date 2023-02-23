package demo.models;

public class CustomerGenre {
    private String mostPopular;
    private String secondMostPopular;
    private boolean tie;


    public String getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(String mostPopular) {
        this.mostPopular = mostPopular;
    }

    public String getSecondMostPopular() {
        return secondMostPopular;
    }

    public void setSecondMostPopular(String secondMostPopular) {
        this.secondMostPopular = secondMostPopular;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }

    public String display() {
        if (tie) {
            return "it's a tie betwen: " + mostPopular + " and " + secondMostPopular;
        }
        return "the most popular genre is " + mostPopular;
    }
}
