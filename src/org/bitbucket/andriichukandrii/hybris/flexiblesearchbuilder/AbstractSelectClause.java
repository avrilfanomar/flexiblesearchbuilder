package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SELECT;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;


/**
 * 'SELECT' clause of the flexible search query, used to query the models.
 */
public abstract class AbstractSelectClause extends AbstractFlexibleSearchQueryChainElement
{

	AbstractSelectClause()
	{
		super(null);
	}

	/**
	 * Builds "FROM" statement of the query.
	 * 
	 * @param lastClauseElement
	 *           ending from clause element, which is wrapping all others (if any)
	 * @return "FROM" clause of the query
	 */
	public FromClause from(final AbstractFromClauseElement lastClauseElement)
	{
		return new FromClause(this, lastClauseElement);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);
		sb.append(SELECT).append(SPACE);
		appendFieldsPart(sb);
	}

	protected abstract void appendFieldsPart(StringBuilder sb);
}
