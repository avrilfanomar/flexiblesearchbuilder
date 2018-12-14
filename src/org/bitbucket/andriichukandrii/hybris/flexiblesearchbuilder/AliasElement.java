package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;

import java.util.Collections;
import java.util.Map;


public class AliasElement extends AbstractFromClauseElement
{
	public static final String AS = "AS";

	private final Alias alias;

	AliasElement(final AbstractFlexibleSearchQueryChainElement parent, final Alias alias)
	{
		super(parent);
		this.alias = alias;
	}

	/**
	 * Join another table using given item type.
	 * 
	 * @param clazz
	 *           model type
	 * @return join query element
	 */
	public JoinElement join(final Class<? extends ItemModel> clazz)
	{
		this.endingClauseElement = false;
		return new JoinElement(this, clazz);
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);

		sb.append(SPACE).append(AS).append(SPACE).append(alias.getAliasValue());
		closeBracketsIfNeeded(sb);
	}

	@Override
	protected Map<String, Object> buildParameters()
	{
		return Collections.emptyMap();
	}
}
