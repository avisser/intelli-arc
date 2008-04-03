package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;


/**
 *
 */
public class VariableReference extends ArcElement {
    public VariableReference(@NotNull final ASTNode node) {
        super(node);
    }

    public PsiReference getReference() {
        return new PsiReferenceBase<VariableReference>(this) {

            public PsiElement resolve() {
                return walkTree(myElement);
            }

            private PsiElement walkTree(PsiElement element) {
                PsiElement parent = element.getParent();
                if (parent == null) {
                    return null;
                }
                if (parent instanceof PsiFile) {
                    for (PsiElement def : parent.getChildren()) {
                        if (def instanceof PsiNamedElement) {
                            PsiNamedElement p = (PsiNamedElement) def;
                            if (p.getName().equals(myElement.getText())) {
                                return p;
                            }
                        }
                    }
                    return null;
                }
                if (parent instanceof Def || parent instanceof Mac) {
                    PsiNamedElement p = (PsiNamedElement) parent;
                    if (p.getName().equals(myElement.getText())) {
                        return p;
                    }
                    ParameterList params = PsiTreeUtil.getChildOfType(p, ParameterList.class);
                    if (params != null) {
                        for (PsiElement param : params.getChildren()) {
                            if (param instanceof VariableDefinition) {
                                VariableDefinition varDef = (VariableDefinition) param;
                                if (varDef.getName().equals(myElement.getText())) {
                                    return varDef;
                                }
                            }
                        }
                    }
                }
                return walkTree(parent);
            }


            public TextRange getRangeInElement() {
                return new TextRange(0, myElement.getTextLength());
            }

            public Object[] getVariants() {
                System.out.println("Inside getVariants()");
                return new Object[0];
            }
        };
    }
}