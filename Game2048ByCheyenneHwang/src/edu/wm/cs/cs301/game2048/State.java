package edu.wm.cs.cs301.game2048;

public class State implements GameState {
	
	public int[][] board;// TODO: need to actually construct this into the game
	
	public State(GameState original) {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public int getValue(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		int value = board[yCoordinate][xCoordinate];
		return value;
	}

	@Override
	public void setValue(int xCoordinate, int yCoordinate, int value) {
		// TODO Auto-generated method stub - consider if statement to check if value param is appropriate
		board[yCoordinate][xCoordinate] = value;

	}

	@Override
	public void setEmptyBoard() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				board[i][j] = 0;
			}
		}
	}

	@Override
	public boolean addTile() {
		// TODO Auto-generated method stub - CONSIDER figure out how to make this actually random
		int min = 0;
		int max = 1;
		
		int random_val = (int)Math.floor(Math.random()*(max-min+1)+min);

		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (board[i][j] == 0) {
					if (random_val == 1) {
						board[i][j] = 2;
					}else {
						board[i][j] = 4;
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (board[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean canMerge() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if ((i > 0) && (i < 3) && (j > 0) && (j < 3)) { // tiles in the middle
					if (board[i][j] == board[i-1][j]) { // check with upper tile
						return true;
					}else if (board[i][j] == board[i+1][j]) {//check with bottom tile
						return true;
					}else if (board[i][j] == board[i][j+1]) {//check right tile
						return true;
					}else if (board[i][j] == board[i][j-1]) {//check left tile
						return true;
					}
				} else if (i == 0) {//tiles in the top row
					if (j == 0) {//top left corner tile case
						if (board[i][j] == board[i+1][j]) {//check with bottom tile
							return true;
						}else if (board[i][j] == board[i][j+1]) {//check right tile
							return true;
						}
					}else if (j ==3) {//top right corner tile case
						if (board[i][j] == board[i+1][j]) {//check with bottom tile
							return true;
						}else if (board[i][j] == board[i][j-1]) {//check left tile
							return true;
						}
					}else {//other two middle tiles in the row
						if (board[i][j] == board[i+1][j]) {//check with bottom tile
							return true;
						}else if (board[i][j] == board[i][j+1]) {//check right tile
							return true;
						}else if (board[i][j] == board[i][j-1]) {//check left tile
							return true;
						}
					}
				}else if (i == 3) {//tiles in the bottom row
					if (j == 0) {//bottom left corner tile case
						if (board[i][j] == board[i-1][j]) { // check with upper tile
							return true;
						}else if (board[i][j] == board[i][j+1]) {//check right tile
							return true;
						}
					}else if (j ==3) {//bottom right corner tile case
						if (board[i][j] == board[i-1][j]) { // check with upper tile
							return true;
						}else if (board[i][j] == board[i][j-1]) {//check left tile
							return true;
						}
					}else{//other two middle tiles in the row
						if (board[i][j] == board[i-1][j]) { // check with upper tile
							return true;
						}else if (board[i][j] == board[i][j+1]) {//check right tile
							return true;
						}else if (board[i][j] == board[i][j-1]) {//check left tile
							return true;
						}
					}
				}else if (j == 0) {// left most column minus corners case
					if (board[i][j] == board[i-1][j]) { // check with upper tile
						return true;
					}else if (board[i][j] == board[i+1][j]) {//check with bottom tile
						return true;
					}else if (board[i][j] == board[i][j+1]) {//check right tile
						return true;
					}
				}else if (j == 3) {//right most column minus corners case
					if (board[i][j] == board[i-1][j]) { // check with upper tile
						return true;
					}else if (board[i][j] == board[i+1][j]) {//check with bottom tile
						return true;
					}else if (board[i][j] == board[i][j-1]) {//check left tile
						return true;
					}
				}
				
			}
		}
		return false;
	}

	@Override
	public boolean reachedThreshold() {
		// TODO Auto-generated method stub
		int cur_max = 0;
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (board[i][j] > cur_max) {
					cur_max = board[i][j];
				}
			}
		}
		if (cur_max >= 2048) {
			return true;
		}
		return false;
	}

	@Override
	public int left() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int right() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int down() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int up() {
		// TODO Auto-generated method stub
		return 0;
	}

}
