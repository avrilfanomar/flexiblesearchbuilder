package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchBuilderFieldUtils.buildFieldsQueryString;

import java.util.List;


public class OrderByClause extends TerminateQueryChainElement
{
	private final List<String> fields;
	private final OrderBySortingType sortingType;

	OrderByClause(final AbstractFlexibleSearchQueryChainElement parent, final List<String> fields,
			final OrderBySortingType sortingType)
	{
		super(parent);
		this.fields = fields;
		this.sortingType = sortingType;
	}

	@Override
	protected void appendQuery(final StringBuilder sb)
	{
		super.appendQuery(sb);

		sb.append(SPACE).append(ORDER_BY).append(SPACE).append(buildFieldsQueryString(fields)).append(SPACE)
				.append(sortingType.getOperator());
	}
}
