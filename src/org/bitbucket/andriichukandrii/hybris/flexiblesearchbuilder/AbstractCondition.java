package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CombineConditionType.AND;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CombineConditionType.OR;

import java.util.Map;

/**
 * Abstract condition of the flexible search query.
 */
public abstract class AbstractCondition extends AbstractFlexibleSearchQueryChainElement
{

	public AbstractCondition(final AbstractCondition parent)
	{
		super(parent);
	}

	public CombineCondition and()
	{
		return new CombineCondition(this, AND);
	}

	public CombineCondition or()
	{
		return new CombineCondition(this, OR);
	}

	protected void addParameters(final Map<String, Object> parameterMap)
	{
		if (getParent() != null)
		{
			getParent().addParameters(parameterMap);
		}
	}

	@Override
	protected AbstractCondition getParent()
	{
		return (AbstractCondition) parent;
	}
}
