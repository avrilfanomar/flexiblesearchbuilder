package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * Abstract 'JOIN' element of the flexible search query.
 */
public abstract class AbstractJoinElement extends AbstractTableFromClauseElement
{
	private final String joinStatement;

	AbstractJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz,
			final String joinStatement)
	{
		super(parent, clazz);
		this.joinStatement = joinStatement;
	}

	AbstractJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode, final String joinStatement)
	{
		super(parent, typeCode);
		this.joinStatement = joinStatement;
	}

	/**
	 * Marks the table with given alias.
	 * 
	 * @param alias
	 *           alias
	 * @return alias query element
	 */
	public JoinAliasElement as(final Alias alias)
	{
		return new JoinAliasElement(this, alias);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(joinStatement).append(SPACE).append(getTypecode());
	}
}
