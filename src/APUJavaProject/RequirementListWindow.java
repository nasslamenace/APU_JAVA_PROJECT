package APUJavaProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RequirementListWindow extends JDialog {

	private MyPanel container = new MyPanel();
	private MyButton okBtn = new MyButton("ok");

	public RequirementListWindow(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		Manager.requirements = FileManager.returnRequirements();
		this.setSize(470, 490);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initComponent();
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void initComponent() {

		container.setLayout(new BorderLayout());

		MyPanel requirementsScroll = new MyPanel();

		requirementsScroll.setLayout(new BoxLayout(requirementsScroll, BoxLayout.Y_AXIS));

		for (int i = 0; i < Manager.requirements.size(); i++) {
			requirementsScroll.add(new MyLabel("requirement number " + i));

			JPanel requirementPan = new JPanel();

			requirementPan.setOpaque(true);
			requirementPan.setBackground(new Color(145, 219, 187));

			requirementPan.add(new JLabel("<html> " + Manager.requirements.get(i).replaceAll("\n", "<br/>") + "<html>"));

			requirementsScroll.add(requirementPan);

		}

		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}

		});

		container.add(new JScrollPane(requirementsScroll), BorderLayout.CENTER);
		container.add(okBtn, BorderLayout.SOUTH);

	}

}
