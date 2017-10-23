/**
 * 
 */
package matricestool;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author San Chau
 *
 */
public class MatrixFillInFrame extends JFrame implements ActionListener {
	
	private final JButton enterButton = new JButton("Enter Numbers");
	private final int rows;
	private final int columns;
	private JTextField[][] JTFMatrix;
	private JPanel textFieldPanel = new JPanel();
	
	public MatrixFrame display3;
	
	public MatrixFillInFrame(String title, int length, int width, int rows, int columns) {
		
		super(title);
		this.rows = rows;
		this.columns = columns;
		JTFMatrix = new JTextField[rows][columns]; 
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textFieldPanel.setLayout(new GridLayout(rows, columns));
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				JPanel textFieldSubPanel = new JPanel();
				textFieldSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				textFieldSubPanel.add(JTFMatrix[row][column] = new JTextField(2));
				textFieldPanel.add(textFieldSubPanel);
			}
		}
		
		enterButton.addActionListener(this);
		add(textFieldPanel,BorderLayout.CENTER);
		add(enterButton, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			double[][] matrix = new double[rows][columns];
			for(int row = 0; row < rows; row++) {
				for(int column = 0; column < columns; column++) {
					matrix[row][column] = Integer.parseInt(JTFMatrix[row][column].getText());
				}
			}
			display3 = new MatrixFrame("Matrices Tool", 500, 200, matrix, rows, columns);
			dispose();
		}
	}
}
