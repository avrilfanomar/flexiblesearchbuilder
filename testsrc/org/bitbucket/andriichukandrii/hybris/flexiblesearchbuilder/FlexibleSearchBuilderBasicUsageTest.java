package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.braces;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.customCondition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.count;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.UnaryQueryConditionType.EXISTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.yacceleratorcore.model.ApparelProductModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.util.CollectionUtils;


//This test relies on the constants and current implementation. However, it serves as a point of safety and a reference now.
@UnitTest
@SuppressWarnings({"LawOfDemeter"})
public class FlexibleSearchBuilderBasicUsageTest
{

	@Test
	public void testSelectAllFromTableQuery()
	{
		final FlexibleSearchQuery fQuery = selectFrom(ProductModel.class).build();

		assertEquals("Query does not match", "SELECT {pk} FROM {Product}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectWithParameterlessCondition()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(VariantProductModel.class)
				.where(
						condition(VariantProductModel.NAME, IS_NOT_NULL)
				)
				.build();

		assertEquals("Query does not match", "SELECT {pk} FROM {VariantProduct} WHERE {name} IS NOT NULL", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectWithParameterCondition()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(OrderModel.class)
				.where(
						condition(OrderModel.CALCULATED, IS_EQUAL_TO, false)
				)
				.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Order} WHERE {calculated}=?calculated1", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", false, fQuery.getQueryParameters().get("calculated1"));
	}

