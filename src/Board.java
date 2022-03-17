public class Board {

    int rowOne = 3;
    int rowTwo = 5;
    int rowThree = 7;
    int numLeft = rowOne + rowTwo + rowThree;

    int getRowOne(){
        return rowOne;
    }

    int getRowTwo(){
        return rowTwo;
    }

    int getRowThree(){
        return rowThree;
    }

    void printBoard(){
        String lineOne = "";
        String lineTwo = "";
        String lineThree = "";
        lineOne = updateLines(lineOne, 1, rowOne);
        System.out.println("  " +lineOne);
        lineTwo = updateLines(lineTwo, 2, rowTwo);
        System.out.println(" " + lineTwo);
        lineThree = updateLines(lineThree, 3, rowThree);
        System.out.println(lineThree);
    }

    String updateLines(String input, int lineNum, int rowLength){
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
                input += "X";
            } else {
                input += "0";
            }
        }
        return input;
    }

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

    void updateNumLeft(){
        numLeft = rowOne + rowTwo + rowThree;
    }

    boolean checkWin(){
        if(numLeft == 0){
            return true;
        } else {
            return false;
        }
    }
}