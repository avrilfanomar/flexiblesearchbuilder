package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.RIGHT_JOIN;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'RIGHT JOIN' element of the flexible search query.
 */
public class RightJoinElement extends AbstractJoinElement
{

	RightJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz, RIGHT_JOIN);
	}

	RightJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode)
	{
		super(parent, typeCode, RIGHT_JOIN);
	}
}
