package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import java.util.Arrays;

public interface GroupByAcceptable
{
	default GroupByClause groupBy(final String... fields)
	{
		return new GroupByClause((AbstractFlexibleSearchQueryChainElement) this, Arrays.asList(fields));
	}
}
