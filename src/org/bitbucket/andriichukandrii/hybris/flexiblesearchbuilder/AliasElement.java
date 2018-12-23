package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


public class AliasElement extends AbstractFromClauseElement
{
	public static final String AS = "AS";

	private final Alias alias;

	AliasElement(final AbstractFlexibleSearchQueryChainElement parent, final Alias alias)
	{
		super(parent);
		this.alias = alias;
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

		sb.append(SPACE).append(AS).append(SPACE).append(alias.getAliasValue());
	}

}
