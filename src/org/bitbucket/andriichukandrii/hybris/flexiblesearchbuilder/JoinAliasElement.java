package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.AliasElement.AS;


public class JoinAliasElement extends AbstractFlexibleSearchQueryChainElement
{
	private final Alias alias;

	JoinAliasElement(final AbstractFlexibleSearchQueryChainElement parent, final Alias alias)
	{
		super(parent);
		this.alias = alias;
	}

	/**
	 * Marks on which fields table joining should happen.
	 * 
	 * @param field1
	 *           first aliased field
	 * @param condition
	 *           condition
	 * @param field2
	 *           second aliased field
	 * @return "join on" query element
	 */
	public JoinOnElement on(final AliasedField field1, final ParameterConditionType condition, final AliasedField field2)
	{
		return new JoinOnElement(this, new FieldToFieldCondition(field1.getValue(), condition, field2.getValue()));
	}

	@Override
	protected void apply(final StringBuilder sb)
	{
		super.apply(sb);
		sb.append(SPACE).append(AS).append(SPACE).append(alias.getAliasValue());
	}
}
