# Flexible search query builder

Flexible search query builder is SAP Hybris Commerce extension (released as a library) that provides developer-friendly way to build flexible search queries.
The aim of this extension is to `write().flexibleSearchQueries().easily()` with the help of IDE in a compile-time safe manner.

## Installation

It is released as a Java library.
You can find jars at https://bitbucket.org/andriichukandrii/flexiblesearchbuilder/downloads/
It is now also possible to use following Maven dependency:
```xml
<dependency>
    <groupId>org.bitbucket.andriichukandrii</groupId>
    <artifactId>flexiblesearchbuilder</artifactId>
    <version>3.2.2</version>
</dependency>
```

For development purposes it can be added as an extension either to localextensions.xml or to other extension dependencies.

## Usage

All the elements of the builder chain are immutable (unless you pass a mutable parameter and then modify it), 
thus they can be safely reused among different queries.

Here are some examples of using the flexible search query builder (executable in groovy scripting console)
```java
import static de.hybris.platform.core.model.order.AbstractOrderEntryModel.ORDER;
import static de.hybris.platform.core.model.order.AbstractOrderEntryModel.PRODUCT;
import static de.hybris.platform.core.model.order.AbstractOrderModel.USER;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.braces;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.TwoParameterConditionType.BETWEEN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.SqlFunctions.count;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.category.constants.CategoryConstants;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.variants.model.VariantProductModel;

import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.AbstractFieldCondition;
import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Alias;
import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FieldWithType;
import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FunctionWithType;
import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.JoinOnElement;


final FlexibleSearchQuery query1 = 
    selectFrom(CatalogUnawareMediaModel.class)
    .where(
        condition(CatalogUnawareMediaModel.CODE, IS_EQUAL_TO, "someLogoCode"))
    .build();

final AbstractFieldCondition nameAndDescriptionNonNull = 
    condition(ProductModel.NAME, IS_NOT_NULL)
    .and()
    .condition(ProductModel.DESCRIPTION, IS_NOT_NULL);

final FlexibleSearchQuery query2 =
    selectFrom(ProductModel.class)
    .where(
        condition(ProductModel.CODE, LIKE, "p%")
        .and()
        .condition(nameAndDescriptionNonNull)
    )
    .orderByAsc(ProductModel.NAME)
    .build();

final Alias p = new Alias("p");
final Alias v = new Alias("v");
final FlexibleSearchQuery query3 =
    select(p)
    .from(
        table(ProductModel.class).as(p)
        join(VariantProductModel.class).as(v)
            .on(p.pk(), IS_EQUAL_TO, v.field(VariantProductModel.BASEPRODUCT))
    )
    .where(
        condition(v.field(VariantProductModel.OFFLINEDATE), IS_GREATER_THAN, timeService.getCurrentTime())
    )
    .build();

final FlexibleSearchQuery query4 =
    select(
        FieldWithType.of(ProductModel.NAME, String.class),
        FieldWithType.of(ProductModel.DESCRIPTION, String.class),
        FieldWithType.of(ProductModel.PK, Long.class)
    )
    .from(
        table(ProductModel.class)
    )
    .where(
        braces(
            condition(ProductModel.SUMMARY, IS_NULL)
            .or()
            .condition(ProductModel.NAME, IS_NOT_NULL)
        )
        .and()
        .condition(ProductModel.PK, BETWEEN, 8796093054977L, 8796193054977L)
    )
    .build();

final UserModel user = userService.getAnonymousUser();
final CategoryModel category = new CategoryModel();//just for demonstration purposes

final Alias r = new Alias("r");
final Alias e = new Alias("e");
final Alias o = new Alias("o");
final Alias c2p = new Alias("c2p");
final Alias c = new Alias("c");

final JoinOnElement joinTables =
    table(ProductModel.class).as(p)
    .leftJoin(ProductReferenceModel.class).as(r)
        .on(p.pk(), r.target())
    .leftJoin(OrderEntryModel.class).as(e)
        .on(r.source(), e.field(PRODUCT))
    .leftJoin(OrderModel.class).as(o)
        .on(o.pk(), e.field(ORDER))
    .leftJoin(CategoryConstants.Relations.CATEGORYPRODUCTRELATION).as(c2p)
        .on(r.source(), c2p.target())
    .leftJoin(CategoryModel.class).as(c)
        .on(c.pk(), c2p.source());

final FlexibleSearchQuery query5 =
    select(p)
    .from(joinTables)
    .where(
        condition(o.field(USER), IS_EQUAL_TO, user)
        .and()
        .condition(c.pk(), IS_EQUAL_TO, category)
    )
    .orderByAsc(p.field(ProductModel.CODE))
    .build();

final FlexibleSearchQuery query6 = 
    select(
        FunctionWithType.of(count(o.field(OrderModel.CODE)), Long.class),
        FieldWithType.of(p.field(ProductModel.CODE), String.class)
    )
    .from(
        table(ProductModel.class).as(p)
        .join(OrderEntryModel.class).as(e)
            .on(p.pk(), e.field(OrderEntryModel.PRODUCT))
        .join(OrderModel.class).as(o)
            .on(o.pk(), e.field(OrderEntryModel.ORDER))
    )
    .groupBy(p.field(ProductModel.CODE))
    .orderByDesc(count(o.field(OrderModel.CODE)))
    .build();

```

