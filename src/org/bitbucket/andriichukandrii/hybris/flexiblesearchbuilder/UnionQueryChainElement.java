package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.CLOSING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.OPENING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents a flexible search query composed of multiple sub-queries combined using UNION or UNION ALL.
 * This element allows chaining of multiple union operations and ultimately building the final
 * {@link FlexibleSearchQuery}.
 */
public class UnionQueryChainElement extends TerminateQueryChainElement
{
	private final List<TerminateQueryChainElement> queries;
	private final List<UnionType> unionTypes;

	/**
	 * Constructs a new {@link UnionQueryChainElement} by combining two initial queries with a specified {@link UnionType}.
	 *
	 * @param firstQuery The first query in the UNION operation. Must not be null.
	 * @param secondQuery The second query to be UNIONed with the first. Must not be null.
	 * @param unionType The type of UNION operation (UNION or UNION ALL) to combine the first two queries. Must not be null.
	 * @throws IllegalArgumentException if any of the parameters are null.
	 */
	public UnionQueryChainElement(final TerminateQueryChainElement firstQuery, final TerminateQueryChainElement secondQuery,
			final UnionType unionType)
	{
		super(null); // Represents a new top-level query structure
		if (firstQuery == null || secondQuery == null || unionType == null)
		{
			throw new IllegalArgumentException("Initial queries and union type cannot be null");
		}
		this.queries = new ArrayList<>(Arrays.asList(firstQuery, secondQuery));
		this.unionTypes = new ArrayList<>(Arrays.asList(unionType));
	}

	/**
	 * Appends another query to this chain using a standard UNION operation.
	 *
	 * @param queryToAdd The query to be added to the chain. Must not be null.
	 * @return This {@link UnionQueryChainElement} for further chaining.
	 * @throws IllegalArgumentException if queryToAdd is null.
	 */
	public UnionQueryChainElement union(final TerminateQueryChainElement queryToAdd)
	{
		if (queryToAdd == null)
		{
			throw new IllegalArgumentException("queryToAdd cannot be null");
		}
		this.queries.add(queryToAdd);
		this.unionTypes.add(UnionType.UNION);
		return this;
	}

	/**
	 * Appends another query to this chain using a UNION ALL operation.
	 *
	 * @param queryToAdd The query to be added to the chain. Must not be null.
	 * @return This {@link UnionQueryChainElement} for further chaining.
	 * @throws IllegalArgumentException if queryToAdd is null.
	 */
	public UnionQueryChainElement unionAll(final TerminateQueryChainElement queryToAdd)
	{
		if (queryToAdd == null)
		{
			throw new IllegalArgumentException("queryToAdd cannot be null");
		}
		this.queries.add(queryToAdd);
		this.unionTypes.add(UnionType.UNION_ALL);
		return this;
	}

	/**
	 * Appends the string representation of all UNIONed sub-queries to the provided {@link StringBuilder}.
	 * Each sub-query is enclosed in parentheses and separated by the appropriate UNION keyword.
	 * @param sb The {@link StringBuilder} to append the query to.
	 */
	@Override
	void appendQuery(final StringBuilder sb)
	{
		for (int i = 0; i < queries.size(); i++)
		{
			if (i > 0)
			{
				sb.append(SPACE);
				sb.append(unionTypes.get(i - 1).getKeyword());
				sb.append(SPACE);
			}

			final StringBuilder subQuerySb = new StringBuilder();
			queries.get(i).appendQuery(subQuerySb);

			sb.append(OPENING_BRACKET);
			sb.append(subQuerySb);
			sb.append(CLOSING_BRACKET);
		}
	}

	@Override
	void addParameters(final Map<String, Object> parameterMap)
	{
		for (final TerminateQueryChainElement query : queries)
		{
			query.addParameters(parameterMap);
		}
	}

	/**
	 * Configures the provided {@link FlexibleSearchQuery} object, primarily by setting its result class list
	 * based on the result types of the first sub-query in the UNION chain.
	 * Parameters are aggregated by the {@link #addParameters(Map)} method.
	 * @param flexibleSearchQuery The {@link FlexibleSearchQuery} to configure.
	 */
	@Override
	void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		if (!queries.isEmpty())
		{
			// Build the first query to get its configuration (especially resultClassList)
			final FlexibleSearchQuery firstSubQueryConfig = queries.get(0).build();
			if (firstSubQueryConfig.getResultClassList() != null && !firstSubQueryConfig.getResultClassList().isEmpty())
			{
				flexibleSearchQuery.setResultClassList(firstSubQueryConfig.getResultClassList());
			}
			// Other properties like count, start, needTotal are typically applied to the overall UNION query.
			// The FlexibleSearchQuery passed to this method is the one that will be returned by the
			// main build() method, so its existing settings for count, start, needTotal etc. should be respected.
		}
	}
}
