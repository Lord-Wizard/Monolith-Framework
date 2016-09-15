package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class mTable<P, Q, R>
{
	private HashMap<P, HashMap<Q, R>> matrix = new HashMap<P, HashMap<Q, R>>();
	private ArrayList<P> rows;
	private ArrayList<Q> columns;
	
	// CONSTRUCTOR
	public mTable()
	{
		rows = new ArrayList<P>();
		columns = new ArrayList<Q>();
	}
	public mTable(P[] p)
	{
		this(p, (Q[])p);
	}
	public mTable(P[] rows, Q[] columns)
	{
		for(P p : rows)
		{
			HashMap<Q, R> m = new HashMap<Q, R>();
			
			for(Q j : columns)
			{
				m.put(j, null);
			}
			
			matrix.put(p, m);
		}
		
		this.rows = new ArrayList<P>(Arrays.asList(rows));
		this.columns = new ArrayList<Q>(Arrays.asList(columns));
	}
	public mTable(ArrayList<P> p)
	{
		this(p, (ArrayList<Q>)p);
	}
	public mTable(ArrayList<P> rows, ArrayList<Q> columns)
	{
		for(P p : rows)
		{
			HashMap<Q, R> m = new HashMap<Q, R>();
			
			for(Q j : columns)
			{
				m.put(j, null);
			}
			
			matrix.put(p, m);
		}
		
		this.rows = rows;
		this.columns = columns;
	}
	
	//===================================================
	// GETTERS AND SETTERS
	//===================================================
	public R get(P p, Q q)
	{
		return matrix.get(p).get(q);
	}
	public void set(P p, Q q, R r)
	{
		if(!containsRow(p))
		{
			addRow(p);
		}
		if(!containsColumn(q))
		{
			addColumn(q);
		}
		matrix.get(p).put(q, r);
	}
	public void addRow(P p)
	{
		matrix.put(p, new HashMap<Q, R>());
		rows.add(p);
	}
	public void addColumn(Q q)
	{
		for(Entry<P, HashMap<Q, R>> e : matrix.entrySet())
		{
			e.getValue().put(q, null);
		}
		columns.add(q);
	}
	public void removeRow(P p)
	{
		matrix.remove(p);
		rows.remove(p);
	}
	public void removeColumn(Q q)
	{
		for(Entry<P, HashMap<Q, R>> e : matrix.entrySet())
		{
			e.getValue().remove(q);
		}
		columns.remove(q);
	}
	
	public HashMap<P, HashMap<Q, R>> getMatrix()
	{
		return matrix;
	}
	public ArrayList<P> getRowKeys()
	{
		return rows;
	}
	public ArrayList<Q> getColumnKeys()
	{
		return columns;
	}
	public ArrayList<R> getEntries()
	{
		ArrayList<R> list = new ArrayList<R>();
		
		for(Entry<P, HashMap<Q, R>> e1 : matrix.entrySet())
		{
			for(Entry<Q, R> e2 : e1.getValue().entrySet())
			{
				R r = matrix.get(e1.getKey()).get(e2.getKey());
				
				if(r != null)
				{
					list.add(r);
				}
			}
		}
		
		return list;
	}
	public int getRowCount()
	{
		return rows.size();
	}
	public int getColumnCount()
	{
		return columns.size();
	}

	public boolean containsRow(P p)
	{
		return rows.contains(p);
	}
	public boolean containsColumn(Q q)
	{
		return columns.contains(q);
	}

	public boolean containsObject(R r)
	{
		for(Entry<P, HashMap<Q, R>> e : matrix.entrySet())
		{
			if(e.getValue().containsValue(r))
				return true;
		}
		return false;
	}
}