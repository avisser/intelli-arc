/* The following code was generated by JFlex 1.4.1 on 5/15/08 4:21 PM */

/***** JFlex specification for Arc *****/

package com.bitbakery.plugin.arc.lexer;

import static com.bitbakery.plugin.arc.lexer.ArcTokenTypes.*;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 5/15/08 4:21 PM from the specification file
 * <tt>src/com/bitbakery/plugin/arc/lexer/arc.flex</tt>
 */
public class _ArcLexer implements FlexLexer {
    /**
     * initial size of the lookahead buffer
     */
    private static final int ZZ_BUFFERSIZE = 16384;

    /**
     * lexical states
     */
    public static final int YYINITIAL = 0;

    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
            "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\16\1\35" +
                    "\1\7\3\16\1\60\1\36\1\37\1\5\1\14\1\61\1\14\1\15" +
                    "\1\6\1\12\11\11\1\63\1\4\1\16\1\56\2\16\1\62\1\65" +
                    "\1\16\1\66\1\52\1\13\1\51\1\16\1\54\1\44\2\16\1\45" +
                    "\1\64\1\43\1\50\1\16\1\46\2\16\1\42\1\47\1\16\1\53" +
                    "\3\16\1\40\1\17\1\41\2\16\1\57\1\27\1\32\1\30\1\52" +
                    "\1\21\1\51\1\16\1\54\1\24\2\16\1\23\1\64\1\20\1\50" +
                    "\1\26\1\46\1\33\1\25\1\31\1\34\1\16\1\22\3\16\1\0" +
                    "\1\10\1\0\1\55\uff81\0";

    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();

    private static final String ZZ_ACTION_PACKED_0 =
            "\1\1\1\2\3\3\3\1\1\4\1\1\1\5\4\1" +
                    "\1\6\1\7\1\10\1\11\1\12\1\13\3\1\1\14" +
                    "\1\15\1\16\1\17\1\20\1\21\1\1\2\0\2\22" +
                    "\1\1\1\0\4\4\3\1\1\23\2\7\1\1\1\24" +
                    "\1\1\1\25\1\26\1\1\1\0\2\1\5\27\1\30" +
                    "\1\1\1\31\1\1\1\32\1\33\2\0\1\34\4\0" +
                    "\1\35\1\1\1\34\3\0\1\36\5\0";

