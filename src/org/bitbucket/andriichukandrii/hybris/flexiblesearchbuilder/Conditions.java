package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Collection;


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
	public static AbstractFieldCondition condition(final String fieldName, final RegularParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(fieldName, conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given collection parameter.
	 *
	 * @param fieldName
	 *           field name (from model item, e.g. ProductModel.NAME)
	 * @param conditionType
	 *           type of condition (which supports collection as parameter)
	 * @param collectionConditionParameter
	 *           collection parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final String fieldName, final CollectionAndQueryConditionType conditionType,
			final Collection collectionConditionParameter)
	{
		return new ParameterFieldCondition(fieldName, conditionType, collectionConditionParameter);
	}

	/**
	 * Creates field condition with a given inner query.
	 *
	 * @param fieldName
	 *           field name (from model item, e.g. ProductModel.NAME)
	 * @param conditionType
	 *           type of condition (which supports inner query as parameter)
	 * @param innerQuery
	 *           inner query
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final String fieldName, final CollectionAndQueryConditionType conditionType,
			TerminateQueryChainElement innerQuery)
	{
		return new InnerQueryFieldCondition(fieldName, conditionType, innerQuery);
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
	public static AbstractFieldCondition condition(final AliasedField aliasedField,
			final RegularParameterConditionType conditionType, final Object conditionParameter)
	{
		return new ParameterFieldCondition(aliasedField.getValue(), conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given collection parameter.
	 * 
	 * @param aliasedField
	 *           field with alias
	 * @param conditionType
	 *           type of condition (which supports collection as parameter)
	 * @param collectionConditionParameter
	 *           collection parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final AliasedField aliasedField,
			final CollectionAndQueryConditionType conditionType, Collection collectionConditionParameter)
	{
		return new ParameterFieldCondition(aliasedField.getValue(), conditionType, collectionConditionParameter);
	}

	/**
	 * Creates field condition with a given inner query.
	 *
	 * @param aliasedField
	 *           field with alias
	 * @param conditionType
	 *           type of condition (which supports inner query as parameter)
	 * @param innerQuery
	 *           inner query
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final AliasedField aliasedField,
			final CollectionAndQueryConditionType conditionType, TerminateQueryChainElement innerQuery)
	{
		return new InnerQueryFieldCondition(aliasedField.getValue(), conditionType, innerQuery);
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

	/**
	 * Puts given condition (with chained conditions if any) into braces.
	 * 
	 * @param condition
	 *           condition to wrap
	 * @return braced condition chain
	 */
	public static BraceConditionWrapper braces(final AbstractCondition condition)
	{
		return new BraceConditionWrapper(condition);
	}
}
