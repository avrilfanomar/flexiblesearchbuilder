package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.CLOSING_BRACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.OPENING_BRACE;


public class SqlFunction implements FieldRepresentation
{
	private final SqlFunctionType functionType;
	private final Field field;

	SqlFunction(final SqlFunctionType functionType, final String fieldName)
	{
		this.functionType = functionType;
		this.field = new SimpleField(fieldName);
	}

	SqlFunction(final SqlFunctionType functionType, final Field field)
	{
		this.functionType = functionType;
		this.field = field;
	}

	@Override
	public String toString()
	{
		return functionType.getName() + OPENING_BRACE + field.toString() + CLOSING_BRACE;
	}
}
