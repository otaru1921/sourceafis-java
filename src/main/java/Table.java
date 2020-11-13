import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;

import java.sql.*;  


public class Table {

	public JFrame getFrmNivelX() {
		return frmNivelX;
	}

	public void setFrmNivelX(JFrame frmNivelX) {
		this.frmNivelX = frmNivelX;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	private JFrame frmNivelX;
	private JTable table;
	public String nivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Linha abaixo é desnecessária ao instanciar.
					Table window = new Table();
					window.frmNivelX.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	// Importante para instanciar corretamente.
	public void run() {
		try {
			this.frmNivelX.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Método construtor que vai executar o nível.
	public Table(String nivel) {
		this.nivel = nivel;
		initialize(nivel);
		frmNivelX.setTitle("Acesso ao Banco de Dados de nível "+nivel);
	}
	
	public Table() {
		nivel = "1";
		initialize(nivel);
	}
	
	
	public void resetTable()
	{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
	}
	
	public void select(String nivel2) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/fazenda?useTimezone=true&serverTimezone=UTC","nivel_"+nivel2,"aps");   
			PreparedStatement stmt=con.prepareStatement("select * from select_"+nivel2);  
			ResultSet rs=stmt.executeQuery();  
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.revalidate();
	    }
	    catch(Exception e) {
	    }
	    
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nivel) {
		frmNivelX = new JFrame();
		frmNivelX.setTitle("Nivel X");
		frmNivelX.setBounds(100, 100, 896, 529);
		frmNivelX.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNivelX.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 860, 468);
		frmNivelX.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Teste", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		//System.out.println(nivel);
		select(nivel);
		
		//resetTable();
		
	}
}
