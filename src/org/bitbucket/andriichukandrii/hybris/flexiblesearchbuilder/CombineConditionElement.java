package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;


public class CombineConditionElement extends AbstractFlexibleSearchQueryChainElement
{
	private final CombineConditionType combineOperator;


	CombineConditionElement(final AbstractCondition parent, final CombineConditionType combineOperator)
	{
		super(parent);
		this.combineOperator = combineOperator;
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
	public AbstractFieldCondition condition(final String fieldName, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(this, fieldName, conditionType, conditionParameter);
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
	public AbstractFieldCondition condition(final AliasedField aliasedField, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(this, aliasedField.getValue(), conditionType, conditionParameter);
	}

	/**
	 * Creates field condition with a given parameter.
	 *
	 * @param field1
	 *           first field with alias
	 * @param conditionType
	 *           type of condition
	 * @param field2
	 *           second field with alias
	 * @return new field condition
	 */
	public AbstractFieldCondition condition(final AliasedField field1, final ParameterConditionType conditionType,
											final AliasedField field2)
	{
		return new FieldToFieldCondition(this, field1.getValue(), conditionType, field2.getValue());
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
	public AbstractFieldCondition condition(final String fieldName, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(this, fieldName, conditionType);
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
	public AbstractFieldCondition condition(final AliasedField aliasedField, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(this, aliasedField.getValue(), conditionType);
	}

	/**
	 * Wraps given condition to be chained.
	 * 
	 * @param condition
	 *           condition to wrap
	 * @return wrapped condition
	 */
	public AbstractFieldCondition condition(final AbstractFieldCondition condition)
	{
		return new WrapperCondition(this, condition);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (combineOperator != null)
		{
			sb.append(SPACE).append(combineOperator.getValue());
		}
	}

}
