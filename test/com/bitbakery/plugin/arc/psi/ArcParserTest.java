package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.bitbakery.plugin.arc.ArcFileType;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcParserTest {
    
    private ASTNode parseTestExpression(String testExpression) {
        ArcParser parser = new ArcParser();
        return parser.parse(ArcElementTypes.FILE,
                new PsiBuilderImpl(ArcFileType.ARC, null, null, null, testExpression));
    }
}


