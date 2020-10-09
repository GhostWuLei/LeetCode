package leetcode.editor.cn.swing;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingDemo01
{
	private static void createGUI()
	{
		JFrame frame = new JFrame("Swing Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
// 		JFrame创建时自带了一个根容器，称为ContentPane，但一般我们会自己创建一个JPanel并设为根容器
//		例如:
//		JPanel panel = new JPanel();  // 可以自己创建一个JPanel
//		frame.setContentPane(panel);  // 把它设置为根容器
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(new JLabel("Hello,World"));
		contentPane.add(new JButton("测试"));
		
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

	}
}
