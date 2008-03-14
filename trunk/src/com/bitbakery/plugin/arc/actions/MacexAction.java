package com.bitbakery.plugin.arc.actions;

import com.bitbakery.plugin.arc.psi.Mac;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.psi.PsiElement;

/**
 * Displays macroexpansion ("macex") for the currently selected macro definition
 */
public class MacexAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {

        PsiElement mac = e.getData(DataKeys.PSI_ELEMENT);

        // TODO: This doesn't totally work, because mac (and other elements) don't have the correct text range
        if (mac instanceof Mac) {
            // TODO: Display tooltip window...?
            System.out.println("mac = " + ((Mac) mac).getName());
        }
    }
}
