package com.bitbakery.plugin.arc.psi;

import static com.bitbakery.plugin.arc.ArcResourceBundle.message;
import static com.bitbakery.plugin.arc.lexer.ArcTokenTypes.*;
import static com.bitbakery.plugin.arc.psi.ArcElementTypes.*;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Consumes a stream of Arc tokens and generates a PSI tree for an Arc file
 */
public class ArcParser implements PsiParser {

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker rootMarker = builder.mark();
        while (!builder.eof()) {
            IElementType token = builder.getTokenType();
            if (token == LEFT_PAREN) {
                parseParens(builder);

            } else {
                if (token == RIGHT_PAREN) {
                    builder.error(message("parser.error.expectedLeftParen"));
                }
                builder.advanceLexer();
            }
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    /**
     * Enter: Lexer is pointed at the opening left paren
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseParens(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        builder.advanceLexer();

        // TODO - Here's where we can test to see if this is a def, mac or otherwise
        IElementType token = builder.getTokenType();
        if (token == DEF) {
            // TODO - We need specialized handling for parameter lists and such, methinks...
            parseRest(builder, marker, FUNCTION_DEFINITION);
        } else if (token == MAC) {
            // TODO - We need specialized handling for parameter lists and such, methinks...
            parseRest(builder, marker, MACRO_DEFINITION);
        } else if (LITERALS.contains(token)) {
            // TODO - But this is OK if we're building a list, so we need to handle that situation...
            builder.error(message("parser.error.expectedFunctionOrMacro"));
            parseRest(builder, marker, EXPRESSION);
        } else {
            parseRest(builder, marker, EXPRESSION);
        }


    }

    private void parseRest(PsiBuilder builder, PsiBuilder.Marker marker, IElementType type) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(type);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }
}
