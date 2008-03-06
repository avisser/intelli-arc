package com.bitbakery.plugin.arc.config;

import com.bitbakery.plugin.arc.repl.ReplApplicationComponent;
import com.intellij.ide.BrowserUtil;

import javax.swing.*;
import java.awt.*;
import static java.awt.Cursor.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private JLabel arcUrl;
    private JLabel mzSchemeUrl;


    public ArcConfigurationForm() {
        addFileChooser(mzSchemeHome, mzSchemeHomeChooserButton);
        addFileChooser(arcHome, arcHomeChooserButton);

        enableHyperlink(mzSchemeUrl);
        enableHyperlink(arcUrl);
    }

    private void enableHyperlink(final JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            private Color textColor = label.getForeground();

            public void mouseEntered(MouseEvent mouseEvent) {
                label.setCursor(getPredefinedCursor(HAND_CURSOR));
                label.setForeground(Color.BLUE);
            }

            public void mouseExited(MouseEvent mouseEvent) {
                label.setForeground(textColor);
                label.setCursor(getDefaultCursor());
            }

            public void mouseClicked(MouseEvent mouseEvent) {
                BrowserUtil.launchBrowser(label.getText());
            }
        });
    }

    private void addFileChooser(final JTextField textField, JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                createFileChooser(textField);
            }
        });
    }

    private void createFileChooser(JTextField textField) {
        File currentDirectory = new File(textField.getText());
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
