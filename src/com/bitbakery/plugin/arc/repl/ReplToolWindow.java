package com.bitbakery.plugin.arc.repl;

//import com.bitbakery.plugin.arc.LispIcons;

import com.bitbakery.plugin.arc.ArcIcons;
import static com.bitbakery.plugin.arc.ArcResourceBundle.message;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.util.Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplToolWindow implements ProjectComponent {

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

        tabbedPane.addTab(message("repl.title"), new Repl());

        myToolWindow = toolWindowManager.registerToolWindow(message("repl.toolWindowId"), myContentPanel, ToolWindowAnchor.BOTTOM);
        myToolWindow.setAnchor(ToolWindowAnchor.BOTTOM, null);
        myToolWindow.setIcon(ArcIcons.ARC_REPL_ICON);
    }

    private JPanel createButtonPanel() {
        // TODO - Modify the buttons to match look and feel of other open/close tab buttons - see "Add" and "Cancel" icons in the IntelliJ resource bundle...
        // TODO - See how we can use the built-in tool window toolbars...
        JButton addButton = new JButton(Icons.ADD_ICON);
        addButton.setToolTipText(message("repl.open"));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                tabbedPane.addTab(message("repl.title"), new Repl());
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });

        // TODO - We should also close the tab and kill the process if the user enters the right Lisp command (usually, (quit), or some such)
        JButton removeButton = new JButton(Icons.DELETE_ICON);
        removeButton.setToolTipText(message("repl.close"));
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
        JMenuItem item = new JMenuItem(message("repl.rename"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int tabIndex = tabbedPane.getSelectedIndex();
                if (tabIndex > -1) {
                    String oldTitle = tabbedPane.getTitleAt(tabIndex);
                    String newTitle = (String) JOptionPane.showInputDialog(
                            (Component) actionEvent.getSource(), message("repl.newName"), message("repl.rename"),
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
        ToolWindowManager.getInstance(myProject).unregisterToolWindow(message("repl.toolWindowId"));
    }
}
