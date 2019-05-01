package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Maps field to it's type.
 */
public class FieldWithType
{
	private final String field;
	private final Class<?> type;

	private FieldWithType(final String field, final Class<?> type)
	{
		this.field = field;
		this.type = type;
	}

	/**
	 * Creates this field-to-type mapped class.
	 * 
	 * @param field
	 *           field
	 * @param type
	 *           field type
	 * @return new FieldWithType instance of given parameters
	 */
	public static FieldWithType of(final String field, final Class<?> type)
	{
		return new FieldWithType(field, type);
	}

	/**
	 * Creates this field-to-type mapping class.
	 *
	 * @param field
	 *           field
	 * @param type
	 *           field type
	 * @return new FieldWithType instance of given parameters
	 */
	public static FieldWithType of(final AliasedField field, final Class<?> type)
	{
		return new FieldWithType(field.getValue(), type);
	}

	public String getField()
	{
		return field;
	}

	public Class<?> getType()
	{
		return type;
	}
}
