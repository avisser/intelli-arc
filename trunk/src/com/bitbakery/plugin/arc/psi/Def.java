package com.bitbakery.plugin.arc.psi;

import static com.bitbakery.plugin.arc.lexer.ArcTokenTypes.SYMBOL_FILTER;
import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class Def extends Expression implements PsiNamedElement {
    private String name;

    public Def(ASTNode node) {
        super(node);
        ASTNode[] children = node.getChildren(SYMBOL_FILTER);
        name = isEmpty(children) ? "def" : children[0].getText();
    }

    public String getName() {
        return name;
    }

    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        // TODO - Need to actually set the text in the symbol child element that defines the name
        this.name = name;
        return this;
    }

    public Icon getIcon(int flags) {
        return ArcIcons.ARC_DEF_ICON;
    }
}
