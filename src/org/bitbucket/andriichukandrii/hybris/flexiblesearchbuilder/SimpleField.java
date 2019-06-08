package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.CLOSING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.OPENING_BRACKET;


class SimpleField implements Field, FieldRepresentation
{
	private final String fieldName;

	SimpleField(final String fieldName)
	{
		this.fieldName = fieldName;
	}

	@Override
	public String getFieldName()
	{
		return fieldName;
	}

	@Override
	public String toString()
	{
		return OPENING_BRACKET + fieldName + CLOSING_BRACKET;
	}
}
