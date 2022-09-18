package edu.wm.cs.cs301.game2048;

import java.util.Arrays;

public class State implements GameState {
	
	private int[][] board = new int[4][4];//CONSIDER making this private
	
	public State(GameState original) {
		// TODO consider updating this so that copy is returned instead	
		for(int i = 0; i<4; i++) {
			for(int j=0; j<4; j++) {
				board[i][j] = original.getValue(j, i);
			}
		}
	}

	
	public State() {
		// TODO 
		setEmptyBoard();
	}


	@Override
	public int getValue(int xCoordinate, int yCoordinate) {
		// TODO 
		int value = board[yCoordinate][xCoordinate];
		return value;
	}

	@Override
	public void setValue(int xCoordinate, int yCoordinate, int value) {
		// TODO consider if statement to check if value param is appropriate
		board[yCoordinate][xCoordinate] = value;

	}

	@Override
	public void setEmptyBoard() {
		// TODO 
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				setValue(i, j, 0);
			}
		}
	}

	@Override
	public boolean addTile() {
		// TODO 
		
		int counter = 0;
		int[] rowVal = new int[16];
		int[] colVal = new int[16];
		
		for (int k = 0; k < 4; k++) {// creates array of tile coordinates that are 0 to choose from randomly later
			for (int l = 0; l < 4; l++)
			if (getValue(l, k) == 0) {
				rowVal[counter] = k;
				colVal[counter] = l;
				counter = counter + 1;
			}
		}
		if (counter < 1) {
			return false;
		}else {
			int max1 = counter - 1;
			
			int randomTileVal = (int)Math.floor(Math.random()*(max1+1));
			int randomTileRow = rowVal[randomTileVal];
			int randomTileCol = colVal[randomTileVal];
				
			int randomVal = (int)Math.floor(Math.random()*(2)); // creates random value to randomly choose if new tile 4 or 2
			
			if (randomVal == 0) {
				setValue(randomTileCol, randomTileRow, 2);
			}else {
				setValue(randomTileCol, randomTileRow, 4);
			}
			return true;
		}
	}

	@Override
	public boolean isFull() {
		// TODO 
		int counter = 0;
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (getValue(i, j) != 0) {
					counter++;
				}
			}
		}
		if (counter == 16) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canMerge() {
		// TODO consider more efficient way?
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if ((i > 0) && (i < 3) && (j > 0) && (j < 3)) { // tiles in the middle
					if (getValue(j , i) == getValue(i-1, j)) { // check with upper tile
						return true;
					}else if (getValue(j , i) == getValue(j, i+1)) {//check with bottom tile
						return true;
					}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
						return true;
					}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
						return true;
					}
				} else if (i == 0) {//tiles in the top row
					if (j == 0) {//top left corner tile case
						if (getValue(j , i) == getValue(j, i+1)) {//check with bottom tile
							return true;
						}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
							return true;
						}
					}else if (j ==3) {//top right corner tile case
						if (getValue(j , i) == getValue(j, i+1)) {//check with bottom tile
							return true;
						}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
							return true;
						}
					}else {//other two middle tiles in the row
						if (getValue(j, i) == getValue(j, i+1)) {//check with bottom tile
							return true;
						}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
							return true;
						}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
							return true;
						}
					}
				}else if (i == 3) {//tiles in the bottom row
					if (j == 0) {//bottom left corner tile case
						if (getValue(j , i) == getValue(j, i-1)) { // check with upper tile
							return true;
						}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
							return true;
						}
					}else if (j ==3) {//bottom right corner tile case
						if (getValue(j , i) == getValue(j, i-1)) { // check with upper tile
							return true;
						}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
							return true;
						}
					}else{//other two middle tiles in the row
						if (getValue(j , i) == getValue(j, i-1)) { // check with upper tile
							return true;
						}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
							return true;
						}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
							return true;
						}
					}
				}else if (j == 0) {// left most column minus corners case
					if (getValue(j , i) == getValue(j, i-1)) { // check with upper tile
						return true;
					}else if (getValue(j , i) == getValue(j, i+1)) {//check with bottom tile
						return true;
					}else if (getValue(j , i) == getValue(j+1, i)) {//check right tile
						return true;
					}
				}else if (j == 3) {//right most column minus corners case
					if (getValue(j , i) == getValue(j, i-1)) { // check with upper tile
						return true;
					}else if (getValue(j , i) == getValue(j, i+1)) {//check with bottom tile
						return true;
					}else if (getValue(j , i) == getValue(j-1, i)) {//check left tile
						return true;
					}
				}
				
			}
		}
		return false;
	}

	@Override
	public boolean reachedThreshold() {
		// TODO 
		int curMax = 0;
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				int curVal = getValue(i, j);
				if (curVal > curMax) {
					curMax = curVal;
				}
			}
		}
		if (curMax >= 2048) {
			return true;
		}
		return false;
	}

	@Override
	public int left() {
		// TODO 
		int pointCounter = 0;
		for (int r = 0; r < 4; r++) {
			for (int c = 1; c < 4; c++) {//scenario: initial move
				if (board[r][c] > 0) {
					int toMove = c;
					for (int t = c-1; t >= 0; t--) {
						if (board[r][t] == 0) {
							toMove = t;
						}
					}
					if (toMove != c) {//scenario: action of the sliding down
						int tempVal = (int) board[r][c];
						board[r][toMove] = tempVal;
						board[r][c] = 0;
					}
				}				
			}
			for (int m = 1; m < 4; m++) {//scenario: merge tiles
				if ((board[r][m-1] == board[r][m]) && (board[r][m] > 0)) {
					board[r][m-1] = board[r][m-1]*2;
					board[r][m] = 0;
					pointCounter = pointCounter + board[r][m-1];
				}
			}
			for (int e = 1; e < 4; e++) {
				if (board[r][e] > 0) {
					int toMove = e;
					for (int t = e-1; t >= 0; t--) {
						if (board[r][t] == 0) {
							toMove = t;
						}
					}
					if (toMove != e) {
						int tempVal = (int) board[r][e];
						board[r][toMove] = tempVal;
						board[r][e] = 0;
					}
				}			
			}
		}
		return pointCounter;
	}

	@Override
	public int right() {
		// TODO 
		
		int pointCounter = 0;
		for (int r = 0; r < 4; r++) {
			for (int c = 2; c >= 0; c--) {//scenario: initial move
				if (board[r][c] > 0) {
					int toMove = c;
					for (int t = c+1; t < 4; t++) {
						if (board[r][t] == 0) {
							toMove = t;
						}
					}
					if (toMove != c) {//scenario: action of the sliding down
						int tempVal = (int) board[r][c];
						board[r][toMove] = tempVal;
						board[r][c] = 0;
					}
				}				
			}
			for (int m = 2; m >= 0; m--) {//scenario: merge tiles
				if ((board[r][m+1] == board[r][m]) && (board[r][m] > 0)) {
					board[r][m+1] = board[r][m+1]*2;
					board[r][m] = 0;
					pointCounter = pointCounter + board[r][m+1];
				}
			}
			for (int e = 2; e >= 0; e--) {
				if (board[r][e] > 0) {
					int toMove = e;
					for (int t = e+1; t < 4; t++) {
						if (board[r][t] == 0) {
							toMove = t;
						}
					}
					if (toMove != e) {
						int tempVal = (int) board[r][e];
						board[r][toMove] = tempVal;
						board[r][e] = 0;
					}
				}			
			}
		}
		return pointCounter;
	}

	@Override
	public int down() {
		// TODO fix only one slide, get rid of add tile - connected to updating game state
		int pointCounter = 0;
		for (int c = 0; c < 4; c++) {
			for (int r = 2; r >= 0; r--) {//scenario: initial move
				if (board[r][c] > 0) {
					int toMove = r;
					for (int t = r+1; t < 4; t++) {
						if (board[t][c] == 0) {
							toMove = t;
						}
					}
					if (toMove != r) {//scenario: action of the sliding down
						int tempVal = (int) board[r][c];
						board[toMove][c] = tempVal;
						board[r][c] = 0;
					}
				}				
			}
			for (int m = 2; m >= 0; m--) {//scenario: merge tiles
				if ((board[m+1][c] == board[m][c]) && (board[m][c] > 0)) {
					board[m+1][c] = board[m+1][c]*2;
					board[m][c] = 0;
					pointCounter = pointCounter + board[m+1][c];
				}
			}
			for (int e = 2; e >= 0; e--) {
				if (board[e][c] > 0) {
					int toMove = e;
					for (int t = e+1; t < 4; t++) {
						if (board[t][c] == 0) {
							toMove = t;
						}
					}
					if (toMove != e) {
						int tempVal = (int) board[e][c];
						board[toMove][c] = tempVal;
						board[e][c] = 0;
					}
				}			
			}
		}
		return pointCounter;
		
	}

	@Override
	public int up() {
		// TODO fix only one slide, get rid of add tile - connected to updating game state
		
		int pointCounter = 0;
		for (int c = 0; c < 4; c++) {
			for (int r = 1; r < 4; r++) {//scenario: initial move
				if (board[r][c] > 0) {
					int toMove = r;
					for (int t = r-1; t >= 0; t--) {
						if (board[t][c] == 0) {
							toMove = t;
						}
					}
					if (toMove != r) {//scenario: action of the sliding down
						int tempVal = (int) board[r][c];
						board[toMove][c] = tempVal;
						board[r][c] = 0;
					}
				}				
			}
			for (int m = 1; m < 4; m++) {//scenario: merge tiles
				if ((board[m-1][c] == board[m][c]) && (board[m][c] > 0)) {
					board[m-1][c] = board[m-1][c]*2;
					board[m][c] = 0;
					pointCounter = pointCounter + board[m-1][c];
				}
			}
			for (int e = 1; e < 4; e++) {
				if (board[e][c] > 0) {
					int toMove = e;
					for (int t = e-1; t >= 0; t--) {
						if (board[t][c] == 0) {
							toMove = t;
						}
					}
					if (toMove != e) {
						int tempVal = (int) board[e][c];
						board[toMove][c] = tempVal;
						board[e][c] = 0;
					}
				}			
			}
		}
		return pointCounter;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Arrays.deepEquals(board, other.board);
	}
	
	

}
