package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.UnaryQueryConditionType.EXISTS;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.UnaryQueryConditionType.NOT_EXISTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import org.junit.Test;


@UnitTest
public class UnaryQueryConditionsTest
{
	private final TerminateQueryChainElement commonInnerQuery =
			selectCustom("UPPER({code})")
			.from(
					table(ProductModel.class)
			);

	@Test
	public void testNotExistsQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(NOT_EXISTS, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE NOT EXISTS " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

	@Test
	public void testExistsQueryCondition()
	{
		final FlexibleSearchQuery query =
				selectFrom(ProductModel.class)
				.where(
						condition(EXISTS, commonInnerQuery)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product} WHERE EXISTS " +
				"({{SELECT UPPER({code}) FROM {Product}}})", query.getQuery());
		assertTrue("Query parameters don't match", query.getQueryParameters().isEmpty());
	}

}
