package com.bitbakery.plugin.arc.psi;

import com.bitbakery.plugin.arc.ArcIcons;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;

import javax.swing.*;

/**
 * PSI element for Arc macro ("mac") definitions.
 */
public class Mac extends VariableAssignment {

    public Mac(ASTNode node) {
        super(node);
        ASTNode[] children = node.getChildren(TokenSet.create(ArcElementTypes.VARIABLE_DEFINITION));
        name = isEmpty(children) ? "mac" : children[0].getText();
    }

    public Icon getIcon(int flags) {
        return ArcIcons.ARC_MAC_ICON;
    }
}
