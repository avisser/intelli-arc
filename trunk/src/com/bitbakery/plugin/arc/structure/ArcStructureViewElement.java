package com.bitbakery.plugin.arc.structure;

import com.bitbakery.plugin.arc.psi.ArcElement;
import com.bitbakery.plugin.arc.psi.ArcElementVisitor;
import com.bitbakery.plugin.arc.psi.Def;
import com.bitbakery.plugin.arc.psi.Mac;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Specifies which Arc elements are to be displayed in the "structure view"
 */
public class ArcStructureViewElement implements StructureViewTreeElement {
    private PsiElement myElement;

    public ArcStructureViewElement(PsiElement element) {
        myElement = element;
    }

    public PsiElement getValue() {
        return myElement;
    }

    public void navigate(boolean requestFocus) {
        ((NavigationItem) myElement).navigate(requestFocus);
    }

    public boolean canNavigate() {
        return ((NavigationItem) myElement).canNavigate();
    }

    public boolean canNavigateToSource() {
        return ((NavigationItem) myElement).canNavigateToSource();
    }

    public StructureViewTreeElement[] getChildren() {
        final List<ArcElement> childrenElements = new ArrayList<ArcElement>();
        myElement.acceptChildren(new ArcElementVisitor() {
            public void visitElement(PsiElement element) {
                if (element instanceof Def || element instanceof Mac) {
                    childrenElements.add((ArcElement) element);
                } else {
                    element.acceptChildren(this);
                }
            }
        });

        StructureViewTreeElement[] children = new StructureViewTreeElement[childrenElements.size()];
        for (int i = 0; i < children.length; i++) {
            children[i] = new ArcStructureViewElement(childrenElements.get(i));
        }

        return children;
    }

    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            public String getPresentableText() {
                return ((PsiNamedElement) myElement).getName();
            }

            public TextAttributesKey getTextAttributesKey() {
                return null;
            }

            public String getLocationString() {
                return null;
            }

            public Icon getIcon(boolean open) {
                return myElement.getIcon(Iconable.ICON_FLAG_OPEN);
            }
        };
    }
}