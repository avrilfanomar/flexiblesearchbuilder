package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel.CODE;
import static de.hybris.platform.core.model.product.ProductModel.NAME;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.UnaryQueryConditionType.EXISTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import org.junit.Test;


@UnitTest
public class CombineConditionsTest
{
	private final TerminateQueryChainElement commonInnerQuery = selectCustom("UPPER({code})").from(table(ProductModel.class));
	private final Alias p = new Alias("p");

	@Test
	public void testParameterlessToParameterCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.or()
						.condition(NAME, LIKE, "%test%")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL OR {name} LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "%test%", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testParameterlessToWrappedCondition()
	{
		final AbstractFieldCondition nameLikeTest = condition(NAME, LIKE, "%test%");

		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.or()
						.condition(nameLikeTest)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL OR {name} LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "%test%", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testParameterlessToParameterlessCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.and()
						.condition(CODE, IS_NULL)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL AND {code} IS NULL", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testParameterlessToInnerQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.and()
						.condition(CODE, IN, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL AND {code} IN " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testParameterlessToUnaryQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.and()
						.condition(EXISTS, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL AND EXISTS " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testParameterlessToCustomCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.and()
						.customCondition("UPPER({code})={code}")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL AND UPPER({code})={code}", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testParameterlessToBracedConditions()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NULL)
						.and()
						.braces(
								condition(CODE, IN, commonInnerQuery)
								.or()
								.condition(NAME, IS_NOT_NULL)
						)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} IS NULL AND ({code} IN " +
				"({{SELECT UPPER({code}) FROM {Product}}}) OR {name} IS NOT NULL)", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testAliasedParameterlessToParameterCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class).as(p))
				.where(
						condition(p.field(NAME), IS_NULL)
						.or()
						.condition(p.field(NAME), LIKE, "%test%")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product AS p} WHERE {p.name} IS NULL OR {p.name} LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "%test%", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testAliasedParameterlessToWrappedCondition()
	{
		final AbstractFieldCondition nameLikeTest = condition(p.field(NAME), LIKE, "%test%");

		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class).as(p))
				.where(
						condition(p.field(NAME), IS_NULL)
						.or()
						.condition(nameLikeTest)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product AS p} WHERE {p.name} IS NULL OR {p.name} LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "%test%", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testAliasedParameterlessToParameterlessCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class).as(p))
				.where(
						condition(p.field(NAME), IS_NULL)
						.and()
						.condition(p.field(CODE), IS_NULL)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product AS p} WHERE {p.name} IS NULL AND {p.code} IS NULL", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testAliasedParameterlessToInnerQueryCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class).as(p))
				.where(
						condition(p.field(NAME), IS_NULL)
						.and()
						.condition(p.field(CODE), IN, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product AS p} WHERE {p.name} IS NULL AND {p.code} IN " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}
}
