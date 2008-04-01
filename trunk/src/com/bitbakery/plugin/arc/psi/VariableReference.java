package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class VariableReference extends ArcElement {
    public VariableReference(@NotNull final ASTNode node) {
        super(node);
    }
}