package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * An abstract element of the flexible search query.
 */
public class AbstractFlexibleSearchQueryChainElement implements FlexibleSearchQueryChainElement
{
	protected final AbstractFlexibleSearchQueryChainElement parent;

	public AbstractFlexibleSearchQueryChainElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		this.parent = parent;
	}

	protected void apply(final StringBuilder sb)
	{
		if (parent != null)
		{
			parent.apply(sb);
		}
	}

	protected AbstractFlexibleSearchQueryChainElement getParent()
	{
		return parent;
	}

}
