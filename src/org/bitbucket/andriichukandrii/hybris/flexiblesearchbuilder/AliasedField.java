package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.ALIAS_AND_FIELD_SEPARATOR;

/**
 * Represents field reference with a given alias.
 */
public class AliasedField
{
	private final Alias alias;
	private final String fieldName;

	AliasedField(final Alias alias, final String fieldName)
	{
		this.alias = alias;
		this.fieldName = fieldName;
	}

	Alias getAlias()
	{
		return alias;
	}

	String getFieldName()
	{
		return fieldName;
	}

	public String getValue()
	{
		return alias.getAliasValue() + ALIAS_AND_FIELD_SEPARATOR + fieldName;
	}
}
