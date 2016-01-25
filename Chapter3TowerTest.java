public class Chapter3TowerTest {

    public static void main(String[] args) {
	int n = 5;
	Chapter3Tower[] towers = new Chapter3Tower[n];
	for (int i = 0; i < 3; i++) { towers[i] = new Chapter3Tower(i); }
	for (int i = n; i >= 1; i--) { towers[0].add(i); }

	for (int i = 0; i < 3; i++) { 
	    towers[i].print();
	}
	System.out.println();
	
	towers[0].moveDisks(n, towers[2], towers[1]);

	for (int i = 0; i < 3; i++) { 
	    towers[i].print();
	}
	System.out.println();
    }

}