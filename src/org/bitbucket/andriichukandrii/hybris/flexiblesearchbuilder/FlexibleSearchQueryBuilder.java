package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;

import de.hybris.platform.core.model.ItemModel;


public class FlexibleSearchQueryBuilder
{

	/**
	 * Builds select statement from given model type.
	 *
	 * @param clazz
	 *           model type
	 * @return from clause with given model type (which is wrapping select clause inside it)
	 */
	public static FromClause selectFrom(final Class<? extends ItemModel> clazz)
	{
		return new FromClause(new SelectClause(), table(clazz));
	}

	/**
	 * Builds default select statement (selecting PK, i.e. model selection) with given alias.
	 * 
	 * @param alias
	 *           alias
	 * @return select statement
	 */
	public static SelectClause select(final Alias alias)
	{
		return new SelectClause(alias.pk().getValue());
	}
}
