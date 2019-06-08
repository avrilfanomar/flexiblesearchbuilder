package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.ASC;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.OrderBySortingType.DESC;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toList;

import java.util.List;
import java.util.stream.Collectors;


public interface OrderByAcceptable
{
	default OrderByClause orderByAsc(final String firstField, final String... restFields)
	{
		final List<FieldRepresentation> fields = toList(firstField, restFields).stream().map(SimpleField::new).collect(Collectors.toList());
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, ASC);
	}

	default OrderByClause orderByAsc(final FieldRepresentation firstField, final FieldRepresentation... restFields)
	{
		final List<FieldRepresentation> fields = toList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, ASC);
	}

	default OrderByClause orderByDesc(final String firstField, final String... restFields)
	{
		final List<FieldRepresentation> fields = toList(firstField, restFields).stream().map(SimpleField::new).collect(Collectors.toList());
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, DESC);
	}

	default OrderByClause orderByDesc(final FieldRepresentation firstField, final FieldRepresentation... restFields)
	{
		final List<FieldRepresentation> fields = toList(firstField, restFields);
		return new OrderByClause((AbstractFlexibleSearchQueryChainElement) this, fields, DESC);
	}
}
