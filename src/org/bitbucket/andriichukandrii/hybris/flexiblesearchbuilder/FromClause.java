package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'FROM' clause of the flexible search query.
 */
public class FromClause extends TableFromClauseElement
{
	public static final String FROM = "FROM";

	FromClause(final Class<? extends ItemModel> clazz)
	{
		super(clazz);
	}

	FromClause(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz);
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
		this.endingClauseElement = false;
		return new AliasElement(this, alias);
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);
		sb.append(SPACE).append(FROM).append(SPACE).append(OPENING_BRACKET).append(getTypecode());
		closeBracketsIfNeeded(sb);
	}

}
