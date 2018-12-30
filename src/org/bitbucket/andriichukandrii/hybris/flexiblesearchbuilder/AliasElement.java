package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;


public class AliasElement extends JoinableFromClauseElement
{
	private final Alias alias;

	AliasElement(final AbstractFlexibleSearchQueryChainElement parent, final Alias alias)
	{
		super(parent);
		this.alias = alias;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(AS).append(SPACE).append(alias.getAliasValue());
	}

}
