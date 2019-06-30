package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


/**
 * Represents flexible search query alias (usually used for tables).
 */
public class Alias
{
	private static final String TARGET = "target";
	private static final String SOURCE = "source";

	private final String aliasValue;

	public Alias(final String aliasValue)
	{
		this.aliasValue = aliasValue;
	}

	/**
	 * Creates alias from a given string value.
	 * 
	 * @param aliasValue
	 *           alias string
	 * @return alias
	 */
	public static Alias alias(final String aliasValue)
	{
		return new Alias(aliasValue);
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

	/**
	 * Creates PK field reference with this alias.
	 * 
	 * @return PK field reference using this alias
	 */
	public AliasedField pk()
	{
		return new AliasedField(this, ItemModel.PK);
	}

	/**
	 * Creates aliased "target" field.
	 * 
	 * @return "target" aliased field
	 */
	public AliasedField target()
	{
		return new AliasedField(this, TARGET);
	}

	/**
	 * Creates aliased "source" field.
	 * 
	 * @return "source" aliased field
	 */
	public AliasedField source()
	{
		return new AliasedField(this, SOURCE);
	}
}
