# Flexible search query builder

Flexible search query builder is SAP Hybris Commerce extension that provides developer-friendly way to build flexible search queries.
The aim of this extension is to `write().flexibleSearchQueries().easily()`

## Installation

It is a regular extension and should be added either to localextensions.xml or to other extension dependencies.

## Usage

Here's the example of using the flexible search query builder
```java
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.Conditions.field;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryBuilder.selectFrom;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterConditionType.IS_EQUAL_TO;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.ParameterlessConditionType.IS_NOT_NULL;

selectFrom(CatalogUnawareMediaModel.class)
    .where(field(CatalogUnawareMediaModel.CODE, IS_EQUAL_TO, logoCode)
            .and()
            .field(CatalogUnawareMediaModel.REALFILENAME, IS_NOT_NULL))
    .build()
```

## Contributing
Pull requests are welcome. If you're planning to contribute, drop me a [mail](mailto:andrey000mar@gmail.com) as currently the process is not formal yet.

## License
[MIT](https://choosealicense.com/licenses/mit/)