package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum ParameterlessConditionType
{
	IS_NULL("IS NULL"), IS_NOT_NULL("IS NOT NULL");


	private final String operator;

	ParameterlessConditionType(final String operator)
	{
		this.operator = operator;
	}

	public String getValue()
	{
		return operator;
	}

}