    private static int[] zzUnpackAction() {
        int[] result = new int[86];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

    private static final String ZZ_ROWMAP_PACKED_0 =
            "\0\0\0\67\0\156\0\67\0\245\0\334\0\u0113\0\u014a" +
                    "\0\u0181\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb\0\u0113" +
                    "\0\u0302\0\67\0\67\0\67\0\67\0\u0339\0\u0370\0\u03a7" +
                    "\0\67\0\u0113\0\67\0\67\0\u03de\0\67\0\u0415\0\245" +
                    "\0\u044c\0\u0483\0\67\0\u04ba\0\u04f1\0\u0528\0\u055f\0\u0596" +
                    "\0\u0113\0\u05cd\0\u0604\0\u063b\0\u0113\0\u0672\0\67\0\u06a9" +
                    "\0\u0113\0\u06e0\0\u0113\0\67\0\u0717\0\u074e\0\u0785\0\u07bc" +
                    "\0\67\0\u07f3\0\u082a\0\u0861\0\u0898\0\u0113\0\u08cf\0\u0113" +
                    "\0\u0906\0\u0113\0\u0113\0\u093d\0\u0974\0\u04ba\0\u09ab\0\u09e2" +
                    "\0\u0a19\0\u0a50\0\u0113\0\u0a87\0\u074e\0\u0abe\0\u0af5\0\u0b2c" +
                    "\0\u0113\0\u0b63\0\u0b9a\0\u0bd1\0\u0c08\0\u0c3f";

    private static int[] zzUnpackRowMap() {
        int[] result = new int[86];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;  /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();

    private static final String ZZ_TRANS_PACKED_0 =
            "\1\2\1\3\1\4\1\5\1\6\2\7\1\10\1\7" +
                    "\2\11\1\7\1\12\1\13\1\7\1\2\1\14\1\7" +
                    "\1\15\1\16\1\17\4\7\1\20\3\7\1\21\1\22" +
                    "\1\23\1\24\1\25\1\20\1\14\1\17\1\16\1\26" +
                    "\2\7\1\27\1\30\1\15\1\7\1\31\1\32\1\33" +
                    "\1\34\1\35\1\7\1\36\1\37\2\7\71\0\1\4" +
                    "\67\0\1\40\1\41\62\0\1\41\1\42\1\43\1\41" +
                    "\13\6\1\41\15\6\5\41\13\6\1\41\1\6\3\41" +
                    "\1\6\1\41\3\6\4\0\13\7\1\0\15\7\5\0" +
                    "\13\7\1\0\1\7\3\0\1\7\1\0\3\7\4\0" +
                    "\4\7\1\44\6\7\1\45\15\7\5\0\13\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\5\7\2\11" +
                    "\1\46\1\7\1\47\1\7\1\0\1\7\1\46\13\7" +
                    "\5\0\13\7\1\0\1\7\3\0\1\7\1\0\3\7" +
                    "\4\0\5\7\1\50\1\51\4\7\1\0\15\7\5\0" +
                    "\13\7\1\0\1\7\3\0\1\7\1\0\3\7\4\0" +
                    "\5\7\2\47\4\7\1\0\15\7\5\0\13\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\13\7\1\0" +
                    "\4\7\1\52\10\7\5\0\2\7\1\52\10\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\13\7\1\0" +
                    "\4\7\1\53\10\7\5\0\2\7\1\53\10\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\7\7\1\54" +
                    "\3\7\1\0\1\7\1\54\13\7\5\0\13\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\13\7\1\0" +
                    "\15\7\5\0\7\7\1\55\3\7\1\0\1\7\3\0" +
                    "\1\7\1\0\3\7\17\21\1\56\15\21\1\57\31\21" +
                    "\4\0\13\7\1\0\14\7\1\60\5\0\5\7\1\60" +
                    "\5\7\1\0\1\7\3\0\1\7\1\0\3\7\4\0" +
                    "\13\7\1\0\1\61\14\7\5\0\1\7\1\61\11\7" +
                    "\1\0\1\7\3\0\1\7\1\0\3\7\4\0\7\7" +
                    "\1\62\3\7\1\0\1\7\1\62\13\7\5\0\6\7" +
                    "\1\63\4\7\1\0\1\7\3\0\1\7\1\0\3\7" +
                    "\62\0\1\64\10\0\13\7\1\0\7\7\1\65\5\7" +
                    "\5\0\13\7\1\0\1\7\3\0\1\7\1\0\1\7" +
                    "\1\65\1\7\1\41\1\42\1\43\64\41\2\0\1\43" +
                    "\64\0\4\66\1\44\1\67\2\44\1\70\6\44\1\66" +
                    "\15\44\5\66\13\44\1\66\1\44\3\66\1\44\1\66" +
                    "\3\44\4\0\13\71\1\0\1\72\4\71\1\73\3\71" +
                    "\1\74\1\71\1\75\2\71\4\0\13\71\1\0\1\71" +
                    "\3\0\1\71\1\0\3\71\4\0\5\7\2\50\1\7" +
                    "\1\50\2\7\1\0\15\7\5\0\13\7\1\0\1\7" +
                    "\3\0\1\7\1\0\3\7\4\0\5\7\2\47\1\46" +
                    "\3\7\1\0\1\7\1\46\13\7\5\0\13\7\1\0" +
                    "\1\7\3\0\1\7\1\0\3\7\4\0\5\7\2\50" +
                    "\4\7\1\0\15\7\5\0\13\7\1\0\1\7\3\0" +
                    "\1\7\1\0\3\7\4\0\13\7\1\0\3\7\1\76" +
                    "\11\7\5\0\3\7\1\76\7\7\1\0\1\7\3\0" +
                    "\1\7\1\0\3\7\4\0\13\7\1\0\11\7\1\77" +
                    "\3\7\5\0\1\77\12\7\1\0\1\7\3\0\1\7" +
                    "\1\0\3\7\4\0\13\7\1\0\11\7\1\100\3\7" +
                    "\5\0\1\100\12\7\1\0\1\7\3\0\1\7\1\0" +
                    "\3\7\1\21\2\0\64\21\4\0\13\7\1\0\15\7" +
                    "\5\0\6\7\1\101\4\7\1\0\1\7\3\0\1\7" +
                    "\1\0\3\7\4\0\13\7\1\0\15\7\5\0\7\7" +
                    "\1\102\3\7\1\0\1\7\3\0\1\7\1\0\3\7" +
                    "\4\0\13\7\1\0\10\7\1\103\4\7\5\0\13\7" +
                    "\1\0\1\7\3\0\1\7\1\0\2\7\1\103\5\66" +
                    "\1\104\2\66\1\105\62\66\1\44\1\67\1\7\10\44" +
                    "\1\66\15\44\5\66\13\44\1\66\1\44\3\66\1\44" +
                    "\1\66\3\44\4\66\1\44\1\67\1\44\1\106\1\70" +
                    "\6\44\1\66\15\44\5\66\13\44\1\66\1\44\3\66" +
                    "\1\44\1\66\3\44\21\0\1\107\73\0\1\110\67\0" +
                    "\1\111\60\0\1\112\51\0\13\7\1\0\15\7\5\0" +
                    "\12\7\1\113\1\0\1\7\3\0\1\7\1\0\3\7" +
                    "\4\0\13\7\1\0\11\7\1\114\3\7\5\0\1\114" +
                    "\12\7\1\0\1\7\3\0\1\7\1\0\3\7\5\66" +
                    "\1\104\1\0\65\66\1\104\1\66\1\115\1\105\56\66" +
                    "\22\0\1\116\73\0\1\117\71\0\1\71\65\0\1\120" +
                    "\41\0\7\7\1\121\3\7\1\0\1\7\1\121\13\7" +
                    "\5\0\13\7\1\0\1\7\3\0\1\7\1\0\3\7" +
                    "\23\0\1\122\73\0\1\123\72\0\1\124\56\0\1\125" +
                    "\63\0\1\71\100\0\1\126\53\0\1\123\66\0\1\71" +
                    "\46\0";

    private static int[] zzUnpackTrans() {
        int[] result = new int[3190];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final char[] EMPTY_BUFFER = new char[0];
    private static final int YYEOF = -1;
    private static java.io.Reader zzReader = null; // Fake

    /* error messages for the codes above */
    private static final String ZZ_ERROR_MSG[] = {
            "Unkown internal scanner error",
            "Error: could not match input",
            "Error: pushback value was too large"
    };

    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

    private static final String ZZ_ATTRIBUTE_PACKED_0 =
            "\1\1\1\11\1\1\1\11\15\1\4\11\3\1\1\11" +
                    "\1\1\2\11\1\1\1\11\1\1\2\0\1\1\1\11" +
                    "\1\1\1\0\11\1\1\11\4\1\1\11\1\1\1\0" +
                    "\2\1\1\11\12\1\2\0\1\1\4\0\3\1\3\0" +
                    "\1\1\5\0";

    private static int[] zzUnpackAttribute() {
        int[] result = new int[86];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    /**
     * the current state of the DFA
     */
    private int zzState;

    /**
     * the current lexical state
     */
    private int zzLexicalState = YYINITIAL;

    /**
     * this buffer contains the current text to be matched and is
     * the source of the yytext() string
     */
    private CharSequence zzBuffer = "";

    /**
     * this buffer may contains the current text array to be matched when it is cheap to acquire it
     */
    private char[] zzBufferArray;

    /**
     * the textposition at the last accepting state
     */
    private int zzMarkedPos;

    /**
     * the textposition at the last state to be included in yytext
     */
    private int zzPushbackPos;

    /**
     * the current text position in the buffer
     */
    private int zzCurrentPos;

    /**
     * startRead marks the beginning of the yytext() string in the buffer
     */
    private int zzStartRead;

    /**
     * endRead marks the last character in the buffer, that has been read
     * from input
     */
    private int zzEndRead;

    /**
     * zzAtBOL == true <=> the scanner is currently at the beginning of a line
     */
    private boolean zzAtBOL = true;

    /**
     * zzAtEOF == true <=> the scanner is at the EOF
     */
    private boolean zzAtEOF;

    /* user code: */
    StringBuffer string = new StringBuffer();


    public _ArcLexer(java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Creates a new scanner.
     * There is also java.io.Reader version of this constructor.
     *
     * @param in the java.io.Inputstream to read input from.
     */
    public _ArcLexer(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[0x10000];
        int i = 0;  /* index in packed string  */
        int j = 0;  /* index in unpacked array */
        while (i < 168) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do map[j++] = value; while (--count > 0);
        }
        return map;
    }

    public final int getTokenStart() {
        return zzStartRead;
    }

    public final int getTokenEnd() {
        return getTokenStart() + yylength();
    }

    public void reset(CharSequence buffer, int start, int end, int initialState) {
        zzBuffer = buffer;
        zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
        zzCurrentPos = zzMarkedPos = zzStartRead = start;
        zzPushbackPos = 0;
        zzAtEOF = false;
        zzAtBOL = true;
        zzEndRead = end;
        yybegin(initialState);
    }

    // For Demetra compatibility
    public void reset(CharSequence buffer, int initialState) {
        zzBuffer = buffer;
        zzBufferArray = null;
        zzCurrentPos = zzMarkedPos = zzStartRead = 0;
        zzPushbackPos = 0;
        zzAtEOF = false;
        zzAtBOL = true;
        zzEndRead = buffer.length();
        yybegin(initialState);
    }

    /**
     * Refills the input buffer.
     *
     * @return <code>false</code>, iff there was new input.
     * @throws java.io.IOException if any I/O-Error occurs
     */
    private boolean zzRefill() throws java.io.IOException {
        return true;
    }


    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }


    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    public final void yybegin(int newState) {
        zzLexicalState = newState;
    }


    /**
     * Returns the text matched by the current regular expression.
     */
    public final CharSequence yytext() {
        return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
    }


    /**
     * Returns the character at position <tt>pos</tt> from the
     * matched text.
     * <p/>
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch.
     *            A value from 0 to yylength()-1.
     * @return the character at position pos
     */
    public final char yycharat(int pos) {
        return zzBufferArray != null ? zzBufferArray[zzStartRead + pos] : zzBuffer.charAt(zzStartRead + pos);
    }


    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }


    /**
     * Reports an error that occured while scanning.
     * <p/>
     * In a wellformed scanner (no or only correct usage of
     * yypushback(int) and a match-all fallback rule) this method
     * will only be called with things that "Can't Possibly Happen".
     * If this method is called, something is seriously wrong
     * (e.g. a JFlex bug producing a faulty scanner etc.).
     * <p/>
     * Usual syntax/scanner level error handling should be done
     * in error fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }


    /**
     * Pushes the specified amount of characters back into the input stream.
     * <p/>
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again.
     *               This number must not be greater than yylength()!
     */
    public void yypushback(int number) {
        if (number > yylength())
            zzScanError(ZZ_PUSHBACK_2BIG);

        zzMarkedPos -= number;
    }


    /**
     * Resumes scanning until the next regular expression is matched,
     * the end of input is encountered or an I/O-Error occurs.
     *
     * @return the next token
     * @throws java.io.IOException if any I/O-Error occurs
     */
    public IElementType advance() throws java.io.IOException {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        CharSequence zzBufferL = zzBuffer;
        char[] zzBufferArrayL = zzBufferArray;
        char[] zzCMapL = ZZ_CMAP;

        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = zzLexicalState;


            zzForAction:
            {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL)
                        zzInput = zzBufferL.charAt(zzCurrentPosL++);
                    else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = zzBufferL.charAt(zzCurrentPosL++);
                        }
                    }
                    int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) break zzForAction;
                    zzState = zzNext;

