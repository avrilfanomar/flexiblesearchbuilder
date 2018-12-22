package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Collections;
import java.util.Map;


/**
 * 'FROM' clause of the flexible search query.
 */
public class FromClause extends TerminateQueryChainElement
{
	public static final String FROM = "FROM";

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
	protected Map<String, Object> buildParameters()
	{
		return Collections.emptyMap();
	}
}
