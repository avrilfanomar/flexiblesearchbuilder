package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel.CODE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.NOT_IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


@UnitTest
public class CollectionAndQueryConditionsTest
{
	private final TerminateQueryChainElement commonInnerQuery =
			selectCustom("UPPER({code})")
			.from(
					table(ProductModel.class)
			);

	@Test
	public void testNotInQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(CODE, NOT_IN, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {code} NOT IN " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testInQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(CODE, IN, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {code} IN " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testNotInCollectionCondition()
	{
		final List<String> codes = Arrays.asList("code1", "code2", "code3");
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(CODE, NOT_IN, codes)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {code} NOT IN (?code1)", query.getQuery());
		assertEquals("Query parameters don't match", codes, query.getQueryParameters().get("code1"));
	}

	@Test
	public void testInCollectionCondition()
	{
		final List<String> codes = Arrays.asList("code1", "code2", "code3");
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(CODE, IN, codes)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {code} IN (?code1)", query.getQuery());
		assertEquals("Query parameters don't match", codes, query.getQueryParameters().get("code1"));
	}
}
