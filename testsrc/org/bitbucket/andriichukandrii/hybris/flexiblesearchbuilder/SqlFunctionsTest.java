package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.avg;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.count;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.max;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.min;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.sum;
import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import org.junit.Test;


@UnitTest
public class SqlFunctionsTest
{

	@Test
	public void testCount()
	{
		final FlexibleSearchQuery query =
				select(
						FunctionWithType.of(count(ProductModel.PK), Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT COUNT({pk}) FROM {Product}", query.getQuery());
	}
	
	@Test
	public void testSum()
	{
		final FlexibleSearchQuery query =
				select(
						FunctionWithType.of(sum(ProductModel.PK), Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT SUM({pk}) FROM {Product}", query.getQuery());
	}

	@Test
	public void testMin()
	{
		final FlexibleSearchQuery query =
				select(
						FunctionWithType.of(min(ProductModel.PK), Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT MIN({pk}) FROM {Product}", query.getQuery());
	}
	
	@Test
	public void testMax()
	{
		final FlexibleSearchQuery query =
				select(
						FunctionWithType.of(max(ProductModel.PK), Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT MAX({pk}) FROM {Product}", query.getQuery());
	}

	@Test
	public void testAvg()
	{
		final FlexibleSearchQuery query =
				select(
						FunctionWithType.of(avg(ProductModel.PK), Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.build();

		assertEquals("Query is not as expected", "SELECT AVG({pk}) FROM {Product}", query.getQuery());
	}
}
