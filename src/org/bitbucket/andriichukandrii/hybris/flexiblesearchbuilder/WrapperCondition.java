package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Map;


/**
 * A proxy for a field condition.
 */
public class WrapperCondition extends AbstractFieldCondition
{

	private final AbstractFieldCondition original;


	WrapperCondition(final AbstractFlexibleSearchQueryChainElement parent, final AbstractFieldCondition condition)
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
}
