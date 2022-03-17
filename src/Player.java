import java.util.Scanner;

public class Player {

    void runPlayer(Board board){
        Scanner playerIn = new Scanner(System.in);
        System.out.println("Player, your turn!");
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

        int numTaken = 0;
        Boolean takenGood = false;
        while(!takenGood){
            System.out.println("How many would you like to take?");
            int numWanted = Integer.parseInt(playerIn.nextLine());
            if(chosenRowNum == 1){
                if(numWanted > board.getRowOne()){
                    System.out.println("That's more than are in the row, pick again");
                } else if(numWanted == 0){
                    System.out.println("You have to pick at least 1 token");
                } else {
                    numTaken = numWanted;
                    takenGood = true;
                }
            } else if(chosenRowNum == 2){
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
        board.makeAMove(chosenRowNum, numTaken);
    }
}
