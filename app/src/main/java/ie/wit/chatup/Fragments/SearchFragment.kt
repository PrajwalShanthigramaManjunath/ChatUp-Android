package ie.wit.chatup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
            mUsers = ArrayList()
            retrieveAllUsers()
            return view
        }

        private fun retrieveAllUsers() {
            var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
            val refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

        }
    }