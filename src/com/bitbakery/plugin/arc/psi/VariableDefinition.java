package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class VariableDefinition extends ArcElement {
    public VariableDefinition(@NotNull final ASTNode node) {
        super(node);
    }
}
