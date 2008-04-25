package com.bitbakery.plugin.arc.psi;

import static com.bitbakery.plugin.arc.ArcStrings.message;
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

                // TODO - Need some special handling around the underscore

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
        } else if (LET == token) {
            parseLet(builder, marker);
        } else if (WITH == token) {
            parseWith(builder, marker);
        } else if (LITERALS.contains(token)) {
            // TODO - But this is OK if we're building a list, so we need to handle that situation...
            builder.error(message("parser.error.expectedFunctionOrMacro"));
            parseExpression(builder, marker);
        } else {
            parseExpression(builder, marker);
        }
    }

    /**
     * Enter: Lexer is pointed at the "let" token
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseLet(PsiBuilder builder, PsiBuilder.Marker marker) {
        parseExpression(builder, marker);
/*
        // TODO - Fix me!
        if (parseIdentifier(builder, marker)) return;
        parseExpression(builder, marker);
        parseBody(builder, marker, LET_BLOCK);
*/
    }

    /**
     * Enter: Lexer is pointed at the "with" token
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseWith(PsiBuilder builder, PsiBuilder.Marker marker) {
        parseExpression(builder, marker);
/*
        // TODO - Fix me!
        if (parseParameterList(builder, marker)) return;
        parseBody(builder, marker, WITH_BLOCK);
*/
    }

    /**
     * Enter: Lexer is pointed at the "def" token
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseDef(PsiBuilder builder, PsiBuilder.Marker marker) {
        if (parseIdentifier(builder, marker)) return;
        if (parseParameterList(builder, marker)) return;

        // TODO - We need to get coloring to work for this.
        // TODO - We *could* just have a single string for the body, in which case this is *not* a docstring...
        if (builder.getTokenType() == STRING_LITERAL) {
            PsiBuilder.Marker docstring = builder.mark();
            builder.advanceLexer();
            docstring.done(DOCSTRING);
        }
        parseBody(builder, marker, FUNCTION_DEFINITION);
    }

    /**
     * Enter: Lexer is pointed at the "fn" token
     * Exit: Lexer is pointed immediately after the closing right paren, or at the end-of-file
     */
    private void parseFn(PsiBuilder builder, PsiBuilder.Marker marker) {
        builder.advanceLexer();
        if (parseParameterList(builder, marker)) return;
        parseBody(builder, marker, ANONYMOUS_FUNCTION_DEFINITION);
    }

    /**
     * Enter: Lexer is pointing at the token immediately preceding the identifier
     * Exit: Lexer is pointed immediately after the identifer, or at the end-of-file
     */
    private boolean parseIdentifier(PsiBuilder builder, PsiBuilder.Marker marker) {
        builder.advanceLexer();
        if (builder.eof()) {
            marker.error(message("parser.error.expectedIdentifier"));
            return true;
        }
        IElementType token = builder.getTokenType();
        PsiBuilder.Marker name = builder.mark();
        builder.advanceLexer();

        // TODO - We should have the annotator warn you if a non-arcN.tar file redefines a special char or keyword
        if (token != SYMBOL && !KEYWORDS.contains(token) && !SPECIAL_CHARACTERS.contains(token)) {
            name.error(message("parser.error.expectedIdentifier"));
        } else {
            name.done(VARIABLE_DEFINITION);
        }
        return false;
    }

    private void parseBody(PsiBuilder builder, PsiBuilder.Marker marker, IElementType defType) {
        while (!builder.eof()) {
            IElementType token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(defType);
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
     * Enter: Lexer is pointing at either the opening left paren of the parameter list, or else at the single "rest" parameter
     * Exit: Lexer is pointing immediately after the parameter list, or at the end-of-file
     */
    private boolean parseParameterList(PsiBuilder builder, PsiBuilder.Marker marker) {
        if (builder.eof()) {
            marker.error(message("parser.error.expectedParameterList"));
            return true;
        }

        PsiBuilder.Marker paramList = builder.mark();
        IElementType token = builder.getTokenType();
        if (token == RIGHT_PAREN) {
            builder.advanceLexer();
            paramList.error(message("parser.error.expectedParameterList"));
        } else if (token == LEFT_PAREN) {
            builder.advanceLexer();
            parseParameters(builder, paramList);
        } else if (token == LEFT_SQUARE) {
            parseBrackets(builder);
            paramList.error(message("parser.error.expectedParameterList"));
        } else {
            // This should be our single "rest" parameter
            PsiBuilder.Marker var = builder.mark();
            builder.advanceLexer();
            var.done(VARIABLE_DEFINITION);
            paramList.done(PARAMETER_LIST);
        }
        return false;
    }

    /**
     * Enter: Lexer is pointed at the "mac" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseMac(PsiBuilder builder, PsiBuilder.Marker marker) {
        if (parseIdentifier(builder, marker)) return;
        if (parseParameterList(builder, marker)) return;
        parseBody(builder, marker, MACRO_DEFINITION);
    }

    /**
     * Enter: Lexer is pointed at the "=" token
     * Exit: Lexer is pointed immediatelytely after the closing right paren, or at the end-of-file
     */
    private void parseAssignment(PsiBuilder builder, PsiBuilder.Marker marker) {
        builder.advanceLexer();
        if (builder.eof()) {
            marker.error(message("parser.error.expectedIdentifier"));
            return;
        }
        IElementType token = builder.getTokenType();
        PsiBuilder.Marker name = builder.mark();
        builder.advanceLexer();
        if (token == LEFT_PAREN) {
            builder.advanceLexer();
            parseExpression(builder, name);
        } else if (token == SYMBOL) {
            name.done(VARIABLE_DEFINITION);
        } else {
            name.error(message("parser.error.expectedIdentifier"));
        }
        parseBody(builder, marker, VARIABLE_ASSIGNMENT);
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
            } else if (token == SYMBOL) {
                PsiBuilder.Marker ref = builder.mark();
                builder.advanceLexer();
                ref.done(VARIABLE_REFERENCE);
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
    private void parseParameters(PsiBuilder builder, PsiBuilder.Marker marker) {
        IElementType token;
        while (!builder.eof()) {

            token = builder.getTokenType();
            if (token == RIGHT_PAREN) {
                builder.advanceLexer();
                marker.done(PARAMETER_LIST);
                return;
            } else if (token == LEFT_PAREN) {
                parseParens(builder);
            } else if (token == LEFT_SQUARE) {
                parseBrackets(builder);
            } else {
                PsiBuilder.Marker var = builder.mark();
                builder.advanceLexer();
                var.done(VARIABLE_DEFINITION);
            }
        }

        marker.error(message("parser.error.expectedRightParen"));
    }
}
