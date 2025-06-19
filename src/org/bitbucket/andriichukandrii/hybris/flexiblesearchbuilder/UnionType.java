package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Represents the type of SQL UNION operation.
 */
public enum UnionType
{
	/**
	 * Represents a standard SQL UNION operation (removes duplicate rows).
	 */
	UNION("UNION"),
	/**
	 * Represents a SQL UNION ALL operation (includes duplicate rows).
	 */
	UNION_ALL("UNION ALL");

	private final String keyword;

	UnionType(final String keyword)
	{
		this.keyword = keyword;
	}

	/**
	 * Returns the SQL keyword for this UNION type.
	 *
	 * @return The SQL keyword (e.g., "UNION", "UNION ALL").
	 */
	public String getKeyword()
	{
		return keyword;
	}
}
