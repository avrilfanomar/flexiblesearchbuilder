package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum CollectionAndQueryConditionType implements QueryConditionType, ParameterConditionType
{
	IN(" IN "), NOT_IN(" NOT IN ");


	private final String operator;

	CollectionAndQueryConditionType(final String operator)
	{
		this.operator = operator;
	}

	@Override
	public String getOperator()
	{
		return operator;
	}
}
