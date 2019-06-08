package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toList;

import java.util.List;
import java.util.stream.Collectors;


public interface GroupByAcceptable
{
	default GroupByClause groupBy(final String firstField, final String... restFields)
	{
		final List<Field> fields = toList(firstField, restFields).stream().map(SimpleField::new).collect(Collectors.toList());
		return new GroupByClause((AbstractFlexibleSearchQueryChainElement) this, fields);
	}

	default GroupByClause groupBy(final AliasedField firstField, final AliasedField... restFields)
	{
		final List<Field> fields = toList(firstField, restFields);
		return new GroupByClause((AbstractFlexibleSearchQueryChainElement) this, fields);
	}
}
