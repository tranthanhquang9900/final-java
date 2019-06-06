package vn.edu.vnuk.swing.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.edu.vnuk.swing.dao.CasualWorkerDao;
import vn.edu.vnuk.swing.dao.LecturerDao;
import vn.edu.vnuk.swing.dao.StaffDao;
import vn.edu.vnuk.swing.define.Define;
import vn.edu.vnuk.swing.model.CasualWorker;
import vn.edu.vnuk.swing.model.Lecturer;
import vn.edu.vnuk.swing.model.Person;
import vn.edu.vnuk.swing.model.Staff;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddNewEmployee extends JFrame {
	private JComboBox<String> cbxtypeOfEmployee;
	private JTextField tbxId;
	private JTextField tbxFullname;
	private JTextField tbxYearOfBirth;
	private WorkerPanel workerPanel;
	private StaffPanel staffPanel;
	private LecturerPanel lecturerPanel;
	private JButton btnAction;
	
	private int typeOfActionStore;
	private JFrame frame;
	/**
	 * Create the panel.
	 */
	public AddNewEmployee(Person person, int typeOfAction) {
		this.typeOfActionStore = typeOfAction;	
		getContentPane().setLayout(null);
		frame = this;
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(63, 22, 77, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(63, 60, 77, 21);
		getContentPane().add(lblType);
		
		JLabel lblFullname = new JLabel("Fullname :");
		lblFullname.setBounds(63, 98, 103, 21);
		getContentPane().add(lblFullname);
		
		JLabel lblYearOfBirth = new JLabel("Year of birth :");
		lblYearOfBirth.setBounds(63, 136, 150, 21);
		getContentPane().add(lblYearOfBirth);
		
		tbxId = new JTextField();
		tbxId.setEditable(false);
		tbxId.setBounds(231, 20, 132, 26);
		tbxId.setColumns(10);
		getContentPane().add(tbxId);
		
		tbxFullname = new JTextField();
		tbxFullname.setBounds(231, 96, 132, 26);
		tbxFullname.setColumns(10);
		getContentPane().add(tbxFullname);
		
		tbxYearOfBirth = new JTextField();
		tbxYearOfBirth.setBounds(231, 134, 132, 26);
		tbxYearOfBirth.setColumns(10);
		getContentPane().add(tbxYearOfBirth);
		
		cbxtypeOfEmployee = new JComboBox<String>();		
		cbxtypeOfEmployee.setEditable(false);
		cbxtypeOfEmployee.setBounds(231, 58, 132, 26);
		cbxtypeOfEmployee.addItem(Define.NAME_OF_LECTURER);
		cbxtypeOfEmployee.addItem(Define.NAME_OF_STAFF);
		cbxtypeOfEmployee.addItem(Define.NAME_OF_CASUAL_WORKER);
		getContentPane().add(cbxtypeOfEmployee);
		// 63 172
		cbxtypeOfEmployee.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setEmployeePanelVisibleByType(cbxtypeOfEmployee.getSelectedIndex());
			}
		});
		
		workerPanel = new WorkerPanel();
		workerPanel.setBounds(454, 17, 385, 64);
		workerPanel.setVisible(false);
		getContentPane().add(workerPanel);
		
		staffPanel = new StaffPanel();
		staffPanel.setBounds(454, 161, 385, 255);
		staffPanel.setVisible(false);
		getContentPane().add(staffPanel);
		
		lecturerPanel = new LecturerPanel();
		lecturerPanel.setBounds(63, 168, 385, 255);
		lecturerPanel.setVisible(false);
		getContentPane().add(lecturerPanel);
		
		btnAction = new JButton("");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeOfActionStore == Define.TYPE_OF_ACTION_CREATE) {
					
					switch (cbxtypeOfEmployee.getSelectedIndex()) {
					case Define.TYPE_OF_STAFF: {
						Staff staff = new Staff();
						staff.setName(tbxFullname.getText());
						staff.setType(cbxtypeOfEmployee.getSelectedIndex());
						staff.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						staff.setAllowance(Integer.valueOf(staffPanel.getAllowance()));
						staff.setDepartment(staffPanel.getDepartment());
						staff.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
						staff.setHometown(staffPanel.getHometown());
						staff.setPosition(staffPanel.getPositionString());
						staff.setSalaryRatio(Float.valueOf(staffPanel.getSalaryRatio()));
						staff.setWorkDay(Integer.valueOf(staffPanel.getWorkDay()));
						staff.setYearOfWork(Integer.valueOf(staffPanel.getYearOfWork()));
						
						try {
							new StaffDao().create(staff);
							JOptionPane.showMessageDialog(frame, "Create staff successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error when creating staff!!!");
							e1.printStackTrace();
						}
						break;
					}
					
					case Define.TYPE_OF_LECTURER: {
						Lecturer lecturer = new Lecturer();
						lecturer.setName(tbxFullname.getText());
						lecturer.setType(cbxtypeOfEmployee.getSelectedIndex());
						lecturer.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						lecturer.setAllowance(Integer.valueOf(lecturerPanel.getAllowance()));
						lecturer.setDepartment(lecturerPanel.getDepartment());
						lecturer.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
						lecturer.setHometown(lecturerPanel.getHometown());
						lecturer.setQualification(lecturerPanel.getQualificationString());
						lecturer.setSalaryRatio(Float.valueOf(lecturerPanel.getSalaryRatio()));
						lecturer.setPeriodsInMonth(Integer.valueOf(lecturerPanel.getPeriodsInMonth()));
						lecturer.setYearOfWork(Integer.valueOf(lecturer.getYearOfWork()));
						
						try {
							new LecturerDao().create(lecturer);
							JOptionPane.showMessageDialog(frame, "Create lecturer successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error whern creating lecturer!!!");
							e1.printStackTrace();
						}
						break;
					}
					
					case Define.TYPE_OF_CASUAL_WORKER: {
						CasualWorker casualWorker = new CasualWorker();
						casualWorker.setName(tbxFullname.getText());
						casualWorker.setType(cbxtypeOfEmployee.getSelectedIndex());
						casualWorker.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						casualWorker.setEarningPerDay(Float.valueOf(workerPanel.getEarningPerDay()));
						casualWorker.setWorkDay(Integer.valueOf(workerPanel.getWorkDay()));
						
						try {
							new CasualWorkerDao().create(casualWorker);
							JOptionPane.showMessageDialog(frame, "Create casual worker successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error when creating causal worker!!!");
							e1.printStackTrace();
						}
						break;
					}
					}
				} else {
					long personId = Long.valueOf(tbxId.getText());					
					
					switch (cbxtypeOfEmployee.getSelectedIndex()) {
					case Define.TYPE_OF_STAFF: {
						Staff staff = new Staff();
						staff.setId(personId);
						staff.setPersonId(personId);
						staff.setName(tbxFullname.getText());
						staff.setType(cbxtypeOfEmployee.getSelectedIndex());
						staff.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						staff.setAllowance(Integer.valueOf(staffPanel.getAllowance()));
						staff.setDepartment(staffPanel.getDepartment());
						staff.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
						staff.setHometown(staffPanel.getHometown());
						staff.setPosition(staffPanel.getPositionString());
						staff.setSalaryRatio(Float.valueOf(staffPanel.getSalaryRatio()));
						staff.setWorkDay(Integer.valueOf(staffPanel.getWorkDay()));
						staff.setYearOfWork(Integer.valueOf(staffPanel.getYearOfWork()));
						
						try {
							new StaffDao().update(personId, staff);
							JOptionPane.showMessageDialog(frame, "Update staff successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error when updating staff!!!");
							e1.printStackTrace();
						}
						break;
					}
					
					case Define.TYPE_OF_LECTURER: {
						Lecturer lecturer = new Lecturer();
						lecturer.setId(personId);
						lecturer.setPersonId(personId);
						lecturer.setName(tbxFullname.getText());
						lecturer.setType(cbxtypeOfEmployee.getSelectedIndex());
						lecturer.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						lecturer.setAllowance(Integer.valueOf(lecturerPanel.getAllowance()));
						lecturer.setDepartment(lecturerPanel.getDepartment());
						lecturer.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
						lecturer.setHometown(lecturerPanel.getHometown());
						lecturer.setQualification(lecturerPanel.getQualificationString());
						lecturer.setSalaryRatio(Float.valueOf(lecturerPanel.getSalaryRatio()));
						lecturer.setPeriodsInMonth(Integer.valueOf(lecturerPanel.getPeriodsInMonth()));
						lecturer.setYearOfWork(Integer.valueOf(lecturer.getYearOfWork()));
						
						try {
							new LecturerDao().update(personId, lecturer);
							JOptionPane.showMessageDialog(frame, "Update lecturer successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error when updating lecturer!!!");
							e1.printStackTrace();
						}
						break;
					}
					
					case Define.TYPE_OF_CASUAL_WORKER: {
						CasualWorker casualWorker = new CasualWorker();
						casualWorker.setId(personId);
						casualWorker.setPersonId(personId);
						casualWorker.setName(tbxFullname.getText());
						casualWorker.setType(cbxtypeOfEmployee.getSelectedIndex());
						casualWorker.setYearOfBirth(Integer.valueOf(tbxYearOfBirth.getText()));
						
						casualWorker.setEarningPerDay(Float.valueOf(workerPanel.getEarningPerDay()));
						casualWorker.setWorkDay(Integer.valueOf(workerPanel.getWorkDay()));
						
						try {
							new CasualWorkerDao().update(personId, casualWorker);
							JOptionPane.showMessageDialog(frame, "Update casual worker successfully!!!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(frame, "Error when updating casual worker!!!");
							e1.printStackTrace();
						}
						break;
					}
					}	
				}
			}
		});
		btnAction.setBounds(345, 436, 103, 25);
		getContentPane().add(btnAction);
		
		setup(person, typeOfAction);
	}
	
	private void setup(Person person, int typeOfAction) {
		
		switch(typeOfAction) {
		case Define.TYPE_OF_ACTION_CREATE: {
			btnAction.setText("Create");
			cbxtypeOfEmployee.setEnabled(true);
			cbxtypeOfEmployee.setSelectedIndex(Define.TYPE_OF_STAFF);			
			break;
		}
		
		case Define.TYPE_OF_ACTION_EDIT: {
			btnAction.setText("Update");
			cbxtypeOfEmployee.setEnabled(false);
			tbxId.setText(String.valueOf(person.getId()));
			
			cbxtypeOfEmployee.setSelectedIndex(person.getType());
			tbxFullname.setText(person.getName());
			tbxYearOfBirth.setText(String.valueOf(person.getYearOfBirth()));
			
			setEmployeePanelVisibleByType(person.getType());
			
			switch(person.getType()) {
			case Define.TYPE_OF_LECTURER: {
				
				Lecturer lecturer = null;
				try {
					lecturer = new LecturerDao().read(person.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lecturerPanel.setDepartment(lecturer.getDepartment());
				lecturerPanel.setHometown(lecturer.getHometown());
				lecturerPanel.setPeriodsInMonth(String.valueOf(lecturer.getPeriodsInMonth()));
				lecturerPanel.setQualification(lecturer.getQualification());
				lecturerPanel.setSalaryRatio(String.valueOf(lecturer.getSalaryRatio()));
				lecturerPanel.setYearOfWork(String.valueOf(lecturer.getYearOfWork()));
				break;
			}
			
			case Define.TYPE_OF_STAFF: {
				Staff staff = null;
				try {
					staff = new StaffDao().read(person.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				staffPanel.setDepartment(staff.getDepartment());
				staffPanel.setHometown(staff.getHometown());
				staffPanel.setPosition(staff.getPosition());
				staffPanel.setSalaryRatio(String.valueOf(staff.getSalaryRatio()));
				staffPanel.setWorkDay(String.valueOf(staff.getWorkDay()));
				staffPanel.setYearOfWork(String.valueOf(staff.getYearOfWork()));
				break;
			}
			
			case Define.TYPE_OF_CASUAL_WORKER: {
				CasualWorker casualWorker = null;
				try {
					casualWorker = new CasualWorkerDao().read(person.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				workerPanel.setWorkDay(String.valueOf(casualWorker.getWorkDay()));
				workerPanel.setEarningPerDay(String.valueOf(casualWorker.getEarningPerDay()));
				break;
			}
			}
			
			break;
		}
		}
		
	}
	
	public int getId() {
		return Integer.parseInt(tbxId.getText());
	}
	
	public void setId(int id) {
		this.tbxId.setText(String.valueOf(id));
	}
	
	public String getFullname() {
		return tbxFullname.getText();
	}
	
	public void setFullname(String fullname) {
		this.tbxFullname.setText(fullname);
	}
	
	public String getYearOfBirth() {
		return this.tbxYearOfBirth.getText();
	}
	
	public void setYearOfBirth(String yearOfBirth) {
		this.tbxYearOfBirth.setText(yearOfBirth);
	}
	
	public int getTypeOfEmployee() {
		return cbxtypeOfEmployee.getSelectedIndex();
	}
	
	public void setTypeOfEmployee(int typeOfEmployee) {
		this.cbxtypeOfEmployee.setSelectedIndex(typeOfEmployee);
	}
	
	public void setEmployeePanelVisibleByType(int type) {
		switch(type) {
		case Define.TYPE_OF_LECTURER:
			workerPanel.setVisible(false);
			staffPanel.setVisible(false);
			lecturerPanel.setVisible(true);
			lecturerPanel.setLocation(63, 172);
			break;
			
		case Define.TYPE_OF_STAFF:
			workerPanel.setVisible(false);
			staffPanel.setVisible(true);
			lecturerPanel.setVisible(false);
			staffPanel.setLocation(63, 172);
			break;
		
		case Define.TYPE_OF_CASUAL_WORKER:
			workerPanel.setVisible(true);
			staffPanel.setVisible(false);
			lecturerPanel.setVisible(false);
			workerPanel.setLocation(63, 172);
			break;
		}
	}
}