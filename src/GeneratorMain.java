
public class GeneratorMain 
{
	public static void main(String[] args) 
	{
		StringBuilder selectFields = new StringBuilder(); 
		selectFields.append("id,name,age,random");
		StringBuilder table = new StringBuilder();
		table.append("RandomTable");
		StringBuilder fields = new StringBuilder();
		fields.append(("id,age,name  ").trim());
		StringBuilder operators = new StringBuilder();
		operators.append("=,>=,!=");
		StringBuilder values = new StringBuilder();
		values.append(("101,25,Arjun  ").trim());
		StringBuilder groupings = new StringBuilder();
		groupings.append("id,name");
		StringBuilder limit = new StringBuilder();
		limit.append("100");
		
		QueryGenerator q = new QueryGenerator();
		//q.setSelectFields(selectFields);
		q.setTable(table);
		q.setFields(fields);
		q.setOperators(operators);
		q.setValues(values);
		//q.setGroupings(groupings);
		//q.setLimit(limit);
		q.formConditions();
		
		StringBuilder res = q.getQuery();
		
		System.out.println("Query: " + res);
	}
}
