package edu.wm.cs.cs301.game2048;

public class State implements GameState {
	
	private int[][] board;// TODO: need to actually work this into game
	
	public State(GameState original) {
		// TODO Auto-generated constructor stub
		this.board = new int[4][4];
	}
	
	public State() {
		// TODO Auto-generated constructor stub
		this.board = new int[4][4];
	}

	@Override
	public int getValue(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		int value = this.board[yCoordinate][xCoordinate];
		return value;
	}

	@Override
	public void setValue(int xCoordinate, int yCoordinate, int value) {
		// TODO Auto-generated method stub - consider if statement to check if value param is appropriate
		this.board[yCoordinate][xCoordinate] = value;

	}

	@Override
	public void setEmptyBoard() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				this.board[i][j] = 0;
			}
		}
	}

	@Override
	public boolean addTile() {
		// TODO Auto-generated method stub - CONSIDER changing to list if allowed to import other packages
		
		int counter = 0;
		int[] rowVal = new int[16];
		int[] colVal = new int[16];
		
		for (int k = 0; k < 4; k++) {// creates array of tile coordinates that are 0 to choose from randomly later
			for (int l = 0; l < 4; l++)
			if (this.board[k][l] == 0) {
				rowVal[counter] = k;
				colVal[counter] = l;
				counter = counter + 1;
			}
		}
		if (counter < 1) {
			return false;
		}else {
			int min = 0;
			int max1 = counter - 1;
			
			int randomTileRow = (int)Math.floor(Math.random()*(max1-min+1)+min);
			int randomTileCol = (int)Math.floor(Math.random()*(max1-min+1)+min);
			
			int max2 = 1;
			
			int random_val = (int)Math.floor(Math.random()*(max2-min+1)+min); // creates random value to randomly choose if new tile 4 or 2
			
			if (random_val == 0) {
				// TODO - why doesn't this work - this.board.setValue(randomTileCol, randomTileRow, 2);
				this.board[randomTileRow][randomTileCol] = 2;// CONISDER - finding way to use setValue
			}else {
				this.board[randomTileRow][randomTileCol] = 4;
			}
			return true;
		}
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (this.board[i][j] != 0) {
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
					if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
						return true;
					}else if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
						return true;
					}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
						return true;
					}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
						return true;
					}
				} else if (i == 0) {//tiles in the top row
					if (j == 0) {//top left corner tile case
						if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
							return true;
						}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
							return true;
						}
					}else if (j ==3) {//top right corner tile case
						if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
							return true;
						}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
							return true;
						}
					}else {//other two middle tiles in the row
						if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
							return true;
						}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
							return true;
						}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
							return true;
						}
					}
				}else if (i == 3) {//tiles in the bottom row
					if (j == 0) {//bottom left corner tile case
						if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
							return true;
						}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
							return true;
						}
					}else if (j ==3) {//bottom right corner tile case
						if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
							return true;
						}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
							return true;
						}
					}else{//other two middle tiles in the row
						if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
							return true;
						}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
							return true;
						}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
							return true;
						}
					}
				}else if (j == 0) {// left most column minus corners case
					if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
						return true;
					}else if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
						return true;
					}else if (this.board[i][j] == this.board[i][j+1]) {//check right tile
						return true;
					}
				}else if (j == 3) {//right most column minus corners case
					if (this.board[i][j] == this.board[i-1][j]) { // check with upper tile
						return true;
					}else if (this.board[i][j] == this.board[i+1][j]) {//check with bottom tile
						return true;
					}else if (this.board[i][j] == this.board[i][j-1]) {//check left tile
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
				if (this.board[i][j] > cur_max) {
					cur_max = this.board[i][j];
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
