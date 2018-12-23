package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public class Conditions
{
	/**
	 * Creates field condition with a given parameter.
	 * 
	 * @param fieldName
	 *           field name (from model item, e.g. ProductModel.NAME)
	 * @param conditionType
	 *           type of condition
	 * @param conditionParameter
	 *           parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final String fieldName, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(fieldName, conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given parameter.
	 * 
	 * @param aliasedField
	 *           field with alias
	 * @param conditionType
	 *           type of condition
	 * @param conditionParameter
	 *           parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final AliasedField aliasedField, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(aliasedField.getValue(), conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given condition.
	 * 
	 * @param fieldName
	 *           field name (from model item, e.g. ProductModel.NAME)
	 * @param conditionType
	 *           type of condition, which doesn't require parameter (e.g. ParameterlessConditionType.IS_NULL)
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final String fieldName, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(fieldName, conditionType);
	}

	/**
	 * Creates field condition with a given condition.
	 * 
	 * @param aliasedField
	 *           field with alias
	 * @param conditionType
	 *           type of condition, which doesn't require parameter (e.g. ParameterlessConditionType.IS_NULL)
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final AliasedField aliasedField, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(aliasedField.getValue(), conditionType);
	}
}
