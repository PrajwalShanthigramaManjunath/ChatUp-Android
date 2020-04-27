package ie.wit.chatup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ie.wit.chatup.AdapterClasses.UserAdapter
import ie.wit.chatup.ModelClasses.Users
import ie.wit.chatup.R


    class SearchFragment : Fragment() {
private var userAdapter: UserAdapter? = null
        private var mUsers: List<Users>? = null

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view: View = inflater.inflate(R.layout.fragment_search, container, false)
            return view
        }
}