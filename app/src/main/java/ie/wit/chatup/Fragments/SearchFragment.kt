package ie.wit.chatup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ie.wit.chatup.R


    class SearchFragment : Fragment() {
        private var listener: ie.wit.chatup.SearchFragment.OnFragmentInteractionListener? = null

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_search, container, false)
        }
}