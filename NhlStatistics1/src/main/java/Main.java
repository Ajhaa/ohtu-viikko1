package main.java;

public class Main {
    public static void main(String[] args) {
        String url = "http://nhlstatistics.herokuapp.com/players.txt";
        Statistics stats = new Statistics(new PlayerReader(url));
          
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("");
        
        System.out.println("Top scorers");
        for (Player player : stats.topScorers(10) ) {
            System.out.println( player );
        }        
    }
}
