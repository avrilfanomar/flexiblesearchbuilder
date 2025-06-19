package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static de.hybris.platform.core.model.product.ProductModel.CODE;
import static de.hybris.platform.core.model.product.ProductModel.NAME;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

@UnitTest
public class UnionClausesTest
{
	@Test
	public void testSimpleUnion()
	{
		final String productCode = "prod1";
		final String orderCode = "order1";

		final TerminateQueryChainElement query1 =
				selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, productCode));
		final TerminateQueryChainElement query2 =
				selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, orderCode));

		final FlexibleSearchQuery fQuery = query1.union(query2).build();

		final String expectedQuery = "(SELECT {pk} FROM {Product} WHERE {code}=?code1) UNION (SELECT {pk} FROM {Order} WHERE {code}=?code2)";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		final Map<String, Object> params = fQuery.getQueryParameters();
		assertNotNull("Parameters map is null", params);
		assertEquals("Product code parameter does not match", productCode, params.get("code1"));
		assertEquals("Order code parameter does not match", orderCode, params.get("code2"));

		assertNotNull("Result class list is null", fQuery.getResultClassList());
		assertEquals("Result class list size is incorrect", 1, fQuery.getResultClassList().size());
		assertEquals("Result class type is incorrect", ProductModel.class, fQuery.getResultClassList().get(0));
	}

	@Test
	public void testSimpleUnionAll()
	{
		final String productCode = "prod2";
		final String orderCode = "order2";

		final TerminateQueryChainElement query1 =
				selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, productCode));
		final TerminateQueryChainElement query2 =
				selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, orderCode));

		final FlexibleSearchQuery fQuery = query1.unionAll(query2).build();

		final String expectedQuery = "(SELECT {pk} FROM {Product} WHERE {code}=?code1) UNION ALL (SELECT {pk} FROM {Order} WHERE {code}=?code2)";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		final Map<String, Object> params = fQuery.getQueryParameters();
		assertNotNull("Parameters map is null", params);
		assertEquals("Product code parameter does not match", productCode, params.get("code1"));
		assertEquals("Order code parameter does not match", orderCode, params.get("code2"));

		assertNotNull("Result class list is null", fQuery.getResultClassList());
		assertEquals("Result class list size is incorrect", 1, fQuery.getResultClassList().size());
		assertEquals("Result class type is incorrect", ProductModel.class, fQuery.getResultClassList().get(0));
	}

	@Test
	public void testMultipleUnionOperations()
	{
		final String productCode = "prod3";
		final String orderCode = "order3";
		final String userUID = "user3";

		final TerminateQueryChainElement query1 =
				selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, productCode));
		final TerminateQueryChainElement query2 =
				selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, orderCode));
		final TerminateQueryChainElement query3 =
				selectFrom(UserModel.class).where(condition(UserModel.UID, IS_EQUAL_TO, userUID));


		final FlexibleSearchQuery fQuery = query1.union(query2).unionAll(query3).build();

		final String expectedQuery =
				"(SELECT {pk} FROM {Product} WHERE {code}=?code1) UNION (SELECT {pk} FROM {Order} WHERE {code}=?code2) UNION ALL (SELECT {pk} FROM {User} WHERE {uid}=?uid1)";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		final Map<String, Object> params = fQuery.getQueryParameters();
		assertNotNull("Parameters map is null", params);
		assertEquals("Product code parameter does not match", productCode, params.get("code1"));
		assertEquals("Order code parameter does not match", orderCode, params.get("code2"));
		assertEquals("User UID parameter does not match", userUID, params.get("uid1"));

		assertNotNull("Result class list is null", fQuery.getResultClassList());
		assertEquals("Result class list size is incorrect", 1, fQuery.getResultClassList().size());
		assertEquals("Result class type is incorrect", ProductModel.class, fQuery.getResultClassList().get(0));
	}

	@Test
	public void testUnionWithComplexSelectClauses()
	{
		final String productName = "SuperProduct";
		final String orderCodeForComplexSelect = "orderForComplex";


		final TerminateQueryChainElement query1 =
				select(
						FieldWithType.of(ProductModel.CODE, String.class),
						FieldWithType.of(ProductModel.NAME, String.class)
				)
				.from(table(ProductModel.class))
				.where(condition(ProductModel.NAME, IS_EQUAL_TO, productName));

		// Q2 selects compatible types. For testing builder, we care about query construction and resultClassList.
		// Actual DB compatibility of CurrencyModel vs String for NAME is a runtime concern for Hybris.
		final TerminateQueryChainElement query2 =
				select(
						FieldWithType.of(OrderModel.CODE, String.class),
						FieldWithType.of(OrderModel.CURRENCY, CurrencyModel.class) // Simulating a different type for the second field
				)
				.from(table(OrderModel.class))
				.where(condition(OrderModel.CODE, IS_EQUAL_TO, orderCodeForComplexSelect));

		final FlexibleSearchQuery fQuery = query1.union(query2).build();

		final String expectedQuery =
				"(SELECT {p.code}, {p.name} FROM {Product AS p} WHERE {p.name}=?name1) UNION (SELECT {o.code}, {o.currency} FROM {Order AS o} WHERE {o.code}=?code1)";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		final Map<String, Object> params = fQuery.getQueryParameters();
		assertNotNull("Parameters map is null", params);
		assertEquals("Product name parameter does not match", productName, params.get("name1"));
		assertEquals("Order code parameter does not match", orderCodeForComplexSelect, params.get("code1"));

		final List<Class<?>> resultClassList = fQuery.getResultClassList();
		assertNotNull("Result class list is null", resultClassList);
		assertEquals("Result class list size is incorrect", 2, resultClassList.size());
		assertEquals("Result class type for first field is incorrect", String.class, resultClassList.get(0));
		assertEquals("Result class type for second field is incorrect", String.class, resultClassList.get(1));
	}

	@Test
	public void testUnionWithInnerOrderBy()
	{
		final TerminateQueryChainElement query1 =
				selectFrom(ProductModel.class).orderByAsc(ProductModel.CODE);
		final TerminateQueryChainElement query2 =
				selectFrom(OrderModel.class).orderByDesc(OrderModel.CODE);

		final FlexibleSearchQuery fQuery = query1.union(query2).build();

		// Observe the query. DBs might ignore ORDER BY in subqueries of UNION,
		// or it might be a syntax error depending on the DB.
		// The builder should construct it as requested.
		final String expectedQuery =
				"(SELECT {pk} FROM {Product} ORDER BY {code} ASC) UNION (SELECT {pk} FROM {Order} ORDER BY {code} DESC)";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		assertTrue("Parameters map should be empty", fQuery.getQueryParameters().isEmpty());

		assertNotNull("Result class list is null", fQuery.getResultClassList());
		assertEquals("Result class list size is incorrect", 1, fQuery.getResultClassList().size());
		assertEquals("Result class type is incorrect", ProductModel.class, fQuery.getResultClassList().get(0));
	}

	@Test
	public void testUnionWithOuterOrderBy()
	{
		final String productCodeValue = "prodOrderBy";
		final String orderCodeValue = "orderOrderBy";

		final TerminateQueryChainElement query1 =
				selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, productCodeValue));
		final TerminateQueryChainElement query2 =
				selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, orderCodeValue));

		final FlexibleSearchQuery fQuery = query1.union(query2)
				.orderByAsc(new Field(AbstractItemModel.PK, Object.class)) // Assuming PK is a common field for ordering
				.build();

		final String expectedQuery =
				"(SELECT {pk} FROM {Product} WHERE {code}=?code1) UNION (SELECT {pk} FROM {Order} WHERE {code}=?code2) ORDER BY {pk} ASC";
		assertEquals("Query string does not match", expectedQuery, fQuery.getQuery());

		final Map<String, Object> params = fQuery.getQueryParameters();
		assertNotNull("Parameters map is null", params);
		assertEquals("Product code parameter does not match", productCodeValue, params.get("code1"));
		assertEquals("Order code parameter does not match", orderCodeValue, params.get("code2"));

		assertNotNull("Result class list is null", fQuery.getResultClassList());
		assertEquals("Result class list size is incorrect", 1, fQuery.getResultClassList().size());
		assertEquals("Result class type is incorrect", ProductModel.class, fQuery.getResultClassList().get(0));
	}
}
