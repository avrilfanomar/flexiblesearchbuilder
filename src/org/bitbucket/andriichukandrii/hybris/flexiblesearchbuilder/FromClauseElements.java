package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public class FromClauseElements
{
	/**
	 * Creates table element by given model type.
	 * 
	 * @param clazz
	 *           model type
	 * @return table element of the query
	 */
	public static TableFromClauseElement table(final Class<? extends ItemModel> clazz)
	{
		return new TableFromClauseElement(clazz);
	}

	private FromClauseElements()
	{
	}
}
