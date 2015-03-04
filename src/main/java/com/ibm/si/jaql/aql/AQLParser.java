// Generated from AQL.g4 by ANTLR 4.1
package com.ibm.si.jaql.aql;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AQLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ISNULL=1, NOTNULL=2, AND=3, OR=4, NOT=5, BETWEEN=6, LIKE=7, ILIKE=8, REGEX=9, 
		IREGEX=10, LIMIT=11, GROUP_BY=12, HAVING=13, ORDER_BY=14, FROM=15, WHERE=16, 
		ASC=17, DESC=18, COLLATE=19, INTO=20, IN=21, START=22, STOP=23, LAST=24, 
		MINUTES=25, HOURS=26, DAYS=27, ALL=28, AS=29, FTS=30, COUNT=31, FIRST=32, 
		SUM=33, AVG=34, MIN=35, MAX=36, UCOUNT=37, SELECT=38, SORTCURSOR=39, GETCURSOR=40, 
		REMOVECURSOR=41, DESCRIBE=42, PARAMETERS=43, TIMEINTERVAL=44, IntegerLiteral=45, 
		FloatingPointLiteral=46, Identifier=47, FunctionName=48, NullLiteral=49, 
		BooleanLiteral=50, StringLiteral=51, ASTERISK=52, Add=53, Subtract=54, 
		Multiply=55, Divide=56, Modulo=57, IDENTIFIER=58, EQ=59, NEQ=60, LT=61, 
		LE=62, GT=63, GE=64, QUOTE=65, SEMI=66, COLON=67, DOT=68, COMMA=69, RPAREN=70, 
		LPAREN=71, RBRACK=72, LBRACK=73, PLUS=74, MINUS=75, NEGATION=76, VERTBAR=77, 
		BITAND=78, POWER_OP=79, SLASH=80, WS=81, COMMENT=82, LINE_COMMENT=83;
	public static final String[] tokenNames = {
		"<INVALID>", "ISNULL", "NOTNULL", "AND", "OR", "NOT", "BETWEEN", "LIKE", 
		"ILIKE", "REGEX", "IREGEX", "LIMIT", "GROUP_BY", "HAVING", "ORDER_BY", 
		"FROM", "WHERE", "ASC", "DESC", "COLLATE", "INTO", "IN", "START", "STOP", 
		"LAST", "MINUTES", "HOURS", "DAYS", "ALL", "AS", "FTS", "COUNT", "FIRST", 
		"SUM", "AVG", "MIN", "MAX", "UCOUNT", "SELECT", "SORTCURSOR", "GETCURSOR", 
		"REMOVECURSOR", "DESCRIBE", "PARAMETERS", "TIMEINTERVAL", "IntegerLiteral", 
		"FloatingPointLiteral", "Identifier", "FunctionName", "NullLiteral", "BooleanLiteral", 
		"StringLiteral", "'*'", "Add", "Subtract", "Multiply", "Divide", "'%'", 
		"IDENTIFIER", "EQ", "NEQ", "'<'", "'<='", "'>'", "'>='", "'''", "';'", 
		"':'", "'.'", "','", "')'", "'('", "']'", "'['", "'+'", "'-'", "'~'", 
		"'|'", "'&'", "'^'", "'/'", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_criteria = 0, RULE_filter = 1, RULE_statement = 2, RULE_query = 3, 
		RULE_scalarSubquery = 4, RULE_queryTime = 5, RULE_queryParams = 6, RULE_parameter = 7, 
		RULE_database = 8, RULE_displayList = 9, RULE_displayColumn = 10, RULE_whereClause = 11, 
		RULE_orderBy = 12, RULE_subquery = 13, RULE_groupBy = 14, RULE_booleanExpression = 15, 
		RULE_booleanOperator = 16, RULE_comparisonOperator = 17, RULE_betweenOperator = 18, 
		RULE_likeOperator = 19, RULE_nullOperator = 20, RULE_inOperator = 21, 
		RULE_expressionList = 22, RULE_expression = 23, RULE_arithmeticOperator = 24, 
		RULE_column = 25, RULE_scalarFunction = 26, RULE_arguments = 27, RULE_aggregateFunction = 28, 
		RULE_literal = 29;
	public static final String[] ruleNames = {
		"criteria", "filter", "statement", "query", "scalarSubquery", "queryTime", 
		"queryParams", "parameter", "database", "displayList", "displayColumn", 
		"whereClause", "orderBy", "subquery", "groupBy", "booleanExpression", 
		"booleanOperator", "comparisonOperator", "betweenOperator", "likeOperator", 
		"nullOperator", "inOperator", "expressionList", "expression", "arithmeticOperator", 
		"column", "scalarFunction", "arguments", "aggregateFunction", "literal"
	};

	@Override
	public String getGrammarFileName() { return "AQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public AQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CriteriaContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public TerminalNode SEMI() { return getToken(AQLParser.SEMI, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public TerminalNode FTS() { return getToken(AQLParser.FTS, 0); }
		public CriteriaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criteria; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterCriteria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitCriteria(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitCriteria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriteriaContext criteria() throws RecognitionException {
		CriteriaContext _localctx = new CriteriaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_criteria);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(60); match(FTS);
				setState(61); match(StringLiteral);
				}
				break;

			case 2:
				{
				setState(65);
				_la = _input.LA(1);
				if (_la==FTS) {
					{
					setState(62); match(FTS);
					setState(63); match(StringLiteral);
					setState(64); match(AND);
					}
				}

				setState(67); booleanExpression(0);
				}
				break;
			}
			setState(71);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(70); match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_filter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(73); match(AND);
				}
			}

			setState(76); booleanExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(AQLParser.SEMI, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(78); query();
			}
			setState(80);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(79); match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public DatabaseContext database;
		public List<DatabaseContext> databases = new ArrayList<DatabaseContext>();
		public Token cursorName;
		public Token limit;
		public TerminalNode LIMIT() { return getToken(AQLParser.LIMIT, 0); }
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode INTO() { return getToken(AQLParser.INTO, 0); }
		public TerminalNode Identifier() { return getToken(AQLParser.Identifier, 0); }
		public DisplayListContext displayList() {
			return getRuleContext(DisplayListContext.class,0);
		}
		public QueryTimeContext queryTime() {
			return getRuleContext(QueryTimeContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(AQLParser.SELECT, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public List<DatabaseContext> database() {
			return getRuleContexts(DatabaseContext.class);
		}
		public QueryParamsContext queryParams() {
			return getRuleContext(QueryParamsContext.class,0);
		}
		public DatabaseContext database(int i) {
			return getRuleContext(DatabaseContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public TerminalNode IntegerLiteral() { return getToken(AQLParser.IntegerLiteral, 0); }
		public GroupByContext groupBy() {
			return getRuleContext(GroupByContext.class,0);
		}
		public TerminalNode FROM() { return getToken(AQLParser.FROM, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(SELECT);
			setState(83); displayList();
			setState(84); match(FROM);
			setState(85); ((QueryContext)_localctx).database = database();
			((QueryContext)_localctx).databases.add(((QueryContext)_localctx).database);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(86); match(COMMA);
				setState(87); ((QueryContext)_localctx).database = database();
				((QueryContext)_localctx).databases.add(((QueryContext)_localctx).database);
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(93); match(INTO);
				setState(94); ((QueryContext)_localctx).cursorName = match(Identifier);
				}
			}

			setState(98);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(97); whereClause();
				}
			}

			setState(101);
			_la = _input.LA(1);
			if (_la==GROUP_BY) {
				{
				setState(100); groupBy();
				}
			}

			setState(104);
			_la = _input.LA(1);
			if (_la==ORDER_BY) {
				{
				setState(103); orderBy();
				}
			}

			setState(108);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(106); match(LIMIT);
				setState(107); ((QueryContext)_localctx).limit = match(IntegerLiteral);
				}
			}

			setState(111);
			_la = _input.LA(1);
			if (_la==START || _la==LAST) {
				{
				setState(110); queryTime();
				}
			}

			setState(114);
			_la = _input.LA(1);
			if (_la==PARAMETERS) {
				{
				setState(113); queryParams();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarSubqueryContext extends ParserRuleContext {
		public DisplayColumnContext displayColumn;
		public List<DisplayColumnContext> columns = new ArrayList<DisplayColumnContext>();
		public DatabaseContext database;
		public List<DatabaseContext> databases = new ArrayList<DatabaseContext>();
		public Token limit;
		public List<DatabaseContext> database() {
			return getRuleContexts(DatabaseContext.class);
		}
		public QueryParamsContext queryParams() {
			return getRuleContext(QueryParamsContext.class,0);
		}
		public TerminalNode LIMIT() { return getToken(AQLParser.LIMIT, 0); }
		public DatabaseContext database(int i) {
			return getRuleContext(DatabaseContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public TerminalNode IntegerLiteral() { return getToken(AQLParser.IntegerLiteral, 0); }
		public GroupByContext groupBy() {
			return getRuleContext(GroupByContext.class,0);
		}
		public QueryTimeContext queryTime() {
			return getRuleContext(QueryTimeContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(AQLParser.SELECT, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public DisplayColumnContext displayColumn() {
			return getRuleContext(DisplayColumnContext.class,0);
		}
		public TerminalNode FROM() { return getToken(AQLParser.FROM, 0); }
		public ScalarSubqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarSubquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterScalarSubquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitScalarSubquery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitScalarSubquery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarSubqueryContext scalarSubquery() throws RecognitionException {
		ScalarSubqueryContext _localctx = new ScalarSubqueryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scalarSubquery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(SELECT);
			setState(117); ((ScalarSubqueryContext)_localctx).displayColumn = displayColumn();
			((ScalarSubqueryContext)_localctx).columns.add(((ScalarSubqueryContext)_localctx).displayColumn);
			setState(118); match(FROM);
			setState(119); ((ScalarSubqueryContext)_localctx).database = database();
			((ScalarSubqueryContext)_localctx).databases.add(((ScalarSubqueryContext)_localctx).database);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(120); match(COMMA);
				setState(121); ((ScalarSubqueryContext)_localctx).database = database();
				((ScalarSubqueryContext)_localctx).databases.add(((ScalarSubqueryContext)_localctx).database);
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(127); whereClause();
				}
			}

			setState(131);
			_la = _input.LA(1);
			if (_la==GROUP_BY) {
				{
				setState(130); groupBy();
				}
			}

			setState(135);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(133); match(LIMIT);
				setState(134); ((ScalarSubqueryContext)_localctx).limit = match(IntegerLiteral);
				}
			}

			setState(138);
			_la = _input.LA(1);
			if (_la==START || _la==LAST) {
				{
				setState(137); queryTime();
				}
			}

			setState(141);
			_la = _input.LA(1);
			if (_la==PARAMETERS) {
				{
				setState(140); queryParams();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryTimeContext extends ParserRuleContext {
		public Token startTime;
		public Token stopTime;
		public Token minutes;
		public TerminalNode STOP() { return getToken(AQLParser.STOP, 0); }
		public TerminalNode START() { return getToken(AQLParser.START, 0); }
		public TerminalNode MINUTES() { return getToken(AQLParser.MINUTES, 0); }
		public TerminalNode IntegerLiteral() { return getToken(AQLParser.IntegerLiteral, 0); }
		public TerminalNode HOURS() { return getToken(AQLParser.HOURS, 0); }
		public List<TerminalNode> StringLiteral() { return getTokens(AQLParser.StringLiteral); }
		public TerminalNode DAYS() { return getToken(AQLParser.DAYS, 0); }
		public TerminalNode LAST() { return getToken(AQLParser.LAST, 0); }
		public TerminalNode StringLiteral(int i) {
			return getToken(AQLParser.StringLiteral, i);
		}
		public QueryTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryTime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterQueryTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitQueryTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitQueryTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryTimeContext queryTime() throws RecognitionException {
		QueryTimeContext _localctx = new QueryTimeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_queryTime);
		int _la;
		try {
			setState(152);
			switch (_input.LA(1)) {
			case START:
				enterOuterAlt(_localctx, 1);
				{
				setState(143); match(START);
				setState(144); ((QueryTimeContext)_localctx).startTime = match(StringLiteral);
				setState(147);
				_la = _input.LA(1);
				if (_la==STOP) {
					{
					setState(145); match(STOP);
					setState(146); ((QueryTimeContext)_localctx).stopTime = match(StringLiteral);
					}
				}

				}
				break;
			case LAST:
				enterOuterAlt(_localctx, 2);
				{
				setState(149); match(LAST);
				setState(150); ((QueryTimeContext)_localctx).minutes = match(IntegerLiteral);
				setState(151);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUTES) | (1L << HOURS) | (1L << DAYS))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryParamsContext extends ParserRuleContext {
		public ParameterContext parameter;
		public List<ParameterContext> params = new ArrayList<ParameterContext>();
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public TerminalNode PARAMETERS() { return getToken(AQLParser.PARAMETERS, 0); }
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public QueryParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterQueryParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitQueryParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitQueryParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryParamsContext queryParams() throws RecognitionException {
		QueryParamsContext _localctx = new QueryParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_queryParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(PARAMETERS);
			setState(155); ((QueryParamsContext)_localctx).parameter = parameter();
			((QueryParamsContext)_localctx).params.add(((QueryParamsContext)_localctx).parameter);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(156); match(COMMA);
				setState(157); ((QueryParamsContext)_localctx).parameter = parameter();
				((QueryParamsContext)_localctx).params.add(((QueryParamsContext)_localctx).parameter);
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public Token id;
		public LiteralContext val;
		public TerminalNode Identifier() { return getToken(AQLParser.Identifier, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode EQ() { return getToken(AQLParser.EQ, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); ((ParameterContext)_localctx).id = match(Identifier);
			setState(164); match(EQ);
			setState(165); ((ParameterContext)_localctx).val = literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DatabaseContext extends ParserRuleContext {
		public Token name;
		public Token alias;
		public TerminalNode AS() { return getToken(AQLParser.AS, 0); }
		public TerminalNode Identifier(int i) {
			return getToken(AQLParser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(AQLParser.Identifier); }
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public DatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDatabase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDatabase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatabaseContext database() throws RecognitionException {
		DatabaseContext _localctx = new DatabaseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_database);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); ((DatabaseContext)_localctx).name = match(Identifier);
			setState(172);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AS) | (1L << Identifier) | (1L << StringLiteral))) != 0)) {
				{
				setState(169);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(168); match(AS);
					}
				}

				setState(171);
				((DatabaseContext)_localctx).alias = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Identifier || _la==StringLiteral) ) {
					((DatabaseContext)_localctx).alias = (Token)_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisplayListContext extends ParserRuleContext {
		public DisplayColumnContext displayColumn;
		public List<DisplayColumnContext> columns = new ArrayList<DisplayColumnContext>();
		public DisplayColumnContext displayColumn(int i) {
			return getRuleContext(DisplayColumnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public List<DisplayColumnContext> displayColumn() {
			return getRuleContexts(DisplayColumnContext.class);
		}
		public DisplayListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_displayList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDisplayList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDisplayList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDisplayList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DisplayListContext displayList() throws RecognitionException {
		DisplayListContext _localctx = new DisplayListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_displayList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); ((DisplayListContext)_localctx).displayColumn = displayColumn();
			((DisplayListContext)_localctx).columns.add(((DisplayListContext)_localctx).displayColumn);
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(175); match(COMMA);
				setState(176); ((DisplayListContext)_localctx).displayColumn = displayColumn();
				((DisplayListContext)_localctx).columns.add(((DisplayListContext)_localctx).displayColumn);
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisplayColumnContext extends ParserRuleContext {
		public DisplayColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_displayColumn; }
	 
		public DisplayColumnContext() { }
		public void copyFrom(DisplayColumnContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DisplayColumnExpressionContext extends DisplayColumnContext {
		public Token alias;
		public TerminalNode AS() { return getToken(AQLParser.AS, 0); }
		public TerminalNode Identifier() { return getToken(AQLParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public DisplayColumnExpressionContext(DisplayColumnContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDisplayColumnExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDisplayColumnExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDisplayColumnExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DisplayColumnAllContext extends DisplayColumnContext {
		public TerminalNode ASTERISK() { return getToken(AQLParser.ASTERISK, 0); }
		public DisplayColumnAllContext(DisplayColumnContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterDisplayColumnAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitDisplayColumnAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitDisplayColumnAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DisplayColumnContext displayColumn() throws RecognitionException {
		DisplayColumnContext _localctx = new DisplayColumnContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_displayColumn);
		int _la;
		try {
			setState(190);
			switch (_input.LA(1)) {
			case COUNT:
			case FIRST:
			case SUM:
			case AVG:
			case MIN:
			case MAX:
			case UCOUNT:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case Identifier:
			case NullLiteral:
			case BooleanLiteral:
			case StringLiteral:
			case Subtract:
			case LPAREN:
				_localctx = new DisplayColumnExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(182); expression(0);
				setState(187);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AS) | (1L << Identifier) | (1L << StringLiteral))) != 0)) {
					{
					setState(184);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(183); match(AS);
						}
					}

					setState(186);
					((DisplayColumnExpressionContext)_localctx).alias = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Identifier || _la==StringLiteral) ) {
						((DisplayColumnExpressionContext)_localctx).alias = (Token)_errHandler.recoverInline(this);
					}
					consume();
					}
				}

				}
				break;
			case ASTERISK:
				_localctx = new DisplayColumnAllContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(189); match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public Token fts;
		public TerminalNode WHERE() { return getToken(AQLParser.WHERE, 0); }
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public TerminalNode FTS() { return getToken(AQLParser.FTS, 0); }
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whereClause);
		try {
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(192); match(WHERE);
				setState(193); booleanExpression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194); match(WHERE);
				setState(195); match(FTS);
				setState(196); ((WhereClauseContext)_localctx).fts = match(StringLiteral);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(197); match(WHERE);
				setState(198); match(FTS);
				setState(199); ((WhereClauseContext)_localctx).fts = match(StringLiteral);
				setState(200); match(AND);
				setState(201); booleanExpression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderByContext extends ParserRuleContext {
		public Token order;
		public Token lang;
		public TerminalNode DESC() { return getToken(AQLParser.DESC, 0); }
		public TerminalNode COLLATE() { return getToken(AQLParser.COLLATE, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode ORDER_BY() { return getToken(AQLParser.ORDER_BY, 0); }
		public TerminalNode ASC() { return getToken(AQLParser.ASC, 0); }
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public OrderByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderBy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterOrderBy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitOrderBy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitOrderBy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByContext orderBy() throws RecognitionException {
		OrderByContext _localctx = new OrderByContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_orderBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); match(ORDER_BY);
			setState(205); expressionList();
			setState(207);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(206);
				((OrderByContext)_localctx).order = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
					((OrderByContext)_localctx).order = (Token)_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(211);
			_la = _input.LA(1);
			if (_la==COLLATE) {
				{
				setState(209); match(COLLATE);
				setState(210); ((OrderByContext)_localctx).lang = match(StringLiteral);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubqueryContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(AQLParser.SELECT, 0); }
		public SubqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterSubquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitSubquery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitSubquery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubqueryContext subquery() throws RecognitionException {
		SubqueryContext _localctx = new SubqueryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213); match(SELECT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupByContext extends ParserRuleContext {
		public TerminalNode HAVING() { return getToken(AQLParser.HAVING, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public TerminalNode GROUP_BY() { return getToken(AQLParser.GROUP_BY, 0); }
		public GroupByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterGroupBy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitGroupBy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitGroupBy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByContext groupBy() throws RecognitionException {
		GroupByContext _localctx = new GroupByContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_groupBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(GROUP_BY);
			setState(216); expressionList();
			setState(219);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(217); match(HAVING);
				setState(218); booleanExpression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanExpressionContext extends ParserRuleContext {
		public int _p;
		public BooleanExpressionContext left;
		public BooleanExpressionContext be;
		public Token bop;
		public BooleanExpressionContext right;
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public TerminalNode ALL() { return getToken(AQLParser.ALL, 0); }
		public BooleanOperatorContext booleanOperator() {
			return getRuleContext(BooleanOperatorContext.class,0);
		}
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(AQLParser.OR, 0); }
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public TerminalNode RPAREN() { return getToken(AQLParser.RPAREN, 0); }
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
		public ScalarFunctionContext scalarFunction() {
			return getRuleContext(ScalarFunctionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AQLParser.LPAREN, 0); }
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBooleanExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExpressionContext booleanExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, _parentState, _p);
		BooleanExpressionContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, RULE_booleanExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(223);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(222); match(NOT);
					}
				}

				setState(225); scalarFunction();
				}
				break;

			case 2:
				{
				setState(227);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(226); match(NOT);
					}
				}

				setState(229); match(LPAREN);
				setState(230); ((BooleanExpressionContext)_localctx).be = booleanExpression(0);
				setState(231); match(RPAREN);
				}
				break;

			case 3:
				{
				setState(234);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(233); match(ALL);
					}
				}

				setState(236); expression(0);
				setState(237); booleanOperator();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BooleanExpressionContext(_parentctx, _parentState, _p);
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
					setState(241);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(242);
					((BooleanExpressionContext)_localctx).bop = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
						((BooleanExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
					}
					consume();
					setState(243); ((BooleanExpressionContext)_localctx).right = booleanExpression(3);
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BooleanOperatorContext extends ParserRuleContext {
		public NullOperatorContext nullOperator() {
			return getRuleContext(NullOperatorContext.class,0);
		}
		public LikeOperatorContext likeOperator() {
			return getRuleContext(LikeOperatorContext.class,0);
		}
		public InOperatorContext inOperator() {
			return getRuleContext(InOperatorContext.class,0);
		}
		public BetweenOperatorContext betweenOperator() {
			return getRuleContext(BetweenOperatorContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public BooleanOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBooleanOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBooleanOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBooleanOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanOperatorContext booleanOperator() throws RecognitionException {
		BooleanOperatorContext _localctx = new BooleanOperatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_booleanOperator);
		try {
			setState(254);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); comparisonOperator();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(250); betweenOperator();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(251); likeOperator();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(252); nullOperator();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(253); inOperator();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public Token cop;
		public TerminalNode NEQ() { return getToken(AQLParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(AQLParser.LT, 0); }
		public TerminalNode LE() { return getToken(AQLParser.LE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode GT() { return getToken(AQLParser.GT, 0); }
		public TerminalNode GE() { return getToken(AQLParser.GE, 0); }
		public TerminalNode EQ() { return getToken(AQLParser.EQ, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			((ComparisonOperatorContext)_localctx).cop = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & ((1L << (EQ - 59)) | (1L << (NEQ - 59)) | (1L << (LT - 59)) | (1L << (LE - 59)) | (1L << (GT - 59)) | (1L << (GE - 59)))) != 0)) ) {
				((ComparisonOperatorContext)_localctx).cop = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(257); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BetweenOperatorContext extends ParserRuleContext {
		public ExpressionContext begin;
		public ExpressionContext end;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BETWEEN() { return getToken(AQLParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(AQLParser.AND, 0); }
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public BetweenOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_betweenOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterBetweenOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitBetweenOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitBetweenOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BetweenOperatorContext betweenOperator() throws RecognitionException {
		BetweenOperatorContext _localctx = new BetweenOperatorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_betweenOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(259); match(NOT);
				}
			}

			setState(262); match(BETWEEN);
			setState(263); ((BetweenOperatorContext)_localctx).begin = expression(0);
			setState(264); match(AND);
			setState(265); ((BetweenOperatorContext)_localctx).end = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LikeOperatorContext extends ParserRuleContext {
		public Token op;
		public Token pattern;
		public TerminalNode IREGEX() { return getToken(AQLParser.IREGEX, 0); }
		public TerminalNode ILIKE() { return getToken(AQLParser.ILIKE, 0); }
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public TerminalNode REGEX() { return getToken(AQLParser.REGEX, 0); }
		public TerminalNode LIKE() { return getToken(AQLParser.LIKE, 0); }
		public LikeOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_likeOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLikeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLikeOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLikeOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LikeOperatorContext likeOperator() throws RecognitionException {
		LikeOperatorContext _localctx = new LikeOperatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_likeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(267); match(NOT);
				}
			}

			setState(270);
			((LikeOperatorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LIKE) | (1L << ILIKE) | (1L << REGEX) | (1L << IREGEX))) != 0)) ) {
				((LikeOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(271); ((LikeOperatorContext)_localctx).pattern = match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NullOperatorContext extends ParserRuleContext {
		public TerminalNode NOTNULL() { return getToken(AQLParser.NOTNULL, 0); }
		public TerminalNode ISNULL() { return getToken(AQLParser.ISNULL, 0); }
		public NullOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterNullOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitNullOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitNullOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullOperatorContext nullOperator() throws RecognitionException {
		NullOperatorContext _localctx = new NullOperatorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_nullOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_la = _input.LA(1);
			if ( !(_la==ISNULL || _la==NOTNULL) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InOperatorContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(AQLParser.IN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ScalarSubqueryContext scalarSubquery() {
			return getRuleContext(ScalarSubqueryContext.class,0);
		}
		public TerminalNode NOT() { return getToken(AQLParser.NOT, 0); }
		public TerminalNode RPAREN() { return getToken(AQLParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(AQLParser.LPAREN, 0); }
		public InOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterInOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitInOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitInOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InOperatorContext inOperator() throws RecognitionException {
		InOperatorContext _localctx = new InOperatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_inOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(275); match(NOT);
				}
			}

			setState(278); match(IN);
			setState(279); match(LPAREN);
			setState(282);
			switch (_input.LA(1)) {
			case SELECT:
				{
				setState(280); scalarSubquery();
				}
				break;
			case COUNT:
			case FIRST:
			case SUM:
			case AVG:
			case MIN:
			case MAX:
			case UCOUNT:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case Identifier:
			case NullLiteral:
			case BooleanLiteral:
			case StringLiteral:
			case Subtract:
			case LPAREN:
				{
				setState(281); expressionList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(284); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> expr = new ArrayList<ExpressionContext>();
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); ((ExpressionListContext)_localctx).expression = expression(0);
			((ExpressionListContext)_localctx).expr.add(((ExpressionListContext)_localctx).expression);
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(287); match(COMMA);
				setState(288); ((ExpressionListContext)_localctx).expression = expression(0);
				((ExpressionListContext)_localctx).expr.add(((ExpressionListContext)_localctx).expression);
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public int _p;
		public TerminalNode RPAREN() { return getToken(AQLParser.RPAREN, 0); }
		public ScalarFunctionContext scalarFunction() {
			return getRuleContext(ScalarFunctionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArithmeticOperatorContext arithmeticOperator() {
			return getRuleContext(ArithmeticOperatorContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AQLParser.LPAREN, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState, _p);
		ExpressionContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(295); scalarFunction();
				}
				break;

			case 2:
				{
				setState(296); match(LPAREN);
				setState(297); expression(0);
				setState(298); arithmeticOperator();
				setState(299); match(RPAREN);
				}
				break;

			case 3:
				{
				setState(301); column();
				}
				break;

			case 4:
				{
				setState(302); literal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(309);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(305);
					if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
					setState(306); arithmeticOperator();
					}
					} 
				}
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArithmeticOperatorContext extends ParserRuleContext {
		public Token op;
		public TerminalNode ASTERISK() { return getToken(AQLParser.ASTERISK, 0); }
		public TerminalNode Subtract() { return getToken(AQLParser.Subtract, 0); }
		public TerminalNode Divide() { return getToken(AQLParser.Divide, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Modulo() { return getToken(AQLParser.Modulo, 0); }
		public TerminalNode Add() { return getToken(AQLParser.Add, 0); }
		public TerminalNode POWER_OP() { return getToken(AQLParser.POWER_OP, 0); }
		public ArithmeticOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterArithmeticOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitArithmeticOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitArithmeticOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticOperatorContext arithmeticOperator() throws RecognitionException {
		ArithmeticOperatorContext _localctx = new ArithmeticOperatorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arithmeticOperator);
		try {
			setState(324);
			switch (_input.LA(1)) {
			case POWER_OP:
				enterOuterAlt(_localctx, 1);
				{
				setState(312); ((ArithmeticOperatorContext)_localctx).op = match(POWER_OP);
				setState(313); expression(0);
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(314); ((ArithmeticOperatorContext)_localctx).op = match(ASTERISK);
				setState(315); expression(0);
				}
				break;
			case Divide:
				enterOuterAlt(_localctx, 3);
				{
				setState(316); ((ArithmeticOperatorContext)_localctx).op = match(Divide);
				setState(317); expression(0);
				}
				break;
			case Modulo:
				enterOuterAlt(_localctx, 4);
				{
				setState(318); ((ArithmeticOperatorContext)_localctx).op = match(Modulo);
				setState(319); expression(0);
				}
				break;
			case Add:
				enterOuterAlt(_localctx, 5);
				{
				setState(320); ((ArithmeticOperatorContext)_localctx).op = match(Add);
				setState(321); expression(0);
				}
				break;
			case Subtract:
				enterOuterAlt(_localctx, 6);
				{
				setState(322); ((ArithmeticOperatorContext)_localctx).op = match(Subtract);
				setState(323); expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public Token db;
		public Token name;
		public TerminalNode DOT() { return getToken(AQLParser.DOT, 0); }
		public TerminalNode Identifier(int i) {
			return getToken(AQLParser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(AQLParser.Identifier); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(326); ((ColumnContext)_localctx).db = match(Identifier);
				setState(327); match(DOT);
				}
				break;
			}
			setState(330); ((ColumnContext)_localctx).name = match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarFunctionContext extends ParserRuleContext {
		public Token db;
		public Token name;
		public Token agg;
		public TerminalNode UCOUNT() { return getToken(AQLParser.UCOUNT, 0); }
		public TerminalNode FIRST() { return getToken(AQLParser.FIRST, 0); }
		public TerminalNode SUM() { return getToken(AQLParser.SUM, 0); }
		public List<TerminalNode> Identifier() { return getTokens(AQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(AQLParser.Identifier, i);
		}
		public TerminalNode AVG() { return getToken(AQLParser.AVG, 0); }
		public TerminalNode MIN() { return getToken(AQLParser.MIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MAX() { return getToken(AQLParser.MAX, 0); }
		public TerminalNode LPAREN() { return getToken(AQLParser.LPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode COUNT() { return getToken(AQLParser.COUNT, 0); }
		public TerminalNode ASTERISK() { return getToken(AQLParser.ASTERISK, 0); }
		public TerminalNode DOT() { return getToken(AQLParser.DOT, 0); }
		public TerminalNode RPAREN() { return getToken(AQLParser.RPAREN, 0); }
		public ScalarFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterScalarFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitScalarFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitScalarFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarFunctionContext scalarFunction() throws RecognitionException {
		ScalarFunctionContext _localctx = new ScalarFunctionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_scalarFunction);
		int _la;
		try {
			setState(356);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(332); ((ScalarFunctionContext)_localctx).db = match(Identifier);
					setState(333); match(DOT);
					}
					break;
				}
				setState(336); ((ScalarFunctionContext)_localctx).name = match(Identifier);
				setState(337); match(LPAREN);
				setState(339);
				_la = _input.LA(1);
				if (((((_la - 31)) & ~0x3f) == 0 && ((1L << (_la - 31)) & ((1L << (COUNT - 31)) | (1L << (FIRST - 31)) | (1L << (SUM - 31)) | (1L << (AVG - 31)) | (1L << (MIN - 31)) | (1L << (MAX - 31)) | (1L << (UCOUNT - 31)) | (1L << (IntegerLiteral - 31)) | (1L << (FloatingPointLiteral - 31)) | (1L << (Identifier - 31)) | (1L << (NullLiteral - 31)) | (1L << (BooleanLiteral - 31)) | (1L << (StringLiteral - 31)) | (1L << (Subtract - 31)) | (1L << (LPAREN - 31)))) != 0)) {
					{
					setState(338); arguments();
					}
				}

				setState(341); match(RPAREN);
				}
				break;
			case COUNT:
				enterOuterAlt(_localctx, 2);
				{
				setState(342); ((ScalarFunctionContext)_localctx).agg = match(COUNT);
				setState(343); match(LPAREN);
				setState(348);
				switch (_input.LA(1)) {
				case ASTERISK:
				case RPAREN:
					{
					setState(345);
					_la = _input.LA(1);
					if (_la==ASTERISK) {
						{
						setState(344); match(ASTERISK);
						}
					}

					}
					break;
				case COUNT:
				case FIRST:
				case SUM:
				case AVG:
				case MIN:
				case MAX:
				case UCOUNT:
				case IntegerLiteral:
				case FloatingPointLiteral:
				case Identifier:
				case NullLiteral:
				case BooleanLiteral:
				case StringLiteral:
				case Subtract:
				case LPAREN:
					{
					setState(347); expression(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(350); match(RPAREN);
				}
				break;
			case FIRST:
			case SUM:
			case AVG:
			case MIN:
			case MAX:
			case UCOUNT:
				enterOuterAlt(_localctx, 3);
				{
				setState(351);
				((ScalarFunctionContext)_localctx).agg = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FIRST) | (1L << SUM) | (1L << AVG) | (1L << MIN) | (1L << MAX) | (1L << UCOUNT))) != 0)) ) {
					((ScalarFunctionContext)_localctx).agg = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(352); match(LPAREN);
				setState(353); expression(0);
				setState(354); match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358); ((ArgumentsContext)_localctx).expression = expression(0);
			((ArgumentsContext)_localctx).args.add(((ArgumentsContext)_localctx).expression);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(359); match(COMMA);
				setState(360); ((ArgumentsContext)_localctx).expression = expression(0);
				((ArgumentsContext)_localctx).args.add(((ArgumentsContext)_localctx).expression);
				}
				}
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateFunctionContext extends ParserRuleContext {
		public Token agg;
		public TerminalNode UCOUNT() { return getToken(AQLParser.UCOUNT, 0); }
		public TerminalNode COUNT() { return getToken(AQLParser.COUNT, 0); }
		public TerminalNode ASTERISK() { return getToken(AQLParser.ASTERISK, 0); }
		public TerminalNode SUM() { return getToken(AQLParser.SUM, 0); }
		public TerminalNode FIRST() { return getToken(AQLParser.FIRST, 0); }
		public TerminalNode RPAREN() { return getToken(AQLParser.RPAREN, 0); }
		public TerminalNode AVG() { return getToken(AQLParser.AVG, 0); }
		public TerminalNode MIN() { return getToken(AQLParser.MIN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MAX() { return getToken(AQLParser.MAX, 0); }
		public TerminalNode LPAREN() { return getToken(AQLParser.LPAREN, 0); }
		public AggregateFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterAggregateFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitAggregateFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitAggregateFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateFunctionContext aggregateFunction() throws RecognitionException {
		AggregateFunctionContext _localctx = new AggregateFunctionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_aggregateFunction);
		int _la;
		try {
			setState(380);
			switch (_input.LA(1)) {
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(366); ((AggregateFunctionContext)_localctx).agg = match(COUNT);
				setState(367); match(LPAREN);
				setState(372);
				switch (_input.LA(1)) {
				case ASTERISK:
				case RPAREN:
					{
					setState(369);
					_la = _input.LA(1);
					if (_la==ASTERISK) {
						{
						setState(368); match(ASTERISK);
						}
					}

					}
					break;
				case COUNT:
				case FIRST:
				case SUM:
				case AVG:
				case MIN:
				case MAX:
				case UCOUNT:
				case IntegerLiteral:
				case FloatingPointLiteral:
				case Identifier:
				case NullLiteral:
				case BooleanLiteral:
				case StringLiteral:
				case Subtract:
				case LPAREN:
					{
					setState(371); expression(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(374); match(RPAREN);
				}
				break;
			case FIRST:
			case SUM:
			case AVG:
			case MIN:
			case MAX:
			case UCOUNT:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				((AggregateFunctionContext)_localctx).agg = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FIRST) | (1L << SUM) | (1L << AVG) | (1L << MIN) | (1L << MAX) | (1L << UCOUNT))) != 0)) ) {
					((AggregateFunctionContext)_localctx).agg = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(376); match(LPAREN);
				setState(377); expression(0);
				setState(378); match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LiteralIntegerContext extends LiteralContext {
		public TerminalNode Subtract() { return getToken(AQLParser.Subtract, 0); }
		public TerminalNode IntegerLiteral() { return getToken(AQLParser.IntegerLiteral, 0); }
		public LiteralIntegerContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralFloatContext extends LiteralContext {
		public TerminalNode Subtract() { return getToken(AQLParser.Subtract, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(AQLParser.FloatingPointLiteral, 0); }
		public LiteralFloatContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralNullContext extends LiteralContext {
		public TerminalNode NullLiteral() { return getToken(AQLParser.NullLiteral, 0); }
		public LiteralNullContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralStringContext extends LiteralContext {
		public TerminalNode StringLiteral() { return getToken(AQLParser.StringLiteral, 0); }
		public LiteralStringContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralBooleanContext extends LiteralContext {
		public TerminalNode BooleanLiteral() { return getToken(AQLParser.BooleanLiteral, 0); }
		public LiteralBooleanContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).enterLiteralBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLListener ) ((AQLListener)listener).exitLiteralBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLVisitor ) return ((AQLVisitor<? extends T>)visitor).visitLiteralBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_literal);
		int _la;
		try {
			setState(393);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				_localctx = new LiteralIntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				_la = _input.LA(1);
				if (_la==Subtract) {
					{
					setState(382); match(Subtract);
					}
				}

				setState(385); match(IntegerLiteral);
				}
				break;

			case 2:
				_localctx = new LiteralFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				_la = _input.LA(1);
				if (_la==Subtract) {
					{
					setState(386); match(Subtract);
					}
				}

				setState(389); match(FloatingPointLiteral);
				}
				break;

			case 3:
				_localctx = new LiteralStringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(390); match(StringLiteral);
				}
				break;

			case 4:
				_localctx = new LiteralBooleanContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(391); match(BooleanLiteral);
				}
				break;

			case 5:
				_localctx = new LiteralNullContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(392); match(NullLiteral);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15: return booleanExpression_sempred((BooleanExpressionContext)_localctx, predIndex);

		case 23: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 3 >= _localctx._p;
		}
		return true;
	}
	private boolean booleanExpression_sempred(BooleanExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3U\u018e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\5\2D\n\2\3\2\5\2G\n\2\3\2\5\2J\n\2\3\3\5\3M\n\3\3\3\3\3\3\4"+
		"\3\4\5\4S\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5[\n\5\f\5\16\5^\13\5\3\5\3\5"+
		"\5\5b\n\5\3\5\5\5e\n\5\3\5\5\5h\n\5\3\5\5\5k\n\5\3\5\3\5\5\5o\n\5\3\5"+
		"\5\5r\n\5\3\5\5\5u\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6}\n\6\f\6\16\6\u0080"+
		"\13\6\3\6\5\6\u0083\n\6\3\6\5\6\u0086\n\6\3\6\3\6\5\6\u008a\n\6\3\6\5"+
		"\6\u008d\n\6\3\6\5\6\u0090\n\6\3\7\3\7\3\7\3\7\5\7\u0096\n\7\3\7\3\7\3"+
		"\7\5\7\u009b\n\7\3\b\3\b\3\b\3\b\7\b\u00a1\n\b\f\b\16\b\u00a4\13\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\5\n\u00ac\n\n\3\n\5\n\u00af\n\n\3\13\3\13\3\13\7"+
		"\13\u00b4\n\13\f\13\16\13\u00b7\13\13\3\f\3\f\5\f\u00bb\n\f\3\f\5\f\u00be"+
		"\n\f\3\f\5\f\u00c1\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00cd"+
		"\n\r\3\16\3\16\3\16\5\16\u00d2\n\16\3\16\3\16\5\16\u00d6\n\16\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\5\20\u00de\n\20\3\21\3\21\5\21\u00e2\n\21\3\21\3"+
		"\21\5\21\u00e6\n\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ed\n\21\3\21\3\21"+
		"\3\21\5\21\u00f2\n\21\3\21\3\21\3\21\7\21\u00f7\n\21\f\21\16\21\u00fa"+
		"\13\21\3\22\3\22\3\22\3\22\3\22\5\22\u0101\n\22\3\23\3\23\3\23\3\24\5"+
		"\24\u0107\n\24\3\24\3\24\3\24\3\24\3\24\3\25\5\25\u010f\n\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\27\5\27\u0117\n\27\3\27\3\27\3\27\3\27\5\27\u011d\n"+
		"\27\3\27\3\27\3\30\3\30\3\30\7\30\u0124\n\30\f\30\16\30\u0127\13\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0132\n\31\3\31\3\31"+
		"\7\31\u0136\n\31\f\31\16\31\u0139\13\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0147\n\32\3\33\3\33\5\33\u014b\n"+
		"\33\3\33\3\33\3\34\3\34\5\34\u0151\n\34\3\34\3\34\3\34\5\34\u0156\n\34"+
		"\3\34\3\34\3\34\3\34\5\34\u015c\n\34\3\34\5\34\u015f\n\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\5\34\u0167\n\34\3\35\3\35\3\35\7\35\u016c\n\35\f\35"+
		"\16\35\u016f\13\35\3\36\3\36\3\36\5\36\u0174\n\36\3\36\5\36\u0177\n\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u017f\n\36\3\37\5\37\u0182\n\37\3"+
		"\37\3\37\5\37\u0186\n\37\3\37\3\37\3\37\3\37\5\37\u018c\n\37\3\37\2 \2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\n\3\2"+
		"\33\35\4\2\61\61\65\65\3\2\23\24\3\2\5\6\3\2=B\3\2\t\f\3\2\3\4\3\2\"\'"+
		"\u01b9\2F\3\2\2\2\4L\3\2\2\2\6P\3\2\2\2\bT\3\2\2\2\nv\3\2\2\2\f\u009a"+
		"\3\2\2\2\16\u009c\3\2\2\2\20\u00a5\3\2\2\2\22\u00a9\3\2\2\2\24\u00b0\3"+
		"\2\2\2\26\u00c0\3\2\2\2\30\u00cc\3\2\2\2\32\u00ce\3\2\2\2\34\u00d7\3\2"+
		"\2\2\36\u00d9\3\2\2\2 \u00f1\3\2\2\2\"\u0100\3\2\2\2$\u0102\3\2\2\2&\u0106"+
		"\3\2\2\2(\u010e\3\2\2\2*\u0113\3\2\2\2,\u0116\3\2\2\2.\u0120\3\2\2\2\60"+
		"\u0131\3\2\2\2\62\u0146\3\2\2\2\64\u014a\3\2\2\2\66\u0166\3\2\2\28\u0168"+
		"\3\2\2\2:\u017e\3\2\2\2<\u018b\3\2\2\2>?\7 \2\2?G\7\65\2\2@A\7 \2\2AB"+
		"\7\65\2\2BD\7\5\2\2C@\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\5 \21\2F>\3\2\2\2"+
		"FC\3\2\2\2GI\3\2\2\2HJ\7D\2\2IH\3\2\2\2IJ\3\2\2\2J\3\3\2\2\2KM\7\5\2\2"+
		"LK\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\5 \21\2O\5\3\2\2\2PR\5\b\5\2QS\7D\2\2"+
		"RQ\3\2\2\2RS\3\2\2\2S\7\3\2\2\2TU\7(\2\2UV\5\24\13\2VW\7\21\2\2W\\\5\22"+
		"\n\2XY\7G\2\2Y[\5\22\n\2ZX\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]a\3"+
		"\2\2\2^\\\3\2\2\2_`\7\26\2\2`b\7\61\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2"+
		"ce\5\30\r\2dc\3\2\2\2de\3\2\2\2eg\3\2\2\2fh\5\36\20\2gf\3\2\2\2gh\3\2"+
		"\2\2hj\3\2\2\2ik\5\32\16\2ji\3\2\2\2jk\3\2\2\2kn\3\2\2\2lm\7\r\2\2mo\7"+
		"/\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pr\5\f\7\2qp\3\2\2\2qr\3\2\2\2rt\3"+
		"\2\2\2su\5\16\b\2ts\3\2\2\2tu\3\2\2\2u\t\3\2\2\2vw\7(\2\2wx\5\26\f\2x"+
		"y\7\21\2\2y~\5\22\n\2z{\7G\2\2{}\5\22\n\2|z\3\2\2\2}\u0080\3\2\2\2~|\3"+
		"\2\2\2~\177\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0081\u0083\5\30"+
		"\r\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084"+
		"\u0086\5\36\20\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0089\3"+
		"\2\2\2\u0087\u0088\7\r\2\2\u0088\u008a\7/\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u008d\5\f\7\2\u008c\u008b\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u0090\5\16\b\2\u008f"+
		"\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\13\3\2\2\2\u0091\u0092\7\30\2"+
		"\2\u0092\u0095\7\65\2\2\u0093\u0094\7\31\2\2\u0094\u0096\7\65\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u009b\3\2\2\2\u0097\u0098\7\32"+
		"\2\2\u0098\u0099\7/\2\2\u0099\u009b\t\2\2\2\u009a\u0091\3\2\2\2\u009a"+
		"\u0097\3\2\2\2\u009b\r\3\2\2\2\u009c\u009d\7-\2\2\u009d\u00a2\5\20\t\2"+
		"\u009e\u009f\7G\2\2\u009f\u00a1\5\20\t\2\u00a0\u009e\3\2\2\2\u00a1\u00a4"+
		"\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\17\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a6\7\61\2\2\u00a6\u00a7\7=\2\2\u00a7\u00a8\5<"+
		"\37\2\u00a8\21\3\2\2\2\u00a9\u00ae\7\61\2\2\u00aa\u00ac\7\37\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\t\3"+
		"\2\2\u00ae\u00ab\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\23\3\2\2\2\u00b0\u00b5"+
		"\5\26\f\2\u00b1\u00b2\7G\2\2\u00b2\u00b4\5\26\f\2\u00b3\u00b1\3\2\2\2"+
		"\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\25"+
		"\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00bd\5\60\31\2\u00b9\u00bb\7\37\2"+
		"\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be"+
		"\t\3\2\2\u00bd\u00ba\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00c1\7\66\2\2\u00c0\u00b8\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\27\3\2\2"+
		"\2\u00c2\u00c3\7\22\2\2\u00c3\u00cd\5 \21\2\u00c4\u00c5\7\22\2\2\u00c5"+
		"\u00c6\7 \2\2\u00c6\u00cd\7\65\2\2\u00c7\u00c8\7\22\2\2\u00c8\u00c9\7"+
		" \2\2\u00c9\u00ca\7\65\2\2\u00ca\u00cb\7\5\2\2\u00cb\u00cd\5 \21\2\u00cc"+
		"\u00c2\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cd\31\3\2\2"+
		"\2\u00ce\u00cf\7\20\2\2\u00cf\u00d1\5.\30\2\u00d0\u00d2\t\4\2\2\u00d1"+
		"\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d4\7\25"+
		"\2\2\u00d4\u00d6\7\65\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\33\3\2\2\2\u00d7\u00d8\7(\2\2\u00d8\35\3\2\2\2\u00d9\u00da\7\16\2\2\u00da"+
		"\u00dd\5.\30\2\u00db\u00dc\7\17\2\2\u00dc\u00de\5 \21\2\u00dd\u00db\3"+
		"\2\2\2\u00dd\u00de\3\2\2\2\u00de\37\3\2\2\2\u00df\u00e1\b\21\1\2\u00e0"+
		"\u00e2\7\7\2\2\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00f2\5\66\34\2\u00e4\u00e6\7\7\2\2\u00e5\u00e4\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7I\2\2\u00e8\u00e9\5 \21"+
		"\2\u00e9\u00ea\7H\2\2\u00ea\u00f2\3\2\2\2\u00eb\u00ed\7\36\2\2\u00ec\u00eb"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\5\60\31\2"+
		"\u00ef\u00f0\5\"\22\2\u00f0\u00f2\3\2\2\2\u00f1\u00df\3\2\2\2\u00f1\u00e5"+
		"\3\2\2\2\u00f1\u00ec\3\2\2\2\u00f2\u00f8\3\2\2\2\u00f3\u00f4\6\21\2\3"+
		"\u00f4\u00f5\t\5\2\2\u00f5\u00f7\5 \21\2\u00f6\u00f3\3\2\2\2\u00f7\u00fa"+
		"\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9!\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fb\u0101\5$\23\2\u00fc\u0101\5&\24\2\u00fd\u0101\5("+
		"\25\2\u00fe\u0101\5*\26\2\u00ff\u0101\5,\27\2\u0100\u00fb\3\2\2\2\u0100"+
		"\u00fc\3\2\2\2\u0100\u00fd\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2"+
		"\2\2\u0101#\3\2\2\2\u0102\u0103\t\6\2\2\u0103\u0104\5\60\31\2\u0104%\3"+
		"\2\2\2\u0105\u0107\7\7\2\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0109\7\b\2\2\u0109\u010a\5\60\31\2\u010a\u010b\7"+
		"\5\2\2\u010b\u010c\5\60\31\2\u010c\'\3\2\2\2\u010d\u010f\7\7\2\2\u010e"+
		"\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\t\7"+
		"\2\2\u0111\u0112\7\65\2\2\u0112)\3\2\2\2\u0113\u0114\t\b\2\2\u0114+\3"+
		"\2\2\2\u0115\u0117\7\7\2\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0119\7\27\2\2\u0119\u011c\7I\2\2\u011a\u011d\5\n"+
		"\6\2\u011b\u011d\5.\30\2\u011c\u011a\3\2\2\2\u011c\u011b\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u011f\7H\2\2\u011f-\3\2\2\2\u0120\u0125\5\60\31\2"+
		"\u0121\u0122\7G\2\2\u0122\u0124\5\60\31\2\u0123\u0121\3\2\2\2\u0124\u0127"+
		"\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126/\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0128\u0129\b\31\1\2\u0129\u0132\5\66\34\2\u012a\u012b"+
		"\7I\2\2\u012b\u012c\5\60\31\2\u012c\u012d\5\62\32\2\u012d\u012e\7H\2\2"+
		"\u012e\u0132\3\2\2\2\u012f\u0132\5\64\33\2\u0130\u0132\5<\37\2\u0131\u0128"+
		"\3\2\2\2\u0131\u012a\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132"+
		"\u0137\3\2\2\2\u0133\u0134\6\31\3\3\u0134\u0136\5\62\32\2\u0135\u0133"+
		"\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\61\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013b\7Q\2\2\u013b\u0147\5\60\31"+
		"\2\u013c\u013d\7\66\2\2\u013d\u0147\5\60\31\2\u013e\u013f\7:\2\2\u013f"+
		"\u0147\5\60\31\2\u0140\u0141\7;\2\2\u0141\u0147\5\60\31\2\u0142\u0143"+
		"\7\67\2\2\u0143\u0147\5\60\31\2\u0144\u0145\78\2\2\u0145\u0147\5\60\31"+
		"\2\u0146\u013a\3\2\2\2\u0146\u013c\3\2\2\2\u0146\u013e\3\2\2\2\u0146\u0140"+
		"\3\2\2\2\u0146\u0142\3\2\2\2\u0146\u0144\3\2\2\2\u0147\63\3\2\2\2\u0148"+
		"\u0149\7\61\2\2\u0149\u014b\7F\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2"+
		"\2\2\u014b\u014c\3\2\2\2\u014c\u014d\7\61\2\2\u014d\65\3\2\2\2\u014e\u014f"+
		"\7\61\2\2\u014f\u0151\7F\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152\u0153\7\61\2\2\u0153\u0155\7I\2\2\u0154\u0156\58"+
		"\35\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0167\7H\2\2\u0158\u0159\7!\2\2\u0159\u015e\7I\2\2\u015a\u015c\7\66\2"+
		"\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015f\3\2\2\2\u015d\u015f"+
		"\5\60\31\2\u015e\u015b\3\2\2\2\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2"+
		"\u0160\u0167\7H\2\2\u0161\u0162\t\t\2\2\u0162\u0163\7I\2\2\u0163\u0164"+
		"\5\60\31\2\u0164\u0165\7H\2\2\u0165\u0167\3\2\2\2\u0166\u0150\3\2\2\2"+
		"\u0166\u0158\3\2\2\2\u0166\u0161\3\2\2\2\u0167\67\3\2\2\2\u0168\u016d"+
		"\5\60\31\2\u0169\u016a\7G\2\2\u016a\u016c\5\60\31\2\u016b\u0169\3\2\2"+
		"\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e9"+
		"\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0171\7!\2\2\u0171\u0176\7I\2\2\u0172"+
		"\u0174\7\66\2\2\u0173\u0172\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0177\3"+
		"\2\2\2\u0175\u0177\5\60\31\2\u0176\u0173\3\2\2\2\u0176\u0175\3\2\2\2\u0177"+
		"\u0178\3\2\2\2\u0178\u017f\7H\2\2\u0179\u017a\t\t\2\2\u017a\u017b\7I\2"+
		"\2\u017b\u017c\5\60\31\2\u017c\u017d\7H\2\2\u017d\u017f\3\2\2\2\u017e"+
		"\u0170\3\2\2\2\u017e\u0179\3\2\2\2\u017f;\3\2\2\2\u0180\u0182\78\2\2\u0181"+
		"\u0180\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u018c\7/"+
		"\2\2\u0184\u0186\78\2\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u018c\7\60\2\2\u0188\u018c\7\65\2\2\u0189\u018c\7"+
		"\64\2\2\u018a\u018c\7\63\2\2\u018b\u0181\3\2\2\2\u018b\u0185\3\2\2\2\u018b"+
		"\u0188\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018a\3\2\2\2\u018c=\3\2\2\2"+
		"=CFILR\\adgjnqt~\u0082\u0085\u0089\u008c\u008f\u0095\u009a\u00a2\u00ab"+
		"\u00ae\u00b5\u00ba\u00bd\u00c0\u00cc\u00d1\u00d5\u00dd\u00e1\u00e5\u00ec"+
		"\u00f1\u00f8\u0100\u0106\u010e\u0116\u011c\u0125\u0131\u0137\u0146\u014a"+
		"\u0150\u0155\u015b\u015e\u0166\u016d\u0173\u0176\u017e\u0181\u0185\u018b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}