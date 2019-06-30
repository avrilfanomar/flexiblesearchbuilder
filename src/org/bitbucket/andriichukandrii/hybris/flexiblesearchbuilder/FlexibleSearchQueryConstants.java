package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public final class FlexibleSearchQueryConstants
{
	public static final String SPACE = " ";
	public static final String QUESTION_MARK = "?";
	public static final String OPENING_BRACKET = "{";
	public static final String CLOSING_BRACKET = "}";
	public static final String OPENING_BRACE = "(";
	public static final String CLOSING_BRACE = ")";
	public static final String INNER_QUERY_OPENING_BRACKETS = OPENING_BRACE + OPENING_BRACKET + OPENING_BRACKET;
	public static final String INNER_QUERY_CLOSING_BRACKETS = CLOSING_BRACKET + CLOSING_BRACKET + CLOSING_BRACE;
	public static final String ALIAS_AND_FIELD_SEPARATOR = ".";
	public static final String FIELD_SEPARATOR = ",";
	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String AS = "AS";
	public static final String JOIN = "JOIN";
	public static final String ON = "ON";
	public static final String LEFT_JOIN = "LEFT JOIN";
	public static final String RIGHT_JOIN = "RIGHT JOIN";
	public static final String WHERE = "WHERE";
	public static final String ORDER_BY = "ORDER BY";
	public static final String GROUP_BY = "GROUP BY";
	public static final String HAVING = "HAVING";


	private FlexibleSearchQueryConstants()
	{
	}
}
