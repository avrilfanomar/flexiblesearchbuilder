package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


/**
 * Element of the 'FROM' clause that holds a model type, marking the database table.
 */
public abstract class AbstractTableFromClauseElement extends AbstractFromClauseElement
{
	private final String typeCode;

	AbstractTableFromClauseElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent);
		this.typeCode = fetchTypeCode(clazz);
	}

	AbstractTableFromClauseElement(final AbstractFlexibleSearchQueryChainElement parent, final String typeCode)
	{
		super(parent);
		this.typeCode = typeCode;
	}

	protected String getTypecode()
	{
		return typeCode;
	}

	private String fetchTypeCode(final Class<? extends ItemModel> clazz)
	{
		try
		{
			return clazz.getField("_TYPECODE").get(null).toString();
		}
		catch (final IllegalAccessException | NoSuchFieldException e)
		{
			throw new IllegalStateException("Failed to get _TYPECODE field from class " + clazz.getName()
					+ " during building flexible search query.", e);
		}
	}
}
