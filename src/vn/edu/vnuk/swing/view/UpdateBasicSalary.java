package vn.edu.vnuk.swing.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.vnuk.swing.define.Define;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UpdateBasicSalary extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JComboBox<String> cbxAllowance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBasicSalary frame = new UpdateBasicSalary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateBasicSalary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current Basic Salary :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 109, 129, 39);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(240, 114, 129, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Edit");
		btnNewButton.setBounds(101, 176, 89, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Apply");
		btnNewButton_1.setBounds(251, 176, 89, 29);
		contentPane.add(btnNewButton_1);
		cbxAllowance = new JComboBox<String>();
		cbxAllowance.setBounds(237, 75, 132, 26);
		getContentPane().add(cbxAllowance);
		cbxAllowance.addItem(Define.QUALIFICATION_OF_BACHELOR);
		cbxAllowance.addItem(Define.QUALIFICATION_OF_MASTER);
		cbxAllowance.addItem(Define.QUALIFICATION_OF_DOCTOR);
		cbxAllowance.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				//setAllowanceByQualification(cbxAllowance.getSelectedIndex());
			}
		});
		
		JLabel lblTypeOfEmployee = new JLabel("Type Of Employee :");
		lblTypeOfEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeOfEmployee.setBounds(101, 69, 129, 39);
		contentPane.add(lblTypeOfEmployee);
	}
}
