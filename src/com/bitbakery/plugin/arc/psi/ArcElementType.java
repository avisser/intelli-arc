package com.bitbakery.plugin.arc.psi;

import com.intellij.psi.tree.IElementType;
import com.bitbakery.plugin.arc.ArcFileType;
import org.jetbrains.annotations.NonNls;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcElementType extends IElementType {

    public ArcElementType(@NonNls String debugName) {
        super(debugName, ArcFileType.ARC);
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Arc:" + super.toString();
    }

}
