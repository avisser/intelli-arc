package com.bitbakery.plugin.arc.repl;

import com.bitbakery.plugin.arc.ArcIcons;
import static com.bitbakery.plugin.arc.ArcResourceBundle.message;
import com.bitbakery.plugin.arc.config.ArcConfiguration;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReplToolWindow implements ProjectComponent {

    private Project myProject;
    private ConsoleView view;
    private OSProcessHandler processHandler;

    public ReplToolWindow(Project project) {
        myProject = project;
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
        ToolWindowManager.getInstance(myProject).unregisterToolWindow(message("repl.toolWindowId"));
        processHandler.destroyProcess();
    }

    public void initComponent() {
        // empty
    }

    public void disposeComponent() {
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
            ToolWindowManager manager = ToolWindowManager.getInstance(myProject);
            TextConsoleBuilderFactory factory = TextConsoleBuilderFactory.getInstance();
            TextConsoleBuilder builder = factory.createBuilder(myProject);

            // TODO - Ctrl-v (paste) should trim string before dropping
            // TODO - Drag-n-drop text isn't working from editor to console... why not?
            view = builder.getConsole();
            view.setHelpId("Kurt's Help ID");

            // TODO - AAAAAAAAAAAAAAAAAAAAAUUUUUUUUUUUUUUUUUUUUGGGGGGGGGGGGGGGGGGGGHHHHHHH!!!

            Application app = ApplicationManager.getApplication();
            ArcConfiguration component = app.getComponent(ArcConfiguration.class);

            String arcHome = component.getArcHome();
            String schemeHome = component.getMzSchemeHome();
            String initializationFile = component.getArcInitializationFile();

            if (notConfigured(arcHome, schemeHome, initializationFile)) {
                if (!ShowSettingsUtil.getInstance().editConfigurable(myProject, component)) {
                    // TODO - This isn't doing what you intend...
                    JOptionPane.showMessageDialog(null, message("config.error.replNotConfiguredMessage"), message("config.error.replNotConfiguredTitle"), JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // For now, these are hard-coded. We may need more flexibility in the future (e.g., different Schemes with different args)
            String scheme = schemeHome + "/bin/mzscheme";
            String[] myCommandLine = new String[]{scheme, "-m", "-f", initializationFile};

            Process p = Runtime.getRuntime().exec(myCommandLine, null, new File(arcHome));

            StringBuffer b = new StringBuffer();
            for (String s : myCommandLine) {
                b.append(s);
                b.append(" ");
            }
            processHandler = new OSProcessHandler(p, b.toString());

            ProcessTerminatedListener.attach(processHandler);
            processHandler.startNotify();

            view.attachToProcess(processHandler);

            String id = message("repl.title");
            ToolWindow window = manager.registerToolWindow(id, view.getComponent(), ToolWindowAnchor.BOTTOM);
            window.setIcon(ArcIcons.ARC_REPL_ICON);


            JComponent c = view.getComponent();
            final Component editorPane = c.getComponent(0);

            // TODO - How in the holy fucking hell do we get the component????????
            EditorImpl editorEx = (EditorImpl) editorPane;

            editorEx.setBackgroundColor(Color.YELLOW);
/*
            editorPane.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent event) {
                    editorPane.
                    view.scrollTo(view.getContentSize());
                }
            });
*/

            view.scrollTo(view.getContentSize());
        }
    }

    private boolean notConfigured(String... args) {
        for (String arg : args) {
            if (StringUtil.isEmptyOrSpaces(arg)) return true;
        }
        return false;
    }
}
