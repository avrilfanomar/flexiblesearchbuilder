package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractFromClauseElement extends TerminateQueryChainElement
{
	protected boolean endingClauseElement = true;

	AbstractFromClauseElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		super(parent);
	}

	public WhereClause where(final AbstractCondition condition)
	{
		return new WhereClause(this, condition);
	}

	protected void closeBracketsIfNeeded(final StringBuilder sb)
	{
		if (this.endingClauseElement)
		{
			sb.append(CLOSING_BRACKET);
		}
	}

	@Override
	protected Map<String, Object> buildParameters()
	{
		return Collections.emptyMap();
	}
}
