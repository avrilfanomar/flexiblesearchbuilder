package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum UnaryQueryConditionType implements QueryConditionType
{
	EXISTS("EXISTS"), NOT_EXISTS("NOT EXISTS");


	private final String operator;

	UnaryQueryConditionType(final String operator)
	{
		this.operator = operator;
	}

	@Override
	public String getOperator()
	{
		return operator;
	}
}
