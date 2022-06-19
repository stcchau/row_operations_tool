
import java.util.Scanner;

public class RowOperationsTool {
	
	public static void displayMatrix(double[][] matrix) {
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[row].length; col++) {
				System.out.print(matrix[row][col] + "\t");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void addToRow(double[] addee, double[] adder, double multiplier) {
		for(int col = 0; col < adder.length; col++) {
			addee[col] += multiplier*adder[col];
		}
	}
	
	public static void divideRow(double[] row, double divisor) {
		for(int i = 0; i < row.length; i++) {
			row[i] /= divisor;
			if(row[i] == -0)
				row[i] = 0;
		}
	}
	
	public static void switchRows(double[][] matrix, int row1, int row2) {
		double[] temp = matrix[row1];
		matrix[row1] = matrix[row2];
		matrix[row2] = temp;
	}
	
	public static void solveMatrix(double[][] matrix) {
		for(int col = 0; col < matrix[0].length - 1; col++) {
			for(int row = 0; row < matrix.length; row++) {
				if(row == col) {
					if(matrix[row][col] != 0) {
						divideRow(matrix[row], matrix[row][col]);
						for(int r = 0; r < matrix.length; r++) {
							if(r != row && matrix[r][col] != 0) {
								addToRow(matrix[r], matrix[row], -(matrix[r][col]));
							}
						}
					}
					else {
						for(int r = 0; r < matrix.length; r++) {
							if(matrix[r][col] != 0) {
								switchRows(matrix, r, row);
								divideRow(matrix[row], matrix[row][col]);
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Rows: ");
		int rows = input.nextInt();
		System.out.print("Columns: ");
		int cols = input.nextInt();
		double[][] matrix = new double[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				System.out.printf("Insert row %s column %s: ",row+1,col+1);
				matrix[row][col]=input.nextDouble();
			}
		}
		displayMatrix(matrix);
		while(true) {
			System.out.println("0: addToRow()\n1: scaleRow()\n2: switchRows()\n3: solveMatrix()");
			System.out.print("What operation?: ");
			int op = input.nextInt();
			switch(op) {
				case 0:
					System.out.print("Row to replace: \nRow ");
					int addee = input.nextInt();
					System.out.print("Row to add with: \nRow ");
					int adder = input.nextInt();
					System.out.print("Multiplier: ");
					double multiplier = input.nextDouble();
					addToRow(matrix[addee-1], matrix[adder-1], multiplier);
					displayMatrix(matrix);
					break;
				case 1:
					System.out.print("Row to divide: \nRow ");
					int row = input.nextInt();
					System.out.print("Divisor: ");
					double divisor = input.nextDouble();
					divideRow(matrix[row-1], divisor);
					displayMatrix(matrix);
					break;
				case 2:
					System.out.print("Rows to switch: \nRow ");
					int row1 = input.nextInt();
					System.out.print("and Row ");
					int row2 = input.nextInt();
					switchRows(matrix, row1-1, row2-1);
					displayMatrix(matrix);
					break;
				case 3:
					solveMatrix(matrix);
					displayMatrix(matrix);
					break;
			}
		}
	}
}
