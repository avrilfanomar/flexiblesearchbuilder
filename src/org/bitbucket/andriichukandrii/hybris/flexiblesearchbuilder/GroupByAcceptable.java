package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.toFieldList;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toList;

import java.util.List;


public interface GroupByAcceptable
{
	default GroupByClause groupBy(final String firstField, final String... restFields)
	{
		final List<String> fields = toList(firstField, restFields);
		return new GroupByClause((AbstractFlexibleSearchQueryChainElement) this, fields);
	}

	default GroupByClause groupBy(final AliasedField firstField, final AliasedField... restFields)
	{
		final List<String> fields = toFieldList(firstField, restFields);
		return new GroupByClause((AbstractFlexibleSearchQueryChainElement) this, fields);
	}
}
