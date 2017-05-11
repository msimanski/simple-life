import java.util.Random;

public class world {
	
	int[][] worldstate = new int[1000][1000];
	
	public world(int[][] world) {
		
		Random rdm = new Random(); // set up random
		int randone = 0;
		int randtwo = 0;
		
		for(int i = 0; i < 1000; i++) { // random cells
				
				randone = rdm.nextInt(900) + 1;
				randtwo = rdm.nextInt(900) + 1;
				
				world[randone][randtwo] = 1; // build random movers
				world[randone + 1][randtwo] = 1;
				world[randone + 2][randtwo] = 1;
				world[randone + 2][randtwo - 1] = 1;
				world[randone + 1][randtwo - 1] = 1;
						
		}
		
		world[500][400] = 1; // build center point
		world[499][400] = 1;
		world[501][400] = 1;
		world[500][401] = 1;
		world[500][399] = 1;
		this.worldstate = world;
		
	}
	
	public int getWorldStateAt(int i, int j) { // see if living or dead at i,j
		
		int temp = this.worldstate[i][j];
		return temp;
		
	}
	
	public void placeCell(int x, int y) { // not used, for mouse placement
		
		this.worldstate[x][y] = 1;
		
	}
	
	public void updateWorldState() { // main algorithm
		
		int n1,n2,n3,n4,n5,n6,n7,n8,total; // all possible buddies of a cell
		
		for(int i = 1; i < 999; i++) { // go to each cell
			for(int j = 1; j < 999; j++) {
				
				n1 = this.worldstate[i-1][j]; // look up the neighborhood
				n2 = this.worldstate[i][j-1];
				n3 = this.worldstate[i-1][j-1];
				n4 = this.worldstate[i+1][j];
				n5 = this.worldstate[i][j+1];
				n6 = this.worldstate[i+1][j+1];
				n7 = this.worldstate[i+1][j-1];
				n8 = this.worldstate[i-1][j+1];
				
				total = n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8; // add them up
				
				if(total < 2 && (this.worldstate[i][j] == 1)) { // rule 1
					
					this.worldstate[i][j] = 0;
					
				}
				
				if((total == 2 || total == 3) && (this.worldstate[i][j] == 1)) { // rule 2
					
					this.worldstate[i][j] = 1;
					
				}
				
				if(total > 3 && (this.worldstate[i][j] == 1)) { // rule 3
					
					this.worldstate[i][j] = 0;
					
				}
				
				if((total == 3) && (this.worldstate[i][j] == 0)) { // rule 4
					
					this.worldstate[i][j] = 1;
					
				}
				
			}
		}
		
	}

}
