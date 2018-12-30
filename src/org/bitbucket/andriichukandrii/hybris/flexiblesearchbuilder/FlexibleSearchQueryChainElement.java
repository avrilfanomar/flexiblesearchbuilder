package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Represents an element of a flexible search query, e.g. 'SELECT' clause, 'FROM' clause, condition, etc.
 */
public interface FlexibleSearchQueryChainElement
{

	String SPACE = " ";
	String QUESTION_MARK = "?";
	String OPENING_BRACKET = "{";
	String CLOSING_BRACKET = "}";
	String OPENING_BRACE = "(";
	String CLOSING_BRACE = ")";
	String INNER_QUERY_OPENING_BRACKETS = OPENING_BRACE + OPENING_BRACKET + OPENING_BRACKET;
	String INNER_QUERY_CLOSING_BRACKETS = CLOSING_BRACKET + CLOSING_BRACKET + CLOSING_BRACE;
	String ALIAS_AND_FIELD_SEPARATOR = ".";
	String FIELD_SEPARATOR = ",";
	String SELECT = "SELECT";
	String FROM = "FROM";
	String AS = "AS";
	String JOIN = "JOIN";
	String ON = "ON";
	String LEFT_JOIN = "LEFT JOIN";
	String RIGHT_JOIN = "RIGHT JOIN";
	String WHERE = "WHERE";
	String ORDER_BY = "ORDER BY";

}
