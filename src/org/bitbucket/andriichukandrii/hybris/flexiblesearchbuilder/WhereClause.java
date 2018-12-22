package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.HashMap;
import java.util.Map;


/**
 * 'WHERE' clause of a flexible search query.
 */
public class WhereClause extends TerminateQueryChainElement
{
	public static final String WHERE = "WHERE";

	private AbstractCondition lastCondition;

	WhereClause(final FromClause fromClause, final AbstractCondition condition)
	{
		super(fromClause);
		this.lastCondition = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(WHERE);
		lastCondition.appendQuery(sb);
	}

	@Override
	protected Map<String, Object> buildParameters()
	{
		final Map<String, Object> parameterMap = new HashMap<>();
		lastCondition.addParameters(parameterMap);
		return parameterMap;
	}
}
