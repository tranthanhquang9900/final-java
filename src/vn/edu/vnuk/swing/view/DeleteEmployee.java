package vn.edu.vnuk.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.vnuk.swing.dao.CasualWorkerDao;
import vn.edu.vnuk.swing.dao.LecturerDao;
import vn.edu.vnuk.swing.dao.StaffDao;
import vn.edu.vnuk.swing.define.Define;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class DeleteEmployee extends JFrame {

	private JPanel contentPane;

	public DeleteEmployee(int typeOfPerson, Long personId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDoYouWant = new JLabel("Do you want to delete?");
		lblDoYouWant.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouWant.setBounds(136, 40, 168, 16);
		contentPane.add(lblDoYouWant);
		
		JButton btnNewButton = new JButton("YES");
		btnNewButton.setBounds(16, 144, 117, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(typeOfPerson) {
					case Define.TYPE_OF_LECTURER:
						new LecturerDao().delete(personId);
						break;
					
					case Define.TYPE_OF_STAFF:
						new StaffDao().delete(personId);
						break;
					
					case Define.TYPE_OF_CASUAL_WORKER:
						new CasualWorkerDao().delete(personId);
						break;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNo = new JButton("NO");
		btnNo.setBounds(297, 144, 117, 29);
		contentPane.add(btnNo);
		
	}
}