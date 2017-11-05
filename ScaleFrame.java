/**
 * 
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
	
	private ButtonGroup group1 = new ButtonGroup();
	private JRadioButton[] rowButtonArray;
	private final JTextField scalarTextField = new JTextField(2);
	private final JButton multiplyButton = new JButton("Multiply"),
			divideButton = new JButton("Divide");
	private final int rows;
	private int[] arr = new int[2];
	
	public ScaleFrame(String title, int length, int width, int rows) {
		
		super(title);
		this.rows = rows;
		rowButtonArray = new JRadioButton[rows];
		final JLabel scaleRowLabel = new JLabel("Row to Scale:"), 
				scalarLabel = new JLabel("Scalar:");
		final JPanel rowPanel = new JPanel(), 
				bottomPanel = new JPanel();
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rowPanel.setLayout(new GridLayout(rows + 1, 1));
		
		JPanel rowSubPanel = new JPanel();
		rowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		rowSubPanel.add(scaleRowLabel);
		rowPanel.add(rowSubPanel);
		
		multiplyButton.addActionListener(this);
		divideButton.addActionListener(this);
		
		bottomPanel.add(scalarLabel);
		bottomPanel.add(scalarTextField);
		bottomPanel.add(multiplyButton);
		bottomPanel.add(divideButton);
		
		for(int r = 0; r < rows; r++) {
			rowSubPanel = new JPanel();
			rowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			rowButtonArray[r] = new JRadioButton(String.format("Row %s", r + 1));
			group1.add(rowButtonArray[r]);
			rowSubPanel.add(rowButtonArray[r]);
			rowPanel.add(rowSubPanel);
		}
		
		add(rowPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private int getRow() {
		for(int r = 0; r < rows; r++) {
			if(rowButtonArray[r].isSelected()) {
				return r;
			}
		}
		return 0;
	}
	
	public int getScalar() {
		try {
			return Integer.parseInt(scalarTextField.getText());
		}
		catch(Exception e) {
			return 1;
		}
	}
	
	public void setScaleArgs() {
		arr[0] = getRow();
		arr[1] = Integer.parseInt(scalarTextField.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == multiplyButton) {
			setScaleArgs();
			Launcher.display1.display2.display3.multiplyRow(arr[0], arr[1]);
			dispose();
		}
		if(event.getSource() == divideButton) {
			setScaleArgs();
			Launcher.display1.display2.display3.divideRow(arr[0], arr[1]);
			dispose();
		}
	}
}
