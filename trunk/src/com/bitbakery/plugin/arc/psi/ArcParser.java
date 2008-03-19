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
            if (LEFT_SQUARE == token) {
                parseBrackets(builder);
            } else if (LEFT_PAREN == token) {
                parseParens(builder);

            } else {
                if (RIGHT_PAREN == token) {
                    builder.error(message("parser.error.expectedLeftParen"));
                }
                builder.advanceLexer();
            }
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    /**
     * Enter: Lexer is pointed at the opening left square bracket
     * Exit: Lexer is pointed immediately after the closing right square bracket, or at the end-of-file
     */
    private void parseBrackets(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();

        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_SQUARE) {
                builder.advanceLexer();
                marker.done(SINGLE_ARG_ANONYMOUS_FUNCTION_DEFINITION);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightBracket"));
    }

    /**
     * Enter: Lexer is pointed at the opening left paren
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseParens(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        builder.advanceLexer();

        IElementType token = builder.getTokenType();
        if (DEF == token) {
            parseDef(builder, marker);
        } else if (FN == token) {
            parseFn(builder, marker);
        } else if (MAC == token) {
            parseMac(builder, marker);
        } else if (EQ == token) {
            parseAssignment(builder, marker);
        } else if (LITERALS.contains(token)) {
            // TODO - But this is OK if we're building a list, so we need to handle that situation...
            builder.error(message("parser.error.expectedFunctionOrMacro"));
            parseExpression(builder, marker);
        } else {
            parseExpression(builder, marker);
        }


    }

    /**
     * Enter: Lexer is pointed at the "def" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseDef(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(FUNCTION_DEFINITION);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }

    /**
     * Enter: Lexer is pointed at the "fn" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseFn(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(ANONYMOUS_FUNCTION_DEFINITION);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }

    /**
     * Enter: Lexer is pointed at the "mac" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseMac(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(MACRO_DEFINITION);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }

    /**
     * Enter: Lexer is pointed at the "=" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseAssignment(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(VARIABLE_ASSIGNMENT);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }

    /**
     * Enter: Lexer is pointed at the first token of the expression - in general, should be a function or macro call
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseExpression(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(EXPRESSION);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                builder.advanceLexer();
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }
}
