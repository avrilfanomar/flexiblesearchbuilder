package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * An abstract element of the flexible search query.
 */
public abstract class AbstractFlexibleSearchQueryChainElement implements FlexibleSearchQueryChainElement
{
	protected final AbstractFlexibleSearchQueryChainElement parent;

	AbstractFlexibleSearchQueryChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		this.parent = parent;
	}

	protected void appendQuery(final StringBuilder sb)
	{
		if (parent != null)
		{
			parent.appendQuery(sb);
		}
	}

	protected void addParameters(final Map<String, Object> parameterMap)
	{
		if (parent != null)
		{
			parent.addParameters(parameterMap);
		}
	}

	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		if (parent != null)
		{
			parent.configureQuery(flexibleSearchQuery);
		}
	}
}
