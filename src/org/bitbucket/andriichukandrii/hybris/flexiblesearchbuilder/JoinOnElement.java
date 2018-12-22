package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


public class JoinOnElement extends AbstractFromClauseElement
{
	public static final String ON = "ON";

	private final AbstractCondition condition;

	JoinOnElement(final AbstractFlexibleSearchQueryChainElement parent, final AbstractCondition condition)
	{
		super(parent);
		this.condition = condition;
	}

	/**
	 * Join another table using given item type.
	 *
	 * @param clazz
	 *           model type
	 * @return join query element
	 */
	public JoinElement join(final Class<? extends ItemModel> clazz)
	{
		return new JoinElement(this, clazz);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(ON);
		condition.appendQuery(sb);
	}
}
