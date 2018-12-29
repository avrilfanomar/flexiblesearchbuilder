package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * A proxy for a field condition.
 */
public class FieldWrapperCondition extends AbstractFieldCondition
{

	private final AbstractFieldCondition original;


	FieldWrapperCondition(final AbstractFlexibleSearchQueryChainElement parent, final AbstractFieldCondition condition)
	{
		super(parent, condition.fieldName);
		this.original = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		original.appendQuery(sb);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		original.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery) {
		super.configureQuery(flexibleSearchQuery);

		original.configureQuery(flexibleSearchQuery);
	}
}
