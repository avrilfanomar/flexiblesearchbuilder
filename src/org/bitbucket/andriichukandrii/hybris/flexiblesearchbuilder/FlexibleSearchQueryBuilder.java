package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toStream;

import de.hybris.platform.core.model.ItemModel;

import java.util.Arrays;
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
	 * @param firstValueWithType
	 *           first field-to-type mapping (which requires at least one argument)
	 * @param restFieldsWithTypes
	 *           rest fields and their types
	 * @return select clause
	 */
	public static SelectClause select(final ValueWithType firstValueWithType, final ValueWithType... restFieldsWithTypes)
	{
		final List<String> selectParameters = toStream(firstValueWithType, restFieldsWithTypes).map(ValueWithType::getValue)
				.collect(Collectors.toList());
		final List<Class<?>> types = toStream(firstValueWithType, restFieldsWithTypes).map(ValueWithType::getType).collect(
				Collectors.toList());
		return new SelectClause(selectParameters, types);
	}

	/**
	 * Builds select clause with given custom fields statement. Puts given result types (if any) into flexible search
	 * query.
	 *
	 * @param customFieldsStatement
	 *           custom statement to put into select clause, e.g. "COUNT({pk})"
	 * @param resultTypes
	 *           result types, may be left empty
	 * @return customized select clause
	 */
	public static CustomSelectClause selectCustom(final String customFieldsStatement, Class<?>... resultTypes)
	{
		return new CustomSelectClause(customFieldsStatement, Arrays.asList(resultTypes));
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
