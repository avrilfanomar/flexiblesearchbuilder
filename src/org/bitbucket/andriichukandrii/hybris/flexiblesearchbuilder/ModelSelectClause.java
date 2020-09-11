package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public class ModelSelectClause extends AbstractSelectClause
{
	private final Field field;

	ModelSelectClause()
	{
		field = new SimpleField(ItemModel.PK);
	}

	ModelSelectClause(final Field aliasedField)
	{
		this.field = aliasedField;
	}

	@Override
	protected void appendFieldsPart(final StringBuilder sb)
	{
		sb.append(field);
	}
}
