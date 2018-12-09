package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;


public class CombineCondition extends AbstractCondition
{
	private final CombineConditionType combineOperator;


	protected CombineCondition(final AbstractCondition parent, final CombineConditionType combineOperator)
	{
		super(parent);
		this.combineOperator = combineOperator;
	}


	public AbstractFieldCondition field(final String fieldName, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(this, fieldName, conditionType, conditionParameter);
	}

	public AbstractFieldCondition field(final AliasedField aliasedField, final ParameterConditionType conditionType,
			final Object conditionParameter)
	{
		return new ParameterFieldCondition(this, aliasedField.getValue(), conditionType, conditionParameter);
	}

	public AbstractFieldCondition field(final String fieldName, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(this, fieldName, conditionType);
	}

	public AbstractFieldCondition field(final AliasedField aliasedField, final ParameterlessConditionType conditionType)
	{
		return new ParameterlessFieldCondition(this, aliasedField.getValue(), conditionType);
	}


	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);

		if (combineOperator != null)
		{
			sb.append(SPACE).append(combineOperator.getValue());
		}
	}

}
