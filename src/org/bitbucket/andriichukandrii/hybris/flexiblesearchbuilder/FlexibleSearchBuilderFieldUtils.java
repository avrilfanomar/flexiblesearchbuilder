package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.FIELD_SEPARATOR;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class FlexibleSearchBuilderFieldUtils
{
	private FlexibleSearchBuilderFieldUtils()
	{
	}

	static String buildFieldsQueryString(final List<?> fields)
	{
		return fields.stream().map(Object::toString).collect(Collectors.joining(FIELD_SEPARATOR));
	}

	static String buildQueryString(final List<String> parts)
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
		while (parameterMap.get(uniqueCode) != null);

		return uniqueCode;
	}
}
