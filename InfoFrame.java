package matricestool;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 */

/**
 * @author San Chau
 *
 */
public class InfoFrame extends JFrame implements ActionListener {
	
	private final JLabel rowsLabel = new JLabel("Rows:");
	private final JLabel columnsLabel = new JLabel("Columns:");
	private final JTextField rows = new JTextField(1);
	private final JTextField columns = new JTextField(1);
	private final JButton enterButton = new JButton("Enter");
	
	public MatrixFillInFrame display2;
	
	public InfoFrame (String title, int length, int width) {
		super(title);
		setSize(length, width);
		setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(rowsLabel);
		add(rows);
		add(columnsLabel);
		add(columns);
		add(enterButton);
		
		enterButton.addActionListener(this);
		
	}
	
	public int getRows() {
		return Integer.parseInt(rows.getText());
	}
	
	public int getColumns() {
		return Integer.parseInt(columns.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == enterButton) {
			display2 = new MatrixFillInFrame("Matrices Tool", 500, 200, getRows(), getColumns());
			dispose();
		}
	}
}
