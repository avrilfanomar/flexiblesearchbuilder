package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.ON;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;


public class JoinOnElement extends JoinableFromClauseElement
{
	private final AbstractCondition condition;

	JoinOnElement(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition condition)
	{
		super(parent);
		this.condition = condition;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(ON).append(SPACE);
		condition.appendQuery(sb);
	}
}
