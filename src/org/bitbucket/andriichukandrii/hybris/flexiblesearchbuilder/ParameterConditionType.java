package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum ParameterConditionType
{
	IS_EQUAL_TO("="), IS_LESS_THAN("<"), IS_LESS_OR_EQUAL("<="), IS_GREATER_THAN(">"), IS_GREATER_OR_EQUAL(">=");


	private final String operator;

	ParameterConditionType(final String operator)
	{
		this.operator = operator;
	}

	public String getValue()
	{
		return operator;
	}

}
