package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public class TableFromClauseElement extends AbstractTableFromClauseElement
{
	TableFromClauseElement(final Class<? extends ItemModel> clazz)
	{
		super(null, clazz);
	}

	/**
	 * Marks the table with given alias.
	 *
	 * @param alias
	 *           alias
	 * @return alias query element
	 */
	public AliasElement as(final Alias alias)
	{
		return new AliasElement(this, alias);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);
		sb.append(getTypecode());
	}
}
