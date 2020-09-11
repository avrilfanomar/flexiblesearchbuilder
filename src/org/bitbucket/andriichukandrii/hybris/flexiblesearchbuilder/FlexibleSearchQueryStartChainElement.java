package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * First chain element of the query. Helps to enable a no-null policy.
 */
public final class FlexibleSearchQueryStartChainElement extends AbstractFlexibleSearchQueryChainElement
{
	public static final FlexibleSearchQueryStartChainElement INSTANCE = new FlexibleSearchQueryStartChainElement();

	private FlexibleSearchQueryStartChainElement()
	{
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		//nothing to do for this class
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		//nothing to do for this class
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		//nothing to do for this class
	}
}
