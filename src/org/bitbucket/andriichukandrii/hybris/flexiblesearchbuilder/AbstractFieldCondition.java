package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;


/**
 * Abstract condition on the model field (DB table column) of the flexible search query.
 */
public abstract class AbstractFieldCondition extends AbstractCondition
{
	protected final Field field;

	protected AbstractFieldCondition(final Field field)
	{
		super(FlexibleSearchQueryStartChainElement.INSTANCE);
		this.field = field;
	}

	protected AbstractFieldCondition(final AbstractFlexibleSearchQueryChainElement parent, final Field field)
	{
		super(parent);
		this.field = field;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		if (!FlexibleSearchQueryStartChainElement.INSTANCE.equals(parent))
		{
			sb.append(SPACE);
		}
		sb.append(field.toString());
	}
}
