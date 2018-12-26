package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;


/**
 * Abstract condition on the model field (DB table column) of the flexible search query.
 */
public abstract class AbstractFieldCondition extends AbstractCondition
{
	protected final String fieldName;

	protected AbstractFieldCondition(final String fieldName)
	{
		super(null);
		this.fieldName = fieldName;
	}

	protected AbstractFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final String fieldName)
	{
		super(parent);
		this.fieldName = fieldName;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(OPENING_BRACKET).append(fieldName).append(CLOSING_BRACKET);
	}
}