	@Test
	public void testSelectWithCollectionParameterCondition()
	{
		final List<Gender> genders = Collections.singletonList(Gender.MALE);

		final FlexibleSearchQuery fQuery =
				selectFrom(ApparelProductModel.class)
						.where(
								condition(ApparelProductModel.GENDERS, IN, genders)
						)
						.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {ApparelProduct} WHERE {genders} IN (?genders1)", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", genders, fQuery.getQueryParameters().get("genders1"));
	}

	@Test
	public void testSelectWithCustomCondition()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(ProductModel.class)
				.where(
						customCondition("UPPER({name})={name}")
				)
				.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Product} WHERE UPPER({name})={name}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
	}

	@Test
	public void testSelectWithBracesAndParameterConditions()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(OrderModel.class)
						.where(
								braces(
									condition(OrderModel.SUBTOTAL, IS_NULL)
									.or()
									.condition(OrderModel.TOTALPRICE, IS_NULL)
								)
								.and()
								.condition(OrderModel.CALCULATED, IS_EQUAL_TO, false)
						)
						.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Order} WHERE ({subtotal} IS NULL OR {totalPrice} IS NULL) AND {calculated}=?calculated1", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", false, fQuery.getQueryParameters().get("calculated1"));
	}

	@Test
	public void testSelectWithJoin()
	{
		final Alias o = new Alias("o");
		final Alias e = new Alias("e");
		final FlexibleSearchQuery fQuery =
				select(o)
				.from(
						table(OrderModel.class).as(o)
						.join(OrderEntryModel.class).as(e)
								.on(o.pk(), IS_EQUAL_TO, e.field(OrderEntryModel.ORDER))
				)
				.build();

		assertEquals("Query does not match", "SELECT {o.pk} FROM {Order AS o JOIN OrderEntry AS e ON {o.pk}={e.order}}", fQuery.getQuery());
		assertTrue("Non-expected parameter(-s) found", CollectionUtils.isEmpty(fQuery.getQueryParameters()));
	}

	@Test
	public void testSelectFields()
	{
		final FlexibleSearchQuery fQuery =
				select(
						FieldWithType.of(ProductModel.NAME, String.class),
						FieldWithType.of(ProductModel.DESCRIPTION, String.class),
						FieldWithType.of(ProductModel.PK, Long.class)
				)
				.from(
						table(ProductModel.class)
				)
				.where(
						condition(ProductModel.SUMMARY, IS_NULL)
						.and()
						.condition(ProductModel.NAME, IS_NOT_NULL)
						.and()
						.condition(ProductModel.DESCRIPTION, IS_NOT_NULL)
				)
				.build();

		assertEquals("Query does not match", "SELECT {name},{description},{pk} FROM {Product} WHERE {summary} IS NULL" +
				" AND {name} IS NOT NULL AND {description} IS NOT NULL", fQuery.getQuery());
		assertEquals("Result classes don't match", Arrays.asList(String.class, String.class, Long.class), fQuery.getResultClassList());
	}

	@Test
	public void testSelectAliasedFields()
	{
		final Alias p = new Alias("p");
		final FlexibleSearchQuery fQuery =
				select(
						FieldWithType.of(p.field(ProductModel.NAME), String.class),
						FieldWithType.of(p.pk(), Long.class)
				)
				.from(
						table(ProductModel.class).as(p)
				)
				.where(
						condition(p.field(ProductModel.SUMMARY), IS_NULL)
						.and()
						.condition(p.field(ProductModel.NAME), IS_NOT_NULL)
				)
				.build();

		assertEquals("Query does not match", "SELECT {p.name},{p.pk} FROM {Product AS p} WHERE {p.summary} IS NULL" +
				" AND {p.name} IS NOT NULL", fQuery.getQuery());
		assertEquals("Result classes don't match", Arrays.asList(String.class, Long.class), fQuery.getResultClassList());
	}

	@Test
	public void testSelectWithCustomStatement()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"DISTINCT {code}"
				)
				.from(
						table(ProductModel.class)
				)
				.build();
		assertEquals("Query does not match", "SELECT DISTINCT {code} FROM {Product}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
	}

	@Test
	public void testSelectWithCustomStatementAndResultTypes()
	{
		final FlexibleSearchQuery fQuery =
				selectCustom(
						"COUNT({pk}),{name}", Long.class, String.class
				)
				.from(
						table(ProductModel.class)
				)
				.groupBy(ProductModel.NAME)
				.build();
		assertEquals("Query does not match", "SELECT COUNT({pk}),{name} FROM {Product} GROUP BY {name}", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
		assertEquals("Result classes don't match", Arrays.asList(Long.class, String.class), fQuery.getResultClassList());
	}

	@Test
	public void testSelectWithInnerQueryFieldCondition()
	{
		final TerminateQueryChainElement innerQuery =
				selectFrom(MediaModel.class)
				.where(
						condition(MediaModel.REMOVABLE, IS_EQUAL_TO, true)
				);
		final FlexibleSearchQuery fQuery =
				selectFrom(ProductModel.class)
						.where(
								condition(ProductModel.LOGO, IN, innerQuery)
								.and()
								.condition(ProductModel.SUMMARY, IS_NULL)
						)
						.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Product} WHERE {logo} IN " +
				"({{SELECT {pk} FROM {Media} WHERE {removable}=?removable1}}) AND {summary} IS NULL", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", true, fQuery.getQueryParameters().get("removable1"));
	}

	@Test
	public void testSelectWithInnerQueryUnaryCondition()
	{
		final Alias m = new Alias("m");
		final Alias p = new Alias("p");
		final TerminateQueryChainElement innerQuery =
				select(m)
				.from(
						table(MediaModel.class).as(m)
				)
				.where(
						condition(m.field(MediaModel.REMOVABLE), IS_EQUAL_TO, false)
						.and()
						.condition(m.field(MediaModel.MODIFIEDTIME), IS_GREATER_THAN, p.field(ProductModel.MODIFIEDTIME))
				);
		final FlexibleSearchQuery fQuery =
				select(p)
				.from(
						table(ProductModel.class).as(p)
				)
				.where(
						condition(EXISTS, innerQuery)
				)
				.build();
		assertEquals("Query does not match", "SELECT {p.pk} FROM {Product AS p} WHERE EXISTS " +
				"({{SELECT {m.pk} FROM {Media AS m} WHERE {m.removable}=?removable1 AND {m.modifiedtime}>{p.modifiedtime}}})", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 1, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", false, fQuery.getQueryParameters().get("removable1"));
	}

	@Test
	public void testSelectWithOrderBy()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(ProductModel.class)
				.orderByAsc(ProductModel.CODE, ProductModel.CATALOGVERSION)
				.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Product} ORDER BY {code} ASC,{catalogVersion} ASC", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
	}

	@Test
	public void testSelectWithGroupByAndOrderBy()
	{
		final FlexibleSearchQuery fQuery =
				selectFrom(ProductModel.class)
				.groupBy(ProductModel.PK, ProductModel.CODE, ProductModel.CATALOGVERSION)
				.orderByDesc(ProductModel.CODE)
				.build();
		assertEquals("Query does not match", "SELECT {pk} FROM {Product} GROUP BY {pk},{code},{catalogVersion} ORDER BY {code} DESC", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
	}

	@Test
	public void testSelectWithGroupByAndFunctions()
	{
		final Alias p = new Alias("p");
		final Alias o = new Alias("o");
		final Alias oe = new Alias("oe");

		final FlexibleSearchQuery fQuery =
				select(
						FunctionWithType.of(count(o.field(OrderModel.CODE)), Long.class),
						FieldWithType.of(p.field(ProductModel.CODE), String.class)
				)
				.from(
						table(ProductModel.class).as(p)
						.join(OrderEntryModel.class).as(oe)
								.on(p.pk(), oe.field(OrderEntryModel.PRODUCT))
						.join(OrderModel.class).as(o)
								.on(o.pk(), oe.field(OrderEntryModel.ORDER))
				)
				.groupBy(p.field(ProductModel.CODE))
				.orderByDesc(count(o.field(OrderModel.CODE)))
				.build();
		assertEquals("Query does not match", "SELECT COUNT({o.code}),{p.code} FROM {Product AS p JOIN OrderEntry " +
				"AS oe ON {p.pk}={oe.product} JOIN Order AS o ON {o.pk}={oe.order}} GROUP BY {p.code} ORDER BY COUNT({o.code}) DESC", fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 0, fQuery.getQueryParameters().size());
	}

	@Test
	public void testSelectWithJoinAndDifferentConditions()
	{
		final Alias productAlias = new Alias("p");
		final Alias entryAlias = new Alias("e");
		final Alias orderAlias = new Alias("o");
		final String productCode = "23191";//product code from electronics store sample data
		final Double priceBarrier = 50d;
		final FlexibleSearchQuery fQuery =
				select(orderAlias)
				.from(
						table(OrderModel.class).as(orderAlias)
						.leftJoin(OrderEntryModel.class).as(entryAlias)
								.on(orderAlias.pk(), IS_EQUAL_TO, entryAlias.field(OrderEntryModel.ORDER))
						.join(ProductModel.class).as(productAlias)
								.on(productAlias.pk(), IS_EQUAL_TO, entryAlias.field(OrderEntryModel.PRODUCT))
				)
				.where(
						condition(productAlias.field(ProductModel.CODE), IS_EQUAL_TO, productCode)
						.and()
						.condition(orderAlias.field(OrderModel.TOTALPRICE), IS_GREATER_THAN, priceBarrier)
				)
				.groupBy(orderAlias.pk(), orderAlias.field(OrderModel.CODE), entryAlias.field(OrderEntryModel.ENTRYNUMBER))
				.orderByDesc(orderAlias.field(OrderModel.CODE), entryAlias.field(OrderEntryModel.ENTRYNUMBER))
				.build();

		assertEquals("Query does not match", "SELECT {o.pk} FROM {Order AS o LEFT JOIN OrderEntry AS e ON " +
				"{o.pk}={e.order} JOIN Product AS p ON {p.pk}={e.product}} WHERE {p.code}=?code1 AND {o.totalPrice}>?totalPrice1" +
				" GROUP BY {o.pk},{o.code},{e.entryNumber} ORDER BY {o.code} DESC,{e.entryNumber} DESC",
				fQuery.getQuery());
		assertEquals("Wrong number of query parameters", 2, fQuery.getQueryParameters().size());
		assertEquals("Query parameter doesn't match", productCode, fQuery.getQueryParameters().get("code1"));
		assertEquals("Query parameter doesn't match", priceBarrier, fQuery.getQueryParameters().get("totalPrice1"));
	}
}
