import java.util.ArrayList;

public class FinallyData {
	private int[] pow;
	private int[] led;
	private int powlin;
	private int ledlin;
	private int[][] cost;
	private char[][] b;
	private LinkedList Result = new LinkedList();// do linkedList to store the result

	public FinallyData(int[] pow, int[] led) {
		super();
		this.pow = pow;
		powlin = this.pow.length;

		this.led = led;
		ledlin = this.led.length;

		cost = new int[powlin + 1][ledlin + 1];
		b = new char[powlin + 1][ledlin + 1];

		CulcData(); // call CulcData method
		DoResult(b, this.pow, powlin, ledlin); // call DoResult method

	}

	// this method to do DP table/array
	private void CulcData() {

		for (int i = 1; i <= powlin; i++) {
			for (int j = 1; j <= ledlin; j++) {
				if (pow[i - 1] == led[j - 1]) {
					cost[i][j] = cost[i - 1][j - 1] + 1;
					b[i][j] = 'D'; // Diagonally
				} else if (cost[i][j - 1] >= cost[i - 1][j]) {
					cost[i][j] = cost[i][j - 1];
					b[i][j] = 'L'; // Left
				} else {
					cost[i][j] = cost[i - 1][j];
					b[i][j] = 'U'; // Up
				}

			}
		}
	}

	private void DoResult(char[][] b, int[] x, int i, int j) {

		if (i == 0 || j == 0) {
			return;
		} else if (b[i][j] == 'D') {
			DoResult(b, x, i - 1, j - 1);
			System.out.print(x[i - 1] + " ");
			Result.add(x[i - 1]);
		} else if (b[i][j] == 'L') {
			DoResult(b, x, i, j - 1);
		} else {
			DoResult(b, x, i - 1, j);
		}
	}

	public LinkedList getResult() {
		return Result;
	}

	public int[] getPow() {
		return pow;
	}

	public void setPow(int[] pow) {
		this.pow = pow;
	}

	public int[] getLed() {
		return led;
	}

	public void setLed(int[] led) {
		this.led = led;
	}

	public int getPowlin() {
		return powlin;
	}

	public void setPowlin(int powlin) {
		this.powlin = powlin;
	}

	public int getLedlin() {
		return ledlin;
	}

	public void setLedlin(int ledlin) {
		this.ledlin = ledlin;
	}

	public int[][] getCost() {

		return cost;
	}

	public void setCost(int[][] cost) {
		this.cost = cost;
	}

	public char[][] getB() {
		return b;
	}

	public void setB(char[][] b) {
		this.b = b;
	}

	public void setResult(LinkedList result) {
		Result = result;
	}

}

/*	for (int i = 1; i <= powlin; i++)
			cost[i][0] = 0; // initial value from cost

		for (int i = 1; i <= ledlin; i++)
			cost[0][i] = 0; // initial value from cost */