package com.bitbakery.plugin.arc.psi;

import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * TODO - Describe roles and responsibilities
 */
public class VariableAssignment extends Expression implements PsiNamedElement {
    protected String name;

    public VariableAssignment(ASTNode node) {
        super(node);
        ASTNode[] children = node.getChildren(TokenSet.create(ArcElementTypes.VARIABLE_ASSIGNMENT));
        name = isEmpty(children) ? "=" : children[0].getText();
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
        return ArcIcons.ARC_DEF_ICON; // TODO - Create an icon for variable assignment
    }
}
