package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FieldWithType.of;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;


@UnitTest
public class FlexibleSearchQueryBuilderTest
{

	@Test
	public void testSelectFrom()
	{
		final FlexibleSearchQuery query = selectFrom(ProductModel.class).build();

		assertEquals("Query is not as expected", "SELECT {pk} FROM {Product}", query.getQuery());
	}

	@Test
	public void testSelectFieldsWithType()
	{
		final FlexibleSearchQuery query =
				select(of(ProductModel.CODE, String.class), of(ProductModel.UNIT, UnitModel.class))
				.from(table(ProductModel.class))
				.build();

		assertEquals("Query is not as expected", "SELECT {code},{unit} FROM {Product}", query.getQuery());
		assertEquals("Result types don't match", Arrays.asList(String.class, UnitModel.class), query.getResultClassList());
	}

	@Test
	public void testSelectCustom()
	{
		final FlexibleSearchQuery query =
				selectCustom("DISTINCT {name}", String.class)
				.from(table(ProductModel.class))
				.build();

		assertEquals("Query is not as expected", "SELECT DISTINCT {name} FROM {Product}", query.getQuery());
		assertEquals("Result types don't match", Collections.singletonList(String.class), query.getResultClassList());
	}

	@Test
	public void testSelectWithAlias()
	{
		final Alias p = new Alias("p");
		final FlexibleSearchQuery query = select(p).from(table(ProductModel.class).as(p)).build();

		assertEquals("Query is not as expected", "SELECT {p.pk} FROM {Product AS p}", query.getQuery());
	}
}