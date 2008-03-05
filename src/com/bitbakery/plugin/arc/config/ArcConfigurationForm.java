package com.bitbakery.plugin.arc.config;

import com.intellij.openapi.util.text.StringUtil;
import com.bitbakery.plugin.arc.repl.ReplApplicationComponent;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * The configuration form for the Arc plugin. This is accessible from the IntelliJ settings dialog.
 */
public class ArcConfigurationForm {
    private JPanel rootComponent;

    private JTextField mzSchemeHome;
    private JTextField arcHome;
    private JTextField replExecutable;

    private JButton mzSchemeHomeChooserButton;
    private JButton arcHomeChooserButton;


    // TODO - How/where can we set the values for the text fields? setData isn't being caled when I expect...
    public ArcConfigurationForm() {
//        enableDependentFields();

        mzSchemeHomeChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                pickFile(chooser, mzSchemeHome);
//                enableDependentFields();
            }
        });
        arcHomeChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                pickFile(chooser, arcHome);
//                enableDependentFields();
            }
        });
    }

/*
    private void enableDependentFields() {
        boolean isEnabled = !StringUtil.isEmptyOrSpaces(homeDirectoryTextField.getText());

        mzSchemeHome.setEnabled(isEnabled);
        mzSchemeHomeChooserButton.setEnabled(isEnabled);

        arcHome.setEnabled(isEnabled);
        arcHomeChooserButton.setEnabled(isEnabled);
    }

*/
    private void pickFile(JFileChooser chooser, JTextField textField) {
/*
        if (StringUtil.isNotEmpty(textField.getText())) {
            chooser.setCurrentDirectory(new File(textField.getText()));
        }
*/
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(rootComponent)) {
            textField.setText(chooser.getSelectedFile().getPath());
        }
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public void setData(ReplApplicationComponent data) {
        arcHome.setText(data.getMzSchemeHome());
        mzSchemeHome.setText(data.getMzSchemeHome());
        replExecutable.setText(data.getReplExecutable());
    }

    public void getData(ReplApplicationComponent data) {
        data.setMzSchemeHome(mzSchemeHome.getText());
        data.setArcHome(arcHome.getText());
        data.setReplExecutable(replExecutable.getText());
    }

    public boolean isModified(ReplApplicationComponent data) {
        return isModified(arcHome, data.getArcHome())
                || isModified(mzSchemeHome, data.getMzSchemeHome())
                || isModified(replExecutable, data.getReplExecutable());
    }

    private boolean isModified(JTextField textField, String data) {
        return textField.getText() != null ? !textField.getText().equals(data) : data != null;
    }
}
