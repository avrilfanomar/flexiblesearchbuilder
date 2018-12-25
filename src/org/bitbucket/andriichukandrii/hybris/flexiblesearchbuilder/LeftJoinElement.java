package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'LEFT JOIN' element of the flexible search query.
 */
public class LeftJoinElement extends AbstractJoinElement
{
	public static final String LEFT_JOIN = "LEFT JOIN";

	LeftJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz);
	}

	@Override
	protected String getJoinStatement()
	{
		return LEFT_JOIN;
	}
}
