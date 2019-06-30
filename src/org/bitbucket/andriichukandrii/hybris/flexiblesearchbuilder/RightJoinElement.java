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
		super(parent, clazz);
	}

	RightJoinElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode)
	{
		super(parent, typeCode);
	}

	@Override
	protected String getJoinStatement()
	{
		return RIGHT_JOIN;
	}
}
