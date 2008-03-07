package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for concrete flavors of S-expressions
 */
public class Expression extends ArcElement {
    public Expression(@NotNull final ASTNode node) {
        super(node);
    }

    protected boolean isEmpty(ASTNode[] children) {
        return children == null || children.length < 1;
    }

    public PsiReference getReference() {
        return new PsiReferenceBase<Expression>(this, false) {
            public PsiElement resolve() {
                PsiFile file = myElement.getContainingFile();
                if (file.getChildren().length > 16) {
                    return file.getChildren()[16];
                }
                return null;
            }

            public Object[] getVariants() {
                return new String[]{"hello", "herlo"};
            }
        };
    }
}
