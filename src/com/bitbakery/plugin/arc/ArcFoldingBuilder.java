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
        } else if (node.getElementType() == ArcElementTypes.EXPRESSION) {
            // TODO - We should actually print the first element, followed by an ellipsis
            return "(...)";
        } else if (node.getElementType() == ArcTokenTypes.BLOCK_COMMENT) {
            // TODO - Adjacent line comments should be foldable as a single block comment
            String text = node.getText();
            return text.length() > 15 ? text.substring(0, 15) + "..." : text;
        }
        return null;
    }

    public boolean isCollapsedByDefault(ASTNode node) {
        return false;
    }

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        touchTree(node);
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, document, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /** We have to touch the PSI tree to get the folding to show up when we first open a file */
    private void touchTree(ASTNode node) {
        if (node.getElementType() == ArcElementTypes.FILE) {
            final PsiElement firstChild = node.getPsi().getFirstChild();
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
                || (node.getElementType() == ArcElementTypes.EXPRESSION && node.getTextLength() > 5)
                || (node.getElementType() == ArcTokenTypes.BLOCK_COMMENT);
    }
}
