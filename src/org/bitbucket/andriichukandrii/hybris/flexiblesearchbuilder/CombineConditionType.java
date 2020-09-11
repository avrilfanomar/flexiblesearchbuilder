package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum CombineConditionType
{
	AND("AND"), OR("OR");

	private final String operator;

	CombineConditionType(final String operator)
	{
		this.operator = operator;
	}

	@Override
	public String toString()
	{
		return operator;
	}
}
