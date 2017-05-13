package julien2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Observer{
	private JLabel lbTemp,lblY;
	private Temperature temperature;
	private PIC pic;
	
	GUI(Temperature temp, PIC pic) {
		this.temperature = temp;
		this.pic = pic;
		
		temp.addObserver(this);
		
		JPanel contentPane;
		JTextField textField;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationPortSrie = new JLabel("Application port s\u00E9rie");
		lblApplicationPortSrie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApplicationPortSrie.setBounds(10, 11, 157, 32);
		contentPane.add(lblApplicationPortSrie);
		
		JLabel lblTemprature = new JLabel("Temp\u00E9rature:");
		lblTemprature.setBounds(0, 54, 89, 14);
		contentPane.add(lblTemprature);
		
		JLabel lblModificationDeLa = new JLabel("Modification de la Temp\u00E9rature seuil:");
		lblModificationDeLa.setBounds(10, 104, 200, 14);
		contentPane.add(lblModificationDeLa);
		
		textField = new JTextField();
		textField.setBounds(10, 129, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		this.lbTemp = new JLabel("X");
		lbTemp.setBounds(96, 54, 46, 14);
		contentPane.add(lbTemp);
		
		JLabel lblNewLabel = new JLabel("Temp seuil");
		lblNewLabel.setBounds(10, 79, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(96, 79, 46, 14);
		contentPane.add(lblY);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() < 4){
					if (textField.getText().matches("[0-9]+")){
						
						int i = Integer.parseInt(textField.getText());
						if(i <= 100 && i >= 0){
							pic.send((byte) i);
							lblY.setText(Integer.toString(i) + " °C");
						}
						
					}
				}
				
			}
		});
		btnSend.setBounds(195, 129, 89, 23);
		contentPane.add(btnSend);
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		       pic.setRunning(false);
		       dispose();
		    }
		});
	}
	
	
	@Override
	public void update(Observable obs, Object obj) {
		this.lbTemp.setText(this.temperature.getTemp() + " °C");
	
		if (temperature.isOverHeating()){
			this.lbTemp.setForeground(Color.RED);
		} else {
			this.lbTemp.setForeground(Color.BLACK);
		}
	}
}
