package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class InnerQueryFieldCondition extends AbstractFieldCondition
{
	private final CollectionAndQueryConditionType conditionType;
	private final TerminateQueryChainElement innerQuery;

	protected InnerQueryFieldCondition(final String fieldName, final CollectionAndQueryConditionType conditionType,
			final TerminateQueryChainElement innerQuery)
	{
		super(fieldName);
		this.conditionType = conditionType;
		this.innerQuery = innerQuery;
	}

	protected InnerQueryFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final String fieldName,
			final CollectionAndQueryConditionType conditionType, final TerminateQueryChainElement innerQuery)
	{
		super(parent, fieldName);
		this.conditionType = conditionType;
		this.innerQuery = innerQuery;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(conditionType.getOperator()).append(OPENING_BRACE).append(OPENING_BRACKET).append(OPENING_BRACKET);
		innerQuery.appendQuery(sb);
		sb.append(CLOSING_BRACKET).append(CLOSING_BRACKET).append(CLOSING_BRACE);
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
