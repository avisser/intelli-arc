package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class Variable extends ArcElement {
    public Variable(@NotNull final ASTNode node) {
        super(node);
    }
}
