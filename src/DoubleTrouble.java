import java.util.Scanner;

public class DoubleTrouble {
    public static void main(String[] args) {
        // create objects
        Scanner input = new Scanner(System.in);
        Board theBoard = new Board();
        Player human = new Player();
        Computer computer = new Computer();

        Boolean goodInput = false;
        String playerOne = "";
        String playerTwo = "";
        String winner = "";
        System.out.println("Let's play Double Trouble!");
        while(!goodInput){
            System.out.println("Who should go first, player or computer?\nType p for player or c for computer");
            String determineFirst = input.nextLine();
            if(determineFirst.equals("p") || determineFirst.equals("P")){
                goodInput = true;
                playerOne = "player";
                playerTwo = "computer";
            } else if (determineFirst.equals("c") || determineFirst.equals("C")){
                goodInput = true;
                playerOne = "computer";
                playerTwo = "player";
            } else {
                System.out.println("Not a valid input, please try again");
            }
        }
        theBoard.printBoard();
        while(theBoard.numLeft > 0){
            if(playerOne.equals("player")){
                if(!theBoard.checkWin()) {
                    human.runPlayer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerOne;
                    }
                }
                if(!theBoard.checkWin()) {
                    System.out.println("\ncomputer's turn!");
                    computer.runComputer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerTwo;
                    }
                }
            } else {
                if(!theBoard.checkWin()) {
                    System.out.println("computer's turn!");
                    computer.runComputer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerOne;
                    }
                }
                if(!theBoard.checkWin()) {
                    human.runPlayer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerTwo;
                    }
                }
            }
        }
        System.out.println("The winner is " + winner);
    }
}
