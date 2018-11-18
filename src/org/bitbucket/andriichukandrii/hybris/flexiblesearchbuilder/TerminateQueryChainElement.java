package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * Part of the flexible search query, that can be the last one, thus having the build() method.
 */
public abstract class TerminateQueryChainElement extends AbstractFlexibleSearchQueryChainElement
{
	TerminateQueryChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		super(parent);
	}

	public FlexibleSearchQuery build()
	{
		final Map<String, Object> parameters = buildParameters();
		final StringBuilder sb = buildQuery();
		return new FlexibleSearchQuery(sb.toString(), parameters);
	}

	protected abstract Map<String, Object> buildParameters();

	private StringBuilder buildQuery()
	{
		final StringBuilder sb = new StringBuilder();
		apply(sb);//applies all clauses internally
		return sb;
	}
}
