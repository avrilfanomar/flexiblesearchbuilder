package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;

import org.apache.log4j.Logger;


/**
 * Element of the 'FROM' clause that holds a model type, marking the database table.
 */
public abstract class AbstractTableFromClauseElement extends AbstractFromClauseElement
{
	private static final Logger LOG = Logger.getLogger(AbstractTableFromClauseElement.class);

	private final Class<? extends ItemModel> clazz;

	AbstractTableFromClauseElement(final AbstractFlexibleSearchQueryChainElement parent, final Class<? extends ItemModel> clazz)
	{
		super(parent);
		this.clazz = clazz;
	}

	protected String getTypecode()
	{
		try
		{
			return clazz.getField("_TYPECODE").get(null).toString();
		}
		catch (final IllegalAccessException | NoSuchFieldException e)
		{
			LOG.error("Failed to get _TYPECODE field from class " + clazz.getName() + " during building of flexible search query.");
			throw new IllegalStateException(e);
		}
	}
}