                    int zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) break zzForAction;
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 16: {
                    return COMMA;
                }
                case 31:
                    break;
                case 15: {
                    return QUOTE;
                }
                case 32:
                    break;
                case 22: {
                    return COMMA_AT;
                }
                case 33:
                    break;
                case 18: {
                    return LINE_COMMENT;
                }
                case 34:
                    break;
                case 23: {
                    return CHAR_LITERAL;
                }
                case 35:
                    break;
                case 19: {
                    return IF;
                }
                case 36:
                    break;
                case 2: {
                    return BAD_CHARACTER;
                }
                case 37:
                    break;
                case 8: {
                    return LEFT_PAREN;
                }
                case 38:
                    break;
                case 20: {
                    return FN;
                }
                case 39:
                    break;
                case 14: {
                    return BACKQUOTE;
                }
                case 40:
                    break;
                case 17: {
                    return COMPOSER;
                }
                case 41:
                    break;
                case 24: {
                    return NIL;
                }
                case 42:
                    break;
                case 10: {
                    return LEFT_SQUARE;
                }
                case 43:
                    break;
                case 30: {
                    return QUOTE_KEYWORD;
                }
                case 44:
                    break;
                case 9: {
                    return RIGHT_PAREN;
                }
                case 45:
                    break;
                case 7: {
                    return STRING_LITERAL;
                }
                case 46:
                    break;
                case 21: {
                    return DO;
                }
                case 47:
                    break;
                case 4: {
                    return NUMERIC_LITERAL;
                }
                case 48:
                    break;
                case 28: {
                    return BLOCK_COMMENT;
                }
                case 49:
                    break;
                case 25: {
                    return LET;
                }
                case 50:
                    break;
                case 1: {
                    return SYMBOL;
                }
                case 51:
                    break;
                case 26: {
                    return DEF;
                }
                case 52:
                    break;
                case 27: {
                    return MAC;
                }
                case 53:
                    break;
                case 13: {
                    return EQ;
                }
                case 54:
                    break;
                case 12: {
                    return TILDE;
                }
                case 55:
                    break;
                case 5: {
                    return DOT;
                }
                case 56:
                    break;
                case 29: {
                    return WITH;
                }
                case 57:
                    break;
                case 6: {
                    return TRUE;
                }
                case 58:
                    break;
                case 3: {
                    return WHITESPACE;
                }
                case 59:
                    break;
                case 11: {
                    return RIGHT_SQUARE;
                }
                case 60:
                    break;
                default:
                    if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
                        zzAtEOF = true;
                        return null;
                    } else {
                        zzScanError(ZZ_NO_MATCH);
                    }
            }
        }
    }


}
