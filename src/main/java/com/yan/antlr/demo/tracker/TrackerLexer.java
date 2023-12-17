package com.yan.antlr.demo.tracker;// Generated from Tracker.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TrackerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Logic=1, Keyword=2, AndLogic=3, OrLogic=4, AndNotLogic=5, LeftBracket=6, 
		RightBracket=7, A=8, N=9, D=10, O=11, R=12, T=13, NEWLINE=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Logic", "Keyword", "AndLogic", "OrLogic", "AndNotLogic", "LeftBracket", 
			"RightBracket", "A", "N", "D", "O", "R", "T", "NEWLINE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Logic", "Keyword", "AndLogic", "OrLogic", "AndNotLogic", "LeftBracket", 
			"RightBracket", "A", "N", "D", "O", "R", "T", "NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public TrackerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tracker.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000fW\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000#\b\u0000\u0001\u0001\u0001"+
		"\u0001\u0004\u0001\'\b\u0001\u000b\u0001\f\u0001(\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0003\rM\b"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0004\u000eR\b\u000e\u000b\u000e\f\u000e"+
		"S\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u0001\u0000\b\u0004"+
		"\u0000  09AZaz\u0002\u0000AAaa\u0002\u0000NNnn\u0002\u0000DDdd\u0002\u0000"+
		"OOoo\u0002\u0000RRrr\u0002\u0000TTtt\u0003\u0000\t\n\r\r  [\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0001\"\u0001\u0000\u0000\u0000\u0003$\u0001\u0000\u0000"+
		"\u0000\u0005,\u0001\u0000\u0000\u0000\u00070\u0001\u0000\u0000\u0000\t"+
		"3\u0001\u0000\u0000\u0000\u000b;\u0001\u0000\u0000\u0000\r=\u0001\u0000"+
		"\u0000\u0000\u000f?\u0001\u0000\u0000\u0000\u0011A\u0001\u0000\u0000\u0000"+
		"\u0013C\u0001\u0000\u0000\u0000\u0015E\u0001\u0000\u0000\u0000\u0017G"+
		"\u0001\u0000\u0000\u0000\u0019I\u0001\u0000\u0000\u0000\u001bL\u0001\u0000"+
		"\u0000\u0000\u001dQ\u0001\u0000\u0000\u0000\u001f#\u0003\t\u0004\u0000"+
		" #\u0003\u0007\u0003\u0000!#\u0003\u0005\u0002\u0000\"\u001f\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000\"!\u0001\u0000\u0000\u0000#\u0002"+
		"\u0001\u0000\u0000\u0000$&\u0005\"\u0000\u0000%\'\u0007\u0000\u0000\u0000"+
		"&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000"+
		"\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*+\u0005\"\u0000"+
		"\u0000+\u0004\u0001\u0000\u0000\u0000,-\u0003\u000f\u0007\u0000-.\u0003"+
		"\u0011\b\u0000./\u0003\u0013\t\u0000/\u0006\u0001\u0000\u0000\u000001"+
		"\u0003\u0015\n\u000012\u0003\u0017\u000b\u00002\b\u0001\u0000\u0000\u0000"+
		"34\u0003\u000f\u0007\u000045\u0003\u0011\b\u000056\u0003\u0013\t\u0000"+
		"67\u0005 \u0000\u000078\u0003\u0011\b\u000089\u0003\u0015\n\u00009:\u0003"+
		"\u0019\f\u0000:\n\u0001\u0000\u0000\u0000;<\u0005(\u0000\u0000<\f\u0001"+
		"\u0000\u0000\u0000=>\u0005)\u0000\u0000>\u000e\u0001\u0000\u0000\u0000"+
		"?@\u0007\u0001\u0000\u0000@\u0010\u0001\u0000\u0000\u0000AB\u0007\u0002"+
		"\u0000\u0000B\u0012\u0001\u0000\u0000\u0000CD\u0007\u0003\u0000\u0000"+
		"D\u0014\u0001\u0000\u0000\u0000EF\u0007\u0004\u0000\u0000F\u0016\u0001"+
		"\u0000\u0000\u0000GH\u0007\u0005\u0000\u0000H\u0018\u0001\u0000\u0000"+
		"\u0000IJ\u0007\u0006\u0000\u0000J\u001a\u0001\u0000\u0000\u0000KM\u0005"+
		"\r\u0000\u0000LK\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NO\u0005\n\u0000\u0000O\u001c\u0001\u0000\u0000\u0000"+
		"PR\u0007\u0007\u0000\u0000QP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000UV\u0006\u000e\u0000\u0000V\u001e\u0001\u0000\u0000\u0000"+
		"\u0005\u0000\"(LS\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}