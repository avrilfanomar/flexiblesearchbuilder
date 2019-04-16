package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * 'WHERE' clause of a flexible search query.
 */
public class WhereClause extends TerminateQueryChainElement implements OrderByAcceptable, GroupByAcceptable
{
	private final AbstractCondition lastCondition;

	WhereClause(final FromClause fromClause, final AbstractCondition condition)
	{
		super(fromClause);
		this.lastCondition = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(WHERE).append(SPACE);
		lastCondition.appendQuery(sb);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		lastCondition.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		lastCondition.configureQuery(flexibleSearchQuery);
	}
}
