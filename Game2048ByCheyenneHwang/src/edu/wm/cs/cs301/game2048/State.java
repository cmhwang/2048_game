package edu.wm.cs.cs301.game2048;

public class State implements GameState {
	
	int[][] board;// TODO: need to actually work this into game
	int myInt;
	double myDouble;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		//TODO need to fill this in from lecture
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Object)) {//TODO should check that Object is valid (Example in the ex from lecture)
			return false;
		}
		Object other = (Object) obj;
//		if (Double.doubleToLongBits(myDouble) != Double.doubleToLongBits(other.myDouble)){TODO need to implement this part - see lecture
//			return false;
//		}
		return true;
	}
	
	public State(GameState original) {
		// TODO Auto-generated constructor stub
		board = new int[4][4];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				board[i][j] = 0;
			}
		}
	}

	
	public State() {
		// TODO Auto-generated constructor stub
		board = new int[4][4];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				board[i][j] = 0;
			}
		}
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
		// TODO Auto-generated method stub - CONSIDER changing to list if allowed to import other packages
		
		int counter = 0;
		int[] rowVal = new int[16];
		int[] colVal = new int[16];
		
		for (int k = 0; k < 4; k++) {// creates array of tile coordinates that are 0 to choose from randomly later
			for (int l = 0; l < 4; l++)
			if (board[k][l] == 0) {
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
			
			int randomTileVal = (int)Math.floor(Math.random()*(max1-min+1)+min);
			int randomTileRow = rowVal[randomTileVal];
			int randomTileCol = colVal[randomTileVal];
			
			int max2 = 1;
			
			int randomVal = (int)Math.floor(Math.random()*(max2-min+1)+min); // creates random value to randomly choose if new tile 4 or 2
			
			if (randomVal == 0) {
				// TODO
				board[randomTileRow][randomTileCol] = 2;// CONISDER - finding way to use setValue
			}else {
				board[randomTileRow][randomTileCol] = 4;
			}
			return true;
		}
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
		int pointCounter = 0;
		for (int r = 0; r <4; r++){
			for (int c = 3; c > 0; c--) {
				int curVal = board[r][c];
				if (board[r][c] == board[r][c-1]) {
					int newVal = curVal * 2;
					
					board[r][c] = 0;//CONSIDER use setValue instead 
					board[r][c-1] = newVal;
					pointCounter = pointCounter + newVal;
					c--;
				} else {
					if (board[r][c-1] == 0) {
						board[r][c-1] = curVal;
						board[r][c] = 0;
					}
				}
			}
		}
		
		return pointCounter;
	}

	@Override
	public int right() {
		// TODO Auto-generated method stub
		int pointCounter = 0;
		for (int r = 0; r <4; r++){
			for (int c = 0; c < 3; c++) {
				int curVal = board[r][c];
				if (board[r][c] == board[r][c+1]) {
					int newVal = curVal * 2;
					
					board[r][c] = 0;//CONSIDER use setValue instead 
					board[r][c+1] = newVal;
					pointCounter = pointCounter + newVal;
					c++;
				} else {
					if (board[r][c+1] == 0) {
						board[r][c+1] = curVal;
						board[r][c] = 0;
					}
				}
			}
		}
		
		return pointCounter;
	}

	@Override
	public int down() {
		// TODO Auto-generated method stub
		int pointCounter = 0;
		for (int r = 0; r < 3; r++){
			for (int c = 0; c <= 3; c++) {
				int curVal = board[r][c];
				if (board[r][c] == board[r+1][c]) {
					int newVal = curVal * 2;
					
					board[r][c] = 0;//CONSIDER use setValue instead 
					board[r+1][c] = newVal;
					pointCounter = pointCounter + newVal;
					r++;
				} else {
					if (board[r+1][c] == 0) {
						board[r+1][c] = curVal;
						board[r][c] = 0;
					}
				}
			}
		}
		
		return pointCounter;
	}

	@Override
	public int up() {
		// TODO Auto-generated method stub
		int pointCounter = 0;
		for (int r = 3; r > 0; r--){
			for (int c = 0; c <= 3; c++) {
				int curVal = board[r][c];
				if (board[r][c] == board[r-1][c]) {
					int newVal = curVal * 2;
					
					board[r][c] = 0;//CONSIDER use setValue instead 
					board[r-1][c] = newVal;
					pointCounter = pointCounter + newVal;
					r--;
				} else {
					if (board[r-1][c] == 0) {
						board[r-1][c] = curVal;
						board[r][c] = 0;
					}
				}
			}
		}
		
		return pointCounter;
	}

}
