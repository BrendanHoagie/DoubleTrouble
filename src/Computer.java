public class Computer {

    void runComputer(Board board){
        int row = chooseRandRow(board);
        int tokens = chooseRandNumTokens(row, board);
        board.makeAMove(row, tokens);
    }

    int chooseRandRow(Board board){
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

    int chooseRandNumTokens(int rowNum, Board board){
        int min = 1;
        int max = 0;
        if(rowNum == 1){
            max = board.getRowOne();
        } else if(rowNum == 2){
            max = board.getRowTwo();
        } else {
            max = board.getRowThree();
        }
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
