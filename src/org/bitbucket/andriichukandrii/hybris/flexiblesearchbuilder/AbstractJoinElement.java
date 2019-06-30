package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * Abstract 'JOIN' element of the flexible search query.
 */
public abstract class AbstractJoinElement extends AbstractTableFromClauseElement
{

	AbstractJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz);
	}

	AbstractJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode)
	{
		super(parent, typeCode);
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

		sb.append(SPACE).append(getJoinStatement()).append(SPACE).append(getTypecode());
	}

	protected abstract String getJoinStatement();
}
