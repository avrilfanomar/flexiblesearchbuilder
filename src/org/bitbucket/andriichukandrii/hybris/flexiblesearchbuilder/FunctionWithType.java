package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


/**
 * Maps function on a field to result type.
 */
public final class FunctionWithType extends ValueWithType
{
	private final SqlFunction function;

	private FunctionWithType(final SqlFunction function, final Class<?> type)
	{
		super(type);
		this.function = function;
	}

	/**
	 * Creates this function-to-type mapping class.
	 *
	 * @param function
	 *           sql function
	 * @param type
	 *           field type
	 * @return new FieldWithType instance of given parameters
	 */
	public static FunctionWithType of(final SqlFunction function, final Class<?> type)
	{
		return new FunctionWithType(function, type);
	}

	@Override
	public String getValue()
	{
		return function.toString();
	}
}
