package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum OrderBySortingType
{
	ASC("ASC"), DESC("DESC");


	private final String operator;

	OrderBySortingType(final String operator)
	{
		this.operator = operator;
	}

	public String getOperator()
	{
		return operator;
	}
}
