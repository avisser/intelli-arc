package com.bitbakery.plugin.arc.repl;

//import com.bitbakery.plugin.arc.LispIcons;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.Icons;
import com.bitbakery.plugin.arc.ArcIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplToolWindow implements ProjectComponent {
    public static final String RENAME_REPL = "Rename REPL";

    private static final String CLOSE_TAB = "Close the current REPL tab";
    private static final String OPEN_TAB = "Open a new REPL tab";
    public static final String TOOL_WINDOW_ID = "Arc REPL";
    public static final String ADD = "Add";
    public static final String REMOVE = "Remove";
    public static final String NEW_NAME = "New name:";
    public static final String REPL = "REPL";
    public static final String OK = "OK";
    public static final String CANCEL = "Cancel";

    private Project myProject;
    private ToolWindow myToolWindow;
    private JTabbedPane tabbedPane;

    public ReplToolWindow(Project project) {
        myProject = project;
    }

    public void projectOpened() {
        initToolWindow();
    }

    public void projectClosed() {
        unregisterToolWindow();
    }

    public void initComponent() {
        // empty
    }

    public void disposeComponent() {
        // Close out any open REPLs
        for (Component repl : tabbedPane.getComponents()) {
            ((Repl) repl).close();            
        }
    }

    public Repl getCurrentRepl() {
        return (Repl) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
    }


    @NotNull
    public String getComponentName() {
        return "ReplToolWindow.ArcPlugin";
    }

    private void initToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(myProject);

        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setComponentPopupMenu(createPopupMenu());

        JPanel myContentPanel = new JPanel(new BorderLayout());
        myContentPanel.add(tabbedPane, BorderLayout.CENTER);
        myContentPanel.add(createButtonPanel(), BorderLayout.WEST);

        tabbedPane.addTab(REPL, new Repl());

        myToolWindow = toolWindowManager.registerToolWindow(TOOL_WINDOW_ID, myContentPanel, ToolWindowAnchor.BOTTOM);
        myToolWindow.setAnchor(ToolWindowAnchor.BOTTOM, null);
        myToolWindow.setIcon(ArcIcons.ARC_REPL_ICON);
    }

    private JPanel createButtonPanel() {
        // TODO - Modify the buttons to match look and feel of other open/close tab buttons - see "Add" and "Cancel" icons in the IntelliJ resource bundle...
        // TODO - See how we can use the built-in tool window toolbars...
        JButton addButton = new JButton(Icons.ADD_ICON);
        addButton.setToolTipText(OPEN_TAB);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                tabbedPane.addTab(REPL, new Repl());
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });

        // TODO - We should also close the tab and kill the process if the user enters the right Lisp command (usually, (quit), or some such)
        JButton removeButton = new JButton(Icons.DELETE_ICON);
        removeButton.setToolTipText(CLOSE_TAB);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (tabbedPane.getSelectedIndex() > -1) {
                    tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                    getCurrentRepl().close();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        return buttonPanel;
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem item = new JMenuItem(RENAME_REPL);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int tabIndex = tabbedPane.getSelectedIndex();
                if (tabIndex > -1) {
                    String oldTitle = tabbedPane.getTitleAt(tabIndex);
                    String newTitle = (String) JOptionPane.showInputDialog(
                            (Component) actionEvent.getSource(), NEW_NAME, RENAME_REPL,
                            JOptionPane.PLAIN_MESSAGE, null, null, oldTitle);
                    if (newTitle != null) {
                        tabbedPane.setTitleAt(tabIndex, newTitle);
                    }
                }
            }
        });
        menu.add(item);
        return menu;
    }

    private void unregisterToolWindow() {
        ToolWindowManager.getInstance(myProject).unregisterToolWindow(TOOL_WINDOW_ID);
    }
}
