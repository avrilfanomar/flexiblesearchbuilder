package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public class FlexibleSearchQueryBuilder
{

	public static <T extends ItemModel> FromClause selectFrom(final Class<T> clazz)
	{
		return new FromClause(clazz);
	}

}
