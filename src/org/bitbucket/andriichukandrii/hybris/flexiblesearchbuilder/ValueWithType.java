package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Represents value to result type mapping, see
 * org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FieldWithType and
 * org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FunctionWithType
 */
public abstract class ValueWithType
{
	private final Class<?> type;

	ValueWithType(final Class<?> type)
	{
		this.type = type;
	}

	/**
	 * Gets value type.
	 * 
	 * @return type
	 */
	public Class<?> getType()
	{
		return type;
	}

	/**
	 * Gets string value of this class, mapped to type.
	 * 
	 * @return string value
	 */
	public abstract String getValue();
}
