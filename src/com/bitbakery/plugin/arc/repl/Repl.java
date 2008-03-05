package com.bitbakery.plugin.arc.repl;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.options.ShowSettingsUtil;
import org.apache.commons.collections.ArrayStack;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.File;


public class Repl extends JScrollPane {
    private static final Font FONT = new Font("Courier New", Font.PLAIN, 12);  // TODO - This should be customizable!

    private byte[] readBuffer = new byte[1024];
    private int inputStartPosition = 0;
    private CustomTextArea textArea;
    private Process arcProcess;
    private PrintWriter outputWriter;

    public Repl() {
        textArea = new CustomTextArea();
        setViewportView(textArea);

        try {
            arcProcess = spawnArcProcess();
            outputWriter = new PrintWriter(arcProcess.getOutputStream(), true);

            spawnListenerThread(arcProcess.getInputStream());
            spawnListenerThread(arcProcess.getErrorStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void spawnListenerThread(final InputStream inputStream) {
        new Thread() {
            public void run() {
                try {
                    for (int i = 0; i > -1; i = inputStream.read(readBuffer)) {
                        textArea.append(new String(readBuffer, 0, i));
                        inputStartPosition = textArea.getText().length();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void setReplBackground(Color color) {
        textArea.setBackground(color);
    }

    public void setReplForeground(Color color) {
        textArea.setForeground(color);
    }

    private Process spawnArcProcess() throws IOException {
        Application app = ApplicationManager.getApplication();
        ReplApplicationComponent component = app.getComponent(ReplApplicationComponent.class);

        // TODO - Does any of this need to be in the config form?
        String arcHome = component.getArcHome();
        if (arcHome == null) {
            arcHome = "/Users/kurtc/dev/arc2";
        }

        String schemeHome = component.getMzSchemeHome();
        if (schemeHome == null) {
            schemeHome = "/Applications/MzScheme v360";
        }

        // TODO - Get the right stuff into the config form...
        String replExecutable = component.getReplExecutable();
        if (replExecutable == null) {
            replExecutable = "mzscheme -m -f as.scm";
        }

        String scheme = schemeHome + "/bin/mzscheme";
        String arg0 = "-m";
        String arg1 = "-f";
        String initializationFile = "as.scm";

        if (arcHome == null || schemeHome == null) {
            // TODO - How do we get the *current* project??
            Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
            ShowSettingsUtil.getInstance().editConfigurable(openProjects[0], component);
        }

        return Runtime.getRuntime().exec(new String[] {scheme, arg0, arg1, initializationFile}, null, new File(arcHome));
    }

    public void execute(String replInput) {
        textArea.execute(replInput);
    }

    public void close() {
        // Clean up resources...
        execute("(quit)");
        arcProcess.destroy();
    }

    private class CustomTextArea extends JTextArea {
        private ArrayStack recentInput = new ArrayStack();
        private int current = 0;

        public CustomTextArea() {
            setFont(FONT);
            addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (getCaretPosition() < inputStartPosition) {
                        // TODO - This is preventing us from copying text (ctrl-c) from the REPL
                        int end = getText().length();
                        setCaretPosition(end);
                        e.consume();
                    }

                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        e.consume();
                    }
                }

                public void keyReleased(KeyEvent e) {
                    try {
                        // TODO - Fix bug - it seems we capture the key up event when we hit enter in the Rename REPL dialog
                        int end = getText().length();
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            String replInput = getText(inputStartPosition, (end - inputStartPosition) - 1);
                            outputWriter.println(replInput);
                            outputWriter.flush();
                            recentInput.push(replInput);
                            current = 0;
                            e.consume();
                        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (!recentInput.empty()) {
                                replaceRange((String) recentInput.peek(current), inputStartPosition, end);
                                if (++current >= recentInput.size()) {
                                    current = 0;
                                }
                            }
                            e.consume();
                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (!recentInput.empty()) {
                                replaceRange((String) recentInput.peek(current), inputStartPosition, end);
                                if (--current < 0) {
                                    current = recentInput.size() - 1;
                                }
                            }
                            e.consume();
                        }
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }


        public void execute(String s) {
            super.append(s.trim());
            super.append("\r\n");
            int end = getText().length();
            String replInput = null;
            try {
                replInput = getText(inputStartPosition, (end - inputStartPosition) - 1);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            outputWriter.println(replInput);
            //outputWriter.flush();
            recentInput.push(replInput);
            current = 0;
        }

        public void append(String line) {
            super.append(line);
            scrollToEnd();
        }

        private void scrollToEnd() {
            // TODO - Is there a nicer way to auto-scroll to the bottom when we append text?
            setSelectionStart(getText().length());
            setSelectionEnd(getText().length());
            if (getCaret() != null && getCaret().getMagicCaretPosition() != null) {
                scrollRectToVisible(new Rectangle(getCaret().getMagicCaretPosition()));
            }
        }
    }
}
