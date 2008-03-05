package com.bitbakery.plugin.arc.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.bitbakery.plugin.arc.ArcFileType;
import com.bitbakery.plugin.arc.lexer.ArcLexer;
import com.bitbakery.plugin.arc.psi.ArcElementTypes;
import org.junit.Test;
import org.junit.Assert;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class ArcParserTest {

    @Test
    public void testEmptyExpression() {
        ASTNode root = parse("()");
        Assert.assertEquals(ArcElementTypes.FILE, root.getElementType());
    }

    private ASTNode parse(String testExpression) {
        ArcParser parser = new ArcParser();
        return parser.parse(ArcElementTypes.FILE,
                new PsiBuilderImpl(ArcFileType.ARC, new ArcLexer(), null, null, testExpression));
    }
}


