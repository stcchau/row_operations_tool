/**
 * Please do not steal.
 */
package row_operations_tool;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author San Chau
 *
 */
public class ScaleFrame extends JFrame implements ActionListener {
	
	private final ButtonGroup BUTTON_GROUP = new ButtonGroup();
	private final JRadioButton[] ROW_BUTTON_ARRAY;
	private final JTextField SCALAR_TEXT_FIELD = new JTextField(2);
	private final JButton MULTIPLY_BUTTON = new JButton("Multiply"),
			DIVIDE_BUTTON = new JButton("Divide");
	private final int ROWS;
	private final MatrixFrame MATRIX_FRAME;
	
	public ScaleFrame(String title, int length, int width, int rows, MatrixFrame matrixFrame) {
		
		super(title);
		ROWS = rows;
		ROW_BUTTON_ARRAY = new JRadioButton[rows];
		final JLabel scaleRowLabel = new JLabel("Select Row to Scale:"), 
				scalarLabel = new JLabel("Scalar:");
		final JPanel rowPanel = new JPanel(), 
				bottomPanel = new JPanel();
		MATRIX_FRAME = matrixFrame;
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		
		rowPanel.setLayout(new GridLayout(rows + 1, 1));
		
		JPanel rowSubPanel = new JPanel();
		rowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		rowSubPanel.add(scaleRowLabel);
		rowPanel.add(rowSubPanel);
		
		MULTIPLY_BUTTON.addActionListener(this);
		DIVIDE_BUTTON.addActionListener(this);
		
		bottomPanel.add(scalarLabel);
		bottomPanel.add(SCALAR_TEXT_FIELD);
		bottomPanel.add(MULTIPLY_BUTTON);
		bottomPanel.add(DIVIDE_BUTTON);
		
		for(int r = 0; r < rows; r++) {
			rowSubPanel = new JPanel();
			rowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			ROW_BUTTON_ARRAY[r] = new JRadioButton(String.format("Row %s", r + 1));
			BUTTON_GROUP.add(ROW_BUTTON_ARRAY[r]);
			rowSubPanel.add(ROW_BUTTON_ARRAY[r]);
			rowPanel.add(rowSubPanel);
		}
		
		add(rowPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private int getRow() throws Exception {
		for(int r = 0; r < ROWS; r++) {
			if(ROW_BUTTON_ARRAY[r].isSelected()) {
				return r;
			}
		}
		throw new Exception();
	}
	
	public Number getScalar() {
		if(SCALAR_TEXT_FIELD.getText().equals(""))
			return new Number(1, 1);
		return Number.parseNumber(SCALAR_TEXT_FIELD.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == MULTIPLY_BUTTON) {
			try {
				if(getScalar().getDenominator() == 0)
					JOptionPane.showMessageDialog(null, "Cannot divide by zero. Please try again.");
				else {
					MATRIX_FRAME.multiplyRow(getRow(), getScalar());
					dispose();
				}
			}
			catch(Exception e) {
				try {
					getRow();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "A row must be selected. Please try again.");
				}
				try {
					getScalar();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
				}
			}
		}
		
		if(event.getSource() == DIVIDE_BUTTON) {
			try {
				if(getScalar().getNumerator() == 0 || getScalar().getDenominator() == 0)
					JOptionPane.showMessageDialog(null, "Cannot divide by zero. Please try again.");
				else {
					MATRIX_FRAME.divideRow(getRow(), getScalar());
					dispose();
				}
			}
			catch(Exception e) {
				try {
					getRow();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "A row must be selected. Please try again.");
				}
				try {
					getScalar();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "All inputs must be integers or fractions. Please try again.");
				}
			}
		}
	}
}
