package com.bitbakery.plugin.arc.repl;

import com.bitbakery.plugin.arc.ArcIcons;
import static com.bitbakery.plugin.arc.ArcResourceBundle.message;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.impl.EditorComponentImpl;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.JScrollPane2;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ReplToolWindow implements ProjectComponent {

    private Project myProject;
    private ConsoleView view;
    private ProcessHandler processHandler;

    public ReplToolWindow(Project project) {
        myProject = project;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                disposeComponent();
            }
        });
    }

    public void projectOpened() {
        try {
            initToolWindow();
        } catch (Exception e) {
            // TODO - Handle me for real...
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void projectClosed() {
    }

    public void initComponent() {
    }

    public void disposeComponent() {
        ToolWindowManager.getInstance(myProject).unregisterToolWindow(message("repl.toolWindowId"));

        // TODO - Is there any way I can get this guy called even on abnormal process termination...? Or at least keep mzscheme from shooting of into outer space??
        processHandler.destroyProcess();
    }

    public void writeToRepl(String s) {
        view.print(s, ConsoleViewContentType.USER_INPUT);
        view.print("\r\n", ConsoleViewContentType.USER_INPUT); // TODO - ???
    }

    @NotNull
    public String getComponentName() {
        return "ReplToolWindow.ArcPlugin";
    }

    private void initToolWindow() throws ExecutionException, IOException {
        if (myProject != null) {
            TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(myProject);
            view = builder.getConsole();

            // TODO - What does the "help ID" give us??
            // view.setHelpId("Kurt's Help ID");

            processHandler = new ArcProcessHandler(myProject);
            ProcessTerminatedListener.attach(processHandler);
            processHandler.startNotify();
            view.attachToProcess(processHandler);

            ToolWindowManager manager = ToolWindowManager.getInstance(myProject);
            ToolWindow window = manager.registerToolWindow(message("repl.title"), view.getComponent(), ToolWindowAnchor.BOTTOM);
            window.setIcon(ArcIcons.ARC_REPL_ICON);

            final EditorImpl e = getEditor();
            e.getContentComponent().addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent event) {
                    e.getCaretModel().moveToOffset(view.getContentSize());
                }
            });
            e.getSettings().setLineNumbersShown(true);

            // This is how we can add add'l tooling to our REPL window!
            // e.setHeaderComponent(new JLabel("Only a test"));

            // TODO - Ctrl-v (paste) should trim string before dropping
            // TODO - Drag-n-drop text isn't working from editor to console... why not?
            // Unfortunately, the following two lines do nothing to make either of the above things happen...
            //e.getSettings().setDndEnabled(true);
            //e.getContentComponent().getDropTarget().setActive(true);
        }
    }

    /**
     * A bit of a hack; needed until JetBrains opens up the ConsoleView class.
     */
    private EditorImpl getEditor() {
        final JPanel editorPanel = (JPanel) view.getComponent().getComponent(0);
        JScrollPane2 scrollPane = (JScrollPane2) editorPanel.getComponents()[1];
        JViewport port = (JViewport) scrollPane.getComponents()[0];
        EditorComponentImpl ed = (EditorComponentImpl) port.getComponents()[0];
        return ed.getEditor();
    }
}
