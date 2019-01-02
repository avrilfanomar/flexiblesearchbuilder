package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;


public class CustomSelectClause extends AbstractSelectClause
{
	private final String customFieldsPart;
	private final List<Class<?>> resultTypes;

	CustomSelectClause(final String customFieldsPart, final List<Class<?>> resultTypes)
	{
		this.customFieldsPart = customFieldsPart;
		this.resultTypes = resultTypes;
	}

	@Override
	protected void appendFieldsPart(final StringBuilder sb)
	{
		sb.append(customFieldsPart);
	}

	@Override
	protected void configureQuery(final FlexibleSearchQuery flexibleSearchQuery)
	{
		super.configureQuery(flexibleSearchQuery);

		flexibleSearchQuery.setResultClassList(resultTypes);
	}
}
