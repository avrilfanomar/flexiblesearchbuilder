package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.CLOSING_BRACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.OPENING_BRACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


public class BraceConditionWrapper extends AbstractCondition
{
	private final AbstractCondition wrappedCondition;

	BraceConditionWrapper(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition wrappedCondition)
	{
		super(parent);
		this.wrappedCondition = wrappedCondition;
	}

	BraceConditionWrapper(final AbstractCondition wrappedCondition)
	{
		super(FlexibleSearchQueryStartChainElement.INSTANCE);
		this.wrappedCondition = wrappedCondition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (!FlexibleSearchQueryStartChainElement.INSTANCE.equals(parent))
		{
			sb.append(SPACE);
		}
		sb.append(OPENING_BRACE);
		wrappedCondition.appendQuery(sb);
		sb.append(CLOSING_BRACE);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		wrappedCondition.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		wrappedCondition.configureQuery(flexibleSearchQuery);
	}
}
