package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel.NAME;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_OR_EQUAL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_LESS_OR_EQUAL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_LESS_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_NOT_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.NOT_LIKE;
import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import org.junit.Test;


@UnitTest
public class ParameterAliasedFieldConditionsTest
{
	private final Alias p = new Alias("p");

	@Test
	public void testEqualCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_EQUAL_TO, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}=?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testNotEqualCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_NOT_EQUAL_TO, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}<>?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testLessThanCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_LESS_THAN, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}<?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testLessOrEqualCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_LESS_OR_EQUAL, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}<=?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testGreaterThanCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_GREATER_THAN, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}>?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testGreaterOrEqualCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), IS_GREATER_OR_EQUAL, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name}>=?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testLikeCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), LIKE, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name} LIKE ?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}

	@Test
	public void testNotLikeCondition()
	{
		final FlexibleSearchQuery query =
				select(p)
				.from(table(ProductModel.class))
				.where(
						condition(p.field(NAME), NOT_LIKE, "TEST")
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product} WHERE {p.name} NOT LIKE ?p.name1", query.getQuery());
		assertEquals("Query parameters don't match", "TEST", query.getQueryParameters().get("p.name1"));
	}
}
