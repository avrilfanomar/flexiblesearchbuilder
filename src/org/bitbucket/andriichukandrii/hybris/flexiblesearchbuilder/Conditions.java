package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Collection;


public final class Conditions
{
	private Conditions()
	{
	}

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
		return new ParameterFieldCondition(new SimpleField(fieldName), conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given parameters.
	 *
	 * @param fieldName
	 *           field name (from model item, e.g. ProductModel.NAME)
	 * @param conditionType
	 *           type of condition
	 * @param firstParameter
	 *           first parameter
	 * @param secondParameter
	 *           second parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final String fieldName, final TwoParameterConditionType conditionType,
			final Object firstParameter, final Object secondParameter)
	{
		return new TwoParameterFieldCondition(new SimpleField(fieldName), conditionType, firstParameter, secondParameter);
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
			final Collection<?> collectionConditionParameter)
	{
		return new ParameterFieldCondition(new SimpleField(fieldName), conditionType, collectionConditionParameter);
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
		return new InnerQueryFieldCondition(new SimpleField(fieldName), conditionType, innerQuery);
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
		return new ParameterFieldCondition(aliasedField, conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given parameter.
	 *
	 * @param aliasedField
	 *           field with alias
	 * @param conditionType
	 *           type of condition
	 * @param firstParameter
	 *           first parameter
	 * @param secondParameter
	 *           second parameter
	 * @return new field condition
	 */
	public static AbstractFieldCondition condition(final AliasedField aliasedField, final TwoParameterConditionType conditionType,
			final Object firstParameter, final Object secondParameter)
	{
		return new TwoParameterFieldCondition(aliasedField, conditionType, firstParameter, secondParameter);
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
			final CollectionAndQueryConditionType conditionType, Collection<?> collectionConditionParameter)
	{
		return new ParameterFieldCondition(aliasedField, conditionType, collectionConditionParameter);
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
		return new InnerQueryFieldCondition(aliasedField, conditionType, innerQuery);
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
		return new ParameterlessFieldCondition(new SimpleField(fieldName), conditionType);
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
		return new ParameterlessFieldCondition(aliasedField, conditionType);
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

	/**
	 * Builds inner query condition.
	 *
	 * @param queryConditionType
	 *           condition type
	 * @param innerQuery
	 *           inner query
	 * @return inner query condition
	 */
	public static AbstractCondition condition(final UnaryQueryConditionType queryConditionType,
			TerminateQueryChainElement innerQuery)
	{
		return new InnerQueryUnaryCondition(queryConditionType, innerQuery);
	}

	/**
	 * Creates custom condition of given string. Given string is trimmed to remove redundant spaces if any.
	 *
	 * @param customCondition
	 *           custom condition string, e.g. "UPPER({name})=?name"
	 * @return new custom condition element
	 */
	public static AbstractCondition customCondition(final String customCondition)
	{
		return new CustomCondition(customCondition.trim());
	}
}
