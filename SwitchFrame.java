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
public class SwitchFrame extends JFrame implements ActionListener {
	private JRadioButton[] row1ButtonArray, row2ButtonArray;
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	private final JButton enterButton = new JButton("Enter");
	private final int rows;
	private int[] arr = new int[2];
	
	public SwitchFrame (String title, int length, int width, int rows) {
		
		super(title);
		this.rows = rows;
		row1ButtonArray = new JRadioButton[rows];
		row2ButtonArray = new JRadioButton[rows];
		final JLabel row1Label = new JLabel("Row 1:"), 
				row2Label = new JLabel("Row 2:");
		final JPanel row1Panel = new JPanel(), 
				row2Panel = new JPanel();
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		
		row1Panel.setLayout(new GridLayout(rows + 1, 1));
		row2Panel.setLayout(new GridLayout(rows + 1, 1));
		
		JPanel row1SubPanel = new JPanel();
		row1SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		row1SubPanel.add(row1Label);
		row1Panel.add(row1SubPanel);
		
		JPanel row2SubPanel = new JPanel();
		row2SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		row2SubPanel.add(row2Label);
		row2Panel.add(row2SubPanel);
		
		enterButton.addActionListener(this);
		
		for(int r = 0; r < rows; r++) {
			row1SubPanel = new JPanel();
			row1SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			row1ButtonArray[r] = new JRadioButton(String.format("Row %s", r + 1));
			group1.add(row1ButtonArray[r]);
			row1SubPanel.add(row1ButtonArray[r]);
			row1Panel.add(row1SubPanel);
			
			row2SubPanel = new JPanel();
			row2SubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			row2ButtonArray[r] = new JRadioButton(String.format("Row %s", r + 1));
			group2.add(row2ButtonArray[r]);
			row2SubPanel.add(row2ButtonArray[r]);
			row2Panel.add(row2SubPanel);
		}
		
		add(row1Panel, BorderLayout.WEST);
		add(row2Panel, BorderLayout.CENTER);
		add(enterButton, BorderLayout.SOUTH);
		
	}
	
	public int getRow1() {
		for(int r = 0; r < rows; r++) {
			if(row1ButtonArray[r].isSelected()) {
				return r;
			}
		}
		return 0;
	}
	
	public int getRow2() {
		for(int r = 0; r < rows; r++) {
			if(row2ButtonArray[r].isSelected()) {
				return r;
			}
		}
		return 0;
	}
	
	public void setSwitchArgs() {
		arr[0] = getRow1();
		arr[1] = getRow2();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			setSwitchArgs();
			Launcher.display1.display2.display3.switchRow(arr[0], arr[1]);
			dispose();
		}
	}
}
