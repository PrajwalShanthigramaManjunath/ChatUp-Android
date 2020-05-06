package ie.wit.chatup

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import ie.wit.chatup.Fragments.ChatsFragment
import ie.wit.chatup.Fragments.SearchFragment
import ie.wit.chatup.Fragments.SettingsFragment
import ie.wit.chatup.ModelClasses.Chat
import ie.wit.chatup.ModelClasses.Users
import androidx.appcompat.widget.DialogTitle
import ie.wit.chatup.ModelClasses.Chatlist

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val ref = FirebaseDatabase.getInstance().reference.child("Chats")
        ref!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot)
            {
                val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

                var countUnreadMessages = 0

                for (dataSnapshot in p0.children)
                {
                    val chat = dataSnapshot.getValue(Chat::class.java)
                    if (chat!!.getReceiver().equals(firebaseUser!!.uid) && !chat.isIsSeen())
                    {
                        countUnreadMessages += 1
                    }
                }

                if (countUnreadMessages == 0)
                {
                    viewPagerAdapter.addFragments(ChatsFragment(), "Chats")
                }
                else
                {
                    viewPagerAdapter.addFragments(ChatsFragment(), "($countUnreadMessages) Chats")
                }

                viewPagerAdapter.addFragments(SearchFragment(), "Search")
                viewPagerAdapter.addFragments(SettingsFragment(), "Settings")
                viewPager.adapter = viewPagerAdapter
                tabLayout.setupWithViewPager(viewPager)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        //display username and profile picture
        refUsers!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists())
                {
                    val user: Users? = p0.getValue(Users::class.java)

                    user_name.text = user!!.getUserName()
                    Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(profile_image)
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return false
    }
    internal class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager)
    {
        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
        override fun getCount(): Int {
            return fragments.size
        }
        fun addFragments(fragment:Fragment, title: String)
        {
        fragments.add(fragment)
            titles.add(title)
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
private fun updateStatus(status: String)
{
    val ref = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)

    val hashMap = HashMap<String, Any>()
    hashMap["status"] = status
    ref!!.updateChildren(hashMap)
}

override fun onResume() {
    super.onResume()

    updateStatus("online")
}

override fun onPause() {
    super.onPause()

    updateStatus("offline")
}
}


