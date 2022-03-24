import java.util.Scanner;

public class DoubleTrouble {
    public static void main(String[] args) {
        // create objects
        Scanner input = new Scanner(System.in);
        Board theBoard = new Board();
        Player human = new Player();
        Player pTwo = new Player();
        Computer computer = new Computer();

        // create global variables
        Boolean goodInput = false;
        Boolean gameplayChosen = false;
        boolean pTwoExists = false;
        String playerOne = "";
        String playerTwo = "";
        String winner = "";

        System.out.println("Let's play Double Trouble!");

        //single player or multiplayer
        while (!gameplayChosen) {
            System.out.println("Are you playing in single player mode or multiplayer mode?\nType s for single player or m for multiplayer");
            String determineFirst = input.nextLine();
            if (determineFirst.equals("s") || determineFirst.equals("S")) {
                gameplayChosen = true;
                pTwoExists = false;
            } else if (determineFirst.equals("m") || determineFirst.equals("M")) {
                gameplayChosen = true;
                pTwoExists = true;
            } else {
                System.out.println("Not a valid input, please try again");
            }
        }

        // decide who should go first
        if (pTwoExists) {
            while (!goodInput) {
                System.out.println("Who should go first, player one or player two?\nType 1 for player one or 2 for player two");
                String determineFirst = input.nextLine();
                if (determineFirst.equals("1") || determineFirst.equals("one") || determineFirst.equals("one")) {
                    goodInput = true;
                    playerOne = "player one";
                    playerTwo = "player two";
                } else if (determineFirst.equals("2") || determineFirst.equals("two") || determineFirst.equals("two")) {
                    goodInput = true;
                    playerOne = "player two";
                    playerTwo = "player one";
                } else {
                    System.out.println("Not a valid input, please try again");
                }
            }
        } else {
            while (!goodInput) {
                System.out.println("Who should go first, player or computer?\nType p for player or c for computer");
                String determineFirst = input.nextLine();
                if (determineFirst.equals("p") || determineFirst.equals("P")) {
                    goodInput = true;
                    playerOne = "player";
                    playerTwo = "computer";
                } else if (determineFirst.equals("c") || determineFirst.equals("C")) {
                    goodInput = true;
                    playerOne = "computer";
                    playerTwo = "player";
                } else {
                    System.out.println("Not a valid input, please try again");
                }
            }
        }

        // start the board configuration
        System.out.println("Starting configuration:");
        theBoard.printBoard();

        if(pTwoExists) {
            // begin main game loop for the case where player one goes first
            if (playerOne.equals("player one")) {
                while (theBoard.getNumLeft() > 0) {
                    // handle player one input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\nPlayer one, your turn!");
                        human.runPlayer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerOne;
                        }
                    }
                    // handle player two input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\nPlayer two, your turn!");
                        pTwo.runPlayer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerTwo;
                        }
                    }
                }
                // begin main game loop for the case where player two goes first
            } else {
                while (theBoard.getNumLeft() > 0) {
                    // handle player two input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\nPlayer two, your turn!");
                        pTwo.runPlayer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerOne;
                        }
                    }
                    // handle player one input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\nPlayer one, your turn!");
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
        } else {
            // begin main game loop for the case where player goes first
            if (playerOne.equals("player")) {
                while (theBoard.getNumLeft() > 0) {
                    // handle player input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        human.runPlayer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerOne;
                        }
                    }
                    // create computer input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\ncomputer's turn!");
                        computer.runComputer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerTwo;
                        }
                    }
                }
                // begin main game loop for the case where the computer goes first
            } else {
                while (theBoard.getNumLeft() > 0) {
                    // create computer input and update the board accordingly
                    if (!theBoard.checkWin()) {
                        System.out.println("\ncomputer's turn!");
                        computer.runComputer(theBoard);
                        theBoard.printBoard();
                        if (theBoard.checkWin()) {
                            winner += playerOne;
                        }
                    }
                    // handle player input and update the board accordingly
                    if (!theBoard.checkWin()) {
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
}
