package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel.CODE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.NOT_IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterLessConditionType.IS_NOT_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;


@UnitTest
public class CollectionAndQueryConditionsTest
{
	private static final String MANUFACTURER_NAME_VALUE = "SAP";
	private static final String CATALOG_VERSION_VALUE = "Online";

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

	@Test
	public void testInQueryConditionWithParametersInInnerQuery()
	{
		final TerminateQueryChainElement innerQueryWithParameter =
				selectCustom(ProductModel.CODE)
				.from(table(ProductModel.class))
				.where(condition(ProductModel.MANUFACTURERNAME, ComparisonConditionType.EQUAL, MANUFACTURER_NAME_VALUE));


		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(ProductModel.CODE, IN, innerQueryWithParameter)
				)
				.and(
						condition(ProductModel.CATALOGVERSION, ComparisonConditionType.EQUAL, CATALOG_VERSION_VALUE)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {code} IN " +
				"({{SELECT {p.code} FROM {Product AS p} WHERE {p.manufacturerName}=?manufacturerName1}}) AND {catalogVersion}=?catalogVersion1", query.getQuery());

		final Map<String, Object> queryParams = query.getQueryParameters();
		assertNotNull("Query parameters are null", queryParams);
		assertEquals("Inner query parameter is not found", MANUFACTURER_NAME_VALUE, queryParams.get("manufacturerName1"));
		assertEquals("Outer query parameter is not found", CATALOG_VERSION_VALUE, queryParams.get("catalogVersion1"));
	}
}
