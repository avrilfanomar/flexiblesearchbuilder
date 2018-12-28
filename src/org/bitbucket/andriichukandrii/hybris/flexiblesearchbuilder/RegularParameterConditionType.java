package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum RegularParameterConditionType implements ParameterConditionType
{
	IS_EQUAL_TO("="), IS_NOT_EQUAL_TO("<>"), IS_LESS_THAN("<"), IS_LESS_OR_EQUAL("<="),
	IS_GREATER_THAN(">"), IS_GREATER_OR_EQUAL(">="), LIKE(" LIKE "), NOT_LIKE(" NOT LIKE ");


	private final String operator;

	RegularParameterConditionType(final String operator)
	{
		this.operator = operator;
	}

	@Override
	public String getOperator()
	{
		return operator;
	}

}
