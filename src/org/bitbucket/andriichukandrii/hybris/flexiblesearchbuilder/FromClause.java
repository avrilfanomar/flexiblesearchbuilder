package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 'FROM' clause of the flexible search query.
 */
public class FromClause extends AbstractFromClauseElement
{
	private static final Logger LOG = Logger.getLogger(FromClause.class);

	public static final String FROM = "FROM";

	private final Class<? extends ItemModel> clazz;

	<T extends ItemModel> FromClause(final Class<T> clazz)
	{
		super(new DefaultSelectClause());
		this.clazz = clazz;
	}

	public AliasElement as(final Alias alias)
	{
		this.endingClauseElement = false;
		return new AliasElement(this, alias);
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);
		sb.append(SPACE).append(FROM).append(SPACE).append(OPENING_BRACKET).append(getTypecode());
		closeBracketsIfNeeded(sb);
	}

	@Override
	protected Map<String, Object> buildParameters()
	{
		return Collections.emptyMap();
	}

	private String getTypecode()
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
