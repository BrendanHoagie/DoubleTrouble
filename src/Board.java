public class Board {

    // private variables representing the number of tokens left in each row
    private int rowOne = 3;
    private int rowTwo = 5;
    private int rowThree = 7;
    private int numLeft = rowOne + rowTwo + rowThree;

    // getters for each variable
    int getRowOne() { return rowOne; }
    int getRowTwo(){
        return rowTwo;
    }
    int getRowThree(){ return rowThree; }
    int getNumLeft() { return numLeft; }


    // prints the board according to the numbers of available tokens in a row
    void printBoard(){
        String lineOne = "";
        String lineTwo = "";
        String lineThree = "";
        lineOne = updateLines(lineOne, 1, rowOne,"X");
        System.out.println("  " +lineOne);
        lineTwo = updateLines(lineTwo, 2, rowTwo, "X");
        System.out.println(" " + lineTwo);
        lineThree = updateLines(lineThree, 3, rowThree, "X");
        System.out.println(lineThree);
    }

    // updates the tokens in a line according to the number of tokens taken
    String updateLines(String input, int lineNum, int rowLength, String marker){
        int originalLineLength;

        if(lineNum == 1){
            originalLineLength = 3;
        } else if(lineNum == 2){
            originalLineLength = 5;
        } else {
            originalLineLength = 7;
        }

        for(int i = 0; i < originalLineLength; i++){
            if(i < rowLength){
                input += marker;
            } else {
                input += "0";
            }
        }
        return input;
    }

    // allows a player or computer to make a move and updates the board information
    void makeAMove(int rowNum, int numTaken){
        if(rowNum == 1){
            rowOne = rowOne - numTaken;
            updateNumLeft();
        } else if(rowNum == 2){
            rowTwo = rowTwo - numTaken;
            updateNumLeft();
        } else {
            rowThree = rowThree - numTaken;
            updateNumLeft();
        }
    }

    // update the number of total tokens available
    void updateNumLeft(){
        numLeft = rowOne + rowTwo + rowThree;
    }

    // check if all tokens have been taken
    boolean checkWin(){
        if(numLeft == 0){
            return true;
        } else {
            return false;
        }
    }
}