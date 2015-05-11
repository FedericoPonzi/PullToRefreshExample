package com.informaticalab.pulltorefreshexample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
	private enum ItemsType
	{
		ITEMS_A,
		ITEMS_B;
	}

	private ListView mListView;
	private ArrayAdapter<String> mArrayAdapter;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private ArrayList<String> items;
	private ItemsType it;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.listview);
		//Prendo la referenza allo SwipeRefreshLayout
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		//Ed imposto il refreshlistener.
		mSwipeRefreshLayout.setOnRefreshListener(this);

		//Gli oggetti della mia listview:
		it = ItemsType.ITEMS_A;

		//Mi serve un arraylist per fare il clear
		items = new ArrayList<>(getItems(it));

		//Associo l'adapter alla listview:
		mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
		mListView.setAdapter(mArrayAdapter);
	}

	@Override
	public void onRefresh()
	{

		if (it.equals(ItemsType.ITEMS_A))
		{
			it = ItemsType.ITEMS_B;
		} else
		{
			it = ItemsType.ITEMS_A;
		}
		//Svuoto la lista
		items.clear();
		//Aggiungo i nuovi elementi
		items.addAll(getItems(it));
		//Notifico l'arrayAdapter
		mArrayAdapter.notifyDataSetChanged();
		//Blocco il refresh.
		mSwipeRefreshLayout.setRefreshing(false);
	}

	/**
	 * Metodo che ritorna una lista di giorni
	 * @param it A per i giorni lavorativi, B per i rimanenti.
	 * @return una lista di giorni
	 */
	private List<String> getItems(ItemsType it)
	{
		String[] toReturn;
		if (it.equals(ItemsType.ITEMS_A))
		{
			toReturn = new String[]{"Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi"};
		} else
		{
			toReturn = new String[]{"Sabato", "Domenica"};
		}
		return Arrays.asList(toReturn);
	}
}
