package com.bitbakery.plugin.arc;

import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.bitbakery.plugin.arc.psi.ArcElementTypes;
import com.bitbakery.plugin.arc.psi.Def;
import com.bitbakery.plugin.arc.psi.Mac;
import com.bitbakery.plugin.arc.lexer.ArcTokenTypes;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines how code folding should behave for Arc files
 */
public class ArcFoldingBuilder implements FoldingBuilder {

    public String getPlaceholderText(ASTNode node) {
        if (node.getElementType() == ArcElementTypes.FUNCTION_DEFINITION) {
            Def def = (Def) node.getPsi();
            return "(def " + def.getName() + "...)";
        } else if (node.getElementType() == ArcElementTypes.MACRO_DEFINITION) {
            Mac def = (Mac) node.getPsi();
            return "(mac " + def.getName() + "...)";
        } else if (node.getElementType() == ArcTokenTypes.BLOCK_COMMENT) {
            String text = node.getText();
            return text.length() > 25 ? text.substring(0, 25) + "..." : text;
        }
        return null;
    }

    public boolean isCollapsedByDefault(ASTNode node) {
        return false;
    }

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        weirdHack(node);
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, document, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private void weirdHack(ASTNode node) {
        // We have to touch the PSI tree to get the folding to show up when we first open a file
        if (node.getElementType() == ArcElementTypes.FILE) {
            final PsiElement firstChild = node.getPsi().getFirstChild();
            System.out.println(firstChild.getText());
        }
    }

    private void appendDescriptors(final ASTNode node, final Document document, final List<FoldingDescriptor> descriptors) {
        if (isFoldableNode(node)) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, document, descriptors);
            child = child.getTreeNext();
        }
    }

    private boolean isFoldableNode(ASTNode node) {
        return (node.getElementType() == ArcElementTypes.FUNCTION_DEFINITION)
                || (node.getElementType() == ArcElementTypes.MACRO_DEFINITION)
                || (node.getElementType() == ArcTokenTypes.BLOCK_COMMENT);
    }
}
