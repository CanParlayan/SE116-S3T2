public class Player {
    private String name;
    private int score;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        String temp1 = name.substring(0,1).toUpperCase();
        String temp2 = name.substring(1,name.length()).toLowerCase();
        return temp1.concat(temp2);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void printPlayerInfo(){
        System.out.println("Player "+getName()+", reached the score "+getScore()+"!");
    }
}
