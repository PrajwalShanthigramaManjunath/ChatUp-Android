package ie.wit.chatup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
            val refUsers = FirebaseDatabase.getInstance().reference.child("Users")
            refUsers.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    (mUsers as ArrayList<Users>).clear()               // clear any array list
                    for (snapshot in p0.children) {                          // get all the users
                        val user: Users? = p0.getValue(Users::class.java)
                        if (!(user!!.getUID()).equals(firebaseUserID))  // to make user profile not seen in search list
                        {
                            (mUsers as ArrayList<Users>).add(user)
                        }
                    }
                    userAdapter = UserAdapter(context!!, mUsers!!, false)
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })

        }

//to search for a particular name
private fun (str: String )
    }
