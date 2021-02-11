import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui_Photo_Album extends JFrame implements 
ActionListener
{
	JTextField Tf1,Tf2,Tf3;
	JLabel l1,l2,l3,l4;
	JTextArea Area;
	JButton prev,home,next,Jb4;
	JScrollPane scroll;
	String [] names;
	int id=0;
	
	
	public void Gui01()
	{
		super.setBounds(100, 100, 800, 600);
		super.setTitle("Family Alubum");
		super.setResizable(false);
		
		//to add text area
		l4=new JLabel();
		scroll=new JScrollPane(l4);
		scroll.setBounds(50,25, 400, 350);
		super.add(scroll);
		
		
		//to add text filed and label
		
		Tf1=new JTextField();
		Tf1.setBounds(575, 75, 200,30);
		super.add(Tf1);
		
		l1=new JLabel("Path");
		l1.setBounds(530, 75, 50, 30);
		super.add(l1);
		
		Tf2=new JTextField();
		Tf2.setBounds(575, 110,200, 30);
		Tf2.setEditable(false);
		super.add(Tf2);
		
		l2=new JLabel("File Name");
		l2.setBounds(500, 110, 75, 30);
		super.add(l2);
		
		
		Tf3=new JTextField();
		Tf3.setBounds(575, 150,200, 30);
		Tf3.setEditable(false);
		super.add(Tf3);
		
		l1=new JLabel("File size");
		l1.setBounds(500, 150, 75, 30);
		super.add(l1);
		
		//to add buttons in frame
		
	prev=new JButton();
		prev.setBounds(70, 400, 75, 40);
		super.add(prev);
		
		home=new JButton();
		home.setBounds(165, 400, 75, 40);
		super.add(home);
		
		next=new JButton();
		next.setBounds(260, 400, 75, 40);
		super.add(next);
		
		Jb4=new JButton("Get It");
		Jb4.setBounds(620, 250, 75, 40);
		super.add(Jb4);
		
		//to add image icon to buttons
		ImageIcon Ic1=new ImageIcon("E:\\prev.png");
		ImageIcon Ic3=new ImageIcon("E:\\next.png");
		ImageIcon Ic2=new ImageIcon("E:\\home.jfif");
		
		prev.setIcon(Ic1);
		home.setIcon(Ic2);
		next.setIcon(Ic3);
		
		
		//to register the buttons to application 
		prev.addActionListener(this);
		home.addActionListener(this);
		next.addActionListener(this);
		Jb4.addActionListener(this);
		
		
		//to set a font
		Font f=new Font("",Font.PLAIN,15);
		Tf1.setForeground(Color.blue);
		Tf1.setFont(f);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
	public void actionPerformed(ActionEvent ae)
	{

		if(ae.getSource()==Jb4)
		{
			File f=new File(Tf1.getText());
			if(f.isDirectory())
			{
				names=f.list();
				int i=0;
				for (int j = 0; j < names.length; j++) 
				{
					if((names[j].endsWith(".jpg"))||(names[j].endsWith(".jpeg"))||(names[j].endsWith(".png"))||(names[j].endsWith(".gif")))
					{
						names[i]=names[j];
						i++;
					}
				}
				names=Arrays.copyOf(names,i);
				if(names.length==0)
					JOptionPane.showMessageDialog(this,"no images");
				else
					JOptionPane.showMessageDialog(this,"images loaded");
			}
			else if((f.isFile())&&((f.getName().endsWith(".jpg"))||(f.getName().endsWith(".jpeg"))||(f.getName().endsWith(".gif"))||(f.getName().endsWith(".png"))))
			{
				names=new String[1];
				names[0]=f.getName();
				JOptionPane.showMessageDialog(this,"images loaded");
			}
		}
		if(ae.getSource()==home)
		{id=0;
			try
			{
			if(names.length==1)
			{
				l4.setIcon(new ImageIcon(Tf1.getText()));
				Tf2.setText(names[0]);
				Tf3.setText(Long.toString(new File(Tf1.getText()).length())+" Bytes");
			}
			else if(names.length!=0)
			{
				if(Tf1.getText().endsWith("\\"))
				{
				l4.setIcon(new ImageIcon(Tf1.getText()+names[0]));
				Tf3.setText(Long.toString(new File(Tf1.getText()+names[0]).length())+" Bytes");
				}
				else
				{
				l4.setIcon(new ImageIcon(Tf1.getText()+"\\"+names[0]));
				Tf3.setText(Long.toString(new File(Tf1.getText()+"\\"+names[0]).length())+" Bytes");
				}
				Tf2.setText(names[0]);
			}
			else
				JOptionPane.showMessageDialog(this,"no images to show");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"no images to show");
			}
		}
		if(ae.getSource()==prev)
		{
			try
			{
			if(names.length==1)
			{
				JOptionPane.showMessageDialog(this,"your already at first pic");
			}
			else if(names.length!=0)
			{
				if(id!=0)
				{id=id-1;
				Tf2.setText(names[id]);
				if(Tf1.getText().endsWith("\\"))
				{
					l4.setIcon(new ImageIcon(Tf1.getText()+names[id]));
					Tf3.setText(Long.toString(new File(Tf1.getText()+names[id]).length())+" Bytes");
				}
				else
				{
					l4.setIcon(new ImageIcon(Tf1.getText()+"\\"+names[id]));
					Tf3.setText(Long.toString(new File(Tf1.getText()+"\\"+names[id]).length())+" Bytes");
				}
				}
				else
					JOptionPane.showMessageDialog(this,"your already at first pic");
			}
			else
				JOptionPane.showMessageDialog(this,"no images to show");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"no images to show");
			}
		}
		if(ae.getSource()==next)
		{
			try
			{
			if(names.length==1)
			{
				JOptionPane.showMessageDialog(this,"your already at last pic");
			}
			else if(names.length!=0)
			{
				if(id!=names.length-1)
				{id=id+1;
				Tf2.setText(names[id]);
				if(Tf1.getText().endsWith("\\"))
				{
					l4.setIcon(new ImageIcon(Tf1.getText()+names[id]));
					Tf3.setText(Long.toString(new File(Tf1.getText()+names[id]).length())+" Bytes");
				}
				else
				{
					l4.setIcon(new ImageIcon(Tf1.getText()+"\\"+names[id]));
					Tf3.setText(Long.toString(new File(Tf1.getText()+"\\"+names[id]).length())+" Bytes");
				}
				}
				else
					JOptionPane.showMessageDialog(this,"your already at last pic");
			}
			else
				JOptionPane.showMessageDialog(this,"no images to show");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"no images to show");
			}
		}
		
	}
	public static void main(String[] args) 
	{
	  
		Gui_Photo_Album Ui=new Gui_Photo_Album();
		
		Ui.Gui01();
			
	}

	
}
