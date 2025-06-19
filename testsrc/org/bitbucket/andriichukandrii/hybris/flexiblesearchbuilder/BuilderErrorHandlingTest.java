package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.CollectionAndQueryConditionType.IN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.braces;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectCustom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterLessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Collection;

import org.junit.Test;


@UnitTest
public class BuilderErrorHandlingTest
{
	private static final Field OTHER_FIELD = new Field(OrderModel.CODE);

	@Test
	public void testSelectFromWithNullClass()
	{
		try
		{
			selectFrom(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	// Tests for clause methods

	@Test
	public void testFromWithNullTableOnSelectClause()
	{
		try
		{
			select(ProductModel.CODE).from(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testAsWithNullAliasOnTableFromClause()
	{
		try
		{
			FromClauseElements.table(ProductModel.class).as(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testOnWithNullLeftFieldInJoin()
	{
		try
		{
			selectFrom(ProductModel.class).join(OrderModel.class).on(null, OTHER_FIELD);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testOnWithNullRightFieldInJoin()
	{
		try
		{
			selectFrom(ProductModel.class).join(OrderModel.class).on(new Field(ProductModel.CODE), null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWhereWithNullConditionOnFromClause()
	{
		try
		{
			selectFrom(ProductModel.class).where(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWhereWithNullConditionOnJoinClause()
	{
		try
		{
			selectFrom(ProductModel.class).join(OrderModel.class).on(new Field(ProductModel.CODE), OTHER_FIELD).where(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testAndWithNullCondition()
	{
		try
		{
			selectFrom(ProductModel.class)
					.where(condition(ProductModel.CODE, IS_EQUAL_TO, "test"))
					.and(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testOrWithNullCondition()
	{
		try
		{
			selectFrom(ProductModel.class)
					.where(condition(ProductModel.CODE, IS_EQUAL_TO, "test"))
					.or(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testOrderByAscWithNullField()
	{
		try
		{
			selectFrom(ProductModel.class).orderByAsc(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testOrderByDescWithNullField()
	{
		try
		{
			selectFrom(ProductModel.class).orderByDesc(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testGroupByWithNullField()
	{
		try
		{
			selectFrom(ProductModel.class).groupBy(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	// Tests for Conditions methods

	@Test
	public void testConditionWithNullFieldNameAndParameter()
	{
		try
		{
			condition(null, IS_EQUAL_TO, "value");
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullConditionTypeAndParameter()
	{
		try
		{
			condition(ProductModel.CODE, null, "value");
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullParameterValue()
	{
		try
		{
			condition(ProductModel.CODE, IS_EQUAL_TO, null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullFieldNameAndParameterless()
	{
		try
		{
			condition(null, IS_NULL);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullConditionTypeAndParameterless()
	{
		try
		{
			condition(ProductModel.CODE, (ParameterlessConditionType) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullCollection()
	{
		try
		{
			condition(ProductModel.CODE, IN, (Collection<?>) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testConditionWithNullInnerQuery()
	{
		try
		{
			condition(ProductModel.CODE, IN, (TerminateQueryChainElement) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testBracesWithNullCondition()
	{
		try
		{
			braces(null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testSelectWithNullFirstValueWithType()
	{
		try
		{
			select((ValueWithType) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testSelectCustomWithNullStatement()
	{
		try
		{
			selectCustom(null, ProductModel.class);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testSelectCustomWithNullResultTypes()
	{
		try
		{
			selectCustom("COUNT(*)", (Class<?>[]) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testSelectWithNullAlias()
	{
		try
		{
			select((Alias) null);
			fail("Expected IllegalArgumentException");
		}
		catch (final IllegalArgumentException e)
		{
			// expected
		}
	}
}
