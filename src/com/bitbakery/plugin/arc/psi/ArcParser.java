package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcParser implements PsiParser {
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = builder.mark();
        while (!builder.eof()) {
            parseTopLevelStatement(builder);
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    private void parseTopLevelStatement(PsiBuilder builder) {
        // Top-level statement should be 
        builder.getTokenType();

        builder.advanceLexer();
    }
}
