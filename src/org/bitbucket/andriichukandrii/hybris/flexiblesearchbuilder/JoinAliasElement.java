package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.AS;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.RegularParameterConditionType.IS_EQUAL_TO;


public class JoinAliasElement extends FlexibleSearchQueryInnerChainElement
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
	public JoinOnElement on(final AliasedField field1, final RegularParameterConditionType condition, final AliasedField field2)
	{
		return new JoinOnElement(this, new FieldToFieldCondition(field1, condition, field2));
	}

	/**
	 * Marks on which fields table joining should happen. Takes RegularParameterConditionType.IS_EQUAL_TO as the default
	 * condition type.
	 *
	 * @param field1
	 *           first aliased field
	 * @param field2
	 *           second aliased field
	 * @return "join on" query element
	 */
	public JoinOnElement on(final AliasedField field1, final AliasedField field2)
	{
		return new JoinOnElement(this, new FieldToFieldCondition(field1, IS_EQUAL_TO, field2));
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);
		sb.append(SPACE).append(AS).append(SPACE).append(alias.getAliasValue());
	}
}
