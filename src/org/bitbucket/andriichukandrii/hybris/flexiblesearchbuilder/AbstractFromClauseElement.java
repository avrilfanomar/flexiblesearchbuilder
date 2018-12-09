package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

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
}
