# Flexible search query builder

Flexible search query builder is SAP Hybris Commerce extension (released as a library) that provides developer-friendly way to build flexible search queries.
The aim of this extension is to `write().flexibleSearchQueries().easily()` in compile-time safe manner.

## Installation

It is released as a Java library.
You can find jars at https://bitbucket.org/andriichukandrii/flexiblesearchbuilder/downloads/

For development purposes it can be added as an extension either to localextensions.xml or to other extension dependencies.

## Usage

All the elements of the builder chain are immutable (unless you pass a mutable parameter and then modify it), 
thus they can be safely reused among different queries.

Here are some examples of using the flexible search query builder (executable in groovy scripting console)
```java
import static de.hybris.platform.core.model.order.AbstractOrderEntryModel.ORDER;
import static de.hybris.platform.core.model.order.AbstractOrderEntryModel.PRODUCT;
import static de.hybris.platform.core.model.order.AbstractOrderModel.USER;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.LIKE;

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
            .join(VariantProductModel.class).as(v)
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
            condition(ProductModel.SUMMARY, IS_NULL)
            .and()
            .condition(ProductModel.NAME, IS_NOT_NULL)
            .and()
            .condition(ProductModel.DESCRIPTION, IS_NOT_NULL)
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

```

## Contributing
Pull requests are welcome. If you're planning to contribute, drop me a [mail](mailto:andrey000mar@gmail.com) as currently the process is not formal yet.

## License
[MIT](https://choosealicense.com/licenses/mit/)
