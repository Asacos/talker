import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class SimpleGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel input=new JLabel("Input:");
	private JTextField inputTxt=new JTextField("", 5);
	private JButton create=new JButton("Create");
	String str0="CreateObject(\"SAPI.SpVoice\").Speak\"";
	String str1="\"";
	
	public SimpleGUI() {
		super("Talker");
		this.setBounds(0, 0, 400, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container=this.getContentPane();
		container.setLayout(new GridLayout(3, 3, 2, 2));
		container.add(input);
		container.add(inputTxt);
		container.add(create);
		create.addActionListener(new ListenerAction());
	}
	
	public class ListenerAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//SimpleGUI sg=new SimpleGUI();
			String str=inputTxt.getText();
			try(FileWriter writer = new FileWriter("sound.vbs", false))
    		{
				writer.write(str0+str+str1);
        		writer.flush();
    		}
    		catch(IOException ex){
        	System.out.println(ex.getMessage());
    		} 				
			
			try {
				Runtime.getRuntime().exec("wscript ./sound.vbs");
				
			} catch (IOException e) {
				System.out.println (e.getMessage());
			}
		}   
	}
}
