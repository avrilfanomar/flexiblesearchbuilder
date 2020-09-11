package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.FIELD_SEPARATOR;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


final class FlexibleSearchBuilderFieldUtils
{
	private FlexibleSearchBuilderFieldUtils()
	{
	}

	static String buildFieldsQueryString(final Collection<?> fields)
	{
		return fields.stream().map(Object::toString).collect(Collectors.joining(FIELD_SEPARATOR));
	}

	static String buildQueryString(final Iterable<String> parts)
	{
		return String.join(FIELD_SEPARATOR, parts);
	}

	static String createUniqueParameterCode(final Map<String, Object> parameterMap, final String fieldName)
	{
		String uniqueCode;
		int counter = 0;
		do
		{
			counter++;
			uniqueCode = fieldName + counter;
		}
		while (parameterMap.containsKey(uniqueCode));

		return uniqueCode;
	}
}
