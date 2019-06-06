package vn.edu.vnuk.swing.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import vn.edu.vnuk.swing.controller.Controller;
import vn.edu.vnuk.swing.dao.CasualWorkerDao;
import vn.edu.vnuk.swing.dao.LecturerDao;
import vn.edu.vnuk.swing.dao.PersonDao;
import vn.edu.vnuk.swing.dao.StaffDao;
import vn.edu.vnuk.swing.define.Define;
import vn.edu.vnuk.swing.model.CasualWorker;
import vn.edu.vnuk.swing.model.Lecturer;
import vn.edu.vnuk.swing.model.Person;
import vn.edu.vnuk.swing.model.Staff;
import vn.edu.vnuk.swing.util.CommonUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {
	private JTextField tbxSearching;
	private JComboBox<String> cbxSearchingChoices;
	private JButton btnSearch;
	private JTable table;
	private JPopupMenu popup;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(new PersonDao().read(1L).toString());
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public void reloadTableData() {
		DefaultTableModel model = null;
		String keyword = tbxSearching.getText();
		String sortBy = cbxSearchingChoices.getSelectedItem().toString();
		
		try {
			switch (sortBy) {
			case "Name":
				model = new DefaultTableModel(CommonUtils.sortByName(loadRowData(keyword)), loadColumnNames());
				break;
			case "Salary":
				model = new DefaultTableModel(CommonUtils.sortBySalary(loadRowData(keyword)), loadColumnNames());
				break;
			}
				
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		table.setModel(model);
	}
	
	public MainMenu() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 483);
		getContentPane().setLayout(null);
		String[] searchingChoices = {"Name", "Salary"};
		cbxSearchingChoices = new JComboBox(searchingChoices);
		cbxSearchingChoices.setBounds(120, 130, 118, 42);
		getContentPane().add(cbxSearchingChoices);
		
		tbxSearching = new JTextField();
		tbxSearching.setBounds(20, 261, 308, 42);
		getContentPane().add(tbxSearching);
		tbxSearching.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadTableData();
			}
		});
		btnSearch.setBounds(367, 11, 149, 37);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 58, 430, 353);
		getContentPane().add(scrollPane);
		
		table = new JTable(loadRowData(""), loadColumnNames()) {
			public boolean isCellEditable(int row,int column){  
				return false;
		    }
		
		};
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		
		scrollPane.setViewportView(table);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Select");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddNewEmployee = new JMenuItem("Add new employee");
		mntmAddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewEmployee addNewEmployeeFrame = new AddNewEmployee(new Person(), Define.TYPE_OF_ACTION_CREATE);
				addNewEmployeeFrame.setSize(500, 510);
				centreWindow(addNewEmployeeFrame);
				addNewEmployeeFrame.setVisible(true);
			}
		});
		mnFile.add(mntmAddNewEmployee);
		
		JMenuItem mntmUpdateBasicSalary = new JMenuItem("Update basic salary");
		mntmUpdateBasicSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateBasicSalary updateBasicSalary = new UpdateBasicSalary();
				updateBasicSalary.setVisible(true);
			}
		});
		mnFile.add(mntmUpdateBasicSalary);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		popup = new JPopupMenu();
		JMenuItem deleteMenuItem = new JMenuItem("Delete employee");
		deleteMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete");
				try {
					switch(getTypeSelected()) {
					case Define.TYPE_OF_LECTURER:
						new LecturerDao().delete(getPersonIDSelected());
						break;
					
					case Define.TYPE_OF_STAFF:
						new StaffDao().delete(getPersonIDSelected());
						break;
					
					case Define.TYPE_OF_CASUAL_WORKER:
						new CasualWorkerDao().delete(getPersonIDSelected());
						break;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				reloadTableData();
			}
		});
		
		JMenuItem viewMenuItem = new JMenuItem("View details employee");
		viewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Person person = null;
				try {
					person = new PersonDao().read(getPersonIDSelected());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddNewEmployee addNewEmployee = new AddNewEmployee(person, Define.TYPE_OF_ACTION_EDIT);
				addNewEmployee.setSize(500, 510);
				centreWindow(addNewEmployee);	
				addNewEmployee.setVisible(true);
			}
		});
		
		popup.add(deleteMenuItem);
		popup.add(viewMenuItem);
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	private int getTypeSelected() {
		int result = 0;
		switch ((String) table.getValueAt(table.getSelectedRow(), 1)) {
		case "LECTURER":
			result = Define.TYPE_OF_LECTURER;
			break;
			
		case "STAFF":
			result = Define.TYPE_OF_STAFF;
			break;
			
		case "CASUAL WORKER":
			result = Define.TYPE_OF_CASUAL_WORKER;
			break;
		}
		
		return result;
	}
	
	private long getPersonIDSelected() {
		return (long) table.getValueAt(table.getSelectedRow(), 0);
	}
	
	private Object[] loadColumnNames() {
		return new Object[] {"ID", "Type", "Name", "Salary"};
	}
	
	private Object[][] loadRowData(String keyword) throws SQLException {
		Controller controller = new Controller();
		ArrayList<Person> persons = controller.getAllPersons(keyword);
		
		Object[][] obj = new Object[persons.size()][4];
		
		for (int index = 0; index < persons.size(); index++) {
			obj[index][0] = persons.get(index).getId();
			obj[index][1] = CommonUtils.getTypeString(persons.get(index).getType());
			obj[index][2] = persons.get(index).getName();
			
			switch (persons.get(index).getType()) {
			case Define.TYPE_OF_STAFF: {
				obj[index][3] = ((Staff) persons.get(index)).getSalary();
				break;
			}
			
			case Define.TYPE_OF_LECTURER: {
				obj[index][3] = ((Lecturer) persons.get(index)).getSalary();
				break;
			}
			
			case Define.TYPE_OF_CASUAL_WORKER: {
				obj[index][3] = ((CasualWorker) persons.get(index)).getSalary();
				break;
			}
			}
		}
		
		return obj;
	}
	
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}