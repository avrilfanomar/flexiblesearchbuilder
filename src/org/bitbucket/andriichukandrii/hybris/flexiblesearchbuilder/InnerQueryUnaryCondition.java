package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class InnerQueryUnaryCondition extends AbstractCondition
{
	private final UnaryQueryConditionType queryConditionType;
	private final TerminateQueryChainElement innerQuery;

	InnerQueryUnaryCondition(final UnaryQueryConditionType queryConditionType, final TerminateQueryChainElement innerQuery)
	{
		super(null);
		this.innerQuery = innerQuery;
		this.queryConditionType = queryConditionType;
	}

	InnerQueryUnaryCondition(final AbstractFlexibleSearchQueryChainElement parentElement,
			final UnaryQueryConditionType queryConditionType, final TerminateQueryChainElement innerQuery)
	{
		super(parentElement);
		this.innerQuery = innerQuery;
		this.queryConditionType = queryConditionType;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (parent != null)
		{
			sb.append(SPACE);
		}
		sb.append(queryConditionType.getOperator()).append(SPACE).append(INNER_QUERY_OPENING_BRACKETS);
		innerQuery.appendQuery(sb);
		sb.append(INNER_QUERY_CLOSING_BRACKETS);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		innerQuery.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		innerQuery.configureQuery(flexibleSearchQuery);
	}
}
