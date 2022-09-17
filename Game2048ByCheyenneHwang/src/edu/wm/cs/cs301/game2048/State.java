package edu.wm.cs.cs301.game2048;

public class State implements GameState {
	
	private int[][] board;
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
		// TODO MERGE NOT WORKING STILL!! not merging beyond full slide even though point coutner updating, also need to get rid of add tile - connected to game state
		int pointCounter = 0;
//		for (int r = 0; r < 4; r++){
//			for (int c = 1; c < 4; c++) {
//				int curVal = getValue(c, r);
//				int compVal = getValue(c-1, r);
//				if (curVal == compVal) {
//					int newVal = curVal * 2;
//					
//					setValue(c, r, 0);
//					setValue(c-1, r, newVal);
//					pointCounter = pointCounter + newVal;
//					if (c < 2) {
//						c++;
//					}
//				} else {
//					if (compVal == 0) {
//						setValue(c-1, r, curVal);
//						setValue(c, r, 0);
//					}
//				}
//			}
//		}
		
		for (int r = 0; r < 4; r++) {//scenario: run through each row
			int[] mergedColVal = new int[4];
			for (int c = 1; c < 4; c++) {//scenario: checking each space in the row, starting with the left
				int curVal = getValue(c, r);
				if (curVal != 0) {//scenario: found the left most tile that needs to be slid
					boolean fullSlide = true;
					for (int i = c-1; i >= 0; i--) {//scenario: checking all the spaces to the left of examined tile
						int compVal = getValue(i, r);
						if (compVal != 0) {//scenario: found the left most space the tile can move into
							fullSlide = false;
							int newVal = curVal * 2;
							
							if ((mergedColVal[i] == 0) && (compVal == curVal) && ((i+1) == c)) {//scenario: left most space it can occupy is the result of a merge only			
								setValue(i, r, newVal);
								setValue(c, r, 0);
								pointCounter = pointCounter + newVal;
								mergedColVal[i] = 1;
							}else if ((mergedColVal[i] == 0) && (compVal == curVal) && ((i+1) != c)){//scenario: left most space it can occupy is result of a slide + merge
								setValue(i, r, newVal);
								setValue(c, r, 0);
								pointCounter = pointCounter + newVal;
								mergedColVal[i] = 1;
							}else if (((i+1) != c) && ((mergedColVal[i] == 1) || (compVal != curVal))) {//scenario: left most space it can occupy is the result of a slide only (position change but no merge)
								setValue(i+1, r, curVal);
								setValue(c, r, 0);
							}
						}
					if (fullSlide) {//scenario: the left most tile goes all the way to the left with no interruption
						setValue(0, r, curVal);
						setValue(c, r, 0);
					}
					}			
				}
			}
		}
		if (!isFull()) {//CONSIDER - instead of adding it update the game so that ends if game over (2048 or board full with no merge), game not over (add tile by changing game state), or not change game state if board full but canMerge()
			addTile();
		}
		
		
		return pointCounter;
	}

	@Override
	public int right() {
		// TODO fix only one slide, get rid of add tile - connected to updating game state
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
		// TODO fix only one slide, get rid of add tile - connected to updating game state
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
		// TODO fix only one slide, get rid of add tile - connected to updating game state
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
