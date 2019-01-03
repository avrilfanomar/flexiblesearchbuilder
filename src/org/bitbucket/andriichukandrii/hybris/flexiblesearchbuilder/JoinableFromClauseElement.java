package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

import de.hybris.platform.core.model.ItemModel;


public abstract class JoinableFromClauseElement extends AbstractFromClauseElement
{
	JoinableFromClauseElement(final AbstractFlexibleSearchQueryChainElement parent)
	{
		super(parent);
	}

	/**
	 * Join another table using given item type.
	 *
	 * @param clazz
	 *           model type
	 * @return join query element
	 */
	public InnerJoinElement join(final Class<? extends ItemModel> clazz)
	{
		return new InnerJoinElement(this, clazz);
	}

	/**
	 * Join another table using given typecode. This method is designed in order to support relations, but regular model
	 * typecodes can be used as well.
	 *
	 * @param typeCode
	 *           type code, e.g.
	 * @return join query element
	 */
	public InnerJoinElement join(final String typeCode)
	{
		return new InnerJoinElement(this, typeCode);
	}

	/**
	 * Left join another table using given item type.
	 *
	 * @param clazz
	 *           model type
	 * @return join query element
	 */
	public LeftJoinElement leftJoin(final Class<? extends ItemModel> clazz)
	{
		return new LeftJoinElement(this, clazz);
	}

	/**
	 * Left join another table using given item type. This method is designed in order to support relations, but regular
	 * model typecodes can be used as well.
	 *
	 * @param typeCode
	 *           type code, e.g.
	 * @return join query element
	 */
	public LeftJoinElement leftJoin(final String typeCode)
	{
		return new LeftJoinElement(this, typeCode);
	}

	/**
	 * Right join another table using given item type.
	 *
	 * @param clazz
	 *           model type
	 * @return join query element
	 */
	public RightJoinElement rightJoin(final Class<? extends ItemModel> clazz)
	{
		return new RightJoinElement(this, clazz);
	}

	/**
	 * Right join another table using given item type.
	 *
	 * @param typeCode
	 *           type code, e.g.
	 * @return join query element
	 */
	public RightJoinElement rightJoin(final String typeCode)
	{
		return new RightJoinElement(this, typeCode);
	}
}
