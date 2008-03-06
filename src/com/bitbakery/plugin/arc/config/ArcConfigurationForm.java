package com.bitbakery.plugin.arc.config;

import com.bitbakery.plugin.arc.repl.ReplApplicationComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The configuration form for the Arc plugin. This is accessible from the IntelliJ settings dialog.
 */
public class ArcConfigurationForm {
    private JPanel rootComponent;

    private JTextField mzSchemeHome;
    private JTextField arcHome;
    private JTextField arcInitializationFile;

    private JButton mzSchemeHomeChooserButton;
    private JButton arcHomeChooserButton;


    public ArcConfigurationForm() {
        mzSchemeHomeChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                createFileChooser(mzSchemeHome);
            }
        });
        arcHomeChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                createFileChooser(arcHome);
            }
        });
    }

    private void createFileChooser(JTextField textField) {
        File currentDirectory = null;
        currentDirectory = new File(textField.getText());

        JFileChooser chooser = new JFileChooser(currentDirectory);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pickFile(chooser, textField);
    }

    private void pickFile(JFileChooser chooser, JTextField textField) {
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(rootComponent)) {
            textField.setText(chooser.getSelectedFile().getPath());
        }
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public void setData(ReplApplicationComponent data) {
        arcHome.setText(data.getArcHome());
        mzSchemeHome.setText(data.getMzSchemeHome());
        arcInitializationFile.setText(data.getArcInitializationFile());
    }

    public void getData(ReplApplicationComponent data) {
        data.setArcHome(arcHome.getText());
        data.setMzSchemeHome(mzSchemeHome.getText());
        data.setArcInitializationFile(arcInitializationFile.getText());
    }

    public boolean isModified(ReplApplicationComponent data) {
        return isModified(arcHome, data.getArcHome())
                || isModified(mzSchemeHome, data.getMzSchemeHome())
                || isModified(arcInitializationFile, data.getArcInitializationFile());
    }

    private boolean isModified(JTextField textField, String data) {
        return textField.getText() != null ? !textField.getText().equals(data) : data != null;
    }
}
