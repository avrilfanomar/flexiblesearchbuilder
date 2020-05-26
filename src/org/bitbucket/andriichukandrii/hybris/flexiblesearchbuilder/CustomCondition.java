package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;


public class CustomCondition extends AbstractCondition
{
	private final String conditionValue;

	CustomCondition(final String conditionValue)
	{
		super(FlexibleSearchQueryStartChainElement.INSTANCE);
		this.conditionValue = conditionValue;
	}

	CustomCondition(final AbstractFlexibleSearchQueryChainElement parent, final String conditionValue)
	{
		super(parent);
		this.conditionValue = conditionValue;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (!FlexibleSearchQueryStartChainElement.INSTANCE.equals(parent))
		{
			sb.append(SPACE);
		}
		sb.append(conditionValue);
	}
}
