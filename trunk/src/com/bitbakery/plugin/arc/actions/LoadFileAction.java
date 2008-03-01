package com.bitbakery.plugin.arc.actions;

import com.bitbakery.plugin.arc.repl.Repl;
import com.bitbakery.plugin.arc.repl.ReplToolWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class LoadFileAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(DataKeys.PROJECT);
        ReplToolWindow replToolWindow = (ReplToolWindow) project.getComponent("ReplToolWindow.ArcPlugin");
        Repl repl = replToolWindow.getCurrentRepl();

        String path = e.getData(DataKeys.VIRTUAL_FILE).getPath();
        repl.execute("(load \"" + path + "\")");
    }
}
