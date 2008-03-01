package com.bitbakery.plugin.arc.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.bitbakery.plugin.arc.repl.ReplToolWindow;
import com.bitbakery.plugin.arc.repl.Repl;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class RunSelectionAction extends AnAction {
    
    public void actionPerformed(AnActionEvent e) {
        Editor ed = e.getData(DataKeys.EDITOR);
        String text = ed.getSelectionModel().getSelectedText();

        Project project = e.getData(DataKeys.PROJECT);
        ReplToolWindow replToolWindow = (ReplToolWindow) project.getComponent("ReplToolWindow.ArcPlugin");
        Repl repl = replToolWindow.getCurrentRepl();

        repl.execute(text);
    }
}
