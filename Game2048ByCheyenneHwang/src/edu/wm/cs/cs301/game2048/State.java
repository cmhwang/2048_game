package edu.wm.cs.cs301.game2048;

public class State implements GameState {
	
	int[][] board;//CONSIDER making this private
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
		// TODO 
		board = new int[4][4];
		setEmptyBoard();
	}

	
	public State() {
		// TODO 
		board = new int[4][4];
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
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (getValue(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
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
		// TODO fix only one slide
		int pointCounter = 0;
		for (int r = 0; r < 4; r++){
			for (int c = 1; c < 4; c++) {
				int curVal = getValue(c, r);
				int compVal = getValue(c-1, r);
				if (curVal == compVal) {
					int newVal = curVal * 2;
					
					setValue(c, r, 0);
					setValue(c-1, r, newVal);
					pointCounter = pointCounter + newVal;
					if (c < 2) {
						c++;
					}
				} else {
					if (compVal == 0) {
						setValue(c-1, r, curVal);
						setValue(c, r, 0);
					}
				}
			}
		}
		if (!isFull()) {
			addTile();
		}
		
		return pointCounter;
	}

	@Override
	public int right() {
		// TODO fix only one slide
		int pointCounter = 0;
		for (int r = 0; r < 4; r++){
			for (int c = 2; c >= 0; c--) {
				int curVal = getValue(c, r);
				int compVal = getValue(c+1, r);
				if (curVal == compVal) {
					int newVal = curVal * 2;
					
					setValue(c, r, 0);
					setValue(c+1, r, newVal);
					pointCounter = pointCounter + newVal;
					if (c > 1) {
						c--;
					}
				} else {
					if (compVal == 0) {
						setValue(c+1, r, curVal);
						setValue(c, r, 0);
					}
				}
			}
		}
		if (!isFull()) {
			addTile();
		}
		
		return pointCounter;
	}

	@Override
	public int down() {
		// TODO fix only one slide
		int pointCounter = 0;
		for (int c = 0; c <= 3; c++) {
			for (int r = 2; r >= 0; r--) {
				int curVal = getValue(c, r);
				int compVal = getValue(c, r+1);;
				if (curVal == compVal) {
					int newVal = curVal * 2;
					
					setValue(c, r, 0);
					setValue(c, r+1, newVal);
					pointCounter = pointCounter + newVal;
					if (r > 1) {
						r--;
					}
				} else {
					if (compVal == 0) {
						setValue(c, r+1, curVal);
						setValue(c, r, 0);
					}
				}
			}
		}
		if (!isFull()) {
			addTile();
		}
		
		return pointCounter;
	}

	@Override
	public int up() {
		// TODO fix only one slide
		int pointCounter = 0;
		for (int c = 0; c <= 3; c++) {
			for (int r = 1; r < 4; r++) {
				int curVal = getValue(c, r);
				int compVal = getValue(c, r-1);
				if (curVal == compVal) {
					int newVal = curVal * 2;
					
					setValue(c, r, 0);
					setValue(c, r-1, newVal);
					pointCounter = pointCounter + newVal;
					if (r < 2) {
						r++;
					}
				} else {
					if (compVal == 0) {
						setValue(c, r-1, curVal);
						setValue(c, r, 0);
					}
				}
			}
		}
		if (!isFull()) {
			addTile();
		}
		
		return pointCounter;
	}

}
