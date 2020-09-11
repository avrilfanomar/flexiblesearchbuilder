package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * Inner chain element, which always has a parent.
 */
public abstract class FlexibleSearchQueryInnerChainElement extends AbstractFlexibleSearchQueryChainElement
{
	protected final AbstractFlexibleSearchQueryChainElement parent;

	FlexibleSearchQueryInnerChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		this.parent = parent;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		parent.appendQuery(sb);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		parent.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		parent.configureQuery(flexibleSearchQuery);
	}
}
