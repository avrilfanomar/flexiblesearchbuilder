package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * A proxy for a field condition.
 */
public class WrapperCondition extends AbstractCondition
{
	private final AbstractCondition original;


	WrapperCondition(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition condition)
	{
		super(parent);
		this.original = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE);//needed because wrapped condition doesn't have a parent and thus doesn't add space
		original.appendQuery(sb);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		original.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		original.configureQuery(flexibleSearchQuery);
	}
}
