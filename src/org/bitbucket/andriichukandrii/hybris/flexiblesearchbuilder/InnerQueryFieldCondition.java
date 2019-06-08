package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class InnerQueryFieldCondition extends AbstractFieldCondition
{
	private final CollectionAndQueryConditionType conditionType;
	private final TerminateQueryChainElement innerQuery;

	protected InnerQueryFieldCondition(final Field field, final CollectionAndQueryConditionType conditionType,
			final TerminateQueryChainElement innerQuery)
	{
		super(field);
		this.conditionType = conditionType;
		this.innerQuery = innerQuery;
	}

	protected InnerQueryFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field,
			final CollectionAndQueryConditionType conditionType, final TerminateQueryChainElement innerQuery)
	{
		super(parent, field);
		this.conditionType = conditionType;
		this.innerQuery = innerQuery;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getOperator()).append(INNER_QUERY_OPENING_BRACKETS);
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
