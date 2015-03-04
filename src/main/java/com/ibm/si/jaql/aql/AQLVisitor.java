// Generated from AQL.g4 by ANTLR 4.1
package com.ibm.si.jaql.aql;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull AQLParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(@NotNull AQLParser.WhereClauseContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#scalarSubquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarSubquery(@NotNull AQLParser.ScalarSubqueryContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#orderBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderBy(@NotNull AQLParser.OrderByContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(@NotNull AQLParser.QueryContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(@NotNull AQLParser.ExpressionListContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#displayList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplayList(@NotNull AQLParser.DisplayListContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#likeOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeOperator(@NotNull AQLParser.LikeOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#arithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator(@NotNull AQLParser.ArithmeticOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#booleanOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOperator(@NotNull AQLParser.BooleanOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#aggregateFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateFunction(@NotNull AQLParser.AggregateFunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull AQLParser.ParameterContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(@NotNull AQLParser.ComparisonOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#LiteralBoolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralBoolean(@NotNull AQLParser.LiteralBooleanContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#scalarFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarFunction(@NotNull AQLParser.ScalarFunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#DisplayColumnAll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplayColumnAll(@NotNull AQLParser.DisplayColumnAllContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#inOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInOperator(@NotNull AQLParser.InOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#LiteralInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralInteger(@NotNull AQLParser.LiteralIntegerContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#queryParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryParams(@NotNull AQLParser.QueryParamsContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#criteria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCriteria(@NotNull AQLParser.CriteriaContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#LiteralNull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralNull(@NotNull AQLParser.LiteralNullContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#DisplayColumnExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplayColumnExpression(@NotNull AQLParser.DisplayColumnExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#queryTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryTime(@NotNull AQLParser.QueryTimeContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabase(@NotNull AQLParser.DatabaseContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#subquery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery(@NotNull AQLParser.SubqueryContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#betweenOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenOperator(@NotNull AQLParser.BetweenOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull AQLParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#groupBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupBy(@NotNull AQLParser.GroupByContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull AQLParser.ArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#nullOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullOperator(@NotNull AQLParser.NullOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn(@NotNull AQLParser.ColumnContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#LiteralFloat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralFloat(@NotNull AQLParser.LiteralFloatContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(@NotNull AQLParser.BooleanExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#LiteralString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralString(@NotNull AQLParser.LiteralStringContext ctx);

	/**
	 * Visit a parse tree produced by {@link AQLParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(@NotNull AQLParser.FilterContext ctx);
}