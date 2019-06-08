package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


public class ParameterlessFieldCondition extends AbstractFieldCondition
{
	private final ParameterlessConditionType conditionType;

	ParameterlessFieldCondition(final Field field, final ParameterlessConditionType conditionType)
	{
		super(field);
		this.conditionType = conditionType;
	}

	ParameterlessFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final ParameterlessConditionType conditionType)
	{
		super(parent, field);
		this.conditionType = conditionType;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(conditionType.getOperator());
	}
}
