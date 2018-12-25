package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'JOIN' (i.e. inner join) element of the flexible search query.
 */
public class InnerJoinElement extends AbstractJoinElement
{
	public static final String JOIN = "JOIN";

	InnerJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz);
	}

	@Override
	protected String getJoinStatement()
	{
		return JOIN;
	}
}
