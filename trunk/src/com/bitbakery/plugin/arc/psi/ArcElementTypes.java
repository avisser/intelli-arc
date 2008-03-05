package com.bitbakery.plugin.arc.psi;

import com.bitbakery.plugin.arc.ArcFileType;
import com.bitbakery.plugin.arc.psi.ArcElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * Defines all of the various PSI element types for Arc
 */
public interface ArcElementTypes {
    IFileElementType FILE = new IFileElementType(ArcFileType.ARC);

    IElementType FUNCTION_DEFINITION = new ArcElementType("def");
    IElementType MACRO_DEFINITION = new ArcElementType("mac");
    IElementType QUOTED_EXPRESSION = new ArcElementType("quoted expression") ;
    IElementType EXPRESSION = new ArcElementType("s expression") ;
}
