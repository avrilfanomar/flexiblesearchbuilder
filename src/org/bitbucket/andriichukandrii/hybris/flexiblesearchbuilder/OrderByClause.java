package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.FIELD_SEPARATOR;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.ORDER_BY;
import static org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder.FlexibleSearchQueryConstants.SPACE;

import java.util.List;
import java.util.stream.Collectors;


public class OrderByClause extends TerminateQueryChainElement
{
	private final List<FieldRepresentation> fields;
	private final OrderBySortingType sortingType;

	OrderByClause(final AbstractFlexibleSearchQueryChainElement parent, final List<FieldRepresentation> fields,
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

		sb.append(SPACE).append(ORDER_BY).append(SPACE).append(joinFields()).append(SPACE).append(sortingType.getOperator());
	}

	private String joinFields()
	{
		final String delimiter = SPACE + sortingType.getOperator() + FIELD_SEPARATOR;
		return fields.stream().map(Object::toString).collect(Collectors.joining(delimiter));
	}
}
