package com.bitbakery.plugin.arc.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.FileViewProvider;
import com.intellij.lang.ASTNode;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.bitbakery.plugin.arc.ArcSupportLoader;
import com.bitbakery.plugin.arc.ArcFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Root PSI element for an Arc file
 */
public class Root extends ArcElement {
    public Root(ASTNode node) {
        super(node);
    }
}
