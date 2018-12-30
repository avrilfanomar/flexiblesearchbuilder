package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.ASC;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.DESC;

import java.util.Arrays;


public interface OrderByAcceptable
{
	default OrderByClause orderByAsc(final String... fields)
	{
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, Arrays.asList(fields), ASC);
	}

	default OrderByClause orderByDesc(final String... fields)
	{
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, Arrays.asList(fields), DESC);
	}
}
