package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'RIGHT JOIN' element of the flexible search query.
 */
public class RightJoinElement extends AbstractJoinElement
{

	RightJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz);
	}

	@Override
	protected String getJoinStatement()
	{
		return RIGHT_JOIN;
	}
}
