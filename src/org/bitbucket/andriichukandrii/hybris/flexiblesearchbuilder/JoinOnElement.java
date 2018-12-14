package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;


public class JoinOnElement extends AbstractFromClauseElement
{
	public static final String ON = "ON";

	private final AbstractCondition condition;

	JoinOnElement(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition condition)
	{
		super(parent);
		this.condition = condition;
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);

		sb.append(SPACE).append(ON);
		condition.apply(sb);
		closeBracketsIfNeeded(sb);
	}
}
