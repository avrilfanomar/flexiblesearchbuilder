package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;


public class ParameterlessFieldCondition extends AbstractFieldCondition
{
	private final ParameterlessConditionType conditionType;

	protected ParameterlessFieldCondition(final String fieldName, final ParameterlessConditionType conditionType)
	{
		super(fieldName);
		this.conditionType = conditionType;
	}

	protected ParameterlessFieldCondition(final AbstractCondition parent, final String fieldName,
			final ParameterlessConditionType conditionType)
	{
		super(parent, fieldName);
		this.conditionType = conditionType;
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);

		sb.append(SPACE).append(conditionType.getValue());
	}
}
