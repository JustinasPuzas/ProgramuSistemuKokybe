package com.example.a05_lab


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

private const val argParam1 = "param1"
private const val argParam2 = "param2"

class ListFragment : Fragment(R.layout.fragment_list) {
    private var param1: String? = null
    private var param2: String? = null
    private val items = mutableListOf<String>("Tekstas su A raidėmis", "Be ieškomo simbolio", "Čia yra ieškomas simbolis", "Nieko nebus", "Rasi ko ieškai", "Testas su A raidėmis", "Be ieškomo simbolio", "Čia yra ieškomas simbolis", "Nieko nebus", "Rasi ko ieškai")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list: ListView = view.findViewById(R.id.listView1) as ListView
        val context = context as MainActivity
        val adapter = ArrayAdapter(context , R.layout.fragment_listview, R.id.textView, items)
        list.adapter = adapter

        list.setOnItemClickListener { adapter, view, position, id ->
            val text = list.getItemAtPosition(position) as String;
            if(text.contains("a")){
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                val bundle = Bundle()
                val secondFragment = SecondFragment()
                bundle.putString("message", text)
                secondFragment.arguments = bundle
                transaction?.replace(R.id.flFragment, secondFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }else{
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                val bundle = Bundle()
                val firstFragment = FirstFragment()
                bundle.putString("message2", text)
                firstFragment.arguments = bundle
                transaction?.replace(R.id.flFragment, firstFragment)
                transaction?.addToBackStack(null)
                transaction?.commit()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(argParam1)
            param2 = it.getString(argParam2)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(argParam1, param1)
                    putString(argParam2, param2)
                }
            }
    }
}
