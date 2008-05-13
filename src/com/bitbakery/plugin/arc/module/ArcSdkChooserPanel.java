package com.bitbakery.plugin.arc.module;

import com.intellij.ide.util.projectWizard.JdkChooserPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ProjectJdk;
import com.intellij.openapi.ui.MultiLineLabelUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArcSdkChooserPanel extends JComponent {
    private JdkChooserPanel myJdkChooser;

    public ArcSdkChooserPanel(final Project project) {
        myJdkChooser = new JdkChooserPanel(project);

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEtchedBorder());

        final JLabel label = new JLabel("Select Arc SDK"); // LispBundle.message("module.cl.select.jdk"));
        label.setUI(new MultiLineLabelUI());
        add(label, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(8, 10, 8, 10), 0, 0));

        final JLabel jdklabel = new JLabel("Arc Project Blah Blah"); // LispBundle.message("module.cl.prompt.label.project.jdk"));
        jdklabel.setFont(UIUtil.getLabelFont().deriveFont(Font.BOLD));
        add(jdklabel, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 2, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(8, 10, 0, 10), 0, 0));

        add(myJdkChooser, new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(2, 10, 10, 5), 0, 0));
        JButton configureButton = new JButton("Arc Configure Button Bluh"); // LispBundle.message("button.configure"));
        add(configureButton, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 0, 10, 5), 0, 0));


        configureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myJdkChooser.editJdkTable();
            }
        });
    }

    public ProjectJdk getChosenJdk() {
        return myJdkChooser.getChosenJdk();
    }

    public JComponent getPreferredFocusedComponent() {
        return myJdkChooser;
    }
}
