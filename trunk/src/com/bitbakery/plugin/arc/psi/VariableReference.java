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
            return walkTree(myElement);
        }

        private PsiElement walkTree(PsiElement element) {
            PsiElement parent = element.getParent();
            if (parent == null) {
                // TODO - Actually, I want to look for variable definitions (including def/mac) in other project files, as well as any core Arc files (i.e., stuff in arcN.tar)
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
                    // TODO - Actually, I should probably return the VariableDefinition child of the Def/Mac element
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
            if (parent instanceof Fn) {
                ParameterList params = PsiTreeUtil.getChildOfType(parent, ParameterList.class);
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
            // TODO - Implement me to get code completion working - need to walk up the tree and gather every variable def (including def/mac), plus all top-level declarations, including other files
            //return new Object[0];
            return new String[]{"var-one", "var-two", "var-three"};
        }
    }
}