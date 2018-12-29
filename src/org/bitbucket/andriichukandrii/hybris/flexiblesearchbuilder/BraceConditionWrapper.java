package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class BraceConditionWrapper extends AbstractCondition
{
	private final AbstractCondition wrappedCondition;

	BraceConditionWrapper(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition wrappedCondition)
	{
		super(parent);
		this.wrappedCondition = wrappedCondition;
	}

	BraceConditionWrapper(final AbstractCondition wrappedCondition)
	{
		super(null);
		this.wrappedCondition = wrappedCondition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (parent != null)
		{
			sb.append(SPACE);
		}
		sb.append(OPENING_BRACE);
		wrappedCondition.appendQuery(sb);
		sb.append(CLOSING_BRACE);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		wrappedCondition.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		wrappedCondition.configureQuery(flexibleSearchQuery);
	}
}
