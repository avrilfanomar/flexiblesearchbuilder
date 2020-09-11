package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.LEFT_JOIN;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'LEFT JOIN' element of the flexible search query.
 */
public class LeftJoinElement extends AbstractJoinElement
{

	LeftJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent, clazz, LEFT_JOIN);
	}

	LeftJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode)
	{
		super(parent, typeCode, LEFT_JOIN);
	}
}
