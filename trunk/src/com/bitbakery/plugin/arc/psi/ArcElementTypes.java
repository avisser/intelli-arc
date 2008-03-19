package com.bitbakery.plugin.arc.psi;

import com.bitbakery.plugin.arc.ArcFileType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * Defines all of the various PSI element types for Arc
 */
public interface ArcElementTypes {
    IFileElementType FILE = new IFileElementType(ArcFileType.ARC);

    IElementType SINGLE_ARG_ANONYMOUS_FUNCTION_DEFINITION = new ArcElementType("single arg fn");
    IElementType ANONYMOUS_FUNCTION_DEFINITION = new ArcElementType("fn");
    IElementType FUNCTION_DEFINITION = new ArcElementType("def");
    IElementType MACRO_DEFINITION = new ArcElementType("mac");
    IElementType VARIABLE_ASSIGNMENT = new ArcElementType("assignment");
    IElementType QUOTED_EXPRESSION = new ArcElementType("quoted expression");
    IElementType EXPRESSION = new ArcElementType("s expression");
}