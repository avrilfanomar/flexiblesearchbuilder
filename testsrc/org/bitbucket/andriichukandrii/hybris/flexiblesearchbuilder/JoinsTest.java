package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel._STOCKLEVELPRODUCTRELATION;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import org.junit.Test;
import org.springframework.util.CollectionUtils;


@UnitTest
public class JoinsTest
{
	private final Alias o = new Alias("o");
	private final Alias e = new Alias("e");
	private final Alias p = new Alias("p");
	private final Alias sl2p = new Alias("sl2p");


	@Test
	public void testSelectWithInnerJoins()
	{
		final FlexibleSearchQuery fQuery =
				select(e)
				.from(
						table(OrderModel.class).as(o)
						.join(OrderEntryModel.class).as(e)
							.on(e.field(OrderEntryModel.ORDER), o.pk())
						.join(ProductModel.class).as(p)
							.on(e.field(OrderEntryModel.PRODUCT), p.pk())
						.join(_STOCKLEVELPRODUCTRELATION).as(sl2p)
							.on(sl2p.target(), p.pk())
				)
				.build();

		assertEquals("Query does not match", "SELECT {e.pk} FROM {Order AS o JOIN OrderEntry AS e " +
				"ON {e.order}={o.pk} JOIN Product AS p ON {e.product}={p.pk} " +
				"JOIN StockLevelProductRelation AS sl2p ON {sl2p.target}={p.pk}}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectTableByTypeCodeWithInnerJoins()
	{
		final FlexibleSearchQuery fQuery =
				select(e)
				.from(
						table(_STOCKLEVELPRODUCTRELATION).as(sl2p)
						.join(ProductModel.class).as(p)
							.on(sl2p.target(), p.pk())
						.join(OrderEntryModel.class).as(e)
							.on(e.field(OrderEntryModel.PRODUCT), p.pk())
						.join(OrderModel.class).as(o)
							.on(e.field(OrderEntryModel.ORDER), o.pk())
				)
				.build();

		assertEquals("Query does not match", "SELECT {e.pk} FROM {StockLevelProductRelation AS sl2p " +
				"JOIN Product AS p ON {sl2p.target}={p.pk} " +
				"JOIN OrderEntry AS e ON {e.product}={p.pk} " +
				"JOIN Order AS o ON {e.order}={o.pk}}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectWithRightJoin()
	{
		final FlexibleSearchQuery fQuery =
				select(e)
				.from(
						table(OrderModel.class).as(o)
						.rightJoin(OrderEntryModel.class).as(e)
							.on(e.field(OrderEntryModel.ORDER), o.pk())
				)
				.build();

		assertEquals("Query does not match", "SELECT {e.pk} FROM {Order AS o RIGHT JOIN OrderEntry AS e " +
				"ON {e.order}={o.pk}}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectWithLeftJoin()
	{
		final FlexibleSearchQuery fQuery =
				select(e)
				.from(
						table(OrderModel.class).as(o)
						.leftJoin(OrderEntryModel.class).as(e)
							.on(e.field(OrderEntryModel.ORDER), o.pk())
				)
				.build();

		assertEquals("Query does not match", "SELECT {e.pk} FROM {Order AS o LEFT JOIN OrderEntry AS e " +
				"ON {e.order}={o.pk}}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

}
