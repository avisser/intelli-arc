package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;


/**
 *
 */
public class VariableReference extends ArcElement {
    private MyPsiReference reference;

    public VariableReference(@NotNull final ASTNode node) {
        super(node);
        reference = new MyPsiReference(this);
    }

    public PsiReference getReference() {
        return reference;
    }

    private class MyPsiReference extends PsiReferenceBase<VariableReference> {
        public MyPsiReference(VariableReference element) {
            super(element);
        }

        public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
            return super.handleElementRename(newElementName);    // TODO - Do something!
        }

        public PsiElement resolve() {
            return walkTree(myElement.getParent());
        }

        private PsiElement walkTree(PsiElement e) {
            if (e == null) {
                // TODO - Actually, I want to look for variable definitions (including def/mac) in other project files, as well as any core Arc files (i.e., stuff in arcN.tar)
                return null;
            } else if (e instanceof PsiFile) {
                for (PsiElement def : e.getChildren()) {
                    if (nameMatches(def)) {
                        return def;
                    }
                }
            } else if (e instanceof Def || e instanceof Mac || e instanceof Fn) {
                if (nameMatches(e)) {
                    return e;
                }

                ParameterList params = PsiTreeUtil.getChildOfType(e, ParameterList.class);
                if (params != null) {
                    for (PsiElement param : params.getChildren()) {
                        if (nameMatches(param)) {
                            return param;
                        }
                    }
                }
            }
            return walkTree(e.getParent());
        }

        private boolean nameMatches(PsiElement e) {
            return e instanceof PsiNamedElement
                    && ((PsiNamedElement) e).getName() != null
                    && ((PsiNamedElement) e).getName().equals(myElement.getText());
        }

        public TextRange getRangeInElement() {
            return new TextRange(0, myElement.getTextLength());
        }

        public Object[] getVariants() {
            // TODO - Implement me to get code completion working - need to walk up the tree and gather every variable def (including def/mac), plus all top-level declarations, including other files
            //return new Object[0];
            return new String[]{"var-one", "var-two", "var-three"};
        }
    }
}