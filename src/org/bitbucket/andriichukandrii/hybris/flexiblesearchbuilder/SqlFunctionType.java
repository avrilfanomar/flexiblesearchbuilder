package org.bitbucket.andriichukandrii.hybris.flexiblesearchbuilder;

public enum SqlFunctionType
{
	MIN("MIN"), MAX("MAX"), AVG("AVG"), SUM("SUM"), COUNT("COUNT");


	private final String name;

	SqlFunctionType(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
