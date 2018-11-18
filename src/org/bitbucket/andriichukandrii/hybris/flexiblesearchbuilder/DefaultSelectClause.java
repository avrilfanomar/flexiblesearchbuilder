package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * Default 'SELECT' clause of the flexible search query, used to query the models.
 */
public class DefaultSelectClause extends AbstractFlexibleSearchQueryChainElement
{
	public static final String SELECT = "SELECT";


	DefaultSelectClause()
	{
		super(null);
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);
		sb.append(SELECT).append(SPACE).append(OPENING_BRACKET).append(ItemModel.PK).append(CLOSING_BRACKET);
	}
}
