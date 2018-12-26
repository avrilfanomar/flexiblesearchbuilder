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
	String ALIAS_AND_FIELD_SEPARATOR = ".";
	String FIELD_SEPARATOR = ",";

}
