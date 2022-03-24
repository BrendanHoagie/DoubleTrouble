import java.util.Scanner;

public class DoubleTrouble {
    public static void main(String[] args) {
        // create objects
        Scanner input = new Scanner(System.in);
        Board theBoard = new Board();
        Player human = new Player();
        Computer computer = new Computer();

        // create global variables
        Boolean goodInput = false;
        String playerOne = "";
        String playerTwo = "";
        String winner = "";

        System.out.println("Let's play Double Trouble!");

        // decide who should go first
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

        // start the board configuration
        System.out.println("Starting configuration:");
        theBoard.printBoard();

        // begin main game loop
        while(theBoard.getNumLeft() > 0){
            // for the case where player goes first
            if(playerOne.equals("player")){
                // handle player input and update the board accordingly
                if(!theBoard.checkWin()) {
                    human.runPlayer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerOne;
                    }
                }
                // create computer input and update the board accordingly
                if(!theBoard.checkWin()) {
                    System.out.println("\ncomputer's turn!");
                    computer.runComputer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerTwo;
                    }
                }
            // for the case where the computer goes first
            } else {
                // create computer input and update the board accordingly
                if(!theBoard.checkWin()) {
                    System.out.println("\ncomputer's turn!");
                    computer.runComputer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerOne;
                    }
                }
                // handle player input and update the board accordingly
                if(!theBoard.checkWin()) {
                    human.runPlayer(theBoard);
                    theBoard.printBoard();
                    if (theBoard.checkWin()) {
                        winner += playerTwo;
                    }
                }
            }
        }
        // declare the winner at the end of the game
        System.out.println("\nThe winner is " + winner);
    }
}
