package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public class Conditions
{
	public static AbstractCondition field(final String fieldName, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(fieldName, conditionType, conditionParameter);
	}

	public static AbstractCondition field(final AliasedField aliasedField, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(aliasedField.getValue(), conditionType, conditionParameter);
	}

	public static AbstractCondition field(final String fieldName, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(fieldName, conditionType);
	}

	public static AbstractCondition field(final AliasedField aliasedField, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(aliasedField.getValue(), conditionType);
	}
}
