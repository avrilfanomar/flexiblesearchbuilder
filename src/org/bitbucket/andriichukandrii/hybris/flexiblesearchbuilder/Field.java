package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

@FunctionalInterface
public interface Field
{
	/**
	 * Returns this field's name
	 * 
	 * @return field name
	 */
	String getFieldName();

	/**
	 * Returns representation of this field, which is used in queries generation.
	 * 
	 * @return representation of this field
	 */
	String toString();
}
