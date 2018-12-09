package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

/**
 * Represents flexible search query alias (usually used for tables).
 */
public class Alias
{
	private final String aliasValue;

	public Alias(final String aliasValue)
	{
		this.aliasValue = aliasValue;
	}

	/**
	 * Creates field reference with this alias.
	 * 
	 * @param fieldName
	 *           field name to be used
	 * @return field reference using this alias
	 */
	public AliasedField field(final String fieldName)
	{
		return new AliasedField(this, fieldName);
	}

	protected String getAliasValue()
	{
		return aliasValue;
	}
}
