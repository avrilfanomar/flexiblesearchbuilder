package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public class ModelSelectClause extends AbstractSelectClause
{
	private final String field;

	ModelSelectClause()
	{
		field = ItemModel.PK;
	}

	ModelSelectClause(final AliasedField aliasedField)
	{
		this.field = aliasedField.getValue();
	}

	@Override
	protected void appendFieldsPart(final StringBuilder sb)
	{
		sb.append(OPENING_BRACKET).append(field).append(CLOSING_BRACKET);
	}
}
