# Flexible search query builder

Flexible search query builder is SAP Hybris Commerce extension (released as a library) that provides developer-friendly way to build flexible search queries.
The aim of this extension is to `write().flexibleSearchQueries().easily()` in compile-time safe manner.

## Installation

It is released as a Java library.

For development purposes it can be added as an extension either to localextensions.xml or to other extension dependencies.

## Usage

Here's the examples of using the flexible search query builder
```java
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.condition;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FromClauseElements.table;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_GREATER_THAN;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.variants.model.VariantProductModel;

import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Alias;


final FlexibleSearchQuery query1 = 
    selectFrom(CatalogUnawareMediaModel.class)
    .where(
        condition(CatalogUnawareMediaModel.CODE, IS_EQUAL_TO, "someLogoCode"))
    .build();

final FlexibleSearchQuery query2 = 
    selectFrom(ProductModel.class)
    .where(
        condition(ProductModel.NAME, IS_NOT_NULL)
        .or()
        .condition(ProductModel.DESCRIPTION, IS_NOT_NULL)
    )
    .build();

final Alias p = new Alias("p");
final Alias v = new Alias("v");
final FlexibleSearchQuery query3 =
    select(p)
    .from(table(ProductModel.class).as(p)
    .join(VariantProductModel.class).as(v)
            .on(p.pk(), IS_EQUAL_TO, v.field(VariantProductModel.BASEPRODUCT))
    )
    .where(
        condition(v.field(VariantProductModel.OFFLINEDATE), IS_GREATER_THAN, timeService.getCurrentTime())
    )
    .build();
```

## Contributing
Pull requests are welcome. If you're planning to contribute, drop me a [mail](mailto:andrey000mar@gmail.com) as currently the process is not formal yet.

## License
[MIT](https://choosealicense.com/licenses/mit/)