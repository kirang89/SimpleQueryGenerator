
public class QueryGenerator 
{
	private StringBuilder selectFields = new StringBuilder();
	private StringBuilder table = new StringBuilder();
	private StringBuilder fields = new StringBuilder();
	private StringBuilder operators = new StringBuilder();
	private StringBuilder values = new StringBuilder();
	private StringBuilder conditions = new StringBuilder();
	private StringBuilder groupings = new StringBuilder();
	private StringBuilder limit = new StringBuilder();
	private StringBuilder query =  new StringBuilder();
	private boolean isError = false;
	private StringBuilder errorMsg = new StringBuilder();
	
	public StringBuilder getSelectFields() {return this.selectFields;}
	public StringBuilder getTable() {return this.table;}
	public StringBuilder getFields() {return this.fields;}
	public StringBuilder getOperators() {return this.operators;}
	public StringBuilder getValues() {return this.values;}
	public StringBuilder getConditions() {return this.conditions;}
	public StringBuilder getGroupings() {return this.groupings;}
	public StringBuilder getLimit() {return this.limit;}
	
	public void setSelectFields(StringBuilder fields) {this.selectFields = fields;}
	public void setTable(StringBuilder table) {this.table = table;}
	public void setFields(StringBuilder fields) {this.fields = fields;}
	public void setOperators(StringBuilder operators) {this.operators = operators;}
	public void setValues(StringBuilder values) {this.values = values;}
	public void setGroupings(StringBuilder groupings) {this.groupings = groupings;}
	public void setLimit(StringBuilder limit) {this.limit = limit;}
	
	public QueryGenerator()
	{
		this.selectFields = null;
		this.table = null;
		this.fields = null;
		this.operators = null;
		this.values = null;
		this.conditions = null;
		this.groupings = null;
		this.limit = null;
	}
	
	public void formConditions()
	{	
		
		if (this.fields != null && this.operators != null && this.values != null)
		{
			//System.out.println(this.fields.toString());
			String[] fieldArray = this.fields.toString().split(",");
			String[] operatorArray = this.operators.toString().split(",");
			String[] valueArray = this.values.toString().split(",");
			
			int noOfFields = fieldArray.length;
			int noOfOperators = operatorArray.length;
			int noOfValues = valueArray.length;
			
			StringBuilder temp = new StringBuilder(); 
//			System.out.println("No of Fields: " + noOfFields);
//			System.out.println("No of Oparators: " + noOfOperators);
//			System.out.println("No of Values: " + noOfValues);
			
			//Need to check if the no of fields, operators and values match
			if(noOfFields == noOfOperators && noOfOperators == noOfValues && noOfFields == noOfValues)
			{
				//Now time to form the conditions for the query
				for(int i = 0; i < fieldArray.length; i++)
				{
					temp.append(fieldArray[i]);
					temp.append(operatorArray[i]);
					temp.append("'");
					temp.append(valueArray[i]);
					temp.append("'");
					
					if(i < noOfFields -1 )
						temp.append(" and ");
				}
			}
			else
			{
				this.isError = true;
				this.errorMsg.append("ERROR :: Fields, Operators and Values must match.");
				this.errorMsg.append(System.getProperty("line.separator"));
			}
			this.conditions = temp;
		}
	}
	
	public StringBuilder getQuery()
	{
		addQueryFields();
		if(isError == false)
		{
			addQueryConditions();
			addQueryGroupings();
			addQueryDataLimit();
		}
		else
			showError();
		
		return this.query;
	}
	
	private void addQueryFields()
	{
		if(this.table == null)
		{
			this.isError = true;
			this.errorMsg.append("ERROR :: Please select a table for against which the query is to be applied");
			this.errorMsg.append(System.getProperty("line.separator"));
		}
		else
			if(this.selectFields == null)
			{
				this.query.append("select * from ");
				this.query.append(this.table);
			}
		else
		{
			this.query.append("select ");
			this.query.append(this.selectFields);
			this.query.append(" from ");
			this.query.append(this.table);
		}
	}
	
	private void addQueryConditions() 
	{
		if(this.conditions != null)
		{
			this.query.append(" where ");
			this.query.append(this.conditions);
		}
	}
	
	private void addQueryGroupings() 
	{
		if(this.groupings != null)
		{
			this.query.append(" groupby ");
			this.query.append(this.groupings);
		}
	}
	
	private void addQueryDataLimit()
	{
		if(this.limit != null)
		{
			this.query.append(" limit ");
			this.query.append(this.limit);
		}
	}
	
	private void showError()
	{
		System.out.println(this.errorMsg.toString());
	}
}
