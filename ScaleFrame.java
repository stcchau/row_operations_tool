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
public class ScaleFrame extends JFrame implements ActionListener {
	
	private ButtonGroup group1 = new ButtonGroup();
	private JRadioButton[] rowButtonArray;
	private final JTextField divisorTextField = new JTextField(2);
	private final JButton enterButton = new JButton("Enter");
	private final int rows;
	private int[] arr = new int[2];
	
	public ScaleFrame(String title, int length, int width, int rows) {
		
		super(title);
		this.rows = rows;
		rowButtonArray = new JRadioButton[rows];
		final JLabel scaleRowLabel = new JLabel("Row to Scale:"), 
				divisorLabel = new JLabel("Divisor:");
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
		
		enterButton.addActionListener(this);
		
		bottomPanel.add(divisorLabel);
		bottomPanel.add(divisorTextField);
		bottomPanel.add(enterButton);
		
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
	
	public void setScaleArgs() {
		arr[0] = getRow();
		arr[1] = Integer.parseInt(divisorTextField.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			setScaleArgs();
			Launcher.display1.display2.display3.scaleRow(arr[0], arr[1]);
			dispose();
		}
	}
}
