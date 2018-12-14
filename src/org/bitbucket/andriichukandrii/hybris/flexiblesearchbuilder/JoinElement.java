package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'JOIN' element of the flexible search query.
 */
public class JoinElement extends TableFromClauseElement
{
	public static final String JOIN = "JOIN";

	JoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
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
	public JoinAliasElement as(final Alias alias)
	{
		this.endingClauseElement = false;
		return new JoinAliasElement(this, alias);
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);
		sb.append(SPACE).append(JOIN).append(SPACE).append(getTypecode());
		closeBracketsIfNeeded(sb);
	}
}
