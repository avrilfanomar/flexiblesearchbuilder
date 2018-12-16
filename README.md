# Flexible search query builder

Flexible search query builder is SAP Hybris Commerce extension that provides developer-friendly way to build flexible search queries.
The aim of this extension is to `write().flexibleSearchQueries().easily()`

## Installation

It is a regular extension and should be added either to localextensions.xml or to other extension dependencies.

## Usage

Here's the examples of using the flexible search query builder
```java
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.field;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.select;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NULL;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.variants.model.VariantProductModel;

import org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Alias;

final FlexibleSearchQuery query1 = 
    selectFrom(CatalogUnawareMediaModel.class)
    .where(
		field(CatalogUnawareMediaModel.CODE, IS_EQUAL_TO, "someLogoCode")
		.and()
		.field(CatalogUnawareMediaModel.REALFILENAME, IS_NOT_NULL))
    .build();

final Alias p = new Alias("p");
final FlexibleSearchQuery query2 = 
    selectFrom(ProductModel.class).as(p)
    .where(
    		field(p.field(ProductModel.NAME), IS_NULL)
    		.or()
    		.field(p.field(ProductModel.CODE), IS_EQUAL_TO, "123"))
    .build();

final Alias v = new Alias("v");
final FlexibleSearchQuery query3 =
    select(p)
    .from(ProductModel.class).as(p)
        .join(VariantProductModel.class).as(v)
            .on(p.pk(), IS_EQUAL_TO, v.field(VariantProductModel.BASEPRODUCT))
    .build();
```

## Contributing
Pull requests are welcome. If you're planning to contribute, drop me a [mail](mailto:andrey000mar@gmail.com) as currently the process is not formal yet.

## License
[MIT](https://choosealicense.com/licenses/mit/)