### UNION Operations

The builder supports `UNION` and `UNION ALL` operations between queries. Parameters from all sub-queries are aggregated. The result type (e.g., `resultClassList` for `FlexibleSearchQuery`) is determined by the first query in the UNION chain.

**Simple UNION:**
```java
final TerminateQueryChainElement queryProducts =
    selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, "prod123"));

final TerminateQueryChainElement queryOrders =
    selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, "order789"));

// Results in: (SELECT {pk} FROM {Product} WHERE {code}=?code1) UNION (SELECT {pk} FROM {Order} WHERE {code}=?code2)
final FlexibleSearchQuery unionQuery = queryProducts.union(queryOrders).build();

// Parameters: {code1=prod123, code2=order789}
// Result Class: ProductModel.class
```

**Simple UNION ALL:**
```java
final TerminateQueryChainElement queryActiveProducts =
    selectFrom(ProductModel.class).where(condition(ProductModel.ONLINESTATUS, IS_EQUAL_TO, Boolean.TRUE));

final TerminateQueryChainElement queryRecentOrders =
    selectFrom(OrderModel.class).where(condition(OrderModel.CREATIONTIME, IS_GREATER_THAN, someDate));

// Results in: (SELECT {pk} FROM {Product} WHERE {onlineStatus}=?onlineStatus1) UNION ALL (SELECT {pk} FROM {Order} WHERE {creationtime}>=?creationtime1)
final FlexibleSearchQuery unionAllQuery = queryActiveProducts.unionAll(queryRecentOrders).build();
```

**Chained UNION operations:**
```java
final TerminateQueryChainElement query1 = selectFrom(ProductModel.class).where(condition(ProductModel.CODE, IS_EQUAL_TO, "A"));
final TerminateQueryChainElement query2 = selectFrom(OrderModel.class).where(condition(OrderModel.CODE, IS_EQUAL_TO, "B"));
final TerminateQueryChainElement query3 = selectFrom(UserModel.class).where(condition(UserModel.UID, IS_EQUAL_TO, "C"));

// Results in: ({Q1}) UNION ({Q2}) UNION ALL ({Q3})
final FlexibleSearchQuery chainedUnionQuery = query1.union(query2).unionAll(query3).build();

// Parameters from query1, query2, and query3 will be aggregated with unique names.
// Result Class: ProductModel.class (from query1)
```

## Contributing
Pull requests are welcome. If you're planning to contribute, drop me a [mail](mailto:92jo5eqfy@mozmail.com) as currently the process is not formal yet.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Future Considerations

This section outlines potential enhancements and modernizations that could be considered if the project's baseline Java version is raised in the future. Adopting these features could lead to a more concise, readable, and maintainable codebase.

### Java Modernization

If the minimum supported Java version for this library is increased (e.g., to a more recent LTS version like Java 11, 17, or 21), the following modern Java features could be beneficial:

*   **`var` for local variables (Java 10+):**
    *   Reduces verbosity in local variable declarations where the type is obvious from the right-hand side expression. This can make the code cleaner, especially when dealing with complex generic types returned by builder methods.

*   **Records (Java 14+):**
    *   Excellent for creating simple, immutable data carrier classes with minimal boilerplate. Classes like `ValueWithType` (and its various specific implementations like `FieldWithType`, `FunctionWithType`), or other internal data holders, could potentially be simplified into records if their primary purpose is to transparently hold data. This would automatically provide constructors, getters, `equals()`, `hashCode()`, and `toString()` methods.

*   **Sealed Classes and Interfaces (Java 15+):**
    *   Provide a way to restrict which other classes or interfaces may extend or implement them. This could be very useful for hierarchies like `AbstractCondition`, `AbstractFlexibleSearchQueryChainElement`, or `ValueWithType`. By sealing these, we can explicitly declare all permitted subtypes, enhancing compile-time checks, and making the overall design more robust and easier to reason about.

*   **Text Blocks (Java 13+):**
    *   Allow for the creation of multi-line string literals without the need for explicit line concatenation or escape characters for newlines. While the core query builder constructs queries programmatically, text blocks could be useful in test cases for defining expected query strings or within any utility classes that might handle complex, pre-formatted SQL snippets.

*   **Enhanced `switch` Expressions (Java 12+):**
    *   Offer a more concise and less error-prone way to write `switch` statements, which can be used as expressions. If there are areas in the codebase with complex conditional logic that could be modeled with a switch, this feature can improve readability and reduce the chances of fall-through errors.

Adopting these features would be a strategic decision based on the target Java environment for the library's users.
