package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


public class ParameterlessFieldCondition extends AbstractFieldCondition
{
	private final ParameterlessConditionType conditionType;

	ParameterlessFieldCondition(final String fieldName, final ParameterlessConditionType conditionType)
	{
		super(fieldName);
		this.conditionType = conditionType;
	}

	ParameterlessFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final String fieldName,
			final ParameterlessConditionType conditionType)
	{
		super(parent, fieldName);
		this.conditionType = conditionType;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(conditionType.getOperator());
	}
}
