import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MySQL {

	private JFrame frmNvelX;
	private JTable table;
	public Object[][] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQL window = new MySQL();
					window.frmNvelX.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MySQL() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNvelX = new JFrame();
		frmNvelX.setTitle("N\u00EDvel X");
		frmNvelX.setBounds(100, 100, 533, 571);
		frmNvelX.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		System.out.println(data[0][1]);
		frmNvelX.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable(data, columnNames);
		table.setBackground(Color.WHITE);
		
		frmNvelX.getContentPane().add(table);
		
		resetTable();
		
		
	}

	public void resetTable() {
		System.out.println(data[0][1]);
		Object[][] data = {{""}};
		table.revalidate();
		System.out.println(data);
		
	}
}
