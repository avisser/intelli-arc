package com.bitbakery.plugin.arc.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import static com.intellij.openapi.actionSystem.DataKeys.FILE_TEXT;
import com.intellij.openapi.editor.Editor;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class MacexAction extends AnAction {

    // TODO: QUESTION - How do I get the current editor??
    public void actionPerformed(AnActionEvent e) {
        String fileText = e.getData(FILE_TEXT);    // WOOOOOOOOOOOOOOOOO HOOOOOOOOOOOOOOOOO!!!
        System.out.println(fileText);
        Editor ed = e.getData(DataKeys.EDITOR);
        //TextEditorComponent textEd = ed.getComponent();

        // TODO: There are data keys for the current PsiElement... we just need to pull that.


        // TODO: Note that there are all sorts of good "models" hanging off the Editor!!
        System.out.println(ed.getSelectionModel().getSelectedText());
    }
}
