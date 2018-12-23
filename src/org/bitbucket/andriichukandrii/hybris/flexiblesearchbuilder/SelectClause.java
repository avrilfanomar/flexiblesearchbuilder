package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.core.model.ItemModel;


/**
 * 'SELECT' clause of the flexible search query, used to query the models.
 */
public class SelectClause extends AbstractFlexibleSearchQueryChainElement
{
	public static final String SELECT = "SELECT";

	private final String field;


	SelectClause()
	{
		super(null);
		field = ItemModel.PK;
	}

	SelectClause(final String field)
	{
		super(null);
		this.field = field;
	}

	/**
	 * Builds "FROM" statement of the query.
	 * 
	 * @param lastClauseElement
	 *           ending from clause element, which is wrapping all others (if any)
	 * @return "FROM" clause of the query
	 */
	public FromClause from(final AbstractFromClauseElement lastClauseElement)
	{
		return new FromClause(this, lastClauseElement);
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);
		sb.append(SELECT).append(SPACE).append(OPENING_BRACKET).append(field).append(CLOSING_BRACKET);
	}

}
