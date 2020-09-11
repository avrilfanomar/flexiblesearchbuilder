package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

@SuppressWarnings("SameParameterValue")
public enum TwoParameterConditionType implements ConditionType
{
	BETWEEN("BETWEEN", "AND"), NOT_BETWEEN("NOT BETWEEN", "AND");


	private final String operator;
	private final String parameterJoinOperation;

	TwoParameterConditionType(final String operator, final String parameterJoinOperation)
	{
		this.operator = operator;
		this.parameterJoinOperation = parameterJoinOperation;
	}

	@Override
	public String getOperator()
	{
		return operator;
	}

	public String getParameterJoinOperation()
	{
		return parameterJoinOperation;
	}
}
