package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.toFieldList;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.ASC;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.DESC;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toList;

import java.util.List;


public interface OrderByAcceptable
{
	default OrderByClause orderByAsc(final String firstField, final String... restFields)
	{
		final List<String> fields = toList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, ASC);
	}

	default OrderByClause orderByAsc(final AliasedField firstField, final AliasedField... restFields)
	{
		final List<String> fields = toFieldList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, ASC);
	}


	default OrderByClause orderByDesc(final String firstField, final String... restFields)
	{
		final List<String> fields = toList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, DESC);
	}

	default OrderByClause orderByDesc(final AliasedField firstField, final AliasedField... restFields)
	{
		final List<String> fields = toFieldList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, DESC);
	}
}
