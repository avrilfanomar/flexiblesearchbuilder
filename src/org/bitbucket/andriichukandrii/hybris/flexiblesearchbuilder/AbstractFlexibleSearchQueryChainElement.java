package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Map;


/**
 * An abstract element of the flexible search query.
 */
public abstract class AbstractFlexibleSearchQueryChainElement implements FlexibleSearchQueryChainElement
{
	protected final AbstractFlexibleSearchQueryChainElement parent;

	AbstractFlexibleSearchQueryChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		this.parent = parent;
	}

	protected void appendQuery(final StringBuilder sb)
	{
		if (parent != null)
		{
			parent.appendQuery(sb);
		}
	}

	protected AbstractFlexibleSearchQueryChainElement getParent()
	{
		return parent;
	}

	protected void addParameters(final Map<String, Object> parameterMap)
	{
		if (getParent() != null)
		{
			getParent().addParameters(parameterMap);
		}
	}
}
