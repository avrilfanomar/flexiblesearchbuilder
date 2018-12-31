package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toStream;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;

import de.hybris.platform.core.model.ItemModel;

import java.util.List;
import java.util.stream.Collectors;


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
		return new FromClause(new ModelSelectClause(), table(clazz));
	}

	/**
	 * Builds select clause of given fields. Applies field types to flexible search query.
	 * 
	 * @param firstFieldWithType
	 *           first field-to-type mapping (which requires at least one argument)
	 * @param restFieldsWithTypes
	 *           rest fields and their types
	 * @return select clause
	 */
	public static FieldSelectClause select(final FieldWithType firstFieldWithType, final FieldWithType... restFieldsWithTypes)
	{
		final List<String> fields = toStream(firstFieldWithType, restFieldsWithTypes).map(FieldWithType::getField).collect(
				Collectors.toList());
		final List<Class<?>> types = toStream(firstFieldWithType, restFieldsWithTypes).map(FieldWithType::getType).collect(
				Collectors.toList());
		return new FieldSelectClause(fields, types);
	}

	/**
	 * Builds default select statement (selecting PK, i.e. model selection) with given alias.
	 *
	 * @param alias
	 *           alias
	 * @return select statement
	 */
	public static ModelSelectClause select(final Alias alias)
	{
		return new ModelSelectClause(alias.pk());
	}
}
