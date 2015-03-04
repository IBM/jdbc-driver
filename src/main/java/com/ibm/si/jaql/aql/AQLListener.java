// Generated from AQL.g4 by ANTLR 4.1
package com.ibm.si.jaql.aql;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AQLParser}.
 */
public interface AQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull AQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull AQLParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(@NotNull AQLParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(@NotNull AQLParser.WhereClauseContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#scalarSubquery}.
	 * @param ctx the parse tree
	 */
	void enterScalarSubquery(@NotNull AQLParser.ScalarSubqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#scalarSubquery}.
	 * @param ctx the parse tree
	 */
	void exitScalarSubquery(@NotNull AQLParser.ScalarSubqueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void enterOrderBy(@NotNull AQLParser.OrderByContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void exitOrderBy(@NotNull AQLParser.OrderByContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull AQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull AQLParser.QueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(@NotNull AQLParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(@NotNull AQLParser.ExpressionListContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#displayList}.
	 * @param ctx the parse tree
	 */
	void enterDisplayList(@NotNull AQLParser.DisplayListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#displayList}.
	 * @param ctx the parse tree
	 */
	void exitDisplayList(@NotNull AQLParser.DisplayListContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#likeOperator}.
	 * @param ctx the parse tree
	 */
	void enterLikeOperator(@NotNull AQLParser.LikeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#likeOperator}.
	 * @param ctx the parse tree
	 */
	void exitLikeOperator(@NotNull AQLParser.LikeOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator(@NotNull AQLParser.ArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator(@NotNull AQLParser.ArithmeticOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(@NotNull AQLParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(@NotNull AQLParser.BooleanOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#aggregateFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateFunction(@NotNull AQLParser.AggregateFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#aggregateFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateFunction(@NotNull AQLParser.AggregateFunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull AQLParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull AQLParser.ParameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(@NotNull AQLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(@NotNull AQLParser.ComparisonOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#LiteralBoolean}.
	 * @param ctx the parse tree
	 */
	void enterLiteralBoolean(@NotNull AQLParser.LiteralBooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#LiteralBoolean}.
	 * @param ctx the parse tree
	 */
	void exitLiteralBoolean(@NotNull AQLParser.LiteralBooleanContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#scalarFunction}.
	 * @param ctx the parse tree
	 */
	void enterScalarFunction(@NotNull AQLParser.ScalarFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#scalarFunction}.
	 * @param ctx the parse tree
	 */
	void exitScalarFunction(@NotNull AQLParser.ScalarFunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#DisplayColumnAll}.
	 * @param ctx the parse tree
	 */
	void enterDisplayColumnAll(@NotNull AQLParser.DisplayColumnAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#DisplayColumnAll}.
	 * @param ctx the parse tree
	 */
	void exitDisplayColumnAll(@NotNull AQLParser.DisplayColumnAllContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#inOperator}.
	 * @param ctx the parse tree
	 */
	void enterInOperator(@NotNull AQLParser.InOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#inOperator}.
	 * @param ctx the parse tree
	 */
	void exitInOperator(@NotNull AQLParser.InOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#LiteralInteger}.
	 * @param ctx the parse tree
	 */
	void enterLiteralInteger(@NotNull AQLParser.LiteralIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#LiteralInteger}.
	 * @param ctx the parse tree
	 */
	void exitLiteralInteger(@NotNull AQLParser.LiteralIntegerContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#queryParams}.
	 * @param ctx the parse tree
	 */
	void enterQueryParams(@NotNull AQLParser.QueryParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#queryParams}.
	 * @param ctx the parse tree
	 */
	void exitQueryParams(@NotNull AQLParser.QueryParamsContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#criteria}.
	 * @param ctx the parse tree
	 */
	void enterCriteria(@NotNull AQLParser.CriteriaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#criteria}.
	 * @param ctx the parse tree
	 */
	void exitCriteria(@NotNull AQLParser.CriteriaContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#LiteralNull}.
	 * @param ctx the parse tree
	 */
	void enterLiteralNull(@NotNull AQLParser.LiteralNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#LiteralNull}.
	 * @param ctx the parse tree
	 */
	void exitLiteralNull(@NotNull AQLParser.LiteralNullContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#DisplayColumnExpression}.
	 * @param ctx the parse tree
	 */
	void enterDisplayColumnExpression(@NotNull AQLParser.DisplayColumnExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#DisplayColumnExpression}.
	 * @param ctx the parse tree
	 */
	void exitDisplayColumnExpression(@NotNull AQLParser.DisplayColumnExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#queryTime}.
	 * @param ctx the parse tree
	 */
	void enterQueryTime(@NotNull AQLParser.QueryTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#queryTime}.
	 * @param ctx the parse tree
	 */
	void exitQueryTime(@NotNull AQLParser.QueryTimeContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#database}.
	 * @param ctx the parse tree
	 */
	void enterDatabase(@NotNull AQLParser.DatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#database}.
	 * @param ctx the parse tree
	 */
	void exitDatabase(@NotNull AQLParser.DatabaseContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void enterSubquery(@NotNull AQLParser.SubqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void exitSubquery(@NotNull AQLParser.SubqueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#betweenOperator}.
	 * @param ctx the parse tree
	 */
	void enterBetweenOperator(@NotNull AQLParser.BetweenOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#betweenOperator}.
	 * @param ctx the parse tree
	 */
	void exitBetweenOperator(@NotNull AQLParser.BetweenOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull AQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull AQLParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void enterGroupBy(@NotNull AQLParser.GroupByContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void exitGroupBy(@NotNull AQLParser.GroupByContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull AQLParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull AQLParser.ArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#nullOperator}.
	 * @param ctx the parse tree
	 */
	void enterNullOperator(@NotNull AQLParser.NullOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#nullOperator}.
	 * @param ctx the parse tree
	 */
	void exitNullOperator(@NotNull AQLParser.NullOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(@NotNull AQLParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(@NotNull AQLParser.ColumnContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#LiteralFloat}.
	 * @param ctx the parse tree
	 */
	void enterLiteralFloat(@NotNull AQLParser.LiteralFloatContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#LiteralFloat}.
	 * @param ctx the parse tree
	 */
	void exitLiteralFloat(@NotNull AQLParser.LiteralFloatContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(@NotNull AQLParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(@NotNull AQLParser.BooleanExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#LiteralString}.
	 * @param ctx the parse tree
	 */
	void enterLiteralString(@NotNull AQLParser.LiteralStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#LiteralString}.
	 * @param ctx the parse tree
	 */
	void exitLiteralString(@NotNull AQLParser.LiteralStringContext ctx);

	/**
	 * Enter a parse tree produced by {@link AQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(@NotNull AQLParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(@NotNull AQLParser.FilterContext ctx);
}