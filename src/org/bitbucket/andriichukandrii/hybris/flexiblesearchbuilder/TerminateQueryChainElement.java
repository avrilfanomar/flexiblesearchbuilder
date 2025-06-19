package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.HashMap;
import java.util.Map;


/**
 * Part of the flexible search query, that can be the last one, thus having the build() method.
 */
public abstract class TerminateQueryChainElement extends FlexibleSearchQueryInnerChainElement
{
	TerminateQueryChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		super(parent);
	}

	/**
	 * Begins a UNION operation by combining this query with the provided query.
	 * This query will be the first part of the UNION.
	 *
	 * @param queryToAdd The query to combine with this one using a UNION operation. Must not be null.
	 * @return A new {@link UnionQueryChainElement} representing the combined queries, allowing for further chaining or building.
	 * @throws IllegalArgumentException if queryToAdd is null (handled by UnionQueryChainElement constructor).
	 */
	public UnionQueryChainElement union(final TerminateQueryChainElement queryToAdd)
	{
		return new UnionQueryChainElement(this, queryToAdd, UnionType.UNION);
	}

	/**
	 * Begins a UNION ALL operation by combining this query with the provided query.
	 * This query will be the first part of the UNION ALL.
	 *
	 * @param queryToAdd The query to combine with this one using a UNION ALL operation. Must not be null.
	 * @return A new {@link UnionQueryChainElement} representing the combined queries, allowing for further chaining or building.
	 * @throws IllegalArgumentException if queryToAdd is null (handled by UnionQueryChainElement constructor).
	 */
	public UnionQueryChainElement unionAll(final TerminateQueryChainElement queryToAdd)
	{
		return new UnionQueryChainElement(this, queryToAdd, UnionType.UNION_ALL);
	}

	/**
	 * Builds the flexible search query with all chained elements inside current element. Puts all parameters (if any) into
	 * query as well.
	 * 
	 * @return flexible search query
	 */
	public FlexibleSearchQuery build()
	{
		final Map<String, Object> parameters = buildParameters();
		final String query = buildQuery();
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query, parameters);

		configureQuery(fQuery);

		return fQuery;
	}

	private Map<String, Object> buildParameters()
	{
		final Map<String, Object> parameterMap = new HashMap<>();
		addParameters(parameterMap);
		return parameterMap;
	}

	private String buildQuery()
	{
		final StringBuilder sb = new StringBuilder();
		appendQuery(sb);//applies all clauses internally
		return sb.toString();
	}
}
