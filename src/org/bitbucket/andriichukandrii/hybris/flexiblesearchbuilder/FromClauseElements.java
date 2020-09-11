package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public final class FromClauseElements
{
	private FromClauseElements()
	{
	}

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

	/**
	 * Creates table element by given type code.
	 *
	 * @param typeCode
	 *           model type code
	 * @return table element of the query
	 */
	public static TableFromClauseElement table(final String typeCode)
	{
		return new TableFromClauseElement(typeCode);
	}
}
