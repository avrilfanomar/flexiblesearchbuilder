package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.ALIAS_AND_FIELD_SEPARATOR;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.CLOSING_BRACKET;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryChainElement.OPENING_BRACKET;

/**
 * Represents field reference with a given alias.
 */
public class AliasedField extends SimpleField
{
	private final Alias alias;

	AliasedField(final Alias alias, final String fieldName)
	{
		super(fieldName);
		this.alias = alias;
	}

	Alias getAlias()
	{
		return alias;
	}

	@Override
	public String toString()
	{
		return OPENING_BRACKET + alias.getAliasValue() + ALIAS_AND_FIELD_SEPARATOR + getFieldName() + CLOSING_BRACKET;
	}
}
