package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public class SqlFunctions
{
	/**
	 * Creates SQL SUM() functions with given field.
	 * 
	 * @param field
	 *           field
	 * @return SUM() function
	 */
	public static SqlFunction sum(final String field)
	{
		return new SqlFunction(SqlFunctionType.SUM, field);
	}

	/**
	 * Creates SQL SUM() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return SUM() function
	 */
	public static SqlFunction sum(final AliasedField field)
	{
		return new SqlFunction(SqlFunctionType.SUM, field);
	}

	/**
	 * Creates SQL COUNT() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return COUNT() function
	 */
	public static SqlFunction count(final String field)
	{
		return new SqlFunction(SqlFunctionType.COUNT, field);
	}

	/**
	 * Creates SQL COUNT() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return COUNT() function
	 */
	public static SqlFunction count(final AliasedField field)
	{
		return new SqlFunction(SqlFunctionType.COUNT, field);
	}

	/**
	 * Creates SQL AVG() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return AVG() function
	 */
	public static SqlFunction avg(final String field)
	{
		return new SqlFunction(SqlFunctionType.AVG, field);
	}

	/**
	 * Creates SQL AVG() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return AVG() function
	 */
	public static SqlFunction avg(final AliasedField field)
	{
		return new SqlFunction(SqlFunctionType.AVG, field);
	}

	/**
	 * Creates SQL MIN() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return MIN() function
	 */
	public static SqlFunction min(final String field)
	{
		return new SqlFunction(SqlFunctionType.MIN, field);
	}

	/**
	 * Creates SQL MIN() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return MIN() function
	 */
	public static SqlFunction min(final AliasedField field)
	{
		return new SqlFunction(SqlFunctionType.MIN, field);
	}

	/**
	 * Creates SQL MAX() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return MAX() function
	 */
	public static SqlFunction max(final String field)
	{
		return new SqlFunction(SqlFunctionType.MAX, field);
	}

	/**
	 * Creates SQL MAX() functions with given field.
	 *
	 * @param field
	 *           field
	 * @return MAX() function
	 */
	public static SqlFunction max(final AliasedField field)
	{
		return new SqlFunction(SqlFunctionType.MAX, field);
	}
}
