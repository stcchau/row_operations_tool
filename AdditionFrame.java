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
public class AdditionFrame extends JFrame implements ActionListener {
	
	private JRadioButton[] addeeButtonArray, adderButtonArray;
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	private final JTextField multiplierTextField = new JTextField(2);
	private final JButton enterButton = new JButton("Enter");
	private final int rows;
	private int[] arr = new int[3];
	
	public AdditionFrame (String title, int length, int width, int rows) {
		
		super(title);
		this.rows = rows;
		addeeButtonArray = new JRadioButton[rows];
		adderButtonArray = new JRadioButton[rows];
		final JLabel addeeRowLabel = new JLabel("Row to Replace:"), 
				adderRowLabel = new JLabel("Row to Add With:"), 
				scalarLabel = new JLabel("Multiplier:");
		final JPanel addeeRowPanel = new JPanel(), 
				adderRowPanel = new JPanel(), 
				bottomPanel = new JPanel();
		
		setSize(length, width);
		setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addeeRowPanel.setLayout(new GridLayout(rows + 1, 1));
		adderRowPanel.setLayout(new GridLayout(rows + 1, 1));
		bottomPanel.setLayout(new FlowLayout());
		
		JPanel addeeRowSubPanel = new JPanel();
		addeeRowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addeeRowSubPanel.add(addeeRowLabel);
		addeeRowPanel.add(addeeRowSubPanel);
		
		JPanel adderRowSubPanel = new JPanel();
		adderRowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		adderRowSubPanel.add(adderRowLabel);
		adderRowPanel.add(adderRowSubPanel);
		
		enterButton.addActionListener(this);
		
		bottomPanel.add(scalarLabel);
		bottomPanel.add(multiplierTextField);
		bottomPanel.add(enterButton);
		
		for(int r = 0; r < rows; r++) {
			addeeRowSubPanel = new JPanel();
			addeeRowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			addeeButtonArray[r] = new JRadioButton(String.format("Row %s", r + 1));
			group1.add(addeeButtonArray[r]);
			addeeRowSubPanel.add(addeeButtonArray[r]);
			addeeRowPanel.add(addeeRowSubPanel);
			
			adderRowSubPanel = new JPanel();
			adderRowSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			adderButtonArray[r] = new JRadioButton(String.format("Row %s", r + 1));
			group2.add(adderButtonArray[r]);
			adderRowSubPanel.add(adderButtonArray[r]);
			adderRowPanel.add(adderRowSubPanel);
		}
		
		add(addeeRowPanel, BorderLayout.WEST);
		add(adderRowPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	private int getAddeeRow() {
		for(int r = 0; r < rows; r++) {
			if(addeeButtonArray[r].isSelected()) {
				return r;
			}
		}
		return 0;
	}
	
	private int getAdderRow() {
		for(int r = 0; r < rows; r++) {
			if(adderButtonArray[r].isSelected()) {
				return r;
			}
		}
		return 0;
	}
	
	public int getMultiplier() {
		try {
			return Integer.parseInt(multiplierTextField.getText());
		}
		catch(Exception e) {
			return 1;
		}
	}
	
	public void setAddArgs() {
		arr[0] = getAddeeRow();
		arr[1] = getAdderRow();
		arr[2] = getMultiplier();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			setAddArgs();
			Launcher.display1.display2.display3.addToRow(arr[0], arr[1], arr[2]);
			dispose();
		}
	}
}
