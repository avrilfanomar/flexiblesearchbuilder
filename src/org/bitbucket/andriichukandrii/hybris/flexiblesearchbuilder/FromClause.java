package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.CLOSING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.FROM;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.OPENING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Map;


/**
 * 'FROM' clause of the flexible search query.
 */
public class FromClause extends TerminateQueryChainElement implements OrderByAcceptable, GroupByAcceptable
{
	private final AbstractFromClauseElement lastElement;

	FromClause(final AbstractFlexibleSearchQueryChainElement parent, final AbstractFromClauseElement lastElement)
	{
		super(parent);
		this.lastElement = lastElement;
	}

	/**
	 * Creates 'WHERE' clause of the query.
	 * 
	 * @param condition
	 *           condition (last condition in condition chain)
	 * @return 'WHERE' clause
	 */
	public WhereClause where(final AbstractCondition condition)
	{
		return new WhereClause(this, condition);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(FROM).append(SPACE).append(OPENING_BRACKET);
		lastElement.appendQuery(sb);
		sb.append(CLOSING_BRACKET);
	}

	@Override
	protected void addParameters(final Map<String, Object> parameterMap)
	{
		super.addParameters(parameterMap);

		lastElement.addParameters(parameterMap);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		lastElement.configureQuery(flexibleSearchQuery);
	}
}
