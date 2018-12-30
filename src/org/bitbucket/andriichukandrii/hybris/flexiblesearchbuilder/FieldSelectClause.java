package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;
import java.util.stream.Collectors;


public class FieldSelectClause extends AbstractSelectClause
{
	private final List<String> fieldReferences;
	private final List<Class<?>> resultTypes;

	FieldSelectClause(final List<String> fieldReferences, final List<Class<?>> resultTypes)
	{
		this.fieldReferences = fieldReferences;
		this.resultTypes = resultTypes;
	}

	@Override
	protected void appendFieldsPart(final StringBuilder sb)
	{
		final String fieldsString = fieldReferences.stream().collect(
				Collectors.joining(CLOSING_BRACKET + FIELD_SEPARATOR + OPENING_BRACKET, OPENING_BRACKET, CLOSING_BRACKET));

		sb.append(fieldsString);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		flexibleSearchQuery.setResultClassList(resultTypes);
	}
}
