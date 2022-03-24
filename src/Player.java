import java.util.Scanner;

public class Player {

    // main player function - handles user input and makes a move using that input
    void runPlayer(Board board){
        int chosenRow = chooseRow(board);
        int numTaken = choseNumTaken(board, chosenRow);
        board.makeAMove(chosenRow, numTaken);
    }

    // handle row input and ensure that input follows all game rules
    int chooseRow(Board board){
        Scanner playerIn = new Scanner(System.in);
        int chosenRowNum = 0;
        Boolean rowNumGood = false;
        while(!rowNumGood){
            System.out.println("What row would you like to take from, 1, 2, or 3?");
            String chosenRow = playerIn.nextLine();
            if(chosenRow.equals("1")) {
                if(board.getRowOne() == 0){
                    System.out.println("None left in that row, please pick again");
                } else {
                    chosenRowNum = 1;
                    rowNumGood = true;
                }
            } else if(chosenRow.equals("2")) {
                if(board.getRowTwo() == 0){
                    System.out.println("None left in that row, please pick again");
                } else {
                    chosenRowNum = 2;
                    rowNumGood = true;
                }
            }else if(chosenRow.equals("3")) {
                if(board.getRowThree() == 0){
                    System.out.println("None left in that row, please pick again");
                } else {
                    chosenRowNum = 3;
                    rowNumGood = true;
                }
            } else {
                System.out.println("Not a valid row, choose again");
            }
        }
        return chosenRowNum;
    }
    // handle token input and ensures that the player follows all game rules
    int choseNumTaken(Board board, int chosenRow){
        Scanner playerIn = new Scanner(System.in);
        int numTaken = 0;
        Boolean takenGood = false;
        while(!takenGood){
            System.out.println("How many would you like to take?");
            int numWanted = Integer.parseInt(playerIn.nextLine());
            if(chosenRow == 1){
                if(numWanted > board.getRowOne()){
                    System.out.println("That's more than are in the row, pick again");
                } else if(numWanted == 0){
                    System.out.println("You have to pick at least 1 token");
                } else {
                    numTaken = numWanted;
                    takenGood = true;
                }
            } else if(chosenRow == 2){
                if(numWanted > board.getRowTwo()){
                    System.out.println("That's more than are in the row, pick again");
                } else if(numWanted == 0){
                    System.out.println("You have to pick at least 1 token");
                } else {
                    numTaken = numWanted;
                    takenGood = true;
                }
            } else {
                if(numWanted > board.getRowThree()){
                    System.out.println("That's more than are in the row, pick again");
                } else if(numWanted == 0){
                    System.out.println("You have to pick at least 1 token");
                } else {
                    numTaken = numWanted;
                    takenGood = true;
                }
            }
        }
        return numTaken;
    }
}
