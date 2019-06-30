package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.ItemModel.CREATIONTIME;
import static de.hybris.platform.core.model.product.ProductModel.NAME;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_OR_EQUAL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_LESS_OR_EQUAL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_LESS_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_NOT_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.NOT_LIKE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.TwoParameterConditionType.BETWEEN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.TwoParameterConditionType.NOT_BETWEEN;
import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Date;

import org.junit.Test;


@UnitTest
public class ParameterFieldConditionsTest
{

	@Test
	public void testEqualCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_EQUAL_TO, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}=?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testNotEqualCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_NOT_EQUAL_TO, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}<>?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testLessThanCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_LESS_THAN, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}<?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testLessOrEqualCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_LESS_OR_EQUAL, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}<=?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testGreaterThanCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_GREATER_THAN, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}>?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testGreaterOrEqualCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, IS_GREATER_OR_EQUAL, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name}>=?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testLikeCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, LIKE, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testNotLikeCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NAME, NOT_LIKE, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE {name} NOT LIKE ?name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("name1"));
	}

	@Test
	public void testBetweenCondition()
	{
		final Date dateStart = new Date();
		final Date dateEnd = new Date();
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(CREATIONTIME, BETWEEN, dateStart, dateEnd)
				)
				.build();

		assertEquals("Query is not as expected",
				"SELECT {pk} FROM {Product} WHERE {creationtime} BETWEEN ?creationtime1 AND ?creationtime2", query.getQuery());
		assertEquals("Query parameters size doesn't match", 2, query.getQueryParameters().size());
		assertEquals("Query parameter 1 doesn't match", dateStart, query.getQueryParameters().get("creationtime1"));
		assertEquals("Query parameter 2 doesn't match", dateEnd, query.getQueryParameters().get("creationtime2"));
	}

	@Test
	public void testNotBetweenCondition()
	{
		final Date dateStart = new Date();
		final Date dateEnd = new Date();
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
						.where(
								condition(CREATIONTIME, NOT_BETWEEN, dateStart, dateEnd)
						)
						.build();

		assertEquals("Query is not as expected",
				"SELECT {pk} FROM {Product} WHERE {creationtime} NOT BETWEEN ?creationtime1 AND ?creationtime2", query.getQuery());
		assertEquals("Query parameters size doesn't match", 2, query.getQueryParameters().size());
		assertEquals("Query parameter 1 doesn't match", dateStart, query.getQueryParameters().get("creationtime1"));
		assertEquals("Query parameter 2 doesn't match", dateEnd, query.getQueryParameters().get("creationtime2"));
	}
}
