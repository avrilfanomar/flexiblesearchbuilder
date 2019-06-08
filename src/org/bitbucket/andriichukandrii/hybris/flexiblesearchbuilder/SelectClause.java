package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.buildQueryString;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;


public class SelectClause extends AbstractSelectClause
{
	private final List<String> parameters;
	private final List<Class<?>> resultTypes;

	SelectClause(final List<String> parameters, final List<Class<?>> resultTypes)
	{
		this.parameters = parameters;
		this.resultTypes = resultTypes;
	}

	@Override
	protected void appendFieldsPart(final StringBuilder sb)
	{
		sb.append(buildQueryString(parameters));
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		flexibleSearchQuery.setResultClassList(resultTypes);
	}
}
