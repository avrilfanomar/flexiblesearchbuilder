package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.customCondition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.junit.Assert.assertEquals;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;

import org.junit.Test;

public class HavingClauseTest
{
	@Test
	public void testHavingClause()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"COUNT({pk}),{name}", Long.class, String.class
				)
				.from(table(ProductModel.class))
				.groupBy(ProductModel.NAME)
				.having(customCondition("COUNT({pk})>2"))
				.build();
		assertEquals("Query does not match", "SELECT COUNT({pk}),{name} FROM {Product} GROUP BY {name} HAVING COUNT({pk})>2", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
		assertEquals("Result classes don't match", Arrays.asList(Long.class, String.class), fQuery.getResultClassList());
	}
}