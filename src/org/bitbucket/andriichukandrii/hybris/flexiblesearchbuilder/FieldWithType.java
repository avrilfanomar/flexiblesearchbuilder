package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Maps field to it's type.
 */
public class FieldWithType extends ValueWithType
{
	private final Field field;

	private FieldWithType(final Field field, final Class<?> type)
	{
		super(type);
		this.field = field;
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
		return new FieldWithType(new SimpleField(field), type);
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
		return new FieldWithType(field, type);
	}

	@Override
	public String getValue()
	{
		return field.toString();
	}
}
