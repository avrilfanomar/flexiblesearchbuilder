package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.buildFieldsQueryString;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;


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
		sb.append(buildFieldsQueryString(fieldReferences));
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		flexibleSearchQuery.setResultClassList(resultTypes);
	}
}
