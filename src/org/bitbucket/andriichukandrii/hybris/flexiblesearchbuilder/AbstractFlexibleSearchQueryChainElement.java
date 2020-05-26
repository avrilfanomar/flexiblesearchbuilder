package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;

/**
 * An abstract element of the flexible search query.
 */
public abstract class AbstractFlexibleSearchQueryChainElement implements FlexibleSearchQueryChainElement
{
	protected abstract void appendQuery(final StringBuilder sb);

	protected abstract void addParameters(final Map<String, Object> parameterMap);

	protected abstract void configureQuery(final FlexibleSearchQuery flexibleSearchQuery);
}
