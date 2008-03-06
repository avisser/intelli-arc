package com.bitbakery.plugin.arc;

import static com.bitbakery.plugin.arc.ArcResourceBundle.message;

public class ArcConstants {
    public static final String ERROR_EXPECTED_LEFT_PAREN = message("parser.error.expectedLeftParen"); //"Expected '('";
    public static final String ERROR_EXPECTED_RIGHT_PAREN = message("parser.error.expectedRightParen"); //"Expected ')'";
    public static final String ERROR_EXPECTED_SYMBOL = message("parser.error.expectedSymbol"); //"Expected symbol";
    public static final String ERROR_EXPECTED_SYMBOL_OR_ETC = message("parser.error.expectedSymbolOrOtherwise"); //"Expected symbol, literal, or function/macro call";
}
