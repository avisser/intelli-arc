package com.bitbakery.plugin.arc.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.lang.ASTNode;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class Def extends ArcElement implements PsiNamedElement {
    public Def(ASTNode node) {
        super(node);
    }

    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
