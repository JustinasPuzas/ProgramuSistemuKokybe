package com.example.a05lab


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListFragment : Fragment(R.layout.fragment_list) {
    private val items = mutableListOf<String>("Tekstas su A raidėmis", "Be ieškomo simbolio", "Čia yra ieškomas simbolis", "Nieko nebus", "Rasi ko ieškai", "Testas su A raidėmis", "Be ieškomo simbolio", "Čia yra ieškomas simbolis", "Nieko nebus", "Rasi ko ieškai")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Instert data to List Adapter
        val list: ListView = view.findViewById(R.id.listView1) as ListView
        val context = context as MainActivity
        val adapter = ArrayAdapter(context , R.layout.fragment_listview, R.id.textView, items)
        list.adapter = adapter
        
        // create listener for each element in array
        list.setOnItemClickListener { _, _, position, _ ->
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            val text = list.getItemAtPosition(position) as String;
            val bundle = Bundle()

            // if element contains letter A Call call second fragment else analyse element string
            if(text.contains("a")){
                val letterACounterFragment = SecondFragment()
                bundle.putString("message", text)
                letterACounterFragment.arguments = bundle
                transaction?.replace(R.id.flFragment, letterACounterFragment)
            }else{
                val stringDataDisplayFragment = FirstFragment()
                bundle.putString("message2", text)
                stringDataDisplayFragment.arguments = bundle
                transaction?.replace(R.id.flFragment, stringDataDisplayFragment)
            }
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

    }
}
