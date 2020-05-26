package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CombineConditionType.AND;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CombineConditionType.OR;


/**
 * Abstract condition of the flexible search query.
 */
public abstract class AbstractCondition extends FlexibleSearchQueryInnerChainElement
{

	AbstractCondition(final AbstractFlexibleSearchQueryChainElement parent)
	{
		super(parent);
	}

	public CombineConditionElement and()
	{
		return new CombineConditionElement(this, AND);
	}

	public CombineConditionElement or()
	{
		return new CombineConditionElement(this, OR);
	}
}
