package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.CLOSING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.FIELD_SEPARATOR;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.OPENING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.VarargCollectionUtils.toList;

import java.util.List;
import java.util.stream.Collectors;


public class FlexibleSearchBuilderFieldUtils
{
	public static String buildFieldsQueryString(final List<String> fieldReferences)
	{
		return fieldReferences.stream().collect(
				Collectors.joining(CLOSING_BRACKET + FIELD_SEPARATOR + OPENING_BRACKET, OPENING_BRACKET, CLOSING_BRACKET));
	}

	public static List<String> toFieldList(final AliasedField firstField, final AliasedField... restFields)
	{
		return toList(firstField, restFields).stream().map(AliasedField::getValue).collect(Collectors.toList());
	}
}
