import java.util.LinkedList;

public class Computer {

    // main computer function
    public void runComputer(Board board) {

        // board values
        int firstRow = board.getRowOne();
        int secondRow = board.getRowTwo();
        int thirdRow = board.getRowThree();
        int largestRow = largestOf(firstRow, secondRow, thirdRow);

        // calculate the nim sum of the board
        int nimSum = firstRow ^ secondRow ^ thirdRow;

        // check if the computer is in losing position and
        // make a random move if it is
        if (nimSum == 0) {
            runRandComputer(board);
        }

        // if we have a large unpaired multiple of 4 or 2 (only one row has
        // a 4 bit or no row has a 4 bit and only one row has a 2 bit)
        // in the XOR addition - we need to find the right move
        // ex- 1/2/5 has a nim sum of 6, but we can't take the nim sum
        // from any row like we normally do, because the largest possible row
        // has 5, thus we cannot take 6 from any row
        else if (anySingleMultiples(board, 4) || (anySingleMultiples(board, 2) && !anySingleMultiples(board, 4))) {
            boolean foundSum = false;
            int originalLargestRow = largestRow;
            int rowWanted = 0;
            // the large unpaired/single multiple issue can be solved
            // by taking away from the largest row
            // we loop over the largest row and take one away
            // each time until we find a nim sum that equals 0
            if (largestRow == firstRow) {
                rowWanted = 1;
                while (!foundSum) {
                    largestRow--;
                    int guess = largestRow ^ secondRow ^ thirdRow;
                    if (guess == 0) {
                        foundSum = true;
                        int tokensWanted = originalLargestRow - largestRow;
                        board.makeAMove(rowWanted, tokensWanted);
                    }
                }
            } else if (largestRow == secondRow) {
                rowWanted = 2;
                while (!foundSum) {
                    largestRow--;
                    int guess = firstRow ^ largestRow ^ thirdRow;
                    if (guess == 0) {
                        foundSum = true;
                        int tokensWanted = originalLargestRow - largestRow;
                        board.makeAMove(rowWanted, tokensWanted);
                    }
                }
            } else {
                rowWanted = 3;
                while (!foundSum) {
                    largestRow--;
                    int guess = firstRow ^ secondRow ^ largestRow;
                    if (guess == 0) {
                        foundSum = true;
                        int tokensWanted = originalLargestRow - largestRow;
                        board.makeAMove(rowWanted, tokensWanted);
                    }
                }
            }
        }
        // we want to take away equal to the nim sum and we know the sum is not bigger than the largest row
        // so we choose a random row and see if we can take from there, repeat until we find where we can take
        // ensures moves will be subtly different from game to game
        else {
            int tokensWanted = nimSum;
            int rowNum = 0;
            LinkedList<Integer> triedList = new LinkedList<Integer>();
            boolean rowGood = false;
            // we know how many we want to take away (nim sum), but we need to search
            // for the correct row to take from.
            // ex 1/4/4 needs 1 token removed, but removing from anything except
            // the first row doesn't put the opponent in losing position
            // so we need to continue searching for the right row if we pick rows 2 or 3
            while (!rowGood) {
                // first step is choose a random row such that games are different from each other
                int tryRow = chooseRandRow(board);
                if (!triedList.contains(tryRow)) {
                    if (tryRow == 1) {
                        int changedFirst = firstRow - tokensWanted;
                        if (changedFirst < 0) {
                            triedList.add(tryRow);
                        } else {
                            int newNimSum = changedFirst ^ secondRow ^ thirdRow;
                            if (newNimSum == 0) {
                                rowGood = true;
                                board.makeAMove(tryRow, tokensWanted);
                            } else {
                                triedList.add(tryRow);
                            }
                        }
                    } else if (tryRow == 2) {
                        int changedSecond = secondRow - tokensWanted;
                        if (changedSecond < 0) {
                            triedList.add(tryRow);
                        } else {
                            int newNimSum = firstRow ^ changedSecond ^ thirdRow;
                            if (newNimSum == 0) {
                                rowGood = true;
                                board.makeAMove(tryRow, tokensWanted);
                            } else {
                                triedList.add(tryRow);
                            }
                        }
                    } else if (tryRow == 3) {
                        int changedThird = thirdRow - tokensWanted;
                        if (changedThird < 0) {
                            triedList.add(tryRow);
                        } else {
                            int newNimSum = firstRow ^ secondRow ^ changedThird;
                            if (newNimSum == 0) {
                                rowGood = true;
                                board.makeAMove(tryRow, tokensWanted);
                            } else {
                                triedList.add(tryRow);
                            }
                        }
                    }
                }
            }
        }
    }

    // finds if there are any multiples without pairs, which could cause
    // issues with the way tokens are calculated
    public boolean anySingleMultiples(Board board, int multiple) {
        int rOne = board.getRowOne();
        int rTwo = board.getRowTwo();
        int rThree = board.getRowThree();
        if (rOne >= multiple && ((rTwo < multiple) && (rThree < multiple))) {
            return true;
        } else if (rTwo >= multiple && ((rOne < multiple) && (rThree < multiple))) {
            return true;
        } else return rThree >= multiple && ((rTwo < multiple) && (rOne < multiple));
    }

    public int largestOf(int a, int b, int c) {
        int largestRow = 0;
        if (a > largestRow) {
            largestRow = a;
        }
        if (b > largestRow) {
            largestRow = b;
        }
        if (c > largestRow) {
            largestRow = c;
        }
        return largestRow;
    }

    // make a random move, no strategy
    public void runRandComputer(Board board) {
        int row = chooseRandRow(board);
        int tokens = chooseRandNumTokens(row, board);
        board.makeAMove(row, tokens);
    }

    // chooses a random row and ensures that it follows all game rules
    public int chooseRandRow(Board board) {
        int returnVal = 1;
        int min = 1;
        int max = 3;
        boolean rowGood = false;
        while (!rowGood) {
            returnVal = (int) Math.floor(Math.random() * (max - min + 1) + min);
            if (returnVal == 1) {
                if (board.getRowOne() != 0) {
                    rowGood = true;
                }
            } else if (returnVal == 2) {
                if (board.getRowTwo() != 0) {
                    rowGood = true;
                }
            } else if (returnVal == 3) {
                if (board.getRowThree() != 0) {
                    rowGood = true;
                }
            }
        }
        return returnVal;
    }

    // chooses a random number of tokens in the chosen row and ensures that it follows all game rules
    public int chooseRandNumTokens(int rowNum, Board board) {
        int min = 1;
        int max = 0;
        if (rowNum == 1) {
            max = board.getRowOne();
        } else if (rowNum == 2) {
            max = board.getRowTwo();
        } else {
            max = board.getRowThree();
        }
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
