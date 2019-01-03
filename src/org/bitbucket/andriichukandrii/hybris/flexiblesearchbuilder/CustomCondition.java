package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public class CustomCondition extends AbstractCondition
{
	private final String customCondition;

	CustomCondition(final String customCondition)
	{
		super(null);
		this.customCondition = customCondition;
	}

	CustomCondition(final AbstractFlexibleSearchQueryChainElement parent, final String customCondition)
	{
		super(parent);
		this.customCondition = customCondition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (parent != null)
		{
			sb.append(SPACE);
		}
		sb.append(customCondition);
	}
}
