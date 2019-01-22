package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;
import static org.junit.Assert.assertEquals;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;

import org.junit.Test;

public class GroupByClauseTest
{
	@Test
	public void testGroupByClauseWithOneField()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"COUNT({pk}),{name}", Long.class, String.class
				)
				.from(table(ProductModel.class))
				.groupBy(ProductModel.NAME)
				.build();
		assertEquals("Query does not match", "SELECT COUNT({pk}),{name} FROM {Product} GROUP BY {name}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
		assertEquals("Result classes don't match", Arrays.asList(Long.class, String.class), fQuery.getResultClassList());
	}

	@Test
	public void testGroupByClauseWithThreeFields()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"COUNT({pk}),{name}", Long.class, String.class
				)
				.from(table(ProductModel.class))
				.groupBy(ProductModel.CODE, ProductModel.NAME, ProductModel.DESCRIPTION)
				.build();
		assertEquals("Query does not match", "SELECT COUNT({pk}),{name} FROM {Product} GROUP BY {code},{name},{description}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
		assertEquals("Result classes don't match", Arrays.asList(Long.class, String.class), fQuery.getResultClassList());
	}

	@Test
	public void testGroupByClauseWithWhereClause()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"COUNT({pk}),{name}", Long.class, String.class
				)
				.from(table(ProductModel.class))
				.where(condition(ProductModel.NAME, LIKE, "% for %"))
				.groupBy(ProductModel.NAME)
				.build();
		assertEquals("Query does not match", "SELECT COUNT({pk}),{name} FROM {Product} WHERE {name} LIKE ?name1 GROUP BY {name}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Parameter doesn't match", "% for %", fQuery.getQueryParameters().get("name1"));
		assertEquals("Result classes don't match", Arrays.asList(Long.class, String.class), fQuery.getResultClassList());
	}
}
