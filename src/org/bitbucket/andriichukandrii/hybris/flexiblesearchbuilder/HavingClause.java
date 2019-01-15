package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class HavingClause extends TerminateQueryChainElement implements OrderByAcceptable
{
	private final AbstractCondition condition;

	HavingClause(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition condition)
	{
		super(parent);
		this.condition = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(HAVING).append(SPACE);
		condition.appendQuery(sb);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		condition.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		condition.configureQuery(flexibleSearchQuery);
	}
}
